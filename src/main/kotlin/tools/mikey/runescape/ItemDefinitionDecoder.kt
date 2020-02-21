package tools.mikey.runescape

import org.displee.CacheLibrary
import org.displee.CacheLibraryMode
import org.displee.io.impl.InputStream
import tools.mikey.cachePath


fun init() {

    val archive = CacheLibrary(cachePath, CacheLibraryMode.UN_CACHED).getIndex(0).getArchive(2)
    val dataBuffer = InputStream(archive.getFile("obj.dat").data)
    val indexBuffer = InputStream(archive.getFile("obj.idx").data)

    var offset = 2
    val itemCount = indexBuffer.readUnsignedShort()
    val indices = IntArray(itemCount)


    /**
     * Populate an array containing each items position in the data buffer
     */
    ((0) until itemCount).forEach { index ->
        indices[index] = offset
        offset += indexBuffer.readUnsignedShort()
    }

    /**
     * Decode and store each itemDefinition
     */
    val itemDefinitions = ArrayList<ItemDefinition>(itemCount)

    ((0) until itemCount).forEach {index ->
//        dataBuffer.offset = indices[index]
        dataBuffer.updateOffset(indices[index])
        itemDefinitions.add(decode(dataBuffer))
    }

}

private fun getOpcodeType(opcode: Int) : Opcode {
    println("opcode: $opcode")
    return OPCODES.filter { it.opcode == opcode }[0]
}

fun decode(buffer: InputStream): ItemDefinition {
    val itemDefinition = ItemDefinition()
    while (true) {
        val opcode = buffer.readUnsignedByte()
        when(opcode) {
            0 -> return ItemDefinition()
            in 30..34 -> {

            }
        }
        if(opcode == 0) {
            return ItemDefinition()
        }
        getOpcodeType(opcode).assign(itemDefinition, buffer)
    }
}