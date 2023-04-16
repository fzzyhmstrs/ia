package me.fzzyhmstrs.imbued_ascendancy.item.weapon

import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import me.fzzyhmstrs.fzzy_core.coding_util.PerLvlI
import me.fzzyhmstrs.fzzy_core.coding_util.PersistentEffectHelper
import me.fzzyhmstrs.fzzy_core.item_util.interfaces.Flavorful
import me.fzzyhmstrs.imbued_ascendancy.entity.CelestialTridentAvatarEntity
import me.fzzyhmstrs.imbued_ascendancy.entity.CelestialTridentEntity
import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterItem
import net.minecraft.client.item.TooltipContext
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.MovementType
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.TridentItem
import net.minecraft.registry.Registries
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.stat.Stats
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.UseAction
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

class CelestialTridentItem(settings: Settings) : TridentItem(settings), Flavorful<CelestialTridentItem>, PersistentEffectHelper.PersistentEffect {
    private val attributeModifiers: Multimap<EntityAttribute, EntityAttributeModifier>
    override var flavor: String = ""
    override var glint: Boolean = false
    override var flavorDesc: String = ""

    private val flavorText: MutableText by lazy{
        makeFlavorText()
    }

    private val flavorTextDesc: MutableText by lazy{
        makeFlavorTextDesc()
    }

    override val delay = PerLvlI(10)

    init {
        val builder = ImmutableMultimap.builder<EntityAttribute, EntityAttributeModifier>()
        builder.put(
            EntityAttributes.GENERIC_ATTACK_DAMAGE,
            EntityAttributeModifier(
                ATTACK_DAMAGE_MODIFIER_ID,
                "Tool modifier",
                16.0,
                EntityAttributeModifier.Operation.ADDITION
            )
        )
        builder.put(
            EntityAttributes.GENERIC_ATTACK_SPEED,
            EntityAttributeModifier(
                ATTACK_SPEED_MODIFIER_ID,
                "Tool modifier",
                -2.9,
                EntityAttributeModifier.Operation.ADDITION
            )
        )
        this.attributeModifiers = builder.build()
    }

    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>,
        context: TooltipContext
    ) {
        super.appendTooltip(stack, world, tooltip, context)
        addFlavorText(tooltip, context)
    }

    override fun usageTick(world: World, user: LivingEntity, stack: ItemStack, remainingUseTicks: Int) {
        super.usageTick(world, user, stack, remainingUseTicks)
        if (world.isClient) return
        val i = getMaxUseTime(stack) - remainingUseTicks
        if (i > 10){
            //play hum noise
            when (i){
                16 ->{
                    world.playSound(null,user.blockPos,SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE,SoundCategory.PLAYERS,0.7f,1.3f)
                    emitParticles(world as ServerWorld,user)
                }
                22 ->{
                    world.playSound(null,user.blockPos,SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE,SoundCategory.PLAYERS,0.7f,1.5f)
                    emitParticles(world as ServerWorld,user)
                }
                28 ->{
                    world.playSound(null,user.blockPos,SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE,SoundCategory.PLAYERS,0.7f,1.7f)
                    emitParticles(world as ServerWorld,user)
                }
                34 ->{
                    world.playSound(null,user.blockPos,SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE,SoundCategory.PLAYERS,0.7f,1.9f)
                    emitParticles(world as ServerWorld,user)
                }
                40 ->{
                    world.playSound(null,user.blockPos,SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE,SoundCategory.PLAYERS,0.7f,2.1f)
                    emitParticles(world as ServerWorld,user)
                }
            }
        }

    }

    private fun emitParticles(world: ServerWorld, user: LivingEntity){

    }

    override fun onStoppedUsing(stack: ItemStack, world: World, user: LivingEntity, remainingUseTicks: Int) {
        if (user !is PlayerEntity) {
            return
        }
        val i = getMaxUseTime(stack) - remainingUseTicks
        if (i < 10) {
            return
        }
        val j = EnchantmentHelper.getRiptide(stack)
        if (j > 0 && !user.isTouchingWaterOrRain) {
            return
        }
        val avatars = if(i >=40){
            5
        } else if (i >= 34){
            4
        } else if (i >= 28){
            3
        } else if (i >= 22){
            2
        } else if (i >= 16){
            1
        } else {
            0
        }
        if (!world.isClient) {
            stack.damage(1 + avatars, user) { p: PlayerEntity ->
                p.sendToolBreakStatus(
                    user.getActiveHand()
                )
            }
            if (j == 0) {
                if (avatars > 0) {
                    val data = CelestialTridentData(world, user, stack.copy())
                    PersistentEffectHelper.setPersistentTickerNeed(this, 10, 10 * avatars,data)
                }

                val cte = CelestialTridentEntity(world, user as LivingEntity, stack)
                cte.setVelocity(
                    user,
                    user.pitch,
                    user.yaw,
                    0.0f,
                    2.5f,
                    1.0f
                )
                if (user.abilities.creativeMode) {
                    cte.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY
                }
                world.spawnEntity(cte)
                world.playSoundFromEntity(
                    null,
                    cte,
                    SoundEvents.ITEM_TRIDENT_THROW,
                    SoundCategory.PLAYERS,
                    1.0f,
                    1.0f
                )
                if (!user.abilities.creativeMode) {
                    user.inventory.removeOne(stack)
                }
            }
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this))
        if (j > 0) {
            val yaw = user.yaw
            val pitch = user.pitch
            var g =
                -MathHelper.sin(yaw * (Math.PI.toFloat() / 180)) * MathHelper.cos(pitch * (Math.PI.toFloat() / 180))
            var h = -MathHelper.sin(pitch * (Math.PI.toFloat() / 180))
            var k =
                MathHelper.cos(yaw * (Math.PI.toFloat() / 180)) * MathHelper.cos(pitch * (Math.PI.toFloat() / 180))
            //val l = MathHelper.sqrt(g * g + h * h + k * k)
            val m = 3.0f * ((1.0f + j.toFloat() + avatars.toFloat()) / 4.0f)

            g *= m
            h *= m
            k *= m
            user.addVelocity(g.toDouble(),h.toDouble(),k.toDouble())
            user.useRiptide(20)

            if (user.isOnGround) {
                //val n = 1.1999999f
                user.move(MovementType.SELF, Vec3d(0.0, 1.1999999284744263, 0.0))
            }
            val n =
                if (j >= 3) SoundEvents.ITEM_TRIDENT_RIPTIDE_3 else if (j == 2) SoundEvents.ITEM_TRIDENT_RIPTIDE_2 else SoundEvents.ITEM_TRIDENT_RIPTIDE_1
            world.playSoundFromEntity(null, user, n, SoundCategory.PLAYERS, 1.0f, 1.0f)
        }
    }


    override fun canRepair(stack: ItemStack, ingredient: ItemStack): Boolean {
        return ingredient.isOf(RegisterItem.MYSTIC_FRAGMENT) && stack.isOf(RegisterItem.CELESTIAL_TRIDENT)
    }

    override fun getEnchantability(): Int {
        return 45
    }

    override fun getUseAction(stack: ItemStack): UseAction {
        return UseAction.SPEAR
    }

    override fun getAttributeModifiers(slot: EquipmentSlot): Multimap<EntityAttribute, EntityAttributeModifier> {
        return if (slot == EquipmentSlot.MAINHAND) {
            this.attributeModifiers
        } else super.getAttributeModifiers(slot)
    }


    private fun makeFlavorText(): MutableText {
        val id = Registries.ITEM.getId(this)
        return AcText.translatable("item.${id.namespace}.${id.path}.flavor").formatted(Formatting.WHITE, Formatting.ITALIC)
    }

    private fun makeFlavorTextDesc(): MutableText {
        val id = Registries.ITEM.getId(this)
        return AcText.translatable("item.${id.namespace}.${id.path}.flavor.desc").formatted(Formatting.WHITE)
    }

    override fun flavorText(): MutableText {
        return flavorText
    }

    override fun flavorDescText(): MutableText {
        return flavorTextDesc
    }

    override fun getFlavorItem(): CelestialTridentItem {
        return this
    }

    override fun persistentEffect(data: PersistentEffectHelper.PersistentEffectData) {
        if (data !is CelestialTridentData) return
        val cte = CelestialTridentAvatarEntity(data.world, data.user, data.stack)
        cte.setVelocity(
            data.user,
            data.user.pitch,
            data.user.yaw,
            0.0f,
            2.5f,
            1.0f
        )
        data.world.spawnEntity(cte)
        data.world.playSoundFromEntity(
            null,
            cte,
            SoundEvents.ITEM_TRIDENT_THROW,
            SoundCategory.PLAYERS,
            0.8f,
            (data.world.random.nextFloat() * 0.2f) + 1f
        )


    }

    private class CelestialTridentData(val world: World,val user: LivingEntity, val stack: ItemStack): PersistentEffectHelper.PersistentEffectData

}