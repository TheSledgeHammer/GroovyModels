package ooo.thesledgehammer.groovymodels.api.gom

import ooo.thesledgehammer.groovymodels.api.gom.interfaces.IGomBlock
import ooo.thesledgehammer.groovymodels.api.gom.interfaces.IGomElements
import ooo.thesledgehammer.groovymodels.api.gom.interfaces.IGomTRSR

class GomBlock extends Gom implements IGomBlock, IGomElements, IGomTRSR {

    GomBlock(String mType) {
        super(mType)
    }

    GomBlock(String mPath, String mType) {
        super(mPath, mType)
    }

    GomBlock(String mPath, String mVersion, String mType) {
        super(mPath, mVersion, mType)
    }

    /* Elements & Rules */
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

    /* Translation Rotation Scale Rotation(TRSR) */
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
}
