package userInterface;

import domain.ARROWS;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.Light;

public class TableWindow {
	
	public static void tableWindow() 
	{
		try {
		AnchorPane anchor = new AnchorPane();
		tableWindow(anchor);
		Scene scene = new Scene(anchor);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.setTitle("pls full mark");
		stage.show();
		}
		catch(Exception e) {}
		
		
	}
	
	private static void tableWindow(AnchorPane anchor) {
		StackPane stack = new StackPane();
		stack.setPrefSize(anchor.getWidth(), anchor.getHeight());
		anchor.getChildren().add(stack);
		AnchorPane.setLeftAnchor(stack, 200.0);
		AnchorPane.setTopAnchor(stack, 100.0);
		
		ScrollPane scroll = new ScrollPane();
		scroll.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scroll.setVmax(1);
		scroll.setHmax(1);
		scroll.setMaxSize(1500, 800);
		scroll.setMinSize(1500, 800);
		scroll.setMaxSize(stack.getWidth(), stack.getHeight());
		stack.getChildren().add(scroll);
			
		TilePane hPane = new TilePane();
		hPane.setPrefColumns(Light.comp.getPower().length+1);
		hPane.setOrientation(Orientation.HORIZONTAL);
		scroll.setContent(hPane);
		
		Background background = (new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
		
		Label label = new Label("Lights\\Source");
		label.setAlignment(Pos.CENTER);
		label.setPrefSize(150, 50);
		label.setBackground(background);
		label.setFont(Font.font("Arial",FontWeight.BOLD,20));
		label.setStyle("-fx-border-width: 2;" +"-fx-border-height: 2;"+ "-fx-border-color: black;");
		hPane.getChildren().add(label);
		
		//adding first row in table
		for(int i = 0 ; i < Light.comp.getPower().length ; i++) 	
			addNumber(hPane,i,Color.BLACK,background);
		
		
		//building the table
		for(int i = 0 ; i < Light.comp.getTable().length ; i++)
			for(int j = 0; j < Light.comp.getTable().length ; j++) {
				if(j == 0) // if first column, give certain attributes
					addNumber(hPane,Light.comp.getLights()[i],Color.BLACK,background);
				
				// here we color code the numbers
				// to know our movement pattern
				if(Light.comp.getArrows()[i][j] == ARROWS.LeftTake)
					addNumber(hPane,Light.comp.getTable()[i][j],Color.RED);
				else if(Light.comp.getArrows()[i][j] == ARROWS.UpTake)
					addNumber(hPane,Light.comp.getTable()[i][j],Color.BLUE);
				else if(Light.comp.getArrows()[i][j] == ARROWS.DiagonalTake)
					addNumber(hPane,Light.comp.getTable()[i][j],Color.HOTPINK);
				else
					addNumber(hPane,Light.comp.getTable()[i][j],Color.BLACK);
			}
		
		/*GUIDE*/
		VBox v = new VBox(20);
		Label[] colors= new Label[4];
		for(int i = 0 ; i < colors.length ; i++) {
			colors[i] = new Label();
			colors[i].setPrefSize(150, 100);
			colors[i].setTextFill(Color.WHITE);
			colors[i].setStyle("-fx-border-width: 2;" +"-fx-border-height: 2;"+ "-fx-border-color: black;");
			colors[i].setAlignment(Pos.CENTER);
			colors[i].setFont(Font.font(24));
			v.getChildren().add(colors[i]);
		}
		colors[0].setText("Left");
		colors[0].setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		colors[1].setText("Up");
		colors[1].setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		colors[2].setText("Diagonal");
		colors[2].setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
		colors[3].setText("Nothing");
		colors[3].setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		/*GUIDE*/
		
		anchor.getChildren().add(v);
		AnchorPane.setLeftAnchor(v, 20.0);
		AnchorPane.setTopAnchor(v, 200.0);

	}
	
	// utility method for putting number in cell with certain color
	private static void addNumber(TilePane hPane,int number,Color color) 
	{
		Label label= new Label(String.format("%10d", number));
		label.setTextFill(color);
		label.setFont(Font.font("",FontWeight.BOLD,24));
		label.setPrefSize(150, 50);
		label.setStyle("-fx-border-width: 2;" +"-fx-border-height: 2;"+ "-fx-border-color: black;");
		hPane.getChildren().add(label);
	}
	
	//for background of first row and first column (also utility method)
	private static void addNumber(TilePane hPane,int number,Color color,Background background) 
	{
		Label label= new Label(String.format("%10d", number));
		label.setTextFill(color);
		label.setBackground(background);
		label.setFont(Font.font("",FontWeight.BOLD,24));
		label.setPrefSize(150, 50);
		label.setStyle("-fx-border-width: 2;" +"-fx-border-height: 2;"+ "-fx-border-color: black;");
		hPane.getChildren().add(label);
	}
}
