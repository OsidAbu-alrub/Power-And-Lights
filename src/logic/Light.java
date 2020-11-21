package logic;

import java.util.Arrays;

import domain.Component;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Light {
	
	public static Component comp;
	
	public static void generateLight(int[] lights,VBox pne,VBox vStat,boolean random) {
		
		pne.getChildren().clear();
		
		if(random) { // random == true, then generate random sequence
			comp = LCS.generateRandomSequence(lights.length);
		} else { //else, take lights from user
			comp = LCS.generateLights(lights);
		}
		
		//printing out summary
		((Label)vStat.getChildren().get(0)).setText("Power: "+Arrays.toString(comp.getPower()).replace("[0, ", "["));
		((Label)vStat.getChildren().get(1)).setText("Lights: "+Arrays.toString(comp.getLights()).replace("[0, ", "["));
		((Label)vStat.getChildren().get(2)).setText("Lit Lights: "+Arrays.toString(comp.getWantedLights()));
		((Label)vStat.getChildren().get(3)).setText("Number of Lights Lit: "+comp.getWantedLights().length);
		
		if(comp.getLights().length <= 100)
			for (int i = 1; i < comp.getLights().length; i++) {
				HBox group;
				if (Arrays.binarySearch(comp.getWantedLights(), comp.getLights()[i]) >= 0)
					group = Light.generateLight(true, comp.getLights()[i], i); //if true, that means light is on
				else
					group = Light.generateLight(false, comp.getLights()[i], i); //if true, that means light is off
				pne.getChildren().add(group);
			}		
	}
	
	private static HBox generateLight(boolean on,int light,int i) 
	{
		
		Group root = new Group();
		HBox box = new HBox(15);
		box.setPrefWidth(400);
		box.setAlignment(Pos.CENTER_LEFT);
		box.setPadding(new Insets(0,0,0,15));
		box.setBackground(new Background(new BackgroundFill((i%2==0) ? Color.rgb(215, 215, 215) : Color.rgb(235, 235, 235), CornerRadii.EMPTY, Insets.EMPTY)));
		
		RadialGradient rd = new RadialGradient(
				0,
				0,
				0.5,
				0.5,
				1.0,
				true,
				CycleMethod.NO_CYCLE,
				new Stop(0.0,Color.YELLOW),new Stop(0.7,Color.LIGHTYELLOW));
		
		Circle circleOuter = new Circle(70);
		circleOuter.setStroke(Color.YELLOW);
		circleOuter.setOpacity((on) ? 0.8 : 0.0);
		circleOuter.setFill(rd);
		
		Circle innerCircle = new Circle(50);
		innerCircle.setFill((on) ? Color.YELLOW : Color.GREY);
		innerCircle.setOpacity(0.5);
		innerCircle.setStroke((on) ? Color.BLACK : Color.GREY);
		innerCircle.setStrokeWidth(4);
		
		
	    Bloom bloom = new Bloom(1.0);
	    GaussianBlur blur = new GaussianBlur();
	    blur.setInput(bloom);
	    
	    if(!on)
	    	innerCircle.setEffect(blur);
	    
	    circleOuter.setEffect(blur);
		
		
		
		Label label = new Label(String.valueOf(light));
		label.setFont(Font.font("Arial", FontWeight.BOLD, 32));

		root.getChildren().addAll(innerCircle, circleOuter);
		box.getChildren().addAll(root, label);
	    
	    return box;
	}
}
