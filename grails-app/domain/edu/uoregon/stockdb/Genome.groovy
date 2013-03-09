package edu.uoregon.stockdb

class Genome {

    static constraints = {
        url nullable: true, url: true
    }

    static hasMany = [
            strains: Strain
    ]

    String url
    Integer size
    Integer quality
    Integer note
}
