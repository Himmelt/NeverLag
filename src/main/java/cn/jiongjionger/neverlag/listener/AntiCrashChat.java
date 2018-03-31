package cn.jiongjionger.neverlag.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiCrashChat implements Listener {

    public AntiCrashChat(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void OnChat(AsyncPlayerChatEvent event) {
        if (!event.isCancelled()) {
            if (event.getMessage() != null) {
                if (event.getMessage().contains("İ")) {
                    event.setCancelled(true);
                }
            }
        }
    }

}
