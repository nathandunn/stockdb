package edu.uoregon.stockdb

import grails.test.mixin.Mock
import grails.test.mixin.TestFor

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Species)
@Mock([Genus,Phylum])
class SpeciesTests {

    void testSomething() {
        Phylum phylum = new Phylum(
                name: "some phylum"
        )
                .save(failOnError: true)
        Species species = new Species(
                name: "Zebrafish"
                , genus: new Genus(
                name: "danio"
                , phylum: phylum
        ) )
                .save(failOnError: true)
    }
}
