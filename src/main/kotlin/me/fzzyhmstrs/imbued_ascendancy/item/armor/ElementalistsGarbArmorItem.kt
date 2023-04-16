package me.fzzyhmstrs.imbued_ascendancy.item.armor

import me.fzzyhmstrs.amethyst_core.registry.ModifierRegistry
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterModifier
import me.fzzyhmstrs.imbued_ascendancy.armor.ElementalistsGarbArmorMaterial
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier

class ElementalistsGarbArmorItem(slot: EquipmentSlot, settings: Settings): WitchClassArmorItem(ElementalistsGarbArmorMaterial(),slot,settings) {

    private val modifiers = mutableListOf(RegisterModifier.ELEMENTAL.modifierId,ModifierRegistry.LESSER_THRIFTY.modifierId)

    override fun defaultEquipmentModifiers(): MutableList<Identifier> {
        return modifiers
    }

    override fun celestialModifiers(): List<Identifier> {
        return modifiers
    }

}