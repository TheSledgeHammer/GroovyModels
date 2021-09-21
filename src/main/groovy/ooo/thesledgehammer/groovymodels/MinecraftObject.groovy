package ooo.thesledgehammer.groovymodels

import net.minecraft.resources.ResourceLocation
import ooo.thesledgehammer.groovymodels.api.gom.GomPart
import ooo.thesledgehammer.groovymodels.api.gom.GomProvider

abstract class MinecraftObject<GOM extends GomProvider> {

    private GOM provider;
    private ResourceLocation resourceLocation;
    private final Map<String, GomPart> oParts = new HashMap<>();
    private final Map<String, Object> oTextures = new HashMap<>();

    MinecraftObject(GOM provider, ResourceLocation resourceLocation) {
        this.provider = provider;
        this.resourceLocation = resourceLocation;
        provider.readParts(oParts);
        provider.readTextures(oTextures);
    }

    MinecraftObject(GOM provider, String namespaceIn, String pathIn) {
        this(provider, new ResourceLocation(namespaceIn, pathIn));
    }

    MinecraftObject(GOM provider, String resourceName) {
        this(provider, new ResourceLocation(resourceName));
    }

    GOM getProvider() {
        return provider;
    }
}
