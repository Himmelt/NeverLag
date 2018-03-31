package cn.jiongjionger.neverlag.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiInfiniteRail implements Listener {

    public AntiInfiniteRail(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent e) {
        if (e.getChangedType().equals(Material.RAILS) && (e.getBlock().getType().equals(Material.DETECTOR_RAIL) || e.getBlock().getType().equals(Material.POWERED_RAIL) || e.getBlock().getType().equals(Material.ACTIVATOR_RAIL))) {
            e.setCancelled(true);
        }
    }
}
