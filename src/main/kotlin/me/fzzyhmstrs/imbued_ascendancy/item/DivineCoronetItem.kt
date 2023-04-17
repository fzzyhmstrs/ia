package me.fzzyhmstrs.imbued_ascendancy.item

import me.fzzyhmstrs.amethyst_core.item_util.AbstractAugmentJewelryItem
import me.fzzyhmstrs.amethyst_imbuement.item.Reactant
import me.fzzyhmstrs.fzzy_core.mana_util.ManaItem
import net.minecraft.item.ItemStack

class DivineCoronetItem(settings: Settings): AbstractAugmentJewelryItem(settings), Reactant, ManaItem {
    override fun canReact(stack: ItemStack, reagents: List<ItemStack>): Boolean {
        return true
    }

    override fun react(stack: ItemStack, reagents: List<ItemStack>) {
        TODO("Not yet implemented")
    }

    override fun getRepairTime(): Int {
        return 0
    }


}