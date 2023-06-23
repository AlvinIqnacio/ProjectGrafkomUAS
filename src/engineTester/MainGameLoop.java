package engineTester;

import java.util.*;

import guis.GuiTexture;
import guis.GuiRenderer;
import entities.Player;
import models.RawModel;
import models.TexturedModel;


import objConverter.ModelData;
import objConverter.OBJFileLoader;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
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
		Random random = new Random();

		ModelData polyTreeData = OBJFileLoader.loadOBJ("lowPolyTree");
		ModelData treeData = OBJFileLoader.loadOBJ("pine");
		ModelData playerData = OBJFileLoader.loadOBJ("person");
		ModelData lampData = OBJFileLoader.loadOBJ("lamp");



		RawModel tree1Model = loader.loadToVAOTemp(
				polyTreeData.getVertices(),
				polyTreeData.getTextureCoords(),
				polyTreeData.getNormals(),
				polyTreeData.getIndices(),
				polyTreeData.getVerticesModel(),
				polyTreeData.getIndicesModel());

		RawModel treeModel = loader.loadToVAOTemp(
				treeData.getVertices(),
				treeData.getTextureCoords(),
				treeData.getNormals(),
				treeData.getIndices(),
				treeData.getVerticesModel(),
				treeData.getIndicesModel());

		RawModel personModel = loader.loadToVAOTemp(
				playerData.getVertices(),
				playerData.getTextureCoords(),
				playerData.getNormals(),
				playerData.getIndices(),
				playerData.getVerticesModel(),
				playerData.getIndicesModel());

		RawModel lampModel = loader.loadToVAOTemp(
				lampData.getVertices(),
				lampData.getTextureCoords(),
				lampData.getNormals(),
				lampData.getIndices(),
				lampData.getVerticesModel(),
				lampData.getIndicesModel());


		ModelTexture fernTextureAtlas = new ModelTexture(loader.loadTexture("fern"));
		fernTextureAtlas.setNumberOfRows(2);


		TexturedModel tree = new TexturedModel(treeModel,new ModelTexture(loader.loadTexture("pine")));
		TexturedModel tree1 = new TexturedModel(tree1Model,new ModelTexture(loader.loadTexture("lowPolyTree")));
		TexturedModel lamp = new TexturedModel(lampModel, new ModelTexture(loader.loadTexture("lamp")));
		TexturedModel person = new TexturedModel(personModel,new ModelTexture(loader.loadTexture("playerTexture")));

		//no collision
		TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("grassModel",loader),
				new ModelTexture(loader.loadTexture("grassTexture")));
		TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("fern",loader),
				fernTextureAtlas);


		TerainTexture backgroundTexture = new TerainTexture(loader.loadTexture("grassy2"));
		TerainTexture rTexture = new TerainTexture(loader.loadTexture("mud"));
		TerainTexture gTexture = new TerainTexture(loader.loadTexture("pinkFlowers"));
		TerainTexture bTexture = new TerainTexture(loader.loadTexture("path2"));

		TerainTexturePack texturePack = new TerainTexturePack(backgroundTexture,rTexture,gTexture,bTexture);
		TerainTexturePack texturePack2 = new TerainTexturePack(gTexture,rTexture,rTexture,rTexture);
		TerainTexture blendMap = new TerainTexture(loader.loadTexture("blendMap"));

		Terrain terrain = new Terrain(0,-1,loader,texturePack, blendMap, "heightmap");
		Terrain terrain2 = new Terrain(-1,-1,loader,texturePack2,blendMap,"heightmap");

		grass.getTexture().setHasTranparacy(true);
		grass.getTexture().setUseFakeLighting(true);
		fern.getTexture().setHasTranparacy(true);
		fern.getTexture().setUseFakeLighting(true);
		lamp.getTexture().setUseFakeLighting(true);

		
		List<Entity> entities = new ArrayList<>();
		List<Entity> obstacle = new ArrayList<>();


		List<Light> lights = new ArrayList<>();
		lights.add(new Light(new Vector3f(-10000,2000,5),new Vector3f(1.0f,1.0f,1.0f)));
		lights.add(new Light(new Vector3f(185,terrain.getHeightOfTerain(185,-293)+13f,-293),new Vector3f(2,0,0),new Vector3f(1,0.01f,0.002f)));
		lights.add(new Light(new Vector3f(370,terrain.getHeightOfTerain(370,-300)+13f,-300),new Vector3f(0,2,2),new Vector3f(1,0.01f,0.002f)));
		lights.add(new Light(new Vector3f(293,terrain.getHeightOfTerain(293,-305)+13f,-305),new Vector3f(2,2,0),new Vector3f(1,0.01f,0.002f)));

		entities.add(new Entity(lamp,new Vector3f(185,terrain.getHeightOfTerain(185,-293),-293),0,0,0,1));
		entities.add(new Entity(lamp,new Vector3f(370,terrain.getHeightOfTerain(370,-300),-300),0,0,0,1));
		entities.add(new Entity(lamp,new Vector3f(293,terrain.getHeightOfTerain(293,-305),-305),0,0,0,1));


		Player player = new Player(person, new Vector3f(100,0,-50), 0,0,0,1);

		entities.add(new Entity(tree, new Vector3f(10,0,-20), 0f,0f,0f,5f));


		Camera camera = new Camera(player);
		MasterRenderer renderer = new MasterRenderer(loader,camera);

		for(int i=0;i<100;i++){
			entities.add(new Entity(tree, randomPlacementEntity(terrain,terrain2), 0,0,0,2));
			entities.add(new Entity(tree1, randomPlacementEntity(terrain,terrain2), 0,0,0,1f));

//			entities.add(new Entity(grass, randomPlacementEntity(terrain,terrain2), 0,0,0,1));
//			entities.add(new Entity(fern,random.nextInt(4) , randomPlacementEntity(terrain,terrain2), 0,0,0,0.6f));
		}
		//gui
		List<GuiTexture> guis = new ArrayList<GuiTexture>();
		GuiTexture gui = new GuiTexture(loader.loadTexture("socuwan"), new Vector2f(0.0f,0.8f), new Vector2f(0.25f,0.25f));
//		guis.add(gui);

		GuiTexture shadowMap = new GuiTexture(renderer.getShadowMapTexture(), new Vector2f(0.5f,0.5f),new Vector2f(0.5f,0.5f));
//		guis.add(shadowMap);


		GuiRenderer guiRenderer = new GuiRenderer(loader);
		obstacle = new ArrayList<>(entities);
		entities.add(player);

		int count =0;
		List<Entity> temp = new ArrayList<>();
		while(!Display.isCloseRequested()){
			if (count%300==0) {
				temp = new ArrayList<>();
				for (Entity entity : obstacle) {

					float _playerPosx = player.getPosition().x;
					float _playerPosz = player.getPosition().z;
					float _entityPosx = entity.getPosition().x;
					float _entityPosz = entity.getPosition().z;

					if (_playerPosx+100>_entityPosx && _playerPosx-100<_entityPosx){
						if (_playerPosz+100>_entityPosz && _playerPosz-100<_entityPosz){
							temp.add(entity);
						}
					}
				}
				System.out.println(temp.size());
			}

			count++;

			if (player.getPosition().x>0){
				camera.move(terrain,temp);
				player.move(terrain,temp);
			}else {
				camera.move(terrain2,temp);
				player.move(terrain2,temp);
			}


//			System.out.println(player.getPosition().x +" "+ player.getPosition().y+ " " +player.getPosition().z);

			renderer.renderShadowMap(entities,lights.get(0));
			renderer.processEntity(player);
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);

			for(Entity entity:entities){
				renderer.processEntity(entity);
			}
			renderer.render(lights, camera);
			guiRenderer.render(guis);
			DisplayManager.updateDisplay();
		}

		guiRenderer.cleanUp();
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

	private static Vector3f randomPlacementEntity(Terrain terrain, Terrain terrain2){
		Random random = new Random();
		float x = random.nextFloat()*1600 -800;
		float z = random.nextFloat() * -800;
		float y;
		if (x>0){
			y = terrain.getHeightOfTerain(x,z);
		}else {
			y = terrain2.getHeightOfTerain(x,z);
		}
		return new Vector3f(x,y,z);
	}

}
