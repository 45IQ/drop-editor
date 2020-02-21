package tools.mikey.runescape

data class DropDefinition(val id: Int, val count: MutableList<Int>, val chance: Int)
data class Drop(val npcIds: MutableList<Int>, val drops: MutableList<DropDefinition>)