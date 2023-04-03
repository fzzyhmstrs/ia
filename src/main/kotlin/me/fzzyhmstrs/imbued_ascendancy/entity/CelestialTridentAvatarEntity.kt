package me.fzzyhmstrs.imbued_ascendancy.entity

import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterEnchantment.CONTAMINATED
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterEnchantment.DECAYED
import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterEntity
import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterItem
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LightningEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
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