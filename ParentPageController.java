package application;

import static application.dataBase.connectToDataBase;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BookMarkModel;
import model.DownloadModel;
import model.HistoryModel;

public class ParentPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> ControlBlock;

    @FXML
    private TableColumn<HistoryModel, Boolean> ControlHistory;

    @FXML
    private TableColumn<HistoryModel, String> DateHstory;

    @FXML
    private TableColumn<DownloadModel, String> DonloadDate;

    @FXML
    private TableColumn<DownloadModel, String> DownloadLink;

    @FXML
    private TableColumn<DownloadModel, String> FileNameDownlad;

    @FXML
    private TableColumn<BookMarkModel, String> NameBoolMark;

    @FXML
    private TableColumn<?, ?> VisitedBlock;

    @FXML
    private TableColumn<BookMarkModel, String> WebSIteBookMark;

    @FXML
    private TableColumn<?, ?> WebSiteBlock;

    @FXML
    private TableColumn<HistoryModel, String> WebSiteHstory;

    @FXML
    private TableColumn<HistoryModel, String> WebUrlHstory;

    @FXML
    private TableView<?> blockSiteTable;

    @FXML
    private TableView<BookMarkModel> bookmarkTable;

    @FXML
    private TableView<DownloadModel> downloadTable;

    @FXML
    private TableView<HistoryModel> historyTable;

    @FXML
    private Button reloadBlockSiteButton;

    @FXML
    private Button reloadBookMarekButton;

    @FXML
    private Button reloadDownloadButton;

    @FXML
    private Button reloadHisoryButton;
    
    String sql=null;
    Connection con=null;
    ResultSet rs=null;
    History history=null;
    PreparedStatement ps;
    String id=Seation.getSeationId();
    
    ObservableList<HistoryModel> HistoryList=FXCollections.observableArrayList();
    ObservableList<DownloadModel> DownloadList=FXCollections.observableArrayList();
    ObservableList<BookMarkModel> BookMarkList=FXCollections.observableArrayList();

    

    @FXML
    void reloadBlockSite(ActionEvent event) {

    }

    @FXML
    void reloadBookMarek() {
         BookMarkList.clear();
        try{
            sql="SELECT * FROM bookmark where id="+id;
            ps=connectToDataBase().prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                BookMarkList.add(new BookMarkModel(
                        rs.getString("webSiteName"), 
                        rs.getString("webSiteHost")
                ));
                bookmarkTable.setItems(BookMarkList);
            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(ParentPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 @FXML
    private void reloadHisory() {
        HistoryList.clear();
        try{
            sql="select * from history where id="+id;
            ps=connectToDataBase().prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                HistoryList.add(new HistoryModel(
                        rs.getString("HostName"), 
                        rs.getString("Url"), 
                        rs.getString("Date"), 
                        rs.getBoolean("Block")));
                System.out.println("qqqqqqqqq"+rs.getString("Url"));
                historyTable.setItems(HistoryList);
            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(ParentPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void reloadDownload() {
        DownloadList.clear();
        try{
            sql="SELECT * FROM download where id="+id;
            ps=connectToDataBase().prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                DownloadList.add(new DownloadModel(
                        rs.getString("FileName"), 
                        rs.getString("DownlodLink"), 
                        rs.getString("Date")
                ));
                downloadTable.setItems(DownloadList);
            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(ParentPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    void loadData(){
        con=connectToDataBase();
        
        WebSiteHstory.setCellValueFactory(new PropertyValueFactory<>("HostName"));
        WebUrlHstory.setCellValueFactory(new PropertyValueFactory<>("Url"));
        DateHstory.setCellValueFactory(new PropertyValueFactory<>("Date"));
        ControlHistory.setCellValueFactory(new PropertyValueFactory<>("Block"));
        
        DonloadDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        DownloadLink.setCellValueFactory(new PropertyValueFactory<>("DownlodLink"));
        FileNameDownlad.setCellValueFactory(new PropertyValueFactory<>("FileName"));
        
        NameBoolMark.setCellValueFactory(new PropertyValueFactory<>("webSiteName"));
        WebSIteBookMark.setCellValueFactory(new PropertyValueFactory<>("webSiteHost"));
    }
    @FXML
    void initialize() {
        loadData();
        reloadHisory();
        reloadDownload();
        reloadBookMarek();
    }
   
}
