package uz.diploma.controller.overview;

import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;

import uz.diploma.controller.dialog.source.SourceDialogController;
import uz.diploma.gui.dialog.DialogViewFactory;
import uz.diploma.gui.dialog.source.SourceDialogFactory;
import uz.diploma.gui.window.panel.details.overviewtab.OverviewTabInterface;
import uz.diploma.gui.window.panel.record.ResultListListener;
import uz.diploma.listeners.OverviewTabListener;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.date.PreciseDate;
import uz.diploma.model.record.element.HistoricalPeriod;
import uz.diploma.model.record.element.MediumType;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.RecordElement;
import uz.diploma.model.record.source.FileSource;
import uz.diploma.model.record.source.InternetSource;
import uz.diploma.model.record.source.SourceLocation;
import uz.diploma.model.record.source.SourceType;
import uz.diploma.tools.Validator;
import uz.diploma.viewer.controller.ImageViewerController;
import uz.diploma.viewer.tools.ImageToolKit;
import uz.diploma.viewer.view.ImageContainer;
import uz.diploma.viewer.view.ImageViewer;

/**
 * @author Spider
 * 
 * Class is responsible for controlling data flow between model and Overview tab
 *
 */
public class OverviewTabController implements ResultListListener, OverviewTabListener {

	private OverviewTabInterface overviewTabPanel;
	private Record currentRecord;
	private Project project;
	private DialogViewFactory dialogFactory;
	
	public OverviewTabController(OverviewTabInterface overviewTabPanel, Project project) {
		
		Validator.ensureIsNotNull(overviewTabPanel, "overviewTabPanel");
		this.overviewTabPanel = overviewTabPanel;
		
		Validator.ensureIsNotNull(project, "project");
		this.project = project;
		
		overviewTabPanel.addOverviewTabListener(this);
		overviewTabPanel.fillMediumTypeList();
		dialogFactory = new SourceDialogFactory();
	}
	
	@Override
	public void onResultListItemSelected(Record selectedRecord) {
		Validator.ensureIsNotNull(selectedRecord, "selectedRecord");
		
		currentRecord = selectedRecord;
		setFields();
	}
	
	@Override
	public void onMediumChanged(MediumType selectedMedium) {
		if(currentRecord != null)
			currentRecord.setMedium(selectedMedium);
	}

	@Override
	public void onTitleChanged(String currentTitle) {
		if(currentTitle.trim().equals("")) {
			overviewTabPanel.showInformationDialog("Title cannot be empty");
			overviewTabPanel.setTitleField(currentRecord.getTitle());
			return;
		}
		currentRecord.setTitle(currentTitle);
	}

	@Override
	public void onPublicationDateChanged(String fieldValue) {
		currentRecord.setPublishingYear(fieldValue);
	}

	@Override
	public void onAuthorChanged(String fieldValue) {
		currentRecord.getAuthor().setName(fieldValue);
	}
	
	@Override
	public void onPublishedChanged(String fieldValue) {
		currentRecord.setPublicationData(fieldValue);
	}

	@Override
	public void onSourceComboBoxChanged(SourceLocation selectionValue) {
		if(selectionValue != null) {
			overviewTabPanel.setLocationTypeLabel();
				overviewTabPanel.setOpenBattonEnabled(selectionValue.isOpenable());
		}
	}
	
	private List<RecordElement> extractSources(Record currentRecord) {
		List<RecordElement> sources = new ArrayList<RecordElement>();
		currentRecord.getSourceLocationIterator().forEachRemaining(source -> sources.add(source));
		return sources;
	}

	@Override
	public void onOpenButtonClicked(SourceLocation currentLocation) {
		
		switch(currentLocation.getSourceType()) {
		case FILE:
			try {
				displayFile(currentLocation);
			} catch (IOException | IllegalArgumentException e) {
				overviewTabPanel.showInformationDialog("Fail to open application. " + e.getMessage());
			}
			break;
		case INTERNET:
			try {
				openURI(currentLocation);
			} catch (IOException e) {
				overviewTabPanel.showInformationDialog("Fail to open web browser");
			}
			break;
		default:
			overviewTabPanel.showInformationDialog("Unknown source type");	
		}
	}

	private void openURI(SourceLocation currentLocation) throws IOException {
		InternetSource source = (InternetSource)currentLocation; 
		Desktop.getDesktop().browse(source.getSourceLocationAsURI());
	}

	private void displayFile(SourceLocation currentLocation) throws IOException {
		FileSource file = (FileSource)currentLocation;
		
		if(ImageToolKit.isSupportedImageType(file.getSourceLocationAsPath())) {
			if(!new File(file.getSource()).exists()) throw new IOException("File does not exists");
			new ImageViewerController(new ImageViewer(), file);
		} else {
			Desktop.getDesktop().open(file.getSourceLocationAsPath().toFile());
		}
	}

	@Override
	public void onEditButtonClicked() {
		new SourceDialogController(project, dialogFactory, currentRecord, (Component)overviewTabPanel);
	}

	@Override
	public void onFocusGrabed() {
		setFields();
	}
	
	private void setFields() {
		overviewTabPanel.setAuthorField(currentRecord.getAuthor().getName());
		overviewTabPanel.setPublicationDateField(currentRecord.getPublishingYear());
		overviewTabPanel.setPublishedField(currentRecord.getPublicationData());
		overviewTabPanel.setTitleField(currentRecord.getTitle());
		overviewTabPanel.setCurrentMediumType(currentRecord.getMedium());
		overviewTabPanel.setSourceComboBox(extractSources(currentRecord));
	}

	@Override
	public void onRemoveButtonClicked(SourceLocation currentLocation) {
		if(!overviewTabPanel.positiveAnswerToQuestion("Remove this source location?"))
			return;
		currentRecord.removeSource(currentLocation);
		setFields();
	}
}
