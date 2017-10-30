package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManaging {
	
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private static final int FPS = 60;
	private static final String TITLE = "Particle Engyn V2";
	
	
	/*
	 * Purpose: Creates the display, implementing various settings for it
	 */
	public static void createDisplay(){
		
		ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle(TITLE);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
	}
	
	
	/*
	 * Purpose: Updates the display for the latest frame
	 */
	public static void updateDisplay(){
		//Sets display to run at a steady, predetermined FPS
		Display.sync(FPS);
		Display.update();
		
	}
	
	
	/*
	 * Purpose: Destroys the display for closing the application
	 */
	public static void closeDisplay(){
		Display.destroy();
	}
}
