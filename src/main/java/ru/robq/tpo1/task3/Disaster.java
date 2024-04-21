package ru.robq.tpo1.task3;

public enum Disaster {
    BOMBARDMENT("bombardment"),
    CHEMICAL_ATTACK("chemical attack"),
    GAS_ATTACK("gas attack"),
    NUCLEAR_ATTACK("nuclear attack"),
    QUIETLY("quietly");

    private String descr;

    Disaster(String descr) {
        this.descr = descr;
    }

    public String getDescr() {
        return descr;
    }  
}
