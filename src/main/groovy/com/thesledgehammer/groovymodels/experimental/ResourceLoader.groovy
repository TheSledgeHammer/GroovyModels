package com.thesledgehammer.groovymodels.experimental

import net.minecraft.client.Minecraft
import net.minecraft.resources.IResource

import java.nio.charset.StandardCharsets

class ResourceLoader {

    private final Set<GroovyResourceLocation> loaded = new HashSet<>();
    private final Deque<GroovyResourceLocation> loadingStack = new ArrayDeque<>();

    InputStreamReader startLoading(GroovyResourceLocation location) throws IOException {
        if (!loaded.add(location)) {
            throw new Exception("Already loaded " + location + " from " + loadingStack.peek())
        }
        loadingStack.push(location);
        IResource res =  Minecraft.getInstance().getResourceManager().getResource(location);
        return new InputStreamReader(res.getInputStream(), StandardCharsets.UTF_8);
    }

    InputStreamReader startLoading(String namespace, String path) throws IOException {
        GroovyResourceLocation location = new GroovyResourceLocation(namespace, path);
        if (!loaded.add(location)) {
            throw new Exception("Already loaded " + location + " from " + loadingStack.peek())
        }
        loadingStack.push(location);
        IResource res = Minecraft.getInstance().getResourceManager().getResource(location);
        return new InputStreamReader(res.getInputStream(), StandardCharsets.UTF_8);
    }

    void finishLoading() {
        loadingStack.pop();
    }
}
