package cn.jiongjionger;

import cn.jiongjionger.common.MonitorInfoBean;
import cn.jiongjionger.common.MonitorServiceImpl;
import cn.jiongjionger.listener.*;
import cn.jiongjionger.task.ClearEntity;
import cn.jiongjionger.task.ClearItem;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ConnectionSide;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.FieldAccessException;
import com.comphenix.protocol.reflect.StructureModifier;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

public class NeverLag extends JavaPlugin implements Listener {

    private ProtocolManager protocolManager;
    private static final String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    private static int intversion = 0;
    private static Class aNMSItemstack;
    private static Class aCraftItemstack;
    public static List NoClearItemWorld;
    public static Boolean ClearItem;
    public static int CleanItemTime;
    public static List NoClearItemIDList;
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
    public static List AntiCheatModCode;
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
        if (IsAntiBot.booleanValue()) {
            new AntiBot(this);
        }

        if (IsAntiExplode.booleanValue()) {
            new AntiExplode(this);
        }

        if (CheckRedStone.booleanValue()) {
            new CheckRedstone(this);
        }

        if (IsProtectFarm.booleanValue()) {
            new ProtectFarm(this);
        }

        if (IsAntiFarmMob.booleanValue()) {
            new MobFarmLimiter(this);
        }

        if (IsMobLimit.booleanValue()) {
            new MobLimiter(this);
        }

        if (IsNoSpawnChunk.booleanValue()) {
            new NoSpawnChunk(this);
        }

        if (ClearEntity.booleanValue()) {
            new ClearEntity(this);
        }

        if (ClearItem.booleanValue()) {
            new ClearItem(this);
        }

        if (ChatCommandRate.booleanValue()) {
            new ChatCommandRate(this);
        }

        if (AntiDoorCheat.booleanValue()) {
            new AntiDoorCheat(this);
        }

        if (AntiBonemealCheat.booleanValue() && intversion <= 170) {
            new NoBonemealCheat(this);
        }

        if (AntiNetherHopperCheat.booleanValue()) {
            new AntiNetherHopperCheat(this);
        }

        if (AntiCheatMod.booleanValue()) {
            new AntiCheatMod(this);
        }

        if (AntiMinecraft.booleanValue()) {
            new AntiMinecraft(this);
        }

        if (AntiCountBug.booleanValue()) {
            new AntiCountBug(this);
        }

        if (SpawnRate.booleanValue()) {
            new SpawnRate(this);
        }

        if (AntiChestBug.booleanValue()) {
            new AntiChestBug(this);
        }

        if (FixPluginBug.booleanValue()) {
            new FixPluginBug(this);
        }

        if (AntiMotdPing.booleanValue() && intversion < 183) {
            new AntiMotdPing(this);
        }

        if (AntiPMM.booleanValue() && intversion >= 180) {
            new AntiPMM(this);
        }

        if (AntiBigShop.booleanValue()) {
            new AntiBigShop(this);
        }

        if (AntiCrashSign.booleanValue()) {
            new AntiCrashSign(this);
        }

        if (AntCrashChat.booleanValue()) {
            new AntiCrashChat(this);
        }

        if (AntiRail.booleanValue()) {
            new AntiInfiniteRail(this);
        }

        if (AntiInvEditor.booleanValue()) {
            try {
                this.protocolManager = ProtocolLibrary.getProtocolManager();
            } catch (Exception var3) {
                this.getServer().getLogger().log(Level.WARNING, "Try to use NeverLag Anti-ALL-U-Want, you must install Protocolib frist!");
                this.getServer().getLogger().log(Level.WARNING, "想要使用NeverLag的防御ALL-U-WANT的功能，你必须先安装正确版本的Protocolib！");
                return;
            }

            if (this.protocolManager == null) {
                this.getServer().getLogger().log(Level.WARNING, "Try to use NeverLag Anti-ALL-U-Want, you must install Protocolib frist!");
                this.getServer().getLogger().log(Level.WARNING, "想要使用NeverLag的防御ALL-U-WANT的功能，你必须先安装正确版本的Protocolib！");
                return;
            }

            try {
                aNMSItemstack = Class.forName("net.minecraft.server." + version + ".ItemStack");
                aCraftItemstack = Class.forName("org.bukkit.craftbukkit." + version + ".inventory.CraftItemStack");
            } catch (ClassNotFoundException var2) {
                var2.printStackTrace();
            }

            this.getServer().getPluginManager().registerEvents(this, this);
            this.protocolManager.addPacketListener(new PacketAdapter(this, ConnectionSide.CLIENT_SIDE, ListenerPriority.NORMAL, new Integer[]{Integer.valueOf(107)}) {
                public void onPacketReceiving(PacketEvent e) {
                    if (!e.getPlayer().isOp()) {
                        StructureModifier modfier = e.getPacket().getItemModifier();
                        Object nmsStack = null;

                        try {
                            nmsStack = NeverLag.getMethod(NeverLag.aCraftItemstack, "asNMSCopy").invoke(NeverLag.aCraftItemstack, new Object[]{modfier.read(0)});
                        } catch (IllegalAccessException var8) {
                            ;
                        } catch (IllegalArgumentException var9) {
                            ;
                        } catch (InvocationTargetException var10) {
                            ;
                        } catch (FieldAccessException var11) {
                            ;
                        }

                        if (nmsStack != null) {
                            try {
                                if (((Boolean) NeverLag.getMethod(NeverLag.aNMSItemstack, "hasTag").invoke(nmsStack, new Object[0])).booleanValue()) {
                                    e.setCancelled(true);
                                    e.getPlayer().sendMessage(ChatColor.GREEN + "[NeverLag]请不要使用All-U-Want等作弊MOD！");
                                }
                            } catch (IllegalAccessException var5) {
                                ;
                            } catch (IllegalArgumentException var6) {
                                ;
                            } catch (InvocationTargetException var7) {
                                ;
                            }
                        }

                    }
                }
            });
        }

    }

    public void LoadConfig() {
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
            this.saveDefaultConfig();
        }

        FileConfiguration config = this.getConfig();
        ClearItem = Boolean.valueOf(config.getBoolean("ClearItem.Enable"));
        NoClearItemWorld = config.getStringList("ClearItem.NoClearItemWorld");
        CleanItemTime = config.getInt("ClearItem.Delay");
        NoClearItemIDList = config.getIntegerList("ClearItem.NoClearItemIDList");
        ClearItemFrame = Boolean.valueOf(config.getBoolean("ClearItem.ItemFrame"));
        Boat = Boolean.valueOf(config.getBoolean("ClearItem.Boat"));
        ExpBall = Boolean.valueOf(config.getBoolean("ClearItem.ExpBall"));
        FallingBlock = Boolean.valueOf(config.getBoolean("ClearItem.FallingBlock"));
        Painting = Boolean.valueOf(config.getBoolean("ClearItem.Painting"));
        Minecart = Boolean.valueOf(config.getBoolean("ClearItem.Minecart"));
        Arrow = Boolean.valueOf(config.getBoolean("ClearItem.Arrow"));
        Snowball = Boolean.valueOf(config.getBoolean("ClearItem.Snowball"));
        ClearEntity = Boolean.valueOf(config.getBoolean("ClearEntity.Enable"));
        ClearMobTime = config.getInt("ClearEntity.Delay");
        ClearEntity = Boolean.valueOf(config.getBoolean("ClearEntity.OnlyClearWhenLimit"));
        ClearLimitNum = config.getInt("ClearEntity.Limit");
        ClearAnimals = Boolean.valueOf(config.getBoolean("ClearEntity.Animals"));
        ClearMonster = Boolean.valueOf(config.getBoolean("ClearEntity.Monster"));
        ClearSquid = Boolean.valueOf(config.getBoolean("ClearEntity.Squid"));
        ClearVillager = Boolean.valueOf(config.getBoolean("ClearEntity.Villager"));
        CheckRedStone = Boolean.valueOf(config.getBoolean("RedStone.Enable"));
        RedstoneLimit = config.getInt("RedStone.Limit");
        RedstoneTime = config.getInt("RedStone.Time");
        RedstoneClearType = config.getIntegerList("RedStone.Clear");
        RedStoneDrop = Boolean.valueOf(config.getBoolean("RedStone.Drop"));
        RedStoneMessage = Boolean.valueOf(config.getBoolean("RedStone.IsMessage"));
        IsNoSpawnChunk = Boolean.valueOf(config.getBoolean("Chunk.NoSpawnChunk"));
        IsProtectFarm = Boolean.valueOf(config.getBoolean("ProtectFarm.Enable"));
        IsAntiExplode = Boolean.valueOf(config.getBoolean("AntiExplode.Enable"));
        IsAntiBot = Boolean.valueOf(config.getBoolean("AntiBot.Enable"));
        MaxIPLoginNum = config.getInt("AntiBot.Limit");
        IPLoginTime = config.getInt("AntiBot.Time");
        IPWhiteList = config.getStringList("AntiBot.WhiteList");
        IsAntiFarmMob = Boolean.valueOf(config.getBoolean("AntiFarmMob.Enable"));
        FarmCleanTime = config.getInt("AntiFarmMob.Time");
        FarmLimit = config.getInt("AntiFarmMob.Limit");
        IsMobLimit = Boolean.valueOf(config.getBoolean("MobLimit.Enable"));
        AnimalNum = config.getInt("MobLimit.Animal");
        MonsterNum = config.getInt("MobLimit.Monster");
        SpawnerMobChunkNum = config.getInt("MobLimit.SpawnerMobChunkNum");
        ChatCommandRate = Boolean.valueOf(config.getBoolean("ChatCommandRate.Enable"));
        ChatDelay = config.getInt("ChatCommandRate.ChatDelay");
        CommandDelay = config.getInt("ChatCommandRate.CommandDelay");
        CommandWhiteList = config.getStringList("ChatCommandRate.CommandWhiteList");
        AntiDoorCheat = Boolean.valueOf(config.getBoolean("AntiDoorCheat.Enable"));
        AntiBonemealCheat = Boolean.valueOf(config.getBoolean("AntiBonemealCheat.Enable"));
        BonemealBlackList = config.getIntegerList("AntiBonemealCheat.BlackList");
        AntiNetherHopperCheat = Boolean.valueOf(config.getBoolean("AntiNetherHopperCheat.Enable"));
        AntiCheatMod = Boolean.valueOf(config.getBoolean("AntiCheatMod.Enable"));
        AntiCheatModCode = config.getStringList("AntiCheatMod.Code");
        AntiMinecraft = Boolean.valueOf(config.getBoolean("AntiMinecraft.Enable"));
        AntiCountBug = Boolean.valueOf(config.getBoolean("AntiCountBug.Enable"));
        SpawnRate = Boolean.valueOf(config.getBoolean("SpawnRate.Enable"));
        ChunkRate = config.getInt("SpawnRate.ChunkRate");
        SpawnerRate = config.getInt("SpawnRate.SpawnerRate");
        IronRate = config.getInt("SpawnRate.IronRate");
        CommonRate = config.getInt("SpawnRate.CommonRate");
        PortalRate = config.getInt("SpawnRate.PortalRate");
        AntiDropFromSpawner = Boolean.valueOf(config.getBoolean("SpawnRate.AntiDropFromSpawner"));
        AntiDropExpFromSpawner = Boolean.valueOf(config.getBoolean("SpawnRate.AntiDropExpFromSpawner"));
        AntiChestBug = Boolean.valueOf(config.getBoolean("AntiChestBug.Enable"));
        FixPluginBug = Boolean.valueOf(config.getBoolean("FixPluginBug.Enable"));
        AntiMotdPing = Boolean.valueOf(config.getBoolean("AntiMotdPing.Enable"));
        AntiPMM = Boolean.valueOf(config.getBoolean("AntiPMM.Enable"));
        AntiBigShop = Boolean.valueOf(config.getBoolean("FixQuickShop.Enable"));
        AntiCrashSign = Boolean.valueOf(config.getBoolean("AntiCrashSign.Enable"));
        AntCrashChat = Boolean.valueOf(config.getBoolean("AntCrashChat.Enable"));
        AntiRail = Boolean.valueOf(config.getBoolean("AntiRail.Enable"));
        AntiInvEditor = Boolean.valueOf(config.getBoolean("AntiInvEditor.Enable"));
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
    public void onBlockFromTo(BlockFromToEvent e) {
        if (e.getToBlock().getType().equals(Material.SKULL)) {
            e.setCancelled(true);
        }

    }

    public boolean onCommand(CommandSender sender, Command cmnd, String label, String[] args) {
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
                            p.sendMessage(ChatColor.GREEN + "[NeverLag]配置文件重载完毕");
                        }

                        if (args[0].equalsIgnoreCase("gc")) {
                            long name_list = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                            System.gc();
                            System.runFinalization();
                            long name = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                            if (name < name_list) {
                                p.sendMessage(ChatColor.GREEN + "[NeverLag]强制垃圾回收完毕，总共回收了 " + (name_list - name) / 1024L / 1024L + "MB 内存。");
                            } else {
                                p.sendMessage(ChatColor.GREEN + "[NeverLag]强制垃圾回收完毕，但是没有有效的回收内存，请不要频繁使用该功能！");
                            }
                        }

                        if (args[0].equalsIgnoreCase("show")) {
                            MonitorServiceImpl name_list1 = new MonitorServiceImpl();
                            MonitorInfoBean send_namelist = null;

                            try {
                                send_namelist = name_list1.getMonitorInfoBean();
                            } catch (Exception var13) {
                                var13.printStackTrace();
                            }

                            sender.sendMessage("服务器操作系统：" + send_namelist.getOsName());
                            sender.sendMessage("服务器总CPU占用率：" + send_namelist.getCpuRatio());
                            sender.sendMessage("服务器进程数量：" + send_namelist.getTotalThread());
                            sender.sendMessage("当前程序最大可用内存：" + send_namelist.getMaxMemory());
                            sender.sendMessage("当前程序目前分配内存：" + send_namelist.getTotalMemory());
                            sender.sendMessage("当前程序目前剩余内存：" + send_namelist.getFreeMemory());
                            sender.sendMessage("服务器物理内存总量：" + send_namelist.getTotalMemorySize());
                            sender.sendMessage("服务器物理内存已使用：" + send_namelist.getUsedMemory());
                            sender.sendMessage("服务器物理内存剩余：" + send_namelist.getFreePhysicalMemorySize());
                            sender.sendMessage("当前运行环境PID： " + name_list1.getPid());
                            sender.sendMessage("当前运行环境剩余内存： " + name_list1.getFreeMemoryMB());
                            sender.sendMessage("当前运行环境使用内存：" + name_list1.getUsedMemoryMB());
                        }
                    }

                    if (args.length == 3 && args[0].equalsIgnoreCase("find")) {
                        ItemStack item;
                        Iterator var10;
                        ArrayList name_list2;
                        Player send_namelist1;
                        String send_namelist2;
                        Iterator name1;
                        String name2;
                        Iterator item1;
                        if (args[1].equalsIgnoreCase("display")) {
                            name_list2 = new ArrayList();
                            name1 = Bukkit.getOnlinePlayers().iterator();

                            while (name1.hasNext()) {
                                send_namelist1 = (Player) name1.next();
                                var10 = send_namelist1.getInventory().iterator();

                                while (var10.hasNext()) {
                                    item = (ItemStack) var10.next();
                                    if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().contains(args[2]) && !name_list2.contains(send_namelist1.getName())) {
                                        name_list2.add(send_namelist1.getName());
                                    }
                                }
                            }

                            send_namelist2 = "";

                            for (item1 = name_list2.iterator(); item1.hasNext(); send_namelist2 = send_namelist2 + name2 + " ") {
                                name2 = (String) item1.next();
                            }

                            p.sendMessage(send_namelist2);
                            name_list2.clear();
                        } else if (args[1].equalsIgnoreCase("lore")) {
                            name_list2 = new ArrayList();
                            name1 = Bukkit.getOnlinePlayers().iterator();

                            while (name1.hasNext()) {
                                send_namelist1 = (Player) name1.next();
                                var10 = send_namelist1.getInventory().iterator();

                                while (var10.hasNext()) {
                                    item = (ItemStack) var10.next();
                                    if (item != null && item.hasItemMeta() && item.getItemMeta().hasLore()) {
                                        Iterator var12 = item.getItemMeta().getLore().iterator();

                                        while (var12.hasNext()) {
                                            String lore = (String) var12.next();
                                            if (lore.contains(args[2]) && !name_list2.contains(send_namelist1.getName())) {
                                                name_list2.add(send_namelist1.getName());
                                            }
                                        }
                                    }
                                }
                            }

                            send_namelist2 = "";

                            for (item1 = name_list2.iterator(); item1.hasNext(); send_namelist2 = send_namelist2 + name2 + " ") {
                                name2 = (String) item1.next();
                            }

                            p.sendMessage(send_namelist2);
                            name_list2.clear();
                        }
                    }
                }

                return true;
            }
        }
    }

    public static Method getMethod(Class cl, String method) {
        Method[] var5;
        int var4 = (var5 = cl.getMethods()).length;

        for (int var3 = 0; var3 < var4; ++var3) {
            Method m = var5[var3];
            if (m.getName().equals(method)) {
                return m;
            }
        }

        return null;
    }
}
