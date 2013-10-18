package edu.uoregon.stockdb

// we can have a wildtype and a non-wild-type for the same location
class Population {

    static constraints = {
//        name nullable: false, unique: true, blank: false
        name nullable: false, unique: false, blank: false
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
    Boolean wildtype


    static mapping = {
        notes type:'text'
    }
}
