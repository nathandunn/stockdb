package edu.uoregon.stockdb

class CustomPaginateTagLib {
	def paginateCustom = { attrs ->
		params.max = params.max == "" || params.max == null ? 10 : params.max
		attrs["max"] = params.max
		def pag = g.paginate(attrs)
		if (pag == "" && params.max == 10) return; //if there is no need to paginate, don't display the extra stuff
		out << pag
		
		def textAttrs = [name: 'max', value: params.max]
		out << "Show " << g.textField(textAttrs) + " rows"
	}
}
