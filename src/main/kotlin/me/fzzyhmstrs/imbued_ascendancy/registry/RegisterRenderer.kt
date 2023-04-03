@file:Suppress("MemberVisibilityCanBePrivate")

package me.fzzyhmstrs.imbued_ascendancy.registry

import me.fzzyhmstrs.imbued_ascendancy.model.CelestialTridentEntityModel
import me.fzzyhmstrs.imbued_ascendancy.model.CelestialTridentEntityRenderer
import me.fzzyhmstrs.amethyst_imbuement.model.GlisteringTridentEntityRenderer
import me.fzzyhmstrs.imbued_ascendancy.IA
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

@Environment(value = EnvType.CLIENT)
object RegisterRenderer {

    val CELESTIAL_TRIDENT: EntityModelLayer = EntityModelLayer(Identifier(IA.MOD_ID,"celestial_trident"),"celestial_trident_model")

    fun registerAll() {
/*      BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlock.EXPERIENCE_BUSH, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlock.HARD_LIGHT_BLOCK, RenderLayer.getTranslucent())*/


        /*BlockEntityRendererRegistry.register(RegisterEntity.IMBUING_TABLE_BLOCK_ENTITY
        ){context: BlockEntityRendererFactory.Context ->
            ImbuingTableBlockEntityRenderer(
                context
            )
        }*/

        EntityRendererRegistry.register(
            RegisterEntity.CELESTIAL_TRIDENT_ENTITY
        ){context: EntityRendererFactory.Context ->
            CelestialTridentEntityRenderer(
                context
            )
        }

        EntityRendererRegistry.register(
            RegisterEntity.CELESTIAL_TRIDENT_AVATAR_ENTITY
        ){context: EntityRendererFactory.Context ->
            CelestialTridentEntityRenderer(
                context
            )
        }

        EntityModelLayerRegistry.registerModelLayer(CELESTIAL_TRIDENT, CelestialTridentEntityModel::getTexturedModelData)

        ModelPredicateProviderRegistry.register(
            RegisterItem.STEEL_WARD, Identifier("blocking")
        ) { stack: ItemStack, _: ClientWorld?, entity: LivingEntity?, _: Int ->
            if (entity != null && entity.isUsingItem && entity.activeItem == stack) 1.0f else 0.0f
        }
    }
}


