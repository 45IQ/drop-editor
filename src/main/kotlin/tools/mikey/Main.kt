package tools.mikey

import tools.mikey.runescape.init
import java.lang.invoke.MethodHandles


val jsonPath: String = MethodHandles.lookup().lookupClass().getResource("/json/").path
//val yamlPath: String = MethodHandles.lookup().lookupClass().getResource("/yaml/").path
val cachePath: String = MethodHandles.lookup().lookupClass().getResource("/cache/").path


fun main() {
//    loadDrops()
//    (30..34).forEach { println(it)}
    init()
}