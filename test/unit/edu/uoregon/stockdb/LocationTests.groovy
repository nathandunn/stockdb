package edu.uoregon.stockdb

import grails.test.mixin.TestFor
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Location)
class LocationTests {

    void testSomething() {
        Location location = new Location(
                name:"here"
        )
        .save(failOnError: true)
    }
}
