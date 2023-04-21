@file:Suppress("unused")

package me.fzzyhmstrs.imbued_ascendancy.registry

import me.fzzyhmstrs.amethyst_core.registry.RegisterAttribute
import me.fzzyhmstrs.amethyst_imbuement.item.AiItemSettings
import me.fzzyhmstrs.amethyst_imbuement.item.SpellcastersReagentFlavorItem
import me.fzzyhmstrs.amethyst_imbuement.item.custom.CustomHoeItem
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterModifier
import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterModifier as RegisterModifier1
import me.fzzyhmstrs.imbued_ascendancy.IA
import me.fzzyhmstrs.imbued_ascendancy.item.*
import me.fzzyhmstrs.imbued_ascendancy.item.promise.RealityGemItem
import me.fzzyhmstrs.imbued_ascendancy.item.promise.VoidGemItem
import me.fzzyhmstrs.imbued_ascendancy.item.weapon.*
import me.fzzyhmstrs.imbued_ascendancy.tool.SteelToolMaterial
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.item.*
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity
import java.util.*

// don't know if this is better as a class or object. as an object it allows me to call it without needing to initialize an instance of it.
object RegisterItem {

    private val regItem: MutableMap<String, Item> = mutableMapOf()

    //raw materials
    val SARDONYX = Item(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.GEM)).also{ regItem["sardonyx"] = it}
    val NULL_ONYX = Item(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.GEM).rarity(Rarity.UNCOMMON)).also{ regItem["null_onyx"] = it}
    val SERPENTINE = Item(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.GEM)).also{ regItem["serpentine"] = it}
    val ENSOULED_GEM = RealityGemItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.GEM).rarity(Rarity.UNCOMMON)).also{ regItem["reality_gem"] = it}
    val REALITY_GEM = RealityGemItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.GEM).rarity(Rarity.UNCOMMON)).also{ regItem["reality_gem"] = it}
    val VOID_GEM = VoidGemItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.GEM).rarity(Rarity.UNCOMMON)).also{ regItem["void_gem"] = it}
    val MYSTIC_FRAGMENT = SpellcastersReagentFlavorItem(RegisterAttribute.DAMAGE_MULTIPLICATION,
        EntityAttributeModifier(UUID.fromString("f2a00170-d1c6-11ed-afa1-0242ac120002"),"mystic_modifier",-0.05,EntityAttributeModifier.Operation.MULTIPLY_TOTAL),
        AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.GEM).rarity(Rarity.EPIC)).withGlint().also{ regItem["mystic_fragment"] = it}
    val SHIMMERING_FABRIC = Item(AiItemSettings()).also{ regItem["shimmering_fabric"] = it}
    val PURESTEEL = Item(AiItemSettings()).also{ regItem["puresteel"] = it}
    //powders
    //model tex
    val PURE_POWDER = Item(FabricItemSettings()).also{ regItem["pure_powder"] = it} //lighting bedrock on fire
    //powders
    val NULL_POWDER = Item(FabricItemSettings()).also{ regItem["null_powder"] = it} //lighting bedrock on fire
    //soul powder
    val SOUL_POWDER = Item(FabricItemSettings()).also{ regItem["soul_powder"] = it} //killing a mob with gem fire
    
    //icons
    //model tex lang
    val ICON_OF_NOTHINGNESS = SpellcastersReagentFlavorItem(RegisterAttribute.MAGIC_RESISTANCE,
        EntityAttributeModifier(UUID.fromString("30e7f31c-deb9-11ed-b5ea-0242ac120002"),"nothingness_icon_modifier",0.15,EntityAttributeModifier.Operation.ADDITION),
        FabricItemSettings()).also{ regItem["icon_of_nothingness"] = it}
    //model tex lang
    val ICON_OF_THE_SAINT = SpellcastersReagentFlavorItem(EntityAttributes.GENERIC_MAX_HEALTH,
        EntityAttributeModifier(UUID.fromString("cde55f4a-dec0-11ed-b5ea-0242ac120002"),"saint_icon_modifier",2.0,EntityAttributeModifier.Operation.ADDITION),
        FabricItemSettings()).also{ regItem["icon_of_the_saint"] = it}
    //model tex lang
    val ICON_OF_FLAMES = SpellcastersReagentFlavorItem(RegisterAttribute.SPELL_COOLDOWN,
        EntityAttributeModifier(UUID.fromString("30e7f1fa-deb9-11ed-b5ea-0242ac120002"),"flame_icon_modifier",-0.15,EntityAttributeModifier.Operation.MULTIPLY_TOTAL),
        FabricItemSettings()).also{ regItem["icon_of_flames"] = it}
    //model tex lang
    val ICON_OF_KNOWLEDGE = SpellcastersReagentFlavorItem(RegisterAttribute.PLAYER_EXPERIENCE,
        EntityAttributeModifier(UUID.fromString("30e7f77c-deb9-11ed-b5ea-0242ac120002"),"knowledge_icon_modifier",0.15,EntityAttributeModifier.Operation.ADDITION),
        FabricItemSettings()).also{ regItem["icon_of_knowledge"] = it}
    //model tex lang
    val ICON_OF_THE_BLADE = SpellcastersReagentFlavorItem(EntityAttributes.GENERIC_ATTACK_DAMAGE,
        EntityAttributeModifier(UUID.fromString("30e7f8f8-deb9-11ed-b5ea-0242ac120002"),"blade_icon_modifier",2.0,EntityAttributeModifier.Operation.ADDITION),
        FabricItemSettings()).also{ regItem["icon_of_the_blade"] = it}
    //model tex lang
    val ICON_OF_DEPRAVITY = SpellcastersReagentFlavorItem(RegisterAttribute.SPELL_DAMAGE,
        EntityAttributeModifier(UUID.fromString("30e7fa24-deb9-11ed-b5ea-0242ac120002"),"depravity_icon_modifier",0.2,EntityAttributeModifier.Operation.MULTIPLY_TOTAL),
        FabricItemSettings()).also{ regItem["icon_of_depravity"] = it}
    //model tex lang
    val ICON_OF_THE_WARRIOR = SpellcastersReagentFlavorItem(RegisterAttribute.DAMAGE_MULTIPLICATION,
        EntityAttributeModifier(UUID.fromString("30e7f0ba-deb9-11ed-b5ea-0242ac120002"),"warrior_icon_modifier",-0.15,EntityAttributeModifier.Operation.MULTIPLY_TOTAL),
        FabricItemSettings()).also{ regItem["icon_of_the_warrior"] = it}
    //model tex lang
    val ICON_OF_THE_CHAMPION = SpellcastersReagentFlavorItem(EntityAttributes.ARMOR_TOUGHNESS,
        EntityAttributeModifier(UUID.fromString("30e7eca0-deb9-11ed-b5ea-0242ac120002"),"champion_icon_modifier",3.0,EntityAttributeModifier.Operation.ADDITION),
        FabricItemSettings()).also{ regItem["icon_of_the_champion"] = it}

    //misc
    //item model tex lang
    val ANCIENT_TABLET = AncientTabletItem(FabricItemSettings().maxCount(1)).also{ regItem["ancient_tablet"] = it}
    //item model tex lang
    val ARCHAEOLOGISTS_NOTEBOOK = ArchaeologistsNotebookItem(FabricItemSettings().maxCount(1)).also{ regItem["ancient_tablet"] = it}
    //item model tex lang
    val BOOK_OF_LEGEND = BookOfLegendItem(FabricItemSettings().maxCount(8).rarity(Rarity.EPIC)).withGlint() .also{ regItem["book_of_legend"] = it}
    
    //tools and weapons
    val CELESTIAL_TRIDENT = CelestialTridentItem(FabricItemSettings().maxDamage(2650).rarity(Rarity.EPIC)).also{ regItem["celestial_trident"] = it}
    //tex recipe
    val CHAMPIONS_TRIDENT = ChampionsTridentItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(1250).rarity(Rarity.RARE)).also{ regItem["champions_trident"] = it}
    val CRYSTALLINE_ARROW = CrystallineArrowItem(FabricItemSettings()).also{ regItem["crystalline_arrow"] = it}
    val IMBUED_ARROW = ImbuedArrowItem(FabricItemSettings()).also{ regItem["imbued_arrow"] = it}
    val GEM_AND_STEEL = GemAndSteelItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(32)).also{ regItem["gem_and_steel"] = it}
    val STEEL_AXE = AxeItem(SteelToolMaterial,5.0f,-3.0f,AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT)).also{ regItem["steel_axe"] = it}
    val STEEL_HOE = CustomHoeItem(SteelToolMaterial,-3,-3.0f,AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT)).also{ regItem["steel_hoe"] = it}
    val STEEL_PICKAXE = PickaxeItem(SteelToolMaterial,1,-2.8f,AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT)).also{ regItem["steel_pickaxe"] = it}
    val STEEL_SHOVEL = ShovelItem(SteelToolMaterial,1.5f,-3.0f,AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT)).also{ regItem["steel_shovel"] = it}
    val STEEL_SWORD = SwordItem(SteelToolMaterial,3,-2.4f,AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT)).also{ regItem["steel_sword"] = it}
    val NIHIL_BLADE = NihilBladeItem(FabricItemSettings()).also{ regItem["nihil_blade"] = it}
    val SOULSPIKE = SoulspikeItem(FabricItemSettings()).also{ regItem["soulspike"] = it}

    //trinkets
    //shield-code
    val CADUCEUS = SpecialityOffhandItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(450), scepterModifiers = listOf(RegisterModifier.HEALING.modifierId, RegisterModifier1.HEALERS_REWARD.modifierId)) .also{ regItem["caduceus"] = it}
    val CROWN_OF_SORROWS = CrownOfSorrowsItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(240)).also{ regItem["crown_of_sorrows"] = it}
    val DIVINE_CORONET = DivineCoronetItem(FabricItemSettings().maxDamage(480)).withGlint().also{ regItem["divine_coronet"] = it}
    val PENDANT_OF_MEMORIES = PendantOfMemoriesItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(250)).also{ regItem["pendant_of_memories"] = it}
    val RING_OF_SOULS = RingOfSoulsItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(240)).also{ regItem["ring_of_souls"] = it}
    val STEEL_AMULET = SteelJewelryItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxCount(1)) .also{ regItem["steel_amulet"] = it}
    val STEEL_HEADBAND = SteelJewelryItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxCount(1)) .also{ regItem["steel_headband"] = it}
    val STEEL_RING = SteelJewelryItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxCount(1)) .also{ regItem["steel_ring"] = it}
    val STEEL_WARD = SteelWardItem(
        EntityAttributes.GENERIC_ARMOR,
        EntityAttributeModifier(UUID.fromString("1f6875e4-d167-11ed-afa1-0242ac120002"),"steel_ward_modifier",1.2, EntityAttributeModifier.Operation.ADDITION),
        AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(424)).also{ regItem["steel_ward"] = it}

    //////////////////////////////

    /*val AI_GROUP: ItemGroup by lazy{
        registerItemGroup()
    }*/

    fun registerItemGroup(): ItemGroup{
        return FabricItemGroup.builder(Identifier(IA.MOD_ID,"ia_group"))
            .displayName(Text.translatable("itemGroup.imbued_ascendancy.is_group"))
            .icon { ItemStack(RegisterBlock.CELESTIAL_TRIDENT.asItem()) }
            .entries { _, entries, _ ->
                entries.addAll(regItem.values.stream().map { item -> ItemStack(item) }.toList())
                entries.addAll(RegisterArmor.regArmor.values.stream().map { item -> ItemStack(item) }.toList())
                entries.addAll(RegisterBlock.regBlock.values.stream()
                    .filter { block -> true}
                    .map { block -> ItemStack(block.asItem()) }
                    .toList())

            }.build()
    }

    fun registerAll() {

        for (k in regItem.keys){
            Registry.register(Registries.ITEM,Identifier(IA.MOD_ID,k), regItem[k])
        }
        /*val group = AI_GROUP*/
    }
}
