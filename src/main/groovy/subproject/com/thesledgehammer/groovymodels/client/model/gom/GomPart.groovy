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

package subproject.com.thesledgehammer.groovymodels.client.model.gom

import net.minecraft.util.Direction

abstract class GomPart implements IGomPart {

    private Gom gom;
    private def part;

    GomPart(Gom gom, String index) {
        this.gom = gom;
        this.part = gom.elements.getAt(index);
    }

    @Override
    String getPartName() {
        return part.name;
    }

    @Override
    ArrayList<Float> From() {
        ArrayList<Float> arrPart = new ArrayList<>();
        for(int i = 0; i < part.from.size; i++) {
            arrPart.add(i, part.from.get(i));
        }
        return arrPart;
    }

    @Override
    ArrayList<Float> To() {
        ArrayList<Float> arrPart = new ArrayList<>();
        for(int i = 0; i < part.to.size; i++) {
            arrPart.add(i, part.to.get(i));
        }
        return arrPart;
    }

    @Override
    def Rotation() {
        return part.rotation;
    }

    @Override
    ArrayList<Float> RotationOrigin() {
        ArrayList<Float> arrPart = new ArrayList<>();
        for(int i = 0; i < part.rotation.origin.size; i++) {
            arrPart.add(i, part.rotation.origin.get(i));
        }
        return arrPart;
    }

    @Override
    ArrayList<Float> RotationAxis() {
        ArrayList<Float> arrPart = new ArrayList<>();
        for(int i = 0; i < part.rotation.axis.size; i++) {
            arrPart.add(i, part.rotation.axis.get(i));
        }
        return arrPart;
    }

    @Override
    ArrayList<Float> RotationAngle() {
        ArrayList<Float> arrPart = new ArrayList<>();
        for(int i = 0; i < part.rotation.angle.size; i++) {
            arrPart.add(i, part.rotation.angle.get(i));
        }
        return arrPart;
    }

    @Override
    boolean RotationRescale() {
        return part.rotation.rescale;
    }

    @Override
    boolean Shade() {
        return part.shade;
    }

    @Override
    def Faces() {
        return part.faces;
    }

    @Override
    def Facing(Direction face) {
        String faces = face.getName().toLowerCase();
        if(part.faces.get(faces) == null) {
            throw new Exception("Current Face does not exist...!");
        }
        return part.faces.get(faces);
    }

    @Override
    ArrayList<Float> FacingUv(Direction face) {
        ArrayList<Float> arrPart = new ArrayList<>();
        def uvFace = null;
        if(Facing(face) != null) {
            uvFace = Facing(face);
        }
        if(uvFace != null) {
            for(int i = 0; i < uvFace.uv.size; i++) {
                arrPart.add(i, uvFace.uv.get(i));
            }
        }
        return arrPart;
    }

    @Override
    def TextureFace(Direction face) {
        return Facing(face).texture;
    }

    @Override
    def CullFaceFace(Direction face) {
        return Facing(face).cullface;
    }

    @Override
    def FacingRotation(Direction face, int fallback) {
        if(Facing(face).rotation == null) {
            return fallback;
        }
        return Facing(face).rotation;
    }

    @Override
    def FacingTint(Direction face, int fallback) {
        if(Facing(face).tintindex == null || Facing(face) == null) {
            return fallback;
        }
        return Facing(face).tintindex;
    }

    @Override
    String BlockRenderType() {
        return part.render;
    }

    @Override
    def BlockRenderType(String renderType) {
        String render = renderType.toLowerCase();
        if (part.render.get(render) == null) {
            throw new Exception("The element does not contain a render of: ${renderType}");
        }
        return part.render.get(render);
    }

    @Override
    def Light() {
        return part.light;
    }

    @Override
    def Colour() {
        return part.colour;
    }

    @Override
    def Visible() {
        return part.visible;
    }

    @Override
    def Invert() {
        return part.invert;
    }

    @Override
    def BothSides() {
        return part.bothsides;
    }
}
