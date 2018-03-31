package cn.jiongjionger.neverlag.task;

import cn.jiongjionger.neverlag.NeverLag;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;

public class ClearEntity implements Listener {

    public ClearEntity(org.bukkit.plugin.java.JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            public void run() {
                ClearEntity.cleanRun();
            }
        }, (long) NeverLag.ClearMobTime * 20L, (long) NeverLag.ClearMobTime * 20L);
    }

    private static void cleanRun() {
        int livings = 0;
        for (World world : Bukkit.getWorlds()) {
            livings += world.getLivingEntities().size();
        }
        if (livings >= NeverLag.ClearLimitNum) {
            int mob_count = 0;
            for (World world : Bukkit.getWorlds()) {
                for (LivingEntity entity : world.getLivingEntities()) {
                    if (entity instanceof Animals && NeverLag.ClearAnimals) {
                        if (!entity.hasMetadata("NPC") && !entity.hasMetadata("MyPet")) {
                            entity.remove();
                            ++mob_count;
                        }
                    } else if (entity instanceof Monster && NeverLag.ClearMonster) {
                        if (!entity.hasMetadata("NPC") && !entity.hasMetadata("MyPet")) {
                            entity.remove();
                            ++mob_count;
                        }
                    } else if (entity instanceof Squid && NeverLag.ClearSquid) {
                        if (!entity.hasMetadata("NPC") && !entity.hasMetadata("MyPet")) {
                            entity.remove();
                            ++mob_count;
                        }
                    } else if (entity instanceof Villager && NeverLag.ClearVillager && !entity.hasMetadata("NPC") && !entity.hasMetadata("MyPet")) {
                        entity.remove();
                        ++mob_count;
                    }
                }
            }
            Bukkit.getServer().broadcastMessage(NeverLag.m_clean_mob.replace("%COUNT%", String.valueOf(mob_count)));
        }
    }

}
