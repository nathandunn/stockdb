<%@ page import="edu.uoregon.stockdb.Phylum" %>



<div class="fieldcontain ${hasErrors(bean: phylumInstance, field: 'genuses', 'error')} ">
	<label for="genuses">
		<g:message code="phylum.genuses.label" default="Genuses" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${phylumInstance?.genuses?}" var="g">
    <li><g:link controller="genus" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="genus" action="create" params="['phylum.id': phylumInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'genus.label', default: 'Genus')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: phylumInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="phylum.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${phylumInstance?.name}"/>
</div>

