package cn.jiongjionger.neverlag.listener;

import cn.jiongjionger.neverlag.NeverLag;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class AntiCheatMod implements Listener {

    private String username;
    private final org.bukkit.plugin.java.JavaPlugin plugin;

    public AntiCheatMod(org.bukkit.plugin.java.JavaPlugin plugin) {
        this.username = null;
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPermission("NeverLag.NoCheck")) {
            this.username = event.getPlayer().getName();
            Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(this.plugin, new Runnable() {
                public void run() {
                    AsyncSendTask();
                }
            }, 0L);
        }
    }

    private void AsyncSendTask() {
        if (username != null && !username.isEmpty()) {
            Player player = Bukkit.getPlayer(username);
            if (player != null) {
                for (String code : NeverLag.AntiCheatModCode) {
                    if (!player.isOnline()) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', code));
                    }
                }
            }
            username = null;
        }
    }

}
