package renderEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Player;
import models.TexturedModel;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.vector.Matrix4f;

import org.lwjgl.util.vector.Vector3f;
import shaders.StaticShader;
import shaders.TerrainShader;
import shadows.ShadowMapMasterRenderer;
import skybox.SkyboxRenderer;
import terrains.Terrain;
import entities.Camera;
import entities.Entity;
import entities.Light;

public class MasterRenderer {
	
	public static final float FOV = 70;
	public static final float NEAR_PLANE = 0.1f;
	public static final float FAR_PLANE = 1000;

	private static final float NIGHT_SUN = 0.5f;
	private static final float DAY_SUN = 1.3f;

	private static float RED = 0.5444f;
	private static float GREEN = 0.62f;
	private static float BLUE = 0.69f;

	private Matrix4f projectionMatrix;
	
	private StaticShader shader = new StaticShader();
	private EntityRenderer renderer;
	
	private TerrainRenderer terrainRenderer;
	private TerrainShader terrainShader = new TerrainShader();
	
	
	private Map<TexturedModel,List<Entity>> entities = new HashMap<TexturedModel,List<Entity>>();
	private List<Terrain> terrains = new ArrayList<Terrain>();

	private SkyboxRenderer skyboxRenderer;
	private ShadowMapMasterRenderer shadowMapRenderer;
	public float time;
	private float x=0,y=0,z=0;
	private boolean spotlight = false;
	public float keyPress;

	
	public MasterRenderer(Loader loader,Camera camera){
		enableCulling();
		createProjectionMatrix();
		renderer = new EntityRenderer(shader,projectionMatrix);
		terrainRenderer = new TerrainRenderer(terrainShader,projectionMatrix);
		skyboxRenderer = new SkyboxRenderer(loader,projectionMatrix);
		this.shadowMapRenderer = new ShadowMapMasterRenderer(camera);
		time = skyboxRenderer.getTime();

	}

	public static void enableCulling(){
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}

	public static void disableCulling(){
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
	
	public void render(List<Light> lights, Camera camera, Player player){
		prepare();
		time += DisplayManager.getFrameTimeSeconds() * 500;
		time %= 24000;

		if (time>0 && time<5000){
			RED = 0.02f;
			GREEN = 0.03f;
			BLUE = 0.035f;
			x = NIGHT_SUN;
			y = NIGHT_SUN;
			z = NIGHT_SUN;
			lights.get(0).setColour(new Vector3f(x,y,z));
		}else if (time >= 5000 && time < 8000){
			RED += (0.5444f - 0.02f)/740;
			GREEN += (0.62f - 0.03f)/740;
			BLUE += (0.69f - 0.035f)/740;
			x += (DAY_SUN-NIGHT_SUN)/700;
			y += (DAY_SUN-NIGHT_SUN)/700;
				z +=  (DAY_SUN-NIGHT_SUN)/700;
			lights.get(0).setColour(new Vector3f(x,y,z));
		}else if (time >= 8000 && time < 21000){
			RED = 0.5444f;
			GREEN = 0.62f;
			BLUE = 0.69f;
			x = DAY_SUN;
			y = DAY_SUN;
			z = DAY_SUN;
			lights.get(0).setColour(new Vector3f(x,y,z));
		}else{
			RED -= (0.5444f - 0.02f)/700;
			GREEN -= (0.62f - 0.03f)/700;
			BLUE -= (0.69f - 0.035f)/700;
			x -= (DAY_SUN-NIGHT_SUN)/700;
			y -= (DAY_SUN-NIGHT_SUN)/700;
			z -=  (DAY_SUN-NIGHT_SUN)/700;
			lights.get(0).setColour(new Vector3f(x,y,z));
		}
		float x=0,y=0;

		if (time>6000 && time<24000){
			x = +10;
			if (time < 15000){
				y = 3f;
			}else {
				y = -3f;
			}
		}else {
			x = 0;
		}

		if (x==0){
			lights.get(0).setPosition(new Vector3f(
					-10000,
					2000,
					lights.get(0).getPosition().z));
		}else {
			lights.get(0).setPosition(new Vector3f(
					lights.get(0).getPosition().x + x,
					lights.get(0).getPosition().y + y,
					lights.get(0).getPosition().z));
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_F) && !spotlight && keyPress+100<time){
			spotlight = true;
			keyPress = time;
		}else if (Keyboard.isKeyDown(Keyboard.KEY_F) && spotlight && keyPress+100<time){
			spotlight = false;
			keyPress = time;
		}
		if (time>0 && time<100){
			keyPress = 0;
		}

		Vector3f temp;
		if (spotlight){
			temp = new Vector3f(player.getPosition().x,player.getPosition().y+10,player.getPosition().z);
		}else {
			temp = new Vector3f(10000000,10000000,10000000);
		}

//		System.out.println(lights.get(0).getPosition());



//		System.out.println(RED+" "+GREEN+" "+BLUE);
//		System.out.println(this.time+ " " + x);
		shader.start();
		shader.loadSkyColour(RED,GREEN,BLUE);
		shader.loadLights(lights);
		shader.loadViewMatrix(camera);
		shader.loadSpotLight(
				temp,
				camera.getDirection(),
				new Vector3f(0.0f,0.0f,0.0f),
				new Vector3f(15.0f,15.0f,15.0f),
				new Vector3f(15.0f,15.0f,15.0f),
				1.0f,
				0.09f,
				0.032f,
				(float)Math.cos(Math.toRadians(12.5f)),
				(float)Math.cos(Math.toRadians(12.5f)),camera.getPosition());
		renderer.render(entities);
		shader.stop();
		terrainShader.start();
		terrainShader.loadSkyColour(RED,GREEN,BLUE);
		terrainShader.loadLights(lights);
		terrainShader.loadViewMatrix(camera);
		terrainShader.loadSpotLight(
				temp,
				camera.getDirection(),
				new Vector3f(0.0f, 0.0f, 0.0f),
				new Vector3f(20.0f, 20.0f, 20.0f),
				new Vector3f(20.0f, 20.0f, 20.0f),
				1.0f,
				0.09f,
				0.032f,
				(float) Math.cos(Math.toRadians(12.5f)),
				(float) Math.cos(Math.toRadians(12.5f)), camera.getPosition());
		terrainRenderer.render(terrains,shadowMapRenderer.getToShadowMapSpaceMatrix());
		terrainShader.stop();
		skyboxRenderer.render(camera,RED,GREEN,BLUE, time);
		terrains.clear();
		entities.clear();
	}
	
	public void processTerrain(Terrain terrain){
		terrains.add(terrain);
	}
	
	public void processEntity(Entity entity){
		TexturedModel entityModel = entity.getModel();
		List<Entity> batch = entities.get(entityModel);
		entities.put(entityModel,batch);
		if(batch!=null){
			batch.add(entity);
		}else{
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entityModel, newBatch);		
		}
	}

	public void renderShadowMap(List<Entity> entityList,Light sun){
		for (Entity entity:entityList){
			processEntity(entity);
		}
		shadowMapRenderer.render(entities,sun);
		entities.clear();
	}

	public int getShadowMapTexture(){
		return shadowMapRenderer.getShadowMap();
	}
	
	public void cleanUp(){
		shader.cleanUp();
		terrainShader.cleanUp();
		shadowMapRenderer.cleanUp();
	}
	
	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(RED, GREEN, BLUE, 1);
		GL13.glActiveTexture(GL13.GL_TEXTURE5);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D,getShadowMapTexture());
	}

	private void createProjectionMatrix(){
		projectionMatrix = new Matrix4f();
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))));
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;

		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
	}
	

}
