<%@ page import="edu.uoregon.stockdb.MeasuredValue; edu.uoregon.stockdb.Origin" %>



<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'values', 'error')} ">
    <label for="values">
        <g:message code="hostOrigin.values.label" default="Values"/>

    </label>
    <g:textField name="values" value="${hostOriginInstance?.values}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'age', 'error')} ">
    <label for="age">
        <g:message code="hostOrigin.age.label" default="Age"/>

    </label>
    <g:field name="age" type="number" value="${hostOriginInstance.age}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'partOfFish', 'error')} ">
    <label for="partOfFish">
        <g:message code="hostOrigin.partOfFish.label" default="Part Of Fish"/>

    </label>
    <g:textField name="partOfFish" value="${hostOriginInstance?.partOfFish}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'hostFacility', 'error')} ">
    <label for="hostFacility">
        <g:message code="hostOrigin.hostFacility.label" default="Host Facility"/>

    </label>
    <g:select id="hostFacility" name="hostFacility.id" from="${edu.uoregon.stockdb.HostFacility.list()}" optionKey="id"
              value="${hostOriginInstance?.hostFacility?.id}" class="many-to-one" noSelection="['null': '']"
              optionValue="name"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'genus', 'error')} required">
    <label for="genus">
        <g:message code="hostOrigin.genus.label" default="Genus"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="genus" name="genus.id" from="${edu.uoregon.stockdb.Genus.list()}" optionKey="id" required=""
              value="${hostOriginInstance?.genus?.id}" class="many-to-one"
              optionValue="name"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'name', 'error')} ">
    <label for="name">
        <g:message code="hostOrigin.name.label" default="Name"/>

    </label>
    <g:textField name="name" value="${hostOriginInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'phenotypes', 'error')} ">
    <label for="phenotypes">
        <g:message code="hostOrigin.phenotypes.label" default="Phenotypes"/>

    </label>
    <g:select name="phenotypes" from="${MeasuredValue.list()}" multiple="multiple" optionKey="id"
              size="5" value="${hostOriginInstance?.phenotypes*.id}" class="many-to-many"
              optionValue="name"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'phylum', 'error')} required">
    <label for="phylum">
        <g:message code="hostOrigin.phylum.label" default="Phylum"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="phylum" name="phylum.id" from="${edu.uoregon.stockdb.Phylum.list()}" optionKey="id" required=""
              value="${hostOriginInstance?.phylum?.id}" class="many-to-one"
              optionValue="name"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'strains', 'error')} ">
    <label for="strains">
        <g:message code="hostOrigin.strains.label" default="Strains"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${hostOriginInstance?.strains ?}" var="s">
            <li><g:link controller="strain" action="show" id="${s.id}">${s?.name}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="strain" action="create"
                    params="['hostOrigin.id': hostOriginInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'strain.label', default: 'Strain')])}</g:link>
        </li>
    </ul>

</div>

