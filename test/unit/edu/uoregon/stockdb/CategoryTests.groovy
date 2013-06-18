package edu.uoregon.stockdb

import grails.test.mixin.TestFor

/**
 */
@TestFor(Category)
class CategoryTests {

    void testSomething() {
        Category c1 = new Category(
                name: "c1"
                , type: MeasuredValueTypeEnum.INTEGER
                , units: "inches"
        ).save(failOnError: true)

        Category c2 = new Category(
                name: "c2"
                , type: MeasuredValueTypeEnum.TEXT
                , units: "thing"
        ).save(failOnError: true)

        assertTrue(c1.compareTo(c2)<0)
    }
}
