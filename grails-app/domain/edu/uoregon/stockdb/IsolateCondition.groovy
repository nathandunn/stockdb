package edu.uoregon.stockdb

class IsolateCondition {

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
