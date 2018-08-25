package com.velocitypowered.api.event.player;

import com.velocitypowered.api.proxy.InboundConnection;
import org.checkerframework.checker.nullness.qual.Nullable;

import com.google.common.base.Preconditions;
import com.velocitypowered.api.util.GameProfile;

/**
 * This event is fired after the {@link com.velocitypowered.api.event.connection.PreLoginEvent} in order to set up the
 * game profile for the user. This can be used to configure a custom profile for a user, i.e. skin replacement.
 */
public class GameProfileRequestEvent {
    private final String username;
    private final InboundConnection connection;
    private final GameProfile originalProfile;
    private final boolean onlineMode;
    private GameProfile gameProfile;

    public GameProfileRequestEvent(InboundConnection connection, GameProfile originalProfile, boolean onlineMode) {
        this.connection = Preconditions.checkNotNull(connection, "connection");
        this.originalProfile = Preconditions.checkNotNull(originalProfile, "originalProfile");
        this.username = originalProfile.getName();
        this.onlineMode = onlineMode;
    }

    public InboundConnection getConnection() {
        return connection;
    }

    public String getUsername() {
        return username;
    }
    
    public GameProfile getOriginalProfile() {
        return originalProfile;
    }
    
    public boolean isOnlineMode() {
        return onlineMode;
    }

    /**
     * Returns the game profile that will be used to initialize the connection with. Should no profile be currently
     * specified, the one generated by the proxy (for offline mode) or retrieved from the Mojang session servers (for
     * online mode) will be returned instead.
     * @return the user's {@link GameProfile}
     */
    public GameProfile getGameProfile() {
        return gameProfile == null ? originalProfile : gameProfile;
    }

    /**
     * Sets the game profile to use for this connection. It is invalid to use this method on an online-mode connection.
     * @param gameProfile the profile to use for the connection, {@code null} uses the original profile
     */
    public void setGameProfile(@Nullable GameProfile gameProfile) {
        Preconditions.checkState(!onlineMode, "Connection is in online mode, profiles can not be faked");
        this.gameProfile = gameProfile;
    }

    @Override
    public String toString() {
        return "GameProfileRequestEvent{"+
                    "username=" + username +
                    ", gameProfile=" + gameProfile +
                    "}";
    }
    
    
}
