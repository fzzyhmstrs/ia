@file:Suppress("MemberVisibilityCanBePrivate")

package me.fzzyhmstrs.imbued_ascendancy.registry

import me.fzzyhmstrs.imbued_ascendancy.IA
import me.fzzyhmstrs.imbued_ascendancy.armor.GarnetArmorMaterial
import me.fzzyhmstrs.imbued_ascendancy.armor.GlowingArmorMaterial
import me.fzzyhmstrs.imbued_ascendancy.armor.VoidMailArmorMaterial
import me.fzzyhmstrs.imbued_ascendancy.item.armor.CelestialArmorItem
import me.fzzyhmstrs.imbued_ascendancy.item.armor.SpellbladesArmorArmorItem
import me.fzzyhmstrs.imbued_ascendancy.item.armor.VoidMailArmorItem
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object RegisterArmor {

    internal val regArmor: MutableMap<String,Item> = mutableMapOf()

    val GARNET_ARMOR_MATERIAL = GarnetArmorMaterial()
    val GARNET_HELMET = ArmorItem(GARNET_ARMOR_MATERIAL, EquipmentSlot.HEAD,Item.Settings()).also { regArmor["garnet_helmet"] = it }
    val GARNET_CHESTPLATE = ArmorItem(GARNET_ARMOR_MATERIAL, EquipmentSlot.CHEST,Item.Settings()).also { regArmor["garnet_chestplate"] = it }
    val GARNET_LEGGINGS = ArmorItem(GARNET_ARMOR_MATERIAL, EquipmentSlot.LEGS,Item.Settings()).also { regArmor["garnet_leggings"] = it }
    val GARNET_BOOTS = ArmorItem(GARNET_ARMOR_MATERIAL, EquipmentSlot.FEET,Item.Settings()).also { regArmor["garnet_boots"] = it }

    val GLOWING_ARMOR_MATERIAL = GlowingArmorMaterial()
    val GLOWING_HELMET = ArmorItem(GLOWING_ARMOR_MATERIAL, EquipmentSlot.HEAD,Item.Settings()).also { regArmor["glowing_helmet"] = it }
    val GLOWING_CHESTPLATE = ArmorItem(GLOWING_ARMOR_MATERIAL, EquipmentSlot.CHEST,Item.Settings()).also { regArmor["glowing_chestplate"] = it }
    val GLOWING_LEGGINGS = ArmorItem(GLOWING_ARMOR_MATERIAL, EquipmentSlot.LEGS,Item.Settings()).also { regArmor["glowing_leggings"] = it }
    val GLOWING_BOOTS = ArmorItem(GLOWING_ARMOR_MATERIAL, EquipmentSlot.FEET,Item.Settings()).also { regArmor["glowing_boots"] = it }

    val CELESTIAL_HELM = CelestialArmorItem(EquipmentSlot.HEAD,Item.Settings()).also { regArmor["celestial_helmet"] = it }
    val CELESTIAL_PLATE = CelestialArmorItem(EquipmentSlot.CHEST,Item.Settings()).also { regArmor["celestial_chestplate"] = it }
    val CELESTIAL_GREAVES = CelestialArmorItem(EquipmentSlot.LEGS,Item.Settings()).also { regArmor["celestial_leggings"] = it }
    val CELESTIAL_TREADS = CelestialArmorItem(EquipmentSlot.FEET,Item.Settings()).also { regArmor["celestial_boots"] = it }

    //Void Mail Set - Magic resistance - make with void gem, toss into void?
    val VOID_MAIL_HELMET = VoidMailArmorItem(EquipmentSlot.HEAD,Item.Settings()).also { regArmor["void_mail_helmet"] = it }
    val VOID_MAIL_CHESTPLATE = VoidMailArmorItem(EquipmentSlot.CHEST,Item.Settings()).also { regArmor["void_mail_chestplate"] = it }
    val VOID_MAIL_LEGGINGS = VoidMailArmorItem(EquipmentSlot.LEGS,Item.Settings()).also { regArmor["void_mail_leggings"] = it }
    val VOID_MAIL_BOOTS = VoidMailArmorItem(EquipmentSlot.FEET,Item.Settings()).also { regArmor["void_mail_boots"] = it }

    //Lapidarists Gear - Sockets, have the witch orb modifiers be socketable? The basic gems too??
    //probably going to hold off for now, so I don't have to focus on the slots API for the moment

    //Lich-kings Robes - summoning magic, takes more damage during the day
    val LICH_KINGS_HELMET = LichKingsRobesArmorItem(EquipmentSlot.HEAD,Item.Settings()).also { regArmor["lich_kings_helmet"] = it }
    val LICH_KINGS_CHESTPLATE = LichKingsRobesArmorItem(EquipmentSlot.CHEST,Item.Settings()).also { regArmor["lich_kings_chestplate"] = it }
    val LICH_KINGS_LEGGINGS = LichKingsRobesArmorItem(EquipmentSlot.LEGS,Item.Settings()).also { regArmor["lich_kings_leggings"] = it }
    val LICH_KINGS_BOOTS = LichKingsRobesArmorItem(EquipmentSlot.FEET,Item.Settings()).also { regArmor["lich_kings_boots"] = it }

    //Spellbades Armor - spell damage and cooldown
    val SPELLBLADES_HELMET = SpellbladesArmorArmorItem(EquipmentSlot.HEAD,Item.Settings()).also { regArmor["spellblades_helmet"] = it }
    val SPELLBLADES_CHESTPLATE = SpellbladesArmorArmorItem(EquipmentSlot.CHEST,Item.Settings()).also { regArmor["spellblades_chestplate"] = it }
    val SPELLBLADES_LEGGINGS = SpellbladesArmorArmorItem(EquipmentSlot.LEGS,Item.Settings()).also { regArmor["spellblades_leggings"] = it }
    val SPELLBLADES_BOOTS = SpellbladesArmorArmorItem(EquipmentSlot.FEET,Item.Settings()).also { regArmor["spellblades_boots"] = it }

    //Champions Plate - armor toughness and boost defensive spells

    //Scholars Vestments - Player XP and spell XP

    //Elementalists Garb - elemental spells, mana cost

    //Eternity Set - health and spell duration/range, rare finds in dungeons

    fun registerAll() {
        for (entry in regArmor) {
            Registry.register(Registries.ITEM, Identifier(IA.MOD_ID, entry.key), entry.value)
        }
    }
}
