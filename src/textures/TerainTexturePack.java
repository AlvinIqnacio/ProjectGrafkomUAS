package textures;

public class TerainTexturePack {

    private TerainTexture backgroundTexture;
    private TerainTexture rTexture;
    private TerainTexture gTexture;
    private TerainTexture bTexture;

    public TerainTexturePack(TerainTexture backgroundTexture, TerainTexture rTexture, TerainTexture gTexture, TerainTexture bTexture) {
        this.backgroundTexture = backgroundTexture;
        this.rTexture = rTexture;
        this.gTexture = gTexture;
        this.bTexture = bTexture;
    }

    public TerainTexture getBackgroundTexture() {
        return backgroundTexture;
    }

    public TerainTexture getrTexture() {
        return rTexture;
    }

    public TerainTexture getgTexture() {
        return gTexture;
    }

    public TerainTexture getbTexture() {
        return bTexture;
    }
}
