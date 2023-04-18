package me.fzzyhmstrs.imbued_ascendancy.tool

import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterItem
import net.fabricmc.yarn.constants.MiningLevels
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient

object NihilBladeMaterial: ToolMaterial {
    override fun getDurability(): Int {
        return 250
    }
    override fun getMiningSpeedMultiplier(): Float {
        return 12.5f
    }
    override fun getAttackDamage(): Float {
        return 7f
    }
    override fun getMiningLevel(): Int {
        return MiningLevels.NETHERITE
    }
    override fun getEnchantability(): Int {
        return 15
    }
    override fun getRepairIngredient(): Ingredient? {
        return Ingredient.ofItems(RegisterItem.ENSOULED_GEM)
    }
}
