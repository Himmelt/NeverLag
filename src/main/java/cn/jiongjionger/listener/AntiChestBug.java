package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.InventoryHolder;

public class AntiChestBug implements Listener {

    NeverLag plugin;


    public AntiChestBug(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
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
