/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package umfragen;

import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

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
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = null;
        if(sender instanceof Player){
            p = (Player)sender;
        }
        
        if(command.getName().equalsIgnoreCase("u") || command.getName().equalsIgnoreCase("umfragen")){
            switch(args.length){
                case 1:
                    switch(args[0]){
                        case "types":

                            break;
                        case "list":

                            break;
                        case "reload":

                            break;
                        default:
                            return false;
                    }
                    break;
                case 2:
                    switch(args[0]){
                        case "create":

                            break;
                        case "open":

                            break;
                        case "close":

                            break;
                        case "reopen":

                            break;
                        case "remove":

                            break;
                        case "detail":

                            break;
                        default:
                            return false;
                    }
                    break;
                case 3:
                    switch(args[0]){
                        case "setfrage":

                            break;
                        case "settyp":

                            break;
                        case "addvote":

                            break;
                        case "removevote":

                            break;
                        case "setmaxvotes":

                            break;
                        case "setminvalue":

                            break;
                        case "setmaxvalue":

                            break;
                        case "detail":

                            break;
                        
                    }
                    break;
                default:
                    return false;
            }
        }
        
        return false;
    }
    
    
    
    
}
