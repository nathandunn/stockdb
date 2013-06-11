package edu.uoregon.stockdb

import grails.test.mixin.TestFor
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(HostFacility)
class HostFacilityTests {

    void testSomething() {
        HostFacility hostFacility = new HostFacility(
                name: "Dow the Hall"
        )
        .save(failOnError: true)
    }
}
