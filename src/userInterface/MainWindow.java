package userInterface;

import common.Common;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import logic.Light;

public class MainWindow {
	//TO-DO
	/*
	 * All done!
	 */
	private static int threshold;
	public static void mainWindow(AnchorPane anchor) 
	{
		
		/*interface stuff*/
		
		//left side
		
		//this is a separator between lights and rest of interface
		Separator seperateLeft = new Separator();
		AnchorPane.setLeftAnchor(seperateLeft, 399.0);
		seperateLeft.setOrientation(Orientation.VERTICAL);
		AnchorPane.setBottomAnchor(seperateLeft, 0.0);
		AnchorPane.setTopAnchor(seperateLeft, 0.0);
		anchor.getChildren().add(seperateLeft);
		
		//parent node for scroll
		VBox rootForLights = new VBox();
		rootForLights.setPrefWidth(400);
		anchor.getChildren().add(rootForLights);
		AnchorPane.setLeftAnchor(rootForLights, 1.0);
		AnchorPane.setBottomAnchor(rootForLights, 0.0);
		AnchorPane.setTopAnchor(rootForLights, -1.0);
		
		//scroll functionality
		ScrollPane scroll = new ScrollPane();
		scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		scroll.setVmax(1);
		scroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scroll.setMinHeight(0);
		
		//child of scroll
		VBox vLeft= new VBox();
		scroll.setContent(vLeft);
		rootForLights.getChildren().add(scroll);
		
		//middle
		
		//parent node of textfield,label in middle section
		VBox vMidOuter = new VBox(25);
		
		/*Random Field with button*/
		
		//pane for textfield and label for random and button for generating
		VBox vMidInnerRandom = new VBox(5);
		vMidInnerRandom.setPrefHeight(500);
		
		Label randomLightsLabel = new Label("Number of Lights: ");
		TextField randomLightsField = new TextField();
		randomLightsField.setPromptText("(max 100 for visuals)");
		randomLightsField.setPrefWidth(250);
		
		//button that generates lights
		Button randomLightsBtn = Common.createBtn("Generate",Color.WHITE , 20, 120, 20);
		VBox.setMargin(randomLightsBtn, new Insets(15,0,0,10));
		Common.fancyBtn(randomLightsBtn);
		
		vMidInnerRandom.getChildren().addAll(randomLightsLabel,randomLightsField,randomLightsBtn);
		vMidOuter.getChildren().add(vMidInnerRandom);
		anchor.getChildren().add(vMidOuter);
		AnchorPane.setLeftAnchor(vMidOuter, 435.0);
		AnchorPane.setTopAnchor(vMidOuter, 200.0);
		
		// "random lights" title
		Label midTitleLabel = new Label("Random\nLights");
		midTitleLabel.setFont(Font.font("Showcard Gothic",FontWeight.NORMAL,36));		
		midTitleLabel.setTextAlignment(TextAlignment.CENTER);
		AnchorPane.setTopAnchor(midTitleLabel, 50.0);
		AnchorPane.setLeftAnchor(midTitleLabel, 510.0);
		anchor.getChildren().add(midTitleLabel);
		/*Random Field with button*/
		
		//separator between middle and right
		Separator seperateRight = new Separator();
		AnchorPane.setLeftAnchor(seperateRight, 800.0);
		seperateRight.setOrientation(Orientation.VERTICAL);
		AnchorPane.setBottomAnchor(seperateRight, 0.0);
		AnchorPane.setTopAnchor(seperateRight, 0.0);
		anchor.getChildren().add(seperateRight);
		
		/* Stats */
		
		//parent node of scroll
		StackPane stkStatOuter = new StackPane();
		anchor.getChildren().add(stkStatOuter);
		stkStatOuter.setMinHeight(250);
		stkStatOuter.setMaxHeight(250);
		stkStatOuter.setMinWidth(700);
		AnchorPane.setRightAnchor(stkStatOuter, 20.0);
		AnchorPane.setLeftAnchor(stkStatOuter, 830.0);
		AnchorPane.setBottomAnchor(stkStatOuter, 20.0);
		
		//scoll functionality for summary
		ScrollPane scrollStat = new ScrollPane();
		scrollStat.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollStat.setVbarPolicy(ScrollBarPolicy.NEVER);
		scrollStat.setVmax(1);
		scrollStat.setHmax(1);
		stkStatOuter.getChildren().add(scrollStat);
		
		//labels that can be seen in summary
		Label powerLabel = new Label();
		powerLabel.setFont(Font.font("Arial",24));
		Label lightsLabel = new Label();
		lightsLabel.setFont(Font.font("Arial",24));
		Label wantedLightsLabel = new Label();
		wantedLightsLabel.setFont(Font.font("Arial",24));
		Label numberOfWantedLabel = new Label();
		numberOfWantedLabel.setFont(Font.font("Arial",24));
		
		//child node of scroll
		VBox vStatInner = new VBox(20);
		vStatInner.setPadding(new Insets(50,20,20,20));
		scrollStat.setContent(vStatInner);
		vStatInner.getChildren().addAll(powerLabel,lightsLabel,wantedLightsLabel,numberOfWantedLabel);
		/* Stats */
		
		/* Light Sequence (not random) */
		
		//this list shows the lights index entered by user
		ListView<String> list = new ListView<String>();
		list.setStyle("-fx-font-size:24.0;");
		anchor.getChildren().add(list);
		
		AnchorPane.setLeftAnchor(list, 830.0);
		AnchorPane.setBottomAnchor(list, 300.0);
		AnchorPane.setTopAnchor(list, 50.0);
		list.setPrefSize(300, 400);
		
		//"Lights Entered" Title
		Label listLabel = new Label("Lights Entered");
		listLabel.setFont(Font.font("Showcard Gothic",FontWeight.NORMAL,36));
		AnchorPane.setTopAnchor(listLabel, 10.0);
		AnchorPane.setLeftAnchor(listLabel, 835.0);
		anchor.getChildren().add(listLabel);
		
		//pane to hold labelNumberOfLights and numberOfLightsField
		VBox vNumberOfLights = new VBox(5);
		
		Label labelNumberOfLights = new Label("Number of Lights:");
		
		TextField numberOfLightsField = new TextField();
		numberOfLightsField.setPromptText("Maximum is 100 for visuals");
		numberOfLightsField.setPrefWidth(300);
		
		Button startBtn = Common.createBtn("Start", Color.WHITE, 24, 140,30);
		Common.fancyBtn(startBtn);
		AnchorPane.setLeftAnchor(startBtn, 1170.0);
		AnchorPane.setTopAnchor(startBtn, 170.0);
		
		vNumberOfLights.getChildren().addAll(labelNumberOfLights,numberOfLightsField);
		anchor.getChildren().addAll(startBtn,vNumberOfLights);
		AnchorPane.setLeftAnchor(vNumberOfLights, 1170.0);
		AnchorPane.setTopAnchor(vNumberOfLights, 100.0);
		/*number of lights*/
		
		//this pane hold the text field which the user uses to enter light index
		VBox vLightsEntered = new VBox(5);
		vLightsEntered.setVisible(false);
		
		Label lightsEnteredLabel = new Label("Light Index (from 1 to n):");
		
		TextField lightsEnteredField = new TextField();
		lightsEnteredField.setPromptText("After entering a number, press enter");
		lightsEnteredField.setPrefWidth(300);
		
		Label warnLabel = new Label();
		warnLabel.setTextFill(Color.RED);
		warnLabel.setVisible(false);
		
		//buttont to add number to list
		Button addBtn = Common.createBtn("Add", Color.WHITE, 24, 140, 30);
		Common.fancyBtn(addBtn);
		VBox.setMargin(addBtn, new Insets(10,0,0,0));
		
		vLightsEntered.getChildren().addAll(lightsEnteredLabel,lightsEnteredField,addBtn,warnLabel);
		anchor.getChildren().add(vLightsEntered);
		AnchorPane.setLeftAnchor(vLightsEntered, 1170.0);
		AnchorPane.setTopAnchor(vLightsEntered, 250.0);
		
		/* Light Sequence (not random) */
		
		//button to clear list of indices
		Button clearBtn = Common.createBtn("Clear",Color.WHITE,24, 140, 30);
		clearBtn.setTooltip(new Tooltip("Clear list of lights"));
		Common.fancyBtn(clearBtn);
		anchor.getChildren().add(clearBtn);
		AnchorPane.setBottomAnchor(clearBtn, 310.0);
		AnchorPane.setLeftAnchor(clearBtn, 1170.0);
		
		//button to generate table 
		Button generateTableBtn = Common.createBtn("Table", Color.WHITE, 24, 140, 30);
		generateTableBtn.setTooltip(new Tooltip("Show table used\nfor lights"));
		Common.fancyBtn(generateTableBtn);
		anchor.getChildren().add(generateTableBtn);
		AnchorPane.setBottomAnchor(generateTableBtn, 310.0);
		AnchorPane.setLeftAnchor(generateTableBtn, 1330.0);
		
		/*interface stuff*/
		
		/*functionality*/
		
		//for random textfield
		randomLightsField.setOnAction(e -> {
			try {
				
				vLightsEntered.setVisible(false);
				list.getItems().clear();
				int[] array = new int[Integer.parseInt(randomLightsField.getText())];
				Light.generateLight(array, vLeft, vStatInner, true);

			} catch (Exception o) {
			}
		});
		
		//for random button
		randomLightsBtn.setOnAction(e -> {
			try {
				
				vLightsEntered.setVisible(false);
				list.getItems().clear();
				int[] array = new int[Integer.parseInt(randomLightsField.getText())];
				Light.generateLight(array, vLeft, vStatInner, true);
				
			} catch (Exception o) {
			}
		});

		//number of lights the user wants
		numberOfLightsField.setOnAction(e ->
		{
			try {
				threshold = Integer.parseInt(numberOfLightsField.getText());
				vLightsEntered.setVisible(true);
				list.getItems().clear();
				warnLabel.setVisible(false);
			}
			catch(Exception o) {}
		});
		
		// used to take number of lights the user wants
		startBtn.setOnAction(e ->{
			try {
				threshold = Integer.parseInt(numberOfLightsField.getText());
				vLightsEntered.setVisible(true);
				list.getItems().clear();
				warnLabel.setVisible(false);
			}
			catch(Exception o) {}
		});
		
		
		//this field takes the sequence of lights from the user
		lightsEnteredField.setOnAction(e ->
		{
			try 
			{
				int number = Integer.parseInt(lightsEnteredField.getText());
				
				//check that the index of light entered isn't greater than the threshold user wanted
				//check that index of lights isn't less than or equal to zero
				//check for duplicates
				if (!(number > threshold || number <= 0 || list.getItems().indexOf(lightsEnteredField.getText()) >= 0)) {
					
					warnLabel.setVisible(false);
					
					//string used to know if user wants to edit or add to list
					String editString = list.getSelectionModel().getSelectedItem();
					
					
					if(editString == null) //add 
						list.getItems().add(lightsEnteredField.getText());

					else //edit
					{
						int indexOfChange = list.getSelectionModel().getSelectedIndex();
						list.getItems().set(indexOfChange, lightsEnteredField.getText());
					}
					
					//text field
					lightsEnteredField.clear();
					
					//if number of lights wanted equals threshold, generate lights
					if (list.getItems().size() == threshold) {
						vLightsEntered.setVisible(false);
						int[] lights = new int[threshold + 1];

						for (int i = 1; i < threshold + 1; i++) {
							lights[i] = Integer.parseInt(list.getItems().get(i - 1));
						}
						Light.generateLight(lights, vLeft, vStatInner, false);
					}
				}
				else {
					warnLabel.setVisible(true);
					warnLabel.setText("No duplicates!*\nnumber must be less than or equal to: "+threshold+"*");
				}

			}
			catch(Exception o) {}
		});
		
		//this field takes the sequence of lights from the user
		addBtn.setOnAction(e ->{
			try 
			{
				int number = Integer.parseInt(lightsEnteredField.getText());
				
				//check that the index of light entered isn't greater than the threshold user wanted
				//check that index of lights isn't less than or equal to zero
				//check for duplicates
				if (!(number > threshold || number <= 0 || list.getItems().indexOf(lightsEnteredField.getText()) >= 0)) {
					
					warnLabel.setVisible(false);
					
					//string used to know if user wants to edit or add to list
					String editString = list.getSelectionModel().getSelectedItem();
					
					
					if(editString == null) //add 
						list.getItems().add(lightsEnteredField.getText());

					else //edit
					{
						int indexOfChange = list.getSelectionModel().getSelectedIndex();
						list.getItems().set(indexOfChange, lightsEnteredField.getText());
					}
					
					//text field
					lightsEnteredField.clear();
					
					//if number of lights wanted equals threshold, generate lights
					if (list.getItems().size() == threshold) {
						vLightsEntered.setVisible(false);
						int[] lights = new int[threshold + 1];

						for (int i = 1; i < threshold + 1; i++) {
							lights[i] = Integer.parseInt(list.getItems().get(i - 1));
						}
						Light.generateLight(lights, vLeft, vStatInner, false);
					}
				}
				else {
					warnLabel.setVisible(true);
					warnLabel.setText("No duplicates!*\nnumber must be less than or equal to: "+threshold+"*");
				}

			}
			catch(Exception o) {}
		});
		
		// clear list and textfields
		clearBtn.setOnAction(e ->{
			list.getItems().clear();
			vLightsEntered.setVisible(false);
			numberOfLightsField.clear();
			lightsEnteredField.clear();
			randomLightsField.clear();
			warnLabel.setVisible(false);
		});
		
		// simply, generates the table (how bueatiful)
		generateTableBtn.setOnAction(e->TableWindow.tableWindow());
		
		// clears selection of lights list
		anchor.setOnMouseClicked(e -> list.getSelectionModel().clearSelection());
		/*functionality*/
	
	}
}
