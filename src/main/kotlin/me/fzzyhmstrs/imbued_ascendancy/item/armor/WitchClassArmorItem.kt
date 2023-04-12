package me.fzzyhmstrs.imbued_ascendancy.item.armor

import me.fzzyhmstrs.amethyst_imbuement.item.Reactant
import me.fzzyhmstrs.amethyst_imbuement.item.Reagent
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.imbued_ascendancy.armor.CelestialArmorMaterial
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.Identifier

open class WitchClassArmorItem(material: ArmorMaterial,slot: EquipmentSlot, settings: Settings): ArmorItem(material,slot,settings),Reagent {



    //CelestialEquipmentModifiers used to provide various bonuses or deducts to the final state of the
    open fun modifiers(): List<Identifier>{
        return listOf()
    }

    //used to add specific NBT to the celestial armor stack, used to
    open fun addNbt(nbtCompound: NbtCompound){

    }
}