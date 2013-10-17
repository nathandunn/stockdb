<%@ page import="edu.uoregon.stockdb.BlastQuery" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'genome.label', default: 'Genome')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-genome" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				%{----}%
				<li><g:link class="create" action="create"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-blastQuery" class="content scaffold-create" role="main">
			<h1>Blast</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${genomeInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${genomeInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="display" >
				<fieldset class="form">
					<div class="fieldcontain">
					    <label for="yourSequence">
					        <g:message code="genome.genomeType.label" default="Your sequence"/>
					    </label>
					    <g:textArea id="yourSequence" name="yourSequence" from=""/>
					</div>

				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="Submit" value="Submit" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
