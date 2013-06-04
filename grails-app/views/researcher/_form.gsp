<%@ page import="edu.uoregon.stockdb.ResearcherService; edu.uoregon.stockdb.Researcher" %>



<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'username', 'error')} ">
    <label for="email">
        <g:message code="researcher.email.label" default="Email"/>

    </label>
    <g:field type="email" name="username" value="${researcherInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'firstName', 'error')} ">
    <label for="firstName">
        <g:message code="researcher.firstName.label" default="First Name"/>

    </label>
    <g:textField name="firstName" value="${researcherInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'lastName', 'error')} ">
    <label for="lastName">
        <g:message code="researcher.lastName.label" default="Last Name"/>

    </label>
    <g:textField name="lastName" value="${researcherInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'password1', 'error')} ">
    <label for="lastName">
        <g:message code="researcher.password.label" default="Password"/>

    </label>
    <g:passwordField name="password1"/>
    %{--<g:textField name="lastName" value="${researcherInstance?.password1}"/>--}%
</div>

<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'password2', 'error')} ">
    <label for="lastName">
        <g:message code="researcher.password-repeat.label" default="Repeat Password"/>

    </label>
    <g:passwordField name="password2"/>
    %{--<g:textField name="lastName" value="${researcherInstance?.password1}"/>--}%
</div>

<shiro:hasRole name="${ResearcherService.ROLE_ADMINISTRATOR}">

    <div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'role', 'error')} ">
        <label for="role">
            <g:message code="researcher.role.label" default="Role"/>

        </label>
        <g:select name="roles"
                  from="${edu.uoregon.stockdb.Role.listOrderByName()}"
                  value="${researcherInstance.roles}"
                  optionValue="name"
                  optionKey="id"/>
    </div>
</shiro:hasRole>

<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'lab', 'error')} ">
    <label for="lab">
        <g:message code="researcher.lab.label" default="Lab"/>

    </label>
    <g:select id="lab" name="lab.id" optionValue="name" from="${edu.uoregon.stockdb.Lab.list()}" optionKey="id"
              value="${researcherInstance?.lab?.id}" class="many-to-one" noSelection="['null': '']"/>
    <g:link action="create" controller="lab">Create Lab</g:link>
</div>

