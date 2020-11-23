package com.thesledgehammer.groovymodels.experimental

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonSerializationContext
import com.thesledgehammer.groovymc.client.model.json.GroovysonObject
import net.minecraft.util.JSONUtils
import net.minecraft.util.ResourceLocation

import java.lang.reflect.Type

class ResourceLocationSerializer extends ResourceLocation.Serializer {

    @Override
    ResourceLocation deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new ResourceLocation(JSONUtils.getString(json ,"location"))
    }

    @Override
    JsonElement serialize(ResourceLocation src, Type typeOfSrc, JsonSerializationContext context) {
        return new GsonPrimitive(src.toString()) as JsonElement;
    }

    static class Serialize implements GroovysonSerializer<GroovysonObject>, GroovysonDeserializer<GroovysonObject> {

    }
}

/*
Todo:
  - Abstraction of gson to jsonsluper/jsonparser & vice-versa
Key Minecraft Resource Components:
  - ResourceLocation, ResourceLocationException, TranslationTextComponent, ICustomModelLoader, ISelectiveResourceReloadListener, IResourceManagerReloadListener, IResourceManager, IResource, IResourceType
 */