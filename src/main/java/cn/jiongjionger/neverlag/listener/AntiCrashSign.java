package cn.jiongjionger.neverlag.listener;

import cn.jiongjionger.neverlag.NeverLag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiCrashSign implements Listener {

    public AntiCrashSign(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onSignChange(SignChangeEvent event) {
        for (int i = 0; i < 4; ++i) {
            if (event.getLine(i).matches("^[a-zA-Z0-9_]*$")) {
                if (event.getLine(i).length() > 20) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(NeverLag.m_anti_crashsign);
                }
            } else if (event.getLine(i).length() > 50) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(NeverLag.m_anti_crashsign);
            }
        }
    }

}
