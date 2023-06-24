package shaders;

import org.lwjgl.util.vector.Matrix4f;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import toolbox.Maths;
import entities.Camera;
import entities.Light;

import java.util.List;

public class TerrainShader extends ShaderProgram{

	private static final int MAX_LIGHT = 20;

	private static final String VERTEX_FILE = "src/shaders/terrainVertexShader.txt";
	private static final String FRAGMENT_FILE = "src/shaders/terrainFragmentShader.txt";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_lightPosition[];
	private int location_lightColour[];
	private int location_attenuation[];
	private int location_shineDamper;
	private int location_reflectivity;
	private int location_skyColour;
	private int location_backgroundTexture;
	private int location_rTexture;
	private int location_gTexture;
	private int location_bTexture;
	private int location_blendMap;
	private int location_toShadowMapSpace;
	private int location_shadowMap;

	private int location_spotLightPosition;
	private int location_spotLightDirection;
	private int location_spotLightAmbient;
	private int location_spotLightDiffuse;
	private int location_spotLightSpecular;
	private int location_spotLightConstant;
	private int location_spotLightLinear;
	private int location_spotLightQuadratic;
	private int location_spotLightCutOff;
	private int location_spotLightOuterCutOff;
	private int location_viewPos;


	public TerrainShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoordinates");
		super.bindAttribute(2, "normal");
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_shineDamper = super.getUniformLocation("shineDamper");
		location_reflectivity = super.getUniformLocation("reflectivity");
		location_skyColour = super.getUniformLocation("skyColour");
		location_backgroundTexture = super.getUniformLocation("backgroundTexture");
		location_rTexture = super.getUniformLocation("rTexture");
		location_gTexture = super.getUniformLocation("gTexture");
		location_bTexture = super.getUniformLocation("bTexture");
		location_blendMap = super.getUniformLocation("blendMap");
		location_toShadowMapSpace = super.getUniformLocation("toShadowMapSpace");
		location_shadowMap= super.getUniformLocation("shadowMap");

		location_lightPosition = new int[MAX_LIGHT];
		location_lightColour = new int[MAX_LIGHT];
		location_attenuation = new int[MAX_LIGHT];

		for (int i=0;i<MAX_LIGHT;i++){
			location_lightPosition[i]= super.getUniformLocation("lightPosition["+i+"]");
			location_lightColour[i]= super.getUniformLocation("lightColour["+i+"]");
			location_attenuation[i]= super.getUniformLocation("attenuation["+i+"]");
		}

		location_spotLightPosition = super.getUniformLocation("spotLight.position");
		location_spotLightDirection = super.getUniformLocation("spotLight.direction");
		location_spotLightAmbient = super.getUniformLocation("spotLight.ambient");
		location_spotLightDiffuse = super.getUniformLocation("spotLight.diffuse");
		location_spotLightSpecular = super.getUniformLocation("spotLight.specular");
		location_spotLightConstant = super.getUniformLocation("spotLight.constant");
		location_spotLightLinear = super.getUniformLocation("spotLight.linear");
		location_spotLightQuadratic = super.getUniformLocation("spotLight.quadratic");
		location_spotLightCutOff = super.getUniformLocation("spotLight.cutOff");
		location_spotLightOuterCutOff = super.getUniformLocation("spotLight.outerCutOff");
		location_viewPos = super.getUniformLocation("spotLight.viewPos");

	}

	public void loadSpotLight(Vector3f position, Vector3f direction, Vector3f ambient, Vector3f diffuse, Vector3f specular,
							  float constant, float linear, float quadratic, float cutOff, float outerCutOff, Vector3f viewPos){
		super.loadVector(location_spotLightPosition, position);
		super.loadVector(location_spotLightDirection, direction);
		super.loadVector(location_spotLightAmbient, ambient);
		super.loadVector(location_spotLightDiffuse, diffuse);
		super.loadVector(location_spotLightSpecular, specular);
		super.loadFloat(location_spotLightConstant, constant);
		super.loadFloat(location_spotLightLinear, linear);
		super.loadFloat(location_spotLightQuadratic, quadratic);
		super.loadFloat(location_spotLightCutOff, cutOff);
		super.loadFloat(location_spotLightOuterCutOff,outerCutOff);
		super.loadVector(location_viewPos,viewPos);
	}


	public void connectTextureUnits(){
		super.loadInt(location_backgroundTexture, 0);
		super.loadInt(location_rTexture, 1);
		super.loadInt(location_gTexture, 2);
		super.loadInt(location_bTexture, 3);
		super.loadInt(location_blendMap, 4);
		super.loadInt(location_shadowMap, 5);
	}

	public void loadToShadowSpaceMatrix(Matrix4f matrix){
		super.loadMatrix(location_toShadowMapSpace, matrix);
	}

	public void loadSkyColour(float r,float g, float b){
		super.loadVector(location_skyColour,new Vector3f(r,g,b));
	}

	public void loadShineVariables(float damper,float reflectivity){
		super.loadFloat(location_shineDamper, damper);
		super.loadFloat(location_reflectivity, reflectivity);
	}
	
	public void loadTransformationMatrix(Matrix4f matrix){
		super.loadMatrix(location_transformationMatrix, matrix);
	}

	public void loadLights(List<Light> lights) {
		for (int i = 0; i < MAX_LIGHT; i++) {
			if (i < lights.size()) {
				super.loadVector(location_lightPosition[i], lights.get(i).getPosition());
				super.loadVector(location_lightColour[i], lights.get(i).getColour());
				super.loadVector(location_attenuation[i],lights.get(i).getAttenuation());
			} else {
				super.loadVector(location_lightPosition[i], new Vector3f(0, 0, 0));
				super.loadVector(location_lightColour[i], new Vector3f(0, 0, 0));
				super.loadVector(location_attenuation[i],new Vector3f(1,0,0));
			}
		}
	}

	public void loadViewMatrix(Camera camera){
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
	
	public void loadProjectionMatrix(Matrix4f projection){
		super.loadMatrix(location_projectionMatrix, projection);
	}
	

}
