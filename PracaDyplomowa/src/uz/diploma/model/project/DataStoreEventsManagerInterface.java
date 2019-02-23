package uz.diploma.model.project;

import java.io.FileNotFoundException;
import java.io.IOException;

import uz.diploma.listeners.LoadEventListener;

/**
 * @author Spider
 * 
 * Interface for classes that are responsible for managing storing operations such as load and save project 
 *
 */
public interface DataStoreEventsManagerInterface {

	/**
	 * @param filePath - path to file that will be open
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 */
	public void onOpen(String filePath) throws FileNotFoundException, ClassNotFoundException, IOException;
	/**
	 * @param filePath - path that file will be save to
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void onSave(String filePath) throws FileNotFoundException, IOException;
	public void addLoadEventListener(LoadEventListener listener);
	public void removeLoadEventListener(LoadEventListener listener);
	
}
