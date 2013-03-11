package edu.uoregon.stockdb

import static org.junit.Assert.*
import org.junit.*

class StubDataTests {

    StubData stubData = new StubData() ;

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testStubbing() {
        stubData.stubUsers()
        stubData.stubData()
    }
}
