package me.fzzyhmstrs.imbued_ascendancy.registry

import me.fzzyhmstrs.imbued_ascendancy.IA
import me.fzzyhmstrs.imbued_ascendancy.effects.NihilismStatusEffect
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object RegisterStatus {

    val NIHILISM: StatusEffect = NihilismStatusEffect(StatusEffectCategory.HARMFUL,0x000028)
        .addAttributeModifier(EntityAttributes.GENERIC_MAX_HEALTH,"bebdd92a-e5cd-11ed-a05b-0242ac120003",-4.0,EntityAttributeModifier.Operation.ADDITION)

    fun registerAll(){
        Registry.register(Registries.STATUS_EFFECT, Identifier(IA.MOD_ID,"nihilism"), NIHILISM)
    }
}
