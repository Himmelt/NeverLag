package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class FixPluginBug implements Listener {

    NeverLag plugin;


    public FixPluginBug(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
    }

    @EventHandler
    public void onSign(SignChangeEvent e) {
        if (!e.isCancelled()) {
            String[] var5;
            int var4 = (var5 = e.getLines()).length;

            for (int var3 = 0; var3 < var4; ++var3) {
                String line = var5[var3];
                if (!line.contains("/") && !line.toLowerCase().contains("[command]")) {
                    if ((line.toUpperCase().contains("B1E1") || line.toUpperCase().contains("1E1B")) && !e.isCancelled()) {
                        e.setCancelled(true);
                        e.getBlock().breakNaturally();
                    }
                } else if (!e.getPlayer().isOp() && !e.isCancelled()) {
                    e.setCancelled(true);
                    e.getBlock().breakNaturally();
                }
            }

        }
    }
}
