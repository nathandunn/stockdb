package edu.uoregon.stockdb

class Genus {

    static constraints = {
    }

    Phylum phylum

    String name

    String getDisplayName(){
        return phylum.name + " " + name
    }
}
