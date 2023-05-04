package me.fzzyhmstrs.imbued_ascendancy.screen

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

class NotebookOfEchoesMapScreen(private val player: PlayerEntity, oldScreen: Screen): Screen(AcText.empty()){

    companion object: SimpleSynchronousResourceReloadListener {
        
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
        
        internal val WIDGETS = Identifier(IA.MOD_ID,"textures/gui/map_widgets.png")
        internal val MAP_TEXTURES: Array<Identifier> = listOf(
            Identifier(IA.MOD_ID,"textures/gui/map_background_1.png"),
            Identifier(IA.MOD_ID,"textures/gui/map_background_2.png"),
            Identifier(IA.MOD_ID,"textures/gui/map_background_3.png"),
            Identifier(IA.MOD_ID,"textures/gui/map_background_4.png"),
            Identifier(IA.MOD_ID,"textures/gui/map_background_5.png"),
            Identifier(IA.MOD_ID,"textures/gui/map_background_6.png")
            ).toTypedArray()
        private val LOADED_TILES: MutableMap<ChunkPos, BaseTile> = mutableMapOf()
        private val TILES: Array<Array<BaseTile>> = arrayOf()
        internal val BIOMES: MutableMap<Identifier,Pair<Identifier,Identifier>> = mutableMapOf()
        internal val STRUCTURES: MutableMap<Identifier,Pair<Identifier,Identifier>> = mutableMapOf()
    }
    
    private var chunkHeight = 15
    private var chunkWidth = 20
    private val chunkList: MutableList<ChunkPos> = mutableListOf()
    
    override fun init() {
        val originPos = player.chunkPos
        chunkHeight = height / 16
        if (height % 16 != 0) chunkHeight += 1
        val chunkHeightRange = if(chunkHeight % 2 != 0){
            (-chunkHeight/2)..(chunkHeight/2)
        } else {
            (-chunkHeight/2)..((chunkHeight/2)-1)
        }
        chunkWidth = width / 16
        if (width % 16 != 0) chunkWidth += 1
        val chunkWidthRange = if(chunkWidth % 2 != 0){
            (-chunkWidth/2)..(chunkWidth/2)
        } else {
            (-chunkWidth/2)..((chunkWidth/2)-1)
        }
        val list: MutableList<Identifier>
        for (xx in chunkWidthRange){
            for (yy in chunkHeightRange){
                
            }
        }
        
        
    }
    
    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(matrices)
        super.render(matrices, mouseX, mouseY, delta)
    }
    
    private fun getVisibleTiles(): List<Tile>{
        TODO()
    }
    
    
    private class Tile(private val texture: Identifier, private val highCon: Identifier, private val u: Int, private val v: Int): BaseTile(){
    
        override fun draw(matrices: MatrixStack, x: Int, y: Int, hc: Boolean) {
            RenderSystem.setShader { GameRenderer.getPositionTexProgram() }
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
            RenderSystem.setShaderTexture(0, if (hc) highCon else texture)
            drawTexture(matrices, x, y, u.toFloat(), v.toFloat(),16,16, 64, 64)
        }
        
        override fun toJson(): JsonObject{
            val json = JsonObject()
            json.addProperty("type", BASIC)
            json.addProperty("t", texture.toString())
            json.addProperty("hc",highCon.toString())
            json.addProperty("u",v)
            json.addProperty("v",v)
            return json
        }
    }
    
    private open class BaseTile(){
    
        open fun draw(matrices: MatrixStack, x: Int, y: Int, hc: Boolean) {}
        
        open fun toJson(): JsonObject{
            val json = JsonObject()
            json.addProperty("type", EMPTY)
            return json
        }
    
        companion object{
            protected val EMPTY_TILE = BaseTile()
            val EMPTY = "empty"
            val BASIC = "basic"
        
            internal fun fromJson(json: JsonElement): BaseTile {
                if (!json.isJsonObject) return EMPTY_TILE
                val obj = json.asJsonObject
                val typeEl = obj.get("type")
                if (typeEl == null || !typeEl.isJsonPrimitive) return EMPTY_TILE
                val type = typeEl.asString
                if (type == EMPTY) return EMPTY_TILE
                if (type == BASIC) {
                    val textureEl = obj.get("t")
                    if (textureEl == null || !textureEl.isJsonPrimitive) return EMPTY_TILE
                    val texture = Identifier.tryParse(textureEl.asString) ?: return EMPTY_TILE
                    val hcEl = obj.get("hc")
                    if (hcEl == null || !hcEl.isJsonPrimitive) return EMPTY_TILE
                    val highContrast = Identifier.tryParse(hcEl.asString) ?: texture
                    val uEl = obj.get("u")
                    if (uEl == null || !uEl.isJsonPrimitive) return EMPTY_TILE
                    val u = uEl.asInt
                    val vEl = obj.get("v")
                    if (vEl == null || !vEl.isJsonPrimitive) return EMPTY_TILE
                    val v = vEl.asInt
                    return Tile(texture, highContrast, u, v)
                }
                return EMPTY_TILE
            }
            
        }
    }
}
