<%@ page import="edu.uoregon.stockdb.Stock" %>


<div class="fieldcontain ${hasErrors(bean: stockInstance, field: 'boxNumber', 'error')} ">
    <label for="boxNumber">
        <g:message code="stock.boxNumber.label" default="Box Number" />

    </label>
    <g:field type="number" name="boxNumber" value="${stockInstance?.boxNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stockInstance, field: 'boxIndex', 'error')} ">
    <label for="boxIndex">
        <g:message code="stock.boxIndex.label" default="Box Index" />
    </label>
    <g:field type="number" name="boxIndex" value="${stockInstance?.boxIndex}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: stockInstance, field: 'generalLocation', 'error')} ">
	<label for="generalLocation">
		<g:message code="stock.generalLocation.label" default="General Location" />
		
	</label>
	<g:select id="generalLocation" name="generalLocation.id" from="${edu.uoregon.stockdb.Location.list()}" optionKey="id"
              value="${stockInstance?.generalLocation?.id}" class="many-to-one"
        optionValue="name"
              noSelection="['null': '- Choose Existing Location -']"/>
    <g:link action="create" controller="location">Create Location</g:link>
</div>

<div class="fieldcontain ${hasErrors(bean: stockInstance, field: 'strain', 'error')} ">
    <label for="strain">
        <g:message code="stock.strain.label" default="Strain" />

    </label>
    <g:select id="strain" name="strain.id" from="${edu.uoregon.stockdb.Strain.list()}" optionKey="id" value="${stockInstance?.strain?.id}"
        optionValue="name"
              class="many-to-one" noSelection="['null': '- Choose Existing Strain-']"/>
    <g:link action="create" controller="strain">Create Strain</g:link>
</div>
