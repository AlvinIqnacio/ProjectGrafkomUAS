package entities;

import models.RawModel;
import models.TexturedModel;

import objConverter.Vertex;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import toolbox.Maths;

import java.util.List;


public class Entity {

	private TexturedModel model;
	private Vector3f position;
	private float rotX, rotY, rotZ;
	private float scale;


	private int textureIndex = 0;


	public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ,
			float scale) {
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	}

	public Entity(TexturedModel model,int index,Vector3f position, float rotX, float rotY, float rotZ,
				  float scale) {
		this.textureIndex = index;
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	}

	public float getTextureXOffset(){
		int column = textureIndex%model.getTexture().getNumberOfRows();
		return (float) column/(float) model.getTexture().getNumberOfRows();
	}

	public float getTextureYOffset(){
		int row = textureIndex/model.getTexture().getNumberOfRows();
		return (float) row/(float) model.getTexture().getNumberOfRows();
	}

	public void increasePosition(float dx, float dy, float dz) {
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
	}

	public void increaseRotation(float dx, float dy, float dz) {
		this.rotX += dx;
		this.rotY += dy;
		this.rotZ += dz;
	}

	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public double sdfTriangle(Vector3D p, Vector3D a, Vector3D b, Vector3D c) {
		Vector3D ba = b.subtract(a);
		Vector3D pa = p.subtract(a);
		Vector3D cb = c.subtract(b);
		Vector3D pb = p.subtract(b);
		Vector3D ac = a.subtract(c);
		Vector3D pc = p.subtract(c);
		Vector3D nor = ba.crossProduct(ac);


		return Math.sqrt((Math.signum(dot3(cross3(ba, nor), pa)) +
				Math.signum(dot3(cross3(cb, nor), pb)) +
				Math.signum(dot3(cross3(ac, nor), pc)) < 2.0) ?
				Math.min(Math.min(
						dot2(subtract2(multiply2(ba, constrain(dot3(ba, pa) / dot2(ba), 0.0, 1.0)), pa)),
						dot2(subtract2(multiply2(cb, constrain(dot3(cb, pb) / dot2(cb), 0.0, 1.0)), pb)) ),
						dot2(subtract2(multiply2(ac, constrain(dot3(ac, pc) / dot2(ac), 0.0, 1.0)), pc)) ) :
				dot3(nor, pa) * dot3(nor, pa) / dot2(nor));
	}

	public double dot2(Vector3D v) {
		return dot3(v, v);
	}

	public double dot3(Vector3D v1, Vector3D v2) {
		return v1.dotProduct(v2);
	}

	public Vector3D cross3(Vector3D v1, Vector3D v2) {
		return v1.crossProduct(v2);
	}

	public Vector3D subtract2(Vector3D v1, Vector3D v2) {
		return v1.subtract(v2);
	}

	public Vector3D multiply2(Vector3D v, double scalar) {
		return v.scalarMultiply(scalar);
	}

	public double constrain(double value, double min, double max) {
		return Math.min(Math.max(value, min), max);
	}

	public boolean detectCollision(Vector3D p, RawModel o, double thresh) {
		List<Vertex> vertices = o.getVertices();
		List<Integer> indicies = o.getIndices();

		for (int i = 0; i < indicies.size()/3; i++) {
			Vector3D a = new Vector3D(
					vertices.get(indicies.get(i)).getPosition().x + position.x,
					vertices.get(indicies.get(i)).getPosition().y + position.y,
					vertices.get(indicies.get(i)).getPosition().z + position.z
					);
			Vector3D b = new Vector3D(
					vertices.get(indicies.get(i+1)).getPosition().x + position.x,
					vertices.get(indicies.get(i+1)).getPosition().y + position.y,
					vertices.get(indicies.get(i+1)).getPosition().z + position.z
			);
			Vector3D c = new Vector3D(
					vertices.get(indicies.get(i+2)).getPosition().x + position.x,
					vertices.get(indicies.get(i+2)).getPosition().y + position.y,
					vertices.get(indicies.get(i+2)).getPosition().z + position.z
			);
			if (sdfTriangle(p, a, b, c) < thresh) {
				return true;
			}
		}
		return false;
	}



}
