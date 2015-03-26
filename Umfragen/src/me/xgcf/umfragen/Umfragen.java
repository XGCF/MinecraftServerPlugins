/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.xgcf.umfragen;

import java.io.File;
import java.io.IOException;
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
        }catch(IOException ie){
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
                            auflistenTypen(sender);
                            break;
                        case "list":
                            auflistenUmfragen(sender);
                            break;
                        case "reload":
                            neuladen(sender);
                            break;
                        default:
                            return false;
                    }
                    break;
                case 2:
                    switch(args[0]){
                        case "create":
                            erstellen(sender,args[1]);
                            break;
                        case "open":
                            oeffnen(sender,args[1]);
                            break;
                        case "close":
                            schliessen(sender,args[1]);
                            break;
                        case "reopen":
                            wiederOeffnen(sender,args[1]);
                            break;
                        case "remove":
                            entfernen(sender,args[1]);
                            break;
                        case "detail":
                            detailsAuflisten(sender,args[1]);
                            break;
                        default:
                            return false;
                    }
                    break;
                case 3:
                    switch(args[0]){
                        case "setfrage":
                            frageSetzen(sender,args[1],args[2]);
                            break;
                        case "settyp":
                            typSetzen(sender,args[1],args[2]);
                            break;
                        case "addvote":
                            hinzufuegenVote(sender,args[1],args[2]);
                            break;
                        case "removevote":
                            entfernenVotes(sender,args[1],args[2]);
                            break;
                        case "setmaxvotes":
                            maxVotesSetzen(sender,args[1],args[2]);
                            break;
                        case "setminvalue":
                            minValueSetzen(sender,args[1],args[2]);
                            break;
                        case "setmaxvalue":
                            maxValueSetzen(sender,args[1],args[2]);
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

    private void auflistenTypen(CommandSender sender) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void auflistenUmfragen(CommandSender sender) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void neuladen(CommandSender sender) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void erstellen(CommandSender sender, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void oeffnen(CommandSender sender, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void schliessen(CommandSender sender, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void wiederOeffnen(CommandSender sender, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void entfernen(CommandSender sender, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void detailsAuflisten(CommandSender sender, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void frageSetzen(CommandSender sender, String name, String frage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void typSetzen(CommandSender sender, String name, String typ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void hinzufuegenVote(CommandSender sender, String name, String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void entfernenVotes(CommandSender sender, String name, String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void maxVotesSetzen(CommandSender sender, String name, String anz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void minValueSetzen(CommandSender sender, String name, String anz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void maxValueSetzen(CommandSender sender, String name, String anz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
