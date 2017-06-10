package owl.cs.dlminer.web;

import org.semanticweb.owlapi.model.OWLOntology;

import com.vaadin.ui.VerticalLayout;

public class DLMinerView extends VerticalLayout {
	
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -2884436400103864837L;

	public void generateHypotheses(OWLOntology o) {
		HypothesisGenerator gen = new HypothesisGenerator(o);
		gen.generate();
		SimpleHypothesisViewTable tab = new SimpleHypothesisViewTable("Hypotheses", gen.getHypotheses());
		addComponent(tab);
	}
}
