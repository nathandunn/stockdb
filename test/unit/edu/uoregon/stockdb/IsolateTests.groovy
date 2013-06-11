package edu.uoregon.stockdb



import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(IsolateCondition)
class IsolateTests {

    void testSomething() {
        IsolateCondition isolateCondition = new IsolateCondition(
                isolatedWhen: new Date()
        ).save(failOnError: true)
    }
}
