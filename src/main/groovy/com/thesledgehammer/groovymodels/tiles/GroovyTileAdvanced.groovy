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

package com.thesledgehammer.groovymodels.tiles

import com.thesledgehammer.groovymc.gui.inventory.InventoryAdaptor
import com.thesledgehammer.groovymc.tiles.traits.TileInventoryTraits
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.ISidedInventory
import net.minecraft.tileentity.TileEntityType
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.wrapper.InvWrapper
import net.minecraftforge.items.wrapper.SidedInvWrapper

import javax.annotation.Nonnull

class GroovyTileAdvanced extends GroovyTileBasic implements TileInventoryTraits {

    private IItemHandler itemHandler;
    private IItemHandler itemHandlerSided;

    GroovyTileAdvanced(TileEntityType tileEntityTypeIn) {
        super(tileEntityTypeIn)
        setTileEntity(this);
        setIInventory(new InventoryAdaptor());
    }

    @Override
    <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            for(Direction facing : Direction.values()) {
                if (facing == null) {
                    if (itemHandler == null) {
                        itemHandler = new InvWrapper((IInventory) this);
                    }
                    return (T) itemHandler as LazyOptional<T>;
                } else {
                    if (itemHandlerSided == null) {
                        itemHandlerSided = new SidedInvWrapper((ISidedInventory) this, facing);
                    }
                    return (T) itemHandlerSided as LazyOptional<T>;
                }
            }
        }
        return super.getCapability(capability)
    }
}
