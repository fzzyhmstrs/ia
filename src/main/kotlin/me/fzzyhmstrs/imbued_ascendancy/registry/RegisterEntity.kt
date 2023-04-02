package me.fzzyhmstrs.imbued_ascendancy.registry

import me.fzzyhmstrs.amethyst_imbuement.AI
import me.fzzyhmstrs.amethyst_imbuement.entity.GlisteringTridentEntity
import me.fzzyhmstrs.imbued_ascendancy.entity.CrystallineArrowEntity
import me.fzzyhmstrs.imbued_ascendancy.entity.ImbuedArrowEntity
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.world.World

object RegisterEntity {

    /*val DISENCHANTING_TABLE_BLOCK_ENTITY: BlockEntityType<DisenchantingTableBlockEntity> = Registry.register(
        Registries.BLOCK_ENTITY_TYPE,
        IF.MOD_ID + ":disenchanting_table_entity",
        FabricBlockEntityTypeBuilder.create({ pos: BlockPos, state: BlockState ->
            DisenchantingTableBlockEntity(
                pos,
                state
            )
        },RegisterBlock.DISENCHANTING_TABLE).build(null))*/

    val IMBUED_ARROW_ENTITY: EntityType<ImbuedArrowEntity> = Registry.register(
        Registries.ENTITY_TYPE,
        Identifier(AI.MOD_ID, "imbued_arrow"),
        FabricEntityTypeBuilder.create(
            SpawnGroup.MISC
        ) { entityType: EntityType<ImbuedArrowEntity>, world: World ->
            ImbuedArrowEntity(
                entityType,
                world
            )
        }.dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackRangeChunks(4).trackedUpdateRate(20).build()
    )

    val CRYSTALLINE_ARROW_ENTITY: EntityType<CrystallineArrowEntity> = Registry.register(
        Registries.ENTITY_TYPE,
        Identifier(AI.MOD_ID, "crystalline_arrow"),
        FabricEntityTypeBuilder.create(
            SpawnGroup.MISC
        ) { entityType: EntityType<CrystallineArrowEntity>, world: World ->
            CrystallineArrowEntity(
                entityType,
                world
            )
        }.dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackRangeChunks(4).trackedUpdateRate(20).build()
    )


    fun registerAll(){
    }


}