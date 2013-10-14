package edu.uoregon.stockdb

class Location {

    static constraints = {
        name nullable: false , unique: true, blank: false, minSize: 5
    }

    static hasMany = [
            stocks: Stock
    ]

    static mapping = {
//        stocks sort:["getBoxValue"], order:["asc"]
    }

    String name
}
