package net.nekozouneko.nEarthController;

import lombok.Getter;
import net.nekozouneko.nEarthController.listener.EntityExplodeListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class NEarthController extends JavaPlugin {

    @Getter
    private static NEarthController instance;

    @Override
    public void onEnable() {
        instance = this;

        //Listener
        getServer().getPluginManager().registerEvents(
                new EntityExplodeListener(),
                this
        );
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
