package owl.cs.dlminer.web;


import java.util.Set;

import org.semanticweb.owlapi.model.OWLAxiom;

import io.dlminer.learn.Hypothesis;

public class HypthesisDefaultImpl implements HypothesisI {
	final Hypothesis h;
	public HypthesisDefaultImpl(Hypothesis h) {
		this.h = h;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(OWLAxiom ax:h.axioms) {
			sb.append(DLMinerUIUtils.p(ax)+"\n");
		}
		return sb.toString();
	}

	@Override
	public int getSupport() {
		return h.support.intValue();
	}

	@Override
	public int getAssumption() {
		return h.assumption.intValue();
	}

	@Override
	public double getConfidence() {
		return h.precision;
	}

	@Override
	public double getLift() {
		return h.lift;
	}

	@Override
	public Set<? extends OWLAxiom> getAxioms() {
		return h.axioms;
	}
	
	
}
