package engine.shaders.basic;

import engine.core.ResourceLoader;
import engine.main.RenderingEngine;
import engine.scenegraph.GameObject;
import engine.scenegraph.components.Material;
import engine.shaders.Shader;

public class RGBA extends Shader{

	private static RGBA instance = null;
	
	public static RGBA getInstance() 
	{
		if(instance == null) 
		{
			instance = new RGBA();
		}
		return instance;
	}
		
	protected RGBA()
	{
		super();

		addVertexShader(ResourceLoader.loadShader("shaders/basic/rgba/Vertex.glsl"));
		addFragmentShader(ResourceLoader.loadShader("shaders/basic/rgba/Fragment.glsl"));
		compileShader();
			
		addUniform("modelViewProjectionMatrix");
		addUniform("worldMatrix");
		addUniform("color");
		addUniform("clipplane");
	}
		
	public void updateUniforms(GameObject object)
	{
		setUniform("modelViewProjectionMatrix", object.getTransform().getModelViewProjectionMatrix());
		setUniform("worldMatrix", object.getTransform().getWorldMatrix());
		setUniform("clipplane", RenderingEngine.getClipplane());
		setUniform("color", ((Material) object.getComponent("Material")).getColor());
	}
}