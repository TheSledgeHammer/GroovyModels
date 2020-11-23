/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: Converted to .groovy
 */

package com.thesledgehammer.groovymodels.api.client.json

import groovy.json.JsonException
import net.minecraft.client.Minecraft
import net.minecraft.resources.IResource
import net.minecraft.util.ResourceLocation

import java.nio.charset.StandardCharsets

class ResourceLoader {

    private final Set<ResourceLocation> loaded = new HashSet<>();
    private final Deque<ResourceLocation> loadingStack = new ArrayDeque<>();

    InputStreamReader startLoading(ResourceLocation location) throws IOException {
        if (!loaded.add(location)) {
            throw new JsonException("Already loaded " + location + " from " + loadingStack.peek())
        }
        loadingStack.push(location);
        IResource res =  Minecraft.getInstance().getResourceManager().getResource(location);
        return new InputStreamReader(res.getInputStream(), StandardCharsets.UTF_8);
    }

    InputStreamReader startLoading(String namespace, String path) throws IOException {
        ResourceLocation location = new ResourceLocation(namespace, path);
        if (!loaded.add(location)) {
            throw new JsonException("Already loaded " + location + " from " + loadingStack.peek())
        }
        loadingStack.push(location);
        IResource res = Minecraft.getInstance().getResourceManager().getResource(location);
        return new InputStreamReader(res.getInputStream(), StandardCharsets.UTF_8);
    }

    void finishLoading() {
        loadingStack.pop();
    }
}
