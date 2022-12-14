package yeqi.plugin.replacelib.data;


import org.bukkit.configuration.file.YamlConfiguration;
import yeqi.plugin.replacelib.object.ReplaceElement;
import yeqi.plugin.replacelib.object.ReplaceLib;
import yeqi.plugin.textreplacer.TextReplacer;
import yeqi.tools.yeqilib.file.DataGetter;
import yeqi.tools.yeqilib.message.Color;
import yeqi.tools.yeqilib.message.Sender;
import yeqi.tools.yeqilib.message.TextProcessing;

import java.util.ArrayList;
import java.util.List;

public class ReplaceLibGetter {
    static DataGetter dataGetter;
    public static List<ReplaceLib> replaceLibs=new ArrayList<>();
    public static void getData(){
        replaceLibs.clear();
        dataGetter=new DataGetter(TextReplacer.getInstance(),"replacelib");
        for (YamlConfiguration yamlConfiguration : dataGetter.ymlList) {
            if (!yamlConfiguration.getBoolean("enable")){
                continue;
            }
            ReplaceLib replaceLib=new ReplaceLib(yamlConfiguration);
            replaceLibs.add(replaceLib);
        }
    }
    public static List<String> goReplaceLib(List<String> strList){
        for (ReplaceLib replaceLib:replaceLibs){
            for (ReplaceElement replaceElement : replaceLib.replaceElements) {
                if (!replaceElement.type.equalsIgnoreCase("lore")){
                    continue;
                }
                if(TextProcessing.hasText(strList,replaceElement.object)){
                    strList= TextProcessing.replaceList(strList, replaceElement.object,replaceElement.result);
                }
            }
        }
        return strList;
    }
    public static String goReplaceLib(String str){
        for (ReplaceLib replaceLib:replaceLibs){
            for (ReplaceElement replaceElement : replaceLib.replaceElements) {
                if (!replaceElement.type.equalsIgnoreCase("display")){
                    continue;
                }
                if (str.contains(replaceElement.object)){
                    str=str.replace(replaceElement.object,replaceElement.result);
                }
            }
        }
        return str;
    }
    public static ReplaceLib getReplace(String id){
        for (ReplaceLib replaceLib:replaceLibs){
            if (replaceLib.id.equals(id)){
                return replaceLib;
            }
        }
        return null;
    }
}
