package com.thesledgehammer.groovymodels.api.gom
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

import com.thesledgehammer.groovymodels.config.Constants
import com.thesledgehammer.groovymodels.experimental.Log
import com.thesledgehammer.groovymodels.experimental.StringTools

abstract class GomState implements IGomState {

    private final SuperGom.Serializer instance = new SuperGom.Serializer();
    private def state;
    private String name;
    private static final String defaultpath = Constants.RESOURCE_PATH;
    private static final String defaultversion = SuperGom.CURRENT_VERSION;
    private static final String defaulttype = "json";

    GomState() {
        this(defaultpath, defaultversion, defaulttype);
    }

    GomState(String mType) {
        this(defaultpath, defaultversion, mType);
    }

    GomState(String mPath, String mType) {
        this(mPath, defaultversion, mType);
    }

    /**
     *
     * @param mPath: Resource Location path to .gom file
     * @param mVersion: gom file version in use
     * @param mType: gom file type. json, xml or yaml
     * Empty parameters will return the default values.
     */
    GomState(String mPath, String mVersion, String mType) {
        setGomState(mPath, mVersion, mType);
    }

    @Override
    void setGomState(String mPath, String mVersion, String mType) {
        this.state = instance.ReadGom(mPath, mVersion, mType);
        String[] temp = StringTools.split(mPath, "/");
        this.name = temp[temp.length - 1];
    }

    @Override
    String getName() {
        return name;
    }

    @Override
    def getObjectState() {
        return state;
    }

    @Override
    def getVariants() {
        if(state.variants == null) {
            return new Exception("${state.variants} Isn't defined in ${getName()}");
        }
        return state.variants;
    }

    @Override
    def getVariantName(String name) {
        if(state.variants.get(name) == null) {
            Log.logError("${name} is incorrect...!");
            return null;
        }
        return state.variants.get(name);
    }

    @Override
    ArrayList<String> getVariantsFromName(String name) {
        ArrayList<String> arrObj = new ArrayList<>();
        if(state.variants.get(name) == null) {
            throw new Exception("${name} is incorrect...!");
        }
        for(int i = 0; i < state.variants.get(name).size; i++) {
            arrObj.add(state.variants.get(name).get(i));
        }
        return arrObj;
    }

    @Override
    def getVariantModelProperties(String name, int index) {
        return getVariantsFromName(name).get(index).model;
    }

    @Override
    def getVariantModelXRotationProperties(String name, int index) {
        return getVariantsFromName(name).get(index).x;
    }

    @Override
    def getVariantModelYRotationProperties(String name, int index) {
        return getVariantsFromName(name).get(index).y;
    }

    @Override
    def getVariantModelUVLockProperties(String name, int index) {
        return getVariantsFromName(name).get(index).uvlock;
    }

    @Override
    def getVariantModelWeightProperties(String name, int index) {
        return getVariantsFromName(name).get(index).weight;
    }

    @Override
    def getVariantModelBlock(String name, int index) {
        return getVariantsFromName(name).get(index).model;
    }

    @Override
    def getVariantModelXRotationOnBlock(String name, int index) {
        return getVariantsFromName(name).get(index).x;
    }

    @Override
    def getVariantModelYRotationOnBlock(String name, int index) {
        return getVariantsFromName(name).get(index).y;
    }

    @Override
    def getVariantModelUVLockOnBlock(String name, int index) {
        return getVariantsFromName(name).get(index).uvlock;
    }

    @Override
    def getMultipart() {
        if(obj.multipart == null) {
            return new Exception("${state.multipart} Isn't defined in ${getName()}");
        }
        return obj.multipart;
    }

    @Override
    ArrayList<String> getMultipartWhen() {
        ArrayList<String> arrObj = new ArrayList<>();
        for(int i = 0; i < obj.multipart.size; i++) {
            arrObj.add(state.multipart.get(i).when);
        }
        return arrObj;
    }

    @Override
    ArrayList<String> getMultipartWhenOR() {
        ArrayList<String> arrObj = new ArrayList<>();
        for(int i = 0; i < obj.multipart.size; i++) {
            arrObj.add(state.multipart.get(i).when.OR);
        }
        return arrObj;
    }

    @Override
    def getMultipartWhenORFace(int index, String face) {
        return getMultipartWhenOR().get(index).getAt(face)
    }

    @Override
    ArrayList<String> getMultipartApply() {
        ArrayList<String> arrObj = new ArrayList<>();
        for(int i = 0; i < state.multipart.size; i++) {
            arrObj.add(state.multipart.get(i).apply);
        }
        return arrObj;
    }
}