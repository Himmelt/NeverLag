package cn.jiongjionger.neverlag.listener;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiDoorCheat implements Listener {

    public AntiDoorCheat(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (event.isCancelled() && (event.getItemInHand().getType() == Material.WOOD_DOOR || event.getItemInHand().getType() == Material.IRON_DOOR)) {
            Entity[] entities = event.getPlayer().getLocation().getChunk().getEntities();
            for (int i = 0; entities != null && i < entities.length; ++i) {
                if (entities[i] instanceof Item) {
                    ItemStack stack = ((Item) entities[i]).getItemStack();
                    if (stack.getType() == Material.SUGAR_CANE || stack.getType() == Material.CACTUS) {
                        entities[i].remove();
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        if (!event.isCancelled() && event.getPlayer() != null) {
            if ((event.getPlayer().getItemInHand().getType() == Material.WOOD_DOOR || event.getPlayer().getItemInHand().getType() == Material.IRON_DOOR) && (event.getItem().getItemStack().getType() == Material.SUGAR_CANE || event.getItem().getItemStack().getType() == Material.CACTUS)) {
                event.setCancelled(true);
            }
        }
    }
}
