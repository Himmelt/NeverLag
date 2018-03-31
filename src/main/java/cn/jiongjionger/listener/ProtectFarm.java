package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ProtectFarm implements Listener {

    NeverLag plugin;


    public ProtectFarm(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
    }

    @EventHandler
    public void onJump(PlayerInteractEvent e) {
        if (!e.isCancelled()) {
            if (e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.SOIL) {
                e.setCancelled(true);
            }

        }
    }

    @EventHandler
    public void onMobJump(EntityInteractEvent e) {
        if (!e.isCancelled()) {
            if (e.getEntityType() != EntityType.PLAYER) {
                if (e.getBlock().getType() == Material.SOIL) {
                    e.setCancelled(true);
                }

            }
        }
    }
}
