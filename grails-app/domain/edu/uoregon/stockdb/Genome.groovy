package edu.uoregon.stockdb

class Genome {

    static constraints = {
        url nullable: true, url: true
        version nullable: true
    }

    static hasMany = [
            strains: Strain
    ]

    String url
    Float size
    Float quality
    String note
    String externalId // can't re-use genome id
    Float genomeVersion // can't re-use genome  version

    String getDisplay() {
        if(quality && size){
            return "${quality} ${size}"
        }
        else{
            return id
        }
    }

    boolean hasValues() {
        return (url || size || quality)
    }
}
