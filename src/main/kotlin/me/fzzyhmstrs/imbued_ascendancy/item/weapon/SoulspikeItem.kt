package me.fzzyhmstrs.imbued_ascendancy.item.weapon

import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import me.fzzyhmstrs.fzzy_core.modifier_util.ModifierHelperType
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterModifier
import me.fzzyhmstrs.imbued_ascendancy.tool.NihilBladeMaterial
import net.minecraft.item.SwordItem
import net.minecraft.util.Identifier

class SoulspikeItem(settings: Settings): SwordItem(SoulspikeMaterial,3,-2.4f,settings), Modifiable {

    override fun defaultModifiers(type: ModifierHelperType): MutableList<Identifier> {
        if (type == EquipmentModifierHelper.getType()){
            return mutableListOf(RegisterModifier.DREAM_RENDING.modifierId)
        }
        return super.defaultModifiers(type)
    }

}
