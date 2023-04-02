@file:Suppress("unused")

package me.fzzyhmstrs.imbued_ascendancy.registry

import me.fzzyhmstrs.imbued_ascendancy.item.ImbuedArrowItem
import me.fzzyhmstrs.imbued_ascendancy.IA
import me.fzzyhmstrs.imbued_ascendancy.item.CrystallineArrowItem
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

// don't know if this is better as a class or object. as an object it allows me to call it without needing to initialize an instance of it.
object RegisterItem {

    private val regItem: MutableMap<String, Item> = mutableMapOf()


    //basic ingredients
    val CRYSTALLINE_ARROW = CrystallineArrowItem(FabricItemSettings()).also{ regItem["crystalline_arrow"] = it}
    val IMBUED_ARROW = ImbuedArrowItem(FabricItemSettings()).also{ regItem["imbued_arrow"] = it}
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
