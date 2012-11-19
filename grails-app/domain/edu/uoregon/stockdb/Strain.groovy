package edu.uoregon.stockdb

class Strain {

    static constraints = {
    }

    static hasMany = [
            stockLocations: StockLocation
    ]

    Genus genus
    Phylum phylum

    Origin origin
    String sequence

    PropertyValue arbitraryData

}
