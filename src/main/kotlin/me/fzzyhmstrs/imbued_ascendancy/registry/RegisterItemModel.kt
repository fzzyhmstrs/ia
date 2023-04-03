package me.fzzyhmstrs.imbued_ascendancy.registry

import me.fzzyhmstrs.imbued_ascendancy.model.CelestialTridentEntityModel
import me.fzzyhmstrs.imbued_ascendancy.model.CelestialTridentItemEntityRenderer
import me.fzzyhmstrs.amethyst_imbuement.AI
import me.fzzyhmstrs.fzzy_core.registry.ItemModelRegistry
import me.fzzyhmstrs.imbued_ascendancy.IA
import net.minecraft.client.util.ModelIdentifier

object RegisterItemModel {

    fun registerAll() {
        val modelsPerMode =
            ItemModelRegistry.ModelIdentifierPerModes(ModelIdentifier(IA.MOD_ID, "celestial_trident", "inventory"))
                .withHeld(ModelIdentifier(AI.MOD_ID, "celestial_trident_in_hand", "inventory"), true)
        ItemModelRegistry.registerItemModelId(RegisterItem.CELESTIAL_TRIDENT, modelsPerMode)
        ItemModelRegistry.registerItemEntityModel(
            RegisterItem.CELESTIAL_TRIDENT,
            CelestialTridentItemEntityRenderer,
            RegisterRenderer.CELESTIAL_TRIDENT,
            CelestialTridentEntityModel::class.java
        )
    }

}