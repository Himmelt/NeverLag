package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;

import java.util.Iterator;

public class AntiPMM implements Listener {

    NeverLag plugin;


    public AntiPMM(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
    }

    @EventHandler
    public void onBlockPistonExtend(BlockPistonExtendEvent e) {
        if (!e.isCancelled()) {
            Iterator var3 = e.getBlocks().iterator();

            while (var3.hasNext()) {
                Block b = (Block) var3.next();
                if (b.getType().equals(Material.SLIME_BLOCK)) {
                    e.setCancelled(true);
                    return;
                }
            }

        }
    }
}
