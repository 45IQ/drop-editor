package tools.mikey.runescape

import com.google.common.collect.ImmutableList
import org.displee.io.impl.InputStream

/**
 * @author Mikey
 */

val OPCODES: ImmutableList<Opcode> = ImmutableList.copyOf(Opcode.values())
//val MULTI_VALUE_OPCODES: ImmutableList<MultiValueOpcode> = ImmutableList.copyOf(MultiValueOpcode.values())

enum class Opcode(val opcode: Int)  {
    INVENTORY_MODEL(1) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.inventoryModel = buffer.readUnsignedByte()
        }
    },
    NAME(2) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.name = buffer.readString317()
            println(itemDefinition.name)
        }
    },
    DESCRIPTION(3) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.description = buffer.readString317()
        }
    },
    MODEL_ZOOM(4) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.modelZoom = buffer.readUnsignedShort()
        }
    },
    PITCH(5) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.pitch = buffer.readUnsignedShort()
        }
    },
    ROLL(6) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.roll = buffer.readUnsignedShort()
        }
    },
    TRANSLATE_X(7) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.translateX = buffer.readUnsignedShort()
            if(itemDefinition.translateY > 32767) itemDefinition.translateX -= 0x10000
        }
    },
    TRANSLATE_Y(8) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.translateY = buffer.readUnsignedShort()
            if(itemDefinition.translateY > 32767) itemDefinition.translateY -= 0x10000
        }
    },
    STACKABLE(11) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.stackable = true
        }
    },
    VALUE(12) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.value = buffer.readInt()
        }
    },
    REQUIRES_MEMBERSHIP(16) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.requiresMembership = true
        }
    },
    MALE_EQUIP_MAIN(23) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.maleEquipMain = buffer.readUnsignedShort()
            itemDefinition.maleEquipTranslateY = buffer.readUnsignedShort()
        }
    },
    MALE_EQUIP_ATTACHMENT(24) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.maleEquipAttachment = buffer.readUnsignedShort()
        }
    },
    FEMALE_EQUIP_MAIN(25) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.femaleEquipMain = buffer.readUnsignedShort()
            itemDefinition.femaleEquipTranslateY = buffer.readUnsignedShort()
        }
    },
    FEMALE_EQUIP_ATTACHMENT(26) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.femaleEquipAttachment = buffer.readUnsignedShort()
        }
    },
    TRADABLE(65) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.tradable = true
        }
    },
    MALE_EQUIP_EMBLEM(78) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.maleEquipEmblem = buffer.readUnsignedShort()
        }
    },
    FEMALE_EQUIP_EMBLEM(79) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.femaleEquipEmblem = buffer.readUnsignedShort()
        }
    },
    MALE_DIALOGUE_HEAD(90) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.maleDialogueHead = buffer.readUnsignedShort()
        }
    },
    FEMALE_DIALOGUE_HEAD(91) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.femaleDialogueHead = buffer.readUnsignedShort()
        }
    },
    MALE_DIALOGUE_HEADGEAR(92) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.maleDialogueHeadgear = buffer.readUnsignedShort()
        }
    },
    FEMALE_DIALOGUE_HEADGEAR(93) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.femaleDialogueHeadgear = buffer.readUnsignedShort()
        }
    },
    YAW(95) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.yaw = buffer.readUnsignedShort()
        }
    },
    UNNOTED_ID(97) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.unNotedId = buffer.readUnsignedShort()
        }
    },
    NOTED_ID(98) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.notedId = buffer.readUnsignedShort()
        }
    },
    MODEL_SCALE_X(110) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.modelScaleX = buffer.readUnsignedShort()
        }
    },
    MODEL_SCALE_Y(11) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.modelScaleY = buffer.readUnsignedShort()

        }
    },
    MODEL_SCALE_Z(112) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.modelScaleZ = buffer.readUnsignedShort()
        }
    },
    AMBIENT(113) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.ambient = buffer.readByte()
        }
    },
    CONTRAST(114) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.contrast = buffer.readByte() * 5
        }
    },
    TEAM_ID(115) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.teamId = buffer.readUnsignedByte()
        }
    },
    BOUGHT_ID(139) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.boughtId = buffer.readUnsignedShort()
        }
    },
    BOUGHT_TEMPLATE_ID(140) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.boughtTemplateId = buffer.readUnsignedShort()
        }
    },
    PLACEHOLDER_ID(148) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.placeholderId = buffer.readUnsignedShort()
        }
    },
    PLACEHOLDER_TEMPLATE_ID(149) {
        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
            itemDefinition.placeholderTemplateId = buffer.readUnsignedShort()
        }

    };
//    SCENE_ACTIONS() {
//        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
//
//        }
//    },
//    WIDGET_ACTIONS(40) {
//        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
//            TODO("Not yet implemented")
//        }
//    },
//    SRC_COLOR(50) {
//        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
//            TODO("Not yet implemented")
//        }
//    },
//    DEST_COLOR() {
//        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
//            TODO("Not yet implemented")
//        }
//    },
//    SRC_TEXTURE() {
//        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
//            TODO("Not yet implemented")
//        }
//    },
//    DEST_TEXTURE() {
//        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
//            TODO("Not yet implemented")
//        }
//    },
//    STACK_VARIANT_ID() {
//        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
//            TODO("Not yet implemented")
//        }
//    },
//    STACK_VARIANT_SIZE() {
//        override fun assign(itemDefinition: ItemDefinition, buffer: InputStream) {
//            TODO("Not yet implemented")
//        }
//    };

    abstract fun assign(itemDefinition: ItemDefinition, buffer: InputStream)

}


/**
 * Default function that handles the assignment of ItemDefinition values
 */