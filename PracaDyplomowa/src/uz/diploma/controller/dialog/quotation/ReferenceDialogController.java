package uz.diploma.controller.dialog.quotation;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import uz.diploma.controller.dialog.DialogViewController;
import uz.diploma.gui.dialog.DialogViewFactory;
import uz.diploma.gui.dialog.quotation.ReferenceDialogView;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.Reference;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class is responsible for controlling dialog window for adding reference 
 * Manipulates model's data by adding reference entities
 *
 */
public class ReferenceDialogController implements EventListener {
	
	private Record currentRecord;
	private Record referenceRecord;
	private ReferenceDialogView dialog;
	private Project project;
	private String referenceLocation;
	private String referenceNotes;

	/**
	 * @param project - project which data are manipulated
	 * @param dialog - dialog window view
	 * @param currentRecord - currently selected record
	 * @param parentView - dialog window is positioned relatively to this component
	 */
	public ReferenceDialogController(Project project, ReferenceDialogView dialog, Record currentRecord, Component parentView) {
		Validator.ensureIsNotNull(project, "project");
		Validator.ensureIsNotNull(dialog, "dialog");
		Validator.ensureIsNotNull(currentRecord, "currentRecord");
		Validator.ensureIsNotNull(parentView, "parentView");
		this.currentRecord = currentRecord;
		this.dialog = dialog;
		this.project = project;
		dialog.addDialogViewListener(this);
		dialog.setSelectionComboBox(filterRecordList(recordsAsList(project.getRecordsIterator())));
		((Window) dialog).setLocationRelativeTo(SwingUtilities.getWindowAncestor(parentView));
		((Component) dialog).setVisible(true);
	}

	
	public void onCancelButtonClicked() {
		if(!dialog.positiveAnswerToQuestion("Are you sure?"))
			return;
		
		((JDialog)dialog).dispose();
	}

	
	public void onAddButtonClicked() {
		if(!dialog.positiveAnswerToQuestion("Add this reference?"))
			return;
		
		referenceRecord = dialog.getSelectedRecord();
		referenceLocation = dialog.getLocationText();
		referenceNotes = dialog.getNotes();
		
		Reference referenceTo = new Reference(referenceRecord, referenceNotes, referenceLocation, true);
		Reference referenceFrom = new Reference(referenceRecord, referenceNotes, referenceLocation, false);
		
		currentRecord.addReference(referenceTo);
		referenceRecord.addReference(referenceFrom);
		
		((JDialog)dialog).dispose();
	}
	
	private List<Record> filterRecordList(List<Record> recordList) {
		return recordList.stream().filter(record -> !record.equals(currentRecord)).collect(Collectors.toList());
	}
 
	private List<Record> recordsAsList(Iterator<Record> iter) {
		List<Record> records = new ArrayList<Record>();
		iter.forEachRemaining(e -> records.add(e));
		return records;
	}
	
}
