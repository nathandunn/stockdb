package edu.uoregon.stockdb

class Researcher {

    static constraints = {
//        email nullable:false,email: true
        username email: true,unique: true,blank: false
        firstName nullable:false
        lastName nullable:false
    }

    static hasMany = [
            experiments:Experiment
            ,isolateConditions:IsolateCondition
            ,roles: Role
            ,permissions: String
    ]

//    String email
    String username
    String passwordHash

    String firstName
    String lastName

    Lab lab

    String getEmail(){
        return username
    }

    def getFullName() {
        return firstName + " " + lastName
    }

    Boolean hasRole(String roleString) {
        for(Role role in roles){
            if(role.name==roleString) return true
        }

        return false
    }
}
