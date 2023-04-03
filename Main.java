package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("WebBrowser.fxml")));
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMinWidth(500);
			primaryStage.setMinHeight(400);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		System.out.println(args);
	}
}


