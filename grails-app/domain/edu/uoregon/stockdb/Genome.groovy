package edu.uoregon.stockdb

class Genome {

    static constraints = {
        genomeVersion nullable: true
        genomeType nullable: false
        externalId nullable: false
        strain nullable: true
    }

    // other URL
//    String url
    Float size
    Float quality
    String note
    String externalId // can't re-use genome id
    Float genomeVersion // can't re-use genome  version
    GenomeType genomeType
    Strain strain

    String getDisplay() {
//            return "${externalId} - ${genomeType.organizationName} ${quality}${size}"
        return "${externalId} - ${genomeType.organizationName}"
    }

    boolean hasValues() {
        return (genomeType || size || quality || externalId)
    }

    def renderUrl() {
        return genomeType.baseUrl + externalId
    }
}
