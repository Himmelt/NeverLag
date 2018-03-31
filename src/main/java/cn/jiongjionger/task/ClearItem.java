package cn.jiongjionger.task;

import cn.jiongjionger.NeverLag;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;

import java.util.Iterator;

public class ClearItem implements Listener {

    NeverLag plugin;
    public int nowtime = 0;


    public ClearItem(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
        plg.getServer().getScheduler().scheduleSyncRepeatingTask(plg, new Runnable() {
            public void run() {
                ClearItem.this.clean_run();
            }
        }, (long) NeverLag.CleanItemTime * 20L, (long) NeverLag.CleanItemTime * 20L);
        if (NeverLag.CleanItemTime > 90) {
            plg.getServer().getScheduler().scheduleSyncRepeatingTask(plg, new Runnable() {
                public void run() {
                    ClearItem.this.domessage();
                }
            }, 20L, 20L);
        }

    }

    public void domessage() {
        ++this.nowtime;
        if (NeverLag.CleanItemTime - this.nowtime == 60) {
            Bukkit.getServer().broadcastMessage(NeverLag.m_clean_item_pre.replace("%TIME%", "60"));
        }

        if (NeverLag.CleanItemTime - this.nowtime == 30) {
            Bukkit.getServer().broadcastMessage(NeverLag.m_clean_item_pre.replace("%TIME%", "30"));
        }

        if (NeverLag.CleanItemTime - this.nowtime == 10) {
            Bukkit.getServer().broadcastMessage(NeverLag.m_clean_item_pre.replace("%TIME%", "10"));
        }

        if (NeverLag.CleanItemTime - this.nowtime <= 0) {
            this.nowtime = 0;
        }

    }

    public void clean_run() {
        int count = 0;
        Iterator var3 = Bukkit.getWorlds().iterator();

        while (var3.hasNext()) {
            World world = (World) var3.next();
            if (!NeverLag.NoClearItemWorld.contains(world.getName())) {
                Iterator var5 = world.getEntities().iterator();

                while (var5.hasNext()) {
                    Entity ent = (Entity) var5.next();
                    if (ent instanceof Item && NeverLag.ClearItem.booleanValue()) {
                        Item item = (Item) ent;
                        if (!NeverLag.NoClearItemIDList.contains(Integer.valueOf(item.getItemStack().getTypeId()))) {
                            ++count;
                            ent.remove();
                        }

                        item = null;
                    } else if (ent instanceof ItemFrame && NeverLag.ClearItemFrame.booleanValue()) {
                        ++count;
                        ent.remove();
                    } else if (ent instanceof Boat && NeverLag.Boat.booleanValue()) {
                        ++count;
                        ent.remove();
                    } else if (ent instanceof ExperienceOrb && NeverLag.ExpBall.booleanValue()) {
                        ++count;
                        ent.remove();
                    } else if (ent instanceof FallingBlock && NeverLag.FallingBlock.booleanValue()) {
                        ++count;
                        ent.remove();
                    } else if (ent instanceof Painting && NeverLag.Painting.booleanValue()) {
                        ++count;
                        ent.remove();
                    } else if (ent instanceof Minecart && NeverLag.Minecart.booleanValue()) {
                        ++count;
                        ent.remove();
                    } else if (ent instanceof Arrow && NeverLag.Arrow.booleanValue()) {
                        ++count;
                        ent.remove();
                    } else if (ent instanceof Snowball && NeverLag.Snowball.booleanValue()) {
                        ++count;
                        ent.remove();
                    }
                }
            }
        }

        Bukkit.getServer().broadcastMessage(NeverLag.m_clean_item.replace("%COUNT%", String.valueOf(count)));
        boolean var7 = false;
    }
}
