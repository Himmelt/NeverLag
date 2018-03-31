package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.Iterator;

public class MobFarmLimiter implements Listener {

    NeverLag plugin;


    public MobFarmLimiter(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
        plg.getServer().getScheduler().scheduleSyncRepeatingTask(plg, new Runnable() {
            public void run() {
                MobFarmLimiter.this.CleanFarm();
            }
        }, 0L, (long) NeverLag.FarmCleanTime * 20L);
    }

    @EventHandler
    public void onChunkLoadFarmLimiter(ChunkLoadEvent e) {
        if (e.getChunk().getEntities().length >= NeverLag.FarmLimit) {
            Entity[] var5;
            int var4 = (var5 = e.getChunk().getEntities()).length;

            for (int var3 = 0; var3 < var4; ++var3) {
                Entity entity = var5[var3];
                if ((entity instanceof Monster || entity instanceof Animals || entity instanceof Villager || entity.getType() == EntityType.SQUID) && entity.getNearbyEntities(0.5D, 3.0D, 0.5D).size() >= NeverLag.FarmLimit && !entity.hasMetadata("NPC") && !entity.hasMetadata("MyPet")) {
                    entity.remove();
                }
            }

        }
    }

    public void CleanFarm() {
        Iterator var2 = Bukkit.getWorlds().iterator();

        while (var2.hasNext()) {
            World w = (World) var2.next();
            int count = w.getLivingEntities().size();
            if (count < NeverLag.FarmLimit) {
                return;
            }

            Iterator var5 = w.getLivingEntities().iterator();

            while (var5.hasNext()) {
                LivingEntity entity = (LivingEntity) var5.next();
                if ((entity instanceof Monster || entity instanceof Animals || entity instanceof Villager || entity.getType() == EntityType.SQUID) && entity.getNearbyEntities(0.5D, 3.0D, 0.5D).size() >= NeverLag.FarmLimit && !entity.hasMetadata("NPC") && !entity.hasMetadata("MyPet")) {
                    entity.remove();
                }
            }
        }

    }
}
