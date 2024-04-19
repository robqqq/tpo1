package ru.robq.tpo1;

import java.util.ArrayList;

/**
 * This class implements a Fibonacci heap data structure. Much of the
 * code in this class is based on the algorithms in Chapter 21 of the
 * "Introduction to Algorithms" by Cormen, Leiserson, Rivest, and Stein.
 * The amortized running time of most of these methods is O(1), making
 * it a very fast data structure. Several have an actual running time
 * of O(1). removeMin() and delete() have O(log n) amortized running
 * times because they do the heap consolidation.
 *
 * <p><strong>Note that this implementation is not synchronized.</strong>
 * If multiple threads access a set concurrently, and at least one of the
 * threads modifies the set, it <em>must</em> be synchronized externally.
 * This is typically accomplished by synchronizing on some object that
 * naturally encapsulates the set.</p>
 *
 * @author  Nathan Fiedler
 */
public class FibonacciHeap {
    /** Points to the minimum node in the heap. */
    private Node min;
    /** Number of nodes in the heap. If the type is ever widened,
     * (e.g. changed to long) then recalcuate the maximum degree
     * value used in the consolidate() method. */
    private int n;

    public FibonacciHeap() {

    }

    public FibonacciHeap(int node) {
        insert(node);
    }

    public FibonacciHeap(int[] array) {
        for (int node: array) {
            insert(node);
        }
    }

    /**
     * Removes all elements from this heap.
     *
     * <p><em>Running time: O(1)</em></p>
     */
    public void clear() {
        min = null;
        n = 0;
    }

    /**
     * Consolidates the trees in the heap by joining trees of equal
     * degree until there are no more trees of equal degree in the
     * root list.
     *
     * <p><em>Running time: O(log n) amortized</em></p>
     */
    private void consolidate() {
        // The magic 45 comes from log base phi of Integer.MAX_VALUE,
        // which is the most elements we will ever hold, and log base
        // phi represents the largest degree of any root list node.
        Node[] A = new Node[45];

        // For each root list node look for others of the same degree.
        Node start = min;
        Node w = min;
        do {
            Node x = w;
            // Because x might be moved, save its sibling now.
            Node nextW = w.right;
            int d = x.degree;
            while (A[d] != null) {
                // Make one of the nodes a child of the other.
                Node y = A[d];
                if (x.data > y.data) {
                    Node temp = y;
                    y = x;
                    x = temp;
                }
                if (y == start) {
                    // Because removeMin() arbitrarily assigned the min
                    // reference, we have to ensure we do not miss the
                    // end of the root node list.
                    start = start.right;
                }
                if (y == nextW) {
                    // If we wrapped around we need to check for this case.
                    nextW = nextW.right;
                }
                // Node y disappears from root list.
                y.link(x);
                // We've handled this degree, go to next one.
                A[d] = null;
                d++;
            }
            // Save this node for later when we might encounter another
            // of the same degree.
            A[d] = x;
            // Move forward through list.
            w = nextW;
        } while (w != start);

        // The node considered to be min may have been changed above.
        min = start;
        // Find the minimum key again.
        for (Node a : A) {
            if (a != null && a.data < min.data) {
                min = a;
            }
        }
    }


    /**
     * Tests if the Fibonacci heap is empty or not. Returns true if
     * the heap is empty, false otherwise.
     *
     * <p><em>Running time: O(1)</em></p>
     *
     * @return  true if the heap is empty, false otherwise.
     */
    public boolean isEmpty() {
        return min == null;
    }

    /**
     * Inserts a new data element into the heap. No heap consolidation
     * is performed at this time, the new node is simply inserted into
     * the root list of this heap.
     *
     * <p><em>Running time: O(1)</em></p>
     *
     * @param  x    data object to insert into heap.
     * @param  key  key value associated with data object.
     * @return newly created heap node.
     */
    public Node insert(int x) {
        Node node = new Node(x);
        // concatenate node into min list
        if (min != null) {
            node.right = min;
            node.left = min.left;
            min.left = node;
            node.left.right = node;
            if (x < min.data) {
                min = node;
            }
        } else {
            min = node;
        }
        n++;
        return node;
    }

    /**
     * Returns the smallest element in the heap. This smallest element
     * is the one with the minimum key value.
     *
     * <p><em>Running time: O(1)</em></p>
     *
     * @return  heap node with the smallest key, or null if empty.
     */
    public Node min() {
        return min;
    }

    /**
     * Removes the smallest element from the heap. This will cause
     * the trees in the heap to be consolidated, if necessary.
     *
     * <p><em>Running time: O(log n) amortized</em></p>
     *
     * @return  data object with the smallest key.
     */
    public void removeMin() {
        Node z = min;
        if (z == null) {
            return;
        }
        if (z.child != null) {
            z.child.parent = null;
            // for each child of z do...
            for (Node x = z.child.right; x != z.child; x = x.right) {
                // set parent[x] to null
                x.parent = null;
            }
            // merge the children into root list
            Node minleft = min.left;
            Node zchildleft = z.child.left;
            min.left = zchildleft;
            zchildleft.right = min;
            z.child.left = minleft;
            minleft.right = z.child;
        }
        // remove z from root list of heap
        z.left.right = z.right;
        z.right.left = z.left;
        if (z == z.right) {
            min = null;
        } else {
            min = z.right;
            consolidate();
        }
        // decrement size of heap
        n--;
    }

    /**
     * Returns the size of the heap which is measured in the
     * number of elements contained in the heap.
     *
     * <p><em>Running time: O(1)</em></p>
     *
     * @return  number of elements in the heap.
     */
    public int size() {
        return n;
    }

	public int count() {
		return n;
	}


	public int[] nodeArray() {
		ArrayList<Integer> l = new ArrayList<Integer>();
		if (min != null) min.addToList(l);
		return l.stream().mapToInt(i->i).toArray();
	}

    /**
     * Implements a node of the Fibonacci heap. It holds the information
     * necessary for maintaining the structure of the heap. It acts as
     * an opaque handle for the data element, and serves as the key to
     * retrieving the data from the heap.
     *
     * @author  Nathan Fiedler
     */
    public static class Node {
        /** Data object for this node, holds the key value. */
        private int data;
        /** Parent node. */
        private Node parent;
        /** First child node. */
        private Node child;
        /** Right sibling node. */
        private Node right;
        /** Left sibling node. */
        private Node left;
        /** Number of children of this node. */
        private int degree;
        /** True if this node has had a child removed since this node was
         * added to its parent. */
        private boolean mark;

        /**
         * Two-arg constructor which sets the data and key fields to the
         * passed arguments. It also initializes the right and left pointers,
         * making this a circular doubly-linked list.
         *
         * @param  data  data object to associate with this node
         * @param  key   key value for this data object
         */
        public Node(int data) {
            this.data = data;
            right = this;
            left = this;
        }

		public int getData() {return data;}


        /**
         * Make this node a child of the given parent node. All linkages
         * are updated, the degree of the parent is incremented, and
         * mark is set to false.
         *
         * @param  parent  the new parent node.
         */
        public void link(Node parent) {
            // Note: putting this code here in Node makes it 7x faster
            // because it doesn't have to use generated accessor methods,
            // which add a lot of time when called millions of times.
            // remove this from its circular list
            left.right = right;
            right.left = left;
            // make this a child of x
            this.parent = parent;
            if (parent.child == null) {
                parent.child = this;
                right = this;
                left = this;
            } else {
                left = parent.child;
                right = parent.child.right;
                parent.child.right = this;
                right.left = this;
            }
            // increase degree[x]
            parent.degree++;
            // set mark false
            mark = false;
        }

		public void addToList(ArrayList<Integer> l) {
			Node cur = this;
			do {
				l.add(cur.data);
				if (cur.child != null) cur.child.addToList(l);
				cur = cur.right;
			} while (cur != this);
		}
    }
}
