package pe.com.empresa.rk.exception;

public class BusinessException extends RuntimeException {
    
	private String errorCode;
    private String severity;
	private String summary;
	private String detail;
    
    public BusinessException(String severity, String summary, String detail) {
		super(detail);
		this.severity = severity;
		this.summary = summary;
		this.detail = detail;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}