package me.fzzyhmstrs.imbued_ascendancy.map

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.mojang.blaze3d.systems.RenderSystem
import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import me.fzzyhmstrs.imbued_ascendancy.IA
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.registry.Registries
import net.minecraft.resource.Resource
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import net.minecraft.util.math.ChunkPos

object MapData: SimpleSynchronousResourceReloadListener{

    override fun reload(manager: ResourceManager) {
            loadTextures(manager)
        }

        override fun getFabricId(): Identifier {
            return Identifier(IA.MOD_ID,"map_tiles_loader")
        }
        
        private fun loadTextures(manager: ResourceManager){
            BIOMES.clear()
            STRUCTURES.clear()
            manager.findResources("biome_textures") { path -> path.path.endsWith(".json") }
                .forEach { (t, u) ->
                    loadTexture(t,u, BIOMES)
            }
            manager.findResources("structure_textures") { path -> path.path.endsWith(".json") }
                .forEach { (t, u) ->
                    loadTexture(t,u, STRUCTURES)
            }
        }
        
        private fun loadTexture(resourceId: Identifier, resource: Resource, map: MutableMap<Identifier,Pair<Identifier,Identifier>>){
            val reader = resource.reader
            try{
                val json = JsonParser.parseReader(reader).asJsonObject
                for (el in json.entrySet()) {
                    if (!el.value.isJsonObject) {
                        println("element $el isn't a valid JsonObject")
                        continue
                    }
                    val obj = el.value.asJsonObject
                    val idKey = el.key
                    val id = Identifier.tryParse(idKey)
                    if (id == null) {
                        println("$idKey could not be parsed as a valid identifier")
                        continue
                    } else if (Registries.BIOME_SOURCE.get(id) == null) {
                        println("ID $id isn't a valid biome identifier")
                        continue
                    }
                    val textureEl = obj.get("texture")
                    if (textureEl == null || !textureEl.isJsonPrimitive) {
                        println("Texture value $textureEl isn't valid")
                        continue
                    }
                    val texture = Identifier.tryParse(textureEl.asString)
                    if (texture == null) {
                        println("Object $obj missing primary texture ID")
                        continue
                    }
                    val hcEl = obj.get("high_contrast")
                    if (hcEl == null || !hcEl.isJsonPrimitive) {
                        println("High constrast value $hcEl isn't valid")
                        continue
                    }
                    val highContrast = Identifier.tryParse(hcEl.asString) ?: texture
                    map[id] = Pair(texture, highContrast)

                }
            } catch(e: Exception){
                println("Resource $resourceId isn't valid JSON")
                e.printStackTrace()
            }
        }
        
        fun loadMapTiles(){
            
        }
        
        fun saveMapTiles(){
            
        }
        
        internal val BIOMES: MutableMap<Identifier,Pair<Identifier,Identifier>> = mutableMapOf()
        internal val STRUCTURES: MutableMap<Identifier,Pair<Identifier,Identifier>> = mutableMapOf()


}
