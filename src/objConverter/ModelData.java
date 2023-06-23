package objConverter;

import java.util.ArrayList;
import java.util.List;

public class ModelData {

	private float[] vertices;
	private float[] textureCoords;
	private float[] normals;
	private int[] indices;
	private float furthestPoint;
	List<Vertex> verticesModel = new ArrayList<Vertex>();
	List<Integer> indicesModel = new ArrayList<Integer>();



	public ModelData(float[] vertices, float[] textureCoords, float[] normals, int[] indices,
			float furthestPoint,List<Vertex> verticesModel, List<Integer> indicesModel) {
		this.vertices = vertices;
		this.textureCoords = textureCoords;
		this.normals = normals;
		this.indices = indices;
		this.furthestPoint = furthestPoint;
		this.verticesModel = verticesModel;
		this.indicesModel = indicesModel;
	}

	public float[] getVertices() {
		return vertices;
	}

	public float[] getTextureCoords() {
		return textureCoords;
	}

	public float[] getNormals() {
		return normals;
	}

	public int[] getIndices() {
		return indices;
	}

	public float getFurthestPoint() {
		return furthestPoint;
	}

	public List<Vertex> getVerticesModel() {
		return verticesModel;
	}

	public void setVerticesModel(List<Vertex> verticesModel) {
		this.verticesModel = verticesModel;
	}

	public List<Integer> getIndicesModel() {
		return indicesModel;
	}

	public void setIndicesModel(List<Integer> indicesModel) {
		this.indicesModel = indicesModel;
	}
}
