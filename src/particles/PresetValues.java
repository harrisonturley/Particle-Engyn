package particles;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import models.TexturedModel;

public class PresetValues {

	// Randomness factors, set within the methods
	private static float x_constant;
	private static float y_constant;
	private static float z_constant;
	private static float rotX_constant;
	private static float rotY_constant;
	private static float rotZ_constant;
	private static float dRotX;
	private static float dRotY;
	private static float dRotZ;
	private static float dX;
	private static float dY;
	private static float dZ;

	/*
	 * Purpose: To create a new entity with preset values for snow falling
	 * Parameters: random - A random number generator, used to generate randomness in the models 
	 * texturedModel - The specific model used to create the entity
	 * Return Value: A new entity with snowFalling presets
	 */
	public static Entity snowFalling(Random random, TexturedModel texturedModel) {
		setX_constant(150f);
		setY_constant(50f);
		setZ_constant(-150f);
		setRotX_constant(180f);
		setRotY_constant(180f);
		setRotZ_constant(180f);
		setdRotX(1f);
		setdRotY(1f);
		setdRotZ(1f);
		setdX(0.0f);
		setdY(-0.25f);
		setdZ(0.0f);

		float x = random.nextFloat() * getX_constant() - getX_constant() / 2;
		float y = random.nextFloat() * getY_constant() + 50f;
		float z = random.nextFloat() * getZ_constant();
		float rotX = random.nextFloat() * getRotX_constant();
		float rotY = random.nextFloat() * getRotY_constant();
		float rotZ = random.nextFloat() * getRotZ_constant();
		float dRotX = random.nextFloat() * getdRotX();
		float dRotY = random.nextFloat() * getdRotY();
		float dRotZ = random.nextFloat() * getdRotZ();
		float dX = random.nextFloat() * getdX() - getdX() / 2;
		float dY = random.nextFloat() * getdY();
		float dZ = random.nextFloat() * getdZ() - getdZ() / 2;

		return new Entity(texturedModel, new Vector3f(x, y, z), rotX, rotY, rotZ, dRotX, dRotY, dRotZ, 1f, dX, dY, dZ);

	}

	/*
	 * Purpose: To create a new entity with preset values for random Parameters:
	 * random - A random number generator, used to generate randomness in the models 
	 * texturedModel - The specific model used to create the entity
	 * Return Value: A new entity with random presets
	 */
	public static Entity random(Random random, TexturedModel texturedModel) {
		setX_constant(150f);
		setY_constant(150f);
		setZ_constant(-300f);
		setRotX_constant(180f);
		setRotY_constant(180f);
		setRotZ_constant(180f);
		setdRotX(1f);
		setdRotY(1f);
		setdRotZ(1f);
		setdX(1f);
		setdY(1f);
		setdZ(1f);

		float x = random.nextFloat() * getX_constant() - getX_constant() / 2;
		float y = random.nextFloat() * getY_constant() - getY_constant() / 2;
		float z = random.nextFloat() * getZ_constant();
		float rotX = random.nextFloat() * getRotX_constant();
		float rotY = random.nextFloat() * getRotY_constant();
		float rotZ = random.nextFloat() * getRotZ_constant();
		float dRotX = random.nextFloat() * getdRotX();
		float dRotY = random.nextFloat() * getdRotY();
		float dRotZ = random.nextFloat() * getdRotZ();
		float dX = random.nextFloat() * getdX() - getdX() / 2;
		float dY = random.nextFloat() * getdY() - getdY() / 2;
		float dZ = random.nextFloat() * getdZ();

		return new Entity(texturedModel, new Vector3f(x, y, z), rotX, rotY, rotZ, dRotX, dRotY, dRotZ, 1f, dX, dY, dZ);
	}

	/*
	 * Purpose: To create a new entity with preset values for light speed
	 * Parameters: random - A random number generator, used to generate
	 * randomness in the models 
	 * texturedModel - The specific model used to create the entity
	 * Return Value: A new entity with lightSpeed presets
	 */
	public static Entity lightSpeed(Random random, TexturedModel texturedModel) {
		setX_constant(300f);
		setY_constant(300f);
		setZ_constant(-900f);
		setRotX_constant(180f);
		setRotY_constant(180f);
		setRotZ_constant(180f);
		setdRotX(1f);
		setdRotY(1f);
		setdRotZ(1f);
		setdX(0.0f);
		setdY(0.0f);
		setdZ(3f);

		float x = random.nextFloat() * getX_constant() - getX_constant() / 2;
		float y = random.nextFloat() * getY_constant() - getY_constant() / 2;
		float z = random.nextFloat() * getZ_constant();
		float rotX = random.nextFloat() * getRotX_constant();
		float rotY = random.nextFloat() * getRotY_constant();
		float rotZ = random.nextFloat() * getRotZ_constant();
		float dRotX = random.nextFloat() * getdRotX();
		float dRotY = random.nextFloat() * getdRotY();
		float dRotZ = random.nextFloat() * getdRotZ();
		float dX = random.nextFloat() * getdX() - getdX() / 2;
		float dY = random.nextFloat() * getdY() - getdY() / 2;
		float dZ = random.nextFloat() * getdZ() + 10f;

		return new Entity(texturedModel, new Vector3f(x, y, z), rotX, rotY, rotZ, dRotX, dRotY, dRotZ, 1f, dX, dY, dZ);
	}

	/*
	 * Purpose: To stop all motion in an existing entity 
	 * Parameters: entity - The current entity, for ceasing all of its motion
	 */
	public static void pause(Entity entity, boolean showcaseLastSelected) {

		if (showcaseLastSelected) {
			entity.setdX(0f);
			entity.setdY(0f);
			entity.setdZ(0f);
		} else {
			entity.setdRotX(0f);
			entity.setdRotY(0f);
			entity.setdRotZ(0f);
			entity.setdX(0f);
			entity.setdY(0f);
			entity.setdZ(0f);
		}
	}

	
	/*
	 * Purpose: To create a new entity with preset values for showcase
	 * Parameters: random - A random number generator, used to generate randomness in the models 
	 * texturedModel - The specific model used to create the entity
	 * Return Value: A new entity with showcase presets
	 */
	public static Entity showcase(Random random, TexturedModel texturedModel) {
		setX_constant(0f);
		setY_constant(-3f);
		setZ_constant(-30f);
		setRotX_constant(40f);
		setRotY_constant(0f);
		setRotZ_constant(0f);
		setdRotX(0.3f);
		setdRotY(0.5f);
		setdRotZ(0.2f);
		setdX(0.0f);
		setdY(0.0f);
		setdZ(0f);

		float x = getX_constant();
		float y = getY_constant();
		float z = getZ_constant();
		float rotX = getRotX_constant();
		float rotY = getRotY_constant();
		float rotZ = getRotZ_constant();
		float dRotX = getdRotX();
		float dRotY = getdRotY();
		float dRotZ = getdRotZ();
		float dX = getdX();
		float dY = getdY();
		float dZ = getdZ();

		return new Entity(texturedModel, new Vector3f(x, y, z), rotX, rotY, rotZ, dRotX, dRotY, dRotZ, 1f, dX, dY, dZ);
	}

	/////////////////////////////////////

	public static float getX_constant() {
		return x_constant;
	}

	public static float getY_constant() {
		return y_constant;
	}

	public static float getZ_constant() {
		return z_constant;
	}

	public static float getRotX_constant() {
		return rotX_constant;
	}

	public static void setRotX_constant(float rotX_constant) {
		PresetValues.rotX_constant = rotX_constant;
	}

	public static float getRotY_constant() {
		return rotY_constant;
	}

	public static void setRotY_constant(float rotY_constant) {
		PresetValues.rotY_constant = rotY_constant;
	}

	public static float getRotZ_constant() {
		return rotZ_constant;
	}

	public static void setRotZ_constant(float rotZ_constant) {
		PresetValues.rotZ_constant = rotZ_constant;
	}

	public static float getdRotX() {
		return dRotX;
	}

	public static void setdRotX(float dRotX) {
		PresetValues.dRotX = dRotX;
	}

	public static float getdRotY() {
		return dRotY;
	}

	public static void setdRotY(float dRotY) {
		PresetValues.dRotY = dRotY;
	}

	public static float getdRotZ() {
		return dRotZ;
	}

	public static void setdRotZ(float dRotZ) {
		PresetValues.dRotZ = dRotZ;
	}

	public static float getdX() {
		return dX;
	}

	public static void setdX(float dX) {
		PresetValues.dX = dX;
	}

	public static float getdY() {
		return dY;
	}

	public static void setdY(float dY) {
		PresetValues.dY = dY;
	}

	public static float getdZ() {
		return dZ;
	}

	public static void setdZ(float dZ) {
		PresetValues.dZ = dZ;
	}

	public static void setX_constant(float x_constant) {
		PresetValues.x_constant = x_constant;
	}

	public static void setY_constant(float y_constant) {
		PresetValues.y_constant = y_constant;
	}

	public static void setZ_constant(float z_constant) {
		PresetValues.z_constant = z_constant;
	}

}
