package application;
	
import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {
	
	public static final int APP_WIDTH = 600;
	public static final int APP_HEIGHT = 600;
	
	private MainController controller = new MainController();
	
	@Override
	public void start(Stage primaryStage) {
		
		controller.init();
		
		primaryStage.setScene(controller.getView());
		primaryStage.show();
		
		// Launch the controller/model on a separate thread so the UI can be updated in real time
		Runnable task = () -> { controller.run(); };
		new Thread(task).start();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
