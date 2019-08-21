package fr.themode.minestom.net.packet.server.play;

import fr.adamaq01.ozao.net.Buffer;
import fr.themode.minestom.net.packet.server.ServerPacket;
import fr.themode.minestom.utils.Utils;

public class EntityMetaDataPacket implements ServerPacket {

    public int entityId;
    public Buffer data;

    @Override
    public void write(Buffer buffer) {
        Utils.writeVarInt(buffer, entityId);
        buffer.putBuffer(data);
        buffer.putByte((byte) 0xFF);
    }

    @Override
    public int getId() {
        return 0x43;
    }
}