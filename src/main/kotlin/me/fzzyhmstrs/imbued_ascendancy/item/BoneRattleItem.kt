package me.fzzyhmstrs.imbued_ascendancy.item

import me.fzzyhmstrs.amethyst_core.event.ModifySpellEvent
import me.fzzyhmstrs.fzzy_core.modifier_util.AbstractModifier
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentModifier
import me.fzzyhmstrs.amethyst_core.modifier_util.ModifierHelper
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.ScepterAugment
import me.fzzyhmstrs.imbued_ascendancy.config.IaConfig
import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import me.fzzyhmstrs.fzzy_core.item_util.CustomFlavorItem
import me.fzzyhmstrs.fzzy_core.modifier_util.ModifierHelperType
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import net.minecraft.entity.LivingEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.world.World

open class BoneRattleItem(
    settings: Settings
    :
    SpecialityOffhandItem(settings,listOf(),listOf()), ManaItem
{
    init{
        ModifySpellEvent.EVENT.register{spell: ScepterAugment world: World, user: LivingEntity, hand: Hand, modifiers: AbstractModifier.CompiledModifiers<AugmentModifier> ->
            for (stack in user.handItems) {
                val item = stack.item
                if (item is BoneRattleItem && user.getStackInHand(hand).item is AugmentScepterItem) {
                    if (spell is SummonEntityAugment){
                        if (world.random.nextFloat() <  IaConfig.items.boneRattle.duplicationChance.get()){
                            if (item.checkCanUse(stack,world,user,IaConfig.items.boneRattle.duplicationDamage.get(),AcText.empty()){
                                val testLevel = ScepterHelper.getTestLevel(user.getStackInHand(hand).orCreateNbt,Registry.ENCHANTMENT.getId(spell)?:Identifier("amethyst_imbuement","magic_missile"),spell)
                                val level = max(1, ((testLevel + modifiers.compiledData.levelModifier) * user.getAttributeValue(RegisterAttribute.SPELL_LEVEL)).toInt())
                                spell.applyModifiableTasks(world,user,hand,level,modifiers.modifiers,modifiers.compiledData)
                                item.manaDamage(stack, world,user,IaConfig.items.boneRattle.duplicationDamage.get())
                            }
                        }
                    }
                }
            }
            ActionResult.PASS
        }
        
        
        
    }

    
    override fun getRepairTime(): Int {
        return IaConfig.items.boneRattle.repairTime.get()
    }
    
    override fun onCraft(stack: ItemStack, world: World, player: PlayerEntity) {
        if (world.isClient || getRepairTime() == 0) return
        ManaHelper.initializeManaItem(stack)
    }
    
    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        if (world.isClient || getRepairTime() == 0) return
        if (ManaHelper.needsInitialization(stack)){
            ManaHelper.initializeManaItem(stack)
        }
        //slowly heal damage over time
        if (ManaHelper.tickHeal(stack)){
            healDamage(1,stack)
        }
    }
    
}
