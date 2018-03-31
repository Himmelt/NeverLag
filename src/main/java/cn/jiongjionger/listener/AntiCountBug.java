package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
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

public class AntiCountBug implements Listener {

    NeverLag plugin;


    public AntiCountBug(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
    }

    @EventHandler
    public void onBlockDispense(BlockDispenseEvent e) {
        if (!e.isCancelled()) {
            Inventory content = null;
            if (e.getBlock().getType() == Material.DROPPER) {
                content = ((Dropper) e.getBlock().getState()).getInventory();
            } else if (e.getBlock().getType() == Material.DISPENSER) {
                content = ((Dispenser) e.getBlock().getState()).getInventory();
            }

            if (content != null && content.getSize() != 0) {
                int count = content.getSize();

                for (int i = 0; i < count; ++i) {
                    if (content.getItem(i) != null && content.getItem(i).getType() != Material.AIR && content.getItem(i).getAmount() < 0) {
                        content.getItem(i).setType(Material.AIR);
                        e.setCancelled(true);
                    }
                }

                boolean var5 = false;
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!e.isCancelled()) {
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().getAmount() <= 0) {
                    e.getCurrentItem().setType(Material.AIR);
                    e.setCancelled(true);
                }
            } else if (e.getCursor() != null && e.getCursor().getType() != Material.AIR && e.getCursor().getAmount() <= 0) {
                e.getCursor().setType(Material.AIR);
                e.setCancelled(true);
            }

        }
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent e) {
        if (!e.isCancelled()) {
            ItemStack[] var5;
            int var4 = (var5 = e.getInventory().getContents()).length;

            for (int var3 = 0; var3 < var4; ++var3) {
                ItemStack item = var5[var3];
                if (item != null && item.getType() != Material.AIR && item.getAmount() <= 0) {
                    item.setType(Material.AIR);
                }
            }

        }
    }

    @EventHandler
    public void onSpawn(ItemSpawnEvent e) {
        if (e.getEntity().getItemStack().getAmount() < 0) {
            e.setCancelled(true);
        }

    }
}
