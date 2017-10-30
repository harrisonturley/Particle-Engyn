package renderEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.TexturedModel;
import shaders.StaticShader;

public class MasterRender {
	
	//New instance of the static shader class
	private StaticShader shader = new StaticShader();
	private RenderEngine renderer = new RenderEngine(shader);
	
	private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();
	
	
	/*
	 * Purpose: Renders the scene
	 * Parameters: sun - The light source for ambient lighting
	 * camera - The camera for allowing the user to view the scene
	 */
	public void render(Light sun, Camera camera){
		renderer.prepare();
		shader.start();
		shader.loadLight(sun);
		shader.loadViewMatrix(camera);
		renderer.render(entities);
		shader.stop();
		//Clears hashmap of entities
		entities.clear();
	}
	
	
	/*
	 * Purpose: Sorting entities into a hashmap
	 * Parameters: entity - The entity to be added
	 */
	public void processEntity(Entity entity){
		
		TexturedModel entityModel = entity.getModel();
		//Getting list that corresponds to that entity from the hashmap
		List<Entity> batch = entities.get(entityModel);
		
		if(batch != null){
			
			batch.add(entity);
			
		} else {
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entityModel, newBatch);
		}
	}
	
	
	/*
	 * Purpose: Cleaning up the shaders
	 */
	public void cleanUp(){
		shader.cleanUp();
	}
}
