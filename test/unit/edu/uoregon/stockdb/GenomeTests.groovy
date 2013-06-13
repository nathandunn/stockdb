package edu.uoregon.stockdb

import grails.test.mixin.TestFor
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Genome)
class GenomeTests {

    void testSomething() {
        Genome genome = new Genome(
                url: "http://asdfasdf.com"
                ,size:12.2
                ,quality: 12.2
        )
        .save(failOnError: true)
    }
}
