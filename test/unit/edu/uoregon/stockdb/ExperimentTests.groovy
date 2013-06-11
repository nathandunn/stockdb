package edu.uoregon.stockdb

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Experiment)
@Mock([Experiment, Researcher])
class ExperimentTests {


    Researcher injectSecurityUser() {
        Researcher secUser = Researcher.get(1) ?: new Researcher(
                username: RandomStringGenerator.getRandomEmail()
                , passwordHash: "secret"
                , firstName: "George"
                , lastName: "TheMonkey"
        ).save(failOnError: true, flush: true)
        return secUser
    }

    void testSomething() {
        Researcher researcher = injectSecurityUser()
        Experiment experiment = new Experiment(
                name: "Motility Experiment"
                , whenPerformed: new Date()
                , note: "A note about a motility experiment"
                , researcher: researcher
        ).save(failOnError: true)
    }
}
