package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AntiBot implements Listener {

    NeverLag plugin;
    private List UserName = new ArrayList();
    private HashMap IPCache = new HashMap();

    public AntiBot(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
        plg.getServer().getScheduler().scheduleSyncRepeatingTask(plg, new Runnable() {
            public void run() {
                AntiBot.this.clear_run();
            }
        }, (long) NeverLag.IPLoginTime * 20L, (long) NeverLag.IPLoginTime * 20L);
    }

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent e) {
        if (e.getLoginResult().equals(Result.ALLOWED)) {
            if (!NeverLag.IPWhiteList.contains(e.getAddress().getHostAddress())) {
                if (this.IPCache.containsKey(e.getAddress().getHostAddress()) && (Integer) this.IPCache.get(e.getAddress().getHostAddress()) >= NeverLag.MaxIPLoginNum) {
                    e.disallow(Result.KICK_OTHER, NeverLag.m_antibot);
                } else {
                    String username = e.getName().toLowerCase();
                    if (!this.UserName.contains(username)) {
                        this.UserName.add(username);
                        if (!this.IPCache.containsKey(e.getAddress().getHostAddress())) {
                            this.IPCache.put(e.getAddress().getHostAddress(), 1);
                        } else {
                            this.IPCache.put(e.getAddress().getHostAddress(), (Integer) this.IPCache.get(e.getAddress().getHostAddress()) + 1);
                        }

                    }
                }
            }
        }
    }

    public void clear_run() {
        this.IPCache.clear();
        this.UserName.clear();
    }
}
