
<%@ page import="edu.uoregon.stockdb.IsolateCondition" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'isolate.label', default: 'Isolate')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-isolate" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-isolate" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list isolate">

                <li class="fieldcontain">
                    <span id="media-label" class="property-label"><g:message code="isolate.id.label" default="ID" /></span>

                    <span class="property-value" aria-labelledby="media-label"><g:fieldValue bean="${isolateInstance}" field="id"/></span>

                </li>

				<g:if test="${isolateInstance?.media}">
				<li class="fieldcontain">
					<span id="media-label" class="property-label"><g:message code="isolate.media.label" default="Media" /></span>
					
						<span class="property-value" aria-labelledby="media-label"><g:fieldValue bean="${isolateInstance}" field="media"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${isolateInstance?.notes}">
				<li class="fieldcontain">
					<span id="notes-label" class="property-label"><g:message code="isolate.notes.label" default="Notes" /></span>
					
						<span class="property-value" aria-labelledby="notes-label"><g:fieldValue bean="${isolateInstance}" field="notes"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${isolateInstance?.oxygenCondition}">
				<li class="fieldcontain">
					<span id="oxygenCondition-label" class="property-label"><g:message code="isolate.oxygenCondition.label" default="Oxygen Condition" /></span>
					
						<span class="property-value" aria-labelledby="oxygenCondition-label"><g:fieldValue bean="${isolateInstance}" field="oxygenCondition"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${isolateInstance?.temperature}">
				<li class="fieldcontain">
					<span id="temperature-label" class="property-label"><g:message code="isolate.temperature.label" default="Temperature (C)" /></span>
					
						<span class="property-value" aria-labelledby="temperature-label"><g:fieldValue bean="${isolateInstance}" field="temperature"/></span>
					
				</li>
				</g:if>

                <g:if test="${isolateInstance?.isolatedBy}">
                    <li class="fieldcontain">
                        <span id="isolatedBy-label" class="property-label"><g:message code="isolate.isolatedBy.label" default="Isolated By" /></span>

                        <span class="property-value" aria-labelledby="isolatedBy-label"><g:link controller="researcher" action="show" id="${isolateInstance?.isolatedBy?.id}">${isolateInstance?.isolatedBy?.fullName}</g:link></span>

                    </li>
                </g:if>

                <g:if test="${isolateInstance?.isolatedWhen}">
                    <li class="fieldcontain">
                        <span id="isolatedWhen-label" class="property-label"><g:message code="isolate.isolatedWhen.label" default="Isolated When" /></span>

                        <span class="property-value" aria-labelledby="isolatedWhen-label"><g:formatDate date="${isolateInstance?.isolatedWhen}" type="date" style="MEDIUM" /></span>

                    </li>
                </g:if>

                <g:if test="${strain}">
                    <li class="fieldcontain">
                        <span id="isolatedWhen-label" class="property-label"><g:message code="isolate.isolatedWhen.label" default="Associated Strain" /></span>

                        <span class="property-value" aria-labelledby="isolatedWhen-label">
                           <g:link action="show" controller="strain" id="${strain.id}">${strain.name}</g:link>
                        </span>

                    </li>
                </g:if>
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${isolateInstance?.id}" />
					<g:link class="edit" action="edit" id="${isolateInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
