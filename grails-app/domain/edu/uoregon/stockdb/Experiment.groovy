package edu.uoregon.stockdb

class Experiment {

    static constraints = {
    }

    static hasMany = [
            strains:Strain
            ,measuredValues: MeasuredValue
    ]

    String name
    Researcher researcher
    Date whenPerformed
    String note
}
