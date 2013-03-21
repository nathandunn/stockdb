package edu.uoregon.stockdb

class Species {

    static constraints = {
        genus nullable: false
        name nullable: false
    }


    Genus genus
    String name
    String commonName

    String getDisplayName(){
        return "${genus.name} ${name}"
    }
}
