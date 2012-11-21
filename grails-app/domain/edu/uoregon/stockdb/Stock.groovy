package edu.uoregon.stockdb

class Stock {

    static constraints = {
        strain nullable:  true
        value nullable:  true
    }

    String name
    String value

    Location location
    Strain strain
}
