package edu.uoregon.stockdb

class Strain {

    static constraints = {
    }

    static hasMany = [
            stocks: Stock
    ]


    Origin origin
    String sequence

    // JSON
    String values


    String name
    Genus genus
    Phylum phylum

}
