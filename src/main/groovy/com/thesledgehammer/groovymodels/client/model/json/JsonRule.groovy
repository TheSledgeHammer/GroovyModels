/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: All Static Inner Classes are referenced or altered from BC's JsonModelRule
 */

package com.thesledgehammer.groovymodels.client.model.json

import com.thesledgehammer.groovymodels.client.model.MutableQuad
import com.thesledgehammer.groovymodels.utils.ListTools
import com.thesledgehammer.groovymodels.utils.StringTools
import com.thesledgehammer.groovymodels.utils.variables.VariableBoolean
import com.thesledgehammer.groovymodels.utils.variables.VariableDouble
import com.thesledgehammer.groovymodels.utils.variables.VariableObject
import net.minecraft.util.Direction
import net.minecraft.util.math.MathHelper

//Mostly Complete: Origin & To from rules
abstract class JsonRule {

    private static GroovysonObject groovysonObject;
    private static VariableBoolean when;

    JsonRule(VariableBoolean when) {
        setWhen(when);
    }

    private static void setWhen(VariableBoolean when) {
        this.when = when;
    }

    static VariableBoolean getWhen() {
        return when;
    }

    private static def getRuleWhen() {
        return groovysonObject.getRulesByName("when");
    }

    private static def getRuleType() {
        return groovysonObject.getRulesByName("type");
    }

    private static def getRuleFrom() {
        return groovysonObject.getRulesByName("from");
    }

    private static def getRuleTo() {
        return groovysonObject.getRulesByName("to");
    }

    private static def getRuleOrigin() {
        return groovysonObject.getRulesByName("origin");
    }

    private static def getRuleAngle() {
        return groovysonObject.getRulesByName("angle");
    }

    private static def getRuleScale() {
        return groovysonObject.getRulesByName("scale");
    }

    static JsonRule SetRules(GroovysonObject groovysonObject) {
        this.groovysonObject = groovysonObject;

        String when = getRuleWhen();
        VariableBoolean nodeWhen = new VariableBoolean(Boolean.valueOf(when));

        String builtin = getRuleType();
        if(StringTools.contains(builtin, "rotate_facing")) {

            String from = getRuleFrom();
            String fromValue = StringTools.stringToEnum(from, Direction.class).toUpperCase();
            VariableObject<Direction> nodeFrom = new VariableObject<>();
            if(StringTools.contains(from, fromValue)) {
                nodeFrom.setValue(Direction.valueOf(fromValue));
            }

            String to = getRuleTo();
            String toValue = StringTools.stringToEnum(to, Direction.class).toUpperCase();
            VariableObject<Direction> nodeTo = new VariableObject<>();
            if(StringTools.contains(to, toValue)) {
                nodeTo.setValue(Direction.valueOf(toValue));
            }

            String origin = getRuleOrigin();
            ArrayList<String> rotateFacingOrigin = ListTools.StringToList(origin);
            VariableDouble[] nodeOrigin = new VariableDouble[3];

            for(int i = 0; i < 3; i++) {
                if (nodeOrigin[i] == null) {
                    nodeOrigin = RotateFacing.DEFAULT_ORIGIN;
                } else {
                    //nodeOrigin[i].setValue(Double.valueOf(list.get(i)));
                }
            }
            return new RotateFacing(nodeWhen, nodeFrom, nodeTo, nodeOrigin);
        } else if(StringTools.contains(builtin, "rotate")) {

            String origin = getRuleOrigin();
            ArrayList<String> rotateOrigin = ListTools.StringToList(origin);
            VariableDouble[] nodeOrigin = new VariableDouble[3];

            for(int i = 0; i < 3; i++) {
                if (nodeOrigin[i] == null) {
                    nodeOrigin = Rotate.DEFAULT_ORIGIN;
                } else {
                    //nodeOrigin[i].setValue(Double.valueOf(list.get(i)));
                }
            }

            String angle = getRuleAngle();
            VariableDouble[] nodeAngle = new VariableDouble[3];
            ArrayList<String> alist = ListTools.StringToList(angle);
            for (int i = 0; i < 3; i++) {
                nodeAngle[i].setValue(Double.valueOf(alist.get(i)));
            }
            return new Rotate(nodeWhen, nodeOrigin, nodeAngle);
        } else if(StringTools.contains(builtin, "scale")) {

            String origin = getRuleOrigin();
            ArrayList<String> scaleOrigin = ListTools.StringToList(origin);
            VariableDouble[] nodeOrigin = new VariableDouble[3];

            for(int i = 0; i < 3; i++) {
                if (nodeOrigin[i] == null) {
                    nodeOrigin = Scale.DEFAULT_ORIGIN;
                } else {
                    //nodeOrigin[i].setValue(Double.valueOf(list.get(i)));
                }
            }

            String scale = getRuleScale();
            ArrayList<String> sList = ListTools.StringToList(scale);
            VariableDouble[] nodeScale = new VariableDouble[3];
            for (int i = 0; i < 3; i++) {
                nodeScale[i].setValue(Double.valueOf(sList.get(i)));
            }
            return new Scale(nodeWhen, nodeOrigin, nodeScale);
        } else {
            throw new Exception("Unknown built in rule type ${builtin}");
        }
    }

    abstract void apply(List<MutableQuad> quads);

    static class RotateFacing extends JsonRule {

        private static final VariableDouble CONST_ORIGIN = new VariableDouble(8);
        static final VariableDouble[] DEFAULT_ORIGIN = [CONST_ORIGIN, CONST_ORIGIN, CONST_ORIGIN];

        protected final VariableObject<Direction> from;
        protected final VariableObject<Direction> to;
        protected final VariableDouble[] origin;

        RotateFacing(VariableBoolean when, VariableObject<Direction> from, VariableObject<Direction> to, VariableDouble[] origin) {
            super(when);
            this.from = from;
            this.to = to;
            this.origin = origin;
        }

        @Override
        void apply(List<MutableQuad> quads) {
            Direction faceFrom = from.getValue();
            Direction faceTo = to.getValue();

            if(faceFrom == faceTo) {
                return;
            }

            float ox = origin[0].getValue() / 16f as float;
            float oy = origin[1].getValue() / 16f as float;
            float oz = origin[2].getValue() / 16f as float;

            for(MutableQuad q : quads) {
                q.rotate(faceFrom, faceTo, ox, oy, oz);
            }
        }

        @Override
        String toString() {
            return "when: ${when}, from: ${from}, to: ${to}, origin: ${origin}"
        }
    }

    static class Rotate extends JsonRule {

        private static final VariableDouble CONST_ORIGIN = new VariableDouble(0.5);
        static final VariableDouble[] DEFAULT_ORIGIN = [CONST_ORIGIN, CONST_ORIGIN, CONST_ORIGIN];

        protected final VariableDouble[] origin;
        protected final VariableDouble[] angle;

        Rotate(VariableBoolean when, VariableDouble[] origin, VariableDouble[] angle) {
            super(when);
            this.origin = origin;
            this.angle = angle;
        }

        @Override
        void apply(List<MutableQuad> quads) {
            float ox = origin[0].getValue() / 16f as float;
            float oy = origin[1].getValue() / 16f as float;
            float oz = origin[2].getValue() / 16f as float;

            float ax = Math.toRadians(angle[0].getValue()) as float;
            float ay = Math.toRadians(angle[1].getValue()) as float;
            float az = Math.toRadians(angle[2].getValue()) as float;

            if (ax == 0 && ay == 0 && az == 0) {
                return;
            }

            float cx = MathHelper.cos(ax);
            float cy = MathHelper.cos(ay);
            float cz = MathHelper.cos(az);

            float sx = MathHelper.sin(ax);
            float sy = MathHelper.sin(ay);
            float sz = MathHelper.sin(az);

            for(MutableQuad q : quads) {
                q.translatef(-ox, -oy, -oz);
                if(cx != 1) {
                    q.rotateDirectlyX(cx, sx);
                }
                if(cy != 1) {
                    q.rotateDirectlyY(cy, sy);
                }
                if(cz != 1) {
                    q.rotateDirectlyZ(cz, sz);
                }
                q.translatef(ox, oy, oz);
            }
        }

        @Override
        String toString() {
            return "when: ${when}, origin: ${origin}, angle: ${angle}"
        }
    }

    static class Scale extends JsonRule {

        private static final VariableDouble CONST_ORIGIN = new VariableDouble(0.5);
        static final VariableDouble[] DEFAULT_ORIGIN = [CONST_ORIGIN, CONST_ORIGIN, CONST_ORIGIN];

        protected final VariableDouble[] origin;
        protected final VariableDouble[] scale;

        Scale(VariableBoolean when, VariableDouble[] origin, VariableDouble[] scale) {
            super(when);
            this.origin = origin;
            this.scale = scale;
        }

        @Override
        void apply(List<MutableQuad> quads) {
            float ox = origin[0].getValue() / 16f as float;
            float oy = origin[1].getValue() / 16f as float;
            float oz = origin[2].getValue() / 16f as float;

            float sx = scale[0].getValue() as float;
            float sy = scale[1].getValue() as float;
            float sz = scale[2].getValue() as float;

            if(sx == 1 && sy == 1 && sz == 1) {
                return;
            }

            for(MutableQuad q : quads) {
                q.translatef(-ox, -oy, -oz);
                q.scalef(sx, sy, sz);
                q.translatef(ox, oy, oz);
            }
        }

        @Override
        String toString() {
            return "when: ${when}, origin: ${origin}, scale: ${scale}"
        }
    }
}
