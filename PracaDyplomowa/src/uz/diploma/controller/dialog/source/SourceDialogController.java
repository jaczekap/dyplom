package uz.diploma.controller.dialog.source;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import uz.diploma.gui.dialog.DialogViewFactory;
import uz.diploma.gui.dialog.element.AddingCharacterDialog;
import uz.diploma.gui.dialog.source.SourceDialog;
import uz.diploma.gui.dialog.source.SourceDialogView;
import uz.diploma.gui.window.panel.details.contexttab.panel.PanelView;
import uz.diploma.listeners.DataStoreEventsListener;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.HistoricalCharacter;
import uz.diploma.model.record.element.HistoricalPeriod;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.RecordElement;
import uz.diploma.model.record.source.SourceLocation;
import uz.diploma.model.record.source.SourceLocationFactory;
import uz.diploma.model.record.source.SourceType;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class is responsible for controlling dialog window for editing locations of 
 * sources associated with selected record. 
 * Assures that correct data are presented and manipulate model by adding, 
 * removing and editing sources locations  
 *
 */
public class SourceDialogController extends SourceDialogViewController {
	
	private String enteredSourceName;
	private SourceType currentType;
	private SourceLocation currentLocation;
	

	public SourceDialogController(Project project,
			DialogViewFactory viewFactory, Record currentRecord,
			Component parentView) {
		super(project, viewFactory, currentRecord, parentView);
	}

	@Override
	public void onSelectionChanged(ActionEvent event) {
		Validator.ensureIsNotNull(event, "event");
		if(dialog.getElement() != null) {
		dialog.setAllTextFields();
		getEventData();
		}
	}

	@Override
	public void onTextFieldChanged() {
		if(dialog.getElement() != null)
			getEventData();
	}

	@Override
	public void onCancelButtonClicked() {
		if(!dialog.positiveAnswerToQuestion("Are you sure?"))
			return;
		((JDialog)dialog).dispose();
	}

	@Override
	public void onAddButtonClicked() {
		getEventData();
		if(currentType == null) {
			dialog.showInformationDialog("Source Type can't be empty. Select Source Type.");
			return;
		}
		
		if(enteredSourceName.trim().equals("")) {
			dialog.showInformationDialog("Sourced location can't be empty");
			return;
		}
		
		if(!isValidInternetSource())
			return;
		
		if(!fileExists())
			return;
		
		SourceLocation tempSource = sourceFactory.getSource(currentType, enteredSourceName);
		
		if(!tempSource.equals(currentLocation)) {			
			if(!dialog.positiveAnswerToQuestion("add this source?"))
				return;
			
			currentRecord.addSource(sourceFactory.getSource(currentType, enteredSourceName));	
		} else {
			dialog.showInformationDialog("Source already exists");
		}
		
		fillDialogData();
		((JDialog)dialog).dispose();
	}

	@Override
	public void fillDialogData() {
		List<RecordElement> sourceList = new ArrayList<RecordElement>();
		currentRecord.getSourceLocationIterator().forEachRemaining(source -> sourceList.add(source));
		dialog.setSelectionComboBox(sourceList.stream().distinct().collect(Collectors.toList()));
		dialog.setAllTextFields();
		if(!sourceList.isEmpty())
			getEventData();
	}

	@Override
	public void onRemoveButtonClicked() {
		if(dialog.getElement() != null) {
			if(!dialog.positiveAnswerToQuestion("Remove this source location?"))
				return;
			
			currentRecord.removeSource((SourceLocation)dialog.getElement());
			fillDialogData();
		} else {
			dialog.showInformationDialog("Select item to remove first");
		}
	}
	
	private void getEventData() {
		currentLocation = (SourceLocation) dialog.getElement();
		enteredSourceName = ((SourceDialog)dialog).getSourceFieldText().trim();
		currentType = getCurrentType(((SourceDialog)dialog).getSelectedRadioButtonActionCommand());
		System.out.print(currentType);
	}
	
	private SourceType getCurrentType(String radioButtonComand) {
		if(radioButtonComand == SourceDialog.EXTERNAL_BUTTON_ACTION)
			return SourceType.EXTERNAL;
		else if(radioButtonComand == SourceDialog.FILE_BUTTON_ACTION)
			return SourceType.FILE;
		else if(radioButtonComand == SourceDialog.INTERNET_BUTTON_ACTION)
			return SourceType.INTERNET;
		else
			return null;
	}
	
	private boolean isValidInternetSource() {
		if(currentType == SourceType.INTERNET && Validator.containsSpaceCharacter(enteredSourceName)) {
			dialog.showInformationDialog("address cannot contain space character");
			return false;
		}
		return true;
	}
	
	private boolean fileExists() {
		if(currentType == SourceType.FILE && !Files.exists(Paths.get(enteredSourceName))) {
			dialog.showInformationDialog("file does not exist");
			return false;
		}
		return true;
	}
	
	@Override
	public void onSelectSourceButtonClicked() {
		JFileChooser fileChooser = new JFileChooser();
		int selectedOption = fileChooser.showOpenDialog(parentView);
		if(canOpenFile(selectedOption)) {
			String path = fileChooser.getSelectedFile().getPath();
			((SourceDialog)dialog).setPathTextField(path);
		}
	}

	private boolean canOpenFile(int selectedOption) {
		return selectedOption == JFileChooser.APPROVE_OPTION;
	}

}
