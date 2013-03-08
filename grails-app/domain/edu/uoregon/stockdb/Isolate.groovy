package edu.uoregon.stockdb

class Isolate {

    static constraints = {
    }

    Researcher researcher

    Researcher isolatedBy
    Date isolatedWhen

    String oxygenCondition // aerobic, microaerobic, or anaerobic
    Float temperature
    String media  // open
    String notes
}
