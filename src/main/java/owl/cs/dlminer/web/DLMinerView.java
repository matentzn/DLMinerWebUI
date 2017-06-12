package owl.cs.dlminer.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;

public class DLMinerView extends VerticalLayout {

	File ofile;
	final OntologyUploader receiver;
	final Upload upload;

	VerticalLayout vl_configuration = new VerticalLayout();
	VerticalLayout vl_hypotheses = new VerticalLayout();
	VerticalLayout vl_downloads = new VerticalLayout();
	VerticalLayout vl_filtering = new VerticalLayout();

	Slider sl_maxhyp = new Slider(1, 100);
	Slider sl_maxconceptlength = new Slider(1, 6);
	Slider sl_minprecision = new Slider(0.0, 1.0, 1);
	Slider sl_minsupport = new Slider(1, 1000);

	Button bt_generate = new Button("Generate");
	Button bt_download = new Button("Download");

	SimpleHypothesisViewTable tab;
	HypothesisGenerator gen;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2884436400103864837L;

	public DLMinerView(String tempdir) {
		receiver = new OntologyUploader(this, tempdir);
		upload = new Upload("Upload Ontology", receiver);
		upload.addSucceededListener(receiver);
		upload.setImmediate(false);
		bt_generate.addClickListener(e -> generateHypotheses());
		addComponents(upload);
		layoutConfiguration();
		layoutDownload();
		setMargin(true);
		setSpacing(true);
	}

	private void layoutDownload() {

		vl_downloads.addComponent(bt_download);

		StreamResource myResource = createResource();
		FileDownloader fileDownloader = new FileDownloader(myResource);
		fileDownloader.extend(bt_download);

	}

	private StreamResource createResource() {
		return new StreamResource(new StreamSource() {
			@Override
			public InputStream getStream() {
				String text = "Hypotheses";

				OWLOntology exportedHypotheses = getExportedHypotheses();

				try {
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					exportedHypotheses.getOWLOntologyManager().saveOntology(exportedHypotheses, os);
					return new ByteArrayInputStream(os.toByteArray());
				} catch (OWLOntologyStorageException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}

			
		}, "hypotheses.owl");
	}

	private OWLOntology getExportedHypotheses() {
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		try {
			OWLOntology o = man.createOntology();
			for(HypothesisI hyp:tab.getHypotheses()) {
				man.addAxioms(o, hyp.getAxioms());
			}
			return o;
		} catch (OWLOntologyCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void layoutConfiguration() {
		layoutSliderOption("Maximum number of Hypotheses", sl_maxhyp, 10.0);
		layoutSliderOption("Minimum Precison", sl_minprecision, 0.9);
		layoutSliderOption("Maximum Concept Length", sl_maxconceptlength, 2.0);
		layoutSliderOption("Minimum Support", sl_minsupport, 1.0);

		vl_configuration.addComponent(bt_generate);
	}

	private void layoutSliderOption(String caption, Slider c, double defaultValue) {
		HorizontalLayout hl = new HorizontalLayout();
		Label l = new Label(caption);
		l.setWidth("150px");
		TextField t_val = new TextField();
		c.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				t_val.setValue(c.getValue() + "");
			}
		});
		hl.addComponent(l);
		hl.addComponent(c);
		hl.addComponent(t_val);
		c.setValue(defaultValue);
		c.setWidth("200px");
		t_val.setWidth("60px");
		vl_configuration.addComponent(hl);
	}

	public void setFile(File ofile) {
		this.ofile = ofile;
		removeAllComponents();
		addComponents(upload, vl_configuration, vl_hypotheses, vl_filtering, vl_downloads);
	}

	private void generateHypotheses() {
		gen = new HypothesisGenerator(ofile);
		gen.generate(getMaxHypothesisValue(), getMinPrecisionValue(), getMaxConceptLength(), getMinSupportValue());
		tab = new SimpleHypothesisViewTable("Hypotheses", gen.getHypotheses());
		tab.setWidth("900px");
		vl_hypotheses.addComponent(tab);
	}

	private int getMinSupportValue() {
		return sl_minsupport.getValue().intValue();
	}

	private int getMaxConceptLength() {
		return sl_maxconceptlength.getValue().intValue();
	}

	private double getMinPrecisionValue() {
		return sl_minprecision.getValue();
	}

	private int getMaxHypothesisValue() {
		return sl_maxhyp.getValue().intValue();
	}
}
