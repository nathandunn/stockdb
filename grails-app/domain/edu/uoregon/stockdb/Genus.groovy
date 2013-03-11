package edu.uoregon.stockdb

class Genus {

    static constraints = {
        name nullable: false,unique: true
        phylum nullable: false
    }

    Phylum phylum

    String name

    String getDisplayName(){
        return phylum.name + " " + name
    }
}
