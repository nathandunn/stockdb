package edu.uoregon.stockdb

/**
 */
class ExperimentDTO implements Serializable{

    private String strain ;
    private String value ;
    private String category ;
//    private String experiment ;

    String getStrain() {
        return strain
    }

    void setStrain(String strain) {
        this.strain = strain
    }

    String getValue() {
        return value
    }

    void setValue(String value) {
        this.value = value
    }

    String getCategory() {
        return category
    }

    void setCategory(String category) {
        this.category = category
    }
}
