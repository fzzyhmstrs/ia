package me.fzzyhmstrs.imbued_ascendancy.registry

import me.fzzyhmstrs.amethyst_core.registry.RegisterAttribute
import me.fzzyhmstrs.fzzy_core.modifier_util.AbstractModifier
import me.fzzyhmstrs.fzzy_core.registry.ModifierRegistry
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.imbued_ascendancy.IA
import me.fzzyhmstrs.imbued_ascendancy.config.IaConfig
import me.fzzyhmstrs.imbued_ascendancy.modifier.ConfigEquipmentModifier
import me.fzzyhmstrs.imbued_ascendancy.modifier.ModifierConsumers
import me.fzzyhmstrs.imbued_ascendancy.modifier.ModifierFunctions
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.util.Identifier

object RegisterModifier {

    private val regMod: MutableList<AbstractModifier<*>> = mutableListOf()
    internal val defaultEnabledMap: MutableMap<String,Boolean> = mutableMapOf()

    private val CHEAP_TOLL = ConstantLootNumberProvider.create(3f) // 96% probability
    // private val NORMAL_TOLL = ConstantLootNumberProvider.create(5f) //75% probability
    private val EXPENSIVE_TOLL = UniformLootNumberProvider.create(6f,7f) //50% chance of enough toll
    private val VERY_EXPENSIVE_TOLL = UniformLootNumberProvider.create(7f,9f) //25% chance of toll

    private fun buildModifier(
        modifierId: Identifier,
        target: EquipmentModifier.EquipmentModifierTarget = EquipmentModifier.EquipmentModifierTarget.NONE,
        weight: Int = 10,
        rarity: EquipmentModifier.Rarity = EquipmentModifier.Rarity.COMMON,
        persistent: Boolean = false,
        availableForSelection: Boolean = true
    ): EquipmentModifier{
        val key = modifierId.toString()
        if (!IaConfig.modifiers.enabledModifiers.containsKey(key)){
            defaultEnabledMap[key] = true
            IaConfig.modifiers.enabledModifiers.validateAndSet(defaultEnabledMap)
        } else{
            defaultEnabledMap[key] = IaConfig.modifiers.enabledModifiers[key]?:true
        }
        return ConfigEquipmentModifier(modifierId,target,weight,rarity,persistent, availableForSelection)
    }

    //Random equipment modifiers
    val SHARP = buildModifier(Identifier(IA.MOD_ID,"sharp"), EquipmentModifier.EquipmentModifierTarget.WEAPON_AND_TRINKET, 12)
        .withAttributeModifier(
            EntityAttributes.GENERIC_ATTACK_DAMAGE,0.5, EntityAttributeModifier.Operation.ADDITION)
        .withToll(CHEAP_TOLL)
        .also { regMod.add(it) }
    val DULL = buildModifier(
        Identifier(IA.MOD_ID,"dull"), EquipmentModifier.EquipmentModifierTarget.WEAPON_AND_TRINKET, 8, EquipmentModifier.Rarity.BAD)
        .withAttributeModifier(
            EntityAttributes.GENERIC_ATTACK_DAMAGE,-0.5, EntityAttributeModifier.Operation.ADDITION)
        .withDescendant(SHARP)
        .withToll(CHEAP_TOLL)
        .also { regMod.add(it) }

    //Set and Special equipment modifiers
    val VOID_SHROUDED = buildModifier(Identifier(IA.MOD_ID,"void_shrouded"), persistent = true, availableForSelection = false)
        .withOnDamaged(ModifierFunctions.VOID_SHROUDED_DAMAGE_FUNCTION)
        .also { regMod.add(it) }
    val MANA_KINDLED = buildModifier(Identifier(IA.MOD_ID,"mana_kindled"), persistent = true, availableForSelection = false)
        .withAttributeModifier(RegisterAttribute.SPELL_DAMAGE,0.05,EntityAttributeModifier.Operation.ADDITION)
        .withAttributeModifier(RegisterAttribute.SPELL_COOLDOWN,0.0625,EntityAttributeModifier.Operation.ADDITION)
        .also { regMod.add(it) }
    val NOTHINGNESS = buildModifier(Identifier(IA.MOD_ID,"nothingness"), persistent = true, availableForSelection = false)
        .withPostHit(ModifierConsumers.NOTHINGNESS_HIT_CONSUMER)
        .also { regMod.add(it) }



    fun registerAll(){
        regMod.forEach {
            val id = it.modifierId
            defaultEnabledMap[id.toString()] = true
            ModifierRegistry.register(it)
        }
    }

}