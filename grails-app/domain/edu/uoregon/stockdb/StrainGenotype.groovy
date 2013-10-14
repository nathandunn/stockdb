package edu.uoregon.stockdb

/**
 * Host Genotype
 */
class StrainGenotype {

    static constraints = {
        name nullable: false,unique: true, blank:false, minSize: 3
    }

    static hasMany = [
            strains: Strain
    ]

    String name

    String note

}
