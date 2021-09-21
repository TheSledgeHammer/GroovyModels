package ooo.thesledgehammer.groovymodels.api.gom.interfaces

import ooo.thesledgehammer.groovymodels.api.gom.GomPart

interface IGomProvider {

    void readParts(Map<String, GomPart> parts);

    void readTextures(Map<String, Object> gomTexture);
}