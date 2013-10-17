
<%@ page import="edu.uoregon.stockdb.ResearcherService; edu.uoregon.stockdb.Species" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'species.label', default: 'Species')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-species" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>

				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
<shiro:hasRole name="${ResearcherService.ROLE_ADMINISTRATOR}">
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </shiro:hasRole>
			</ul>
		</div>
		<div id="show-species" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list species">
			
				<g:if test="${speciesInstance?.genus}">
				<li class="fieldcontain">
					<span id="genus-label" class="property-label"><g:message code="species.genus.label" default="Genus" /></span>
					
						<span class="property-value" aria-labelledby="genus-label"><g:link controller="genus" action="show" id="${speciesInstance?.genus?.id}">${speciesInstance?.genus?.name}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${speciesInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="species.name.label" default="Species" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${speciesInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${speciesInstance?.commonName}">
				<li class="fieldcontain">
					<span id="commonName-label" class="property-label"><g:message code="species.commonName.label" default="Common Name" /></span>
					
						<span class="property-value" aria-labelledby="commonName-label"><g:fieldValue bean="${speciesInstance}" field="commonName"/></span>
					
				</li>
				</g:if>

                <g:if test="${speciesInstance?.prefix}">
                    <li class="fieldcontain">
                        <span id="prefix-label" class="property-label"><g:message code="species.prefix.label" default="Strain Prefix" /></span>

                        <span class="property-value" aria-labelledby="prefix-label"><g:fieldValue bean="${speciesInstance}" field="prefix"/></span>

                    </li>
                </g:if>

                <g:if test="${hostOriginsForSpecies}">
                    <li class="fieldcontain">
                        <span id="hostOrigins-label" class="property-label"><g:message code="species.commonName.label" default="Host Origins" /></span>

                        <span class="property-value" aria-labelledby="commonName-label">
                            <ul>
                            <g:each var="hostOrigin" in="${hostOriginsForSpecies}">
                                <li>
                                    <g:link action="show" controller="hostOrigin" id="${hostOrigin.id}">
                                        ${hostOrigin.display}
                                    </g:link>
                                </li>
                            </g:each>
                            </ul>
                            %{--<g:fieldValue bean="${speciesInstance}" field="commonName"/>--}%
                        </span>

                    </li>
                </g:if>

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${speciesInstance?.id}" />
					<g:link class="edit" action="edit" id="${speciesInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
