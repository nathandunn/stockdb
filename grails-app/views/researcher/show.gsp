<%@ page import="edu.uoregon.stockdb.Researcher" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'researcher.label', default: 'Researcher')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-researcher" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>

        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-researcher" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:if test="${flash.error}">
        <div class="errors" role="status">${flash.error}</div>
    </g:if>
    <ol class="property-list researcher">

        <g:if test="${researcherInstance?.username}">
            <li class="fieldcontain">
                <span id="email-label" class="property-label"><g:message code="researcher.email.label"
                                                                         default="Email"/></span>

                <span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${researcherInstance}"
                                                                                         field="username"/></span>

            </li>
        </g:if>

        <g:if test="${researcherInstance?.firstName}">
            <li class="fieldcontain">
                <span id="firstName-label" class="property-label"><g:message code="researcher.firstName.label"
                                                                             default="First Name"/></span>

                <span class="property-value" aria-labelledby="firstName-label"><g:fieldValue
                        bean="${researcherInstance}" field="firstName"/></span>

            </li>
        </g:if>

        <g:if test="${researcherInstance?.lastName}">
            <li class="fieldcontain">
                <span id="lastName-label" class="property-label"><g:message code="researcher.lastName.label"
                                                                            default="Last Name"/></span>

                <span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${researcherInstance}"
                                                                                            field="lastName"/></span>

            </li>
        </g:if>

        <shiro:hasRole name="${edu.uoregon.stockdb.StubData.ROLE_ADMINISTRATOR}">

            <li class="fieldcontain">
                <span id="role-label" class="property-label"><g:message code="researcher.role.label"
                                                                            default="Role"/></span>

                <span class="property-value" aria-labelledby="role-label">
                    %{--<g:fieldValue bean="${researcherInstance}" field="role"/>--}%
                    <ul>
                    <g:each in="${researcherInstance.roles}" var="role">
                       <li>
                           ${role.name}
                       </li>
                    </g:each>
                    </ul>
                </span>

            </li>
        </shiro:hasRole>

        <g:if test="${researcherInstance?.lab}">
            <li class="fieldcontain">
                <span id="lab-label" class="property-label"><g:message code="researcher.lab.label"
                                                                       default="Lab"/></span>

                <span class="property-value" aria-labelledby="lab-label"><g:link controller="lab" action="show"
                                                                                 id="${researcherInstance?.lab?.id}">${researcherInstance?.lab?.name}</g:link></span>

            </li>
        </g:if>

        <g:if test="${researcherInstance?.experiments}">
            <li class="fieldcontain">
                <span id="experiments-label" class="property-label"><g:message code="researcher.experiments.label"
                                                                               default="Experiments"/></span>

                <span class="property-value" aria-labelledby="experiments-label">
                    <ul>
                        <g:each in="${researcherInstance?.experiments}" var="experiment">
                            <li>
                                <g:link controller="experiment" action="show"
                                        id="${experiment.id}">${experiment.name}</g:link>
                            </li>
                        </g:each>
                    </ul>
                </span>
            </li>
        </g:if>

        <g:if test="${researcherInstance?.isolateConditions}">
            <li class="fieldcontain">
                <span id="experiments-label" class="property-label"><g:message code="researcher.experiments.label"
                                                                               default="Isolate Conditions"/></span>

                <span class="property-value" aria-labelledby="isolates-label">
                    <ul>
                        <g:each in="${researcherInstance?.isolateConditions}" var="isolate">
                            <li>
                                <g:link controller="isolate" action="show"
                                        id="${isolate.id}">${isolate.display}</g:link>
                            </li>
                        </g:each>
                    </ul>
                </span>
            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${researcherInstance?.id}"/>
            <g:link class="edit" action="edit" id="${researcherInstance?.id}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
