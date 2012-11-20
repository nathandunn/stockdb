package edu.uoregon.stockdb

class HostFacility {

    static constraints = {
    }

    static hasMany = [
            origins: HostOrigin
    ]

    String name
//    String location
}
