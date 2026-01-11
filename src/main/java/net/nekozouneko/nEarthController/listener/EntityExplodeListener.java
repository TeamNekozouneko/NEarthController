package net.nekozouneko.nEarthController.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplodeListener implements Listener {
    @EventHandler
    public void onExplode(EntityExplodeEvent e){
        //クリスタルの爆発をキャンセルする
        if(e.getEntityType() == EntityType.END_CRYSTAL){
            e.setCancelled(true);
        }
    }
}
