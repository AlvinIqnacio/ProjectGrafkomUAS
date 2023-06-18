package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {



	private Vector3f position = new Vector3f(0,15,0);
	private float pitch = 10;
	private float yaw ;
	private float roll;

	private Player player;
	private float distanceFromPlayer = 75;
	private float angelFromPlayer = 0;


	
	public Camera(Player player){
		this.player = player;
	}
	
	public void move(){
		calculateZoom();
		calculatePitch();
		calculateAngleAroundPlayer();
		float horizontalDistance =calculateHorizontalDistance();
		float verticalDistance =calculateVerticallDistance();
		calculateCameraPosition(horizontalDistance,verticalDistance);
		this.yaw = 180 - (player.getRotY()+angelFromPlayer);
		if (position.y<0){
			position.y = 1;
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
		position.y = player.getPosition().y + vertical + 5;

	}

	private float calculateHorizontalDistance(){
		return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}
	private float calculateVerticallDistance(){
		return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}

	private void calculateZoom(){
		if (distanceFromPlayer>200){
			distanceFromPlayer=200;
		}else if (distanceFromPlayer<50){
			distanceFromPlayer=50;
		}else {
			float zoomLevel = Mouse.getDWheel() *0.1f;
			distanceFromPlayer -= zoomLevel;
		}

	}

	private void calculatePitch(){
		if (Mouse.isButtonDown(1)){
			float pitchChange= Mouse.getDY() * 0.1f;
			pitch -= pitchChange;
		}
	}

	private void calculateAngleAroundPlayer(){
		if (Mouse.isButtonDown(0)){
			float angleChange = Mouse.getDX() *0.3f;
			angelFromPlayer -= angleChange;
		}
	}
	
	

}
