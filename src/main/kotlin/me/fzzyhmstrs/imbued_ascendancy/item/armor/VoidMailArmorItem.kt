package me.fzzyhmstrs.imbued_ascendancy.item.armor

import me.fzzyhmstrs.imbued_ascendancy.armor.VoidMailArmorMaterial
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier

class VoidMailArmorItem(slot: EquipmentSlot,settings: Settings): WitchClassArmorItem(VoidMailArmorMaterial(),slot,settings) {

    override fun modifiers(): List<Identifier> {
        return listOf(Identifier("imbued_ascendancy:call_of_the_void"))
    }

}