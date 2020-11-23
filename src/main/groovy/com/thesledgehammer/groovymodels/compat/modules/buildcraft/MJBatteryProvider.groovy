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
package com.thesledgehammer.groovymodels.compat.modules.buildcraft

//import buildcraft.api.mj.*

/*
@Optional.InterfaceList(
        value = [
                @Optional.Interface(iface = "buildcraft.api.mj.IMjPassiveProvider", modid = "buildcraft"),
                @Optional.Interface(iface = "buildcraft.api.mj.IMjReadable", modid = "buildcraft")
        ]
)
class MJBatteryProvider implements IMjPassiveProvider, IMjReadable, ICapabilitySerializable<NBTTagCompound> {

    private final MjBattery battery;
    protected final MjCapabilityHelper mjCaps;

    MJBatteryProvider(long capacity) {
        this.battery = new MjBattery(capacity);
        this.mjCaps = new MjCapabilityHelper(this);
    }

    MJBatteryProvider(MjBattery battery) {
        this.battery = battery;
        this.mjCaps = new MjCapabilityHelper(this);
    }

    @Override
    long extractPower(long min, long max, boolean simulate) {
        return battery.extractPower(min, max);
    }

    @Override
    long getStored() {
        return battery.getStored();
    }

    @Override
    long getCapacity() {
        return battery.getCapacity();
    }

    @Override
    boolean canConnect(@Nonnull IMjConnector other) {
        return true;
    }

    //writeNBT
    @Override
    NBTTagCompound serializeNBT() {
        return battery.serializeNBT();
    }

    //readNBT
    @Override
    void deserializeNBT(NBTTagCompound nbt) {
        battery.deserializeNBT(nbt);
    }

    @Override
    boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        if(BuildcraftModule.hasMjCapability(capability)) {
            return mjCaps.hasCapability(capability, facing);
        }
        return false
    }

    @Override
    def <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if(BuildcraftModule.hasMjCapability(capability)) {
            if(mjCaps.hasCapability(capability, facing)) {
                return mjCaps.getCapability(capability, facing);
            }
        }
        return null
    }
}
*/