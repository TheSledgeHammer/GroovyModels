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

package ooo.thesledgehammer.groovymodels.api.gom.interfaces

import net.minecraft.core.Direction

interface IGomPart {

    String getPartName();

    ArrayList<Float> From();

    ArrayList<Float> To();

    def Rotation();

    ArrayList<Float> RotationOrigin();

    ArrayList<Float> RotationAxis();

    ArrayList<Float> RotationAngle();

    boolean RotationRescale();

    boolean Shade();

    def Faces();

    def Facing(Direction face);

    ArrayList<Float> FacingUv(Direction face);

    def TextureFace(Direction face);

    def CullFaceFace(Direction face);

    def FacingRotation(Direction face, int fallback);

    def FacingTint(Direction face, int fallback);

    String BlockRenderType();

    def BlockRenderType(String renderType);

    def Light();

    def Colour();

    def Visible();

    def Invert();

    def BothSides();
}