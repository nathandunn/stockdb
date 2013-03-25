<%@ page import="edu.uoregon.stockdb.Researcher" %>



<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="researcher.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" value="${researcherInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'firstName', 'error')} ">
	<label for="firstName">
		<g:message code="researcher.firstName.label" default="First Name" />
		
	</label>
	<g:textField name="firstName" value="${researcherInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'lastName', 'error')} ">
	<label for="lastName">
		<g:message code="researcher.lastName.label" default="Last Name" />
		
	</label>
	<g:textField name="lastName" value="${researcherInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'lab', 'error')} ">
	<label for="lab">
		<g:message code="researcher.lab.label" default="Lab" />
		
	</label>
	<g:select id="lab" name="lab.id" optionValue="name" from="${edu.uoregon.stockdb.Lab.list()}" optionKey="id" value="${researcherInstance?.lab?.id}" class="many-to-one" noSelection="['null': '']"/>
    <g:link action="create" controller="lab">Create Lab</g:link>
</div>

