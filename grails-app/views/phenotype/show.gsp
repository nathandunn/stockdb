<%@ page import="edu.uoregon.stockdb.Phenotype" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'phenotype.label', default: 'Phenotype')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-phenotype" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-phenotype" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:if test="${flash.error}">
        <div class="errors" role="status">${flash.error}</div>
    </g:if>
    <ol class="property-list phenotype">

        <g:if test="${phenotypeInstance?.name}">
            <li class="fieldcontain">
                <span id="name-label" class="property-label"><g:message code="phenotype.name.label"
                                                                        default="Name"/></span>

                <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${phenotypeInstance}"
                                                                                        field="name"/></span>

            </li>
        </g:if>

        <g:if test="${phenotypeInstance?.url}">
            <li class="fieldcontain">
                <span id="url-label" class="property-label"><g:message code="phenotype.url.label" default="Url"/></span>

                <span class="property-value" aria-labelledby="url-label">
                    <g:link url="${phenotypeInstance.url}">
                        <g:fieldValue bean="${phenotypeInstance}" field="url"/>
                    </g:link>
                </span>

            </li>
        </g:if>


        <g:if test="${phenotypeInstance?.hostOrigins}">
            <li class="fieldcontain">
                <span id="url-label" class="property-label">Describes Host Origins
                </span>

                <span class="property-value" aria-labelledby="url-label">
                    <g:each in="${phenotypeInstance.hostOrigins}" var="host">
                        <g:link action="show" controller="hostOrigin" id="${host.id}">${host.display}</g:link>
                    </g:each>
                </span>

            </li>
        </g:if>

        <g:if test="${phenotypeInstance?.measuredValues}">
            <li class="fieldcontain">
                <span id="url-label" class="property-label">Describes Measured Values
                </span>

                <span class="property-value" aria-labelledby="url-label">
                    <ul>
                        <g:each in="${phenotypeInstance.measuredValues}" var="measuredValue">
                            <li>
                                <g:link action="show" controller="measuredValue"
                                        id="${measuredValue.id}">${measuredValue.name}</g:link>
                            </li>
                        </g:each>
                    </ul>
                </span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${phenotypeInstance?.id}"/>
            <g:link class="edit" action="edit" id="${phenotypeInstance?.id}"><g:message code="default.button.edit.label"
                                                                                        default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
