package edu.uoregon.stockdb

/**
 * NOTE: This ONLY applies to host species
 */
class Species {

    static constraints = {
        genus nullable: false
        name nullable: false,blank: false, unique: true
//        prefix nullable: false,unique: true,blank: false
        host default: true
    }

    static hasMany = [
            genotypes: HostGenotype
    ]

    Genus genus
    String name
    String commonName
    String prefix
    Boolean host


    String getDisplayName(){
        return "${genus.name} ${name}"
    }
}
