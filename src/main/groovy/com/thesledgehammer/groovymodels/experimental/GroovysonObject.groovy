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

package com.thesledgehammer.groovymodels.experimental

import subproject.com.thesledgehammer.groovymodels.client.model.gom.Gom
import subproject.com.thesledgehammer.groovymodels.client.model.gom.GomPart

class GroovysonObject extends Gom {

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
    void readParts(List<GomPart> parts) {
        for(String elem : this.elements) {
            parts.add(new GroovysonObjectPart(this, elem))
        }
    }

    @Override
    void readTextures(List<String> textures) {
        for(String texture : this.textures) {
            textures.add(texture);
        }
    }
}
