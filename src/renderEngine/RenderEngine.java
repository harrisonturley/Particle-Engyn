package renderEngine;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import shaders.StaticShader;
import textures.ModelTexture;
import toolbox.MathCalculations;

public class RenderEngine {
	
	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000f;
	
	private Matrix4f projectionMatrix;
	private StaticShader shader;
	
	private static float reds = 0f;
	private static float greens = 0f;
	private static float blues = 0f;
	private static float alpha = 1f;
	
	
	/*
	 * Purpose: To create a render engine
	 * Parameters: shader - The static shader to be used, allowing for preparation of the render engine
	 */
	public RenderEngine(StaticShader shader){
		this.shader = shader;
		//Stops the faces facing away from the camera from rendering
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	
	/*
	 * Purpose: To prepare the render engine for a new frame
	 */
	public void prepare(){
		//Testing which triangles are in front of each other, and render in the correct order.
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
		//sets background color!
		GL11.glClearColor(reds, greens, blues, alpha);
	}
	
	
	/*
	 * Purpose: To render a new frame
	 * Parameters: entities - A map of all current entities in the scene
	 */
	public void render(Map<TexturedModel, List<Entity>> entities){
		
		//Loops through each textured model type in the hashmap
		for(TexturedModel model: entities.keySet()){
			prepareTexturedModel(model);
			//Gets the batch of entities of a specific model from the hashmap?
			List<Entity> batch = entities.get(model);
			
			for (Entity entity:batch){
				prepareInstance(entity);
				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);	
			}
			unbindTexturedModel();
		}
	}
	
	
	/*
	 * Purpose: To prepare a textured model for rendering
	 * Parameters: model - The textured model to be rendered
	 */
	private void prepareTexturedModel(TexturedModel model){
		RawModel rawModel = model.getRawModel();
		GL30.glBindVertexArray(rawModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		
		//All texture necessities (need to be done for each textured model):
		ModelTexture texture = model.getTexture();
		shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
		//Storing the texture in one of the textureBanks
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());
	}
	
	
	/*
	 * Purpose: To unbind the textured model after it has been used for rendering
	 */
	private void unbindTexturedModel(){
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}
	
	
	/*
	 * Purpose: Prepares each instance/entity
	 * Parameters: entity - The entity to be prepared
	 */
	private void prepareInstance(Entity entity){
		Matrix4f transformationMatrix = MathCalculations.createTransformationMatrix(entity.getPosition(), 
				entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
		//loading transformation matrix to shader:
		shader.loadTransformationMatrix(transformationMatrix);
	}
	
	
	/*
	 * Purpose: To create a projection matrix for the shader program
	 */
	private void createProjectionMatrix(){
		//TRY TO LEARN
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) ((1f/Math.tan(Math.toRadians(FOV/2f)))*aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;
		
		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE+NEAR_PLANE)/frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2*NEAR_PLANE*FAR_PLANE)/frustum_length);
		projectionMatrix.m33 = 0;
	}

	
	//////////////////////////////////////////////////

	public static float getReds() {
		return reds;
	}


	public static void setReds(float reds) {
		RenderEngine.reds = reds;
	}


	public static float getGreens() {
		return greens;
	}


	public static void setGreens(float greens) {
		RenderEngine.greens = greens;
	}


	public static float getBlues() {
		return blues;
	}


	public static void setBlues(float blues) {
		RenderEngine.blues = blues;
	}


	public static float getAlpha() {
		return alpha;
	}


	public static void setAlpha(float alpha) {
		RenderEngine.alpha = alpha;
	}
	
	
	
}
