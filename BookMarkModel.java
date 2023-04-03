/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author SWL
 */
public class BookMarkModel {
    private String webSiteName ;	
    private String webSiteHost 	;

    public BookMarkModel(String webSiteName, String webSiteHost) {
        this.webSiteName = webSiteName;
        this.webSiteHost = webSiteHost;
    }

    public String getWebSiteName() {
        return webSiteName;
    }

    public void setWebSiteName(String webSiteName) {
        this.webSiteName = webSiteName;
    }

    public String getWebSiteHost() {
        return webSiteHost;
    }

    public void setWebSiteHost(String webSiteHost) {
        this.webSiteHost = webSiteHost;
    }
    
}
