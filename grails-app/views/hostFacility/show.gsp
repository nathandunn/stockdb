
<%@ page import="edu.uoregon.stockdb.ResearcherService; edu.uoregon.stockdb.HostFacility" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'hostFacility.label', default: 'HostFacility')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-hostFacility" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>

				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
<shiro:hasRole name="${ResearcherService.ROLE_ADMINISTRATOR}">
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </shiro:hasRole>
			</ul>
		</div>
		<div id="show-hostFacility" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list hostFacility">
			
				<g:if test="${hostFacilityInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="hostFacility.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${hostFacilityInstance}" field="name"/></span>
					
				</li>
				</g:if>

                <g:if test="${hostFacilityInstance?.prefix}">
                    <li class="fieldcontain">
                        <span id="prefix-label" class="property-label"><g:message code="hostFacility.prefix.label" default="Name" /></span>

                        <span class="property-value" aria-labelledby="prefix-label"><g:fieldValue bean="${hostFacilityInstance}" field="prefix"/></span>

                    </li>
                </g:if>

				<g:if test="${hostFacilityInstance?.origins}">
				<li class="fieldcontain">
					<span id="origins-label" class="property-label"><g:message code="hostFacility.origins.label" default="Origins" /></span>
					
						<g:each in="${hostFacilityInstance.origins}" var="o">
						<span class="property-value" aria-labelledby="origins-label"><g:link controller="hostOrigin" action="show" id="${o.id}">${o?.display}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${hostFacilityInstance?.id}" />
					<g:link class="edit" action="edit" id="${hostFacilityInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
