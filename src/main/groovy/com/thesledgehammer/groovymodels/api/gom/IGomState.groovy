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

package com.thesledgehammer.groovymodels.api.gom

interface IGomState {

    void setGomState(String mPath, String mVersion, String mType);

    String getName();

    def getObjectState();

    def getVariants();

    def getVariantName(String name);

    ArrayList<String> getVariantsFromName(String name);

    def getVariantModelProperties(String name, int index);

    def getVariantModelXRotationProperties(String name, int index);

    def getVariantModelYRotationProperties(String name, int index);

    def getVariantModelUVLockProperties(String name, int index);

    def getVariantModelWeightProperties(String name, int index);

    def getVariantModelBlock(String name, int index);

    def getVariantModelXRotationOnBlock(String name, int index);

    def getVariantModelYRotationOnBlock(String name, int index);

    def getVariantModelUVLockOnBlock(String name, int index);

    def getMultipart();

    ArrayList<String> getMultipartWhen();

    ArrayList<String> getMultipartWhenOR();

    def getMultipartWhenORFace(int index, String face);

    ArrayList<String> getMultipartApply();
}