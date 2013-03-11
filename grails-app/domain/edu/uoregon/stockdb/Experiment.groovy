package edu.uoregon.stockdb

class Experiment {

    static constraints = {
    }

    static hasMany = [
            strains:Strain
            ,phenotypes:Phenotype
            ,measuredValues: MeasuredValue
    ]


    Researcher researcher
    Date whenPerformed
    String note
}
