package yeqi.plugin.replacelib.object;

import org.bukkit.configuration.file.YamlConfiguration;
import yeqi.tools.yeqilib.message.TextProcessing;

public class ReplaceElement {
    public String object;
    public String result;
    public ReplaceElement(String str, YamlConfiguration yml){
        getDataFromStr(str);
        if (result.contains("!")){
            result=yml.getString("key."+result.replace("!",""));
        }
        if (object.contains("!")){
            object=yml.getString("key."+object.replace("!",""));
        }
    }
    public void getDataFromStr(String str){
        TextProcessing tp=new TextProcessing(str);
        object=tp.getValue("object");
        result=tp.getValue("result");
    }
}
