package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Iterator;

public class AntiCheatMod implements Listener {

    NeverLag plugin;
    private String username = null;


    public AntiCheatMod(NeverLag plg) {
        this.plugin = plg;
        this.username = null;
        plg.getServer().getPluginManager().registerEvents(this, plg);
    }

    @EventHandler(
            priority = EventPriority.LOWEST
    )
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (!e.getPlayer().hasPermission("NeverLag.NoCheck")) {
            this.username = e.getPlayer().getName();
            Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(this.plugin, new Runnable() {
                public void run() {
                    AntiCheatMod.this.AsyncSendTask();
                }
            }, 0L);
        }

    }

    public void AsyncSendTask() {
        if (this.username != "" && this.username != null) {
            String name = this.username;
            this.username = null;
            Player p = Bukkit.getPlayer(name);
            if (p != null) {
                Iterator var4 = NeverLag.AntiCheatModCode.iterator();

                while (var4.hasNext()) {
                    String code = (String) var4.next();
                    if (!p.isOnline()) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', code));
                    }
                }

            }
        }
    }
}
