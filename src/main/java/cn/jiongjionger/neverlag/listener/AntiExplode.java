package cn.jiongjionger.neverlag.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiExplode implements Listener {

    JavaPlugin plugin;

    public AntiExplode(JavaPlugin plg) {
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

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMONITORExplosion(EntityExplodeEvent event) {
        if (!event.isCancelled()) {
            event.blockList().clear();
        }
    }

}
