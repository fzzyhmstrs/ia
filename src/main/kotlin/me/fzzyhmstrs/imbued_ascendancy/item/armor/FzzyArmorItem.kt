package me.fzzyhmstrs.imbued_ascendancy.item.armor

import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import me.fzzyhmstrs.imbued_ascendancy.armor.FzzyArmorMaterial
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.item.ArmorItem
import java.util.*


open class FzzyArmorItem(material: FzzyArmorMaterial, slot: EquipmentSlot, settings: Settings) : ArmorItem(material, slot, settings) {

    private val MODIFIERS = arrayOf(
        UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"),
        UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),
        UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),
        UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")
    )
    private val fzzyAttributeModifiers: Multimap<EntityAttribute, EntityAttributeModifier>
    private val protection: Double
    private val tough: Float

    init{

        this.protection = material.getFzzyProtectionValue(slot)
        this.tough = material.toughness
        val uUID = MODIFIERS[slot.entitySlotId]
        val builder = ImmutableMultimap.builder<EntityAttribute, EntityAttributeModifier>()
        builder.put(
            EntityAttributes.GENERIC_ARMOR,
            EntityAttributeModifier(
                uUID,
                "Armor modifier",
                this.protection,
                EntityAttributeModifier.Operation.ADDITION
            )
        )
        if (material.toughness > 0) {
            builder.put(
                EntityAttributes.GENERIC_ARMOR_TOUGHNESS,
                EntityAttributeModifier(
                    uUID,
                    "Armor toughness",
                    tough.toDouble(),
                    EntityAttributeModifier.Operation.ADDITION
                )
            )
        }
        if (material.knockbackResistance > 0) {
            builder.put(
                EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,
                EntityAttributeModifier(
                    uUID,
                    "Armor knockback resistance",
                    material.knockbackResistance.toDouble(),
                    EntityAttributeModifier.Operation.ADDITION
                )
            )
        }
        fzzyAttributeModifiers = builder.build()
    }



}