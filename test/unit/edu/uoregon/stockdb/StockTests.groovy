package edu.uoregon.stockdb

import grails.test.mixin.TestFor
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Stock)
class StockTests {

    void testSomething() {
        Stock stock = new Stock(
                boxIndex: 12
                ,boxNumber: 19
        )
        .save(failOnError: true)
    }
}
