@file:Suppress("PropertyName")

package me.fzzyhmstrs.imbued_ascendancy

import com.llamalad7.mixinextras.MixinExtrasBootstrap
import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterBlock
import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterEntity
import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterItem
import me.fzzyhmstrs.imbued_ascendancy.registry.RegisterRenderer
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.api.ModInitializer
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint
import kotlin.random.Random


object IA: ModInitializer {
    const val MOD_ID = "imbued_ascendancy"

    override fun onInitialize() {
        RegisterItem.registerAll()
        RegisterBlock.registerAll()
        RegisterEntity.registerAll()
    }

    fun iaRandom(): Random{
        return Random(System.currentTimeMillis())
    }
}

@Environment(value = EnvType.CLIENT)
object IAClient: ClientModInitializer{

    override fun onInitializeClient() {
        RegisterRenderer.registerAll()
    }

    fun iaRandom(): Random{
        return Random(System.currentTimeMillis())
    }
}

object IAPreLaunch: PreLaunchEntrypoint{

    override fun onPreLaunch() {
        MixinExtrasBootstrap.init()
    }

}