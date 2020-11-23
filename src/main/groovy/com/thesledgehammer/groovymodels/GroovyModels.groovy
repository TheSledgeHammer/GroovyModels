package com.thesledgehammer.groovymodels


import net.minecraftforge.fml.ModContainer
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Mod(GroovyModels.MOD_ID)
class GroovyModels {

	static final String MOD_ID = "groovymc";
	static GroovyModels INSTANCE;
	static ModContainer MOD_CONTAINER;

	static final Logger LOGGER = LogManager.getLogger();

	GroovyModels() {
		INSTANCE = this;
		MOD_CONTAINER = ModLoadingContext.get().getActiveContainer();

		//ModuleContainerManager.preInit();
		//ModuleContainerManager.Init();
		//ModuleContainerManager.postInit();
		//Registry.init();
	}


	//GroovyLoader Example:
	//private static final GroovyLoader groovyLoader = new GroovyLoader(Constants.MOD_PATH, Constants.RESOURCE_PATH, Constants.GROOVY_JVM, Constants.URL, Constants.MOD_ID);
}
