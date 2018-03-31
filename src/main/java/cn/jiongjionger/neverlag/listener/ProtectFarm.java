package cn.jiongjionger.neverlag.listener;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ProtectFarm implements Listener {

    JavaPlugin plugin;

    public ProtectFarm(JavaPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJump(PlayerInteractEvent event) {
        if (!event.isCancelled()) {
            if (event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType() == Material.SOIL) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onMobJump(EntityInteractEvent event) {
        if (!event.isCancelled()) {
            if (event.getEntityType() != EntityType.PLAYER) {
                if (event.getBlock().getType() == Material.SOIL) {
                    event.setCancelled(true);
                }
            }
        }
    }

}
