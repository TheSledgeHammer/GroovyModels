/*********************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 * Modified by TheSledgeHammer 2018: Converted to .groovy
 *********************************************************************************/

package com.thesledgehammer.groovymodels.blocks.properties

import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraft.client.renderer.tileentity.TileEntityRenderer
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

interface IMachinePropertiesTER<T extends GroovyTileBasic> extends IMachineProperties<T> {

    void setParticleTextureLocation(String particleTextureLocation);

    String getParticleTextureLocation();

    @OnlyIn(Dist.CLIENT)
    void setRenderer(TileEntityRenderer<? super T> renderer);
}