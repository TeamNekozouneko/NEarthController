package net.nekozouneko.nEarthController.impl;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEffect;
import net.nekozouneko.nEarthController.wrapper.ConfigWrapper;
import org.bukkit.Effect;
import org.bukkit.entity.Player;

public class WitherSummonSoundRestriction implements PacketListener {
    @Override
    public void onPacketSend(PacketSendEvent e) {
        if (e.getPacketType() != PacketType.Play.Server.EFFECT) return;

        var wrapper = new WrapperPlayServerEffect(e);
        var witherSpawnedGlobalId = Effect.WITHER_SPAWNED.getId();

        if (wrapper.getType() != witherSpawnedGlobalId) return;
        if (!(e.getPlayer() instanceof Player player)) return;

        var squaredDistance = getSquaredDistance(player, wrapper);
        var threshold = ConfigWrapper.getWitherSummonSoundRestrictionHearableRadius;

        if(squaredDistance > threshold * threshold){
            e.setCancelled(true);
        }
    }

    private static double getSquaredDistance(Player player, WrapperPlayServerEffect wrapper) {
        var vectorLocX = wrapper.getPosition().getX();
        var vectorLocY = wrapper.getPosition().getY();
        var vectorLocZ = wrapper.getPosition().getZ();

        var playerLocX = player.getLocation().getX();
        var playerLocY = player.getLocation().getY();
        var playerLocZ = player.getLocation().getZ();

        var deltaX = vectorLocX - playerLocX;
        var deltaY = vectorLocY - playerLocY;
        var deltaZ = vectorLocZ - playerLocZ;

        return (deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ);
    }
}
