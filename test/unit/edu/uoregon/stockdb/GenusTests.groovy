package edu.uoregon.stockdb

import grails.test.mixin.Mock
import grails.test.mixin.TestFor

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Genus)
@Mock(Phylum)
class GenusTests {

    void testSomething() {
        Phylum phylum = new Phylum(
                name: "some phylum"
        )
                .save(failOnError: true)
        Genus genus = new Genus(
                name: "some genus"
                , phylum: phylum
        )
                .save(failOnError: true)

    }
}
