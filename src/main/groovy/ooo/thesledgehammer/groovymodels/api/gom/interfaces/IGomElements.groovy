package ooo.thesledgehammer.groovymodels.api.gom.interfaces

interface IGomElements extends IGom {

    def getElements();

    def getElementPart(int index);

    boolean getShade();

    def getVariables();

    def getVariableByName(String variableName);

    def getRules();

    def getRulesByName(String ruleName);

    def getValues();

    def getValueByName(def valueName);

    boolean AmbientOcclusion();
}