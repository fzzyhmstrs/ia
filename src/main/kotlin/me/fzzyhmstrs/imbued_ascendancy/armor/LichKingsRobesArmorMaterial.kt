package me.fzzyhmstrs.imbued_ascendancy.armor

import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterItem
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents

@Suppress("PrivatePropertyName")
class LichKingsRobesArmorMaterial : ArmorMaterial {
    private val BASE_DURABILITY = intArrayOf(13, 15, 16, 11)
    private val PROTECTION_VALUES = intArrayOf(2, 6, 7, 2)


    override fun getName(): String = "ia_lich_kings_robes"
    override fun getEquipSound(): SoundEvent = SoundEvents.ITEM_ARMOR_EQUIP_LEATHER
    override fun getRepairIngredient(): Ingredient? = Ingredient.ofItems(RegisterItem.SERPENTINE)
    override fun getEnchantability(): Int = 13
    override fun getProtectionAmount(slot: EquipmentSlot): Int = PROTECTION_VALUES[slot.entitySlotId]
    override fun getDurability(slot: EquipmentSlot): Int = BASE_DURABILITY[slot.entitySlotId] * 15
    override fun getKnockbackResistance(): Float = 0.0F
    override fun getToughness(): Float = 2.0f
}
