package edu.uoregon.stockdb

class Category {

    static constraints = {
        name unique: true , nullable:false
    }

    static hasMany = [
            measuredValues: MeasuredValue
    ]

    String name


}
