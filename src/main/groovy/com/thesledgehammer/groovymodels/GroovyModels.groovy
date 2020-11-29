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

package com.thesledgehammer.groovymodels

import com.thesledgehammer.groovymodels.config.Constants
import net.minecraftforge.fml.ModContainer
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import subproject.com.thesledgehammer.groovymodels.client.model.gom.SuperGom

@Mod(Constants.MOD_ID)
class GroovyModels {

	static GroovyModels INSTANCE;
	static ModContainer MOD_CONTAINER;

	static final Logger LOGGER = LogManager.getLogger();

	GroovyModels() {
		INSTANCE = this;
		MOD_CONTAINER = ModLoadingContext.get().getActiveContainer();
		SuperGom.init();
	}
	//GroovyLoader Example:
	//private static final GroovyLoader groovyLoader = new GroovyLoader(Constants.MOD_PATH, Constants.RESOURCE_PATH, Constants.GROOVY_JVM, Constants.URL, Constants.MOD_ID);
}

/*
Todo:
  - Abstraction of gson to jsonsluper/jsonparser & vice-versa
Key Minecraft Resource Components:
  - ResourceLocation, ResourceLocationException, TranslationTextComponent, ICustomModelLoader, ISelectiveResourceReloadListener, IResourceManagerReloadListener, IResourceManager, IResource, IResourceType
 */