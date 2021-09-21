package ooo.thesledgehammer.groovymodels.experimental.gom

import ooo.thesledgehammer.groovymodels.api.gom.Gom
import ooo.thesledgehammer.groovymodels.api.gom.GomPart

interface IGroovysonObjectModelProvider<O extends Gom, P extends GomPart> {

    P getGomPart(String name);

    Map<String, P> getGomParts();

    Object getGomTexture(String name);

    Map<String, Object> getGomTextures();
}