package application;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class SampleController extends Application {
  @FXML
    private FontAwesomeIcon backButton;

    @FXML
    private FontAwesomeIcon cancleButton;

    @FXML
    private ProgressBar downloadProgressBar;

    @FXML
    private FontAwesomeIcon favoriteButton;

    @FXML
    private FontAwesomeIcon forward;

    @FXML
    private Button fullScreenButton;

    @FXML
    private ProgressBar loadProgressBar;

    @FXML
    private FontAwesomeIcon menuButton;

    @FXML
    private FontAwesomeIcon parentButton;

    @FXML
    private FontAwesomeIcon reloadButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private WebView webView;

    @FXML
    private Button zoomDefault;

    @FXML
    private Button zoomMins;

    @FXML
    private Button zoomPlusButton;
    
    public Stage newWindow=new Stage();
    
    private final Color paintDis = new Color(0.7412, 0.7647, 0.7765, 1.0);
    private final Color paintActi = new Color(0.3529, 0.749, 0.9608, 1.0);
    
    private WebEngine engine;
	private String searchEngine="http://google.com";
	final DoubleProperty zoomProperty = new SimpleDoubleProperty(200);
    
    @FXML
    void initialize() {
    	engine=webView.getEngine();
        engine.load(searchEngine);
        searchTextField.setPromptText("Search google or input url");
        loadProgressBar.progressProperty().bind(engine.getLoadWorker().progressProperty());
        reloadButton.setDisable(true);
        cancleButton.setDisable(false);
        cancleButton.setFill(paintActi);
        reloadButton.setFill(paintDis);
        engine.getLoadWorker().stateProperty()
        .addListener((obs, oldValue, newValue) -> {
          if (newValue == State.SUCCEEDED) {
            System.out.println("finished loading");
            loadProgressBar.setVisible(false);
            reloadButton.setDisable(false);
            cancleButton.setDisable(true);
            cancleButton.setFill(paintDis);
            reloadButton.setFill(paintActi);
          }else if (newValue == State.READY) {
            System.out.println("READY loading");
            loadProgressBar.setVisible(true);
            reloadButton.setDisable(true);
            cancleButton.setDisable(false);
            cancleButton.setFill(paintActi);
            reloadButton.setFill(paintDis);
          }else if (newValue == State.FAILED) {
            System.out.println("FAILED loading");
            loadProgressBar.setVisible(false);
            reloadButton.setDisable(false);
            cancleButton.setDisable(true);
            cancleButton.setFill(paintDis);
            reloadButton.setFill(paintActi);
          }else if (newValue == State.RUNNING) {
            System.out.println("RUNNING loading");
            loadProgressBar.setVisible(true);
            searchTextField.setText(engine.getLocation());
            reloadButton.setDisable(true);
            cancleButton.setDisable(false);
            reloadButton.setFill(paintDis);
            cancleButton.setFill(paintActi);
          }else if (newValue == State.CANCELLED) {
            System.out.println("CANCELLED loading");
            loadProgressBar.setVisible(false);
            reloadButton.setDisable(false);
            cancleButton.setDisable(true);
            cancleButton.setFill(paintDis);
            reloadButton.setFill(paintActi);
          }
        });
		
		
        //web history 
        final WebHistory history = engine.getHistory();
        history.getEntries().addListener(
            (ListChangeListener.Change<? extends WebHistory.Entry> c) -> {
                c.next();
                c.getRemoved().stream().forEach((e) -> {
                        System.out.println(e.getTitle());
                        //comboBox.getItems().remove(e.getUrl());
            });

                c.getAddedSubList().stream().forEach((e) -> {

                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                    LocalDateTime now = LocalDateTime.now();  
                    try {
                        URL url=new URL(e.getUrl());
                                                String Host = url.getHost();
                                                System.out.println(Host);

                                                History.addHistory(Host, url.toString(), dtf.format(now));
                                                String Id=Seation.getSeationId();
                                                if(Id!="") {
                                                        dataBase.addHistory(Id, Host, url.toString(), dtf.format(now));
                                                }
                                         } catch (MalformedURLException e1) {
                                                e1.printStackTrace();
                                         }
                        //comboBox.getItems().add(e.getUrl());
            });
        });

		
    	try {		
            newWindow.addEventHandler(WindowEvent.WINDOW_SHOWING, new EventHandler<WindowEvent>() {

                @Override
                public void handle(WindowEvent event) {
                        parentButton.setDisable(true);    				
                }
            });
        } catch (Exception e) {
                System.out.println(e.getMessage());
        }
    }

    @FXML
    void addToFavorite(MouseEvent event) {
    	if(!searchTextField.getText().isEmpty()) {
    		URL url;
			try {
				url = new URL(searchTextField.getText());
				String Host = url.getHost();
				String webSiteName=searchTextField.getText().substring(searchTextField.getText().indexOf(".")+1, searchTextField.getText().indexOf(".com"));
				if(History.AddBookMark(webSiteName, Host)) {
					String id=Seation.getSeationId();
					if(id!="") {
						dataBase.addBookMark(id, webSiteName, Host);
					}
				}
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
    	}
    }

    @FXML
    void cancel(MouseEvent event) {
    	engine.getLoadWorker().cancel();
    }
    @FXML
    void dismplayMenu(MouseEvent event) {

    }

    @FXML
    void load(ActionEvent event) {
    	String urlInput=searchTextField.getText();

		Task<Void> task=new Download(urlInput);

		task.stateProperty().addListener((obs, oldValue, newValue) -> {

			
			
	          if (newValue == State.SUCCEEDED) {
	              System.out.println("finished downloading");
	              downloadProgressBar.setVisible(false);
	            }else if (newValue == State.READY) {
	  	            System.out.println("READY downloading");
	            }else if (newValue == State.FAILED) {
	  	            System.out.println("FAILED downloading");
	  	            if((searchTextField.getText().contains("http://")||searchTextField.getText().contains("https://"))) {
	  	    	    	engine.load(searchTextField.getText());    		
                            }else if(searchTextField.getText().contains(".com")||searchTextField.getText().contains(".et")||searchTextField.getText().contains(".org")) {
                                    engine.load("https://"+searchTextField.getText()); 
                            }else  {
                                    engine.load("https://www.google.com/search?q="+searchTextField.getText()); 
                            }

		              downloadProgressBar.setVisible(false);
	            }else if (newValue == State.RUNNING) {
	            	String fileName=searchTextField.getText().substring(urlInput.lastIndexOf("/")+1, urlInput.lastIndexOf("."));
	    			System.out.println(3333);
	  	            System.out.println("RUNNING downloading");
	  	            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	          	    LocalDateTime now = LocalDateTime.now();  
		            searchTextField.setText(engine.getLocation());
	  	            downloadProgressBar.progressProperty().bind(task.progressProperty());
					downloadProgressBar.setMinWidth(400);
					History.AddDownloadStory(fileName, urlInput, dtf.format(now));
					String id=Seation.getSeationId();
					System.out.println(id);
					if(id!="") {
						dataBase.addDownload(id, fileName, urlInput, dtf.format(now));
					}
					
	            }else if (newValue == State.CANCELLED) {
	  	            System.out.println("CANCELLED downloading");

		              downloadProgressBar.setVisible(false);
	            }
	          });

		System.out.println(2);
		Thread thread=new Thread(task);
		thread.setDaemon(true);
		thread.start();
    }

    @FXML
    void loadBack(MouseEvent event) {
    	try {
			engine.getHistory().go(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void loadForward(MouseEvent event) {
    	try {
			engine.getHistory().go(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void mackFfulllScreenWindows(ActionEvent event) {
    	((Stage) fullScreenButton.getScene().getWindow()).setFullScreen(true);
    	
    }

    @FXML
    void makeZoomDefault(ActionEvent event) {
    	webView.setZoom(1);
    }

    @FXML
    void parentalControlDisplay(MouseEvent  event) {
    	System.out.println(1);
    	try {
			FileReader seation=new FileReader("seation.txt");
			BufferedReader bReader=new BufferedReader(seation);
			String userID=bReader.readLine();
			bReader.close();

	    	System.out.println(userID);
			if(userID==null) {
				hostLogIn();
			}else {
				String QUERY = "select ID,fName,lName,email,password from user where ID =?;";
				try {
					new dataBase();
					PreparedStatement pStatement = dataBase.connectToDataBase().prepareStatement(QUERY);
					pStatement.setString(1, userID);
					ResultSet rs = pStatement.executeQuery();
					if(rs.next()) {
						localLogIn();
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				
			}
		}catch(FileNotFoundException ex) {
			try {
				FileWriter seation =new FileWriter("seation.txt");
				BufferedWriter bW=new BufferedWriter(seation);
				bW.close();
				hostLogIn();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
    	catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    	

    }
    private void localLogIn() {
    	try {
			
			Parent browser=FXMLLoader.load(getClass().getResource("localLogin.fxml"));
			Scene secondScene = new Scene(browser);
			newWindow.setScene(secondScene);
			newWindow.setResizable(false);
			newWindow.setTitle("Parent Control");
			newWindow.show();
			parentButton.setDisable(true);
                        parentButton.setFill(paintDis);
			newWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent e) {
                                parentButton.setDisable(false);
                                parentButton.setFill(paintActi);
			    }
			  });
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    }
    private void hostLogIn() {
    	try {
			
			Parent browser=FXMLLoader.load(getClass().getResource("LoginCreateAccount.fxml"));
			Scene secondScene = new Scene(browser);
			newWindow.setScene(secondScene);
			newWindow.setResizable(false);
			newWindow.setTitle("Parent Control");
			newWindow.show();
			parentButton.setDisable(true);
                        parentButton.setFill(paintDis);
			newWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent e) {
                                parentButton.setDisable(false);
                                parentButton.setFill(paintActi);
			    }
			  });
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    }
    @Override
    public void stop(){
        System.out.println("Stage is closing");
        // Save file
    }
    @FXML
    void reload(MouseEvent event) {
    	engine.reload();
		searchTextField.setText(engine.getLocation());
    }

    @FXML
    void zoomMins(ActionEvent event) {
    	if(webView.getZoom()>0.35049389948139215) {
			webView.setZoom(webView.getZoom() / 1.1);
		};
    }

    @FXML
    void zoomPlus(ActionEvent event) {
    	if(webView.getZoom()<2.1435888100000002) {
			webView.setZoom(webView.getZoom() * 1.1);
		};
    }

	@Override
	public void start(Stage newWindow) throws Exception {
		try {
			
			newWindow.show();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
