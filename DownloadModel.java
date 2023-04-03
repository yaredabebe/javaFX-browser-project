
package model;

/**
 *
 * @author SWL
 */
public class DownloadModel {        
     	private String FileName;
        private String DownlodLink;
        private String Date;

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    public String getDownlodLink() {
        return DownlodLink;
    }

    public void setDownlodLink(String DownlodLink) {
        this.DownlodLink = DownlodLink;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public DownloadModel(String FileName, String DownlodLink, String Date) {
        this.FileName = FileName;
        this.DownlodLink = DownlodLink;
        this.Date = Date;
    }
}
