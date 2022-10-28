package yeqi.plugin.listener.protocol;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import yeqi.plugin.replacelib.data.ReplaceLibGetter;

import java.util.ArrayList;
import java.util.List;

public class ContainerListener extends PacketAdapter {
    public ContainerListener(JavaPlugin plugin) {
        super(plugin, ListenerPriority.HIGHEST,  PacketType.Play.Server.WINDOW_ITEMS);
    }
    public void onPacketSending(PacketEvent e){
        PacketContainer packet=e.getPacket();
        List<ItemStack> items=packet.getItemListModifier().read(0);
        List<ItemStack> resultItems=new ArrayList<>();
        for(ItemStack item:items){
            if(item.getType().equals(Material.AIR)){
                resultItems.add(item);
                continue;
            }
            ItemMeta itemMeta= item.getItemMeta();
        }
    }
}
