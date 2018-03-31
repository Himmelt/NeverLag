package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

import java.util.HashMap;
import java.util.Iterator;

public class CheckRedstone implements Listener {

    NeverLag plugin;
    private HashMap cache = new HashMap();


    public CheckRedstone(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
        plg.getServer().getScheduler().scheduleSyncRepeatingTask(plg, new Runnable() {
            public void run() {
                CheckRedstone.this.redstone_run();
            }
        }, 0L, (long) NeverLag.RedstoneTime * 20L);
    }

    @EventHandler(
            priority = EventPriority.LOWEST,
            ignoreCancelled = false
    )
    public void onBlockRedstone(BlockRedstoneEvent e) {
        if (NeverLag.RedstoneClearType.contains(Integer.valueOf(e.getBlock().getTypeId()))) {
            if (this.cache.containsKey(e.getBlock().getLocation())) {
                this.cache.put(e.getBlock().getLocation(), Integer.valueOf(((Integer) this.cache.get(e.getBlock().getLocation())).intValue() + 1));
            } else {
                this.cache.put(e.getBlock().getLocation(), Integer.valueOf(1));
            }

        }
    }

    public void redstone_run() {
        if (!this.cache.isEmpty()) {
            Boolean flag = Boolean.valueOf(false);
            Location location = null;

            Iterator iterator;
            Location NameList;
            for (iterator = this.cache.keySet().iterator(); iterator.hasNext(); NameList = null) {
                NameList = (Location) iterator.next();
                if (((Integer) this.cache.get(NameList)).intValue() >= NeverLag.RedstoneLimit) {
                    Bukkit.getScheduler().runTaskLater(this.plugin, new CheckRedstone.restone_drop(NameList), 2L);
                    if (location == null) {
                        location = NameList;
                    }

                    if (!flag.booleanValue()) {
                        flag = Boolean.valueOf(true);
                    }
                }
            }

            if (flag.booleanValue() && NeverLag.RedStoneMessage.booleanValue()) {
                if (location == null) {
                    Bukkit.getServer().broadcastMessage(NeverLag.m_redstone);
                } else {
                    String var11 = "";
                    int count = 0;
                    Entity[] var9;
                    int var8 = (var9 = location.getChunk().getEntities()).length;

                    for (int var7 = 0; var7 < var8; ++var7) {
                        Entity entity = var9[var7];
                        if (count >= 3) {
                            break;
                        }

                        if (entity instanceof Player) {
                            Player p = (Player) entity;
                            if (!p.hasMetadata("NPC")) {
                                if (var11 == "") {
                                    var11 = p.getName();
                                } else {
                                    var11 = var11 + ", " + p.getName();
                                }

                                ++count;
                            }
                        }
                    }

                    boolean var12 = false;
                    Bukkit.getServer().broadcastMessage(NeverLag.m_redstone.replace("%Location%", "[" + location.getWorld().getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + "]").replace("%Player%", var11));
                    var11 = "";
                }
            }

            iterator = null;
            this.cache.clear();
        }

    }

    private class restone_drop implements Runnable {

        Location loc;


        public restone_drop(Location loc) {
            this.loc = loc;
        }

        public void run() {
            try {
                if (NeverLag.RedStoneDrop.booleanValue()) {
                    this.loc.getBlock().breakNaturally();
                } else {
                    this.loc.getBlock().setType(Material.AIR);
                }
            } catch (Exception var2) {
                ;
            }

        }
    }
}
