package edu.uoregon.stockdb

class Genome {

    static constraints = {
        url nullable: true, url: true
    }

    static hasMany = [
            strains: Strain
    ]

    String url
    Float size
    Float quality
    String note

    String getDisplay() {
        if(quality && size && note){
            return "${quality} ${size} ${note.size()>20 ? note.subSequence(0,20) + ' ... ' : note }"
        }
        else{
            return id
        }
    }

    boolean hasValues() {
        return (url || size || quality ||  note)
    }
}
