@file:Suppress("MemberVisibilityCanBePrivate")

package me.fzzyhmstrs.imbued_ascendancy.registry

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

@Environment(value = EnvType.CLIENT)
object RegisterRenderer {

    fun registerAll() {
/*      BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlock.EXPERIENCE_BUSH, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlock.HARD_LIGHT_BLOCK, RenderLayer.getTranslucent())*/


        /*BlockEntityRendererRegistry.register(RegisterEntity.IMBUING_TABLE_BLOCK_ENTITY
        ){context: BlockEntityRendererFactory.Context ->
            ImbuingTableBlockEntityRenderer(
                context
            )
        }*/
        ModelPredicateProviderRegistry.register(
            RegisterItem.STEEL_WARD, Identifier("blocking")
        ) { stack: ItemStack, _: ClientWorld?, entity: LivingEntity?, _: Int ->
            if (entity != null && entity.isUsingItem && entity.activeItem == stack) 1.0f else 0.0f
        }
    }
}


