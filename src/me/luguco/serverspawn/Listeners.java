package me.luguco.serverspawn;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Andreas on 11.07.2017.
 */
public class Listeners implements Listener {

    private Main plugin;
    public Listeners(Main main) {

        this.plugin = main;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!p.hasPermission("spawn.nospawntp." + p.getWorld().getName() ) || !p.hasPermission("spawn.admin")){
            Main.Spawntp(p);
        }
        e.setJoinMessage("");
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage("");
    }
}
