package uz.diploma.model.project;

import java.util.EventObject;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * object that is send as event object when objects are notified about load event occurrence 
 *
 */
public class LoadEvent extends EventObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8792836060718822278L;
	private Project project;

	/**
	 * @param source - object on which the event occurred 
	 * @param project - project that has been loaded
	 */
	public LoadEvent(Object source, Project project) {
		super(source);
		Validator.ensureIsNotNull(project, "project");
		this.project = project;
	}

	public Project getProject() {
		return project;
	}
}
