package edu.uoregon.stockdb

class Genus {

    static constraints = {
        name nullable: false,unique: true,blank: false
        phylum nullable: false
    }

    static hasMany = [
            species:Species
    ]

    static hasOne = [
            phylum: Phylum
    ]


    Phylum phylum

    String name
    Boolean host = false

    String getDisplayName(){
        return phylum.name + " " + name
    }

    String getGenusFirst(){
        return name + "(${phylum.name})"
    }
}
