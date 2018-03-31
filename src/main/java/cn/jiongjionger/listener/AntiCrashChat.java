package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AntiCrashChat implements Listener {

    NeverLag plugin;


    public AntiCrashChat(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
    }

    @EventHandler
    public void OnChat(AsyncPlayerChatEvent e) {
        if (!e.isCancelled()) {
            if (e.getMessage() != null) {
                if (e.getMessage().contains("İ")) {
                    e.setCancelled(true);
                }

            }
        }
    }
}
