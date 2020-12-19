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
package com.thesledgehammer.groovymodels.experimental.gom

import com.thesledgehammer.groovymodels.experimental.GroovyResourceLocation

class GroovysonObjectModel extends GroovysonObject implements IGroovysonObjectModelProvider<GroovysonObject, GroovysonObjectPart>  {

    private final Map<String, GroovysonObjectPart> oParts = new HashMap<>();
    private final Map<String, Object> oTextures = new HashMap<>();

    GroovysonObjectModel(GroovyResourceLocation resourceLocation) {
        super(resourceLocation);
        readParts(oParts);
        readTextures(oTextures);
    }

    GroovysonObjectModel(String namespaceIn, String pathIn) {
        super(namespaceIn, pathIn);
        readParts(oParts);
        readTextures(oTextures);
    }

    GroovysonObjectModel(String resourceName) {
        super(resourceName);
        readParts(oParts);
        readTextures(oTextures);
    }

    @Override
    GroovysonObjectPart getGomPart(String name) {
        return oParts.get(name);
    }

    @Override
    Map<String, GroovysonObjectPart> getGomParts() {
        return oParts;
    }

    @Override
    Object getGomTexture(String name) {
        return oTextures.get(name);
    }

    @Override
    Map<String, Object> getGomTextures() {
        return oTextures;
    }
}
