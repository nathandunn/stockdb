package edu.uoregon.stockdb



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Researcher)
class ResearcherTests {

    @Test
    void testImplement() {
        Researcher secUser = Researcher.get(1) ?: new Researcher(
                username: RandomStringGenerator.getRandomEmail()
                , passwordHash: "secret"
                , firstName: "George"
                , lastName: "TheMonkey"
        ) .save(failOnError: true,flush: true)
    }
}
