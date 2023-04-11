package me.fzzyhmstrs.imbued_ascendancy.item.promise

import me.fzzyhmstrs.amethyst_imbuement.item.promise.IgnitedGemItem
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterCriteria
import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import me.fzzyhmstrs.imbued_ascendancy.config.IaConfig
import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterItem
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import kotlin.math.sqrt

class VoidGemItem(settings: Settings): IgnitedGemItem(settings) {

    override fun getModifier(): Identifier {
        return Identifier("imbued_ascendancy:void_touched")
    }

    override fun giveTooltipHint(nbt: NbtCompound, stack: ItemStack, tooltip: MutableList<Text>){
    }

    fun voidGemCheck(stack: ItemStack, inventory: PlayerInventory){
        stack.decrement(1)
        val newStack = ItemStack(RegisterItem.VOID_GEM)
        inventory.offerOrDrop(newStack)
        val player = inventory.player
        if (player is ServerPlayerEntity) {
            RegisterCriteria.IGNITE.trigger(player)
        }
    }
}
