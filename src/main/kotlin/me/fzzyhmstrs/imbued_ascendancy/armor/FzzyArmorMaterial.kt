package me.fzzyhmstrs.imbued_ascendancy.armor

import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorMaterial

interface FzzyArmorMaterial : ArmorMaterial {
    fun getFzzyProtectionValue(slot: EquipmentSlot): Double

    companion object{
        internal val PROTECTION_VALUES = intArrayOf(3, 7, 9, 3)
    }
}