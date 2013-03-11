package edu.uoregon.stockdb

/**
 * This is the physical location of the Strain
 */
class Stock {

    static constraints = {
        strain nullable:  true
//        value nullable:  true
//        physicalLocation nullable: false
//        generalLocation nullable: false
    }

    String physicalLocation
//    String value

    Location generalLocation
    Strain strain
}
