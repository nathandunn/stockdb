package edu.uoregon.stockdb

/**
 * Host Genotype
 */
class HostGenotype {

    static constraints = {
    }

    static hasMany = [
            hostOrigins: HostOrigin
    ]

    static belongsTo = [
            HostOrigin
    ]

    String name
    String zfinId
//    String note

}
