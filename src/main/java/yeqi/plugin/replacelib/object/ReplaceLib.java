package yeqi.plugin.replacelib.object;


import org.bukkit.configuration.file.YamlConfiguration;
import yeqi.tools.yeqilib.message.TextProcessing;

import java.util.ArrayList;
import java.util.List;

public class ReplaceLib {
    public List<ReplaceElement> replaceElements=new ArrayList<>();
    public String id;
    public YamlConfiguration yml;
    public ReplaceLib(YamlConfiguration yml){
        this.yml=yml;
        this.id=yml.getString("id");
        for (String s:yml.getStringList("replace")){
            ReplaceElement replaceElement=new ReplaceElement(s,yml);
            replaceElements.add(replaceElement);
        }
    }
}
