package edu.uoregon.stockdb

/**
 * Host Genotype
 */
class StrainGenotype {

    static constraints = {
    }

    static hasMany = [
            strains: Strain
    ]

    String name

    String note

}
