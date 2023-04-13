package me.fzzyhmstrs.imbued_ascendancy.item.armor

import me.fzzyhmstrs.imbued_ascendancy.armor.VoidMailArmorMaterial
import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterModifier
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier

class VoidMailArmorItem(slot: EquipmentSlot,settings: Settings): WitchClassArmorItem(VoidMailArmorMaterial(),slot,settings) {

    override fun modifiers(): List<Identifier> {
        return listOf(RegisterModifier.VOID_SHROUDED.modifierId)
    }

}