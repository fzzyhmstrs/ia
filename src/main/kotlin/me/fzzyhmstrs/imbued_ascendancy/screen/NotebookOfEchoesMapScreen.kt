package me.fzzyhmstrs.imbued_ascendancy.screen

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.mojang.blaze3d.systems.RenderSystem
import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import me.fzzyhmstrs.imbued_ascendancy.IA
import me.fzzyhmstrs.imbued_ascendancy.map.*
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
    }
    
    private var chunkHeight = 15
    private var chunkWidth = 20
    private val chunkList: MutableList<ChunkPos> = mutableListOf()
    private var tiles: Array<Array<BaseTile>> = arrayOf()
    
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
                //Loading in from map tiles needs to happen here
            }
        }
        
        
    }
    
    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(matrices)
        super.render(matrices, mouseX, mouseY, delta)
    }
    
    private fun getVisibleTiles(): List<BaseTile>{
        TODO()
    }

}
