package me.fzzyhmstrs.imbued_ascendancy.item

import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.item.TooltipContext
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerEntity

class NotebookOfEchoesScreen(private val player: PlayerEntity): Screen(AcText.empty()){

    override fun init() {
    }
    
     override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(matrices)
        super.render(matrices, mouseX, mouseY, delta)
     }

}
