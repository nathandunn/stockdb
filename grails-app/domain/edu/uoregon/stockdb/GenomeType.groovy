package edu.uoregon.stockdb

class GenomeType {

    static constraints = {
        baseUrl nullable:false,url:true,blank: false
        organizationName nullable:false,blank: false
    }

    static hasMany = [
            genomes: Genome
    ]

    String baseUrl
    String organizationName
}
