package edu.uoregon.stockdb

class QuickEntryService {
    static expose = [ 'gwt:edu.uoregon.stockdb.client' ]

    String doit() {
        println "somethign back from doit "
        return "returning stuff from doit"
    }
}
