package tools.mikey.runescape

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import tools.mikey.jsonPath
import java.io.File

import java.util.*

val drops = TreeMap<Int, MutableList<DropDefinition>>()

fun loadDrops() {
    val objMapper = ObjectMapper().registerModule(KotlinModule())
    val dropInformation = objMapper.readValue(File("${jsonPath}drops.json"), Array<Drop>::class.java)
    dropInformation.forEach{ element ->
      element.npcIds.forEach { npcId -> drops[npcId] = element.drops }
    }

    drops.entries.stream().forEachOrdered { entry -> println(entry) }
}
