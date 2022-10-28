package yeqi.plugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import yeqi.plugin.replacelib.data.ReplaceLibGetter;
import yeqi.plugin.textreplacer.TextReplacer;

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
            TextReplacer.getInstance().reloadConfig();
            ReplaceLibGetter.getData();
        }
        return false;
    }
}
