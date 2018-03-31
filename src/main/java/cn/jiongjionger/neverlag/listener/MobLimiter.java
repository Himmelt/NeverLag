package cn.jiongjionger.neverlag.listener;

import cn.jiongjionger.neverlag.NeverLag;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.plugin.java.JavaPlugin;

public class MobLimiter implements Listener {

    private int cache_count = 0;
    private int cache_time = 0;

    public MobLimiter(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (!event.isCancelled()) {
            int count = 0;
            boolean var7;
            if (event.getSpawnReason() != SpawnReason.SPAWNER_EGG && event.getSpawnReason() != SpawnReason.SPAWNER) {
                if (this.cache_time == 0 || this.cache_time >= 40) {
                    count = 0;
                    for (World world : Bukkit.getWorlds()) {
                        for (LivingEntity living : world.getLivingEntities()) {
                            if (living instanceof Monster || living instanceof Animals || living instanceof Villager || living.getType() == EntityType.SQUID || living.getType() == EntityType.BAT) {
                                ++count;
                            }
                        }
                    }

                    this.cache_count = count;
                    this.cache_time = 0;
                    var7 = false;
                }

                ++this.cache_time;
                if (event.getEntity() instanceof Animals) {
                    if (this.cache_count >= NeverLag.AnimalNum) {
                        event.setCancelled(true);
                    }
                } else if (event.getEntity() instanceof Monster && this.cache_count >= NeverLag.MonsterNum) {
                    event.setCancelled(true);
                }
            }

            if (event.getSpawnReason() == SpawnReason.SPAWNER) {
                count = 0;
                Entity[] var11;
                int var10 = (var11 = event.getLocation().getChunk().getEntities()).length;

                for (int var9 = 0; var9 < var10; ++var9) {
                    Entity var8 = var11[var9];
                    if (var8 instanceof Monster) {
                        ++count;
                    }
                }

                if (count >= NeverLag.SpawnerMobChunkNum) {
                    event.setCancelled(true);
                }
                var7 = false;
            }
        }
    }

}
