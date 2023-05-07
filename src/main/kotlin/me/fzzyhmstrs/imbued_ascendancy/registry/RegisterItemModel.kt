package me.fzzyhmstrs.imbued_ascendancy.registry

import me.fzzyhmstrs.amethyst_imbuement.AI
import me.fzzyhmstrs.fzzy_core.registry.ItemModelRegistry
import me.fzzyhmstrs.imbued_ascendancy.IA
import me.fzzyhmstrs.imbued_ascendancy.model.CelestialTridentEntityModel
import me.fzzyhmstrs.imbued_ascendancy.model.CelestialTridentItemEntityRenderer
import me.fzzyhmstrs.imbued_ascendancy.model.ChampionsTridentEntityModel
import me.fzzyhmstrs.imbued_ascendancy.model.ChampionsTridentItemEntityRenderer
import net.minecraft.client.util.ModelIdentifier

object RegisterItemModel {

    fun registerAll() {
        val celestialModelModes =
            ItemModelRegistry.ModelIdentifierPerModes(ModelIdentifier(IA.MOD_ID, "celestial_trident", "inventory"))
                .withHeld(ModelIdentifier(AI.MOD_ID, "celestial_trident_in_hand", "inventory"), true)
        ItemModelRegistry.registerItemModelId(RegisterItem.CELESTIAL_TRIDENT, celestialModelModes)
        ItemModelRegistry.registerItemEntityModel(
            RegisterItem.CELESTIAL_TRIDENT,
            CelestialTridentItemEntityRenderer,
            RegisterRenderer.CELESTIAL_TRIDENT,
            CelestialTridentEntityModel::class.java
        )

        val championsModelModes =
            ItemModelRegistry.ModelIdentifierPerModes(ModelIdentifier(IA.MOD_ID, "champions_trident", "inventory"))
                .withHeld(ModelIdentifier(AI.MOD_ID, "champions_trident_in_hand", "inventory"), true)
        ItemModelRegistry.registerItemModelId(RegisterItem.CHAMPIONS_TRIDENT, championsModelModes)
        ItemModelRegistry.registerItemEntityModel(
            RegisterItem.CHAMPIONS_TRIDENT,
            ChampionsTridentItemEntityRenderer,
            RegisterRenderer.CHAMPIONS_TRIDENT,
            ChampionsTridentEntityModel::class.java
        )
    }

}