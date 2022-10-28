package yeqi.plugin.replacelib.data;


import org.bukkit.configuration.file.YamlConfiguration;
import yeqi.plugin.replacelib.object.ReplaceElement;
import yeqi.plugin.replacelib.object.ReplaceLib;
import yeqi.plugin.textreplacer.TextReplacer;
import yeqi.tools.yeqilib.file.DataGetter;
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
                if(TextProcessing.hasText(strList,replaceElement.object)){
                    strList= TextProcessing.replaceList(strList, replaceElement.object,replaceElement.result);
                }
            }
        }
        return strList;
    }
}
