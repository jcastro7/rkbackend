package pe.com.empresa.rk.dto;

/**
 * Created by josediaz on 22/02/2017.
 */
public class JsonResult {

    private String status="OK";
    private String severity ="";  //error, info, warn
    private String summary="";  //title of screen
    private String detail=""; //error message

    private String fileDocName="";

    public JsonResult(String status, String severity, String summary, String detail) {
        this.status = status;
        this.severity = severity;
        this.summary = summary;
        this.detail = detail;
    }

    public JsonResult() {
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getFileDocName() {
        return fileDocName;
    }

    public void setFileDocName(String fileDocName) {
        this.fileDocName = fileDocName;
    }
}
