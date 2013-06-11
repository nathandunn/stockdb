package edu.uoregon.stockdb



import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(HostGenotype)
class HostGenotypeTests {

    void testSomething() {
        HostGenotype hostGenotype = new HostGenotype(
                name: "WildType"
        ).save(failOnError: true)
    }
}
