<%@ page import="edu.uoregon.stockdb.Genus" %>



<div class="fieldcontain ${hasErrors(bean: genusInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="genus.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${genusInstance?.name}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: genusInstance, field: 'host', 'error')} ">
    <label for="host">
        <g:message code="genus.host.label" default="Host" />

    </label>
    <g:checkBox name="host" value="${genusInstance?.host}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: genusInstance, field: 'phylum', 'error')} ">
	<label for="phylum">
		<g:message code="genus.phylum.label" default="Phylum" />
		
	</label>
	<g:select id="phylum" name="phylum.id" from="${edu.uoregon.stockdb.Phylum.list()}" optionKey="id" value="${genusInstance?.phylum?.id}" class="many-to-one"
        optionValue="name"
              noSelection="['null': '- Choose Existing -']"/>

    <g:link controller="phylum" action="create">Create Phylum</g:link>
</div>

