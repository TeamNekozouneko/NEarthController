package net.nekozouneko.nEarthController.impl;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EndCrystalDisabler implements Listener {
    @EventHandler
    public void onExplode(EntityExplodeEvent e){
        if(e.getEntityType() == EntityType.END_CRYSTAL){
            e.setCancelled(true);
        }
    }
}
