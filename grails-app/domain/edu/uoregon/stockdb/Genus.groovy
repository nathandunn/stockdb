package edu.uoregon.stockdb

class Genus {

    static constraints = {
        name nullable: false,unique: true
        phylum nullable: false
    }

    static hasMany = [
            species:Species
    ]

    Phylum phylum

    String name
    Boolean host

    String getDisplayName(){
        return phylum.name + " " + name
    }
}
