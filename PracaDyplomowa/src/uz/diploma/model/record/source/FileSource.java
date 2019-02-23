package uz.diploma.model.record.source;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import uz.diploma.viewer.model.ImageTag;

public class FileSource extends SourceLocation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8334252242883677559L;
	private List<ImageTag> tags;
	
	public FileSource(String source) {
		super(source);
		isOpenable = true;
		sourceType = SourceType.FILE;
		this.source = Paths.get(source).toString();
		tags = new ArrayList<ImageTag>();
	}
	
	public Path getSourceLocationAsPath() {
		return Paths.get(source);
	}
	
	public void addTag(ImageTag tag) {
			tags.add(tag);
	}
	
	public void removeTag(ImageTag tag) {
			tags.remove(tag);
	}
	
	public List<ImageTag> getTags() {
		return tags;
	}

}
