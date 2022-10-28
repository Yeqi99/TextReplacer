package yeqi.plugin.listener.work;

import jdk.nashorn.internal.ir.IfNode;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import yeqi.plugin.textreplacer.TextReplacer;
import yeqi.tools.yeqilib.basecomponent.FunctionKey;
import yeqi.tools.yeqilib.basecomponent.Processing;
import yeqi.tools.yeqilib.message.Sender;
import yeqi.tools.yeqilib.message.TextProcessing;

import java.util.ArrayList;
import java.util.List;

public class WorkListener implements Listener {
    public static List<String> workPlayerList=new ArrayList<>();
    @EventHandler
    public static void PlayerClickInv(InventoryClickEvent e){
        Player player= (Player) e.getWhoClicked();
        if (hasPlayer(player)){
            InventoryAction action=e.getAction();
            if (action.equals(InventoryAction.PICKUP_ALL)){
                ItemStack itemStack= e.getCurrentItem();
                ItemMeta itemMeta=itemStack.getItemMeta();
                String display="";
                List<String> lore=new ArrayList<>();
                if (itemMeta.hasDisplayName()){
                    display=itemMeta.getDisplayName();
                }
                if (itemMeta.hasLore()){
                    lore=itemStack.getItemMeta().getLore();
                }
                if (!display.equals("")){
                    FunctionKey functionKey=new FunctionKey();
                    functionKey.clickEventEnable=true;
                    functionKey.display="&c点击更改名称 ";
                    functionKey.clickEventAction= ClickEvent.Action.SUGGEST_COMMAND;
                    functionKey.clickEventValue="/tp replace display <lib-id> "+display+" <要修改的文字>";
                    functionKey.hoverEventEnable=true;
                    functionKey.hoverEventAction= HoverEvent.Action.SHOW_TEXT;
                    functionKey.hoverEventTextValue="点击补全命令快速替换，使用<>包围的参数需自行更改";
                    functionKey.getBaseComponet();
                    new Sender(TextReplacer.getInstance()).sendToPlayerBC(player,TextProcessing.Merge(functionKey.getBaseComponet(),Processing.StrToBaseComponent(display)));
                }
                if (lore.size()>0){
                    for (String s: lore){
                        FunctionKey functionKey=new FunctionKey();
                        functionKey.clickEventEnable=true;
                        functionKey.display="&c点击更改本行 ";
                        functionKey.clickEventAction= ClickEvent.Action.SUGGEST_COMMAND;
                        functionKey.clickEventValue="/tp replace lore <lib-id> "+s+" <要修改的文字>";
                        new Sender(TextReplacer.getInstance()).sendToLogger(s);
                        functionKey.hoverEventEnable=true;
                        functionKey.hoverEventAction= HoverEvent.Action.SHOW_TEXT;
                        functionKey.hoverEventTextValue="点击补全命令快速替换，使用<>包围的参数需自行更改";
                        functionKey.getBaseComponet();
                        new Sender(TextReplacer.getInstance()).sendToPlayerBC(player,TextProcessing.Merge(functionKey.getBaseComponet(),Processing.StrToBaseComponent(s)));
                    }
                }
            }
        }
    }
    public static boolean hasPlayer(Player player){
        for (String s : workPlayerList) {
            if (player.getDisplayName().equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;
    }
    public static boolean addPlayer(Player player){
        if (hasPlayer(player)){
            return false;
        }else {
            workPlayerList.add(player.getDisplayName());
            return true;
        }
    }
    public static boolean removePlayer(Player player){
        if (hasPlayer(player)){
            workPlayerList.remove(player.getDisplayName());
            return true;
        }else {
            return false;
        }
    }
}
