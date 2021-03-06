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

package com.thesledgehammer.groovymodels.client.render

import com.thesledgehammer.groovymodels.api.modules.BlankRenderEventModule
import com.thesledgehammer.groovymodels.client.definitions.model.ModelEntryHolder
import net.minecraft.tileentity.TileEntity

abstract class RenderEntryFast<M extends ModelEntryHolder, T extends TileEntity> extends BlankRenderEventModule implements IRenderEntryFast<M, T> {

    private M modelEntry;

    RenderEntryFast(String modID, String eventName, M model) {
        super(modID, "renderEntryFast", eventName);
        this.modelEntry = model;
    }
}
