package application;



import java.io.IOException;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;


public class WebBrowserController {

	@FXML
    private TabPane tabPane;

    @FXML
    private Tab tab1;

    @FXML
    private Tab newTab;

  
    @FXML
    void initialize() {
    	newTab.setClosable(false);
    	tab1.setClosable(false);
    	Label label=new Label();
    	label.setText("hello");
        try {
        	FXMLLoader browser=new FXMLLoader(getClass().getResource("Sample.fxml"));
        	Node n=(Node)browser.load();
        	AnchorPane pan=new AnchorPane();
        	tab1.setContent(pan);
        	AnchorPane.setBottomAnchor(n,  0.0);
        	AnchorPane.setLeftAnchor(n,(double) 0);
        	AnchorPane.setRightAnchor(n,  0.0);
        	AnchorPane.setTopAnchor(n,(double) 0);
        	pan.getChildren().add(n);
        	tabPane.getSelectionModel().selectedItemProperty().addListener(this::onTabSelection);
		} catch (IOException e) {
			e.printStackTrace();
		}
        final ObservableList<Tab> tabs = tabPane.getTabs();
        tab1.closableProperty().bind(Bindings.size(tabs).greaterThan(2));
    }
    
    private void onTabSelection(ObservableValue<? extends Tab> observable, Tab oldSelectedTab, Tab newSelectedTab) {
        if (newSelectedTab == newTab) {
            Tab tab = new Tab();
            
            FXMLLoader browser=new FXMLLoader(getClass().getResource("Sample.fxml"));
        	try {
				Node n=(Node)browser.load();
	        	AnchorPane pan=new AnchorPane();
	        	tab.setContent(pan);
	        	AnchorPane.setBottomAnchor(n,  0.0);
	        	AnchorPane.setLeftAnchor(n,(double) 0);
	        	AnchorPane.setRightAnchor(n,  0.0);
	        	AnchorPane.setTopAnchor(n,(double) 0);
	        	pan.getChildren().add(n);
			} catch (IOException e) {
				e.printStackTrace();
			}
        
            

            final ObservableList<Tab> tabs = tabPane.getTabs();
            tab.closableProperty().bind(Bindings.size(tabs).greaterThan(2));
            tab.setText("new tab");
            tabs.add(tabs.size() - 1, tab);
            tabPane.getSelectionModel().select(tab);
        }
    }
  
}
