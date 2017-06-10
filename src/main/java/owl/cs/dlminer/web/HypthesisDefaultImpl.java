package owl.cs.dlminer.web;

import org.semanticweb.owlapi.model.OWLAxiom;

public class HypthesisDefaultImpl implements HypothesisI {
	OWLAxiom ax;
	
	HypthesisDefaultImpl(OWLAxiom ax) {
		this.ax = ax;
	}

	@Override
	public String toString() {
		return ax.toString();
	}
	
	
}
