package guis;

import org.lwjgl.util.vector.Vector2f;

public class GuiTexture {

    private int texture;
    private Vector2f postion;
    private Vector2f scale;

    public GuiTexture(int texture, Vector2f postion, Vector2f scale) {
        this.texture = texture;
        this.postion = postion;
        this.scale = scale;
    }

    public int getTexture() {
        return texture;
    }

    public Vector2f getPostion() {
        return postion;
    }

    public Vector2f getScale() {
        return scale;
    }
}
