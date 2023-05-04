package me.fzzyhmstrs.imbued_ascendancy.screen

import com.mojang.blaze3d.systems.RenderSystem
import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import me.fzzyhmstrs.imbued_ascendancy.IA
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.item.TooltipContext
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.Identifier

class NotebookOfEchoesScreen(private val player: PlayerEntity): Screen(AcText.empty()){

    companion object{
        internal val BOOK_TEXTURE = Identifier(IA.MOD_ID,"textures/gui/notebook_of_echoes.png")
    }
    
    override fun init() {
    }
    
     override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(matrices)
        super.render(matrices, mouseX, mouseY, delta)
     }

}
