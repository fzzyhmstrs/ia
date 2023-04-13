package me.fzzyhmstrs.imbued_ascendancy.config

import me.fzzyhmstrs.fzzy_config.config_util.ConfigClass
import me.fzzyhmstrs.fzzy_config.config_util.ReadMeText
import me.fzzyhmstrs.fzzy_config.config_util.SyncedConfigHelperV1.readOrCreateAndValidate
import me.fzzyhmstrs.fzzy_config.config_util.SyncedConfigWithReadMe
import me.fzzyhmstrs.fzzy_config.validated_field.ValidatedFloat
import me.fzzyhmstrs.fzzy_config.validated_field.ValidatedInt
import me.fzzyhmstrs.fzzy_config.validated_field.map.ValidatedStringBoolMap
import me.fzzyhmstrs.imbued_ascendancy.IA
import net.minecraft.util.Identifier

object IaConfig:
    SyncedConfigWithReadMe(
        IA.MOD_ID,
        "README.txt",
        IA.MOD_ID,
        Header.Builder()
            .box("ia.readme.main_header.title")
            .space()
            .add("ia.readme.main_header.changelog")
            .literal()
            .add("1.19.3-01: Initial release of Imbued Ascendancy.")
            .space()
            .translate()
            .add("ia.readme.main_header.note")
            .space()
            .space()
            .build())
{

    private val itemsHeader = buildSectionHeader("items")

    class Items: ConfigClass(itemsHeader){

        var realityTravelTarget = ValidatedInt(1000,10000,1)

    }

    private val modifiersHeader = buildSectionHeader("modifiers")

    class Modifiers: ConfigClass(modifiersHeader){

        fun isModifierEnabled(id: Identifier): Boolean{
            return enabledModifiers[id.toString()]?:false
        }

        var enabledModifiers = ValidatedStringBoolMap(
            mapOf(),
            {str,_ -> Identifier.tryParse(str) != null},
            "Needs to be a valid modifier identifier string"
        )

        @ReadMeText("ia.readme.modifiers.voidShroudedMultiplier")
        var voidShroudedMultiplier = ValidatedFloat(0.84f,1.0f)
        var nihilBladeNothingnessChance = ValidatedFloat(0.15f,1.0f)

    }





    var items = readOrCreateAndValidate("items_v0.json", base = IA.MOD_ID) {Items()}
    var modifiers = readOrCreateAndValidate("modifiers_v0.json", base = IA.MOD_ID) {Modifiers()}

    private fun buildSectionHeader(name:String): Header{
        return Header.Builder().space().underoverscore("ia.readme.header.$name").add("ia.readme.header.$name.desc").space().build()
    }
}