package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import java.util.Iterator;

public class MobLimiter implements Listener {

    private int cache_count = 0;
    private int cache_time = 0;
    NeverLag plugin;


    public MobLimiter(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
    }

    @EventHandler(
            priority = EventPriority.LOWEST
    )
    public void onCreatureSpawn(CreatureSpawnEvent e) {
        if (!e.isCancelled()) {
            int count;
            boolean var7;
            if (e.getSpawnReason() != SpawnReason.SPAWNER_EGG && e.getSpawnReason() != SpawnReason.SPAWNER) {
                if (this.cache_time == 0 || this.cache_time >= 40) {
                    count = 0;
                    Iterator var4 = Bukkit.getWorlds().iterator();

                    while (var4.hasNext()) {
                        World entity = (World) var4.next();
                        Iterator var6 = entity.getLivingEntities().iterator();

                        while (var6.hasNext()) {
                            LivingEntity entity1 = (LivingEntity) var6.next();
                            if (entity1 instanceof Monster || entity1 instanceof Animals || entity1 instanceof Villager || entity1.getType() == EntityType.SQUID || entity1.getType() == EntityType.BAT) {
                                ++count;
                            }
                        }
                    }

                    this.cache_count = count;
                    this.cache_time = 0;
                    var7 = false;
                }

                ++this.cache_time;
                if (e.getEntity() instanceof Animals) {
                    if (this.cache_count >= NeverLag.AnimalNum) {
                        e.setCancelled(true);
                    }
                } else if (e.getEntity() instanceof Monster && this.cache_count >= NeverLag.MonsterNum) {
                    e.setCancelled(true);
                }
            }

            if (e.getSpawnReason() == SpawnReason.SPAWNER) {
                count = 0;
                Entity[] var11;
                int var10 = (var11 = e.getLocation().getChunk().getEntities()).length;

                for (int var9 = 0; var9 < var10; ++var9) {
                    Entity var8 = var11[var9];
                    if (var8 instanceof Monster) {
                        ++count;
                    }
                }

                if (count >= NeverLag.SpawnerMobChunkNum) {
                    e.setCancelled(true);
                }

                var7 = false;
            }

        }
    }
}
