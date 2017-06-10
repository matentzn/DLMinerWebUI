package owl.cs.dlminer.web;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;


/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("dlminer")
public class DLMinerUI extends UI {

	private static final long serialVersionUID = -1654458768922351109L;
	final Label name = new Label("<h1>DL Miner Web UI</h1>", ContentMode.HTML);
	
	DLMinerView dlmain = new DLMinerView();
	OntologyUploader receiver = new OntologyUploader(dlmain,"D:\\000\\hypothesisgen");
	Upload upload = new Upload("Upload Image Here", receiver);
	

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();
		upload.addSucceededListener(receiver);
		upload.setImmediate(false);

		layout.addComponents(name, upload,dlmain);
		layout.setMargin(true);
		layout.setSpacing(true);

		setContent(layout);
	}

    @WebServlet(urlPatterns = "/*", name = "DLMinerUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = DLMinerUI.class, productionMode = false)
    public static class DLMinerUIServlet extends VaadinServlet {
    }
}
