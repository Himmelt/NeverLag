package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.block.Hopper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

public class AntiNetherHopperCheat implements Listener {

    NeverLag plugin;


    public AntiNetherHopperCheat(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
    }

    @EventHandler
    public void onHopper(InventoryMoveItemEvent e) {
        if (e.getInitiator().getHolder() instanceof Hopper) {
            Hopper hopper = (Hopper) e.getInitiator().getHolder();
            if (hopper.getWorld().getName().equalsIgnoreCase("world_nether")) {
                e.setCancelled(true);
            }
        }

    }
}
