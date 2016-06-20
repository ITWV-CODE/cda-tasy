package br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2;

public class RelatedDocumentObject extends Object {

	private String currentId;
	private String currentPdfId;
	private long currentVersion;
	private String parentId;
	private String parentPdfId;
	private long parentVersion;

	public RelatedDocumentObject(String currentId, long currentVersion, String parentId, long parentVersion) {
		super();
		this.currentId = currentId;
		this.currentVersion = currentVersion;
		this.parentId = parentId;
		this.parentVersion = parentVersion;
	}

	public RelatedDocumentObject(String currentId, long currentVersion) {
		super();
		this.currentId = currentId;
		this.currentVersion = currentVersion;
	}

	public String getCurrentId() {
		return currentId;
	}

	public void setCurrentId(String currentId) {
		this.currentId = currentId;
	}

	public long getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(long currentVersion) {
		this.currentVersion = currentVersion;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public long getParentVersion() {
		return parentVersion;
	}

	public void setParentVersion(long parentVersion) {
		this.parentVersion = parentVersion;
	}

	public RelatedDocumentObject() {}

	public String getCurrentPdfId() {
		return currentPdfId;
	}

	public void setCurrentPdfId(String currentPdfId) {
		this.currentPdfId = currentPdfId;
	}

	public String getParentPdfId() {
		return parentPdfId;
	}

	public void setParentPdfId(String parentPdfId) {
		this.parentPdfId = parentPdfId;
	}
}