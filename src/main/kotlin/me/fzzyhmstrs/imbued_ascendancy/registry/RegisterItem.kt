@file:Suppress("unused")

package me.fzzyhmstrs.imbued_ascendancy.registry

import me.fzzyhmstrs.amethyst_core.registry.RegisterAttribute
import me.fzzyhmstrs.amethyst_imbuement.item.AiItemSettings
import me.fzzyhmstrs.amethyst_imbuement.item.SpellcastersReagentFlavorItem
import me.fzzyhmstrs.imbued_ascendancy.IA
import me.fzzyhmstrs.imbued_ascendancy.item.*
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity
import java.util.*

// don't know if this is better as a class or object. as an object it allows me to call it without needing to initialize an instance of it.
object RegisterItem {

    private val regItem: MutableMap<String, Item> = mutableMapOf()

    //raw materials
    val REALITY_GEM = RealityGemItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.GEM).rarity(Rarity.UNCOMMON)).also{ regItem["reality_gem"] = it}
    val MYSTIC_FRAGMENT = SpellcastersReagentFlavorItem(RegisterAttribute.DAMAGE_MULTIPLICATION,
        EntityAttributeModifier(UUID.fromString("f2a00170-d1c6-11ed-afa1-0242ac120002"),"mystic_modifier",-0.05,EntityAttributeModifier.Operation.MULTIPLY_TOTAL),
        AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.GEM).rarity(Rarity.EPIC)).withGlint().also{ regItem["mystic_fragment"] = it}

    //tools and weapons
    val CELESTIAL_TRIDENT = CelestialTridentItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(2650).rarity(
        Rarity.EPIC)).also{ regItem["celestial_trident"] = it}
    val CRYSTALLINE_ARROW = CrystallineArrowItem(FabricItemSettings()).also{ regItem["crystalline_arrow"] = it}
    val IMBUED_ARROW = ImbuedArrowItem(FabricItemSettings()).also{ regItem["imbued_arrow"] = it}
        val COPPER_RING = SteelJewelryItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxCount(1)) .also{ regItem["steel_ring"] = it}
    val COPPER_HEADBAND = SteelJewelryItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxCount(1)) .also{ regItem["steel_headband"] = it}
    val COPPER_AMULET = SteelJewelryItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxCount(1)) .also{ regItem["steel_amulet"] = it}
    val STEEL_WARD = SteelWardItem(
        EntityAttributes.GENERIC_ARMOR,
        EntityAttributeModifier(UUID.fromString("1f6875e4-d167-11ed-afa1-0242ac120002"),"steel_ward_modifier",0.5, EntityAttributeModifier.Operation.ADDITION),
        AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(384)).also{ regItem["steel_ward"] = it}
    //////////////////////////////

    /*val AI_GROUP: ItemGroup by lazy{
        registerItemGroup()
    }*/

    /*fun registerItemGroup(): ItemGroup{
        return FabricItemGroup.builder(Identifier(IF.MOD_ID,"ai_group"))
            .displayName(Text.translatable("itemGroup.amethyst_imbuement.ai_group"))
            .icon { ItemStack(RegisterBlock.IMBUING_TABLE.asItem()) }
            .entries { _, entries, _ ->
                entries.addAll(regItem.values.stream().map { item -> ItemStack(item) }.toList())
                entries.addAll(RegisterArmor.regArmor.stream().map { item -> ItemStack(item) }.toList())
                entries.addAll(RegisterBlock.regBlock.values.stream()
                    .filter { block -> block !== RegisterBlock.EXPERIENCE_BUSH }
                    .map { block -> ItemStack(block.asItem()) }
                    .toList())

            }.build()
    }*/

    fun registerAll() {

        for (k in regItem.keys){
            Registry.register(Registries.ITEM,Identifier(IA.MOD_ID,k), regItem[k])
        }
        /*val group = AI_GROUP*/
    }
}
