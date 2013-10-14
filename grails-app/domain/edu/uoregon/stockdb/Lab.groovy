package edu.uoregon.stockdb

/**
 * Not entirely important
 */
class Lab {

    static constraints = {
        name nullable: false,unique: true,blank: false
    }

    static hasMany = [
            researchers: Researcher
    ]

    String name
}
