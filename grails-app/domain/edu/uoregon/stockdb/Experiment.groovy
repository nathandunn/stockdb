package edu.uoregon.stockdb

class Experiment {

    static constraints = {
    }

    static hasMany = [
            strains:Strain
            ,phenotypes:Phenotype
    ]


    Researcher researcher
    Date whenPerformed
    String note
}
