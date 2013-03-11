package edu.uoregon.stockdb

class Phylum {

    static constraints = {
        name nullable: false,unique: true
    }

    static hasMany = [
            genuses: Genus
    ]

    String name
}
