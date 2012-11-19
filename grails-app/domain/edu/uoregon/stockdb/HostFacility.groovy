package edu.uoregon.stockdb

class HostFacility {

    static constraints = {
    }

    static hasMany = [
            origins: Origin
    ]

    String name
    String location
}
