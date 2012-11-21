package edu.uoregon.stockdb

class Strain {

    static constraints = {
        sequence nullable: true, url: true
        values nullable: true
        formAlias nullable: true
        isolatedBy nullable: true
        isolatedWhen nullable: true
    }

    static hasMany = [
            stocks: Stock
    ]


    // will be one or the other .. . .
    Strain parentStrain
    // more like a capture
    HostOrigin origin


    String sequence

    // JSON
    String values


    String name
    String formAlias


    // properties
    double motility

    Genus genus
    Phylum phylum

    User isolatedBy
    Date isolatedWhen
}
