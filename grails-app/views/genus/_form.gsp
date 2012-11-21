<%@ page import="edu.uoregon.stockdb.Genus" %>



<div class="fieldcontain ${hasErrors(bean: genusInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="genus.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${genusInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: genusInstance, field: 'phylum', 'error')} required">
	<label for="phylum">
		<g:message code="genus.phylum.label" default="Phylum" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="phylum" name="phylum.id" from="${edu.uoregon.stockdb.Phylum.list()}" optionKey="id" required="" value="${genusInstance?.phylum?.id}" class="many-to-one" optionValue="name"/>
</div>

