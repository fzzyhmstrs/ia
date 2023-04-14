package me.fzzyhmstrs.imbued_ascendancy.item.armor

import me.fzzyhmstrs.imbued_ascendancy.armor.LichKingsRobesArmorMaterial
import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterModifier
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier

class LichKingsRobesArmorItem(slot: EquipmentSlot, settings: Settings): WitchClassArmorItem(LichKingsRobesArmorMaterial(),slot,settings) {

    private val modifiers = mutableListOf(RegisterModifier.RULER_OF_THE_DAMNED.modifierId, RegisterModifier.HELIOPHOBIA.modifierId)

    override fun defaultEquipmentModifiers(): MutableList<Identifier> {
        return modifiers
    }

    override fun celestialModifiers(): List<Identifier> {
        return modifiers
    }

}
