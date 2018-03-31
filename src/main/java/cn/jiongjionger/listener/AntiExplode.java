package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class AntiExplode implements Listener {

    NeverLag plugin;


    public AntiExplode(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
    }

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onHIGHESTExplosion(EntityExplodeEvent e) {
        if (!e.isCancelled()) {
            e.blockList().clear();
        }
    }

    @EventHandler(
            priority = EventPriority.MONITOR
    )
    public void onMONITORExplosion(EntityExplodeEvent e) {
        if (!e.isCancelled()) {
            e.blockList().clear();
        }
    }
}
