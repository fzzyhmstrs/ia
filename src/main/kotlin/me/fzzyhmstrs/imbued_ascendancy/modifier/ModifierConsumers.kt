package me.fzzyhmstrs.imbued_ascendancy.modifier

import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentConsumer
import me.fzzyhmstrs.fzzy_core.trinket_util.EffectQueue
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.imbued_ascendancy.config.IaConfig
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack

object ModifierConsumers {

    val HEALERS_REWARD_CONSUMER = AugmentConsumer({ list: List<LivingEntity> -> healersRewardConsumer(list) }, AugmentConsumer.Type.AUTOMATIC)
    private fun healersRewardConsumer(list: List<LivingEntity>){
        list.forEach {
            val rnd1 = it.world.random.nextInt(3)
            if (rnd1 == 0 && it is PlayerEntity)
                it.addExperience(1)
        }
    }

    val NOTHINGNESS_HIT_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer { _: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (target == null) return@ToolConsumer
            if (user.world.random.nextFloat() < IaConfig.modifiers.nihilBladeNothingnessChance.get()){
                EffectQueue.addStatusToQueue(target, StatusEffects.WEAKNESS,50,1)
            }
        }

    val MANA_VAMPIRIC_HIT_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer { _: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (target == null) return@ToolConsumer
            if (user.world.random.nextFloat() < IaConfig.modifiers.nihilBladeNothingnessChance.get()){
                EffectQueue.addStatusToQueue(target, StatusEffects.WEAKNESS,50,1)
            }
        }

    val MANA_VAMPIRIC_KILL_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer { _: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (target == null) return@ToolConsumer
            if (user.world.random.nextFloat() < IaConfig.modifiers.nihilBladeNothingnessChance.get()){
                EffectQueue.addStatusToQueue(target, StatusEffects.WEAKNESS,50,1)
            }
        }

    val MANA_DRAINING_HIT_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer { _: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (target == null) return@ToolConsumer
            if (user.world.random.nextFloat() < IaConfig.modifiers.nihilBladeNothingnessChance.get()){
                EffectQueue.addStatusToQueue(target, StatusEffects.WEAKNESS,50,1)
            }
        }

    val MANA_DRAINING_KILL_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer { _: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (target == null) return@ToolConsumer
            if (user.world.random.nextFloat() < IaConfig.modifiers.nihilBladeNothingnessChance.get()){
                EffectQueue.addStatusToQueue(target, StatusEffects.WEAKNESS,50,1)
            }
        }
}