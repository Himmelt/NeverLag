package cn.jiongjionger.neverlag.listener;

import cn.jiongjionger.neverlag.NeverLag;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiBigShop implements Listener {

    public AntiBigShop(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void OnPlaceChest(BlockPlaceEvent event) {
        if (!event.isCancelled()) {
            if ((event.getBlock().getType().equals(Material.CHEST) || event.getBlock().getType().equals(Material.TRAPPED_CHEST)) && this.CheckShop(event.getBlock()).booleanValue()) {
                event.getPlayer().sendMessage(NeverLag.m_anti_bigshop);
                event.setCancelled(true);
            }
        }
    }

    private Boolean CheckShop(Block block) {
        if (block == null) {
            return Boolean.FALSE;
        } else {
            Block nearchest = null;
            if (block.getType().equals(Material.CHEST)) {
                nearchest = this.GetNearChest(block);
            } else if (block.getType().equals(Material.TRAPPED_CHEST)) {
                nearchest = this.GetNearTrappedChest(block);
            }
            if (nearchest == null) {
                return Boolean.FALSE;
            } else {
                Sign sign;
                String content;
                int var5;
                int var6;
                String[] var7;
                if (nearchest.getRelative(BlockFace.EAST).getType().equals(Material.WALL_SIGN)) {
                    sign = (Sign) nearchest.getRelative(BlockFace.EAST).getState();
                    var6 = (var7 = sign.getLines()).length;

                    for (var5 = 0; var5 < var6; ++var5) {
                        content = var7[var5];
                        if (content.toLowerCase().contains("quickshop")) {
                            return Boolean.TRUE;
                        }
                    }
                }

                if (nearchest.getRelative(BlockFace.NORTH).getType().equals(Material.WALL_SIGN)) {
                    sign = (Sign) nearchest.getRelative(BlockFace.NORTH).getState();
                    var6 = (var7 = sign.getLines()).length;

                    for (var5 = 0; var5 < var6; ++var5) {
                        content = var7[var5];
                        if (content.toLowerCase().contains("quickshop")) {
                            return Boolean.TRUE;
                        }
                    }
                }

                if (nearchest.getRelative(BlockFace.SOUTH).getType().equals(Material.WALL_SIGN)) {
                    sign = (Sign) nearchest.getRelative(BlockFace.SOUTH).getState();
                    var6 = (var7 = sign.getLines()).length;

                    for (var5 = 0; var5 < var6; ++var5) {
                        content = var7[var5];
                        if (content.toLowerCase().contains("quickshop")) {
                            return Boolean.TRUE;
                        }
                    }
                }

                if (nearchest.getRelative(BlockFace.WEST).getType().equals(Material.WALL_SIGN)) {
                    sign = (Sign) nearchest.getRelative(BlockFace.WEST).getState();
                    var6 = (var7 = sign.getLines()).length;

                    for (var5 = 0; var5 < var6; ++var5) {
                        content = var7[var5];
                        if (content.toLowerCase().contains("quickshop")) {
                            return Boolean.TRUE;
                        }
                    }
                }

                return Boolean.FALSE;
            }
        }
    }

    private Block GetNearChest(Block b) {
        return b.getRelative(BlockFace.EAST).getType().equals(Material.CHEST) ? b.getRelative(BlockFace.EAST) : (b.getRelative(BlockFace.NORTH).getType().equals(Material.CHEST) ? b.getRelative(BlockFace.NORTH) : (b.getRelative(BlockFace.SOUTH).getType().equals(Material.CHEST) ? b.getRelative(BlockFace.SOUTH) : (b.getRelative(BlockFace.WEST).getType().equals(Material.CHEST) ? b.getRelative(BlockFace.WEST) : null)));
    }

    private Block GetNearTrappedChest(Block b) {
        return b.getRelative(BlockFace.EAST).getType().equals(Material.TRAPPED_CHEST) ? b.getRelative(BlockFace.EAST) : (b.getRelative(BlockFace.NORTH).getType().equals(Material.TRAPPED_CHEST) ? b.getRelative(BlockFace.NORTH) : (b.getRelative(BlockFace.SOUTH).getType().equals(Material.TRAPPED_CHEST) ? b.getRelative(BlockFace.SOUTH) : (b.getRelative(BlockFace.WEST).getType().equals(Material.TRAPPED_CHEST) ? b.getRelative(BlockFace.WEST) : null)));
    }

}
