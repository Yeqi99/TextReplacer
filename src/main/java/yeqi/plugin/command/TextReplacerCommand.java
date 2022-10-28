package yeqi.plugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yeqi.plugin.listener.work.WorkListener;
import yeqi.plugin.replacelib.data.ReplaceLibGetter;
import yeqi.plugin.replacelib.object.ReplaceLib;

public class TextReplacerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()){
            if (!sender.hasPermission("TextReplacer")){
                return true;
            }
        }
        if (args.length==0){
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")){
            ReplaceLibGetter.getData();
            sender.sendMessage("§c重载成功");
        }else if (args[0].equalsIgnoreCase("work")){
            if (!(sender instanceof Player)){
                return true;
            }
            Player player=(Player)sender;
            if (WorkListener.hasPlayer(player)){
                WorkListener.removePlayer(player);
            }else {
                WorkListener.addPlayer(player);
            }
            return true;
        }else if(args[0].equalsIgnoreCase("set")){
            ReplaceLib replaceLib=ReplaceLibGetter.getReplace(args[1]);
            if (replaceLib==null){
                sender.sendMessage("未找到指定替换库");
                return true;
            }
            WorkListener.replaceLib=replaceLib;
        }
        return false;
    }
}
