package cn.jiongjionger.neverlag.listener;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiPMM implements Listener {

    public AntiPMM(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

/*    @EventHandler
    public void onBlockPistonExtend(BlockPistonExtendEvent event) {
        if (!event.isCancelled()) {
            for (Block block : event.getBlocks()) {
                if (block.getType().equals(Material.SLIME_BLOCK)) {
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }*/

}
