package me.fzzyhmstrs.imbued_ascendancy.item.armor

import me.fzzyhmstrs.amethyst_core.registry.ModifierRegistry
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterModifier
import me.fzzyhmstrs.imbued_ascendancy.armor.ScholarsVestmentsArmorMaterial
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier

class ScholarsVestmentsArmorItem(slot: EquipmentSlot, settings: Settings): WitchClassArmorItem(ScholarsVestmentsArmorMaterial(),slot,settings) {

    private val modifiers = mutableListOf(RegisterModifier.ELEMENTAL.modifierId,ModifierRegistry.LESSER_THRIFTY.modifierId)

    override fun defaultEquipmentModifiers(): MutableList<Identifier> {
        return modifiers
    }

    override fun celestialModifiers(): List<Identifier> {
        return modifiers
    }

}