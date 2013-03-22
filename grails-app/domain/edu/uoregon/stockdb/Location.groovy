package edu.uoregon.stockdb

class Location {

    static constraints = {
    }

    static hasMany = [
            stocks: Stock
    ]

    static mapping = {
        stocks sort:"physicalLocation",order: "asc"
    }

    String name
}
