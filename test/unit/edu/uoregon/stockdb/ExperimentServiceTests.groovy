package edu.uoregon.stockdb

import grails.test.mixin.Mock
import grails.test.mixin.TestFor

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ExperimentService)
@Mock([Experiment, Researcher, MeasuredValue,Category,Strain])
class ExperimentServiceTests {

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

        Category category = new Category(
                name: "Motility Category"
        )
        .save()

        Strain strain = new Strain()

        MeasuredValue measuredValue = new MeasuredValue(
                category: category
                , strain: strain
                , experiment: experiment
                , value: "123"
        ).save(failOnError: true,flush: true)

        category.addToMeasuredValues(measuredValue)


        Map<Category, List<MeasuredValue>> map = service.createValuesMap(experiment)
        assert 1==map.size()
        println "${map.size()}"
        Category c2 = map.keySet().iterator().next()
        assert c2.name=="Motility Category"
        MeasuredValue m2 = map.get(category).iterator().next()
        assert m2.value=="123"
    }
}
