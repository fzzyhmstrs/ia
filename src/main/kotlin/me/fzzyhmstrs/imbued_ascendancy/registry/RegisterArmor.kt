@file:Suppress("MemberVisibilityCanBePrivate")

package me.fzzyhmstrs.imbued_ascendancy.registry

import me.fzzyhmstrs.imbued_ascendancy.IA
import me.fzzyhmstrs.imbued_ascendancy.armor.GarnetArmorMaterial
import me.fzzyhmstrs.imbued_ascendancy.armor.GlowingArmorMaterial
import me.fzzyhmstrs.imbued_ascendancy.item.armor.CelestialArmorItem
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object RegisterArmor {

    internal val regArmor: MutableList<Item> = mutableListOf()

    val GARNET_ARMOR_MATERIAL = GarnetArmorMaterial()
    val GARNET_HELMET = ArmorItem(GARNET_ARMOR_MATERIAL, EquipmentSlot.HEAD,Item.Settings()).also { regArmor.add(it) }
    val GARNET_CHESTPLATE = ArmorItem(GARNET_ARMOR_MATERIAL, EquipmentSlot.CHEST,Item.Settings()).also { regArmor.add(it) }
    val GARNET_LEGGINGS = ArmorItem(GARNET_ARMOR_MATERIAL, EquipmentSlot.LEGS,Item.Settings()).also { regArmor.add(it) }
    val GARNET_BOOTS = ArmorItem(GARNET_ARMOR_MATERIAL, EquipmentSlot.FEET,Item.Settings()).also { regArmor.add(it) }

    val GLOWING_ARMOR_MATERIAL = GlowingArmorMaterial()
    val GLOWING_HELMET = ArmorItem(GLOWING_ARMOR_MATERIAL, EquipmentSlot.HEAD,Item.Settings()).also { regArmor.add(it) }
    val GLOWING_CHESTPLATE = ArmorItem(GLOWING_ARMOR_MATERIAL, EquipmentSlot.CHEST,Item.Settings()).also { regArmor.add(it) }
    val GLOWING_LEGGINGS = ArmorItem(GLOWING_ARMOR_MATERIAL, EquipmentSlot.LEGS,Item.Settings()).also { regArmor.add(it) }
    val GLOWING_BOOTS = ArmorItem(GLOWING_ARMOR_MATERIAL, EquipmentSlot.FEET,Item.Settings()).also { regArmor.add(it) }

    val CELESTIAL_HELM = CelestialArmorItem(EquipmentSlot.HEAD,Item.Settings()).also { regArmor.add(it) }
    val CELESTIAL_PLATE = CelestialArmorItem(EquipmentSlot.CHEST,Item.Settings()).also { regArmor.add(it) }
    val CELESTIAL_GREAVES = CelestialArmorItem(EquipmentSlot.LEGS,Item.Settings()).also { regArmor.add(it) }
    val CELESTIAL_TREADS = CelestialArmorItem(EquipmentSlot.FEET,Item.Settings()).also { regArmor.add(it) }

    //Void Mail Set - Magic resistance - make with void gem, toss into void?

    //Lapidarists Gear - Sockets, have the witch orb modifiers be socketable? The basic gems too??

    //Lich-kings Robes - summoning magic

    //Spellbades Armor - spell damage and cooldown

    //Champions Plate - armor toughness and boost defensive spells

    //Scholars Vestments - Player XP and spell XP

    //Elementalists Garb - elemental spells, mana cost

    //Eternity Set - health and spell duration/range, rare finds in dungeons

    fun registerAll() {
        Registry.register(Registries.ITEM, Identifier(IA.MOD_ID,"garnet_helmet"), GARNET_HELMET)
        Registry.register(Registries.ITEM, Identifier(IA.MOD_ID,"garnet_chestplate"), GARNET_CHESTPLATE)
        Registry.register(Registries.ITEM, Identifier(IA.MOD_ID,"garnet_leggings"), GARNET_LEGGINGS)
        Registry.register(Registries.ITEM, Identifier(IA.MOD_ID,"garnet_boots"), GARNET_BOOTS)
        Registry.register(Registries.ITEM, Identifier(IA.MOD_ID,"glowing_helmet"), GLOWING_HELMET)
        Registry.register(Registries.ITEM, Identifier(IA.MOD_ID,"glowing_chestplate"), GLOWING_CHESTPLATE)
        Registry.register(Registries.ITEM, Identifier(IA.MOD_ID,"glowing_leggings"), GLOWING_LEGGINGS)
        Registry.register(Registries.ITEM, Identifier(IA.MOD_ID,"glowing_boots"), GLOWING_BOOTS)
        Registry.register(Registries.ITEM, Identifier(IA.MOD_ID,"celestial_helmet"), CELESTIAL_HELM)
        Registry.register(Registries.ITEM, Identifier(IA.MOD_ID,"celestial_chestplate"), CELESTIAL_PLATE)
        Registry.register(Registries.ITEM, Identifier(IA.MOD_ID,"celestial_leggings"), CELESTIAL_GREAVES)
        Registry.register(Registries.ITEM, Identifier(IA.MOD_ID,"celestial_boots"), CELESTIAL_TREADS)
    }
}
