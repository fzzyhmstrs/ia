package me.fzzyhmstrs.imbued_ascendancy.item

import com.google.common.collect.Multimap
import dev.emi.trinkets.api.SlotReference
import dev.emi.trinkets.api.TrinketItem
import me.fzzyhmstrs.fzzy_core.item_util.interfaces.Flavorful
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.world.World
import java.util.*

open class SteelJewelryItem(settings: Settings) : TrinketItem(settings), Flavorful<SteelJewelryItem> {

    override var flavor: String = ""
    override var glint: Boolean = false
    override var flavorDesc: String = ""

    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>,
        context: TooltipContext
    ) {
        super.appendTooltip(stack, world, tooltip, context)
        addFlavorText(tooltip, context)
    }

    override fun getModifiers(
        stack: ItemStack,
        slot: SlotReference,
        entity: LivingEntity,
        uuid: UUID
    ): Multimap<EntityAttribute, EntityAttributeModifier>? {
        val modifiers = super.getModifiers(stack, slot, entity, uuid)
        modifiers.put(
            EntityAttributes.GENERIC_ARMOR,
            EntityAttributeModifier(uuid, "imbued_ascendancy:armor", 0.5, EntityAttributeModifier.Operation.ADDITION)
        )
        return modifiers
    }

    override fun getFlavorItem(): SteelJewelryItem {
        return this
    }
}