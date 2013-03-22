<%@ page import="edu.uoregon.stockdb.Lab" %>

<div class="fieldcontain ${hasErrors(bean: labInstance, field: 'labdBy', 'error')} ">
    <label for="labdBy">
        <g:message code="lab.labdBy.label" default="Isolated By" />

    </label>
    <g:textField name="name" value="${labInstance.name}" size="40"/>
</div>


<div class="fieldcontain ${hasErrors(bean: labInstance, field: 'researchers', 'error')} ">
	<label for="researchers">
		<g:message code="lab.researchers.label" default="Researchers" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${labInstance?.researchers?}" var="r">
    <li><g:link controller="researcher" action="show" id="${r.id}">${r?.fullName}</g:link></li>
</g:each>
<li class="add">
<g:link controller="researcher" action="create" params="['lab.id': labInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'researcher.label', default: 'Researcher')])}</g:link>
</li>
</ul>

</div>

