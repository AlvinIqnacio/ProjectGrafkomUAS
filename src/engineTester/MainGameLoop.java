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
		ModelData houseData = OBJFileLoader.loadOBJ("Socuwan Scene/Houses/home1");
		ModelData windwillData = OBJFileLoader.loadOBJ("Socuwan Scene/Windmill/model");
		ModelData rockData = OBJFileLoader.loadOBJ("Socuwan Scene/Bushes/model");



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

		RawModel houseModel = loader.loadToVAOTemp(
				houseData.getVertices(),
				houseData.getTextureCoords(),
				houseData.getNormals(),
				houseData.getIndices(),
				houseData.getVerticesModel(),
				houseData.getIndicesModel());

		RawModel windmillModel = loader.loadToVAOTemp(
				windwillData.getVertices(),
				windwillData.getTextureCoords(),
				windwillData.getNormals(),
				windwillData.getIndices(),
				windwillData.getVerticesModel(),
				windwillData.getIndicesModel());

		RawModel rockModel = loader.loadToVAOTemp(
				rockData.getVertices(),
				rockData.getTextureCoords(),
				rockData.getNormals(),
				rockData.getIndices(),
				rockData.getVerticesModel(),
				rockData.getIndicesModel());


		ModelTexture fernTextureAtlas = new ModelTexture(loader.loadTexture("fern"));
		fernTextureAtlas.setNumberOfRows(2);


		TexturedModel tree = new TexturedModel(treeModel,new ModelTexture(loader.loadTexture("pine")));
		TexturedModel tree1 = new TexturedModel(tree1Model,new ModelTexture(loader.loadTexture("lowPolyTree")));
		TexturedModel lamp = new TexturedModel(lampModel, new ModelTexture(loader.loadTexture("lamp")));
		TexturedModel person = new TexturedModel(personModel,new ModelTexture(loader.loadTexture("playerTexture")));
		TexturedModel house = new TexturedModel(houseModel,new ModelTexture(loader.loadTexture("Socuwan Scene/Houses/diffuse")));
		TexturedModel windmill = new TexturedModel(windmillModel,new ModelTexture(loader.loadTexture("Socuwan Scene/Windmill/diffuse")));
		TexturedModel rock = new TexturedModel(rockModel,new ModelTexture(loader.loadTexture("Socuwan Scene/Bushes/diffuse")));

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


		Player player = new Player(person, new Vector3f(405,3.64f,-382.92123f), 0,0,0,1);

		entities.add(new Entity(tree, new Vector3f(10,0,-20), 0f,0f,0f,5f));
//		entities.add(new Entity(house, new Vector3f(400f,3.647f,-384), 0f,0f,0f,10f));
//		entities.add(new Entity(herbstall, new Vector3f(340f,5.0f,-395f), 0f,0f,0f,10f));

		entities.add(new Entity(house,new Vector3f(370.95074f,terrain.getHeightOfTerain(370.95074f,-420.24857f),-420.24857f),0,0,0,1));
		entities.add(new Entity(windmill,new Vector3f(380.3f,terrain.getHeightOfTerain(380.3f,-387f),-387f),0,0,0,10));
		entities.add(new Entity(rock,new Vector3f(387.3f,terrain.getHeightOfTerain(387.3f,-395.2f),-395.2f),0,0,0,10));
//		entities.add(new Entity(rock,new Vector3f(213.15736f,terrain.getHeightOfTerain(213.15736f,-641.9919f),-641.9919f),0,0,0,10));

		entities.add(new Entity(tree,new Vector3f(213.15736f,terrain.getHeightOfTerain(213.15736f,-641.9919f),-641.9919f),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(306.46277f,terrain.getHeightOfTerain(306.46277f,-585.40564f),-585.40564f),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(219.85002f,terrain.getHeightOfTerain(219.85002f,-554.98584f),-554.98584f),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(145.94952f,terrain.getHeightOfTerain(145.94952f,-606.1742f),-606.1742f),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(137.78445f,terrain.getHeightOfTerain(137.78445f,-542.7215f),-542.7215f),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(129.4428f,terrain.getHeightOfTerain(129.4428f,-479.5613f),-479.5613f),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(28.632921f,terrain.getHeightOfTerain(28.632921f,-530.201f),-530.201f),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(80.15097f,terrain.getHeightOfTerain(80.15097f,-503.5026f),-503.5026f),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(418.51733f,terrain.getHeightOfTerain(418.51733f,-296.52744f),-296.52744f),0,0,0,5));

		entities.add(new Entity(tree1,new Vector3f(376.82504f,terrain.getHeightOfTerain(376.82504f,-628.5623f),-628.5623f),0,0,0,3));
		entities.add(new Entity(tree1,new Vector3f(322.97224f,terrain.getHeightOfTerain(376.82504f,-628.5623f),-698.4444f),0,0,0,3));
		entities.add(new Entity(tree1,new Vector3f(72.11883f,terrain.getHeightOfTerain(72.11883f,-422.20193f),-422.20193f),0,0,0,3));
		entities.add(new Entity(tree1,new Vector3f(18.279293f,terrain.getHeightOfTerain(18.279293f,-616.31055f),-616.31055f),0,0,0,3));
		entities.add(new Entity(tree1,new Vector3f(98.08309f,terrain.getHeightOfTerain(98.08309f,-687.21875f),-687.21875f),0,0,0,3));
		entities.add(new Entity(tree1,new Vector3f(347.22693f,terrain.getHeightOfTerain(347.22693f,-229.64406f),-229.64406f),0,0,0,3));
		entities.add(new Entity(tree1,new Vector3f(347.22693f,terrain.getHeightOfTerain(347.22693f,-229.64406f),-229.64406f),0,0,0,3));

		lights.add(new Light(new Vector3f(274.75735f,terrain.getHeightOfTerain(274.75735f,-553.3781f)+25f,-553.3781f),new Vector3f(0,2,0),new Vector3f(1,0.01f,0.002f)));
		lights.add(new Light(new Vector3f(293.78223f,terrain.getHeightOfTerain(293.78223f,-202.7974f)+25f,-202.7974f),new Vector3f(2,0,0),new Vector3f(2,0.01f,0.002f)));
		lights.add(new Light(new Vector3f(312.42526f,terrain.getHeightOfTerain(312.42526f,-428.33685f)+25f,-428.33685f),new Vector3f(0,0,2),new Vector3f(2,0.01f,0.002f)));
		entities.add(new Entity(lamp,new Vector3f(274.75735f,terrain.getHeightOfTerain(274.75735f,-553.3781f),-553.3781f),0,0,0,1.5f));
		entities.add(new Entity(lamp,new Vector3f(293.78223f,terrain.getHeightOfTerain(293.78223f,-202.7974f),-202.7974f),0,0,0,1.5f));
		entities.add(new Entity(lamp,new Vector3f(312.42526f,terrain.getHeightOfTerain(312.42526f,-428.33685f),-428.33685f),0,0,0,1.5f));


		Camera camera = new Camera(player);
		MasterRenderer renderer = new MasterRenderer(loader,camera);

//		for(int i=0;i<100;i++){
//			entities.add(new Entity(tree, randomPlacementEntity(terrain,terrain2), 0,0,0,2));
//			entities.add(new Entity(tree1, randomPlacementEntity(terrain,terrain2), 0,0,0,1f));
//
////			entities.add(new Entity(grass, randomPlacementEntity(terrain,terrain2), 0,0,0,1));
////			entities.add(new Entity(fern,random.nextInt(4) , randomPlacementEntity(terrain,terrain2), 0,0,0,0.6f));
//		}
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


			System.out.println(player.getPosition().x +" "+ player.getPosition().y+ " " +player.getPosition().z);

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
