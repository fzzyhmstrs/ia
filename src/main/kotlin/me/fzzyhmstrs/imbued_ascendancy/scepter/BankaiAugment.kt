package me.fzzyhmstrs.imbued_ascendancy.scepter

import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentEffect
import me.fzzyhmstrs.amethyst_core.scepter_util.LoreTier
import me.fzzyhmstrs.amethyst_core.scepter_util.ScepterTier
import me.fzzyhmstrs.amethyst_core.scepter_util.SpellType
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.AugmentDatapoint
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.ScepterAugment
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterItem
import me.fzzyhmstrs.fzzy_core.coding_util.PerLvlI
import me.fzzyhmstrs.fzzy_core.coding_util.PersistentEffectHelper
import me.fzzyhmstrs.fzzy_core.raycaster_util.RaycasterUtil
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolItem
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

class BankaiAugment: ScepterAugment(ScepterTier.TWO,9), PersistentEffectHelper.PersistentEffect{

    private val tries = doubleArrayOf(0.0,-1.0,1.0)

    override val baseEffect: AugmentEffect
        get() = super.baseEffect.withRange(6.75,0.25)
            .withDamage(9.75f,0.25f)
            .withAmplifier(16)

    override fun augmentStat(imbueLevel: Int): AugmentDatapoint {
        return AugmentDatapoint(SpellType.FURY,600,80,
            19,imbueLevel,25, LoreTier.NO_TIER, RegisterItem.SPARKING_GEM)
    }

    override fun applyTasks(world: World, user: LivingEntity, hand: Hand, level: Int, effects: AugmentEffect): Boolean {
        if (world !is ServerWorld) return false
        if (user !is PlayerEntity) return false
        val rotation = user.getRotationVec(1.0F)
        val perpendicularVector = RaycasterUtil.perpendicularVector(rotation, RaycasterUtil.InPlane.XZ)
        val raycasterPos = user.pos.add(rotation.multiply(effects.range(level)/2)).add(Vec3d(0.0,user.height/2.0,0.0))
        val entityList: MutableList<Entity> =
            RaycasterUtil.raycastEntityRotatedArea(
                world.iterateEntities(),
                user,
                raycasterPos,
                rotation,
                perpendicularVector,
                effects.range(level),
                0.8,
                0.8)
        if (entityList.isEmpty()) return false
        val map: MutableMap<Float,Entity> = mutableMapOf()
        entityList.forEach {
            map[user.distanceTo(it)] = it
        }
        val sortedMap = map.toSortedMap()

        val target = sortedMap.getValue(sortedMap.lastKey())
        val rot = target.rotationVector.negate()
        val pos = target.pos.add(rot.x,0.0,rot.z)
        for (i in tries) {
            if (world.isSpaceEmpty(user.type.createSimpleBoundingBox(pos.x,pos.y+i, pos.z))){
                doTeleport(pos, i, user, hand, world, entityList, level, effects)
                return true
            }
        }
        val rot2 = target.rotationVector
        val pos2 = target.pos.add(rot2.x,0.0,rot2.z)
        for (i in tries) {
            if (world.isSpaceEmpty(user.type.createSimpleBoundingBox(pos2.x,pos2.y+i, pos2.z))){
                doTeleport(pos2, i, user, hand, world, entityList, level, effects)
                return true
            }
        }

        return true
    }

    private fun doTeleport(pos: Vec3d, i: Double, user: PlayerEntity, hand: Hand, world: World, entityList: MutableCollection<Entity>, level: Int, effects: AugmentEffect){
        user.teleport(pos.x,pos.y+i, pos.z,true)
        world.playSound(null, user.blockPos, SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, SoundCategory.PLAYERS, 2.0F, 1.4F)
        val stack = user.getStackInHand(hand)
        val item = stack.item
        val damage = if (item is ToolItem){
            item.material.attackDamage * (effects.amplifier(level).toFloat() / 10f)
        } else {
            effects.damage(level)
        }
        val data = BankaiPersistentEffectData(world,user,entityList,stack, DamageSource.player(user),damage,level,effects)
        PersistentEffectHelper.setPersistentTickerNeed(this,10,10,data)
    }

    override fun soundEvent(): SoundEvent {
        return SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER
    }

    override val delay: PerLvlI
        get() = PerLvlI()

    override fun persistentEffect(data: PersistentEffectHelper.PersistentEffectData) {
        if (data !is BankaiPersistentEffectData) return
        val entities = data.entityList
        for (entity in entities){
            entity.damage(data.source,data.damage)
        }
        data.world.playSound(null, data.user.blockPos, soundEvent(), SoundCategory.PLAYERS, 2.0F, 1.4F)

    }

    private class BankaiPersistentEffectData(
        val world: World,
        val user: LivingEntity,
        val entityList: MutableCollection<Entity>,
        val stack: ItemStack,
        val source: DamageSource,
        val damage: Float,
        val level: Int,
        val effect: AugmentEffect)
        :
        PersistentEffectHelper.PersistentEffectData
}