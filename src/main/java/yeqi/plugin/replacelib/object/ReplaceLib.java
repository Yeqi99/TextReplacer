package yeqi.plugin.replacelib.object;


import org.bukkit.configuration.file.YamlConfiguration;
import yeqi.plugin.textreplacer.TextReplacer;
import yeqi.tools.yeqilib.message.Color;
import yeqi.tools.yeqilib.message.TextProcessing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReplaceLib {
    public List<ReplaceElement> replaceElements=new ArrayList<>();
    public String id;
    public YamlConfiguration yml;
    public ReplaceLib(YamlConfiguration yml){
        this.yml=yml;
        this.id=this.yml.getString("id");
        for (String s:this.yml.getStringList("replace")){
            ReplaceElement replaceElement=new ReplaceElement(s,yml);
            replaceElements.add(replaceElement);
        }
    }
    public void saveYaml(){
        List<String> replace=new ArrayList<>();
        for (ReplaceElement replaceElement: replaceElements){
            replace.add(replaceElement.getFormat());
        }
        yml.set("replace",replace);
        try {
            yml.save(TextReplacer.getInstance().getDataFolder()+"/replacelib/"+id+".yml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void addElement(String formatStr){
        ReplaceElement replaceElement=new ReplaceElement(formatStr,yml);
        replaceElements.add(replaceElement);
        saveYaml();
    }
    public void removeElement(String object,String type){
        object=Color.toColor(object);
        for(int i=0;i<replaceElements.size();i++){
            if (replaceElements.get(i).object.equals(object)){
                if (replaceElements.get(i).type.equalsIgnoreCase(type)){
                    replaceElements.remove(i);
                    saveYaml();
                    return;
                }
            }
        }
    }
    public boolean hasElement(String object){
        object=Color.toColor(object);
        for (ReplaceElement replaceElement:replaceElements){
            if (object.contains(replaceElement.object)){
                return true;
            }
        }
        return false;
    }
    public ReplaceElement getElement(String object){
        object=Color.toColor(object);
        for (ReplaceElement replaceElement:replaceElements){
            if (replaceElement.object.equals(object)){
                return replaceElement;
            }
        }
        return null;
    }
}
