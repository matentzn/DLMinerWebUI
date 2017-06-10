package owl.cs.dlminer.web;

import java.util.List;
import com.vaadin.ui.Table;


public class SimpleHypothesisViewTable extends Table {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2387336169084613228L;
	List<HypothesisI> comparisons;
	String caption;

	public SimpleHypothesisViewTable(String caption, List<HypothesisI> comparisons) {
		super(caption);
		this.caption = caption;
		this.comparisons = comparisons;
		prepareSimilarFormsTable();
	}

	private void prepareSimilarFormsTable() {
		addContainerProperty("ID", Integer.class, null);
		addContainerProperty("Hypothesis", HypothesisI.class, null);
		int i = 1;
		for (HypothesisI f : comparisons) {
			addRow(i, f);
			i++;
		}
		setPageLength(size());
	}

	private void addRow(Integer formid, HypothesisI ax) {
		addItem(new Object[] { formid, ax }, formid);
	}

}