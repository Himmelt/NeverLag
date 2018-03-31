package cn.jiongjionger.neverlag.listener;

import cn.jiongjionger.neverlag.NeverLag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiChestBug implements Listener {

    public AntiChestBug(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (!e.isCancelled()) {
            if (e.getBlock().getState() instanceof InventoryHolder) {
                InventoryHolder inventory = (InventoryHolder) e.getBlock().getState();
                if (inventory.getInventory().getViewers().size() > 0) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(NeverLag.m_antichest);
                }
            }
        }
    }

}
