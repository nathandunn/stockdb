package edu.uoregon.stockdb

class Experiment {

    static constraints = {
        name nullable: false,unique: true
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
