package business.representations;

import java.io.Serializable;

public class ImageTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Boolean directory;

	public Boolean getDirectory() {
		return directory;
	}

	public void setDirectory(Boolean directory) {
		this.directory = directory;
	}

	private String mediaType;

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

}
