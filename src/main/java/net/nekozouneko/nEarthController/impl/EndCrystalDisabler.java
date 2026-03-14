package net.nekozouneko.nEarthController.impl;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EndCrystalDisabler implements Listener {
    @EventHandler
    public void onExplode(EntityExplodeEvent e){
        if(e.getEntityType() == EntityType.END_CRYSTAL){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        Entity directEntity = e.getDamageSource().getDirectEntity();

        if(directEntity == null) return;
        if(directEntity.getType() != EntityType.END_CRYSTAL) return;

        e.setCancelled(true);
    }
}
