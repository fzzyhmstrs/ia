package me.fzzyhmstrs.imbued_ascendancy.item

import me.fzzyhmstrs.amethyst_imbuement.item.Reagent
import net.minecraft.item.ItemStack

interface DivineReagent: Reagent {
    fun addModifiers(stack: ItemStack)
}