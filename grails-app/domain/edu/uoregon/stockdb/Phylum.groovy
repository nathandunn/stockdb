package edu.uoregon.stockdb

class Phylum {

    static constraints = {
        name nullable: false,unique: true,blank: false
    }

    static hasMany = [
            genuses: Genus
    ]

    String name
    Boolean host = false
}
