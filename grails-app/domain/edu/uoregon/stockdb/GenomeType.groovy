package edu.uoregon.stockdb

class GenomeType {

    static constraints = {
        baseUrl nullable:false,url:true
        organizationName nullable:false
    }

    static hasMany = [
            genomes: Genome
    ]

    String baseUrl
    String organizationName
}
