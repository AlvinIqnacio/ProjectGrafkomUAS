package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.RawModel;
import models.TexturedModel;


import objConverter.ModelData;
import objConverter.OBJFileLoader;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import entities.Camera;
import entities.Entity;
import entities.Light;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();

		ModelData polyTreeData = OBJFileLoader.loadOBJ("lowPolyTree");
		ModelData treeData = OBJFileLoader.loadOBJ("tree");


		RawModel tree1Model = loader.loadToVAO(
				polyTreeData.getVertices(),
				polyTreeData.getTextureCoords(),
				polyTreeData.getNormals(),
				polyTreeData.getIndices());

		RawModel treeModel = loader.loadToVAO(
				treeData.getVertices(),
				treeData.getTextureCoords(),
				treeData.getNormals(),
				treeData.getIndices());


		TexturedModel tree = new TexturedModel(treeModel,new ModelTexture(loader.loadTexture("tree")));
		TexturedModel tree1 = new TexturedModel(tree1Model,new ModelTexture(loader.loadTexture("lowPolyTree")));

		TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("grassModel",loader),
				new ModelTexture(loader.loadTexture("grassTexture")));
		TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("fern",loader),
				new ModelTexture(loader.loadTexture("fern")));
		grass.getTexture().setHasTranparacy(true);
		grass.getTexture().setUseFakeLighting(true);
		fern.getTexture().setHasTranparacy(true);
		fern.getTexture().setUseFakeLighting(true);

		
		List<Entity> entities = new ArrayList<Entity>();
		Random random = new Random();
		for(int i=0;i<200;i++){
			entities.add(new Entity(tree, new Vector3f(random.nextFloat()*800 - 800,0,random.nextFloat() * -600),0,0,0,5));
			entities.add(new Entity(tree1, new Vector3f(random.nextFloat()*800 - 400,0,random.nextFloat() * -600),0,0,0,0.5f));

			entities.add(new Entity(grass, new Vector3f(random.nextFloat()*800 - 400,0,random.nextFloat() * -600),0,0,0,1));
			entities.add(new Entity(fern, new Vector3f(random.nextFloat()*800 - 400,0,random.nextFloat() * -600),0,0,0,0.6f));

		}
		
		Light light = new Light(new Vector3f(20000,20000,2000),new Vector3f(1,1,1));
		
		Terrain terrain = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain2 = new Terrain(-1,-1,loader,new ModelTexture(loader.loadTexture("grass")));

		Camera camera = new Camera();	
		MasterRenderer renderer = new MasterRenderer();
		
		while(!Display.isCloseRequested()){
			camera.move();
			
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);

			for(Entity entity:entities){
				renderer.processEntity(entity);
			}
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
