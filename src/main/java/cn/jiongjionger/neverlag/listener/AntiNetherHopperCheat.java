package cn.jiongjionger.neverlag.listener;

import org.bukkit.block.Hopper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiNetherHopperCheat implements Listener {

    public AntiNetherHopperCheat(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onHopper(InventoryMoveItemEvent event) {
        if (event.getInitiator().getHolder() instanceof Hopper) {
            Hopper hopper = (Hopper) event.getInitiator().getHolder();
            // TODO 用名字判定是否是某个世界是不明智的
            if (hopper.getWorld().getName().equalsIgnoreCase("world_nether")) {
                event.setCancelled(true);
            }
        }
    }
}
