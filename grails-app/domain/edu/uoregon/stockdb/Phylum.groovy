package edu.uoregon.stockdb

class Phylum {

    static constraints = {
    }

    static hasMany = [
            genuses: Genus
    ]

    String name
}
