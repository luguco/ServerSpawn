package me.luguco.serverspawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by luguco on 11.07.2017.
 */

public class Main extends JavaPlugin implements  CommandExecutor{

    @Override
    public void onEnable(){
        Bukkit.getConsoleSender().sendMessage("§a[ServerSpawn] enabled!");
        createFile();
        new Listeners(this);
    }

    @Override
    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage("§c[ServerSpawn] disabled!");
    }

    public void createFile(){

        getConfig().options().copyDefaults(true);
        saveConfig();
    }



    public static void Spawntp(Player p) {
        if (getConfig().contains("Spawn")) {
            String world1 =  getConfig().getString("Spawn.world");
            World world =  Bukkit.getWorld(world1);
            double x = getConfig().getDouble("Spawn.x");
            double y = getConfig().getDouble("Spawn.y");
            double z = getConfig().getDouble("Spawn.z");
            float yaw = (float) getConfig().getDouble("Spawn.yaw");
            float pitch = (float) getConfig().getDouble("Spawn.pitch");
            Location loc = new Location(world, x, y, z, yaw, pitch);
            p.teleport(loc);
        }
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("spawn")){

                Spawntp(p);
            }else
                if(cmd.getName().equalsIgnoreCase("setspawn")){
                    if(p.hasPermission("spawn.setspawn")){
                        String world = p.getWorld().getName();
                        double x = p.getLocation().getX();
                        double y = p.getLocation().getY();
                        double z = p.getLocation().getZ();
                        double yaw = p.getLocation().getYaw();
                        double pitch = p.getLocation().getPitch();

                        getConfig().set("Spawn.world", world);
                        getConfig().set("Spawn.x", x);
                        getConfig().set("Spawn.y", y);
                        getConfig().set("Spawn.z", z);
                        getConfig().set("Spawn.yaw", yaw);
                        getConfig().set("Spawn.pitch", pitch);
                        saveConfig();

                        p.sendMessage("§aSpawn set");
                    }
                }
        }
        return true;
    }

}
