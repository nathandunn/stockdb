package edu.uoregon.stockdb



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(GenomeType)
class GenomeTypeTests {

    void testSomething() {
        GenomeType genomeType = new GenomeType(
                organizationName: "Rast 123"
                ,baseUrl: "http://asdfasf.com"
        )
        .save(flush: true,insert: true)
    }
}
