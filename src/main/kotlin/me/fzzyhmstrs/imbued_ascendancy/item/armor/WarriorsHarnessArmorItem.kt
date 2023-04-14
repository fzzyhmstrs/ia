package me.fzzyhmstrs.imbued_ascendancy.item.armor

import me.fzzyhmstrs.imbued_ascendancy.armor.VoidMailArmorMaterial
import me.fzzyhmstrs.imbued_ascendancy.armor.WarriorsHarnessArmorMaterial
import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterModifier
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier

class WarriorsHarnessArmorItem(slot: EquipmentSlot, settings: Settings): WitchClassArmorItem(WarriorsHarnessArmorMaterial(),slot,settings) {

    private val modifiers = mutableListOf(RegisterModifier.WARRIORS_LIGHT.modifierId)

    override fun defaultEquipmentModifiers(): MutableList<Identifier> {
        return modifiers
    }

    override fun celestialModifiers(): List<Identifier> {
        return modifiers
    }

}