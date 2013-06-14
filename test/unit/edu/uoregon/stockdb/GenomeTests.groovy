package edu.uoregon.stockdb

import grails.test.mixin.TestFor
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Genome)
class GenomeTests {

    void testSomething() {
        GenomeType genomeType = new GenomeType(
                organizationName: "Rast 123"
                ,baseUrl: "http://raststuff.com"
        )
        Genome genome = new Genome(
                genomeType:genomeType
                ,size:12.2
                ,quality: 12.2
                ,externalId: "ABC123"
        )
        .save(failOnError: true)
    }
}
