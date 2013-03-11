<%@ page import="edu.uoregon.stockdb.Genome" %>



<div class="fieldcontain ${hasErrors(bean: genomeInstance, field: 'url', 'error')} ">
	<label for="url">
		<g:message code="genome.url.label" default="Url" />
		
	</label>
	<g:field type="url" name="url" value="${genomeInstance?.url}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: genomeInstance, field: 'note', 'error')} ">
	<label for="note">
		<g:message code="genome.note.label" default="Note" />
		
	</label>
	<g:textField name="note" value="${genomeInstance?.note}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: genomeInstance, field: 'quality', 'error')} ">
	<label for="quality">
		<g:message code="genome.quality.label" default="Quality" />
		
	</label>
	<g:field name="quality" value="${fieldValue(bean: genomeInstance, field: 'quality')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: genomeInstance, field: 'size', 'error')} ">
	<label for="size">
		<g:message code="genome.size.label" default="Size" />
		
	</label>
	<g:field name="size" value="${fieldValue(bean: genomeInstance, field: 'size')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: genomeInstance, field: 'strains', 'error')} ">
	<label for="strains">
		<g:message code="genome.strains.label" default="Strains" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${genomeInstance?.strains?}" var="s">
    <li><g:link controller="strain" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="strain" action="create" params="['genome.id': genomeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'strain.label', default: 'Strain')])}</g:link>
</li>
</ul>

</div>

