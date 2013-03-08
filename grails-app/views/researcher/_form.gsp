<%@ page import="edu.uoregon.stockdb.Researcher" %>



<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'firstname', 'error')} ">
	<label for="firstname">
		<g:message code="researcher.firstname.label" default="Firstname" />
		
	</label>
	<g:textField name="firstname" value="${researcherInstance?.firstname}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'lastname', 'error')} ">
	<label for="lastname">
		<g:message code="researcher.lastname.label" default="Lastname" />
		
	</label>
	<g:textField name="lastname" value="${researcherInstance?.lastname}"/>
</div>

