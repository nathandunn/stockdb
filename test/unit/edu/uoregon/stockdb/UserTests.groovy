package edu.uoregon.stockdb



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Researcher)
class UserTests {

    @Test
    void databaseCsv() {
        StubData.stubData()
    }
}
