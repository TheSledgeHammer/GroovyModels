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
package com.thesledgehammer.groovymodels.client.definitions.json

import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.utils.variables.*
import net.minecraft.util.Direction

interface IVariableKey {

    void setFrom(GroovysonObjectPart part, String newValue);

    void setTo(GroovysonObjectPart part, String newValue);

    void setUV(GroovysonObjectPart part, String newValue);

    void setShade(GroovysonObjectPart part, String newValue);

    void setVisible(GroovysonObjectPart part, String newValue);

    void setColour(GroovysonObjectPart part, String newValue);

    void setLight(GroovysonObjectPart part, String newValue);

    void setInvert(GroovysonObjectPart part, String newValue);

    void setBothSides(GroovysonObjectPart part, String newValue);

    void setTexture(GroovysonObjectPart part);

    void setTextureRotation(GroovysonObjectPart part, String newValue);

    List<VariableDouble> getFrom(GroovysonObjectPart part);

    List<VariableDouble> getTo(GroovysonObjectPart part);

    List<VariableDouble> getFaceUV(GroovysonObjectPart part, Direction face);

    VariableBoolean getShade(GroovysonObjectPart part);

    VariableBoolean getVisible(GroovysonObjectPart part);

    VariableLong getColour(GroovysonObjectPart part);

    VariableLong getLight(GroovysonObjectPart part);

    VariableBoolean getInvert(GroovysonObjectPart part);

    VariableBoolean getBothSides(GroovysonObjectPart part);

    VariableObject<String> getTexture(GroovysonObjectPart part, Direction face);

    VariableLong getTextureRotation(GroovysonObjectPart part, Direction face);

    VariableInteger AssignVariableInteger(String newValue, List<String> arr, int index, String inputVariable);

    VariableLong AssignVariableLong(String newValue, List<String> arr, int index, String inputVariable)

    VariableFloat AssignVariableFloat(String newValue, List<String> arr, int index, String inputVariable);

    VariableDouble AssignVariableDouble(String newValue, List<String> arr, int index, String inputVariable);

    VariableBoolean AssignVariableBoolean(boolean newValue, String inputVariable);

    VariableObject<String> AssignVariableString(String newValue, String inputVariable);
}