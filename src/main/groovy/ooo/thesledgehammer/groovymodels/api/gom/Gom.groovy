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

import ooo.thesledgehammer.groovymodels.api.gom.interfaces.IGom
import ooo.thesledgehammer.groovymodels.config.Constants
import ooo.thesledgehammer.groovymodels.utils.StringTools

abstract class Gom implements IGom {

    private final SuperGom.Serializer instance = new SuperGom.Serializer();
    protected def obj;
    private String name;
    private static final String defaultpath = Constants.RESOURCE_PATH;
    private static final String defaultversion = Constants.GOM_VERSION;
    private static final String defaulttype = Constants.GOM_TYPE_DEFAULT;
/*
    Gom() {
        setGom(defaultpath, defaultversion, defaulttype);
        setName(defaultpath);
    }
    */

    Gom(String mType) {
        setGom(defaultpath, defaultversion, mType);
        setName(defaultpath);
    }

    Gom(String mPath, String mType) {
        setGom(mPath, defaultversion, mType);
        setName(mPath);
    }

    /**
     *
     * @param mPath: Resource Location path to .gom file
     * @param mVersion: gom file version in use
     * @param mType: gom file type. json, xml or yaml
     * Empty parameters will return the default values.
     */
    Gom(String mPath, String mVersion, String mType) {
        setGom(mPath, mVersion, mType);
        setName(mPath);
    }

    private void setName(String mPath) {
        String[] temp = StringTools.split(mPath, "/");
        this.name = temp[temp.length - 1];
    }

    private Object createGom(String mPath, String mVersion, String mType) {
        def gom = instance.ReadGom(mPath, mVersion, mType);
        return (gom.gom);
    }

    @Override
    void setGom(String mPath, String mVersion, String mType) {
        this.obj = createGom(mPath, mVersion, mType);
    }

    @Override
    String getGOMVersion() {
        return obj.modelversion;
    }

    @Override
    String getName() {
        return name;
    }

    @Override
    def getGOM() {
        return obj;
    }

    @Override
    def getParent() {
        if(obj.parent == null) {
            return new Exception("Parent isn't defined in ${getName()}")
        }
        return obj.parent;
    }

    @Override
    def getTextures() {
        return obj.textures;
    }

    @Override
    def getTexturesByName(String name) {
        return obj.textures.get(name);
    }
/*
    @Override
    def getItemTextureLayer(int index) {
        return obj.textures.getAt("layer${index}");
    }

    @Override
    def getElements() {
        return obj.elements;
    }

    @Override
    def getElementPart(int index) {
        return obj.elements.get(index);
    }

    @Override
    boolean getShade() {
        return obj.shade
    }

    @Override
    def getVariables() {
        return obj.variables;
    }

    @Override
    def getVariableByName(String variableName) {
        return obj.variables.get(variableName);
    }

    @Override
    def getRules() {
        return obj.rules;
    }

    @Override
    def getRulesByName(String ruleName) {
        return getRules()[ruleName];
    }

    @Override
    def getValues() {
        return  obj.values;
    }

    @Override
    def getValueByName(Object valueName) {
        return obj.values.get(valueName);
    }

    @Override
    boolean AmbientOcclusion() {
        return obj.ambientocclusion;
    }
*/
    /* Translation Rotation Scale Rotation(TRSR) */
    /*
    @Override
    def Display() {
        return obj.display;
    }

    @Override
    def DisplayName(String name) {
        if(obj.display.get(name) == null) {
            return new Exception("${name} is incorrect...!");
        }
        return obj.display.get(name);
    }

    @Override
    ArrayList<Float> Translation(String name) {
        ArrayList<Float> arrObj = new ArrayList<>();
        if(obj.display.get(name) == null) {
            throw new Exception("${name} is incorrect...!")
        }
        if(obj.display.get(name).translation == null) {
            throw new Exception("${name} does not contain Translation...!");
        }
        for(int i = 0; i < obj.display.get(name).translation.size; i++) {
            Float trans = obj.display.get(name).translation.get(i);
            arrObj.add(i, trans);
        }
        return arrObj;
    }

    @Override
    ArrayList<Float> Rotation(String name) {
        ArrayList<Float> arrObj = new ArrayList<>();
        if(obj.display.get(name) == null) {
            throw new Exception("${name} is incorrect...!");
        }
        if(obj.display.get(name).rotation == null) {
            throw new Exception("${name} does not contain Rotation...!");
        }
        for(int i = 0; i < obj.display.get(name).rotation.size; i++) {
            Float rot = obj.display.get(name).rotation.get(i);
            arrObj.add(i, rot);
        }
        return arrObj;
    }

    @Override
    ArrayList<Float> Scale(String name) {
        ArrayList<Float> arrObj = new ArrayList<>();
        if(obj.display.get(name) == null) {
            throw new Exception("${name} is incorrect...!");
        }
        if(obj.display.get(name).scale == null) {
            throw new Exception("${name} does not contain Scale...!");
        }
        for(int i = 0; i < obj.display.get(name).scale.size; i++) {
            Float scale = obj.display.get(name).scale.get(i);
            arrObj.add(i, scale);
        }
        return arrObj;
    }

    @Override
    def Overrides() {
        return obj.overrides;
    }

    @Override
    def OverridesPredicate() {
        return obj.overrides.predicate;
    }

    @Override
    def OverridesModel() {
        return obj.overrides.model;
    }
    */
}
