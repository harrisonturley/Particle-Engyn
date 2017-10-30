package entities;

import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;

public class Entity {
	//essentially, an instance of a textured model
	
	private TexturedModel model;
	private Vector3f position;
	private float rotX,rotY,rotZ;
	private float dX, dY, dZ;
	private float dRotX, dRotY, dRotZ;
	private float scale;
	
	/*
	 * Purpose: To create a new entity
	 * Parameters: model - The model to be used to create the entity
	 * position - The position of the entity
	 * rotX - The rotation of the entity in the X direction
	 * rotY - The rotation of the entity in the Y direction
	 * rotZ - The rotation of the entity in the Z direction
	 * dRotX - The velocity of the rotation in the X direction
	 * dRotY - The velocity of the rotation in the Y direction
	 * dRotZ - The velocity of the rotation in the Z direction
	 * scale - The scale of the entity
	 * dX - The velocity of the entity in the X direction
	 * dY - The velocity of the entity in the Y direction
	 * dZ - The velocity of the entity in the Z direction
	 */
	public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float dRotX, float dRotY, float dRotZ, float scale, float dX, float dY, float dZ) {
		super();
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.dRotX = dRotX;
		this.dRotY = dRotY;
		this.dRotZ = dRotZ;
		this.scale = scale;
		this.dX = dX;
		this.dY = dY;
		this.dZ = dZ;
	}

	/*
	 * Purpose: To increase the position of the entity 
	 */
	public void increasePosition(){
		this.position.x += dX;
		this.position.y += dY;
		this.position.z += dZ;
	}
	
	
	/*
	 * Purpose: To increase the velocity of the entity
	 */
	public void increaseVelocity(float ddX, float ddY, float ddZ){
		dX += ddX;
		dY += ddY;
		dZ += ddZ;
	}
	
	
	/*
	 * Purpose: To increase the rotation of the entity
	 */
	public void increaseRotation(){
		this.rotX += dRotX;
		this.rotY += dRotY;
		this.rotZ += dRotZ;
	}
	
	
	//////////////////////////////////////////////////
	
	
	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public float getdX() {
		return dX;
	}

	public void setdX(float dX) {
		this.dX = dX;
	}

	public float getdY() {
		return dY;
	}

	public void setdY(float dY) {
		this.dY = dY;
	}

	public float getdZ() {
		return dZ;
	}

	public void setdZ(float dZ) {
		this.dZ = dZ;
	}

	public float getdRotX() {
		return dRotX;
	}

	public void setdRotX(float dRotX) {
		this.dRotX = dRotX;
	}

	public float getdRotY() {
		return dRotY;
	}

	public void setdRotY(float dRotY) {
		this.dRotY = dRotY;
	}

	public float getdRotZ() {
		return dRotZ;
	}

	public void setdRotZ(float dRotZ) {
		this.dRotZ = dRotZ;
	}
	
	
}
