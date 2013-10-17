<%@ page import="edu.uoregon.stockdb.HostFacility" %>



<div class="fieldcontain ${hasErrors(bean: hostFacilityInstance, field: 'name', 'error')} ">
    <label for="name">
        <g:message code="hostFacility.name.label" default="Name"/>

    </label>
    <g:textField name="name" value="${hostFacilityInstance?.name}" size="40"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostFacilityInstance, field: 'prefix', 'error')} ">
    <label for="prefix">
        <g:message code="hostFacility.prefix.label" default="Name"/>

    </label>
    <g:textField prefix="prefix" value="${hostFacilityInstance?.prefix}" size="5"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostFacilityInstance, field: 'origins', 'error')} ">
    <label for="origins">
        <g:message code="hostFacility.origins.label" default="Origins"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${hostFacilityInstance?.origins ?}" var="o">
            <li><g:link controller="hostOrigin" action="show" id="${o.id}">${o?.display}</g:link></li>
        </g:each>
    </ul>

</div>

