package edu.uoregon.stockdb

/**
 * Host Genotype
 */
class StrainGenotype {

    static constraints = {
        name nullable: false,unique: true
    }

    static hasMany = [
            strains: Strain
    ]

    String name

    String note

}
