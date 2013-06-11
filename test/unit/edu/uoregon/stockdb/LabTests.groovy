package edu.uoregon.stockdb



import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Lab)
class LabTests {

    void testSomething() {
        Lab lab = new Lab(
                name: "Some Lab "
        )
        .save(failOnError: true)

    }
}
