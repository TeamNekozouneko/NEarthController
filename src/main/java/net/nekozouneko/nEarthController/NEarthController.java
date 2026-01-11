package net.nekozouneko.nEarthController;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import lombok.Getter;
import net.nekozouneko.nEarthController.impl.EndCrystalDisabler;
import net.nekozouneko.nEarthController.impl.WitherSummonSoundRestriction;
import net.nekozouneko.nEarthController.wrapper.ConfigWrapper;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class NEarthController extends JavaPlugin {

    @Getter
    private static NEarthController instance;

    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
    }

    @Override
    public void onEnable() {
        instance = this;

        //PacketEvents Initialize
        PacketEvents.getAPI().init();

        //Configuration
        saveDefaultConfig();
        reloadConfig();

        //Activation Settings
        List<Listener> bukkitListeners = new ArrayList<>();
        List<PacketListener> packetListeners = new ArrayList<>();

        var isPacketEventsEnabled = Bukkit.getServer().getPluginManager().isPluginEnabled("packetevents");

        if(ConfigWrapper.isEndCrystalDisablerEnabled) bukkitListeners.add(new EndCrystalDisabler());
        if(ConfigWrapper.isWitherSummonSoundRestrictionEnabled && isPacketEventsEnabled) packetListeners.add(
                new WitherSummonSoundRestriction()
        );

        //Listener
        for(Listener listener : bukkitListeners){
            getLogger().info("Registered Bukkit Listener: " + listener.getClass().getSimpleName());
            getServer().getPluginManager().registerEvents(
                    listener,
                    this
            );
        }
        for(PacketListener listener : packetListeners){
            getLogger().info("Registered Packet Listener: " + listener.getClass().getSimpleName());
            PacketEvents.getAPI().getEventManager().registerListener(
                    listener,
                    PacketListenerPriority.LOWEST
            );
        }
    }

    @Override
    public void onDisable() {
        PacketEvents.getAPI().terminate();
    }
}
