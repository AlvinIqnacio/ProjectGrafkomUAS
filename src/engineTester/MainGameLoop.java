package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entities.Player;
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
import textures.TerainTexture;
import textures.TerainTexturePack;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();

		ModelData polyTreeData = OBJFileLoader.loadOBJ("lowPolyTree");
		ModelData treeData = OBJFileLoader.loadOBJ("tree");
		ModelData playerData = OBJFileLoader.loadOBJ("person");



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

		RawModel personModel = loader.loadToVAO(
				playerData.getVertices(),
				playerData.getTextureCoords(),
				playerData.getNormals(),
				playerData.getIndices());

		TexturedModel tree = new TexturedModel(treeModel,new ModelTexture(loader.loadTexture("tree")));
		TexturedModel tree1 = new TexturedModel(tree1Model,new ModelTexture(loader.loadTexture("lowPolyTree")));

		TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("grassModel",loader),
				new ModelTexture(loader.loadTexture("grassTexture")));
		TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("fern",loader),
				new ModelTexture(loader.loadTexture("fern")));
		TexturedModel person = new TexturedModel(personModel,new ModelTexture(loader.loadTexture("playerTexture")));


		grass.getTexture().setHasTranparacy(true);
		grass.getTexture().setUseFakeLighting(true);
		fern.getTexture().setHasTranparacy(true);
		fern.getTexture().setUseFakeLighting(true);

		
		List<Entity> entities = new ArrayList<Entity>();
		Random random = new Random();
		for(int i=0;i<100;i++){
			entities.add(new Entity(tree, new Vector3f(random.nextFloat()*800,0,random.nextFloat() * -800),0,0,0,10));
			entities.add(new Entity(tree1, new Vector3f(random.nextFloat()*800 - 800,0,random.nextFloat() * -800),0,0,0,1f));

			entities.add(new Entity(grass, new Vector3f(random.nextFloat()*800 - 800,0,random.nextFloat() * -800),0,0,0,1));
			entities.add(new Entity(fern, new Vector3f(random.nextFloat()*800,0,random.nextFloat() * -800),0,0,0,0.6f));

		}
		
		Light light = new Light(new Vector3f(20000,20000,2000),new Vector3f(1,1,1));

		TerainTexture backgroundTexture = new TerainTexture(loader.loadTexture("grassy"));
		TerainTexture rTexture = new TerainTexture(loader.loadTexture("dirt"));
		TerainTexture gTexture = new TerainTexture(loader.loadTexture("pinkFlowers"));
		TerainTexture bTexture = new TerainTexture(loader.loadTexture("path"));


		TerainTexturePack texturePack = new TerainTexturePack(backgroundTexture,rTexture,gTexture,bTexture);
		TerainTexture blendMap = new TerainTexture(loader.loadTexture("blendMap"));

		Terrain terrain = new Terrain(0,-1,loader,texturePack, blendMap);
		Terrain terrain2 = new Terrain(-1,-1,loader,texturePack,blendMap);

		Player player = new Player(person, new Vector3f(0,0,-50), 0,0,0,1);

		Camera camera = new Camera(player);
		MasterRenderer renderer = new MasterRenderer();
		
		while(!Display.isCloseRequested()){
			camera.move();

			player.move();
			System.out.println(player.getPosition().x +" "+player.getPosition().z);

			renderer.processEntity(player);
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
