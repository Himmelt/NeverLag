package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;

public class AntiMinecraft implements Listener {

    NeverLag plugin;


    public AntiMinecraft(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
    }

    @EventHandler
    public void onEntityPortal(EntityPortalEvent e) {
        if (e.getEntityType() == EntityType.MINECART || e.getEntityType() == EntityType.MINECART_CHEST || e.getEntityType() == EntityType.MINECART_FURNACE || e.getEntityType() == EntityType.MINECART_HOPPER || e.getEntityType() == EntityType.MINECART_TNT) {
            e.setCancelled(true);
        }

    }
}
