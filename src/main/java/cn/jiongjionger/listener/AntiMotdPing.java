package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.HashMap;

public class AntiMotdPing implements Listener {

    NeverLag plugin;
    private HashMap log = new HashMap();


    public AntiMotdPing(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
        plg.getServer().getScheduler().scheduleSyncRepeatingTask(plg, new Runnable() {
            public void run() {
                AntiMotdPing.this.clear_run();
            }
        }, 2400L, 2400L);
    }

    @EventHandler
    public void onPing(ServerListPingEvent e) {
        if (this.log.containsKey(e.getAddress().getHostAddress())) {
            if (((Long) this.log.get(e.getAddress().getHostAddress())).longValue() + 100L > System.currentTimeMillis()) {
                e.setMotd((String) null);
            }
        } else {
            this.log.put(e.getAddress().getHostAddress(), Long.valueOf(System.currentTimeMillis()));
        }

    }

    public void clear_run() {
        this.log.clear();
    }
}
