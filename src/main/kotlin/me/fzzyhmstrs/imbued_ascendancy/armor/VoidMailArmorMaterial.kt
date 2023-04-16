package me.fzzyhmstrs.imbued_ascendancy.armor

import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterItem
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents

@Suppress("PrivatePropertyName")
class VoidMailArmorMaterial : ArmorMaterial {
    private val BASE_DURABILITY = intArrayOf(13, 15, 16, 11)
    private val PROTECTION_VALUES = intArrayOf(3, 6, 8, 3)


    override fun getName(): String = "ia_void_mail"
    override fun getEquipSound(): SoundEvent = SoundEvents.ITEM_ARMOR_EQUIP_CHAIN
    override fun getRepairIngredient(): Ingredient? = Ingredient.ofItems(RegisterItem.NULL_ONYX)
    override fun getEnchantability(): Int = 20
    override fun getProtectionAmount(slot: EquipmentSlot): Int = PROTECTION_VALUES[slot.entitySlotId]
    override fun getDurability(slot: EquipmentSlot): Int = BASE_DURABILITY[slot.entitySlotId] * 29
    override fun getKnockbackResistance(): Float = 0.0F
    override fun getToughness(): Float = 2.0f
}