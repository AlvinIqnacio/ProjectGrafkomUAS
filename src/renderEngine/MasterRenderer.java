package renderEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import org.lwjgl.util.vector.Vector3f;
import shaders.StaticShader;
import shaders.TerrainShader;
import skybox.SkyboxRenderer;
import terrains.Terrain;
import entities.Camera;
import entities.Entity;
import entities.Light;

public class MasterRenderer {
	
	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;

	private static final float NIGHT_SUN = 0.3f;
	private static final float DAY_SUN = 0.9f;

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
	private float time;
	private float x=0,y=0,z=0;

	
	public MasterRenderer(Loader loader){
		enableCulling();
		createProjectionMatrix();
		renderer = new EntityRenderer(shader,projectionMatrix);
		terrainRenderer = new TerrainRenderer(terrainShader,projectionMatrix);
		skyboxRenderer = new SkyboxRenderer(loader,projectionMatrix);
		time = skyboxRenderer.getTime();
	}

	public static void enableCulling(){
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}

	public static void disableCulling(){
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
	
	public void render(List<Light> lights,Camera camera){
		prepare();
		time += DisplayManager.getFrameTimeSeconds() * 1000;
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
			RED += (0.5444f - 0.02f)/370;
			GREEN += (0.62f - 0.03f)/370;
			BLUE += (0.69f - 0.035f)/370;
			x += (DAY_SUN-NIGHT_SUN)/350;
			y += (DAY_SUN-NIGHT_SUN)/350;
			z +=  (DAY_SUN-NIGHT_SUN)/350;
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
			RED -= (0.5444f - 0.02f)/350;
			GREEN -= (0.62f - 0.03f)/350;
			BLUE -= (0.69f - 0.035f)/350;
			x -= (DAY_SUN-NIGHT_SUN)/350;
			y -= (DAY_SUN-NIGHT_SUN)/350;
			z -=  (DAY_SUN-NIGHT_SUN)/350;
			lights.get(0).setColour(new Vector3f(x,y,z));
		}

//		System.out.println(RED+" "+GREEN+" "+BLUE);
//		System.out.println(this.time);
		shader.start();
		shader.loadSkyColour(RED,GREEN,BLUE);
		shader.loadLights(lights);
		shader.loadViewMatrix(camera);
		renderer.render(entities);
		shader.stop();
		terrainShader.start();
		terrainShader.loadSkyColour(RED,GREEN,BLUE);
		terrainShader.loadLights(lights);
		terrainShader.loadViewMatrix(camera);
		terrainRenderer.render(terrains);
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
		if(batch!=null){
			batch.add(entity);
		}else{
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entityModel, newBatch);		
		}
	}
	
	public void cleanUp(){
		shader.cleanUp();
		terrainShader.cleanUp();
	}
	
	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(RED, GREEN, BLUE, 1);
	}
	
	private void createProjectionMatrix() {
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;

		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
	}
	

}
