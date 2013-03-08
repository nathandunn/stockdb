package edu.uoregon.stockdb

/**
 * This is the university . .  where the fish was raised.
 */
class HostFacility {

    static constraints = {
    }

    static hasMany = [
            origins: Origin
    ]

    String name
//    String location
}
