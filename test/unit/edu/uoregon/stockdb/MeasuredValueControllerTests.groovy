package edu.uoregon.stockdb

import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(MeasuredValueController)
@Mock(MeasuredValue)
class MeasuredValueControllerTests {

    def populateValidParams(params) {
        assert params != null
        params["value"] = 'someValidName'
        params["category"] = new Category(
                name: "A Category"
        )
        Phylum phylum = new Phylum(
                name: "aPhylum"
        )
        params["strain"] = new Strain(
                name: "Z1234"
                , genus: new Genus(
                name: "asdf"
                , phylum: phylum
        ))

        params["experiment"] = new Experiment(
                name: "An Experiment"
                ,whenPerformed: new Date()
        )

    }

    void testIndex() {
        controller.index()
        assert "/measuredValue/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.measuredValueInstanceList.size() == 0
        assert model.measuredValueInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.measuredValueInstance != null
    }

    void testSave() {
        controller.save()

        assert model.measuredValueInstance != null
        assert view == '/measuredValue/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/measuredValue/show/1'
        assert controller.flash.message != null
        assert MeasuredValue.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/measuredValue/list'

        populateValidParams(params)
        def measuredValue = new MeasuredValue(params)

        assert measuredValue.save() != null

        params.id = measuredValue.id

        def model = controller.show()

        assert model.measuredValueInstance == measuredValue
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/measuredValue/list'

        populateValidParams(params)
        def measuredValue = new MeasuredValue(params)

        assert measuredValue.save() != null

        params.id = measuredValue.id

        def model = controller.edit()

        assert model.measuredValueInstance == measuredValue
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/measuredValue/list'

        response.reset()

        populateValidParams(params)
        def measuredValue = new MeasuredValue(params)

        assert measuredValue.save() != null

        // test invalid parameters in update
        params.id = measuredValue.id
        params.category = null

        controller.update()

        assert view == "/measuredValue/edit"
        assert model.measuredValueInstance != null

        measuredValue.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/measuredValue/show/$measuredValue.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        measuredValue.clearErrors()

        populateValidParams(params)
        params.id = measuredValue.id
        params.version = -1
        controller.update()

        assert view == "/measuredValue/edit"
        assert model.measuredValueInstance != null
        assert model.measuredValueInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/measuredValue/list'

        response.reset()

        populateValidParams(params)
        def measuredValue = new MeasuredValue(params)

        assert measuredValue.save() != null
        assert MeasuredValue.count() == 1

        params.id = measuredValue.id

        controller.delete()

        assert MeasuredValue.count() == 0
        assert MeasuredValue.get(measuredValue.id) == null
        assert response.redirectedUrl == '/measuredValue/list'
    }
}
