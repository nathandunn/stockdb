package edu.uoregon.stockdb
import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(SpeciesController)
@Mock([Species,HostOrigin])
class SpeciesControllerTests {

    def populateValidParams(params) {
        assert params != null
        params["name"] = 'Some Species'
        params["genus"] = new Genus(
                name: "Some Genus"
                ,phylum: new Phylum(
                name:"Some Phylum"
        ) )
    }

    void testIndex() {
        controller.index()
        assert "/species/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.speciesInstanceList.size() == 0
        assert model.speciesInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.speciesInstance != null
    }

    void testSave() {
        controller.save()

        assert model.speciesInstance != null
        assert view == '/species/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/species/show/1'
        assert controller.flash.message != null
        assert Species.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/species/list'

        populateValidParams(params)
        def species = new Species(params)

        assert species.save() != null

        params.id = species.id

        def model = controller.show()

        assert model.speciesInstance == species
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/species/list'

        populateValidParams(params)
        def species = new Species(params)

        assert species.save() != null

        params.id = species.id

        def model = controller.edit()

        assert model.speciesInstance == species
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/species/list'

        response.reset()

        populateValidParams(params)
        def species = new Species(params)

        assert species.save() != null

        // test invalid parameters in update
        params.id = species.id
        params.name = null

        controller.update()

        assert view == "/species/edit"
        assert model.speciesInstance != null

        species.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/species/show/$species.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        species.clearErrors()

        populateValidParams(params)
        params.id = species.id
        params.version = -1
        controller.update()

        assert view == "/species/edit"
        assert model.speciesInstance != null
        assert model.speciesInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/species/list'

        response.reset()

        populateValidParams(params)
        def species = new Species(params)

        assert species.save() != null
        assert Species.count() == 1

        params.id = species.id

        controller.delete()

        assert Species.count() == 0
        assert Species.get(species.id) == null
        assert response.redirectedUrl == '/species/list'
    }
}
