package cn.jiongjionger.neverlag;

import cn.jiongjionger.neverlag.common.MonitorInfoBean;
import cn.jiongjionger.neverlag.common.MonitorServiceImpl;
import cn.jiongjionger.neverlag.listener.*;
import cn.jiongjionger.neverlag.task.ClearEntity;
import cn.jiongjionger.neverlag.task.ClearItem;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ConnectionSide;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

public class NeverLag extends JavaPlugin implements Listener {

    private ProtocolManager protocolManager;
    private static final String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    private static int intversion = 0;
    public static List NoClearItemWorld;
    public static Boolean ClearItem;
    public static int CleanItemTime;
    public static List<Integer> NoClearItemIDList;
    public static Boolean ClearItemFrame;
    public static Boolean Boat;
    public static Boolean ExpBall;
    public static Boolean FallingBlock;
    public static Boolean Painting;
    public static Boolean Minecart;
    public static Boolean Arrow;
    public static Boolean Snowball;
    public static Boolean ClearEntity;
    public static int ClearMobTime;
    public static Boolean ClearLimit;
    public static int ClearLimitNum;
    public static Boolean ClearAnimals;
    public static Boolean ClearMonster;
    public static Boolean ClearSquid;
    public static Boolean ClearVillager;
    public static Boolean CheckRedStone;
    public static int RedstoneLimit;
    public static int RedstoneTime;
    public static List RedstoneClearType;
    public static Boolean RedStoneDrop;
    public static Boolean RedStoneMessage;
    public static Boolean IsNoSpawnChunk;
    public static Boolean IsProtectFarm;
    public static Boolean IsAntiBot;
    public static Boolean IsAntiExplode;
    public static int MaxIPLoginNum;
    public static int IPLoginTime;
    public static List IPWhiteList;
    public static Boolean IsAntiFarmMob;
    public static int FarmLimit;
    public static int FarmCleanTime;
    public static Boolean IsMobLimit;
    public static int AnimalNum;
    public static int MonsterNum;
    public static int SpawnerMobChunkNum;
    public static Boolean AntiDoorCheat;
    public static Boolean AntiBonemealCheat;
    public static List BonemealBlackList;
    public static Boolean AntiNetherHopperCheat;
    public static Boolean AntiCheatMod;
    public static List<String> AntiCheatModCode;
    public static Boolean AntiMinecraft;
    public static Boolean AntiCountBug;
    public static Boolean SpawnRate;
    public static int ChunkRate;
    public static int SpawnerRate;
    public static int IronRate;
    public static int CommonRate;
    public static int PortalRate;
    public static Boolean AntiDropFromSpawner;
    public static Boolean AntiDropExpFromSpawner;
    public static Boolean ChatCommandRate;
    public static int ChatDelay;
    public static int CommandDelay;
    public static List CommandWhiteList;
    public static Boolean AntiChestBug;
    public static Boolean FixPluginBug;
    public static Boolean AntiMotdPing;
    public static Boolean AntiPMM;
    public static Boolean AntiInvEditor;
    public static Boolean AntiBigShop;
    public static Boolean AntiRail;
    public static Boolean AntiCrashSign;
    public static Boolean AntCrashChat;
    public static String m_chatrate;
    public static String m_commandrate;
    public static String m_redstone = "";
    public static String m_antibot = "";
    public static String m_clean_item = "";
    public static String m_clean_item_pre = "";
    public static String m_clean_mob = "";
    public static String m_antichest = "";
    public static String m_spam = "";
    public static String m_anti_bigshop = "";
    public static String m_anti_crashsign = "";


    public void onEnable() {
        this.LoadConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
        intversion = Integer.parseInt(Bukkit.getServer().getBukkitVersion().split("-")[0].replace(".", ""));
        if (IsAntiBot) {
            new AntiBot(this);
        }

        if (IsAntiExplode) {
            new AntiExplode(this);
        }

        if (CheckRedStone) {
            new CheckRedstone(this);
        }

        if (IsProtectFarm) {
            new ProtectFarm(this);
        }

        if (IsAntiFarmMob) {
            new MobFarmLimiter(this);
        }

        if (IsMobLimit) {
            new MobLimiter(this);
        }

        if (IsNoSpawnChunk) {
            new NoSpawnChunk(this);
        }

        if (ClearEntity) {
            new ClearEntity(this);
        }

        if (ClearItem) {
            new ClearItem(this);
        }

        if (ChatCommandRate) {
            new ChatCommandRate(this);
        }

        if (AntiDoorCheat) {
            new AntiDoorCheat(this);
        }

        if (AntiBonemealCheat && intversion <= 170) {
            new NoBonemealCheat(this);
        }

        if (AntiNetherHopperCheat) {
            new AntiNetherHopperCheat(this);
        }

        if (AntiCheatMod) {
            new AntiCheatMod(this);
        }

        if (AntiMinecraft) {
            new AntiMinecraft(this);
        }

        if (AntiCountBug) {
            new AntiCountBug(this);
        }

        if (SpawnRate) {
            new SpawnRate(this);
        }

        if (AntiChestBug) {
            new AntiChestBug(this);
        }

        if (FixPluginBug) {
            new FixPluginBug(this);
        }

        if (AntiMotdPing && intversion < 183) {
            new AntiMotdPing(this);
        }

/*        if (AntiPMM && intversion >= 180) {
            new AntiPMM(this);
        }*/

        if (AntiBigShop) {
            new AntiBigShop(this);
        }

        if (AntiCrashSign) {
            new AntiCrashSign(this);
        }

        if (AntCrashChat) {
            new AntiCrashChat(this);
        }

        if (AntiRail) {
            new AntiInfiniteRail(this);
        }

        if (AntiInvEditor) {
            try {
                this.protocolManager = ProtocolLibrary.getProtocolManager();
            } catch (Exception e) {
                this.getServer().getLogger().log(Level.WARNING, "Try to use NeverLag Anti-ALL-U-Want, you must install ProtocolLib first !");
                this.getServer().getLogger().log(Level.WARNING, "想要使用NeverLag的防御 ALL-U-WANT 的功能，你必须先安装正确版本的 ProtocolLib !");
                return;
            }

            if (this.protocolManager == null) {
                this.getServer().getLogger().log(Level.WARNING, "Try to use NeverLag Anti-ALL-U-Want, you must install ProtocolLib first !");
                this.getServer().getLogger().log(Level.WARNING, "想要使用NeverLag的防御 ALL-U-WANT 的功能，你必须先安装正确版本的 ProtocolLib !");
                return;
            }

            this.getServer().getPluginManager().registerEvents(this, this);
            this.protocolManager.addPacketListener(new PacketAdapter(this, ConnectionSide.CLIENT_SIDE, ListenerPriority.NORMAL, 107) {
                public void onPacketReceiving(PacketEvent event) {
                    if (!event.getPlayer().isOp()) {
                        net.minecraft.server.v1_7_R4.ItemStack nmsStack = CraftItemStack.asNMSCopy(event.getPacket().getItemModifier().read(0));
                        if (nmsStack != null && nmsStack.hasTag()) {
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.GREEN + "[NeverLag] 请不要使用 All-U-Want 等作弊MOD！");
                        }
                    }
                }
            });
        }
    }

    private void LoadConfig() {
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
            this.saveDefaultConfig();
        }

        FileConfiguration config = this.getConfig();
        ClearItem = config.getBoolean("ClearItem.Enable");
        NoClearItemWorld = config.getStringList("ClearItem.NoClearItemWorld");
        CleanItemTime = config.getInt("ClearItem.Delay");
        NoClearItemIDList = config.getIntegerList("ClearItem.NoClearItemIDList");
        ClearItemFrame = config.getBoolean("ClearItem.ItemFrame");
        Boat = config.getBoolean("ClearItem.Boat");
        ExpBall = config.getBoolean("ClearItem.ExpBall");
        FallingBlock = config.getBoolean("ClearItem.FallingBlock");
        Painting = config.getBoolean("ClearItem.Painting");
        Minecart = config.getBoolean("ClearItem.Minecart");
        Arrow = config.getBoolean("ClearItem.Arrow");
        Snowball = config.getBoolean("ClearItem.Snowball");
        ClearEntity = config.getBoolean("ClearEntity.Enable");
        ClearMobTime = config.getInt("ClearEntity.Delay");
        ClearEntity = config.getBoolean("ClearEntity.OnlyClearWhenLimit");
        ClearLimitNum = config.getInt("ClearEntity.Limit");
        ClearAnimals = config.getBoolean("ClearEntity.Animals");
        ClearMonster = config.getBoolean("ClearEntity.Monster");
        ClearSquid = config.getBoolean("ClearEntity.Squid");
        ClearVillager = config.getBoolean("ClearEntity.Villager");
        CheckRedStone = config.getBoolean("RedStone.Enable");
        RedstoneLimit = config.getInt("RedStone.Limit");
        RedstoneTime = config.getInt("RedStone.Time");
        RedstoneClearType = config.getIntegerList("RedStone.Clear");
        RedStoneDrop = config.getBoolean("RedStone.Drop");
        RedStoneMessage = config.getBoolean("RedStone.IsMessage");
        IsNoSpawnChunk = config.getBoolean("Chunk.NoSpawnChunk");
        IsProtectFarm = config.getBoolean("ProtectFarm.Enable");
        IsAntiExplode = config.getBoolean("AntiExplode.Enable");
        IsAntiBot = config.getBoolean("AntiBot.Enable");
        MaxIPLoginNum = config.getInt("AntiBot.Limit");
        IPLoginTime = config.getInt("AntiBot.Time");
        IPWhiteList = config.getStringList("AntiBot.WhiteList");
        IsAntiFarmMob = config.getBoolean("AntiFarmMob.Enable");
        FarmCleanTime = config.getInt("AntiFarmMob.Time");
        FarmLimit = config.getInt("AntiFarmMob.Limit");
        IsMobLimit = config.getBoolean("MobLimit.Enable");
        AnimalNum = config.getInt("MobLimit.Animal");
        MonsterNum = config.getInt("MobLimit.Monster");
        SpawnerMobChunkNum = config.getInt("MobLimit.SpawnerMobChunkNum");
        ChatCommandRate = config.getBoolean("ChatCommandRate.Enable");
        ChatDelay = config.getInt("ChatCommandRate.ChatDelay");
        CommandDelay = config.getInt("ChatCommandRate.CommandDelay");
        CommandWhiteList = config.getStringList("ChatCommandRate.CommandWhiteList");
        AntiDoorCheat = config.getBoolean("AntiDoorCheat.Enable");
        AntiBonemealCheat = config.getBoolean("AntiBonemealCheat.Enable");
        BonemealBlackList = config.getIntegerList("AntiBonemealCheat.BlackList");
        AntiNetherHopperCheat = config.getBoolean("AntiNetherHopperCheat.Enable");
        AntiCheatMod = config.getBoolean("AntiCheatMod.Enable");
        AntiCheatModCode = config.getStringList("AntiCheatMod.Code");
        AntiMinecraft = config.getBoolean("AntiMinecraft.Enable");
        AntiCountBug = config.getBoolean("AntiCountBug.Enable");
        SpawnRate = config.getBoolean("SpawnRate.Enable");
        ChunkRate = config.getInt("SpawnRate.ChunkRate");
        SpawnerRate = config.getInt("SpawnRate.SpawnerRate");
        IronRate = config.getInt("SpawnRate.IronRate");
        CommonRate = config.getInt("SpawnRate.CommonRate");
        PortalRate = config.getInt("SpawnRate.PortalRate");
        AntiDropFromSpawner = config.getBoolean("SpawnRate.AntiDropFromSpawner");
        AntiDropExpFromSpawner = config.getBoolean("SpawnRate.AntiDropExpFromSpawner");
        AntiChestBug = config.getBoolean("AntiChestBug.Enable");
        FixPluginBug = config.getBoolean("FixPluginBug.Enable");
        AntiMotdPing = config.getBoolean("AntiMotdPing.Enable");
        AntiPMM = config.getBoolean("AntiPMM.Enable");
        AntiBigShop = config.getBoolean("FixQuickShop.Enable");
        AntiCrashSign = config.getBoolean("AntiCrashSign.Enable");
        AntCrashChat = config.getBoolean("AntCrashChat.Enable");
        AntiRail = config.getBoolean("AntiRail.Enable");
        AntiInvEditor = config.getBoolean("AntiInvEditor.Enable");
        m_redstone = ChatColor.translateAlternateColorCodes('&', config.getString("RedStone.Message"));
        m_antibot = ChatColor.translateAlternateColorCodes('&', config.getString("AntiBot.Message"));
        m_clean_item = ChatColor.translateAlternateColorCodes('&', config.getString("ClearItem.Message"));
        m_clean_item_pre = ChatColor.translateAlternateColorCodes('&', config.getString("ClearItem.PreMessage"));
        m_clean_mob = ChatColor.translateAlternateColorCodes('&', config.getString("ClearEntity.Message"));
        m_commandrate = ChatColor.translateAlternateColorCodes('&', config.getString("ChatCommandRate.CommandMessage"));
        m_chatrate = ChatColor.translateAlternateColorCodes('&', config.getString("ChatCommandRate.ChatMessage"));
        m_antichest = ChatColor.translateAlternateColorCodes('&', config.getString("AntiChestBug.Message"));
        m_spam = ChatColor.translateAlternateColorCodes('&', config.getString("ChatCommandRate.Message"));
        m_anti_bigshop = ChatColor.translateAlternateColorCodes('&', config.getString("FixQuickShop.Message"));
        m_anti_crashsign = ChatColor.translateAlternateColorCodes('&', config.getString("AntiCrashSign.Message"));
    }

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        if (event.getToBlock().getType().equals(Material.SKULL)) {
            event.setCancelled(true);
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        } else {
            Player p = (Player) sender;
            if (!p.isOp()) {
                return true;
            } else {
                if (label.equalsIgnoreCase("NeverLag")) {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("reload")) {
                            this.LoadConfig();
                            p.sendMessage(ChatColor.GREEN + "[NeverLag] 配置文件重载完毕 !");
                        }

                        if (args[0].equalsIgnoreCase("gc")) {
                            long name_list = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                            System.gc();
                            System.runFinalization();
                            long name = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                            if (name < name_list) {
                                p.sendMessage(ChatColor.GREEN + "[NeverLag] 强制垃圾回收完毕 , 总共回收了 " + (name_list - name) / 1024L / 1024L + "MB 内存 .");
                            } else {
                                p.sendMessage(ChatColor.GREEN + "[NeverLag] 强制垃圾回收完毕 , 但是没有有效的回收内存 , 请不要频繁使用该功能 !");
                            }
                        }

                        if (args[0].equalsIgnoreCase("show")) {
                            MonitorServiceImpl monitorService = new MonitorServiceImpl();
                            MonitorInfoBean infoBean = monitorService.getMonitorInfoBean();

                            sender.sendMessage("服务器操作系统：" + infoBean.getOsName());
                            sender.sendMessage("服务器总CPU占用率：" + infoBean.getCpuRatio());
                            sender.sendMessage("服务器进程数量：" + infoBean.getTotalThread());
                            sender.sendMessage("当前程序最大可用内存：" + infoBean.getMaxMemory());
                            sender.sendMessage("当前程序目前分配内存：" + infoBean.getTotalMemory());
                            sender.sendMessage("当前程序目前剩余内存：" + infoBean.getFreeMemory());
                            sender.sendMessage("服务器物理内存总量：" + infoBean.getTotalMemorySize());
                            sender.sendMessage("服务器物理内存已使用：" + infoBean.getUsedMemory());
                            sender.sendMessage("服务器物理内存剩余：" + infoBean.getFreePhysicalMemorySize());
                            sender.sendMessage("当前运行环境PID： " + monitorService.getPid());
                            sender.sendMessage("当前运行环境剩余内存： " + monitorService.getFreeMemoryMB());
                            sender.sendMessage("当前运行环境使用内存：" + monitorService.getUsedMemoryMB());
                        }
                    }

                    if (args.length == 3 && args[0].equalsIgnoreCase("find")) {
                        ArrayList<String> nameList2 = new ArrayList<>();
                        String sendNamelist2;
                        String name2;
                        Iterator item1;
                        if (args[1].equalsIgnoreCase("display")) {
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                ItemStack[] stacks = player.getInventory().getContents();
                                for (int i = 0; stacks != null && i < stacks.length; i++) {
                                    if (stacks[i] != null && stacks[i].hasItemMeta() && stacks[i].getItemMeta().hasDisplayName() && stacks[i].getItemMeta().getDisplayName().contains(args[2]) && !nameList2.contains(player.getName())) {
                                        nameList2.add(player.getName());
                                    }
                                }
                            }

                            sendNamelist2 = "";

                            for (item1 = nameList2.iterator(); item1.hasNext(); sendNamelist2 = sendNamelist2 + name2 + " ") {
                                name2 = (String) item1.next();
                            }

                            p.sendMessage(sendNamelist2);
                            nameList2.clear();
                        } else if (args[1].equalsIgnoreCase("lore")) {
                            nameList2 = new ArrayList<>();

                            for (Player player : Bukkit.getOnlinePlayers()) {
                                ItemStack[] stacks = player.getInventory().getContents();
                                for (int i = 0; stacks != null && i < stacks.length; i++) {
                                    if (stacks[i] != null && stacks[i].hasItemMeta() && stacks[i].getItemMeta().hasLore()) {
                                        for (String lore : stacks[i].getItemMeta().getLore()) {
                                            if (lore.contains(args[2]) && !nameList2.contains(player.getName())) {
                                                nameList2.add(player.getName());
                                            }
                                        }
                                    }
                                }
                            }

                            sendNamelist2 = "";

                            for (item1 = nameList2.iterator(); item1.hasNext(); sendNamelist2 = sendNamelist2 + name2 + " ") {
                                name2 = (String) item1.next();
                            }
                            p.sendMessage(sendNamelist2);
                            nameList2.clear();
                        }
                    }
                }
                return true;
            }
        }
    }

}
