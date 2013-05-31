package edu.uoregon.stockdb

/**
 */
class MeasuredValueDTO implements Serializable{

    String strain
    String value
    String category
    Long id
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
