package ooo.thesledgehammer.groovymodels.api.gom

class GomBlockProvider extends GomProvider {

    private GomBlock block;

    GomBlockProvider(GomBlock block, String elem) {
        super(block, elem);
        this.block = block;
    }

    GomBlock getGomBlock() {
        return block;
    }

    @Override
    void readParts(Map<String, GomPart> parts) {
        for(String elem : block.elements) {
            parts.put(elem, new GomBlockProvider(block, elem));
        }
    }

    @Override
    void readTextures(Map<String, Object> gomTexture) {
        for(String texture : block.textures) {
            gomTexture.put(texture, block.getTextures());
        }
    }
}
