package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class AntiCrashSign implements Listener {

    NeverLag plugin;


    public AntiCrashSign(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
    }

    @EventHandler(
            priority = EventPriority.HIGHEST,
            ignoreCancelled = true
    )
    public void onSignChange(SignChangeEvent e) {
        for (int i = 0; i < 4; ++i) {
            if (e.getLine(i).matches("^[a-zA-Z0-9_]*$")) {
                if (e.getLine(i).length() > 20) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(NeverLag.m_anti_crashsign);
                }
            } else if (e.getLine(i).length() > 50) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(NeverLag.m_anti_crashsign);
            }
        }

    }
}
