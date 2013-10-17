package edu.uoregon.stockdb

class BlastQueryController {

	def blast() {
		render(view: "create")
	}
	
	def display() {
		try {
			//get the Blast input as a Stream
			//TODO: change this to specific result for a given query
			InputStream is = new FileInputStream("results/result_xml.blast");
	   
		} catch (IOException ex) {
			//IO problem, possibly file not found
			ex.printStackTrace();
		}
		render(view: "display")
	} 
}
