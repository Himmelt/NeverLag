package cn.jiongjionger.neverlag.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class AntiMotdPing implements Listener {

    private HashMap<String, Long> log = new HashMap<>();

    public AntiMotdPing(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            public void run() {
                log.clear();
            }
        }, 2400L, 2400L);
    }

    @EventHandler
    public void onPing(ServerListPingEvent event) {
        if (this.log.containsKey(event.getAddress().getHostAddress())) {
            if (this.log.get(event.getAddress().getHostAddress()) + 100L > System.currentTimeMillis()) {
                event.setMotd("");
            }
        } else {
            this.log.put(event.getAddress().getHostAddress(), System.currentTimeMillis());
        }
    }

}
