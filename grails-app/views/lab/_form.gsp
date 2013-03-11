<%@ page import="edu.uoregon.stockdb.Lab" %>



<div class="fieldcontain ${hasErrors(bean: labInstance, field: 'researchers', 'error')} ">
	<label for="researchers">
		<g:message code="lab.researchers.label" default="Researchers" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${labInstance?.researchers?}" var="r">
    <li><g:link controller="researcher" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="researcher" action="create" params="['lab.id': labInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'researcher.label', default: 'Researcher')])}</g:link>
</li>
</ul>

</div>

