package edu.uoregon.stockdb

/**
 * An independent class used to describe host or strain phenotypes
 */
class Phenotype {

    static constraints = {
        name nullable: false, unique: true
        url url: true,unique: true
    }

    static hasMany = [
            hostOrigins: HostOrigin
            ,measuredValues:MeasuredValue
    ]

    static belongsTo = [
            HostOrigin
            ,MeasuredValue
    ]

    String name
    String url // possible canonical url
}
