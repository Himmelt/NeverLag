package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;

public class AntiInfiniteRail implements Listener {

    NeverLag plugin;


    public AntiInfiniteRail(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
    }

    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent e) {
        if (e.getChangedType().equals(Material.RAILS) && (e.getBlock().getType().equals(Material.DETECTOR_RAIL) || e.getBlock().getType().equals(Material.POWERED_RAIL) || e.getBlock().getType().equals(Material.ACTIVATOR_RAIL))) {
            e.setCancelled(true);
        }

    }
}
