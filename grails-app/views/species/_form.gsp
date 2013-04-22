<%@ page import="edu.uoregon.stockdb.Species" %>



<div class="fieldcontain ${hasErrors(bean: speciesInstance, field: 'genus', 'error')} required">
	<label for="genus">
		<g:message code="species.genus.label" default="Genus" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="genus" name="genus.id" from="${edu.uoregon.stockdb.Genus.findAllByHost(true)}" optionKey="id" optionValue="name" required="" value="${speciesInstance?.genus?.name}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: speciesInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="species.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${speciesInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: speciesInstance, field: 'commonName', 'error')} ">
	<label for="commonName">
		<g:message code="species.commonName.label" default="Common Name" />
		
	</label>
	<g:textField name="commonName" value="${speciesInstance?.commonName}"/>
</div>

