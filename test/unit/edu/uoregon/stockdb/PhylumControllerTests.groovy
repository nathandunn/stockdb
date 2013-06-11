package edu.uoregon.stockdb
import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(PhylumController)
@Mock([Phylum,Strain,Genus])
class PhylumControllerTests {

    def populateValidParams(params) {
        assert params != null
        params["name"] = 'somePhlym'
    }

    void testIndex() {
        controller.index()
        assert "/phylum/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.phylumInstanceList.size() == 0
        assert model.phylumInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.phylumInstance != null
    }

    void testSave() {
        controller.save()

        assert model.phylumInstance != null
        assert view == '/phylum/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/phylum/show/1'
        assert controller.flash.message != null
        assert Phylum.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/phylum/list'

        populateValidParams(params)
        def phylum = new Phylum(params)

        assert phylum.save() != null

        params.id = phylum.id

        def model = controller.show()

        assert model.phylumInstance == phylum
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/phylum/list'

        populateValidParams(params)
        def phylum = new Phylum(params)

        assert phylum.save() != null

        params.id = phylum.id

        def model = controller.edit()

        assert model.phylumInstance == phylum
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/phylum/list'

        response.reset()

        populateValidParams(params)
        def phylum = new Phylum(params)

        assert phylum.save() != null

        // test invalid parameters in update
        params.id = phylum.id
        params.name = null

        controller.update()

        assert view == "/phylum/edit"
        assert model.phylumInstance != null

        phylum.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/phylum/show/$phylum.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        phylum.clearErrors()

        populateValidParams(params)
        params.id = phylum.id
        params.version = -1
        controller.update()

        assert view == "/phylum/edit"
        assert model.phylumInstance != null
        assert model.phylumInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/phylum/list'

        response.reset()

        populateValidParams(params)
        def phylum = new Phylum(params)

        assert phylum.save() != null
        assert Phylum.count() == 1

        params.id = phylum.id

        controller.delete()

        assert Phylum.count() == 0
        assert Phylum.get(phylum.id) == null
        assert response.redirectedUrl == '/phylum/list'
    }
}
