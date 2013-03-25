package edu.uoregon.stockdb

class Phenotype {

    static constraints = {
        name nullable: false, unique: true
        url url: true,unique: true
    }

    String name
    String url // possible canonical url
}
