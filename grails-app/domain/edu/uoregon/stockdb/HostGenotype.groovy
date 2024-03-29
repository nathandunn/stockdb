package edu.uoregon.stockdb

/**
 * Host Genotype
 */
class HostGenotype {

    static constraints = {
        name nullable: false, unique: true,blank: false
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
