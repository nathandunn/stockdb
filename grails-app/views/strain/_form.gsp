<%@ page import="edu.uoregon.stockdb.HostOrigin; edu.uoregon.stockdb.Strain" %>



<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'sequence', 'error')} ">
	<label for="sequence">
		<g:message code="strain.sequence.label" default="Sequence" />
		
	</label>
	<g:textField name="sequence" value="${strainInstance?.sequence}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'values', 'error')} ">
	<label for="values">
		<g:message code="strain.values.label" default="Values" />
		
	</label>
	<g:textField name="values" value="${strainInstance?.values}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'genus', 'error')} required">
	<label for="genus">
		<g:message code="strain.genus.label" default="Genus" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="genus" name="genus.id" from="${edu.uoregon.stockdb.Genus.list()}" optionKey="id" required="" value="${strainInstance?.genus?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="strain.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${strainInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'origin', 'error')} required">
	<label for="origin">
		<g:message code="strain.origin.label" default="Origin" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="origin" name="origin.id" from="${HostOrigin.list()}" optionKey="id" required="" value="${strainInstance?.origin?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'phylum', 'error')} required">
	<label for="phylum">
		<g:message code="strain.phylum.label" default="Phylum" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="phylum" name="phylum.id" from="${edu.uoregon.stockdb.Phylum.list()}" optionKey="id" required="" value="${strainInstance?.phylum?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'stocks', 'error')} ">
	<label for="stocks">
		<g:message code="strain.stocks.label" default="Stocks" />
		
	</label>
	<g:select name="stocks" from="${edu.uoregon.stockdb.Stock.list()}" multiple="multiple" optionKey="id" size="5" value="${strainInstance?.stocks*.id}" class="many-to-many"/>
</div>

