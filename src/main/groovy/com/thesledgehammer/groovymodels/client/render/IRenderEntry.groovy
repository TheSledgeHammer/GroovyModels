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

import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryHolder
import com.thesledgehammer.groovymc.client.model.MutableQuad
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

@OnlyIn(Dist.CLIENT)
interface IRenderEntry<M extends ModelEntryHolder, T extends TileEntity> {

    void render(T te, double x, double y, double z, float partialTicks, int destroyStage, float alpha);

    MutableQuad[] getQuads(M modelEntryHolder, T tile, float partialTicks);
}