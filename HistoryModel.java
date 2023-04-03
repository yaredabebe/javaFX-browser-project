package model;

public class HistoryModel {
	private String HostName;
	private String Url;
	private String Date;
	private boolean Block;
	public HistoryModel(String Site, String url, String date, boolean con) {
		HostName = Site;
		Url = url;
		Date = date;
		Block = con;
	}

    public String getHostName() {
        return HostName;
    }

    public void setHostName(String HostName) {
        this.HostName = HostName;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public boolean isBlock() {
        return Block;
    }

    public void setBlock(boolean Block) {
        this.Block = Block;
    }

    
	

}
