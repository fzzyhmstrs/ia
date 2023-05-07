package me.fzzyhmstrs.imbued_ascendancy.armor

import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterItem
import me.fzzyhmstrs.imbued_ascendancy.armor.FzzyArmorMaterial.Companion.PROTECTION_VALUES
import net.minecraft.entity.EquipmentSlot
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents

@Suppress("PrivatePropertyName")
class GlowingArmorMaterial : FzzyArmorMaterial{
    private val BASE_DURABILITY = intArrayOf(13, 15, 16, 11)
    private val ARMOR_VALUES = doubleArrayOf(3.5, 6.5, 8.5, 3.5)


    override fun getName(): String = "ia_glowing"
    override fun getEquipSound(): SoundEvent = SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND
    override fun getRepairIngredient(): Ingredient? = Ingredient.ofItems(RegisterItem.GARNET)
    override fun getEnchantability(): Int = 35
    override fun getProtectionAmount(slot: EquipmentSlot): Int = PROTECTION_VALUES[slot.entitySlotId]
    override fun getFzzyProtectionValue(slot: EquipmentSlot): Double = ARMOR_VALUES[slot.entitySlotId]
    override fun getDurability(slot: EquipmentSlot): Int = BASE_DURABILITY[slot.entitySlotId] * 22
    override fun getKnockbackResistance(): Float = 0.1F
    override fun getToughness(): Float = 2.5f
}