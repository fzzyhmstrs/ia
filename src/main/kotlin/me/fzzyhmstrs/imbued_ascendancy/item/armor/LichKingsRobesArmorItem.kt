package me.fzzyhmstrs.imbued_ascendancy.item.armor

import me.fzzyhmstrs.imbued_ascendancy.armor.SpellbladesArmorArmorMaterial
import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterModifier
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier

class LichKingsRobesArmorItem(slot: EquipmentSlot, settings: Settings): WitchClassArmorItem(LichKingsRobesArmorMaterial(),slot,settings) {

    override fun modifiers(): List<Identifier> {
        return listOf(RegisterModifier.RULER_OF_THE_DAMNED.modifierId, RegisterModifier.SOLARPHOBIA.modifierId)
    }

}
