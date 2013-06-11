package edu.uoregon.stockdb

class Location {

    static constraints = {
        name nullable: false , unique: true
    }

    static hasMany = [
            stocks: Stock
    ]

    static mapping = {
//        stocks sort:["getBoxValue"], order:["asc"]
    }

    String name
}
