package cn.jiongjionger.neverlag.listener;

import cn.jiongjionger.neverlag.NeverLag;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class NoBonemealCheat implements Listener {

    public NoBonemealCheat(org.bukkit.plugin.java.JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void AntiCheat(PlayerInteractEvent event) {
        if (!event.isCancelled()) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (!event.getPlayer().getItemInHand().getType().equals(Material.INK_SACK)) {
                    return;
                }
                if (NeverLag.BonemealBlackList.contains(event.getClickedBlock().getTypeId())) {
                    event.setCancelled(true);
                }
            }
        }
    }

}
