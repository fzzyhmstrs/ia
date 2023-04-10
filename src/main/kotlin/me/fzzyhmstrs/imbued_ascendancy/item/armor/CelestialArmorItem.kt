package me.fzzyhmstrs.imbued_ascendancy.item.armor

import me.fzzyhmstrs.amethyst_imbuement.item.Reactant
import me.fzzyhmstrs.imbued_ascendancy.armor.CelestialArmorMaterial
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack

class CelestialArmorItem(slot: EquipmentSlot, settings: Settings): ArmorItem(CelestialArmorMaterial(),slot,settings), Reactant {
    override fun canReact(stack: ItemStack, reagents: List<ItemStack>): Boolean {
        TODO("Not yet implemented")
    }

    override fun react(stack: ItemStack, reagents: List<ItemStack>) {
        TODO("Not yet implemented")
    }
}