
<%@ page import="edu.uoregon.stockdb.ResearcherService; edu.uoregon.stockdb.Lab" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'lab.label', default: 'Lab')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-lab" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>

				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
<shiro:hasRole name="${ResearcherService.ROLE_ADMINISTRATOR}">
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </shiro:hasRole>
			</ul>
		</div>
		<div id="show-lab" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
            <g:if test="${flash.error}">
                <div class="errors" role="status">${flash.error}</div>
            </g:if>
			<ol class="property-list lab">

                <li class="fieldcontain">
                    <span id="researchers-label" class="property-label"><g:message code="lab.name.label" default="Name" /></span>

                    <span class="property-value" aria-labelledby="researchers-label">
                        ${labInstance.name}
                        </span>

                </li>

				<g:if test="${labInstance?.researchers}">
				<li class="fieldcontain">
					<span id="researchers-label" class="property-label"><g:message code="lab.researchers.label" default="Researchers" /></span>
					
						<g:each in="${labInstance.researchers}" var="r">
						<span class="property-value" aria-labelledby="researchers-label">
                            <g:link controller="researcher" action="show" id="${r.id}">${r?.fullName}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${labInstance?.id}" />
					<g:link class="edit" action="edit" id="${labInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
