package ooo.thesledgehammer.groovymodels.api.gom

class GomItemProvider extends GomProvider {

    private GomItem item;

    GomItemProvider(GomItem item, String elem) {
        super(item, elem);
        this.item = item;
    }

    @Override
    void readParts(Map<String, GomPart> parts) {
        for(String elem : item.elements) {
            parts.put(elem, new GomItemProvider(item, elem));
        }
    }

    @Override
    void readTextures(Map<String, Object> gomTexture) {
        for(String texture : item.textures) {
            gomTexture.put(texture, item.getTextures());
        }
    }

    GomItem getGomItem() {
        return item;
    }
}
