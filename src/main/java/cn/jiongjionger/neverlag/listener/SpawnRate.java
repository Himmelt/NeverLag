package cn.jiongjionger.neverlag.listener;

import cn.jiongjionger.neverlag.NeverLag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class SpawnRate implements Listener {

    private final JavaPlugin plugin;

    public SpawnRate(JavaPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void AntiRate(CreatureSpawnEvent event) {
        if (!event.isCancelled()) {
            String reason = event.getSpawnReason().toString().toUpperCase();
            int rand = new Random().nextInt(100);
            if ("NATURAL".equals(reason)) {
                if (NeverLag.CommonRate <= 0 || rand > NeverLag.CommonRate) {
                    event.setCancelled(true);
                }
            } else if ("SPAWNER".equals(reason)) {
                if (NeverLag.AntiDropExpFromSpawner || NeverLag.AntiDropFromSpawner) {
                    event.getEntity().setMetadata("spanwer", new FixedMetadataValue(this.plugin, Boolean.valueOf(true)));
                }
                if (NeverLag.SpawnerRate <= 0 || rand > NeverLag.SpawnerRate) {
                    event.setCancelled(true);
                }
            } else if ("NETHER_PORTAL".equals(reason)) {
                if (NeverLag.PortalRate <= 0 || rand > NeverLag.PortalRate) {
                    event.setCancelled(true);
                }
            } else if ("CHUNK_GEN".equals(reason)) {
                if (NeverLag.ChunkRate <= 0 || rand > NeverLag.ChunkRate) {
                    event.setCancelled(true);
                }
            } else if ("VILLAGE_DEFENSE".equals(reason)) {
                if (NeverLag.IronRate <= 0 || rand > NeverLag.IronRate) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onDrop(EntityDeathEvent event) {
        if (event.getEntity().hasMetadata("spanwer")) {
            if (NeverLag.AntiDropExpFromSpawner) {
                event.setDroppedExp(0);
            }

            if (NeverLag.AntiDropFromSpawner) {
                event.getDrops().clear();
            }
        }
    }

}
