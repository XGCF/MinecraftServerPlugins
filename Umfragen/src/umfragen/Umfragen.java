/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package umfragen;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author vgraber
 */
public class Umfragen extends JavaPlugin {
    
    private YamlConfiguration umfragen;
    
    @Override
    public void onEnable() {
        try{
        File file = new File(getDataFolder(), "umfragen.yml");
        if(!file.exists()){
            file.createNewFile();
            YamlConfiguration.loadConfiguration(file).set("umfragen", "");
        }
        umfragen = YamlConfiguration.loadConfiguration(file);
        
        
        System.out.println("[Umfragen] Aktiviert!");
        }catch(Exception e){
            System.out.println("[Umfragen] Konnte nicht aktiviert werden!");
        }
        
    }

    @Override
    public void onDisable() {
        System.out.println("[Umfragen] Deaktiviert!");
    }
    
    
}
