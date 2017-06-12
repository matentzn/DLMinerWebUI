package owl.cs.dlminer.web;

import org.semanticweb.owlapi.dlsyntax.renderer.DLSyntaxObjectRenderer;
import org.semanticweb.owlapi.io.OWLObjectRenderer;
import org.semanticweb.owlapi.model.OWLAxiom;

public class DLMinerUIUtils {
	
	static OWLObjectRenderer ren = new DLSyntaxObjectRenderer();

	public static String p(OWLAxiom ax) {
		return ren.render(ax);
	}

}
