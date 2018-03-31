package cn.jiongjionger.neverlag.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiMinecraft implements Listener {

    public AntiMinecraft(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityPortal(EntityPortalEvent event) {
        if (event.getEntityType() == EntityType.MINECART || event.getEntityType() == EntityType.MINECART_CHEST || event.getEntityType() == EntityType.MINECART_FURNACE || event.getEntityType() == EntityType.MINECART_HOPPER || event.getEntityType() == EntityType.MINECART_TNT) {
            event.setCancelled(true);
        }
    }
}
