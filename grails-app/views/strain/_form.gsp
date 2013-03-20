<%@ page import="edu.uoregon.stockdb.IsolateCondition;  edu.uoregon.stockdb.Strain" %>


<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'name', 'error')} required">
    <label for="name">
        <g:message code="strain.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" value="${strainInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'genus', 'error')} required">
    <label for="genus">
        <g:message code="strain.genus.label" default="Genus"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="genus" name="genus.id" from="${edu.uoregon.stockdb.Genus.list()}" optionKey="id"
              value="${strainInstance?.genus?.id}" optionValue="displayName" class="many-to-one" noSelection="['null': '']"/>
</div>


<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'genome', 'error')} ">
    <label for="genome">
        <g:message code="strain.genome.label" default="Genome"/>

    </label>
    <g:select id="genome" name="genome.id" from="${edu.uoregon.stockdb.Genome.list()}" optionKey="id"
              value="${strainInstance?.genome?.id}"
        optionValue="display"
              class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'hostOrigin', 'error')} ">
    <label for="hostOrigin">
        <g:message code="strain.hostOrigin.label" default="Host Origin"/>
        <span class="required-indicator">*</span>

    </label>
    <g:select id="hostOrigin" name="hostOrigin.id" from="${edu.uoregon.stockdb.HostOrigin.list()}" optionKey="id"
              value="${strainInstance?.hostOrigin?.id}"
        optionValue="display"
              class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'formerCloneAlias', 'error')} ">
    <label for="formerCloneAlias">
        <g:message code="strain.formerCloneAlias.label" default="Former Clone Alias"/>

    </label>
    <g:textField name="formerCloneAlias" value="${strainInstance?.formerCloneAlias}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'isolate', 'error')} ">
    <label for="isolate">
        <g:message code="strain.isolate.label" default="Isolate"/>
        <span class="required-indicator">*</span>

    </label>
    <g:select id="isolate" name="isolate.id" from="${IsolateCondition.list()}" optionKey="id"
              value="${strainInstance?.isolate?.id}"
        optionValue="display"
              class="many-to-one" noSelection="['null': '']"/>
</div>

%{--<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'dateEntered', 'error')} ">--}%
%{--<label for="dateEntered">--}%
%{--<g:message code="strain.dateEntered.label" default="Date Entered" />--}%
%{----}%
%{--</label>--}%
%{--<g:datePicker name="dateEntered" precision="day"  value="${strainInstance?.dateEntered}" default="none" noSelection="['': '']" />--}%
%{--</div>--}%

%{--<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'experiments', 'error')} ">--}%
%{--<label for="experiments">--}%
%{--<g:message code="strain.experiments.label" default="Experiments" />--}%
%{----}%
%{--</label>--}%
%{--</div>--}%



<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'notes', 'error')} ">
    <label for="notes">
        <g:message code="strain.notes.label" default="Notes"/>

    </label>
    <g:textField name="notes" value="${strainInstance?.notes}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'parentStrain', 'error')}">
    <label for="parentStrain">
        <g:message code="strain.parentStrain.label" default="Parent Strain"/>
    </label>
    <g:select id="parentStrain" name="parentStrain.id" from="${edu.uoregon.stockdb.Strain.list()}" optionKey="id"
              required="" value="${strainInstance?.parentStrain?.id}" optionValue="name" class="many-to-one"
        noSelection="['null':'']"
    />
</div>


<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'stocks', 'error')} ">
    <label for="stocks">
        <g:message code="strain.stocks.label" default="Stocks"/>
        <span class="required-indicator">*</span>
    </label>

    <ul class="one-to-many">
        <g:each in="${strainInstance?.stocks ?}" var="s">
            <li><g:link controller="stock" action="show" id="${s.id}">${s?.name}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="stock" action="create"
                    params="['strain.id': strainInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'stock.label', default: 'Stock')])}</g:link>
        </li>
    </ul>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'strainGenotype', 'error')} ">
    <label for="strainGenotype">
        <g:message code="strain.strainGenotype.label" default="Strain Genotype"/>

    </label>
    <g:select id="strainGenotype" name="strainGenotype.id" from="${edu.uoregon.stockdb.StrainGenotype.list()}"
              optionKey="id" value="${strainInstance?.strainGenotype?.id}" class="many-to-one"
              noSelection="['null': '']"/>
</div>

