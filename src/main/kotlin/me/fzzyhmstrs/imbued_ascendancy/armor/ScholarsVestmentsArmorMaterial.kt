package me.fzzyhmstrs.imbued_ascendancy.armor

import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterBlock
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterItem
import me.fzzyhmstrs.imbued_ascendancy.armor.FzzyArmorMaterial.Companion.PROTECTION_VALUES
import net.minecraft.entity.EquipmentSlot
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents

@Suppress("PrivatePropertyName")
class ScholarsVestmentsArmorMaterial : FzzyArmorMaterial {
    private val BASE_DURABILITY = intArrayOf(13, 15, 16, 11)
    private val ARMOR_VALUES = doubleArrayOf(2.0, 5.5, 6.5, 2.0)


    override fun getName(): String = "ia_elementalist"
    override fun getEquipSound(): SoundEvent = SoundEvents.ITEM_ARMOR_EQUIP_LEATHER
    override fun getRepairIngredient(): Ingredient? = Ingredient.ofItems(RegisterItem.SPARKING_GEM,RegisterItem.BLAZING_GEM,RegisterBlock.GLISTENING_ICE_ITEM)
    override fun getEnchantability(): Int = 50
    override fun getProtectionAmount(slot: EquipmentSlot): Int = PROTECTION_VALUES[slot.entitySlotId]
    override fun getFzzyProtectionValue(slot: EquipmentSlot): Double = ARMOR_VALUES[slot.entitySlotId]
    override fun getDurability(slot: EquipmentSlot): Int = BASE_DURABILITY[slot.entitySlotId] * 19
    override fun getKnockbackResistance(): Float = 0.0F
    override fun getToughness(): Float = 0.0f
}