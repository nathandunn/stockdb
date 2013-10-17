package edu.uoregon.stockdb

/**
 * This is the university . .  where the fish was raised.
 */
class HostFacility {

    static constraints = {
        name nullable: false,unique: true,blank: false
//        prefix nullable: false,unique: true,blank: false
    }

    static hasMany = [
            origins: HostOrigin
    ]

    String name
    String prefix
//    String location
}
