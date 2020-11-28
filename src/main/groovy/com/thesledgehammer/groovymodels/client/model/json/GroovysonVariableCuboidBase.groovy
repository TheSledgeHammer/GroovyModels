/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: Several changes made for use in GroovyMC. Renamed from JsonVariableCuboidBase. Converted to .groovy
 */

package com.thesledgehammer.groovymodels.client.model.json

import com.thesledgehammer.groovymodels.client.definitions.GroovyDefinitionContext
import com.thesledgehammer.groovymodels.client.model.ModelUtil
import com.thesledgehammer.groovymodels.client.model.MutableQuad
import com.thesledgehammer.groovymodels.utils.JsonTools
import com.thesledgehammer.groovymodels.utils.MathTools
import net.minecraft.util.Direction

import javax.vecmath.Vector3f

abstract class GroovysonVariableCuboidBase {

    protected GroovyDefinitionContext GDC = GroovyDefinitionContext.Instance();

    GroovysonVariableCuboidBase(List<GroovysonObjectPart> objectParts) {
        for(GroovysonObjectPart parts : objectParts) {
            if(parts != null) {
                GDC.setVariableFrom(parts, "0");
                GDC.setVariableTo(parts, "0");
                GDC.setVariableShade(parts, "true");
                GDC.setVariableVisible(parts, "true");
                GDC.setVariableLight(parts, "0");
                GDC.setVariableColour(parts, "-1");
            }
        }
    }

    void addQuads(GroovysonObjectPart parts, List<MutableQuad> addTo, ITextureGetter spriteLookup) {
        if (GDC.getVariableVisible(parts).getValue()) {
            float[] from = JsonTools.bakePosition(GDC.getVariableFrom(parts));
            float[] to = JsonTools.bakePosition(GDC.getVariableTo(parts));
            boolean shade = GDC.getVariableShade(parts).getValue();
            int l = (int) (GDC.getVariableLight(parts).getValue() & 15);
            int rgba = MathTools.swapARGBforABGR((int) GDC.getVariableColour(parts).getValue());
            for (Direction face : Direction.values()) {
                VariableFaceData data = getFaceData(parts, face, spriteLookup);
                if (data != null) {
                    Vector3f radius = new Vector3f(to[0] - from[0] as float, to[1] - from[1] as float, to[2] - from[2] as float);
                    radius.scale(0.5f);
                    Vector3f center = new Vector3f(from);
                    center.add(radius);
                    MutableQuad quad = ModelUtil.createFace(face, center, radius, data.uvs);
                    quad.rotateTextureUp(data.rotations);
                    quad.lighti(l, 0);
                    quad.colouri(rgba);
                    quad.texFromSprite(data.sprite);
                    quad.setSprite(data.sprite);
                    quad.setShade(shade);
                    if (data.bothSides) {
                        addTo.add(quad.copyAndInvertNormal());
                    } else if (data.invertNormal) {
                        quad = quad.copyAndInvertNormal();
                    }
                    addTo.add(quad);
                }
            }
        }
    }

    protected abstract VariableFaceData getFaceData(GroovysonObjectPart objectPart, Direction side, ITextureGetter spriteLookup);
}

