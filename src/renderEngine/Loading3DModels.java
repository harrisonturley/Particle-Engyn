package renderEngine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import models.RawModel;

public class Loading3DModels {
	
	//For loading 3D models in memory by storing positional data in a VAO
	//Essentially, it activates the VAO, so then it knows where VBO data will go, direrctly to active, then deactivates
	
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> textures = new ArrayList<Integer>();
 	
	
	/*
	 * Purpose: Loads each provided parameter to different slots of the VAO, then returns the created raw model
	 * Parameters: positions - Array of the vertex positions
	 * textureCoords - Array of the texture coordinates
	 * normals - Array of the normals of the model
	 * indices - Array of the indices
	 */
	public RawModel loadToVAO(float[] positions, float[] textureCoords, float[] normals, int[] indices){
		
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0,3,positions);
		storeDataInAttributeList(1,2,textureCoords);
		storeDataInAttributeList(2,3,normals);
		unbindVAO();
		return new RawModel(vaoID, indices.length);
	
	}
	
	
	/*
	 * Purpose: To load a new texture
	 * Parameters: fileName - The file name of the texture to be loaded
	 * Return Value: textureID (int) - The ID of the texture
	 */
	public int loadTexture(String fileName){
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream("res/"+fileName+".png"));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		int textureID = texture.getTextureID();
		textures.add(textureID);
		return textureID;
	}
	
	
	/*
	 * Purpose: Deletes all VAO, VBO, and texture data
	 */
	public void cleanUp(){
		
		for(int vao:vaos){
			GL30.glDeleteVertexArrays(vao);
		}
		
		for (int vbo:vbos){
			GL15.glDeleteBuffers(vbo);
		}
		
		for (int texture:textures){
			GL11.glDeleteTextures(texture);
		}
	}
	
	
	/*
	 * Purpose: Creates a new VAO
	 * Return Value: vaoID - The ID of the VAO
	 */
	private int createVAO(){
		//Essentially, making a new vaoID, then activate it, then return it
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	
	
	/*
	 * Purpose: Stores data in a specific attribute of a VAO
	 * Parameters: attributeNumber - The specific slot which the data will be stored in
	 * coordinateSize - The dimension of the vector to be stored (ex: 2D vs 3D vectors)
	 * data - The data to be stored
	 */
	private void storeDataInAttributeList(int attributeNumber, int coordinateSize, float[] data){
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		//to do anything with a vbo, must bind it first
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		//static draw means we will never change it
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		//which list number, lenght of each vertex (x,y,z), float, norrmalized?, distance between verticies, offset?(at beginning of data = 0)
		GL20.glVertexAttribPointer(attributeNumber, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
		//unbinds 
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	
	/*
	 * Purpose: To create and bind the indices buffer
	 * Parameters: indices - The array of indices
	 */
	private void bindIndicesBuffer(int[] indices){
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	
	/*
	 * Purpose: To store the data provided (in this case of use, indices) in an int buffer
	 * Parameters: data - The data to be stored (array of indices)
	 * Return Value: buffer (IntBuffer) - The buffer for the data array
	 */
	private IntBuffer storeDataInIntBuffer(int[] data){
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	
	/*
	 * Purpose: Unbinds the currently bound VAO
	 */
	private void unbindVAO(){
		//unbinds currently bound vao
		GL30.glBindVertexArray(0);
	}
	
	
	/*
	 * Purpose: Stores an array of floats into a float buffer
	 * Parameters: data - The data to be stored
	 * Return Value: buffer (FloatBuffer) - The buffer for the data array
	 */
	private FloatBuffer storeDataInFloatBuffer(float[] data){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		//changes from writing to reading
		buffer.flip();
		return buffer;
	}
}
