package me.fzzyhmstrs.imbued_ascendancy.item.armor

import me.fzzyhmstrs.imbued_ascendancy.armor.SpellbladesArmorArmorMaterial
import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterModifier
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier

class SpellbladesArmorArmorItem(slot: EquipmentSlot, settings: Settings): WitchClassArmorItem(SpellbladesArmorArmorMaterial(),slot,settings) {

    override fun modifiers(): List<Identifier> {
        return listOf(RegisterModifier.MANA_KINDLED.modifierId)
    }

}