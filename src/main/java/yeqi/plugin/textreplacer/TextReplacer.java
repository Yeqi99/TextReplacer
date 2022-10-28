package yeqi.plugin.textreplacer;

import hook.ProtocolLibHook;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import yeqi.plugin.command.TextReplacerCommand;
import yeqi.plugin.listener.protocol.ContainerListener;
import yeqi.plugin.listener.work.WorkListener;
import yeqi.plugin.replacelib.data.ReplaceLibGetter;
import yeqi.plugin.replacelib.object.ReplaceLib;
import yeqi.tools.yeqilib.command.CommandRegister;
import yeqi.tools.yeqilib.listener.ListenerRegister;
import yeqi.tools.yeqilib.message.Sender;
import yeqi.tools.yeqilib.plugin.YeqiPlugin;

public final class TextReplacer extends YeqiPlugin {
    static Sender sd;
    @Override
    public void onEnable() {
        super.onEnable();
        sd=new Sender(this);
        ReplaceLibGetter.getData();
        saveDefaultConfig();
        saveResource("replacelib/example.yml",false);
        ProtocolLibHook.pm.addPacketListener(new ContainerListener(this));
        sd.sendOnEnableMsgToLogger("TextReplacer","Yeqi","1.0.0","Premium");
        CommandRegister.register(this,new TextReplacerCommand(),"textreplacer");
        ListenerRegister.register(this,new WorkListener());
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player: Bukkit.getOnlinePlayers()){
                    if(player.getItemOnCursor().getType().equals(Material.AIR)){
                        if (player.getGameMode().equals(GameMode.SURVIVAL)){
                            player.getInventory().addItem(new ItemStack(Material.AIR));
                            player.updateInventory();
                        }
                    }
                }
            }
        }.runTaskTimerAsynchronously(this,0,20);
    }

    @Override
    public void onDisable() {
        sd.sendOnDisableMsgToLogger("TextReplacer","Yeqi","1.0.0","Premium");
    }
}
