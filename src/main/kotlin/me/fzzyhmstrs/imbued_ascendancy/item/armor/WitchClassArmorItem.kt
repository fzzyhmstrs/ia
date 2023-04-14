package me.fzzyhmstrs.imbued_ascendancy.item.armor

import me.fzzyhmstrs.amethyst_imbuement.item.Reactant
import me.fzzyhmstrs.amethyst_imbuement.item.Reagent
import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import me.fzzyhmstrs.fzzy_core.modifier_util.ModifierHelperType
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import me.fzzyhmstrs.imbued_ascendancy.armor.CelestialArmorMaterial
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.Identifier

open class WitchClassArmorItem(material: ArmorMaterial,slot: EquipmentSlot, settings: Settings): ArmorItem(material,slot,settings), Reagent, Modifiable {

    override fun defaultModifiers(type: ModifierHelperType): MutableList<Identifier> {
        if (type == EquipmentModifierHelper.getType()) return defaultEquipmentModifiers()
        return super.defaultModifiers(type)
    }

    //passing the equipment type modifiers into the modifiable interface with a helper method that just cares about equip. mods.
    open fun defaultEquipmentModifiers(): MutableList<Identifier>{
        return mutableListOf()
    }

    //CelestialEquipmentModifiers used to provide various bonuses or deducts to the final state of the
    open fun celestialModifiers(): List<Identifier>{
        return listOf()
    }

    //used to add specific NBT to the celestial armor stack, used to
    open fun celestialNbt(nbtCompound: NbtCompound, stack: ItemStack){

    }
}