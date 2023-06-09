package me.fzzyhmstrs.imbued_ascendancy.item.weapon

import me.fzzyhmstrs.imbued_ascendancy.entity.ImbuedArrowEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.ArrowItem
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class CrystallineArrowItem(settings: Settings): ArrowItem(settings) {

    override fun createArrow(world: World, stack: ItemStack, shooter: LivingEntity): PersistentProjectileEntity {
        val arrowEntity = ImbuedArrowEntity(world, shooter)
        arrowEntity.initFromStack(stack)
        arrowEntity.damage = 2.5
        return arrowEntity
    }

}