package edu.uoregon.stockdb

class Population {

    static constraints = {
        name nullable: false, unique: true, blank: false
    }

    static hasMany = [
            hostOrigins: HostOrigin
    ]

    static belongsTo = [
            HostOrigin
    ]


    String name
    Double latitude
    Double longitude
    Date captureDate

    String notes
    String externalId // ZFIN, sticklebackdb, etc.


    static mapping = {
        notes type:'text'
    }
}
