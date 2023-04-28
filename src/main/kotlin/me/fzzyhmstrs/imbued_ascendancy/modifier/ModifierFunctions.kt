package me.fzzyhmstrs.imbued_ascendancy.modifier

import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.imbued_ascendancy.config.IaConfig

object ModifierFunctions {

    val VOID_SHROUDED_DAMAGE_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, _, _, damage, amount ->
            if (damage.isMagic){
                amount * IaConfig.modifiers.voidShroudedMultiplier.get()
            } else {
                amount
            }
        }

    val MANA_REACTIVE_DAMAGE_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, user, _, damage, amount ->
            if (damage.isMagic){
                ModifierConsumers.manaHealItems(user,IaConfig.modifiers.gear.manaReactiveAmount.get() * 2)
            } else {
                ModifierConsumers.manaHealItems(user,IaConfig.modifiers.gear.manaReactiveAmount.get())
            }
            amount
        }

    val HELIOPHOBIA_DAMAGE_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, user, _, damage, amount ->
            if (user.world.isSkyVisible(user.blockPos) && world.isDay){
                amount * IaConfig.modifiers.heliophobiaMultiplier.get()
            } else {
                amount
            }
        }

    val WARRIORS_LIGHT_DAMAGE_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, _, attacker, damage, amount ->
            if (attacker != null && attacker.isUndead){
                amount * IaConfig.modifiers.warriorsLightMultiplier.get()
            } else {
                amount
            }
        }
}
