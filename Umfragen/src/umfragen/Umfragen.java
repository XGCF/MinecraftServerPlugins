/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package umfragen;

import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author vgraber
 */
public class Umfragen extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("[Umfragen] Aktiviert!");
        
    }

    @Override
    public void onDisable() {
        System.out.println("[Umfragen] Deaktiviert!");
    }
    
    
}
