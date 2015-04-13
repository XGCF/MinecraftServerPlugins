/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.xgcf.umfragen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
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
                            return auflistenTypen(sender);
                        case "list":
                            return auflistenUmfragen(sender);
                        case "reload":
                            return neuladen(sender);
                        default:
                            return false;
                    }
                case 2:
                    switch(args[0]){
                        case "create":
                            return erstellen(sender,args[1]);
                        case "open":
                            return oeffnen(sender,args[1]);
                        case "close":
                            return schliessen(sender,args[1]);
                        case "reopen":
                            return wiederOeffnen(sender,args[1]);
                        case "remove":
                            return entfernen(sender,args[1]);
                        case "detail":
                            return detailsAuflisten(sender,args[1]);
                        default:
                            return false;
                    }
                case 3:
                    switch(args[0]){
                        case "setfrage":
                            return frageSetzen(sender,args[1],args[2]);
                        case "settyp":
                            return typSetzen(sender,args[1],args[2]);
                        case "addvote":
                            return hinzufuegenVote(sender,args[1],args[2]);
                        case "removevote":
                            return entfernenVotes(sender,args[1],args[2]);
                        case "setmaxvotes":
                            return maxVotesSetzen(sender,args[1],args[2]);
                        case "setminvalue":
                            return minValueSetzen(sender,args[1],args[2]);
                        case "setmaxvalue":
                            return maxValueSetzen(sender,args[1],args[2]);
                        default:
                            return false;
                    }
                default:
                    return false;
            }
        }
        
        return false;
    }

    private boolean auflistenTypen(CommandSender sender) {
        if(sender instanceof ConsoleCommandSender){
            sender.sendMessage("Umfragentypen:");
            sender.sendMessage("- text");
            sender.sendMessage("- multitext");
            sender.sendMessage("- number");
            return true;
        }
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("umfragen.types")){
                p.sendMessage(ChatColor.LIGHT_PURPLE+"Umfragentypen:");
                p.sendMessage(ChatColor.LIGHT_PURPLE+"- text");
                p.sendMessage(ChatColor.LIGHT_PURPLE+"- multitext");
                p.sendMessage(ChatColor.LIGHT_PURPLE+"- number");
                return true;
            }else{
                p.sendMessage(ChatColor.RED+"Keine Permission!");
                return true;
            }
        }
        return false;
    }

    private boolean auflistenUmfragen(CommandSender sender) {
        if(sender instanceof ConsoleCommandSender){
            ArrayList<String> alUmfragen = (ArrayList<String>)umfragen.getList("umfragen");
            sender.sendMessage("Aktive Umfragen:");
            for (String umfrage : alUmfragen) {
                if(umfragen.getBoolean("umfragen."+umfrage)){
                    sender.sendMessage("- "+umfrage);
                    alUmfragen.remove(umfrage);
                }
            }
            sender.sendMessage("Inaktive Umfragen:");
            for (String umfrage : alUmfragen) {
                sender.sendMessage("- "+umfrage);
            }
            
            return true;
        }
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("umfragen.list")){
                ArrayList<String> alUmfragen = (ArrayList<String>)umfragen.getList("umfragen");
                sender.sendMessage("Aktive Umfragen:");
                for (String umfrage : alUmfragen) {
                    if(umfragen.getBoolean("umfragen."+umfrage)){
                        sender.sendMessage("- "+umfrage);
                        alUmfragen.remove(umfrage);
                    }
                }
                if(p.hasPermission("umfragen.list.closed")){
                    sender.sendMessage("Inaktive Umfragen:");
                    for (String umfrage : alUmfragen) {
                        sender.sendMessage("- "+umfrage);
                    }
                }
                return true;
            }else{
                p.sendMessage(ChatColor.RED+"Keine Permission!");
                return true;
            }
        }
        return false;
    }

    private boolean neuladen(CommandSender sender) {
        if(sender instanceof ConsoleCommandSender){
            File file = new File(getDataFolder(), "umfragen.yml");
            umfragen = YamlConfiguration.loadConfiguration(file);
            sender.sendMessage("[Umfragen] Reloaded!");
            return true;
        }
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("umfragen.reload")){
                File file = new File(getDataFolder(), "umfragen.yml");
                umfragen = YamlConfiguration.loadConfiguration(file);
                sender.sendMessage(ChatColor.LIGHT_PURPLE+"[Umfragen] Reloaded!");
                return true;
            }else{
                p.sendMessage(ChatColor.RED+"Keine Permission!");
                return true;
            }
        }
        return false;
    }

    private boolean erstellen(CommandSender sender, String name) {
        if(sender instanceof ConsoleCommandSender){
            if(umfragen.getList("umfragen").contains(name)){
                sender.sendMessage("Die Umfrage "+name+" existiert bereits!");
                return true;
            }
            umfragen.set("umfragen."+name+".owner", "!CONSOLE!");
            saveUmfragen();
            sender.sendMessage("Umfrage "+name+" erstellt!");
            return true;
        }
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("umfragen.create")){
                if(umfragen.getList("umfragen").contains(name)){
                    sender.sendMessage(ChatColor.LIGHT_PURPLE+"Die Umfrage "+ChatColor.BLUE+name+ChatColor.LIGHT_PURPLE+" existiert bereits!");
                    return true;
                }
                umfragen.set("umfragen."+name+".owner", p.getName());
                saveUmfragen();
                sender.sendMessage(ChatColor.LIGHT_PURPLE+"Umfrage "+ChatColor.BLUE+name+ChatColor.LIGHT_PURPLE+" erstellt!");
                return true;
            }else{
                p.sendMessage(ChatColor.RED+"Keine Permission!");
                return true;
            }
        }
        return false;
    }

    private boolean oeffnen(CommandSender sender, String name) {
        if(sender instanceof ConsoleCommandSender){
            if(!umfragen.getList("umfragen").contains(name)){
                sender.sendMessage("Umfrage "+name+" ist nicht vorhanden!");
                return true;
            }
            if(umfragen.getString("umfragen."+name+".frage") == null){
                sender.sendMessage("Umfrage "+name+" hat noch keine Frage!");
                return true;
            }
            if(umfragen.getString("umfragen."+name+".typ") == null){
                sender.sendMessage("Umfrage "+name+" hat noch keinen Typ!");
                return true;
            }
            switch(umfragen.getString("umfragen."+name+".typ")){
                case "text":
                    if(umfragen.getInt("umfragen."+name+".maxvotes",-1) == -1){
                        sender.sendMessage("Zum Typ 'text' fehlt die maximale Anzahl an Votes!");
                        return true;
                    }
                    break;
                case "multitext":
                    if(umfragen.getInt("umfragen."+name+".maxvotes",-1) == -1){
                        sender.sendMessage("Zum Typ 'multitext' fehlt die maximale Anzahl an Votes!");
                        return true;
                    }
                    if(umfragen.getList("umfragen."+name+"votes").size() < 2){
                        sender.sendMessage("Zum Typ 'multitext' müssen mindestens zwei Votes angegeben werden!");
                        return true;
                    }
                    break;
                case "number":
                    if(umfragen.getInt("umfragen."+name+".minvalue",-1) == -1){
                        sender.sendMessage("Zum Typ 'number' fehlt der Mindestwert!");
                        return true;
                    }
                    if(umfragen.getInt("umfragen."+name+".maxvalue",-1) == -1){
                        sender.sendMessage("Zum Typ 'number' fehlt der Maximalwert!");
                        return true;
                    }
                    if(umfragen.getInt("umfragen."+name+".maxvalue") <= umfragen.getInt("umfragen."+name+".minvalue")){
                        sender.sendMessage("Der Maximalwert muss über dem Minimalwert liegen!");
                        return true;
                    }
                    break;
                default:
                    sender.sendMessage("Der Typ '"+umfragen.getString("umfragen."+name+".typ")+"' ist nicht bekannt!");
                    return true;
            }
            umfragen.set("umfragen."+name+".aktiv", true);
            saveUmfragen();
            sender.sendMessage("Umfrage "+name+"aktiviert!");
            return true;
        }
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(!(umfragen.getString("umfragen."+name+"owner").equals(p.getName()) && p.hasPermission("umfragen.open")) || !p.hasPermission("umfragen.open.others") ){
                p.sendMessage(ChatColor.RED+"Keine Permission!");
                return true;
            }
            if(!umfragen.getList("umfragen").contains(name)){
                sender.sendMessage(ChatColor.LIGHT_PURPLE+"Umfrage "+ChatColor.BLUE+name+ChatColor.LIGHT_PURPLE+" ist nicht vorhanden!");
                return true;
            }
            if(umfragen.getString("umfragen."+name+".frage") == null){
                sender.sendMessage(ChatColor.LIGHT_PURPLE+"Umfrage "+ChatColor.BLUE+name+ChatColor.LIGHT_PURPLE+" hat noch keine "+ChatColor.BLUE+"Frage"+ChatColor.LIGHT_PURPLE+"!");
                return true;
            }
            if(umfragen.getString("umfragen."+name+".typ") == null){
                sender.sendMessage(ChatColor.LIGHT_PURPLE+"Umfrage "+ChatColor.BLUE+name+ChatColor.LIGHT_PURPLE+" hat noch keinen "+ChatColor.BLUE+"Typ"+ChatColor.LIGHT_PURPLE+"!");
                return true;
            }
            switch(umfragen.getString("umfragen."+name+".typ")){
                case "text":
                    if(umfragen.getInt("umfragen."+name+".maxvotes",-1) == -1){
                        sender.sendMessage(ChatColor.LIGHT_PURPLE+"Zum Typ "+ChatColor.BLUE+"'text' "+ChatColor.LIGHT_PURPLE+"fehlt die "+ChatColor.BLUE+"maximale Anzahl "+ChatColor.LIGHT_PURPLE+"an "+ChatColor.BLUE+"Votes"+ChatColor.LIGHT_PURPLE+"!");
                        return true;
                    }
                    break;
                case "multitext":
                    if(umfragen.getInt("umfragen."+name+".maxvotes",-1) == -1){
                        sender.sendMessage(ChatColor.LIGHT_PURPLE+"Zum Typ "+ChatColor.BLUE+"'multitext' "+ChatColor.LIGHT_PURPLE+"fehlt die "+ChatColor.BLUE+"maximale Anzahl "+ChatColor.LIGHT_PURPLE+"an "+ChatColor.BLUE+"Votes"+ChatColor.LIGHT_PURPLE+"!");
                        return true;
                    }
                    if(umfragen.getList("umfragen."+name+"votes").size() < 2){
                        sender.sendMessage(ChatColor.LIGHT_PURPLE+"Zum Typ "+ChatColor.BLUE+"'multitext' "+ChatColor.LIGHT_PURPLE+"müssen mindestens "+ChatColor.BLUE+"zwei Votes "+ChatColor.LIGHT_PURPLE+"angegeben werden!");
                        return true;
                    }
                    break;
                case "number":
                    if(umfragen.getInt("umfragen."+name+".minvalue",-1) == -1){
                        sender.sendMessage(ChatColor.LIGHT_PURPLE+"Zum Typ "+ChatColor.BLUE+"'number' "+ChatColor.LIGHT_PURPLE+"fehlt der "+ChatColor.BLUE+"Mindestwert"+ChatColor.LIGHT_PURPLE+"!");
                        return true;
                    }
                    if(umfragen.getInt("umfragen."+name+".maxvalue",-1) == -1){
                        sender.sendMessage(ChatColor.LIGHT_PURPLE+"Zum Typ "+ChatColor.BLUE+"'number' "+ChatColor.LIGHT_PURPLE+"fehlt der "+ChatColor.BLUE+"Maximalwert"+ChatColor.LIGHT_PURPLE+"!");
                        return true;
                    }
                    if(umfragen.getInt("umfragen."+name+".maxvalue") <= umfragen.getInt("umfragen."+name+".minvalue")){
                        sender.sendMessage(ChatColor.LIGHT_PURPLE+"Der "+ChatColor.BLUE+"Maximalwert "+ChatColor.LIGHT_PURPLE+"muss über dem "+ChatColor.BLUE+"Minimalwert "+ChatColor.LIGHT_PURPLE+"liegen!");
                        return true;
                    }
                    break;
                default:
                    sender.sendMessage(ChatColor.LIGHT_PURPLE+"Der Typ '"+ChatColor.BLUE+umfragen.getString("umfragen."+name+".typ")+ChatColor.LIGHT_PURPLE+"' ist nicht bekannt!");
                    return true;
            }
            umfragen.set("umfragen."+name+".aktiv", true);
            saveUmfragen();
            sender.sendMessage(ChatColor.LIGHT_PURPLE+"Umfrage "+ChatColor.BLUE+name+ChatColor.LIGHT_PURPLE+" aktiviert!");
            return true;
        }
        return false;
    }

    private boolean schliessen(CommandSender sender, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean wiederOeffnen(CommandSender sender, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean entfernen(CommandSender sender, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean detailsAuflisten(CommandSender sender, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean frageSetzen(CommandSender sender, String name, String frage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean typSetzen(CommandSender sender, String name, String typ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean hinzufuegenVote(CommandSender sender, String name, String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean entfernenVotes(CommandSender sender, String name, String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean maxVotesSetzen(CommandSender sender, String name, String anz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean minValueSetzen(CommandSender sender, String name, String anz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean maxValueSetzen(CommandSender sender, String name, String anz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void saveUmfragen() {
        try{
            umfragen.save(new File(getDataFolder(),"umfragen.yml"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
