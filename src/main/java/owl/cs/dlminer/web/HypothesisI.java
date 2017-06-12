package owl.cs.dlminer.web;

import java.util.Set;

import org.semanticweb.owlapi.model.OWLAxiom;

public interface HypothesisI {

	int getSupport();

	int getAssumption();

	double getConfidence();

	double getLift();

	Set<? extends OWLAxiom> getAxioms();

}
