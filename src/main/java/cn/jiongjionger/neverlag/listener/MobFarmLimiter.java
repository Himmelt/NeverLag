package cn.jiongjionger.neverlag.listener;

import cn.jiongjionger.neverlag.NeverLag;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MobFarmLimiter implements Listener {

    JavaPlugin plugin;

    public MobFarmLimiter(JavaPlugin plg) {
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

    private void CleanFarm() {
        for (World world : Bukkit.getWorlds()) {
            int count = world.getLivingEntities().size();
            if (count < NeverLag.FarmLimit) {
                return;
            }
            for (LivingEntity entity : world.getLivingEntities()) {
                if ((entity instanceof Monster || entity instanceof Animals || entity instanceof Villager || entity.getType() == EntityType.SQUID) && entity.getNearbyEntities(0.5D, 3.0D, 0.5D).size() >= NeverLag.FarmLimit && !entity.hasMetadata("NPC") && !entity.hasMetadata("MyPet")) {
                    entity.remove();
                }
            }
        }
    }

}
