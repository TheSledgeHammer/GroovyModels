package com.thesledgehammer.groovymodels.experimental.gom

import com.thesledgehammer.groovymodels.api.gom.Gom
import com.thesledgehammer.groovymodels.api.gom.GomPart


class GomBlock extends Gom {

    private final Map<String, GomPart> oParts = new HashMap<>();

    GomBlock(String type) {
        super(type);
        createBlockParts(oParts);
    }

    @Override
    void readParts(Map<String, GomPart> parts) {
        for(String elem : this.elements) {
            parts.put(elem, new GomBlockPart(this, elem));
        }
    }

    @Override
    void readTextures(Map<String, Object> gomTexture) {

    }

    static class GomBlockPart extends GomPart {

        GomBlockPart(Gom gom, String elem) {
            super(gom, elem)
        }
    }

    void createBlockParts(Map<String, GomPart> parts) {
        for(String elem : this.elements) {
            parts.put(elem, new GomBlockPart(this, elem));
        }
    }

    Map<String, GomPart> getBlockParts() {
        return oParts;
    }
}
