package me.fzzyhmstrs.imbued_ascendancy.armor

import me.fzzyhmstrs.imbued_ascendancy.armor.FzzyArmorMaterial.Companion.PROTECTION_VALUES
import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterItem
import net.minecraft.entity.EquipmentSlot
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents

@Suppress("PrivatePropertyName")
class VoidMailArmorMaterial : FzzyArmorMaterial {
    private val BASE_DURABILITY = intArrayOf(13, 15, 16, 11)
    private val ARMOR_VALUES = doubleArrayOf(3.0, 6.0, 8.0, 3.0)


    override fun getName(): String = "ia_void_mail"
    override fun getEquipSound(): SoundEvent = SoundEvents.ITEM_ARMOR_EQUIP_CHAIN
    override fun getRepairIngredient(): Ingredient? = Ingredient.ofItems(RegisterItem.NULL_ONYX)
    override fun getEnchantability(): Int = 20
    override fun getProtectionAmount(slot: EquipmentSlot): Int = PROTECTION_VALUES[slot.entitySlotId]
    override fun getFzzyProtectionValue(slot: EquipmentSlot): Double = ARMOR_VALUES[slot.entitySlotId]
    override fun getDurability(slot: EquipmentSlot): Int = BASE_DURABILITY[slot.entitySlotId] * 29
    override fun getKnockbackResistance(): Float = 0.0F
    override fun getToughness(): Float = 2.0f
}