package edu.uoregon.stockdb

class Role {
    String name

    static hasMany = [ users: Researcher, permissions: String ]
    static belongsTo = Researcher

    static constraints = {
        name(nullable: false, blank: false, unique: true)
    }
}
