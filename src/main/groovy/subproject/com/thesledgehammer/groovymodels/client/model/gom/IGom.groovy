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

/* GOM (Groovy Object Model Interface) */
interface IGom {

    /* GOM format version */
    String getGOMVersion();

    String getName();

    void readParts(List<GomPart> parts);

    void readTextures(List<String> textures);

    /* retrieve GOM object */
    def getGOM();

    def getParent();

    def getTextures();

    def getTexturesByName(String name);

    def getItemTextureLayer(int index);

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

    /* Translation Rotation Scale Rotation(TRSR) */
    def Display();

    def DisplayName(String name);

    ArrayList<Float> Translation(String name);

    ArrayList<Float> Rotation(String name);

    ArrayList<Float> Scale(String name);

    /* Item Models Only */
    def Overrides();

    def OverridesPredicate();

    def OverridesModel();
}