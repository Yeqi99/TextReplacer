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
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import yeqi.plugin.replacelib.data.ReplaceLibGetter;
import yeqi.plugin.replacelib.object.ReplaceElement;
import yeqi.plugin.replacelib.object.ReplaceLib;
import yeqi.plugin.textreplacer.TextReplacer;
import yeqi.tools.yeqilib.basecomponent.FunctionKey;
import yeqi.tools.yeqilib.basecomponent.Processing;
import yeqi.tools.yeqilib.message.Color;
import yeqi.tools.yeqilib.message.Sender;
import yeqi.tools.yeqilib.message.TextProcessing;

import java.util.ArrayList;
import java.util.List;

public class WorkListener implements Listener {
    public static List<String> workPlayerList=new ArrayList<>();
    public static ReplaceLib replaceLib;
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
                    functionKey.display="&c?????????????????? ";
                    functionKey.clickEventAction= ClickEvent.Action.SUGGEST_COMMAND;
                    functionKey.clickEventValue="object:"+Color.returnColor(display)+",type:display,"+"result:<??????????????????>";
                    functionKey.hoverEventEnable=true;
                    functionKey.hoverEventAction= HoverEvent.Action.SHOW_TEXT;
                    functionKey.hoverEventTextValue="???????????????????????????????????????<>??????????????????????????????";
                    if (replaceLib.hasElement(display)){
                        functionKey.display="&a?????????????????? ";
                        functionKey.clickEventValue="type:display,remove:"+Color.returnColor(display);
                        functionKey.hoverEventTextValue="??????????????????????????????";
                        new Sender(TextReplacer.getInstance()).sendToPlayerBC(player,
                                TextProcessing.Merge(functionKey.getBaseComponet(),Processing.StrToBaseComponent(ReplaceLibGetter.goReplaceLib(display))));
                    }else {
                        new Sender(TextReplacer.getInstance()).sendToPlayerBC(player,
                                TextProcessing.Merge(functionKey.getBaseComponet(),Processing.StrToBaseComponent(display)));
                    }
                }
                if (lore.size()>0){
                    for (String s: lore){
                        FunctionKey functionKey=new FunctionKey();
                        functionKey.clickEventEnable=true;
                        functionKey.display="&c?????????????????? ";
                        functionKey.clickEventAction= ClickEvent.Action.SUGGEST_COMMAND;
                        functionKey.clickEventValue="object:"+Color.returnColor(s)+",type:lore,"+"result:<??????????????????>";
                        functionKey.hoverEventEnable=true;
                        functionKey.hoverEventAction= HoverEvent.Action.SHOW_TEXT;
                        functionKey.hoverEventTextValue="???????????????????????????????????????<>??????????????????????????????";
                        if (replaceLib.hasElement(s)){
                            functionKey.display="&a?????????????????? ";
                            functionKey.clickEventValue="type:lore,remove:"+Color.returnColor(s);
                            functionKey.hoverEventTextValue="??????????????????????????????";
                            new Sender(TextReplacer.getInstance()).sendToPlayerBC(player,
                                    TextProcessing.Merge(functionKey.getBaseComponet(),Processing.StrToBaseComponent(ReplaceLibGetter.goReplaceLib(s))));
                        }else {
                            new Sender(TextReplacer.getInstance()).sendToPlayerBC(player,
                                    TextProcessing.Merge(functionKey.getBaseComponet(),Processing.StrToBaseComponent(s)));
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public static void PlayerSendMsg(AsyncPlayerChatEvent e){
        if (hasPlayer(e.getPlayer())){
            String formatStr=e.getMessage();
            TextProcessing tp=new TextProcessing(formatStr);
            if(tp.getValue("remove")!=null){
                while(replaceLib.hasElement(tp.getValue("remove"))){
                    replaceLib.removeElement(tp.getValue("remove"),tp.getValue("type"));
                    new Sender(TextReplacer.getInstance()).sendToPlayer(e.getPlayer(),"&a????????????!");
                }
                e.setCancelled(true);
                return;
            }
            replaceLib.addElement(formatStr);
            e.setCancelled(true);
            new Sender(TextReplacer.getInstance()).sendToPlayer(e.getPlayer(),"&a????????????!");
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
