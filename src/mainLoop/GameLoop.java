package mainLoop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import particles.ParticleMaster;
import renderEngine.DisplayManaging;
import renderEngine.Loading3DModels;
import renderEngine.MasterRender;
import renderEngine.OBJLoader;
import renderEngine.RenderEngine;
import shaders.StaticShader;
import textures.ModelTexture;

public class GameLoop {
	
	public static void main(String[] args){
		
		DisplayManaging.createDisplay();
		Light light = new Light(new Vector3f(0,0,100), new Vector3f(1,1,1));
		Camera camera = new Camera();
		
		Loading3DModels loader = new Loading3DModels();
		ParticleMaster particles = new ParticleMaster(loader, "selected_model", "selected_texture", 10,1);
		MasterRender renderer = new MasterRender();
		
		/*
		 * Purpose: Loops though until the user attempts to close the window
		 */
		while(!Display.isCloseRequested()){
			particles.runParticles(renderer);
			renderer.render(light, camera);
			DisplayManaging.updateDisplay();
		}
		
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManaging.closeDisplay();
		
	}
}
