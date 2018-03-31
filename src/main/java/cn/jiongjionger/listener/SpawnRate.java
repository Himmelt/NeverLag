package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Random;

public class SpawnRate implements Listener {

    NeverLag plugin;


    public SpawnRate(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
    }

    @EventHandler
    public void AntiRate(CreatureSpawnEvent e) {
        if (!e.isCancelled()) {
            String Reason = e.getSpawnReason().toString().toUpperCase();
            Random r = new Random();
            int rand = r.nextInt(100);
            switch (Reason.hashCode()) {
                case -1848073207:
                    if (!Reason.equals("NATURAL")) {
                        return;
                    }

                    if (NeverLag.CommonRate <= 0 || rand > NeverLag.CommonRate) {
                        e.setCancelled(true);
                    }
                    break;
                case -1293575864:
                    if (!Reason.equals("SPAWNER")) {
                        return;
                    }

                    if (NeverLag.AntiDropExpFromSpawner.booleanValue() || NeverLag.AntiDropFromSpawner.booleanValue()) {
                        e.getEntity().setMetadata("spanwer", new FixedMetadataValue(this.plugin, Boolean.valueOf(true)));
                    }

                    if (NeverLag.SpawnerRate <= 0 || rand > NeverLag.SpawnerRate) {
                        e.setCancelled(true);
                    }
                    break;
                case -1182414285:
                    if (!Reason.equals("NETHER_PORTAL")) {
                        return;
                    }

                    if (NeverLag.PortalRate <= 0 || rand > NeverLag.PortalRate) {
                        e.setCancelled(true);
                    }
                    break;
                case 521567806:
                    if (!Reason.equals("CHUNK_GEN")) {
                        return;
                    }

                    if (NeverLag.ChunkRate <= 0 || rand > NeverLag.ChunkRate) {
                        e.setCancelled(true);
                    }
                    break;
                case 1206494573:
                    if (Reason.equals("VILLAGE_DEFENSE")) {
                        if (NeverLag.IronRate <= 0 || rand > NeverLag.IronRate) {
                            e.setCancelled(true);
                        }
                        break;
                    }

                    return;
                default:
                    return;
            }

        }
    }

    @EventHandler
    public void onDrop(EntityDeathEvent e) {
        if (e.getEntity().hasMetadata("spanwer")) {
            if (NeverLag.AntiDropExpFromSpawner.booleanValue()) {
                e.setDroppedExp(0);
            }

            if (NeverLag.AntiDropFromSpawner.booleanValue()) {
                e.getDrops().clear();
            }
        }

    }
}
