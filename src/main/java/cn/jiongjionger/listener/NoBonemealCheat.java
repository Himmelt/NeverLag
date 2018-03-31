package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class NoBonemealCheat implements Listener {

    NeverLag plugin;


    public NoBonemealCheat(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
    }

    @EventHandler
    public void AntiCheat(PlayerInteractEvent e) {
        if (!e.isCancelled()) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (!e.getPlayer().getItemInHand().getType().equals(Material.INK_SACK)) {
                    return;
                }

                if (NeverLag.BonemealBlackList.contains(Integer.valueOf(e.getClickedBlock().getTypeId()))) {
                    e.setCancelled(true);
                }
            }

        }
    }
}
