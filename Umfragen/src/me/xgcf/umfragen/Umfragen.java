/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.xgcf.umfragen;

import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
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
                file.getParentFile().mkdirs();
                file.createNewFile();
                umfragen = YamlConfiguration.loadConfiguration(file);
                umfragen.set("umfragen", "");
                umfragen.save(file);
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
                            auflistenTypen();
                            break;
                        case "list":
                            auflistenUmfragen();
                            break;
                        case "reload":
                            neuladen();
                            break;
                        default:
                            return false;
                    }
                    break;
                case 2:
                    switch(args[0]){
                        case "create":
                            erstellen();
                            break;
                        case "open":
                            oeffnen();
                            break;
                        case "close":
                            schliessen();
                            break;
                        case "reopen":
                            wiederOeffnen();
                            break;
                        case "remove":
                            entfernen();
                            break;
                        case "detail":
                            detailsAuflisten();
                            break;
                        default:
                            return false;
                    }
                    break;
                case 3:
                    switch(args[0]){
                        case "setfrage":
                            frageSetzen();
                            break;
                        case "settyp":
                            typSetzen();
                            break;
                        case "addvote":
                            hinzufuegenVote();
                            break;
                        case "removevote":
                            entfernenVotes();
                            break;
                        case "setmaxvotes":
                            maxVotesSetzen();
                            break;
                        case "setminvalue":
                            minValueSetzen();
                            break;
                        case "setmaxvalue":
                            maxValueSetzen();
                            break;
                        default:
                            return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        
        return false;
    }

    private void auflistenTypen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void auflistenUmfragen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void neuladen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void erstellen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void oeffnen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void schliessen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void wiederOeffnen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void entfernen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void detailsAuflisten() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void frageSetzen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void typSetzen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void hinzufuegenVote() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void entfernenVotes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void maxVotesSetzen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void minValueSetzen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void maxValueSetzen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
