package entities;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
import terrains.Terrain;

import java.util.List;

public class Camera {

	private float sensitivityX =0.3f;
	private float sensitivityY =0.1f;
	private float sensitivityZoom =0.05f;



	private Vector3f position = new Vector3f(0,0,0);
	private float pitch = 10;
	private float yaw ;
	private float roll;


	private Player player;
	private float distanceFromPlayer = 75;
	private float angelFromPlayer = 0;
	private float minDistance = 25;
	private float maxDistance = 100;
	private float minAngle = -90;
	private float maxAngle = 90;


	private float cameraHeightFromPlayer = 5;
	private boolean firstPersonView = false;


	boolean isCollide= false;

	public Camera(Player player){
		this.player = player;
	}
	
	public void move(Terrain terrain, List<Entity> entities){
		if (Keyboard.isKeyDown(Keyboard.KEY_F5)) {
			minDistance = 0;
			maxDistance = 0;
			cameraHeightFromPlayer = 10;
			firstPersonView = true;
		}else if (Keyboard.isKeyDown(Keyboard.KEY_F1)){
			minDistance = 25;
			maxDistance = 100;
			cameraHeightFromPlayer = 5;
			firstPersonView = false;
		}
		calculateZoom();
		calculatePitch();
		calculateAngleAroundPlayer();
		float horizontalDistance =calculateHorizontalDistance();
		float verticalDistance =calculateVerticallDistance();
		calculateCameraPosition(horizontalDistance,verticalDistance);
		this.yaw = 180 - (player.getRotY()+angelFromPlayer);

		if (position.y<terrain.getHeightOfTerain(position.x,position.z)){
			position.y = terrain.getHeightOfTerain(position.x,position.z) + 1;
		}
		for (Entity entity : entities) {
			isCollide = entity.detectCollision(new Vector3D(getPosition().x, getPosition().y, getPosition().z), entity.getModel().getRawModel(), 1);
			if (isCollide) {
				break;
			}
		}


	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}

	private void calculateCameraPosition(float horizontal, float vertical){
		float theta = player.getRotY() + angelFromPlayer;
		float offsetX = (float) (horizontal * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (horizontal * Math.cos(Math.toRadians(theta)));
		position.x = player.getPosition().x - offsetX;
		position.z = player.getPosition().z - offsetZ;
		position.y = player.getPosition().y + vertical + cameraHeightFromPlayer;

	}

	private float calculateHorizontalDistance(){
		return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}
	private float calculateVerticallDistance(){
		return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}

	private void calculateZoom(){
		if (distanceFromPlayer>maxDistance){
			distanceFromPlayer=maxDistance;
		}else if (distanceFromPlayer<minDistance){
			distanceFromPlayer=minDistance;
		}else {
			float zoomLevel = Mouse.getDWheel() * sensitivityZoom;
			distanceFromPlayer -= zoomLevel;
		}

	}

	private void calculatePitch(){
		if (firstPersonView) {
			if (pitch > maxAngle) {
				pitch = maxAngle;
			} else if (pitch < minAngle) {
				pitch = minAngle;
			} else {
				if (Mouse.isButtonDown(0)){
					float pitchChange= Mouse.getDY() * sensitivityY;
					pitch -= pitchChange;
				}
			}
		}else {
			if (Mouse.isButtonDown(0)){
				float pitchChange= Mouse.getDY() * sensitivityY;
				pitch -= pitchChange;
			}
		}

	}

	private void calculateAngleAroundPlayer(){
		if (firstPersonView) {
			if (angelFromPlayer > maxAngle) {
				angelFromPlayer = maxAngle;
			} else if (angelFromPlayer < minAngle) {
				angelFromPlayer = minAngle;
			} else {
				if (Mouse.isButtonDown(0)) {
					float angleChange = Mouse.getDX() * sensitivityX;
					angelFromPlayer -= angleChange;
				}
			}
		}else {
			if (Mouse.isButtonDown(0)) {
				float angleChange = Mouse.getDX() * sensitivityX;
				angelFromPlayer -= angleChange;
			}
		}
	}
	
	

}
