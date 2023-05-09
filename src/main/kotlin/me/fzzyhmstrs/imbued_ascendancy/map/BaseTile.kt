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

open class BaseTile(){

      open fun draw(matrices: MatrixStack, x: Int, y: Int, hc: Boolean) {}

      open fun toJson(): JsonObject{
          val json = JsonObject()
          json.addProperty("type", EMPTY)
          return json
      }

      companion object{
          protected val EMPTY_TILE = BaseTile()
          val EMPTY = "empty"
          val BASIC = "basic"

          internal fun fromJson(json: JsonElement): BaseTile {
              if (!json.isJsonObject) return EMPTY_TILE
              val obj = json.asJsonObject
              val typeEl = obj.get("type")
              if (typeEl == null || !typeEl.isJsonPrimitive) return EMPTY_TILE
              val type = typeEl.asString
              if (type == EMPTY) return EMPTY_TILE
              if (type == BASIC) {
                  val textureEl = obj.get("t")
                  if (textureEl == null || !textureEl.isJsonPrimitive) return EMPTY_TILE
                  val texture = Identifier.tryParse(textureEl.asString) ?: return EMPTY_TILE
                  val hcEl = obj.get("hc")
                  if (hcEl == null || !hcEl.isJsonPrimitive) return EMPTY_TILE
                  val highContrast = Identifier.tryParse(hcEl.asString) ?: texture
                  val uEl = obj.get("u")
                  if (uEl == null || !uEl.isJsonPrimitive) return EMPTY_TILE
                  val u = uEl.asInt
                  val vEl = obj.get("v")
                  if (vEl == null || !vEl.isJsonPrimitive) return EMPTY_TILE
                  val v = vEl.asInt
                  return Tile(texture, highContrast, u, v)
              }
              return EMPTY_TILE
          }

      }
  }
