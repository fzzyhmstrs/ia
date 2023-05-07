package me.fzzyhmstrs.imbued_ascendancy.entity

import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterEntity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class CelestialTridentAvatarEntity : CelestialTridentEntity {

    constructor(entityType: EntityType<out CelestialTridentEntity?>?, world: World?) : super(entityType, world)
    constructor(world: World, owner: LivingEntity, parentStack: ItemStack) : super(
        RegisterEntity.CELESTIAL_TRIDENT_AVATAR_ENTITY,
        world,
        owner,
        parentStack
    )

    override val damage = 6.0f

    override fun asItemStack(): ItemStack {
        return ItemStack.EMPTY.copy()
    }

    override fun tickTrident() {
    }

    override fun tryPickup(player: PlayerEntity): Boolean {
        return false
    }

    override fun onPlayerCollision(player: PlayerEntity) {
        if (isOwner(player) || owner == null) {
            super.onPlayerCollision(player)
        }
    }
}