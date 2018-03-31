package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class AntiDoorCheat implements Listener {

    NeverLag plugin;


    public AntiDoorCheat(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (e.isCancelled() && (e.getItemInHand().getType() == Material.WOOD_DOOR || e.getItemInHand().getType() == Material.IRON_DOOR)) {
            Entity[] var5;
            int var4 = (var5 = e.getPlayer().getLocation().getChunk().getEntities()).length;

            for (int var3 = 0; var3 < var4; ++var3) {
                Entity entity = var5[var3];
                if (entity instanceof Item && (((Item) entity).getItemStack().getType() == Material.SUGAR_CANE || ((Item) entity).getItemStack().getType() == Material.CACTUS)) {
                    entity.remove();
                }
            }
        }

    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        if (!e.isCancelled() && e.getPlayer() != null) {
            if ((e.getPlayer().getItemInHand().getType() == Material.WOOD_DOOR || e.getPlayer().getItemInHand().getType() == Material.IRON_DOOR) && (e.getItem().getItemStack().getType() == Material.SUGAR_CANE || e.getItem().getItemStack().getType() == Material.CACTUS)) {
                e.setCancelled(true);
            }

        }
    }
}
