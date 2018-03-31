package cn.jiongjionger.task;

import cn.jiongjionger.NeverLag;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;

import java.util.Iterator;

public class ClearEntity implements Listener {

    NeverLag plugin;


    public ClearEntity(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
        plg.getServer().getScheduler().scheduleSyncRepeatingTask(plg, new Runnable() {
            public void run() {
                ClearEntity.this.clean_run();
            }
        }, (long) NeverLag.ClearMobTime * 20L, (long) NeverLag.ClearMobTime * 20L);
    }

    public void clean_run() {
        int num = 0;

        World count;
        for (Iterator world = Bukkit.getWorlds().iterator(); world.hasNext(); num += count.getLivingEntities().size()) {
            count = (World) world.next();
        }

        if (num >= NeverLag.ClearLimitNum) {
            int var7 = 0;
            Iterator var4 = Bukkit.getWorlds().iterator();

            while (var4.hasNext()) {
                World var9 = (World) var4.next();
                Iterator var6 = var9.getLivingEntities().iterator();

                while (var6.hasNext()) {
                    LivingEntity env = (LivingEntity) var6.next();
                    if (env instanceof Animals && NeverLag.ClearAnimals.booleanValue()) {
                        if (!env.hasMetadata("NPC") && !env.hasMetadata("MyPet")) {
                            env.remove();
                            ++var7;
                        }
                    } else if (env instanceof Monster && NeverLag.ClearMonster.booleanValue()) {
                        if (!env.hasMetadata("NPC") && !env.hasMetadata("MyPet")) {
                            env.remove();
                            ++var7;
                        }
                    } else if (env instanceof Squid && NeverLag.ClearSquid.booleanValue()) {
                        if (!env.hasMetadata("NPC") && !env.hasMetadata("MyPet")) {
                            env.remove();
                            ++var7;
                        }
                    } else if (env instanceof Villager && NeverLag.ClearVillager.booleanValue() && !env.hasMetadata("NPC") && !env.hasMetadata("MyPet")) {
                        env.remove();
                        ++var7;
                    }
                }
            }

            Bukkit.getServer().broadcastMessage(NeverLag.m_clean_mob.replace("%COUNT%", String.valueOf(var7)));
            boolean var8 = false;
        }
    }
}
