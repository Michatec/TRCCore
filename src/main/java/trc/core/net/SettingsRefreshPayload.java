package trc.core.net;

import io.netty.buffer.ByteBuf;

import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import trc.core.TRCCore;

public record SettingsRefreshPayload() implements CustomPacketPayload {
    public static final SettingsRefreshPayload INSTANCE = new SettingsRefreshPayload();

    public static final CustomPacketPayload.Type<SettingsRefreshPayload> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(TRCCore.MOD_ID, "settings_refresh"));

    public static final StreamCodec<ByteBuf, SettingsRefreshPayload> CODEC =
            TolerantCodec.of("settings_refresh", StreamCodec.unit(INSTANCE), INSTANCE);

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() { return TYPE; }
}
