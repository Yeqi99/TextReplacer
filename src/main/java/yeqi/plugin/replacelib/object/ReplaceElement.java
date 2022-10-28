package yeqi.plugin.replacelib.object;

import org.bukkit.configuration.file.YamlConfiguration;
import yeqi.tools.yeqilib.message.TextProcessing;

public class ReplaceElement {
    public String object;
    public String result;
    public ReplaceElement(String str, YamlConfiguration yml){

    }
    public ReplaceElement(String str){

    }
    public void getDataFromStr(String str){
        TextProcessing tp=new TextProcessing(str);
        object=tp.getValue("object");
        
    }
}
