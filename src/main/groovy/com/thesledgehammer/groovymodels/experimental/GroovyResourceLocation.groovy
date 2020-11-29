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

import com.thesledgehammer.groovymodels.utils.StringTools
import net.minecraft.util.ResourceLocation
import subproject.com.thesledgehammer.groovymodels.client.model.gom.Gom

class GroovyResourceLocation extends ResourceLocation {

    protected GroovyResourceLocation(String[] resourceParts) {
        super(resourceParts);
    }

    GroovyResourceLocation(String resourceName) {
        super(resourceName)
    }

    GroovyResourceLocation(String namespaceIn, String pathIn) {
        super(namespaceIn, pathIn)
    }

    static class GroovySerializer implements GomDeserializer<GroovyResourceLocation>, GomSerializer<GroovyResourceLocation> {

        @Override
        Gom serialize(GroovyResourceLocation src, String type, GomSerializationContext context) {
            return new GroovyObjectModel(src);
        }

        @Override
        GroovyResourceLocation deserialize(Gom src, String type, GomDeserializationContext context) {
            return new GroovyResourceLocation(StringTools.split(src.getGOM().getClass().getSimpleName(), "location"));
        }
    }
}
