package uz.diploma.gui.window.panel.details.overviewtab;

import java.util.List;

import uz.diploma.listeners.OverviewTabListener;
import uz.diploma.model.record.element.MediumType;
import uz.diploma.model.record.element.RecordElement;
import uz.diploma.model.record.source.SourceLocation;

/**
 * @author Spider
 * 
 * Interface that need to be implemented by overview tab view classes
 *
 */
public interface OverviewTabInterface {

	public void addOverviewTabListener(OverviewTabListener listener);
	public void removeOverviewTabListener(OverviewTabListener listener);
	public void setPublishedField(String fieldValue);
	public void setPublicationDateField(String fieldValue);
	public void setTitleField(String fieldValue);
	public void setAuthorField(String fieldValue);
	public void fillMediumTypeList();
	public void setCurrentMediumType(MediumType medium);
	public void mediumChanged(MediumType selectedMedium);
	public void titleChanged(String fieldValue);
	public void publicationDateChanged(String fieldValue);
	public void authorChanged(String fieldValue);
	public void publishedChanged(String fieldValue);
	public void sourceComboBoxChanged(SourceLocation selectionValue);
	public void setSourceComboBox(List<RecordElement> locationsList);
	public void setLocationTypeLabel();
	public void openButtonClicked(SourceLocation currentLocation);
	public void setOpenBattonEnabled(boolean option);
	public void editButtonClicked();
	public void focusGrabed();
	public void showInformationDialog(String message);
	public void removeButtonClicked(SourceLocation currentLocation);
	public boolean positiveAnswerToQuestion(String question);
	public int showQuestionDialog(String question);
}
