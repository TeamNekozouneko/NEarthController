package net.nekozouneko.nEarthController;

import lombok.Getter;
import net.nekozouneko.nEarthController.impl.EndCrystalDisabler;
import net.nekozouneko.nEarthController.wrapper.ConfigWrapper;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class NEarthController extends JavaPlugin {

    @Getter
    private static NEarthController instance;

    @Override
    public void onEnable() {
        instance = this;

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
        // Plugin shutdown logic
    }
}
