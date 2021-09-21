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

package ooo.thesledgehammer.groovymodels.api.gom

import net.minecraft.core.Direction
import ooo.thesledgehammer.groovymodels.api.gom.interfaces.IGomPart

abstract class GomPart implements IGomPart {

    //private Gom gom;
    private GomItem item;
    private GomBlock block;
    private def part;
/*
    GomPart(Gom gom, String elem) {
        this.gom = gom;
        this.part = gom.getGOM()elements[elem];
    } */

    GomPart(GomItem item, String elem) {
        this.item = item;
        this.part = item.elements[elem];
    }

    GomPart(GomBlock block, String elem) {
        this.block = block;
        this.part = block.elements[elem];
    }
    
    @Override
    String getPartName() {
        return part.name;
    }

    @Override
    ArrayList<Float> From() {
        ArrayList<Float> arrPart = new ArrayList<>();
        for(int i = 0; i < part.from.size; i++) {
            Float from = part.from.get(i);
            arrPart.add(i, from);
        }
        return arrPart;
    }

    @Override
    ArrayList<Float> To() {
        ArrayList<Float> arrPart = new ArrayList<>();
        for(int i = 0; i < part.to.size; i++) {
            Float to = part.to.get(i);
            arrPart.add(i, to);
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
            Float origin = part.rotation.origin.get(i);
            arrPart.add(i, origin);
        }
        return arrPart;
    }

    @Override
    ArrayList<Float> RotationAxis() {
        ArrayList<Float> arrPart = new ArrayList<>();
        for(int i = 0; i < part.rotation.axis.size; i++) {
            Float axis = part.rotation.axis.get(i);
            arrPart.add(i, axis);
        }
        return arrPart;
    }

    @Override
    ArrayList<Float> RotationAngle() {
        ArrayList<Float> arrPart = new ArrayList<>();
        for(int i = 0; i < part.rotation.angle.size; i++) {
            Float angle = part.rotation.angle.get(i);
            arrPart.add(i, angle);
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
        String faces = face.toString().toLowerCase();
        if(part.faces.get(faces) == null) {
            throw new Exception("Current Face does not exist...!");
        }
        return part.faces.get(faces);
    }

    @Override
    ArrayList<Float> FacingUv(Direction face) {
        String faces = face.toString().toLowerCase();
        ArrayList<Float> arrPart = new ArrayList<>();
        def uvFace = part.faces.get(faces).uv;
        if(uvFace != null) {
            for(int i = 0; i < uvFace.size; i++) {
                Float uv =  uvFace.get(i);
                arrPart.add(i, uv);
            }
        }
        return arrPart;
    }

    @Override
    def TextureFace(Direction face) {
        String faces = face.toString().toLowerCase();
        def texture = part.faces.get(faces).texture;
        return texture;
    }

    @Override
    def CullFaceFace(Direction face) {
        String faces = face.toString().toLowerCase();
        def cull = part.faces.get(faces).cullface;
        return cull;
    }

    @Override
    def FacingRotation(Direction face, int fallback) {
        String faces = face.toString().toLowerCase();
        def rotation = part.faces.get(faces).rotation;
        if(rotation == null) {
            return fallback;
        }
        return rotation;
    }

    @Override
    def FacingTint(Direction face, int fallback) {
        String faces = face.toString().toLowerCase();
        def tintindex = part.faces.get(faces).tintindex;
        if(tintindex == null || Facing(face) == null) {
            return fallback;
        }
        return tintindex;
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
