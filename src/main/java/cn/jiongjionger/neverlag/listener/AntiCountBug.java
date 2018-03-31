package cn.jiongjionger.neverlag.listener;

import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Dropper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiCountBug implements Listener {

    public AntiCountBug(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockDispense(BlockDispenseEvent event) {
        if (!event.isCancelled()) {
            Inventory content = null;
            if (event.getBlock().getType() == Material.DROPPER) {
                content = ((Dropper) event.getBlock().getState()).getInventory();
            } else if (event.getBlock().getType() == Material.DISPENSER) {
                content = ((Dispenser) event.getBlock().getState()).getInventory();
            }
            if (content != null && content.getSize() != 0) {
                int count = content.getSize();
                for (int i = 0; i < count; ++i) {
                    if (content.getItem(i) != null && content.getItem(i).getType() != Material.AIR && content.getItem(i).getAmount() < 0) {
                        content.getItem(i).setType(Material.AIR);
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.isCancelled()) {
            if (event.getCurrentItem() != null) {
                if (event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().getAmount() <= 0) {
                    event.getCurrentItem().setType(Material.AIR);
                    event.setCancelled(true);
                }
            } else if (event.getCursor() != null && event.getCursor().getType() != Material.AIR && event.getCursor().getAmount() <= 0) {
                event.getCursor().setType(Material.AIR);
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        if (!event.isCancelled()) {
            ItemStack[] stacks = event.getInventory().getContents();
            for (int i = 0; stacks != null && i < stacks.length; i++) {
                if (stacks[i] != null && stacks[i].getType() != Material.AIR && stacks[i].getAmount() <= 0) {
                    stacks[i].setType(Material.AIR);
                }
            }
        }
    }

    @EventHandler
    public void onSpawn(ItemSpawnEvent event) {
        if (event.getEntity().getItemStack().getAmount() < 0) {
            event.setCancelled(true);
        }
    }

}
