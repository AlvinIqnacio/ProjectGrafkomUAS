package models;

import objConverter.Vertex;

import java.util.List;

public class RawModel {
	
	private int vaoID;
	private int vertexCount;
	private List<Vertex> vertices;
	private List<Integer> indices;
	
	public RawModel(int vaoID, int vertexCount){
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}

	public RawModel(int vaoID, int vertexCount, List<Vertex> vertices, List<Integer> indices){
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
		this.vertices = vertices;
		this.indices = indices;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public List<Vertex> getVertices() {
		return vertices;
	}

	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}

	public List<Integer> getIndices() {
		return indices;
	}

	public void setIndices(List<Integer> indices) {
		this.indices = indices;
	}
}
