package me.fzzyhmstrs.imbued_ascendancy.entity.living

import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterItem
import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import me.fzzyhmstrs.imbued_ascendancy.IA
import net.minecraft.block.BlockState
import net.minecraft.entity.*
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.mob.Angerable
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.tag.BlockTags
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.WorldView
import java.util.*

class ElestiteEntity(entityType: EntityType<out ElestiteEntity>, world: World, val variant: CrystalVariant = DEFAULT): HostileEntity(entityType, world), Angerable {

    companion object{
        fun createElestiteAttributes(): DefaultAttributeContainer.Builder {
            return createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0)
        }

        class CrystalVariant(val variantType: String, val drop: Item){
            val variantTexture: Identifier = Identifier(IA.MOD_ID,"texture/entity/elestite/elestite_$variantType.png")
        }
        val DEFAULT = CrystalVariant("amethyst", Items.AMETHYST_SHARD)
        val CITRINE = CrystalVariant("citrine", RegisterItem.CITRINE)
        val QUARTZ = CrystalVariant("quartz", Items.QUARTZ)
        val SMOKY = CrystalVariant("smoky_quartz", RegisterItem.SMOKY_QUARTZ)
        val DIAMOND = CrystalVariant("diamond", Items.DIAMOND)
        val EMERALD = CrystalVariant("bloodstone", Items.EMERALD)
    }

    override fun initGoals() {
        goalSelector.add(1, PowderSnowJumpGoal(this, world))
        goalSelector.add(4, MeleeAttackGoal(this, 1.0, false))
        goalSelector.add(7,WanderAroundGoal(this,1.0))
        targetSelector.add(1, RevengeGoal(this, ElestiteEntity::class.java).setGroupRevenge(*arrayOfNulls(0)))
        targetSelector.add(4, UniversalAngerGoal(this, true))
    }

    override fun tick() {
        bodyYaw = yaw
        super.tick()
    }

    override fun setBodyYaw(bodyYaw: Float) {
        yaw = bodyYaw
        super.setBodyYaw(bodyYaw)
    }

    override fun getPathfindingFavor(pos: BlockPos, world: WorldView): Float {
        return if (world.getBlockState(pos).isIn(BlockTags.BEACON_BASE_BLOCKS)) {
            10.0f
        } else super.getPathfindingFavor(pos, world)
    }

    override fun getGroup(): EntityGroup? {
        return EntityGroup.ARTHROPOD
    }


    override fun getHeightOffset(): Double {
        return 0.15
    }

    override fun getActiveEyeHeight(pose: EntityPose, dimensions: EntityDimensions): Float {
        return 0.18f
    }

    override fun dropEquipment(source: DamageSource, lootingMultiplier: Int, allowDrops: Boolean) {
        super.dropEquipment(source, lootingMultiplier, allowDrops)
        if (allowDrops){
            val baseDrops = if (world.random.nextInt(3) == 0) { 1 } else { 0 }
            val extraDrops = world.random.nextInt(lootingMultiplier)
            dropStack(ItemStack(variant.drop,baseDrops + extraDrops))
        }
    }

    override fun getAmbientSound(): SoundEvent {
        TODO("Not yet implemented")
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        TODO("Not yet implemented")
    }

    override fun getDeathSound(): SoundEvent {
        TODO("Not yet implemented")
    }

    override fun playStepSound(pos: BlockPos, state: BlockState) {
        playSound(SoundEvents.ENTITY_SILVERFISH_STEP, 0.15f, 1.0f)
    }


    override fun getAngerTime(): Int {
        TODO("Not yet implemented")
    }

    override fun setAngerTime(angerTime: Int) {
        TODO("Not yet implemented")
    }

    override fun getAngryAt(): UUID? {
        TODO("Not yet implemented")
    }

    override fun setAngryAt(angryAt: UUID?) {
        TODO("Not yet implemented")
    }

    override fun chooseRandomAngerTime() {
        TODO("Not yet implemented")
    }

    override fun getDefaultName(): Text {
        return AcText.translatable(this.type.translationKey + "." + variant.variantType)
    }
}