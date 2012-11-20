package edu.uoregon.stockdb

class Location {

    static constraints = {
    }

    static hasMany = [
            stocks: Stock
    ]

    String name
}
