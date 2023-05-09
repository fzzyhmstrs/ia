package me.fzzyhmstrs.imbued_ascendancy.map

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.mojang.blaze3d.systems.RenderSystem
import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import me.fzzyhmstrs.imbued_ascendancy.IA
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.registry.Registries
import net.minecraft.resource.Resource
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import net.minecraft.util.math.ChunkPos

class Tile(private val texture: Identifier, private val highCon: Identifier, private val u: Int, private val v: Int,biomeId: Identifier,val isStructure: Boolean = false): BaseTile(biomeId){
    
        override fun draw(matrices: MatrixStack, x: Int, y: Int, hc: Boolean) {
            RenderSystem.setShader { GameRenderer.getPositionTexProgram() }
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
            RenderSystem.setShaderTexture(0, if (hc) highCon else texture)
            drawTexture(matrices, x, y, u.toFloat(), v.toFloat(),16,16, 64, 64)
        }
        
        override fun toJson(): JsonObject{
            val json = JsonObject()
            json.addProperty("type", BASIC)
            json.addProperty("t", texture.toString())
            json.addProperty("hc",highCon.toString())
            json.addProperty("u",v)
            json.addProperty("v",v)
            return json
        }
    }
