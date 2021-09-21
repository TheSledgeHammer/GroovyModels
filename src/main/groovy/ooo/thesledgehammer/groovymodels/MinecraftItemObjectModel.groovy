package ooo.thesledgehammer.groovymodels

import net.minecraft.resources.ResourceLocation
import ooo.thesledgehammer.groovymodels.api.gom.GomItemProvider

class MinecraftItemObjectModel extends MinecraftObjectModel<GomItemProvider> {

    MinecraftItemObjectModel(GomItemProvider provider, ResourceLocation resourceLocation) {
        super(provider, resourceLocation);
    }

    MinecraftItemObjectModel(GomItemProvider provider, String namespaceIn, String pathIn) {
        super(provider, namespaceIn, pathIn)
    }

    MinecraftItemObjectModel(GomItemProvider provider, String resourceName) {
        super(provider, resourceName)
    }
}
