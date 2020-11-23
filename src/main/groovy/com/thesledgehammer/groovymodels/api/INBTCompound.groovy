package com.thesledgehammer.groovymodels.api

import net.minecraft.nbt.CompoundNBT

interface INBTCompound {

    void read(CompoundNBT tag);

    CompoundNBT write(CompoundNBT tag);
}