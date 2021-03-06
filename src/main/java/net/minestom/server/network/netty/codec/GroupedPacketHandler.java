package net.minestom.server.network.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.minestom.server.network.netty.packet.FramedPacket;

public class GroupedPacketHandler extends MessageToByteEncoder<FramedPacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx, FramedPacket msg, ByteBuf out) {
        final ByteBuf packet = msg.body;

        out.setBytes(0, packet, 0, packet.writerIndex());
        out.writerIndex(packet.writerIndex());
    }

    @Override
    protected ByteBuf allocateBuffer(ChannelHandlerContext ctx, FramedPacket msg, boolean preferDirect) {
        final int size = msg.body.writerIndex();
        if (preferDirect) {
            return ctx.alloc().directBuffer(size, size);
        } else {
            return ctx.alloc().heapBuffer(size, size);
        }
    }

}
