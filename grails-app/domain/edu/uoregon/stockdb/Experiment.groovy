package edu.uoregon.stockdb

class Experiment {

    static constraints = {
        name nullable: false,unique: true
    }

    static hasMany = [
            measuredValues: MeasuredValue
    ]

    String name
    Researcher researcher
    Date whenPerformed
    String note



}
