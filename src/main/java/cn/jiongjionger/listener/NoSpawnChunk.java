package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

public class NoSpawnChunk implements Listener {

    NeverLag plugin;


    public NoSpawnChunk(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
    }

    @EventHandler(
            priority = EventPriority.LOWEST
    )
    public void onWorldLoad(WorldInitEvent e) {
        e.getWorld().setKeepSpawnInMemory(false);
    }
}
