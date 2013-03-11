package edu.uoregon.stockdb

class Researcher {

    static constraints = {
//        email nullable:false,email: true
        email email: true
        firstName nullable:false
        lastName nullable:false
    }
    String email
    String firstName
    String lastName

    Lab lab

    def fullName() {
        return firstName + " " + lastName
    }
}
