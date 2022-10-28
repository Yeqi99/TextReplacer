package yeqi.plugin.textreplacer;

import hook.ProtocolLibHook;
import yeqi.plugin.listener.protocol.ContainerListener;
import yeqi.tools.yeqilib.message.Sender;
import yeqi.tools.yeqilib.plugin.YeqiPlugin;

public final class TextReplacer extends YeqiPlugin {
    static Sender sd;
    @Override
    public void onEnable() {
        sd=new Sender(this);
        saveResource("replacelib/example.yml",false);
        ProtocolLibHook.pm.addPacketListener(new ContainerListener(this));
        sd.sendOnEnableMsgToLogger("TextReplacer","Yeqi","1.0.0","Premium");
    }

    @Override
    public void onDisable() {
        sd.sendOnDisableMsgToLogger("TextReplacer","Yeqi","1.0.0","Premium");
    }
}
