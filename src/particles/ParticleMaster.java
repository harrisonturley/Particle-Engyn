package particles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.Loading3DModels;
import renderEngine.MasterRender;
import renderEngine.OBJLoader;
import textures.ModelTexture;

public class ParticleMaster {

	private static int numberOfModels = 250;
	private static int currentSelection = 5;
	private static int inputSelection;

	private static Random random = new Random();
	private static TexturedModel texturedModel;
	
	private static boolean showcaseLastSelected = true;

	private static List<Entity> particleList = new ArrayList<Entity>();

	/////////////////////////////////////////////////////////////////////////////

	/*
	 * Purpose: Creating the particle master, which will be used to create and control models Parameters: 
	 * loader - Loads the model 
	 * modelFile - The filename for the file, which is found in the res folder 
	 * texturerFile - The filename for the texture, which is found in the res folder
	 * shineDamper - How close the camera needs to be to witness the shine
	 * reflectivity - How much shine is given to the model
	 */
	public ParticleMaster(Loading3DModels loader, String modelFile, String textureFile, float shineDamper, float reflectivity) {

		RawModel model = OBJLoader.loadObjModel(modelFile, loader);
		texturedModel = new TexturedModel(model, new ModelTexture(loader.loadTexture(textureFile)));
		ModelTexture texture = texturedModel.getTexture();
		
		texture.setShineDamper(shineDamper);
		texture.setReflectivity(reflectivity);

		System.out.println("\nWelcome to the Particle Engyn!\nCreated by: Harrison Turley\nDate Completed: July 20, 2017\nFor information, press: I");

		particleList.add(PresetValues.showcase(random, texturedModel));

	}

	/*
	 * Purpose: Runs the particle system, calling any required methods
	 * Parameters: renderer - Renders the models
	 */
	public void runParticles(MasterRender renderer) {

		int listSize = particleList.size();

		for (int index = 0; index < listSize; index++) {
			particleList.get(index).increasePosition();
			particleList.get(index).increaseRotation();
			renderer.processEntity(particleList.get(index));
			modeRules(index, particleList.get(index));
			KeyboardMaster.multiEvents(particleList.get(index));
		}
		
		KeyboardMaster.backgroundColour();
		KeyboardMaster.modelAddition();
		
		inputSelection = KeyboardMaster.singleEvents();

		if (inputSelection != currentSelection) {
			setMode(inputSelection);
			currentSelection = inputSelection;
		} 
	}

	/*
	 * Purpose: Sets the mode to a new choice, changing all particles to this
	 * new setting Parameters: newSelection - The new chosen setting, numbered
	 * 1-5
	 */
	public static void setMode(int newSelection) {

		if (currentSelection != 5 && currentSelection != 4) {
			numberOfModels = particleList.size();
		}

		switch (newSelection) {
		// Snow falling
		case 1:

			particleList.clear();

			for (int i = 0; i < numberOfModels; i++) {
				particleList.add(PresetValues.snowFalling(random, texturedModel));
			}

			break;
		// Random
		case 2:

			particleList.clear();

			for (int i = 0; i < numberOfModels; i++) {
				particleList.add(PresetValues.random(random, texturedModel));
			}

			break;
		// Lightspeed
		case 3:

			particleList.clear();

			for (int i = 0; i < numberOfModels; i++) {
				particleList.add(PresetValues.lightSpeed(random, texturedModel));
			}

			break;
		// Pause
		case 4:
			
			if (currentSelection != 5) {
				for (int i = 0; i < numberOfModels; i++) {
					PresetValues.pause(particleList.get(i), showcaseLastSelected);
				}
			} else {
				PresetValues.pause(particleList.get(0), showcaseLastSelected);
			}

			break;
		// Showcase
		case 5:
			particleList.clear();
			particleList.add(PresetValues.showcase(random, texturedModel));
			break;

		default:
			System.out.println("Error changing modes!");
			break;
		}
	}

	
	/*
	 * Purpose: Puts bounds and other rules on the particles to ensure the
	 * continuous operation of the engine Parameters: mode - The current setting
	 * index - Current index in the particleList, for removing specific models
	 * entity - Current entity, allowing for access to entity-specific
	 * information
	 */
	public static void modeRules(int index, Entity entity) {

		// Enclosing box to ensure particle aren't lost:

		// X enclosing box
		if (entity.getPosition().x <= -100) {
			entity.setPosition(new Vector3f(entity.getPosition().x + 200, entity.getPosition().y, entity.getPosition().z));
		} else if (entity.getPosition().x >= 100) {
			entity.setPosition(new Vector3f(entity.getPosition().x - 200, entity.getPosition().y, entity.getPosition().z));
		}

		// Y enclosing box
		if (entity.getPosition().y <= -100) {
			entity.setPosition(new Vector3f(entity.getPosition().x, entity.getPosition().y + 200, entity.getPosition().z));
		} else if (entity.getPosition().y >= 100) {
			entity.setPosition(new Vector3f(entity.getPosition().x, entity.getPosition().y - 200, entity.getPosition().z));
		}

		// Z enclosing box
		if (entity.getPosition().z <= -1000) {
			entity.setPosition(new Vector3f(entity.getPosition().x, entity.getPosition().y, entity.getPosition().z + 1000));
		} else if (entity.getPosition().z >= 1) {
			entity.setPosition(new Vector3f(entity.getPosition().x, entity.getPosition().y, entity.getPosition().z - 1000));
		}

		switch (currentSelection) {
		// snow falling rules
		case 1:

			showcaseLastSelected = false;
			
			if (entity.getPosition().y < -100) {
				entity.setPosition(new Vector3f(entity.getPosition().x, 75f, entity.getPosition().z));
			}

			break;
		// random rules
		case 2:
			
			showcaseLastSelected = false;

			if (entity.getPosition().y <= -150 || entity.getPosition().y >= 150 || entity.getPosition().x <= -100 || entity.getPosition().x >= 100 || entity.getPosition().z > 0
					|| entity.getPosition().z < -500) {

				replaceModel(index);

			}

			break;
		// lightspeed rules
		case 3:
			
			showcaseLastSelected = false;

			if (entity.getPosition().z > 0) {
				entity.setPosition(new Vector3f(entity.getPosition().x, entity.getPosition().y, -900));
			}

			else if ((entity.getPosition().x < 5 && entity.getPosition().x > -5) && (entity.getPosition().y < 5 && entity.getPosition().y > -5)) {
				replaceModel(index);
			}

			break;
		// pause rules
		case 4:
			
			if (KeyboardMaster.isPauseRequested() && showcaseLastSelected){
				PresetValues.pause(entity, showcaseLastSelected);
			}
			
			break;
		// Showcase rules
		case 5:
			
			showcaseLastSelected = true;

			break;

		default:
			System.out.println("Error in the rules section!");
			break;
		}
	}

	
	/*
	 * Purpose: Adds a model to the particleList, based on the current selection
	 */
	public static void addModel() {
		switch (currentSelection) {

		case 1:
			particleList.add(PresetValues.snowFalling(random, texturedModel));
			break;

		case 2:
			particleList.add(PresetValues.random(random, texturedModel));
			break;

		case 3:
			particleList.add(PresetValues.lightSpeed(random, texturedModel));
			break;

		case 4:
			System.out.println("Engine is paused!");
			break;

		case 5:
			particleList.add(PresetValues.showcase(random, texturedModel));
			break;

		default:
			System.out.println("Error adding a new model!");
			break;

		}
	}

	
	/*
	 * Purpose: Removes the last model in the particleList
	 */
	public static void removeModel() {
		if (currentSelection == 4) {
			System.out.println("Engine is paused!");
		}

		else if (currentSelection == 5) {
			System.out.println("Cannot remove, showcase is active!");
		}

		else if (particleList.size() > 0) {
			particleList.remove(particleList.size() - 1);
		}
	}

	
	/*
	 * Purpose: Removing a specific model in the particleList and replacing it
	 * instantly Parameters: index - Current position in the particleList
	 */
	public static void replaceModel(int index) {
		particleList.remove(index);
		addModel();
	}

	
	/*
	 * Purpose: Prints out instructions for the user to use the program
	 */
	public static void printInstructions() {
		System.out.println("To see current setting, press (INSERT KEY)");
		System.out.println("To see all keybindings, press (INSERT KEY)");
		System.out.println("To see current FPS, press (INSERT KEY)");
		System.out.println("To see the current number of models in the scene, press (INSERT KEY)");
	}

	///////////////////////////////////////////////

	public static int getNumberOfModels() {
		return numberOfModels;
	}

	public static void setNumberOfModels(int numberOfModels) {
		ParticleMaster.numberOfModels = numberOfModels;
	}

	public static int getCurrentSelection() {
		return currentSelection;
	}

	public static void setCurrentSelection(int currentSelection) {
		ParticleMaster.currentSelection = currentSelection;
	}

	public static int getInputSelection() {
		return inputSelection;
	}

	public static void setInputSelection(int inputSelection) {
		ParticleMaster.inputSelection = inputSelection;
	}

}
