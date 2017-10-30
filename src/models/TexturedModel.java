package models;

import textures.ModelTexture;

public class TexturedModel {
	private RawModel rawModel;
	private ModelTexture texture;
	
	//Textures must be png, and must be 2^n x 2^n in size.
	
	public TexturedModel(RawModel model, ModelTexture texture){
		this.rawModel = model;
		this.texture = texture;
	}


	public RawModel getRawModel() {
		return rawModel;
	}


	public ModelTexture getTexture() {
		return texture;
	}
	
}
