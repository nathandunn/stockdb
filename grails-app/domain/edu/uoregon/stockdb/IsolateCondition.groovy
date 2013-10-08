package edu.uoregon.stockdb

class IsolateCondition {

    static constraints = {
        isolatedWhen nullable: true
    }

    static hasMany = [
            strains: Strain
    ]

    Researcher isolatedBy
    Date isolatedWhen

    String oxygenCondition // aerobic, microaerobic, or anaerobic
    Float temperature
    String media  // open
    String notes


    String getDisplay(){
        return "${oxygenCondition?:''} ${media?:''} ${temperature?:''} C "
    }

}
