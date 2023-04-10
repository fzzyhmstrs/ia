package me.fzzyhmstrs.imbued_ascendancy.item.armor

import me.fzzyhmstrs.amethyst_imbuement.item.Reactant
import me.fzzyhmstrs.amethyst_imbuement.item.Reagent
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.imbued_ascendancy.armor.CelestialArmorMaterial
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

open class WitchClassArmorItem(material: ArmorMaterial,slot: EquipmentSlot, settings: Settings): ArmorItem(material,slot,settings),Reagent {

    open fun modifiers(): List<Identifier>{
        return listOf()
    }

}