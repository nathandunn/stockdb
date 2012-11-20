<%@ page import="edu.uoregon.stockdb.Stock; edu.uoregon.stockdb.Strain" %>



<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'arbitraryData', 'error')} required">
	<label for="arbitraryData">
		<g:message code="strain.arbitraryData.label" default="Arbitrary Data" />
		<span class="required-indicator">*</span>
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'genus', 'error')} required">
	<label for="genus">
		<g:message code="strain.genus.label" default="Genus" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="genus" name="genus.id" from="${edu.uoregon.stockdb.Genus.list()}" optionKey="id" required="" value="${strainInstance?.genus?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'origin', 'error')} required">
	<label for="origin">
		<g:message code="strain.origin.label" default="Origin" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="origin" name="origin.id" from="${edu.uoregon.stockdb.Origin.list()}" optionKey="id" required="" value="${strainInstance?.origin?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'phylum', 'error')} required">
	<label for="phylum">
		<g:message code="strain.phylum.label" default="Phylum" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="phylum" name="phylum.id" from="${edu.uoregon.stockdb.Phylum.list()}" optionKey="id" required="" value="${strainInstance?.phylum?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'sequence', 'error')} ">
	<label for="sequence">
		<g:message code="strain.sequence.label" default="Sequence" />
		
	</label>
	<g:textField name="sequence" value="${strainInstance?.sequence}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'stockLocations', 'error')} ">
	<label for="stockLocations">
		<g:message code="strain.stockLocations.label" default="Stock Locations" />
		
	</label>
	<g:select name="stockLocations" from="${Stock.list()}" multiple="multiple" optionKey="id" size="5" value="${strainInstance?.stockLocations*.id}" class="many-to-many"/>
</div>

