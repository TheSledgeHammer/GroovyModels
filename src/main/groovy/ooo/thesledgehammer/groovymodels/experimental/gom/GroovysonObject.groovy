/*
 * Copyright [2018] [TheSledgeHammer]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ooo.thesledgehammer.groovymodels.experimental.gom

import ooo.thesledgehammer.groovymodels.api.gom.Gom
import ooo.thesledgehammer.groovymodels.api.gom.GomPart
import ooo.thesledgehammer.groovymodels.experimental.GroovyResourceLocation

abstract class GroovysonObject extends Gom {

    private GroovyResourceLocation resourceLocation;

    GroovysonObject(GroovyResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    GroovysonObject(String namespaceIn, String pathIn) {
        this(new GroovyResourceLocation(namespaceIn, pathIn));
    }

    GroovysonObject(String resourceName) {
        this(new GroovyResourceLocation(resourceName));
    }

    @Override
    void readParts(Map<String, GomPart> parts) {
        for(String elem : this.elements) {
            parts.put(elem, new GroovysonObjectPart(this, elem))
        }
    }

    @Override
    void readTextures(Map<String, Object> gomTexture) {
        for(String texture : this.textures) {
            gomTexture.put(texture, this.getTextures());
        }
    }
}
