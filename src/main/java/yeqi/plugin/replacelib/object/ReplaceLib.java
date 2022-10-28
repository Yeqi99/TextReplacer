package yeqi.plugin.replacelib.object;


import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ReplaceLib {
    public List<ReplaceElement> replaceElements=new ArrayList<>();
    public String id;
    public YamlConfiguration yml;
    public ReplaceLib(String id,YamlConfiguration yml){
        this.id=id;
        this.yml=yml;
    }
}
