package me.fzzyhmstrs.imbued_ascendancy.item

import com.google.gson.JsonObject
import com.mojang.blaze3d.systems.RenderSystem
import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.item.TooltipContext
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.Identifier

class NotebookOfEchoesMapScreen(private val player: PlayerEntity, oldScreen: Screen): Screen(AcText.empty()){

    companion object{
        companion object{
            internal val WIDGETS = Identifier(IA.MOD_ID,"textures/gui/map_widgets.png")
            internal val MAP_TEXTURES: Array<Identifier> = listOf(
                Identifier(IA.MOD_ID,"textures/gui/map_background_1.png"),
                Identifier(IA.MOD_ID,"textures/gui/map_background_2.png"),
                Identifier(IA.MOD_ID,"textures/gui/map_background_3.png"),
                Identifier(IA.MOD_ID,"textures/gui/map_background_4.png"),
                Identifier(IA.MOD_ID,"textures/gui/map_background_5.png"),
                Identifier(IA.MOD_ID,"textures/gui/map_background_6.png")
                ).toTypedArray()
            internal val TILES: MutableMap<ChunkPos,BaseTile> = mutableMapOf()
        }
    }
    
    private var chunkHeight = 15
    private var chunkWidth = 20
    private val chunkList: MutableList<ChunkPos>
    
    override fun init() {
    }
    
    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(matrices)
        super.render(matrices, mouseX, mouseY, delta)
    }
    
    private fun getVisibleTiles(): List<Tile>{
            
    }
    
    
    private class Tile(private val identifier, private val u: Int, private val v: Int): BaseTile(){
    
        override fun draw(matrices: MatrixStack, x: Int, y: Int) {
            RenderSystem.setShader { GameRenderer.getPositionTexProgram() }
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
            RenderSystem.setShaderTexture(0, identifier)
            drawTexture(matrices, x, y, u, v, 256, 179)
        }
        
        override fun toJson(): JsonObject{
            val json = JsonObject()
            json.addProperty("type", BASIC)
        }
    }
    
    private open class BaseTile(){
    
        open fun draw(matrices: MatrixStack, x: Int, y: Int) {}
        
        open fun toJson(): JsonObject{
            val json = JsonObject()
            json.addProperty("type", EMPTY)
            return json
        }
    
        companion object{
            protected val EMPTY = "empty"
            protected val BASIC = "basic"
        
            internal fun fromJson(json: JsonElement): EmptyTile
            
        }
    }
}
