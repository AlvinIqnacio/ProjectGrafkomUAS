package entities;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import terrains.Terrain;
import toolbox.Maths;

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

	private float lastDirectionx;
	private float lastDirectiony;

	private float cameraHeightFromPlayer = 5;
	private boolean firstPersonView = false;

	private Matrix4f matrix4f;
	private Vector3f direction;

	boolean isCollide= false;

	public Camera(Player player){
		this.player = player;
		matrix4f = Maths.createTransformationMatrix(getPosition(),
				player.getRotX(),player.getRotY(), player.getRotZ(),player.getScale());
		direction = new Vector3f();
	}
	
	public void move(Terrain terrain, List<Entity> entities){
		for (Entity entity : entities) {
			isCollide = entity.detectCollision(new Vector3D(getPosition().x, getPosition().y, getPosition().z), entity.getModel().getRawModel(), 5);
			if (isCollide) {
				System.out.println(true);
				break;
			}
		}
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

		matrix4f = Maths.createTransformationMatrix(getPosition(),
				player.getRotX(),player.getRotY(), player.getRotZ(),player.getScale());

		direction.x =matrix4f.m20;
		direction.y =matrix4f.m10 - 0.1f;
		direction.z = matrix4f.m00;
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

	public Vector3f getDirection() {
		return direction;
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
			if (!isCollide) {
				if (Mouse.isButtonDown(0)) {
					float pitchChange = Mouse.getDY() * sensitivityY;
					pitch -= pitchChange;
					lastDirectiony = pitchChange;
				}
			}else {
				if (Mouse.isButtonDown(0)) {
					float pitchChange = Mouse.getDY() * sensitivityY;
					if (pitchChange < 0) {
						pitch += pitchChange;
					} else {
						pitch += pitchChange;
					}
				}

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
			if (!isCollide) {
				if (Mouse.isButtonDown(0)) {
					float angleChange = Mouse.getDX() * sensitivityX;
					angelFromPlayer -= angleChange;
					lastDirectionx = angleChange;
				}
			}else {
				if (Mouse.isButtonDown(0)) {
					float angleChange = Mouse.getDX() * sensitivityX;
					if (angleChange<0){
						angelFromPlayer +=angleChange;
					}else {
						angelFromPlayer +=angleChange;
					}
				}
			}

		}
	}
	
	

}
