package edu.uoregon.stockdb

import grails.test.mixin.TestFor
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Strain)
class StrainTests {

    void testSomething() {
        Strain strain = new Strain(
                name:"Z123"
        )
        .save(failOnError: true)
    }
}
