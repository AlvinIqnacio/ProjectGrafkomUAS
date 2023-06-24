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

		// House 1
		ModelData houseData = OBJFileLoader.loadOBJ("Socuwan Scene/Houses/home1");
		ModelData housetData = OBJFileLoader.loadOBJ("Socuwan Scene/Houses/home1timur");
		ModelData housebData = OBJFileLoader.loadOBJ("Socuwan Scene/Houses/home1barat");
		ModelData houseuData = OBJFileLoader.loadOBJ("Socuwan Scene/Houses/home1utara");

		// House 2
		ModelData house2Data = OBJFileLoader.loadOBJ("Socuwan Scene/Houses/home2");
		ModelData house2tData = OBJFileLoader.loadOBJ("Socuwan Scene/Houses/home2timur");
		ModelData house2bData = OBJFileLoader.loadOBJ("Socuwan Scene/Houses/home2barat");
		ModelData house2uData = OBJFileLoader.loadOBJ("Socuwan Scene/Houses/home2utara");

		// Windmill
		ModelData windwillData = OBJFileLoader.loadOBJ("Socuwan Scene/Windmill/windmill");
		ModelData windwilltData = OBJFileLoader.loadOBJ("Socuwan Scene/Windmill/windmill_timur");
		ModelData windwillbData = OBJFileLoader.loadOBJ("Socuwan Scene/Windmill/windmill_barat");
		ModelData windwilluData = OBJFileLoader.loadOBJ("Socuwan Scene/Windmill/windmill_utara");

		// Herb Stall
		ModelData stallData = OBJFileLoader.loadOBJ("Socuwan Scene/Herb Stall/herbstall_");
		ModelData stall1Data = OBJFileLoader.loadOBJ("Socuwan Scene/Herb Stall/herbstall1");

		ModelData bridgeData = OBJFileLoader.loadOBJ("Socuwan Scene/Bridge/bridge");



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

		// House 1
		RawModel houseModel = loader.loadToVAOTemp(
				houseData.getVertices(),
				houseData.getTextureCoords(),
				houseData.getNormals(),
				houseData.getIndices(),
				houseData.getVerticesModel(),
				houseData.getIndicesModel());
		RawModel housetModel = loader.loadToVAOTemp(
				housetData.getVertices(),
				housetData.getTextureCoords(),
				housetData.getNormals(),
				housetData.getIndices(),
				housetData.getVerticesModel(),
				housetData.getIndicesModel());
		RawModel housebModel = loader.loadToVAOTemp(
				housebData.getVertices(),
				housebData.getTextureCoords(),
				housebData.getNormals(),
				housebData.getIndices(),
				housebData.getVerticesModel(),
				housebData.getIndicesModel());
		RawModel houseuModel = loader.loadToVAOTemp(
				houseuData.getVertices(),
				houseuData.getTextureCoords(),
				houseuData.getNormals(),
				houseuData.getIndices(),
				houseuData.getVerticesModel(),
				houseuData.getIndicesModel());

		// House 2
		RawModel house2Model = loader.loadToVAOTemp(
				house2Data.getVertices(),
				house2Data.getTextureCoords(),
				house2Data.getNormals(),
				house2Data.getIndices(),
				house2Data.getVerticesModel(),
				house2Data.getIndicesModel());
		RawModel house2tModel = loader.loadToVAOTemp(
				house2tData.getVertices(),
				house2tData.getTextureCoords(),
				house2tData.getNormals(),
				house2tData.getIndices(),
				house2tData.getVerticesModel(),
				house2tData.getIndicesModel());
		RawModel house2bModel = loader.loadToVAOTemp(
				house2bData.getVertices(),
				house2bData.getTextureCoords(),
				house2bData.getNormals(),
				house2bData.getIndices(),
				house2bData.getVerticesModel(),
				house2bData.getIndicesModel());
		RawModel house2uModel = loader.loadToVAOTemp(
				house2uData.getVertices(),
				house2uData.getTextureCoords(),
				house2uData.getNormals(),
				house2uData.getIndices(),
				house2uData.getVerticesModel(),
				house2uData.getIndicesModel());

		// Windmill
		RawModel windmillModel = loader.loadToVAOTemp(
				windwillData.getVertices(),
				windwillData.getTextureCoords(),
				windwillData.getNormals(),
				windwillData.getIndices(),
				windwillData.getVerticesModel(),
				windwillData.getIndicesModel());
		RawModel windmilltModel = loader.loadToVAOTemp(
				windwilltData.getVertices(),
				windwilltData.getTextureCoords(),
				windwilltData.getNormals(),
				windwilltData.getIndices(),
				windwilltData.getVerticesModel(),
				windwilltData.getIndicesModel());
		RawModel windmillbModel = loader.loadToVAOTemp(
				windwillbData.getVertices(),
				windwillbData.getTextureCoords(),
				windwillbData.getNormals(),
				windwillbData.getIndices(),
				windwillbData.getVerticesModel(),
				windwillbData.getIndicesModel());
		RawModel windmilluModel = loader.loadToVAOTemp(
				windwilluData.getVertices(),
				windwilluData.getTextureCoords(),
				windwilluData.getNormals(),
				windwilluData.getIndices(),
				windwilluData.getVerticesModel(),
				windwilluData.getIndicesModel());

		RawModel bridgeModel = loader.loadToVAOTemp(
				bridgeData.getVertices(),
				bridgeData.getTextureCoords(),
				bridgeData.getNormals(),
				bridgeData.getIndices(),
				bridgeData.getVerticesModel(),
				bridgeData.getIndicesModel());

		// Herb Stall
		RawModel stallModel = loader.loadToVAOTemp(
				stallData.getVertices(),
				stallData.getTextureCoords(),
				stallData.getNormals(),
				stallData.getIndices(),
				stallData.getVerticesModel(),
				stallData.getIndicesModel());
		RawModel stall1Model = loader.loadToVAOTemp(
				stall1Data.getVertices(),
				stall1Data.getTextureCoords(),
				stall1Data.getNormals(),
				stall1Data.getIndices(),
				stall1Data.getVerticesModel(),
				stall1Data.getIndicesModel());


		ModelTexture fernTextureAtlas = new ModelTexture(loader.loadTexture("fern"));
		fernTextureAtlas.setNumberOfRows(2);


		TexturedModel tree = new TexturedModel(treeModel,new ModelTexture(loader.loadTexture("pine")));
		TexturedModel tree1 = new TexturedModel(tree1Model,new ModelTexture(loader.loadTexture("lowPolyTree")));
		TexturedModel lamp = new TexturedModel(lampModel, new ModelTexture(loader.loadTexture("lamp")));
		TexturedModel person = new TexturedModel(personModel,new ModelTexture(loader.loadTexture("playerTexture")));

		// House 1
		TexturedModel house = new TexturedModel(houseModel,new ModelTexture(loader.loadTexture("Socuwan Scene/Houses/diffuse")));
		TexturedModel houset = new TexturedModel(housetModel,new ModelTexture(loader.loadTexture("Socuwan Scene/Houses/diffuse")));
		TexturedModel houseb = new TexturedModel(housebModel,new ModelTexture(loader.loadTexture("Socuwan Scene/Houses/diffuse")));
		TexturedModel houseu = new TexturedModel(houseuModel,new ModelTexture(loader.loadTexture("Socuwan Scene/Houses/diffuse")));

		// House 2
		TexturedModel house2 = new TexturedModel(house2Model,new ModelTexture(loader.loadTexture("Socuwan Scene/Houses/diffuse")));
		TexturedModel house2t = new TexturedModel(house2tModel,new ModelTexture(loader.loadTexture("Socuwan Scene/Houses/diffuse")));
		TexturedModel house2b = new TexturedModel(house2bModel,new ModelTexture(loader.loadTexture("Socuwan Scene/Houses/diffuse")));
		TexturedModel house2u = new TexturedModel(house2uModel,new ModelTexture(loader.loadTexture("Socuwan Scene/Houses/diffuse")));

		// Windmill
		TexturedModel windmill = new TexturedModel(windmillModel,new ModelTexture(loader.loadTexture("Socuwan Scene/Windmill/diffuse")));
		TexturedModel windmillt = new TexturedModel(windmilltModel,new ModelTexture(loader.loadTexture("Socuwan Scene/Windmill/diffuse")));
		TexturedModel windmillb = new TexturedModel(windmillbModel,new ModelTexture(loader.loadTexture("Socuwan Scene/Windmill/diffuse")));
		TexturedModel windmillu = new TexturedModel(windmilluModel,new ModelTexture(loader.loadTexture("Socuwan Scene/Windmill/diffuse")));

		TexturedModel bridge = new TexturedModel(bridgeModel,new ModelTexture(loader.loadTexture("Socuwan Scene/Bridge/diffuse")));

		// Herb Stall
		TexturedModel stall = new TexturedModel(stallModel,new ModelTexture(loader.loadTexture("Socuwan Scene/Herb Stall/diffuse")));
		TexturedModel stall1 = new TexturedModel(stall1Model,new ModelTexture(loader.loadTexture("Socuwan Scene/Herb Stall/diffuse")));

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

		// Light 1 & Lamp
		lights.add(new Light(new Vector3f(318.71042f,terrain.getHeightOfTerain(318.71042f,-349.7511f)+20f,-349.7511f),new Vector3f(2,0,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(318.71042f,terrain.getHeightOfTerain(318.71042f,-349.7511f),-349.7511f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(256f,terrain.getHeightOfTerain(256f,-423f)+20f,-423f),new Vector3f(2,0,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(256f,terrain.getHeightOfTerain(256f,-423f),-423f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(247f,terrain.getHeightOfTerain(247f,-373f)+20f,-373f),new Vector3f(2,0,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(247f,terrain.getHeightOfTerain(247f,-373f),-373f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(663f,terrain.getHeightOfTerain(663f,-315f)+20f,-315f),new Vector3f(2,0,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(663f,terrain.getHeightOfTerain(663f,-315f),-315f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(753f,terrain.getHeightOfTerain(753f,-98f)+20f,-98f),new Vector3f(2,0,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(753f,terrain.getHeightOfTerain(753f,-98f),-98f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(433f,terrain.getHeightOfTerain(433f,-77f)+20f,-77f),new Vector3f(2,0,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(433f,terrain.getHeightOfTerain(433f,-77f),-77f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(573f,terrain.getHeightOfTerain(573f,-200f)+20f,-200f),new Vector3f(2,0,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(573f,terrain.getHeightOfTerain(573f,-200f),-200f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(474f,terrain.getHeightOfTerain(474f,-435f)+20f,-435f),new Vector3f(2,0,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(474f,terrain.getHeightOfTerain(474f,-435f),-435f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(418f,terrain.getHeightOfTerain(418f,-660f)+20f,-660f),new Vector3f(2,0,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(418f,terrain.getHeightOfTerain(418f,-660f),-660f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(213f,terrain.getHeightOfTerain(213f,-193f)+20f,-193f),new Vector3f(2,0,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(213f,terrain.getHeightOfTerain(213f,-193f),-193f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(207f,terrain.getHeightOfTerain(207f,-114f)+20f,-114f),new Vector3f(2,0,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(207f,terrain.getHeightOfTerain(207f,-114f),-114f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(87f,terrain.getHeightOfTerain(87f,-202f)+20f,-202f),new Vector3f(2,0,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(87f,terrain.getHeightOfTerain(87f,-202f),-202f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(265f,terrain.getHeightOfTerain(265f,-113f)+20f,-113f),new Vector3f(2,0,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(265f,terrain.getHeightOfTerain(265f,-113f),-113f),0,0,0,1.5f));

		// Light 2 & Lamp
		lights.add(new Light(new Vector3f(421.35f,terrain.getHeightOfTerain(421.35f,-322)+20f,-322),new Vector3f(0,2,2),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(421.35f,terrain.getHeightOfTerain(421.35f,-322),-322),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(323f,terrain.getHeightOfTerain(323f,-423)+20f,-423),new Vector3f(0,2,2),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(323f,terrain.getHeightOfTerain(323f,-423),-423),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(166f,terrain.getHeightOfTerain(166f,-426)+20f,-426),new Vector3f(0,2,2),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(166f,terrain.getHeightOfTerain(166f,-426),-426),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(127f,terrain.getHeightOfTerain(127f,-373)+20f,-373),new Vector3f(0,2,2),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(127f,terrain.getHeightOfTerain(127f,-373),-373),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(770f,terrain.getHeightOfTerain(770f,-289)+20f,-289),new Vector3f(0,2,2),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(770f,terrain.getHeightOfTerain(770f,-289),-289),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(703f,terrain.getHeightOfTerain(703f,-46)+20f,-46),new Vector3f(0,2,2),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(703f,terrain.getHeightOfTerain(703f,-46),-46),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(365f,terrain.getHeightOfTerain(365f,-153)+20f,-153),new Vector3f(0,2,2),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(365f,terrain.getHeightOfTerain(365f,-153),-153),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(613f,terrain.getHeightOfTerain(613f,-449)+20f,-449),new Vector3f(0,2,2),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(613f,terrain.getHeightOfTerain(613f,-449),-449),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(126f,terrain.getHeightOfTerain(126f,-265)+20f,-265),new Vector3f(0,2,2),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(126f,terrain.getHeightOfTerain(126f,-265),-265),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(158f,terrain.getHeightOfTerain(158f,-221)+20f,-221),new Vector3f(0,2,2),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(158f,terrain.getHeightOfTerain(158f,-221),-221),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(316f,terrain.getHeightOfTerain(316f,-14)+20f,-14),new Vector3f(2,2,2),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(316f,terrain.getHeightOfTerain(316f,-14),-14),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(283f,terrain.getHeightOfTerain(283f,-502)+20f,-502),new Vector3f(0,0,2),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(283f,terrain.getHeightOfTerain(283f,-502),-502),0,0,0,1.5f));



		// Light 3 & Lamp
		lights.add(new Light(new Vector3f(470,terrain.getHeightOfTerain(470,-305)+20f,-305),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(470,terrain.getHeightOfTerain(470,-305),-305),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(780,terrain.getHeightOfTerain(780,-175)+20f,-175),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(780,terrain.getHeightOfTerain(780,-175),-175),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(501,terrain.getHeightOfTerain(501,-140)+20f,-140),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(501,terrain.getHeightOfTerain(501,-140),-140),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(555,terrain.getHeightOfTerain(555,-14)+20f,-14),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(555,terrain.getHeightOfTerain(555,-14),-14),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(422,terrain.getHeightOfTerain(422,-227)+20f,-227),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(422,terrain.getHeightOfTerain(422,-227),-227),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(303,terrain.getHeightOfTerain(303,-225)+20f,-225),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(303,terrain.getHeightOfTerain(303,-225),-225),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(653,terrain.getHeightOfTerain(653,-401)+20f,-401),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(653,terrain.getHeightOfTerain(653,-401),-401),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(450,terrain.getHeightOfTerain(450,-565)+20f,-565),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.0002f)));
		entities.add(new Entity(lamp,new Vector3f(450,terrain.getHeightOfTerain(450,-565),-565),0,0,0,1.5f));




		Player player = new Player(person, new Vector3f(405,3.64f,-382.92123f), 0,0,0,1);

		entities.add(new Entity(tree, new Vector3f(10,0,-20), 0f,0f,0f,5f));


		// HOUSE 1
		entities.add(new Entity(house,new Vector3f(338f,terrain.getHeightOfTerain(338f,-188),-188),0,0,0,1));
		entities.add(new Entity(house,new Vector3f(338f,terrain.getHeightOfTerain(338f,-230),-230),0,0,0,1));
		entities.add(new Entity(houset,new Vector3f(499f,terrain.getHeightOfTerain(499f,-275),-275),0,0,0,1));
		entities.add(new Entity(houseb,new Vector3f(215f,terrain.getHeightOfTerain(215f,-440),-440),0,0,0,1));
		entities.add(new Entity(houseb,new Vector3f(268f,terrain.getHeightOfTerain(268f,-353),-353),0,0,0,1));
		entities.add(new Entity(house,new Vector3f(590f,terrain.getHeightOfTerain(590f,-230),-230),0,0,0,1));
		entities.add(new Entity(houset,new Vector3f(550f,terrain.getHeightOfTerain(550f,-383),-383),0,0,0,1));
		entities.add(new Entity(houseb,new Vector3f(577f,terrain.getHeightOfTerain(577f,-326),-326),0,0,0,1));
		entities.add(new Entity(houseb,new Vector3f(690f,terrain.getHeightOfTerain(690f,-325),-325),0,0,0,1));
		entities.add(new Entity(houseb,new Vector3f(670f,terrain.getHeightOfTerain(670f,-65),-65),0,0,0,1));
		entities.add(new Entity(houseb,new Vector3f(460f,terrain.getHeightOfTerain(460f,-65),-65),0,0,0,1));
		entities.add(new Entity(house,new Vector3f(450f,terrain.getHeightOfTerain(450f,-599),-599),0,0,0,1));
		entities.add(new Entity(houseu,new Vector3f(155f,terrain.getHeightOfTerain(155f,-289),-289),0,0,0,1));
		entities.add(new Entity(houseb,new Vector3f(186f,terrain.getHeightOfTerain(186f,-214),-214),0,0,0,1));
		entities.add(new Entity(houseu,new Vector3f(238f,terrain.getHeightOfTerain(238f,-119),-119),0,0,0,1));
		entities.add(new Entity(house,new Vector3f(187f,terrain.getHeightOfTerain(187f,-138),-138),0,0,0,1));
		entities.add(new Entity(houset,new Vector3f(120f,terrain.getHeightOfTerain(120f,-164),-164),0,0,0,1));


		// HOUSE 2
		entities.add(new Entity(house2,new Vector3f(580f,terrain.getHeightOfTerain(580f,-468),-468),0,0,0,1));
		entities.add(new Entity(house2,new Vector3f(580f,terrain.getHeightOfTerain(580f,-518),-518),0,0,0,1));
		entities.add(new Entity(house2u,new Vector3f(608f,terrain.getHeightOfTerain(608f,-544),-544),0,0,0,1));
		entities.add(new Entity(house2u,new Vector3f(608f,terrain.getHeightOfTerain(608f,-490),-490),0,0,0,1));
		entities.add(new Entity(house2,new Vector3f(304f,terrain.getHeightOfTerain(304f,-630),-630),0,0,0,1));
		entities.add(new Entity(house2t,new Vector3f(40f,terrain.getHeightOfTerain(40f,-327),-327),0,0,0,1));
		entities.add(new Entity(house2,new Vector3f(400f,terrain.getHeightOfTerain(400f,-266),-266),0,0,0,1));
		entities.add(new Entity(house2u,new Vector3f(423f,terrain.getHeightOfTerain(423f,-268),-268),0,0,0,1));
		entities.add(new Entity(house2u,new Vector3f(423f,terrain.getHeightOfTerain(423f,-200),-200),0,0,0,1));
		entities.add(new Entity(house2b,new Vector3f(271.50055f,terrain.getHeightOfTerain(271.50055f,-440),-440),0,0,0,1));
		entities.add(new Entity(house2t,new Vector3f(209.52f,terrain.getHeightOfTerain(209.52f,-353),-353),0,0,0,1));
		entities.add(new Entity(house2b,new Vector3f(355f,terrain.getHeightOfTerain(355f,-487),-487),0,0,0,1));
		entities.add(new Entity(house2b,new Vector3f(427f,terrain.getHeightOfTerain(427f,-495),-495),0,0,0,1));
		entities.add(new Entity(house2u,new Vector3f(486f,terrain.getHeightOfTerain(486f,-368),-368),0,0,0,1));
		entities.add(new Entity(house2b,new Vector3f(519f,terrain.getHeightOfTerain(519f,-185),-185),0,0,0,1));
		entities.add(new Entity(house2t,new Vector3f(668f,terrain.getHeightOfTerain(668f,-265),-265),0,0,0,1));
		entities.add(new Entity(house2,new Vector3f(799f,terrain.getHeightOfTerain(790f,-120),-120),0,0,0,1));
		entities.add(new Entity(house2,new Vector3f(799f,terrain.getHeightOfTerain(790f,-170),-170),0,0,0,1));
		entities.add(new Entity(house2u,new Vector3f(650f,terrain.getHeightOfTerain(650f,-175),-175),0,0,0,1));
		entities.add(new Entity(house2t,new Vector3f(580f,terrain.getHeightOfTerain(580f,-110),-110),0,0,0,1));
		entities.add(new Entity(house2,new Vector3f(300f,terrain.getHeightOfTerain(300f,-258),-258),0,0,0,1));
		entities.add(new Entity(house2,new Vector3f(420f,terrain.getHeightOfTerain(420f,-70),-70),0,0,0,1));
		entities.add(new Entity(house2,new Vector3f(215f,terrain.getHeightOfTerain(215f,-60),-60),0,0,0,1));
		entities.add(new Entity(house2,new Vector3f(120f,terrain.getHeightOfTerain(120f,-280),-260),0,0,0,1));

		// Windmill
		entities.add(new Entity(windmillu,new Vector3f(708f,terrain.getHeightOfTerain(708f,-337f),-337f),0,0,0,1));
		entities.add(new Entity(windmillu,new Vector3f(685f,terrain.getHeightOfTerain(685f,-65f),-65f),0,0,0,1));


		// Herb Stall
		entities.add(new Entity(stall1,new Vector3f(490f,terrain.getHeightOfTerain(490f,-447),-447),0,0,0,1));
		entities.add(new Entity(stall1,new Vector3f(609f,terrain.getHeightOfTerain(609f,-515),-515),0,0,0,1));
		entities.add(new Entity(stall,new Vector3f(248f,terrain.getHeightOfTerain(248f,-452),-452),0,0,0,1));

//		entities.add(new Entity(housefull,new Vector3f(580f,terrain.getHeightOfTerain(580f,-468),-468),0,0,0,1));

//		entities.add(new Entity(rock,new Vector3f(387.3f,terrain.getHeightOfTerain(387.3f,-395.2f),-395.2f),0,0,0,10));
//		entities.add(new Entity(rock,new Vector3f(213.15736f,terrain.getHeightOfTerain(213.15736f,-641.9919f),-641.9919f),0,0,0,10));

		// Tree
		entities.add(new Entity(tree,new Vector3f(178f,terrain.getHeightOfTerain(178f,-356),-356),0,0,0,3));
		entities.add(new Entity(tree,new Vector3f(231f,terrain.getHeightOfTerain(231f,-482),-482),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(422f,terrain.getHeightOfTerain(422f,-558),-558),0,0,0,3));
		entities.add(new Entity(tree,new Vector3f(683f,terrain.getHeightOfTerain(683f,-507),-507),0,0,0,4));
		entities.add(new Entity(tree,new Vector3f(534f,terrain.getHeightOfTerain(534f,-439),-439),0,0,0,3));
		entities.add(new Entity(tree,new Vector3f(562f,terrain.getHeightOfTerain(562f,-255),-255),0,0,0,3));
		entities.add(new Entity(tree,new Vector3f(479f,terrain.getHeightOfTerain(479f,-239),-239),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(698f,terrain.getHeightOfTerain(698f,-363),-363),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(631f,terrain.getHeightOfTerain(631f,-52),-52),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(523f,terrain.getHeightOfTerain(523f,-72),-72),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(285f,terrain.getHeightOfTerain(285f,-168),-168),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(249f,terrain.getHeightOfTerain(249f,-302),-302),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(436f,terrain.getHeightOfTerain(436f,-639),-639),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(233f,terrain.getHeightOfTerain(233f,-216),-216),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(110f,terrain.getHeightOfTerain(110f,-345),-345),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(100f,terrain.getHeightOfTerain(100f,-174),-174),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(23f,terrain.getHeightOfTerain(23f,-200),-200),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(41f,terrain.getHeightOfTerain(41f,-139),-139),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(86f,terrain.getHeightOfTerain(86f,-96),-96),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(73f,terrain.getHeightOfTerain(73f,-56),-56),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(283f,terrain.getHeightOfTerain(283f,-99),-99),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(175f,terrain.getHeightOfTerain(175f,-22),-22),0,0,0,5));

		// Tree 1
		entities.add(new Entity(tree1,new Vector3f(144f,terrain.getHeightOfTerain(144f,-255),-255),0,0,0,1.5f));
		entities.add(new Entity(tree1,new Vector3f(346f,terrain.getHeightOfTerain(346f,-567),-567),0,0,0,1.5f));
		entities.add(new Entity(tree1,new Vector3f(625f,terrain.getHeightOfTerain(625f,-579),-579),0,0,0,1.5f));
		entities.add(new Entity(tree1,new Vector3f(603f,terrain.getHeightOfTerain(603f,-390),-390),0,0,0,1.5f));
		entities.add(new Entity(tree1,new Vector3f(745f,terrain.getHeightOfTerain(745f,-250),-250),0,0,0,1.5f));
		entities.add(new Entity(tree1,new Vector3f(784f,terrain.getHeightOfTerain(784f,-14),-14),0,0,0,1.5f));
		entities.add(new Entity(tree1,new Vector3f(482f,terrain.getHeightOfTerain(482f,-14),-14),0,0,0,1.5f));
		entities.add(new Entity(tree1,new Vector3f(633f,terrain.getHeightOfTerain(633f,-132),-132),0,0,0,1.5f));
		entities.add(new Entity(tree1,new Vector3f(673f,terrain.getHeightOfTerain(673f,-464),-464),0,0,0,1.5f));
		entities.add(new Entity(tree1,new Vector3f(77f,terrain.getHeightOfTerain(77f,-225),-225),0,0,0,1.5f));
		entities.add(new Entity(tree1,new Vector3f(216f,terrain.getHeightOfTerain(216f,-64),-64),0,0,0,1.5f));
		entities.add(new Entity(tree1,new Vector3f(294f,terrain.getHeightOfTerain(294f,-54),-54),0,0,0,1.5f));



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
