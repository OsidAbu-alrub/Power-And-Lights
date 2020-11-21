package logic;

import java.util.Random;

import domain.ARROWS;
import domain.Component;

public class LCS {

	/*
	 * this method generates a random sequence of lights and initiates both arrays
	 */
	public static Component generateRandomSequence(int numberOfSources) {

		if (numberOfSources <= 0)
			throw new ArrayIndexOutOfBoundsException("Number of sources must me at least one!");

		int[] power = new int[numberOfSources + 1];
		int[] lights = new int[numberOfSources + 1];

		for (int i = 0; i < power.length; i++) {
			power[i] = i;
			lights[i] = i;
		}

		// loop used for randomizing lights
		Random rnd = new Random();
		for (int i = lights.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);

			// if statement to make sure zero is always first
			if (index != 0) {
				int a = lights[index];
				lights[index] = lights[i];
				lights[i] = a;
			} else {
				i++;
			}
		}

		Component comp = tableFill(power, lights);

		return comp;

	}
	
	/*
	 * This method fills power table
	 * (used when user enters specific pattern of lights)
	 */
	public static Component generateLights(int[] lights) 
	{
		int[] power = new int[lights.length];
		
		for(int i = 0 ; i < power.length ; i++) {
			power[i] = i;
		}
		
		Component comp = tableFill(power,lights);
		
		return comp;
	}

	//utility method to fill the table with possiblities
	private static Component tableFill(int[] power, int[] lights) {

		// this has all the possible sets
		int[][] table = new int[lights.length][lights.length];
		
		// table of enums to denote our movements in table
		ARROWS[][] tableArrows = new ARROWS[lights.length][lights.length];
		
		//loop to build 2d table
		for (int i = 0; i < table.length; i++) {

			//iterating through columns
			for (int j = 0; j < table[i].length; j++) {

				//if we are at first row or column, fill with zero
				if (i == 0 || j == 0) {
					table[i][j] = 0;
					tableArrows[i][j] = ARROWS.None;
				}
				else if (power[j] == lights[i]) {//if power index matches light index
					table[i][j] += table[i - 1][j - 1] + 1;
					tableArrows[i][j] = ARROWS.Diagonal;
				}
				else {//if not match 
					table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
					tableArrows[i][j] = (table[i - 1][j] >= table[i][j - 1]) ? ARROWS.Up : ARROWS.Left;
				}

			}
		}
		
		//number of lights we want
		int wantedLights = table[lights.length - 1][power.length - 1];
		
		// array that contains the lights we want (their numbers)
		int[] wantedLightsArray = new int[wantedLights];

		int rows = lights.length - 1;
		int columns = power.length - 1;

		//this loop iterates through the 2d matrix we build above
		//to get the exact lights for our solutions
		while (wantedLights != 0) {
			
			//if element has diagonal arrow, then take it
			if(tableArrows[rows][columns] == ARROWS.Diagonal) {
				wantedLightsArray[wantedLights-1] = power[columns]; 
				
				tableArrows[rows][columns] = ARROWS.DiagonalTake;
				rows--;
				columns--;
				wantedLights--;
			}
			else if(tableArrows[rows][columns] == ARROWS.Up) {//else check if it goes up or left
				tableArrows[rows][columns] = ARROWS.UpTake;
				rows--;
			}
			else {
				tableArrows[rows][columns] = ARROWS.LeftTake;
				columns--;
			}

			}

		Component comp = new Component(power,lights,table,wantedLightsArray,tableArrows);
		return comp;
		}
		

	}

