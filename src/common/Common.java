package common;

import java.io.File;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Common {
	public static void fancyBtn(Button btn) 
	{
		btn.setStyle("-fx-background-color: #2e7eff;");

		btn.setOnMouseEntered(e -> {
			btn.setStyle("-fx-background-color: #5294ff;");
		});

		btn.setOnMousePressed(e -> {
			btn.setStyle("-fx-background-color: #2261c7;");
		});
		
		btn.setOnMouseReleased(e -> {
			btn.setStyle("-fx-background-color: #5294ff;");
		});

		btn.setOnMouseExited(e -> {
			btn.setStyle("-fx-background-color: #2e7eff;");
		});
	}
	
	public static Button createBtn(String text,Color color,double textSize,double width,double height) 
	{
		Button button = new Button(text);
		button.setPrefSize(width, height);
		button.setTextFill(color);
		button.setFont(Font.font(textSize));
		button.setCursor(Cursor.HAND);
		return button;
	}
	
	public static void AddImage(String imageURL,Group group,double width,double height) 
	{
		try {
			ImageView image = new ImageView(new Image(new File(imageURL).toURI().toURL().toExternalForm()));
			image.setPreserveRatio(true);
			image.setFitWidth(width);
			image.setFitHeight(height);
			group.getChildren().add(image);;

		} catch (Exception e) {
		}
	}
}
