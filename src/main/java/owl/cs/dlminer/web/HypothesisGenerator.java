package owl.cs.dlminer.web;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.parameters.Imports;

public class HypothesisGenerator {
	OWLOntology o;
	HypothesisGenerator(OWLOntology o) {
		this.o = o;
	}
	
	public void generate() {
		// generate hypotheses here
	}

	public List<HypothesisI> getHypotheses() {
		List<HypothesisI> hypos = new ArrayList<>();
		for(OWLAxiom ax:o.getTBoxAxioms(Imports.INCLUDED)) {
			hypos.add(new HypthesisDefaultImpl(ax));
		}
		return hypos;
	}

}
