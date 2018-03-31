package cn.jiongjionger.neverlag.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NoSpawnChunk implements Listener {

    public NoSpawnChunk(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onWorldLoad(WorldInitEvent event) {
        event.getWorld().setKeepSpawnInMemory(false);
    }

}
