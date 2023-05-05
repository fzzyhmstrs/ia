package me.fzzyhmstrs.imbued_ascendancy.item.weapon

import me.fzzyhmstrs.amethyst_core.item_util.DefaultAugmentSwordItem
import me.fzzyhmstrs.imbued_ascendancy.IA
import me.fzzyhmstrs.imbued_ascendancy.tool.CracklingToolMaterial
import net.minecraft.util.Identifier

class CracklingSpellbladeItem(settings: Settings): DefaultAugmentSwordItem(CracklingToolMaterial,3,-2.4f,settings) {
    override val fallbackId: Identifier = Identifier(IA.MOD_ID,"bankai")
}