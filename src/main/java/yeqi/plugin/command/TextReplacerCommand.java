package yeqi.plugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yeqi.plugin.listener.work.WorkListener;
import yeqi.plugin.replacelib.data.ReplaceLibGetter;
import yeqi.plugin.replacelib.object.ReplaceLib;
import yeqi.plugin.textreplacer.TextReplacer;
import yeqi.tools.yeqilib.message.Sender;

import java.util.ArrayList;
import java.util.List;

public class TextReplacerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()){
            if (!sender.hasPermission("TextReplacer.admin")){
                return true;
            }
        }
        if (args.length==0){
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")){
            TextReplacer.getInstance().reloadConfig();
            ReplaceLibGetter.getData();
            sender.sendMessage("§c重载成功");
        }else if (args[0].equalsIgnoreCase("work")){
            if (!(sender instanceof Player)){
                return true;
            }
            Player player=(Player)sender;
            if (WorkListener.hasPlayer(player)){
                WorkListener.removePlayer(player);
                new Sender(TextReplacer.getInstance()).sendToSender(sender,"&c你关闭了工作模式");
            }else {
                WorkListener.addPlayer(player);
                new Sender(TextReplacer.getInstance()).sendToSender(sender,"&c你开启了工作模式");
            }
            return true;
        }else if(args[0].equalsIgnoreCase("set")){
            ReplaceLib replaceLib=ReplaceLibGetter.getReplace(args[1]);
            if (replaceLib==null){
                sender.sendMessage("未找到指定替换库");
                return true;
            }
            WorkListener.replaceLib=replaceLib;
            new Sender(TextReplacer.getInstance()).sendToSender(sender,"&e当前工作库为:&d"+replaceLib.id);
            return true;
        }else {
            List<String> help=new ArrayList<String>(){{
                add("§c/tp work");
                add("§7切换工作模式");
                add("§c/tp set <lib-id>");
                add("§7切换工作库(默认为normal)");
                add("§c/tp reload");
                add("§7重载插件");
            }} ;
            new Sender(TextReplacer.getInstance()).sendToSender(sender,help);
        }
        return false;
    }
}
