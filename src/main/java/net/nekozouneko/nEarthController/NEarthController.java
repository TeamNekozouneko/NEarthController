package net.nekozouneko.nEarthController;

import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import lombok.Getter;
import net.nekozouneko.nEarthController.impl.EndCrystalDisabler;
import net.nekozouneko.nEarthController.wrapper.ConfigWrapper;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class NEarthController extends JavaPlugin {

    @Getter
    private static NEarthController instance;

    public static boolean isPacketEventsEnabled =
            Bukkit.getServer().getPluginManager().isPluginEnabled("packetevents");

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
        List<Listener> listeners = new ArrayList<>();
        if(ConfigWrapper.isEndCrystalDisablerEnabled) listeners.add(new EndCrystalDisabler());

        //Listener
        for(Listener listener : listeners){
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    @Override
    public void onDisable() {
        PacketEvents.getAPI().terminate();
    }
}
