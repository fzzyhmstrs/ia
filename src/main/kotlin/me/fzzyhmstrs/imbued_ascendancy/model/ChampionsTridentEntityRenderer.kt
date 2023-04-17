package me.fzzyhmstrs.imbued_ascendancy.model

import me.fzzyhmstrs.imbued_ascendancy.IA
import me.fzzyhmstrs.imbued_ascendancy.entity.CelestialTridentAvatarEntity
import me.fzzyhmstrs.imbued_ascendancy.entity.CelestialTridentEntity
import me.fzzyhmstrs.imbued_ascendancy.entity.ChampionsTridentEntity
import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterRenderer
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.RotationAxis

@Suppress("PrivatePropertyName")
class ChampionsTridentEntityRenderer(context: EntityRendererFactory.Context) : EntityRenderer<ChampionsTridentEntity>(context) {
    private val TEXTURE = Identifier(IA.MOD_ID,"textures/entity/champions_trident.png")
    private val model = CelestialTridentEntityModel(context.getPart(RegisterRenderer.CHAMPIONS_TRIDENT))

    override fun render(
        glisteringTridentEntity: ChampionsTridentEntity,
        f: Float,
        g: Float,
        matrixStack: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        i: Int
    ) {
        matrixStack.push()
        matrixStack.multiply(
            RotationAxis.POSITIVE_Y.rotationDegrees(
                MathHelper.lerp(
                    g,
                    glisteringTridentEntity.prevYaw,
                    glisteringTridentEntity.yaw
                ) - 90.0f
            )
        )
        matrixStack.multiply(
            RotationAxis.POSITIVE_Z.rotationDegrees(
                MathHelper.lerp(
                    g,
                    glisteringTridentEntity.prevPitch,
                    glisteringTridentEntity.pitch
                ) + 90.0f
            )
        )
        val vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(
            vertexConsumerProvider,
            model.getLayer(getTexture(glisteringTridentEntity)),
            false,
            glisteringTridentEntity.isEnchanted
        )
        model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f)
        matrixStack.pop()
        super.render(glisteringTridentEntity, f, g, matrixStack, vertexConsumerProvider, i)
    }

    override fun getTexture(entity: ChampionsTridentEntity): Identifier {
        return TEXTURE
    }
}
