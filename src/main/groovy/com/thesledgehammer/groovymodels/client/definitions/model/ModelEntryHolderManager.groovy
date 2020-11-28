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

package com.thesledgehammer.groovymodels.client.definitions.model

import com.thesledgehammer.groovymodels.api.IInitModel
import com.thesledgehammer.groovymodels.api.client.ISprite
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.model.IBakedModel
import net.minecraft.client.renderer.texture.AtlasTexture
import net.minecraft.client.renderer.texture.TextureManager
import net.minecraft.util.ResourceLocation
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.event.ModelBakeEvent

//import net.minecraft.client.renderer.texture.TextureMap

@OnlyIn(Dist.CLIENT)
class ModelEntryHolderManager /*implements IModelEntryHolderManager*/ {

    private static final ModelEntryHolderManager instance = new ModelEntryHolderManager();

    private final Set<IInitModel> INIT_MODELS = new HashSet<>();

    private final List<ModelEntryHolder> HOLDERS = new ArrayList<>();
    private final List<ModelEntry> MODEL_ENTRIES = new ArrayList<>();
    final List<TextureEntry> TEXTURE_ENTRIES = new ArrayList<>();

    static ModelEntryHolderManager Instance() {
        return instance;
    }

    List<ModelEntryHolder> ENTRY_HOLDERS() {
        return HOLDERS;
    }
/*
    @Override
    void initModel(Item item, int meta, ModelResourceLocation modelResourceLocation) {
        ModelLoader.setCustomModelResourceLocation(item, meta, modelResourceLocation);
    }

    @Override
    void initModel(Item item, int meta, String resourceLocation) {
        ModelLoader.setCustomModelResourceLocation(item, meta, getModelLocation(resourceLocation));
    }

    @Override
    void initModel(Item item, int meta, String domain, String path) {
        ModelLoader.setCustomModelResourceLocation(item, meta, getModelLocation(domain, path));
    }

    @Override
    ModelResourceLocation getModelLocation(String domain, String path) {
        ResourceLocation location = new ResourceLocation(domain, path);
        ModelResourceLocation modelLocation = new ModelResourceLocation(location, "inventory");
        return modelLocation;
    }

    @Override
    ModelResourceLocation getModelLocation(String resourceLocation) {
        ResourceLocation location = new ResourceLocation(resourceLocation);
        ModelResourceLocation modelLocation = new ModelResourceLocation(location, "inventory");
        return modelLocation;
    }

    @OnlyIn(Dist.CLIENT)
    void initModels() {
        for(IInitModel initModel : initModels) {
            Item item = null;
            if(initModel instanceof Block) {
                item = Item.getItemFromBlock((Block) initModel);
            } else if(initModel instanceof Item) {
                item = (Item) initModel;
            }
            if(item != null) {
                //initModel.initModel(item, this);
                initModel.initModel();
            }
        }
    }
*/
    void registerModelEntry(ModelEntry model) {
        this.MODEL_ENTRIES.add(model);
    }

    void registerTextureEntry(TextureEntry texture) {
        this.TEXTURE_ENTRIES.add(texture);
    }

    void onTextureStitchPre(AtlasTexture map) {
        TextureManager textureManager = Minecraft.getInstance().getTextureManager();
        Set<ResourceLocation> toStitch = new HashSet<>();
        if(hasEntries()) {
            for (ModelEntryHolder holder : ENTRY_HOLDERS()) {
                holder.onTextureStitchPre(toStitch);
                for(TextureEntry entry : TEXTURE_ENTRIES) {
                    if(entry.getTextureAtlasSprite() instanceof ISprite) {
                        entry.onTextureStitchPre(map);
                    }
                    textureManager.bindTexture(entry.getSpriteResourceLocation());
                }
            }
        }
    }

    void onModelBake(ModelBakeEvent event) {
        Map<ResourceLocation, IBakedModel> registry = event.getModelRegistry();
        if(hasEntries()) {
            for (ModelEntryHolder holder : ENTRY_HOLDERS()) {
                holder.onModelBake();

                for(final ModelEntry entry : MODEL_ENTRIES) {
                    registry.put(entry.getModelResourceLocation(), entry.getBakedModel());
                }
            }
        }
    }

    private boolean hasEntries() {
        if(!ENTRY_HOLDERS().isEmpty()) {
            return true;
        }
        return false;
    }
}