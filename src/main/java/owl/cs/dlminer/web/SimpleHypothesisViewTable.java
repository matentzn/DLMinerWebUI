package owl.cs.dlminer.web;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Table;


public class SimpleHypothesisViewTable extends Table {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2387336169084613228L;
	List<HypothesisI> hypotheses;
	String caption;

	public SimpleHypothesisViewTable(String caption, List<HypothesisI> hypotheses) {
		super(caption);
		this.caption = caption;
		this.hypotheses = hypotheses;
		prepareSimilarFormsTable();
	}

	private void prepareSimilarFormsTable() {
		addContainerProperty("ID", Integer.class, null);
		addContainerProperty("Hypothesis", HypothesisI.class, null);
		addContainerProperty("Sup", Integer.class, null);
		addContainerProperty("Assum", Integer.class, null);
		addContainerProperty("Conf", Double.class, null);
		addContainerProperty("Lift", Double.class, null);
		addContainerProperty("X", CheckBox.class, null);
		int i = 1;
		for (HypothesisI f : hypotheses) {
			CheckBox cb = new CheckBox();
			addRow(i, f,f.getSupport(), f.getAssumption(), f.getConfidence(), f.getLift(), cb);
			i++;
		}
		setPageLength(size());
	}

	private void addRow(Integer formid, HypothesisI ax, int support, int assumption, double confidence, double lift, CheckBox cb) {
		addItem(new Object[] { formid, ax, support, assumption, confidence, lift, cb }, formid);
	}

	public List<HypothesisI> getHypotheses() {
		List<HypothesisI> hyps = new ArrayList<>();
		hyps.addAll(hypotheses);
		return hyps;
	}

}