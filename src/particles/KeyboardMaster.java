package particles;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import entities.Entity;
import renderEngine.RenderEngine;

public class KeyboardMaster {

	private static final float SMALL_INCREMENT = 0.02f;
	private static final float LARGE_INCREMENT = 0.25f;
	
	private static final float COLOUR_CHANGE = 0.01f;

	private static int currentSelection = 5;

	
	/*
	 * Purpose: To check keyboard-actuated events that must occur every frame that the button is held down
	 * Parameters: entity - The current entity, for changing the individual characteristics (such as velocity, rotation etc...)
	 */
	public static void multiEvents(Entity entity) {

		// Constant event increase/decrease

		// X velocity increase
		if (Keyboard.isKeyDown(Keyboard.KEY_M) && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))) {
			entity.setdX(entity.getdX() + getLargeIncrement());
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_M)) {
			entity.setdX(entity.getdX() + getSmallIncrement());
		}
		
		// X velocity decrease
		if (Keyboard.isKeyDown(Keyboard.KEY_N) && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))) {
			entity.setdX(entity.getdX() - getLargeIncrement());
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_N)) {
			entity.setdX(entity.getdX() - getSmallIncrement());
		}


		// Y velocity increase
		if (Keyboard.isKeyDown(Keyboard.KEY_PERIOD) && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))) {
			entity.setdY(entity.getdY() + getLargeIncrement());
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_PERIOD)) {
			entity.setdY(entity.getdY() + getSmallIncrement());
		}

		// Y velocity decrease
		if (Keyboard.isKeyDown(Keyboard.KEY_COMMA) && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))) {
			entity.setdY(entity.getdY() - getLargeIncrement());
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_COMMA)) {
			entity.setdY(entity.getdY() - getSmallIncrement());
		}

		
		// Z velocity increase
		if (Keyboard.isKeyDown(Keyboard.KEY_L) && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))) {
			entity.setdZ(entity.getdZ() + getLargeIncrement());
		}
		
		else if (Keyboard.isKeyDown(Keyboard.KEY_L)) {
			entity.setdZ(entity.getdZ() + getSmallIncrement());

		}

		// Z velocity decrease
		if (Keyboard.isKeyDown(Keyboard.KEY_K) && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))) {
			entity.setdZ(entity.getdZ() - getLargeIncrement());
		}
		
		else if (Keyboard.isKeyDown(Keyboard.KEY_K)) {
			entity.setdZ(entity.getdZ() - getSmallIncrement());
		}


	}

	
	/*
	 * Purpose: To check if the user is attempting to add more or less models to the scene
	 */
	public static void modelAddition() {

		// Adding new models, to increase particle density
		if (Keyboard.isKeyDown(Keyboard.KEY_H)) {

			ParticleMaster.addModel();

		} else if (Keyboard.isKeyDown(Keyboard.KEY_G)) {

			ParticleMaster.removeModel();

		}
	}
	
	
	/*
	 * Purpose: To test whether the user is requesting the background colour to be changed
	 */
	public static void backgroundColour(){
		/*
		 * Background Colours
		 */
		
		//Reds increment
		if (Keyboard.isKeyDown(Keyboard.KEY_A) && (RenderEngine.getReds() < 1)){
			RenderEngine.setReds(RenderEngine.getReds() + COLOUR_CHANGE);
		}
		
		//Reds decrement
		if (Keyboard.isKeyDown(Keyboard.KEY_Z) && (RenderEngine.getReds() > 0)){
			RenderEngine.setReds(RenderEngine.getReds() - COLOUR_CHANGE);
		}
		
		
		//Greens increment
		if (Keyboard.isKeyDown(Keyboard.KEY_S) && (RenderEngine.getGreens() < 1)){
			RenderEngine.setGreens(RenderEngine.getGreens() + COLOUR_CHANGE);
		}
		
		//Greens decrement
		if (Keyboard.isKeyDown(Keyboard.KEY_X) && (RenderEngine.getGreens() > 0)){
			RenderEngine.setGreens(RenderEngine.getGreens() - COLOUR_CHANGE);
		}
		
		
		//Blues increment
		if(Keyboard.isKeyDown(Keyboard.KEY_D) && (RenderEngine.getBlues() < 1)){
			RenderEngine.setBlues(RenderEngine.getBlues() + COLOUR_CHANGE);
		}
		
		//Blues decrement
		if (Keyboard.isKeyDown(Keyboard.KEY_C) && (RenderEngine.getBlues() > 0)){
			RenderEngine.setBlues(RenderEngine.getBlues() - COLOUR_CHANGE);
		}
	}

	
	/*
	 * Purpose: To search for keyboard inputs on events that will be only actuated once until the key is released 
	 * Return Value: currentSelection (int) - The selection from the user
	 */
	public static int singleEvents() {
		
		while (Keyboard.next()){
			
			if (Keyboard.getEventKeyState()) {
				
				if (Keyboard.getEventKey() == Keyboard.KEY_I) {
					System.out.println("\nKeybinds: ");
					System.out.println("X VELOCITY\nM: X velocity small increase\nM + Shift: X velocity large increase");
					System.out.println("N: X velocity small decrease\nN + Shift: X velocity large decrease");
					System.out.println("\nY VELOCITY\nPeriod: Y velocity small increase\nPeriod + Shift: Y velocity large increase");
					System.out.println("Comma: Y velocity small decrease\nComma + Shift: Y velocity large decrease");
					System.out.println("\nZ VELOCITY\nL: Z velocity small incease\nL + Shift: Z velocity large increase");
					System.out.println("K: Z velocity small decrease\nK + Shift: Z velocity large decrease");
					System.out.println("\nReset: R");
					System.out.println("\nMODEL ADDITIONS\nH: Add models\nG: Remove models");
					System.out.println("\nPRESET VALUES\n1: Snow Falling\n2: Random\n3: Lightspeed\n4: Pause");
				}
				
				if (Keyboard.getEventKey() == Keyboard.KEY_1) {
					setCurrentSelection(1);
				}

				// random preset
				if (Keyboard.getEventKey() == Keyboard.KEY_2) {
					setCurrentSelection(2);
				}

				// Lightspeed preset
				if (Keyboard.getEventKey() == Keyboard.KEY_3) {
					setCurrentSelection(3);
				}

				// Pause preset
				if (Keyboard.getEventKey() == Keyboard.KEY_4) {
					setCurrentSelection(4);
				}
				
				// Showcase preset
				if (Keyboard.getEventKey() == Keyboard.KEY_5){
					setCurrentSelection(5);
				}
				
				
				//Reset Key
				if (Keyboard.getEventKey() == Keyboard.KEY_R){
					ParticleMaster.setMode(ParticleMaster.getCurrentSelection());
				}
			}
		}
		
		return getCurrentSelection();

	}
	
	
	/*
	 * Purpose: To find whether the user is requesting to pause the engine
	 * Return Value: pauseRequest (boolean) - Whether pause was requested or not
	 */
	public static boolean isPauseRequested(){
		boolean pauseRequest = false;
		if (Keyboard.isKeyDown(Keyboard.KEY_4)){
			pauseRequest = true;
		}
		
		return pauseRequest;
	}
	
	/////////////////////////////////////////////////

	public static float getSmallIncrement() {
		return SMALL_INCREMENT;
	}

	public static float getLargeIncrement() {
		return LARGE_INCREMENT;
	}

	public static int getCurrentSelection() {
		return currentSelection;
	}

	public static void setCurrentSelection(int currentSelection) {
		KeyboardMaster.currentSelection = currentSelection;
	}

}
