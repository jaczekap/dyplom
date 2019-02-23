package uz.diploma.listeners;

import java.util.EventListener;

import uz.diploma.model.record.element.MediumType;
import uz.diploma.model.record.source.SourceLocation;

/**
 * @author Spider
 * 
 * Interface need to be implemented by classes that need to be notified
 * about events occurred on overview tab
 *
 */
public interface OverviewTabListener extends EventListener {

	public void onMediumChanged(MediumType selectedMedium);
	public void onTitleChanged(String currentTitle);
	public void onPublicationDateChanged(String fieldValue);
	public void onAuthorChanged(String fieldValue);
	public void onPublishedChanged(String fieldValue);
	public void onSourceComboBoxChanged(SourceLocation selectionValue);
	public void onOpenButtonClicked(SourceLocation currentLocation);
	public void onEditButtonClicked();
	public void onFocusGrabed();
	public void onRemoveButtonClicked(SourceLocation currentLocation);

}