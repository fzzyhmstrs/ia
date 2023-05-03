package me.fzzyhmstrs.imbued_ascendancy.item

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.mojang.blaze3d.systems.RenderSystem
import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.item.TooltipContext
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.Identifier

class NotebookOfEchoesMapScreen(private val player: PlayerEntity, oldScreen: Screen): Screen(AcText.empty()){

    companion object: SimpleSynchronousResourceReloadListener {
        
        override fun reload(manager: ResourceManager) {
            loadMapTiles(manager)
        }

        override fun getFabricId(): Identifier {
            return Identifier(IA.MOD_ID,"map_tiles_loader")
        }
        
        private fun loadBiomeTextures(manager: ResourceManager){
            BIOMES.clear()
            STRUCTURES.clear()
            resourceManager.findResources("biome_textures") { path -> path.path.endsWith(".json") }
                .forEach { (t, u) ->
                    loadTexture(t,u, BIOMES)
            }
            resourceManager.findResources("structure_textures") { path -> path.path.endsWith(".json") }
                .forEach { (t, u) ->
                    loadTexture(t,u, STRUCTURES)
            }
        }
        
        private fun loadTexture(resourceId: Identifier,resource: Resource, map: MutableMap<Identifier,Pair<Identifier>>){
            val reader = resource.reader
            try{
                val json = JsonParser.parseReader(reader).asJsonObject
                for (el in json.entrySet()){
                    if (!el.isJsonObject){
                        println("element $el isn't a valid JsonObject")
                        continue
                    }
                    val obj = el.asJsonObject
                    val idKey = obj.key
                    val id = Identifier.tryParse(biomeId)
                    if (id == null){
                        println("$idKey could not be parsed as a valid identifier")
                        continue
                    } else if (Registried.BIOME.get(id) == null){
                        println("ID $biomeId isn't a valid biome identifier")
                        continue
                    }
                    val textureEl = obj.get("texture")
                    val texture = Identifier.tryParse(textureId)
                    if (texture == null){
                        println("Object $obj missing primary texture ID")
                        continue
                    }
                    val hcEl = obj.get("high_contrast")
                    val hc = Identifier.tryParse(hcId)
                    val highContrast = if(hc == null){
                        texture
                    } else {
                        hc
                    }
                    map[biomeId] = Pair(texture, highContrast)

                }
            } catch(e: Exception){
                println("Resource $resourceId isn't valid JSON")
                e.printStacktrace()
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
        internal val LOADED_TILES: MutableMap<ChunkPos, BaseTile> = MutableMapOf()
        internal val TILES: Array<Array<BaseTile>> = arrayOf()
        internal val BIOMES: MutableMap<Identifier,Pair<Identifier>> = mutableMapOf()
        internal val STRUCTURES: MutableMap<Identifier,Pair<Identifier>> = mutableMapOf()
    }
    
    private var chunkHeight = 15
    private var chunkWidth = 20
    private val chunkList: MutableList<ChunkPos>
    
    override fun init() {
        val originPos = player.chunkPos
        chunkHeight = height / 16
        if (height % 16 != 0) chunkHeight += 1
        chunkHeightRange = if(chunkHeight % 2 != 0){
            (-chunkHeight/2)..(chunkHeight/2)
        } else {
            (-chunkHeight/2)..((chunkHeight/2)-1)
        }
        chunkWidth = width / 16
        if (width % 16 != 0) chunkWidth += 1
        chunkWidthRange = if(chunkWidth % 2 != 0){
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
            
    }
    
    
    private class Tile(private val texture, private val highCon, private val u: Int, private val v: Int): BaseTile(){
    
        override fun draw(matrices: MatrixStack, x: Int, y: Int, hc: Boolean) {
            RenderSystem.setShader { GameRenderer.getPositionTexProgram() }
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
            RenderSystem.setShaderTexture(0, if (hc) highCon else texture)
            drawTexture(matrices, x, y, u, v, 256, 179)
        }
        
        override fun toJson(): JsonObject{
            val json = JsonObject()
            json.addProperty("type", BASIC)
            json.addProperty("t", texture.toString())
            json.addProperty("hc",highCon.toString())
            json.addProperty("u",v)
            json.addProperty("v",v)
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
            protected val EMPTY = "empty"
            protected val BASIC = "basic"
        
            internal fun fromJson(json: JsonElement): BaseTile{
                if (!json.isJsonObject) return EMPTY_TILE
                val obj = json.asJsonObject
                val typeEl = obj.get("type")
                if (typeEl == null || !typeEl.isJsonPrimitive) return EMPTY_TILE
                val type = typeEl.getAsString()
                if (type == EMPTY) return EMPTY_TILE
                if (type == BASIC){
                    val textureEl = obj.get("t")
                    val texture = Identifier.tryParse(textureId)
                    if (texture == null) return EMPTY_TILE
                    val hcEl = obj.get("hc")
                    val hc = Identifier.tryParse(hcId)
                    val highContrast = if(hc == null){
                        texture
                    } else {
                        hc
                    }
                    val uEl = obj.get("u")
                    if (uEl == null || !uEl.isJsonPrimitive) return EMPTY_TILE
                    val u = uEl.getAsInt()
                    val vEl = obj.get("v")
                    if (vEl == null || !vEl.isJsonPrimitive) return EMPTY_TILE
                    val v = vEl.getAsInt()
                    return Tile(texture, highContrast, u, v)
                }
            }
            
        }
    }
}
