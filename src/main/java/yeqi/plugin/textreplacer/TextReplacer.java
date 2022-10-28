package yeqi.plugin.textreplacer;

import yeqi.tools.yeqilib.message.Sender;
import yeqi.tools.yeqilib.plugin.YeqiPlugin;

public final class TextReplacer extends YeqiPlugin {
    static Sender sd;
    @Override
    public void onEnable() {
        sd=new Sender(this);
        sd.sendOnEnableMsgToLogger("TextReplacer","Yeqi","1.0.0","Premium");
    }

    @Override
    public void onDisable() {
        sd.sendOnDisableMsgToLogger("TextReplacer","Yeqi","1.0.0","Premium");
    }
}
