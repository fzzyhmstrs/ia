@file:Suppress("MemberVisibilityCanBePrivate")

package me.fzzyhmstrs.imbued_ascendancy.registry

import me.fzzyhmstrs.imbued_ascendancy.IA
import me.fzzyhmstrs.imbued_ascendancy.armor.GarnetArmorMaterial
import me.fzzyhmstrs.imbued_ascendancy.armor.GlowingArmorMaterial
import me.fzzyhmstrs.imbued_ascendancy.armor.ShimmeringArmorMaterial
import me.fzzyhmstrs.imbued_ascendancy.armor.VoidMailArmorMaterial
import me.fzzyhmstrs.imbued_ascendancy.item.armor.*
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity

object RegisterArmor {

    internal val regArmor: MutableMap<String,Item> = mutableMapOf()

    /*
    garnet <- steel
    glowing <- garnet
    shimmering = base
    celestial <- many
    void mail <- steel
    lapidarists <- steel
    lich-king <- shimmering
    spellblades <- steel
    champions <- ametrine
    warriors <- glowing
    scholars <- shimmering
    elementalists <- garnet
    clerics <- shimmering
    eternity <- N/A
    */

    //garnet armor -tex done
    val GARNET_ARMOR_MATERIAL = GarnetArmorMaterial()
    val GARNET_HELMET = ArmorItem(GARNET_ARMOR_MATERIAL, EquipmentSlot.HEAD,Item.Settings()).also { regArmor["garnet_helmet"] = it }
    val GARNET_CHESTPLATE = ArmorItem(GARNET_ARMOR_MATERIAL, EquipmentSlot.CHEST,Item.Settings()).also { regArmor["garnet_chestplate"] = it }
    val GARNET_LEGGINGS = ArmorItem(GARNET_ARMOR_MATERIAL, EquipmentSlot.LEGS,Item.Settings()).also { regArmor["garnet_leggings"] = it }
    val GARNET_BOOTS = ArmorItem(GARNET_ARMOR_MATERIAL, EquipmentSlot.FEET,Item.Settings()).also { regArmor["garnet_boots"] = it }

    //Glowing armor -tex done
    val GLOWING_ARMOR_MATERIAL = GlowingArmorMaterial()
    val GLOWING_HELMET = ArmorItem(GLOWING_ARMOR_MATERIAL, EquipmentSlot.HEAD,Item.Settings()).also { regArmor["glowing_helmet"] = it }
    val GLOWING_CHESTPLATE = ArmorItem(GLOWING_ARMOR_MATERIAL, EquipmentSlot.CHEST,Item.Settings()).also { regArmor["glowing_chestplate"] = it }
    val GLOWING_LEGGINGS = ArmorItem(GLOWING_ARMOR_MATERIAL, EquipmentSlot.LEGS,Item.Settings()).also { regArmor["glowing_leggings"] = it }
    val GLOWING_BOOTS = ArmorItem(GLOWING_ARMOR_MATERIAL, EquipmentSlot.FEET,Item.Settings()).also { regArmor["glowing_boots"] = it }

    //Shimmering robes -tex done
    val SHIMMERING_ARMOR_MATERIAL = ShimmeringArmorMaterial()
    val SHIMMERING_HOOD = ArmorItem(SHIMMERING_ARMOR_MATERIAL, EquipmentSlot.HEAD,Item.Settings()).also { regArmor["shimmering_hood"] = it }
    val SHIMMERING_ROBE = ArmorItem(SHIMMERING_ARMOR_MATERIAL, EquipmentSlot.CHEST,Item.Settings()).also { regArmor["shimmering_robe"] = it }
    val SHIMMERING_PANTS = ArmorItem(SHIMMERING_ARMOR_MATERIAL, EquipmentSlot.LEGS,Item.Settings()).also { regArmor["shimmering_pants"] = it }
    val SHIMMERING_BOOTS = ArmorItem(SHIMMERING_ARMOR_MATERIAL, EquipmentSlot.FEET,Item.Settings()).also { regArmor["shimmering_boots"] = it }

    //Celestial Armor -tex done
    val CELESTIAL_HELM = CelestialArmorItem(EquipmentSlot.HEAD,Item.Settings().rarity(Rarity.EPIC)).also { regArmor["celestial_helmet"] = it }
    val CELESTIAL_PLATE = CelestialArmorItem(EquipmentSlot.CHEST,Item.Settings().rarity(Rarity.EPIC)).also { regArmor["celestial_chestplate"] = it }
    val CELESTIAL_GREAVES = CelestialArmorItem(EquipmentSlot.LEGS,Item.Settings().rarity(Rarity.EPIC)).also { regArmor["celestial_leggings"] = it }
    val CELESTIAL_TREADS = CelestialArmorItem(EquipmentSlot.FEET,Item.Settings().rarity(Rarity.EPIC)).also { regArmor["celestial_boots"] = it }

    //Void Mail Set - Magic resistance - make with void gem, toss into void? -tex done
    val VOID_MAIL_HELMET = VoidMailArmorItem(EquipmentSlot.HEAD,Item.Settings()).also { regArmor["void_mail_helmet"] = it }
    val VOID_MAIL_CHESTPLATE = VoidMailArmorItem(EquipmentSlot.CHEST,Item.Settings()).also { regArmor["void_mail_chestplate"] = it }
    val VOID_MAIL_LEGGINGS = VoidMailArmorItem(EquipmentSlot.LEGS,Item.Settings()).also { regArmor["void_mail_leggings"] = it }
    val VOID_MAIL_BOOTS = VoidMailArmorItem(EquipmentSlot.FEET,Item.Settings()).also { regArmor["void_mail_boots"] = it }

    //Lapidarists Gear - Sockets, have the witch orb modifiers be socketable? The basic gems too??
    //probably going to hold off for now, so I don't have to focus on the slots API for the moment

    //Lich-kings Robes - summoning magic, takes more damage during the day -tex done
    val LICH_KINGS_HELMET = LichKingsRobesArmorItem(EquipmentSlot.HEAD,Item.Settings()).also { regArmor["lich_kings_helmet"] = it }
    val LICH_KINGS_CHESTPLATE = LichKingsRobesArmorItem(EquipmentSlot.CHEST,Item.Settings()).also { regArmor["lich_kings_chestplate"] = it }
    val LICH_KINGS_LEGGINGS = LichKingsRobesArmorItem(EquipmentSlot.LEGS,Item.Settings()).also { regArmor["lich_kings_leggings"] = it }
    val LICH_KINGS_BOOTS = LichKingsRobesArmorItem(EquipmentSlot.FEET,Item.Settings()).also { regArmor["lich_kings_boots"] = it }

    //Spellblades Armor - spell damage and cooldown
    val SPELLBLADES_HELMET = SpellbladesArmorArmorItem(EquipmentSlot.HEAD,Item.Settings()).also { regArmor["spellblades_helmet"] = it }
    val SPELLBLADES_CHESTPLATE = SpellbladesArmorArmorItem(EquipmentSlot.CHEST,Item.Settings()).also { regArmor["spellblades_chestplate"] = it }
    val SPELLBLADES_LEGGINGS = SpellbladesArmorArmorItem(EquipmentSlot.LEGS,Item.Settings()).also { regArmor["spellblades_leggings"] = it }
    val SPELLBLADES_BOOTS = SpellbladesArmorArmorItem(EquipmentSlot.FEET,Item.Settings()).also { regArmor["spellblades_boots"] = it }

    //Armor of the Champion - armor toughness and boost defensive spells
    val CHAMPIONS_HELMET = ArmorOfTheChampionArmorItem(EquipmentSlot.HEAD,Item.Settings()).also { regArmor["champions_helmet"] = it }
    val CHAMPIONS_CHESTPLATE = ArmorOfTheChampionArmorItem(EquipmentSlot.CHEST,Item.Settings()).also { regArmor["champions_chestplate"] = it }
    val CHAMPIONS_LEGGINGS = ArmorOfTheChampionArmorItem(EquipmentSlot.LEGS,Item.Settings()).also { regArmor["champions_leggings"] = it }
    val CHAMPIONS_BOOTS = ArmorOfTheChampionArmorItem(EquipmentSlot.FEET,Item.Settings()).also { regArmor["champions_boots"] = it }

    //Warriors Harness - damage against undead
    val WARRIORS_HELMET = WarriorsHarnessArmorItem(EquipmentSlot.HEAD,Item.Settings()).also { regArmor["warriors_helmet"] = it }
    val WARRIORS_CHESTPLATE = WarriorsHarnessArmorItem(EquipmentSlot.CHEST,Item.Settings()).also { regArmor["warriors_chestplate"] = it }
    val WARRIORS_LEGGINGS = WarriorsHarnessArmorItem(EquipmentSlot.LEGS,Item.Settings()).also { regArmor["warriors_leggings"] = it }
    val WARRIORS_BOOTS = WarriorsHarnessArmorItem(EquipmentSlot.FEET,Item.Settings()).also { regArmor["warriors_boots"] = it }

    //Scholars Vestments - Player XP and spell XP
    val SCHOLARS_HELMET = ScholarsVestmentsArmorItem(EquipmentSlot.HEAD,Item.Settings()).also { regArmor["scholars_helmet"] = it }
    val SCHOLARS_CHESTPLATE = ScholarsVestmentsArmorItem(EquipmentSlot.CHEST,Item.Settings()).also { regArmor["scholars_chestplate"] = it }
    val SCHOLARS_LEGGINGS = ScholarsVestmentsArmorItem(EquipmentSlot.LEGS,Item.Settings()).also { regArmor["scholars_leggings"] = it }
    val SCHOLARS_BOOTS = ScholarsVestmentsArmorItem(EquipmentSlot.FEET,Item.Settings()).also { regArmor["scholars_boots"] = it }

    //Elementalists Garb - elemental spells, mana cost
    val ELEMENTALISTS_HELMET = ElementalistsGarbArmorItem(EquipmentSlot.HEAD,Item.Settings()).also { regArmor["elementalists_helmet"] = it }
    val ELEMENTALISTS_CHESTPLATE = ElementalistsGarbArmorItem(EquipmentSlot.CHEST,Item.Settings()).also { regArmor["warriors_chestplate"] = it }
    val ELEMENTALISTS_LEGGINGS = ElementalistsGarbArmorItem(EquipmentSlot.LEGS,Item.Settings()).also { regArmor["warriors_leggings"] = it }
    val ELEMENTALISTS_BOOTS = ElementalistsGarbArmorItem(EquipmentSlot.FEET,Item.Settings()).also { regArmor["warriors_boots"] = it }

    //Eternity Shroud - health and spell duration/range, rare finds in dungeons - Tex Done
    val ETERNITY_HELMET = EternityShroudArmorItem(EquipmentSlot.HEAD,Item.Settings()).also { regArmor["eternity_helmet"] = it }
    val ETERNITY_CHESTPLATE = EternityShroudArmorItem(EquipmentSlot.CHEST,Item.Settings()).also { regArmor["eternity_chestplate"] = it }
    val ETERNITY_LEGGINGS = EternityShroudArmorItem(EquipmentSlot.LEGS,Item.Settings()).also { regArmor["eternity_leggings"] = it }
    val ETERNITY_BOOTS = EternityShroudArmorItem(EquipmentSlot.FEET,Item.Settings()).also { regArmor["eternity_boots"] = it }

    fun registerAll() {
        for (entry in regArmor) {
            Registry.register(Registries.ITEM, Identifier(IA.MOD_ID, entry.key), entry.value)
        }
    }
}
