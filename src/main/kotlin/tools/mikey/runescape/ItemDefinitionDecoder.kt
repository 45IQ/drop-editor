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
    for (i in 0 until itemCount) {
        indices[i] = offset
        offset += indexBuffer.readUnsignedShort()
    }

    /**
     * Decode and store each itemDefinition
     */
    val itemDefinitions = ArrayList<ItemDefinition>(itemCount)

    for (i in 0 until itemCount) {
        itemDefinitions.add(decode(dataBuffer))
    }
}

fun decode(buffer: InputStream): ItemDefinition {
    val itemDefinition = ItemDefinition()
    while (true) {
        when (val opcode = buffer.readUnsignedByte()) {
            0 -> return itemDefinition
            1 -> itemDefinition.inventoryModel = buffer.readUnsignedByte()
            2 -> {
                itemDefinition.name = buffer.readString317()
            }
            3 -> itemDefinition.description = buffer.readString317()
            4 -> itemDefinition.modelZoom = buffer.readUnsignedShort()
            5 -> itemDefinition.pitch = buffer.readUnsignedShort()
            6 -> itemDefinition.roll = buffer.readUnsignedShort()
            7 -> {
                itemDefinition.translateX = buffer.readUnsignedShort()
                if (itemDefinition.translateY > 32767) itemDefinition.translateX -= 0x10000
            }
            8 -> {
                itemDefinition.translateY = buffer.readUnsignedShort()
                if (itemDefinition.translateY > 32767) itemDefinition.translateY -= 0x10000
            }
            11 -> itemDefinition.stackable = true
            12 -> itemDefinition.value = buffer.readInt()
            16 -> itemDefinition.requiresMembership = true
            23 -> {
                itemDefinition.maleEquipMain = buffer.readUnsignedShort()
                itemDefinition.maleEquipTranslateY = buffer.readUnsignedShort()
            }
            24 -> itemDefinition.maleEquipAttachment = buffer.readUnsignedShort()
            25 -> {
                itemDefinition.femaleEquipMain = buffer.readUnsignedShort()
                itemDefinition.femaleEquipTranslateY = buffer.readUnsignedShort()
            }
            26 -> {
                itemDefinition.femaleEquipAttachment = buffer.readUnsignedShort()
            }
            in 30..34 -> {
                val sceneActions = arrayOfNulls<String>(5)
                sceneActions[opcode - 30] = buffer.readString317()
                if (sceneActions[opcode - 30] == "hidden") {
                    sceneActions[opcode - 30] = null
                }
                println(sceneActions[opcode - 30])
                itemDefinition.sceneActions.addAll(sceneActions)
            }
            in 35..39 -> {
                val widgetActions = arrayOfNulls<String>(5)
                widgetActions[opcode - 35] = buffer.readString317()
                itemDefinition.widgetActions.addAll(widgetActions)
            }
            40 -> {
                val length = buffer.readUnsignedByte()
                for (i in 0 until length) {
                    itemDefinition.destColor.add(i, buffer.readUnsignedShort())
                    itemDefinition.srcColor.add(i, buffer.readUnsignedShort())
                }
            }
            41 -> {
                val length = buffer.readUnsignedByte()
                val srcTexture = arrayOfNulls<String>(length)
                val destTexture = arrayOfNulls<String>(length)
                for (i in 0 until length) {
                    itemDefinition.srcTexture.add(i, buffer.readUnsignedShort())
                    itemDefinition.destTexture.add(i, buffer.readUnsignedShort())
                }
            }
            42 -> {
                buffer.readUnsignedByte()
            }
            65 -> itemDefinition.tradable = true
            78 -> itemDefinition.maleEquipEmblem = buffer.readUnsignedShort()
            79 -> itemDefinition.femaleEquipEmblem = buffer.readUnsignedShort()
            90 -> itemDefinition.maleDialogueHead = buffer.readUnsignedShort()
            91 -> itemDefinition.femaleDialogueHead = buffer.readUnsignedShort()
            92 -> itemDefinition.maleDialogueHeadgear = buffer.readUnsignedShort()
            93 -> itemDefinition.femaleDialogueHeadgear = buffer.readUnsignedShort()
            95 -> itemDefinition.yaw = buffer.readUnsignedShort()
            97 -> itemDefinition.unNotedId = buffer.readUnsignedShort()
            98 -> itemDefinition.notedId = buffer.readUnsignedShort()
            in 100..109 -> {
                val stackVariantId = Array(10) { 0 }
                val stackVariantSize = Array(10) { 0 }
                stackVariantId[opcode - 100] = buffer.readUnsignedShort()
                stackVariantSize[opcode - 100] = buffer.readUnsignedShort()
                itemDefinition.stackVariantId.addAll(stackVariantId)
                itemDefinition.stackVariantSize.addAll(stackVariantSize)

            }
            110 -> itemDefinition.modelScaleX = buffer.readUnsignedShort()
            111 -> itemDefinition.modelScaleY = buffer.readUnsignedShort()
            112 -> itemDefinition.modelScaleZ = buffer.readUnsignedShort()
            113 -> itemDefinition.ambient = buffer.readByte()
            114 -> itemDefinition.contrast = buffer.readByte() * 5
            115 -> itemDefinition.teamId = buffer.readUnsignedByte()
            139 -> itemDefinition.boughtId = buffer.readUnsignedShort()
            140 -> itemDefinition.boughtTemplateId = buffer.readUnsignedShort()
            148 -> itemDefinition.placeholderId = buffer.readUnsignedShort()
            149 -> itemDefinition.placeholderTemplateId = buffer.readUnsignedShort()
        }

    }
}