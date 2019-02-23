package uz.diploma.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import uz.diploma.gui.window.panel.details.RightPanelInterface;
import uz.diploma.gui.window.panel.record.RecordsView;
import uz.diploma.gui.window.panel.record.ResultListListener;
import uz.diploma.listeners.LoadEventListener;
import uz.diploma.listeners.RecordMenuListener;
import uz.diploma.listeners.SearchTextListener;
import uz.diploma.model.project.LoadEvent;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.Record;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class is responsible for controlling data flow between model and left panel
 *
 */
public class LeftPaneController implements LoadEventListener, SearchTextListener, RecordMenuListener {
	
	private Project project;
	private RecordsView leftPanel;
	
	
	/**
	 * @param leftPanel - left panel view
	 * @param project - project which data are presented
	 */
	public LeftPaneController(RecordsView leftPanel, Project project) {
		Validator.ensureIsNotNull(project, "project");
		this.project = project;
		Validator.ensureIsNotNull(leftPanel, "leftPanel");
		this.leftPanel = leftPanel;
		leftPanel.addSearchTextListener(this);
		leftPanel.setResultList(recordsAsList(project.getRecordsIterator()));
		//leftPanel.setResultList(project.getProjectRecords());
		leftPanel.setResultListSelection();
	}

	@Override
	public void loadEventReceived(LoadEvent loadEvent) {
		Validator.ensureIsNotNull(loadEvent, "loadEvent", "Project can not be loaded");
		this.project = loadEvent.getProject();
		//leftPanel.setResultList(project.getProjectRecords());
		leftPanel.setResultList(recordsAsList(project.getRecordsIterator()));
		leftPanel.setResultListSelection();
	}

	@Override
	public void onSearchTextChanged(String textFromField) {
		if(!textFromField.contains("type for search"))
			leftPanel.setResultList(project.findAll(textFromField));	
	}

	@Override
	public void recordMenuItemSelected() {
		//leftPanel.setResultList(project.getProjectRecords());
		leftPanel.setResultList(recordsAsList(project.getRecordsIterator()));
		leftPanel.setResultListSelection();
	}

	private List<Record> recordsAsList(Iterator<Record> iter) {
		List<Record> records = new ArrayList<Record>();
		iter.forEachRemaining(e -> records.add(e));
		return records;
	}
}
