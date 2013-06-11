package edu.uoregon.stockdb

import grails.test.mixin.TestFor
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Phylum)
class PhylumTests {

    void testSomething() {
        Phylum phylum =new Phylum(
                name:"some phylum"
        )
        .save(failOnError: true)
    }
}
