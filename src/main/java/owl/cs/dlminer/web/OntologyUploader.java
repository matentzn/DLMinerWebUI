package owl.cs.dlminer.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

public class OntologyUploader implements Receiver, SucceededListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -338376059038028356L;
	public File file;
	DLMinerView genView;
	final String path;

	public OntologyUploader(DLMinerView genView, String path) {
		this.genView = genView;
		this.path = path;
	}

	public OutputStream receiveUpload(String filename, String mimeType) {
		FileOutputStream fos = null; // Output stream to write to
		try {
			// Open the file for writing.
			file = new File(path + filename);
			fos = new FileOutputStream(file);
		} catch (final java.io.FileNotFoundException e) {
			Notification.show("File could not be found.");
		}
		return fos;
	}

	public void uploadSucceeded(SucceededEvent event) {
		genView.setFile(file);
	}
};
