package me.fzzyhmstrs.imbued_ascendancy.item

import me.fzzyhmstrs.amethyst_core.item_util.AbstractAugmentJewelryItem
import me.fzzyhmstrs.fzzy_core.mana_util.ManaItem
import me.fzzyhmstrs.fzzy_core.trinket_util.TrinketUtil
import me.fzzyhmstrs.gear_core.interfaces.KillTracking
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld

class PendantOfMemoriesItem(settings: Settings): AbstractAugmentJewelryItem(settings), ManaItem, KillTracking {

    override fun intermittentTick(stack: ItemStack, entity: LivingEntity) {
        if (entity.world.random.nextFloat() < 0.1 && stack.isDamaged){
            heal(stack)
        }
        if (entity is PlayerEntity && getActive(stack)) {
            val stacks: MutableList<ItemStack> = mutableListOf()
            for (stack2 in entity.inventory.main) {
                if (stack2.item is ManaItem && stack2.isDamaged && !stack.isOf(RegiterItem.PENDANT_OF_MEMORIES)) {
                    stacks.add(stack2)
                }
            } // iterate over the inventory and look for items that are interfaced with "ManaItem"
            for (stack2 in entity.inventory.offHand) {
                if (stack2.item is ManaItem && stack2.isDamaged && !stack.isOf(RegiterItem.PENDANT_OF_MEMORIES)) {
                    stacks.add(stack2)
                }
            }
            for (stack2 in entity.inventory.armor) {
                if (stack2.item is ManaItem && stack2.isDamaged && !stack.isOf(RegiterItem.PENDANT_OF_MEMORIES)) {
                    stacks.add(stack2)
                }
            }
            val stacks2 = TrinketUtil.getTrinketStacks(entity)
            stacks2.forEach {
                if (it.item is ManaItem && it.isDamaged && !stack.isOf(RegiterItem.PENDANT_OF_MEMORIES)) {
                    stacks.add(it)
                }
            }
            if (stack.damage < stack.maxDamage - 1){
                if (stacks.isNotEmpty()){
                    val i = entity.world.random.nextInt(stacks.size)
                    this.healDamage(1,stacks[i])
                } else {
                    entity.addExperience(1)
                }
            }
            if (this.manaDamage(stack,entity.world,entity,1)){
                val nbt = stack.orCreateNbt
                nbt.putBoolean("active",false)
            }
        }
        super.intermittentTick(stack, entity)
    }

    override fun onWearerKilledOther(stack: ItemStack, wearer: LivingEntity, victim: LivingEntity, world: ServerWorld) {
        heal(stack)
        super.onWearerKilledOther(stack, wearer, victim, world)
    }

    private fun getActive(stack: ItemStack): Boolean{
        val nbt = stack.nbt
        return nbt?.getBoolean("active") == true
    }

    private fun heal(stack: ItemStack){
        this.healDamage(1,stack)
        if (!stack.isDamaged){
            val nbt = stack.orCreateNbt
            nbt.putBoolean("active",true)
        }
    }

    override fun getRepairTime(): Int {
        return 0
    }

}
