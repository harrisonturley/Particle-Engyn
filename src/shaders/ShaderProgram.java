package shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public abstract class ShaderProgram {
	
	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;
	
	//using 4x4 matrix
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	
	/*
	 * Purpose: Constructs the shader program
	 * Parameters: vertexFile - GLSL vertex shader file name in the res folder
	 * fragmentFile - GLSL fragment shader file name in the res folder
	 */
	public ShaderProgram(String vertexFile, String fragmentFile){
		vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
		programID = GL20.glCreateProgram();
		//Attaching vertex/fragment shader to program
		GL20.glAttachShader(programID, vertexShaderID);
		GL20.glAttachShader(programID, fragmentShaderID);
		//binds inputs of shader programs to one of the attributes of the VAO
		bindAttributes();
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
		getAllUniformLocations();
	}
	
	
	/*
	 * Purpose: Abstract method for finding all uniform variables in GLSL code
	 */
	protected abstract void getAllUniformLocations();
	
	
	/*
	 * Purpose: Finds the location of the uniform variable in the GLSL code of a specific program
	 * Parameters: uniformName - The name of the uniform variable in the GLSL code
	 * Return Value: The location of the uniform variable
	 */
	protected int getUniformLocation(String uniformName){
		return GL20.glGetUniformLocation(programID, uniformName);
	}
	
	
	/*
	 * Purpose: Starts the use of a shader
	 */
	public void start(){
		GL20.glUseProgram(programID);
	}
	
	
	/*
	 * Purpose: Stops the use of a shader
	 */
	public void stop(){
		GL20.glUseProgram(0);
	}
	
	
	/*
	 * Purpose: Cleans up all shader data
	 */
	public void cleanUp(){
		stop();
		GL20.glDetachShader(programID, vertexShaderID);
		GL20.glDetachShader(programID, fragmentShaderID);
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteShader(fragmentShaderID);
		GL20.glDeleteProgram(programID);
	}
	
	
	/*
	 * Purpose: Binds an attribute to a specific shader program, such that it can be used in the shader code
	 * Parameters: attribute - The number corresponding to the attribute (location in the VAO list)
	 * variableName - Name of the variable in the GLSL code to bind the attribute to
	 */
	protected void bindAttribute(int attribute, String variableName){
		//For binding the attribute, communicate from outside to shader?
		GL20.glBindAttribLocation(programID, attribute, variableName);
	}
	
	
	/*
	 * Purpose: Abstract method for binding attributes to variables in shader code
	 */
	protected abstract void bindAttributes();
	
	
	/*
	 * Purpose: Loads a float to a uniform variable in GLSL code
	 * Parameters: location - Location of the uniform variable in the GLSL code
	 * value - The float to be loaded
	 */
	protected void loadFloat (int location, float value){
		GL20.glUniform1f(location, value);
	}
	
	
	/*
	 * Purpose: Loads a vector to a uniform variable in GLSL code
	 * Parameters: location - Location of the uniform variable in the GLSL code
	 * vector - The vector to be loaded
	 */
	protected void loadVector(int location, Vector3f vector){
		GL20.glUniform3f(location, vector.x, vector.y, vector.z);
	}
	
	
	/*
	 * Purpose: Loads a boolean to a uniform variable in GLSL code
	 * Parameters: location - Location of the uniform variable in the GLSL code
	 * value - The boolean to be loaded
	 */
	protected void loadBoolean(int location, boolean value){
		float toLoad = 0;
		if (value){
			toLoad = 1;
		}
		GL20.glUniform1f(location, toLoad);
	}
	
	
	/*
	 * Purpose: Loads a matrix to a uniform variable in GLSL code
	 * Parameters: location - Location of the matrix in the GLSL code
	 * matrix - The matrix to be loaded
	 */
	protected void loadMatrix (int location, Matrix4f matrix){
		matrix.store(matrixBuffer);
		matrixBuffer.flip();
		GL20.glUniformMatrix4(location, false, matrixBuffer);
	}
	
	
	/*
	 * Purpose: Loads a shader, reading the GLSL code
	 * Parameters: file - The file name of the shader to be loaded
	 * type - The type of shader to be loaded, such as vertex, fragment, etc...
	 * Return Value: shaderID (int) - The ID of the shader
	 */
	private static int loadShader(String file, int type){
		
		StringBuilder shaderSource = new StringBuilder();
		try{
			InputStream in = Class.class.getResourceAsStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null){
				shaderSource.append(line).append("\n");
			}
			reader.close();
		} catch(IOException e){
			System.err.println("Could not read file!");
			e.printStackTrace();
			System.exit(-1);
		}
		
		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID,  shaderSource);
		GL20.glCompileShader(shaderID);
		if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE){
			System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
			System.err.println("Could not compile shader.");
			System.exit(-1);
		}
		
		return shaderID;
	}
}
