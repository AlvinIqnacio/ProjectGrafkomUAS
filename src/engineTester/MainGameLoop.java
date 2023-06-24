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
		ModelData lampuData = OBJFileLoader.loadOBJ("Socuwan Scene/Lanterns/lampu");


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

		// Barrels
		ModelData barrelData = OBJFileLoader.loadOBJ("Socuwan Scene/Barrels/barrel");
		ModelData barrel1Data = OBJFileLoader.loadOBJ("Socuwan Scene/Barrels/barrels1");
		ModelData barrel2Data = OBJFileLoader.loadOBJ("Socuwan Scene/Barrels/barrels2");



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
		RawModel lampuModel = loader.loadToVAOTemp(
				lampuData.getVertices(),
				lampuData.getTextureCoords(),
				lampuData.getNormals(),
				lampuData.getIndices(),
				lampuData.getVerticesModel(),
				lampuData.getIndicesModel());

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

		RawModel barrelModel = loader.loadToVAOTemp(
				barrelData.getVertices(),
				barrelData.getTextureCoords(),
				barrelData.getNormals(),
				barrelData.getIndices(),
				barrelData.getVerticesModel(),
				barrelData.getIndicesModel());
		RawModel barrel1Model = loader.loadToVAOTemp(
				barrel1Data.getVertices(),
				barrel1Data.getTextureCoords(),
				barrel1Data.getNormals(),
				barrel1Data.getIndices(),
				barrel1Data.getVerticesModel(),
				barrel1Data.getIndicesModel());
		RawModel barrel2Model = loader.loadToVAOTemp(
				barrel2Data.getVertices(),
				barrel2Data.getTextureCoords(),
				barrel2Data.getNormals(),
				barrel2Data.getIndices(),
				barrel2Data.getVerticesModel(),
				barrel2Data.getIndicesModel());

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
		TexturedModel lampu = new TexturedModel(lampuModel, new ModelTexture(loader.loadTexture("Socuwan Scene/Lanterns/diffuse")));
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

		TexturedModel barrel = new TexturedModel(barrelModel,new ModelTexture(loader.loadTexture("Socuwan Scene/Barrels/diffuse")));
		TexturedModel barrel1 = new TexturedModel(barrel1Model,new ModelTexture(loader.loadTexture("Socuwan Scene/Barrels/diffuse")));
		TexturedModel barrel2 = new TexturedModel(barrel2Model,new ModelTexture(loader.loadTexture("Socuwan Scene/Barrels/diffuse")));

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
		lampu.getTexture().setUseFakeLighting(true);

		
		List<Entity> entities = new ArrayList<>();
		List<Entity> obstacle = new ArrayList<>();


		List<Light> lights = new ArrayList<>();
		lights.add(new Light(new Vector3f(-10000,2000,5),new Vector3f(1.0f,1.0f,1.0f)));

		// Light 1 & Lamp
		lights.add(new Light(new Vector3f(318.76355f,terrain.getHeightOfTerain(318.76355f,-356.12622f)+15f,-356.12622f),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.002f)));
		entities.add(new Entity(lampu,new Vector3f(318.71042f,terrain.getHeightOfTerain(318.71042f,-349.7511f),-349.7511f),0,90,0,1.5f));

		lights.add(new Light(new Vector3f(256f,terrain.getHeightOfTerain(256f,-423f)+20f,-423f),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.002f)));
		entities.add(new Entity(lamp,new Vector3f(256f,terrain.getHeightOfTerain(256f,-423f),-423f),0,0,0,1.5f));


		lights.add(new Light(new Vector3f(433f,terrain.getHeightOfTerain(433f,-77f)+20f,-77f),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.002f)));
		entities.add(new Entity(lamp,new Vector3f(433f,terrain.getHeightOfTerain(433f,-77f),-77f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(573f,terrain.getHeightOfTerain(573f,-200f)+20f,-200f),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.002f)));
		entities.add(new Entity(lamp,new Vector3f(573f,terrain.getHeightOfTerain(573f,-200f),-200f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(469f,terrain.getHeightOfTerain(469f,-432.86765f)+20f,-432.86765f),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.002f)));
		entities.add(new Entity(lampu,new Vector3f(474f,terrain.getHeightOfTerain(474f,-435f),-435f),0,200,0,1.5f));

		lights.add(new Light(new Vector3f(418f,terrain.getHeightOfTerain(418f,-660f)+20f,-660f),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.002f)));
		entities.add(new Entity(lamp,new Vector3f(418f,terrain.getHeightOfTerain(418f,-660f),-660f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(325f,terrain.getHeightOfTerain(325f,-587f)+20f,-587f),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.002f)));
		entities.add(new Entity(lamp,new Vector3f(325f,terrain.getHeightOfTerain(325f,-587f),-587f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(213f,terrain.getHeightOfTerain(213f,-193f)+20f,-193f),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.002f)));
		entities.add(new Entity(lamp,new Vector3f(213f,terrain.getHeightOfTerain(213f,-193f),-193f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(207f,terrain.getHeightOfTerain(207f,-114f)+20f,-114f),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.002f)));
		entities.add(new Entity(lamp,new Vector3f(207f,terrain.getHeightOfTerain(207f,-114f),-114f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(87f,terrain.getHeightOfTerain(87f,-202f)+20f,-202f),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.002f)));
		entities.add(new Entity(lamp,new Vector3f(87f,terrain.getHeightOfTerain(87f,-202f),-202f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(265f,terrain.getHeightOfTerain(265f,-113f)+20f,-113f),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.002f)));
		entities.add(new Entity(lamp,new Vector3f(265f,terrain.getHeightOfTerain(265f,-113f),-113f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(200f,terrain.getHeightOfTerain(200f,-550f)+20f,-550f),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.002f)));
		entities.add(new Entity(lamp,new Vector3f(200f,terrain.getHeightOfTerain(200f,-550f),-550f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(40f,terrain.getHeightOfTerain(40f,-729f)+20f,-729f),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.002f)));
		entities.add(new Entity(lamp,new Vector3f(40f,terrain.getHeightOfTerain(40f,-729f),-729f),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(417.43423f,terrain.getHeightOfTerain(417.43423f,-325.214f)+20f,-325.214f),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.002f)));
		entities.add(new Entity(lampu,new Vector3f(421.35f,terrain.getHeightOfTerain(421.35f,-322),-322),0,130,0,1.5f));

		lights.add(new Light(new Vector3f(60f,terrain.getHeightOfTerain(60f,-406)+20f,-406),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.002f)));
		entities.add(new Entity(lamp,new Vector3f(60f,terrain.getHeightOfTerain(60f,-406),-406),0,0,0,1.5f));

		lights.add(new Light(new Vector3f(328.43793f,terrain.getHeightOfTerain(328.43793f,-419.62714f)+20f,-419.62714f),new Vector3f(2,2,0),new Vector3f(1.5f,0.01f,0.002f)));
		entities.add(new Entity(lampu,new Vector3f(323f,terrain.getHeightOfTerain(323f,-423),-423),0,300,0,1.5f));




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
		entities.add(new Entity(house,new Vector3f(46f,terrain.getHeightOfTerain(46f,-756),-756),0,0,0,1));
		entities.add(new Entity(house,new Vector3f(31f,terrain.getHeightOfTerain(31f,-645),-645),0,0,0,1));
		entities.add(new Entity(house,new Vector3f(358f,terrain.getHeightOfTerain(358f,-610),-610),0,0,0,1));
		entities.add(new Entity(houseb,new Vector3f(345f,terrain.getHeightOfTerain(345f,-760),-760),0,0,0,1));
		entities.add(new Entity(houset,new Vector3f(270f,terrain.getHeightOfTerain(270f,-697),-697),0,0,0,1));
		entities.add(new Entity(houseb,new Vector3f(665f,terrain.getHeightOfTerain(665f,-668),-668),0,0,0,1));
		entities.add(new Entity(houseb,new Vector3f(710.1882f,terrain.getHeightOfTerain(710.1882f,-634.46f),-634.46f),0,0,0,1));
		entities.add(new Entity(houseu,new Vector3f(438f,terrain.getHeightOfTerain(438f,-760f),-760f),0,0,0,1));
		entities.add(new Entity(houseu,new Vector3f(757f,terrain.getHeightOfTerain(757f,-442f),-442f),0,0,0,1));
		entities.add(new Entity(houseu,new Vector3f(767f,terrain.getHeightOfTerain(767f,-562f),-562f),0,0,0,1));


		// HOUSE 2
		entities.add(new Entity(house2,new Vector3f(525f,terrain.getHeightOfTerain(525f,-468),-468),0,0,0,1));
		entities.add(new Entity(house2,new Vector3f(525f,terrain.getHeightOfTerain(525,-518),-518),0,0,0,1));
		entities.add(new Entity(house2u,new Vector3f(608f,terrain.getHeightOfTerain(608f,-544),-544),0,0,0,1));
		entities.add(new Entity(house2u,new Vector3f(608f,terrain.getHeightOfTerain(608f,-490),-490),0,0,0,1));
		entities.add(new Entity(house2b,new Vector3f(208f,terrain.getHeightOfTerain(208f,-637),-637),0,0,0,1));
		entities.add(new Entity(house2b,new Vector3f(272f,terrain.getHeightOfTerain(272f,-617),-617),0,0,0,1));
		entities.add(new Entity(house2t,new Vector3f(40f,terrain.getHeightOfTerain(40f,-327),-327),0,0,0,1));
		entities.add(new Entity(house2,new Vector3f(338f,terrain.getHeightOfTerain(338f,-280),-280),0,0,0,1));
		entities.add(new Entity(house2u,new Vector3f(423f,terrain.getHeightOfTerain(423f,-268),-268),0,0,0,1));
		entities.add(new Entity(house2u,new Vector3f(423f,terrain.getHeightOfTerain(423f,-200),-200),0,0,0,1));
		entities.add(new Entity(house2b,new Vector3f(271.50055f,terrain.getHeightOfTerain(271.50055f,-440),-440),0,0,0,1));
		entities.add(new Entity(house2t,new Vector3f(209.52f,terrain.getHeightOfTerain(209.52f,-353),-353),0,0,0,1));
		entities.add(new Entity(house2b,new Vector3f(355f,terrain.getHeightOfTerain(355f,-487),-487),0,0,0,1));
		entities.add(new Entity(house2b,new Vector3f(427f,terrain.getHeightOfTerain(427f,-495),-495),0,0,0,1));
		entities.add(new Entity(house2u,new Vector3f(486f,terrain.getHeightOfTerain(486f,-368),-368),0,0,0,1));
		entities.add(new Entity(house2b,new Vector3f(519f,terrain.getHeightOfTerain(519f,-185),-185),0,0,0,1));
		entities.add(new Entity(house2t,new Vector3f(668f,terrain.getHeightOfTerain(668f,-265),-265),0,0,0,1));
		entities.add(new Entity(house2,new Vector3f(735f,terrain.getHeightOfTerain(735f,-120),-123),0,0,0,1));
		entities.add(new Entity(house2,new Vector3f(735f,terrain.getHeightOfTerain(735f,-177),-169),0,0,0,1));
		entities.add(new Entity(house2u,new Vector3f(650f,terrain.getHeightOfTerain(650f,-175),-175),0,0,0,1));
		entities.add(new Entity(house2t,new Vector3f(580f,terrain.getHeightOfTerain(580f,-110),-110),0,0,0,1));
		entities.add(new Entity(house2,new Vector3f(239f,terrain.getHeightOfTerain(239f,-269),-269),0,0,0,1));
		entities.add(new Entity(house2,new Vector3f(360f,terrain.getHeightOfTerain(360f,-70),-70),0,0,0,1));
		entities.add(new Entity(house2,new Vector3f(142f,terrain.getHeightOfTerain(142f,-66),-66),0,0,0,1));
		entities.add(new Entity(house2,new Vector3f(58f,terrain.getHeightOfTerain(58f,-251),-251),0,0,0,1));
		entities.add(new Entity(house2b,new Vector3f(90f,terrain.getHeightOfTerain(90f,-420),-420),0,0,0,1));
		entities.add(new Entity(house2,new Vector3f(332f,terrain.getHeightOfTerain(332f,-667),-667),0,0,0,1));
		entities.add(new Entity(house2b,new Vector3f(474f,terrain.getHeightOfTerain(474f,-701),-701),0,0,0,1));
		entities.add(new Entity(house2u,new Vector3f(520f,terrain.getHeightOfTerain(520f,-636),-636),0,0,0,1));


		// Herb Stall
		entities.add(new Entity(stall1,new Vector3f(490f,terrain.getHeightOfTerain(490f,-447),-447),0,0,0,1));
		entities.add(new Entity(stall1,new Vector3f(609f,terrain.getHeightOfTerain(609f,-515),-515),0,0,0,1));
		entities.add(new Entity(stall,new Vector3f(248f,terrain.getHeightOfTerain(248f,-452),-452),0,0,0,1));

		// Barrel
		entities.add(new Entity(barrel2,new Vector3f(297f,terrain.getHeightOfTerain(297f,-451),-451),0,0,0,1));
		entities.add(new Entity(barrel,new Vector3f(235f,terrain.getHeightOfTerain(235f,-648),-648),0,0,0,1));
		entities.add(new Entity(barrel,new Vector3f(209f,terrain.getHeightOfTerain(209f,-269),-269),0,0,0,1));
		entities.add(new Entity(barrel1,new Vector3f(175f,terrain.getHeightOfTerain(175f,-283),-283),0,0,0,1));
		entities.add(new Entity(barrel1,new Vector3f(525f,terrain.getHeightOfTerain(525f,-372),-372),0,0,0,1));
		entities.add(new Entity(barrel,new Vector3f(570f,terrain.getHeightOfTerain(570f,-340),-340),0,0,0,1));
		entities.add(new Entity(barrel2,new Vector3f(701f,terrain.getHeightOfTerain(701f,-232),-232),0,0,0,1));
		entities.add(new Entity(barrel2,new Vector3f(511f,terrain.getHeightOfTerain(511f,-249),-249),0,0,0,1));
		entities.add(new Entity(barrel1,new Vector3f(501f,terrain.getHeightOfTerain(501f,-197),-197),0,0,0,1));
		entities.add(new Entity(barrel,new Vector3f(459f,terrain.getHeightOfTerain(459f,-257),-257),0,0,0,1));
		entities.add(new Entity(barrel,new Vector3f(325f,terrain.getHeightOfTerain(325f,-173),-173),0,0,0,1));
		entities.add(new Entity(barrel2,new Vector3f(74f,terrain.getHeightOfTerain(74f,-419),-419),0,0,0,1));
		entities.add(new Entity(barrel1,new Vector3f(56f,terrain.getHeightOfTerain(56f,-277),-277),0,0,0,1));
		entities.add(new Entity(barrel2,new Vector3f(166f,terrain.getHeightOfTerain(166f,-142),-142),0,0,0,1));
		entities.add(new Entity(barrel2,new Vector3f(281,terrain.getHeightOfTerain(281,-351),-351),0,0,0,1));
		entities.add(new Entity(barrel1,new Vector3f(428,terrain.getHeightOfTerain(428,-287),-287),0,0,0,1));
		entities.add(new Entity(barrel2,new Vector3f(335,terrain.getHeightOfTerain(335,-66),-66),0,0,0,1));
		entities.add(new Entity(barrel2,new Vector3f(687,terrain.getHeightOfTerain(687,-64),-64),0,0,0,1));
		entities.add(new Entity(barrel1,new Vector3f(664,terrain.getHeightOfTerain(664,-151),-151),0,0,0,1));

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
		entities.add(new Entity(tree,new Vector3f(45f,terrain.getHeightOfTerain(45f,-428),-428),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(41f,terrain.getHeightOfTerain(41f,-538),-538),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(45f,terrain.getHeightOfTerain(45f,-474),-474),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(38f,terrain.getHeightOfTerain(38f,-592),-592),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(160f,terrain.getHeightOfTerain(160f,-678),-678),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(252f,terrain.getHeightOfTerain(252f,-784),-784),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(202f,terrain.getHeightOfTerain(202f,-781),-781),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(161f,terrain.getHeightOfTerain(161f,-773),-773),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(109f,terrain.getHeightOfTerain(109f,-757),-757),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(34f,terrain.getHeightOfTerain(34f,-697),-697),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(320f,terrain.getHeightOfTerain(320f,-614),-614),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(615f,terrain.getHeightOfTerain(615f,-644),-644),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(689f,terrain.getHeightOfTerain(689f,-728),-728),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(757f,terrain.getHeightOfTerain(757f,-710),-710),0,0,0,5));
		entities.add(new Entity(tree,new Vector3f(759f,terrain.getHeightOfTerain(759f,-622),-622),0,0,0,4));
		entities.add(new Entity(tree,new Vector3f(732f,terrain.getHeightOfTerain(732f,-73),-73),0,0,0,4));
		entities.add(new Entity(tree,new Vector3f(474f,terrain.getHeightOfTerain(474f,-149),-149),0,0,0,5));

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
		entities.add(new Entity(tree1,new Vector3f(118f,terrain.getHeightOfTerain(118f,-477),-477),0,0,0,1.5f));
		entities.add(new Entity(tree1,new Vector3f(233f,terrain.getHeightOfTerain(223f,-556),-556),0,0,0,1.5f));
		entities.add(new Entity(tree1,new Vector3f(165f,terrain.getHeightOfTerain(165f,-545),-545),0,0,0,1.5f));
		entities.add(new Entity(tree1,new Vector3f(146f,terrain.getHeightOfTerain(146f,-599),-599),0,0,0,1.5f));
		entities.add(new Entity(tree1,new Vector3f(102f,terrain.getHeightOfTerain(102f,-636),-636),0,0,0,1.5f));
		entities.add(new Entity(tree1,new Vector3f(100f,terrain.getHeightOfTerain(100f,-709),-709),0,0,0,1.5f));
		entities.add(new Entity(tree1,new Vector3f(772f,terrain.getHeightOfTerain(772f,-489),-489),0,0,0,1.5f));



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
			renderer.render(lights, camera,player);
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
