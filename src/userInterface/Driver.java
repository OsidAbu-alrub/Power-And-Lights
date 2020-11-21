package userInterface;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Driver extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		AnchorPane anchor = new AnchorPane();
		MainWindow.mainWindow(anchor);
		Scene scene = new Scene(anchor,1600,800);
		anchor.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		stage.setMinWidth(1200);
		stage.setMinHeight(600);
		stage.setScene(scene);
		stage.setTitle("Dynamic Programming");
		stage.setResizable(true);
		stage.setMaximized(true);
		stage.show();	
	}

}
