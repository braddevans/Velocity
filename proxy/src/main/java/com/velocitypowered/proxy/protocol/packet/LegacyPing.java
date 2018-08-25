package com.velocitypowered.proxy.protocol.packet;

import com.velocitypowered.proxy.protocol.MinecraftPacket;
import com.velocitypowered.proxy.protocol.ProtocolConstants;
import io.netty.buffer.ByteBuf;

public class LegacyPing implements MinecraftPacket {
    @Override
    public void decode(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void encode(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion) {
        throw new UnsupportedOperationException();
    }
}
