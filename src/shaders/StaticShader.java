package shaders;

import org.lwjgl.util.vector.Matrix4f;

import entities.Camera;
import entities.Light;
import toolbox.MathCalculations;

public class StaticShader extends ShaderProgram{
	
	private static final String VERTEX_FILE = "/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE = "/shaders/fragmentShader.txt";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_lightPosition;
	private int location_lightColour;
	private int location_shineDamper;
	private int location_reflectivity;


	/*
	 * Purpose: To construct a new static shader, sending the vertex/fragment shader path to the superclass
	 */
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	
	/*
	 * Purpose: Binds various attributes to different slots in the VAO
	 */
	protected void bindAttributes() {
		//Binding attribute 0 because that's where stored vertex position, connecting to variable position in vertex shader

		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
		super.bindAttribute(2, "normal");
		
	}

	
	/*
	 * Purpose: Gets all uniform variable locations from the GLSL code
	 */
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_lightPosition = super.getUniformLocation("lightPosition");
		location_lightColour = super.getUniformLocation("lightColour");
		location_shineDamper = super.getUniformLocation("shineDamper");
		location_reflectivity = super.getUniformLocation("reflectivity");
		
	}
	
	
	/*
	 * Purpose: Load the shine variables to the GLSL shader code
	 * Parameters: damper - The damper value to be loaded
	 * reflectivity - The reflectivity to be loaded
	 */
	public void loadShineVariables(float damper, float reflectivity){
		super.loadFloat(location_shineDamper, damper);
		super.loadFloat(location_reflectivity, reflectivity);
	}
	
	
	/*
	 * Purpose: Loads a transformation matrix to the GLSL shader code
	 * Parameters: matrix - The matrix to be loaded
	 */
	public void loadTransformationMatrix(Matrix4f matrix){
		super.loadMatrix(location_transformationMatrix,matrix);
		
	}
	
	
	/*
	 * Purpose: Loads a light to the GLSL shader code
	 * Parameters: light - The light to be loaded
	 */
	public void loadLight(Light light){
		super.loadVector(location_lightPosition, light.getPosition());
		super.loadVector(location_lightColour, light.getColour());
	}
	
	
	/*
	 * Purpose: Loads a projection matrix to the GLSL shader code
	 * Parameters: projection - The projection matrix to be loaded
	 */
	public void loadProjectionMatrix(Matrix4f projection){
		super.loadMatrix(location_projectionMatrix, projection);
	}

	
	/*
	 * Purpose: Loads a view matrix to the GLSL shader code
	 * Parameters: camera - The camera to be converted into a view matrix, then loaded
	 */
	public void loadViewMatrix(Camera camera){
		//creating the view matrix
		Matrix4f viewMatrix = MathCalculations.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
}
