package edu.uoregon.stockdb

class Category {

    static constraints = {
        name unique: true , nullable:false
    }

    static hasMany = [
            measuredValues: MeasuredValue
            ,experiments: Experiment
    ]

    static belongsTo = [
            Experiment
    ]


    String name


}
