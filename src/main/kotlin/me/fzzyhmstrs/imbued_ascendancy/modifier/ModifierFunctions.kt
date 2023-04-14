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
        EquipmentModifier.DamageFunction { _, _, _, damage, amount ->
            if (damage.isMagic){
                amount * IaConfig.modifiers.voidShroudedMultiplier.get()
            } else {
                amount
            }
        }

    val HELIOPHOBIA_DAMAGE_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, _, _, damage, amount ->
            if (damage.isMagic){
                amount * IaConfig.modifiers.voidShroudedMultiplier.get()
            } else {
                amount
            }
        }

    val WARRIORS_LIGHT_DAMAGE_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, _, _, damage, amount ->
            if (damage.isMagic){
                amount * IaConfig.modifiers.voidShroudedMultiplier.get()
            } else {
                amount
            }
        }
}