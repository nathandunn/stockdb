package edu.uoregon.stockdb

class Researcher {

    static constraints = {
//        email nullable:false,email: true
        email email: true
        firstName nullable:false
        lastName nullable:false
    }

    static hasMany = [
            experiments:Experiment
            ,isolateConditions:IsolateCondition
    ]

    String email
    String firstName
    String lastName

    Lab lab

    def getFullName() {
        return firstName + " " + lastName
    }
}
