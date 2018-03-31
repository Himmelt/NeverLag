package cn.jiongjionger.neverlag.task;

import cn.jiongjionger.neverlag.NeverLag;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;

public class ClearItem implements Listener {

    private static int time = 0;

    public ClearItem(org.bukkit.plugin.java.JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            public void run() {
                cleanRun();
            }
        }, (long) NeverLag.CleanItemTime * 20L, (long) NeverLag.CleanItemTime * 20L);
        if (NeverLag.CleanItemTime > 90) {
            plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                public void run() {
                    sendMessage();
                }
            }, 20L, 20L);
        }
    }

    private static void sendMessage() {
        ++time;
        if (NeverLag.CleanItemTime - time == 60) {
            Bukkit.getServer().broadcastMessage(NeverLag.m_clean_item_pre.replace("%TIME%", "60"));
        }

        if (NeverLag.CleanItemTime - time == 30) {
            Bukkit.getServer().broadcastMessage(NeverLag.m_clean_item_pre.replace("%TIME%", "30"));
        }

        if (NeverLag.CleanItemTime - time == 10) {
            Bukkit.getServer().broadcastMessage(NeverLag.m_clean_item_pre.replace("%TIME%", "10"));
        }

        if (NeverLag.CleanItemTime - time <= 0) {
            time = 0;
        }

    }

    private static void cleanRun() {
        int count = 0;
        for (World world : Bukkit.getWorlds()) {
            if (!NeverLag.NoClearItemWorld.contains(world.getName())) {
                for (Entity entity : world.getEntities()) {
                    if (entity instanceof Item && NeverLag.ClearItem) {
                        Item item = (Item) entity;
                        if (!NeverLag.NoClearItemIDList.contains(item.getItemStack().getTypeId())) {
                            ++count;
                            entity.remove();
                        }
                    } else if (entity instanceof ItemFrame && NeverLag.ClearItemFrame) {
                        ++count;
                        entity.remove();
                    } else if (entity instanceof Boat && NeverLag.Boat) {
                        ++count;
                        entity.remove();
                    } else if (entity instanceof ExperienceOrb && NeverLag.ExpBall) {
                        ++count;
                        entity.remove();
                    } else if (entity instanceof FallingBlock && NeverLag.FallingBlock) {
                        ++count;
                        entity.remove();
                    } else if (entity instanceof Painting && NeverLag.Painting) {
                        ++count;
                        entity.remove();
                    } else if (entity instanceof Minecart && NeverLag.Minecart) {
                        ++count;
                        entity.remove();
                    } else if (entity instanceof Arrow && NeverLag.Arrow) {
                        ++count;
                        entity.remove();
                    } else if (entity instanceof Snowball && NeverLag.Snowball) {
                        ++count;
                        entity.remove();
                    }
                }
            }
        }
        Bukkit.getServer().broadcastMessage(NeverLag.m_clean_item.replace("%COUNT%", String.valueOf(count)));
    }

}
