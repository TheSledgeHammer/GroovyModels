package ooo.thesledgehammer.groovymodels

import net.minecraft.resources.ResourceLocation
import ooo.thesledgehammer.groovymodels.api.gom.GomProvider

class MinecraftObjectModel extends MinecraftObject<GomProvider> {

    MinecraftObjectModel(GomProvider provider, ResourceLocation resourceLocation) {
        super(provider, resourceLocation);
    }

    MinecraftObjectModel(GomProvider provider, String namespaceIn, String pathIn) {
        super(provider, namespaceIn, pathIn)
    }

    MinecraftObjectModel(GomProvider provider, String resourceName) {
        super(provider, resourceName)
    }
}
