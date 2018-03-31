package cn.jiongjionger.listener;

import cn.jiongjionger.NeverLag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class ChatCommandRate implements Listener {

    NeverLag plugin;
    private HashMap CommandCoolDown = new HashMap();
    private HashMap ChatCoolDown = new HashMap();


    public ChatCommandRate(NeverLag plg) {
        this.plugin = plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);
        plg.getServer().getScheduler().scheduleSyncRepeatingTask(plg, new Runnable() {
            public void run() {
                ChatCommandRate.this.CacheClear();
            }
        }, 0L, 72000L);
    }

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void OnCommand(PlayerCommandPreprocessEvent e) {
        if (!e.isCancelled()) {
            if (!e.getPlayer().isOp()) {
                if (!e.getPlayer().hasPermission("ChatCommandRate.Pass")) {
                    String message = e.getMessage().toLowerCase();
                    message = message.replace(" ", "").replace("。", ".").replace("：", ":");
                    if (!message.contains("com:") && !message.contains("net:") && !message.contains("cn:") && !message.contains("cc:") && !message.contains("tk:") && !message.contains("org:")) {
                        String[] command = e.getMessage().toLowerCase().split(" ");
                        if (command.length >= 1 && NeverLag.CommandWhiteList.contains(command[0])) {
                            command = null;
                        } else {
                            command = null;
                            long time;
                            if (this.CommandCoolDown.containsKey(e.getPlayer().getName())) {
                                if (((Long) this.CommandCoolDown.get(e.getPlayer().getName())).longValue() > System.currentTimeMillis() / 1000L) {
                                    e.getPlayer().sendMessage(NeverLag.m_commandrate);
                                    e.setCancelled(true);
                                } else {
                                    time = System.currentTimeMillis() / 1000L + (long) NeverLag.CommandDelay;
                                    this.CommandCoolDown.put(e.getPlayer().getName(), Long.valueOf(time));
                                }
                            } else {
                                time = System.currentTimeMillis() / 1000L + (long) NeverLag.CommandDelay;
                                this.CommandCoolDown.put(e.getPlayer().getName(), Long.valueOf(time));
                            }

                        }
                    } else {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(NeverLag.m_spam);
                    }
                }
            }
        }
    }

    @EventHandler
    public void OnChat(AsyncPlayerChatEvent e) {
        if (!e.isCancelled()) {
            if (!e.getPlayer().isOp()) {
                if (!e.getPlayer().hasPermission("ChatCommandRate.Pass")) {
                    String message = e.getMessage().toLowerCase();
                    message = message.replace(" ", "").replace("。", ".").replace("：", ":");
                    if (!message.contains("com:") && !message.contains("net:") && !message.contains("cn:") && !message.contains("cc:") && !message.contains("tk:") && !message.contains("org:")) {
                        long time;
                        if (this.ChatCoolDown.containsKey(e.getPlayer().getName())) {
                            if (((Long) this.ChatCoolDown.get(e.getPlayer().getName())).longValue() > System.currentTimeMillis() / 1000L) {
                                e.getPlayer().sendMessage(NeverLag.m_chatrate);
                                e.setCancelled(true);
                            } else {
                                time = System.currentTimeMillis() / 1000L + (long) NeverLag.ChatDelay;
                                this.ChatCoolDown.put(e.getPlayer().getName(), Long.valueOf(time));
                            }
                        } else {
                            time = System.currentTimeMillis() / 1000L + (long) NeverLag.ChatDelay;
                            this.ChatCoolDown.put(e.getPlayer().getName(), Long.valueOf(time));
                        }

                    } else {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(NeverLag.m_spam);
                    }
                }
            }
        }
    }

    @EventHandler
    public void OnQuit(PlayerQuitEvent e) {
        if (this.ChatCoolDown.containsKey(e.getPlayer().getName())) {
            this.ChatCoolDown.remove(e.getPlayer().getName());
        }

        if (this.ChatCoolDown.containsKey(e.getPlayer().getName())) {
            this.ChatCoolDown.remove(e.getPlayer().getName());
        }

    }

    public void CacheClear() {
        this.CommandCoolDown.clear();
        this.ChatCoolDown.clear();
    }
}
