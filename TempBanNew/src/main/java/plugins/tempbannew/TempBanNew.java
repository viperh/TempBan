package plugins.tempbannew;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;


import org.bukkit.entity.Player;
import plugins.tempbannew.commands.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class TempBanNew extends JavaPlugin {

    private FileConfiguration config;

    private static TempBanNew instance;





    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        getCommand("tempban").setExecutor(new tempban());
        getCommand("unban").setExecutor(new unban());


        PlayerJoinEventListener playerJoinEventListener = new PlayerJoinEventListener();

        getServer().getPluginManager().registerEvents(playerJoinEventListener, this);


        config = getConfig();
        if(config == null){
            saveDefaultConfig();
            config = getConfig();
        }

    }

    public void kickPlayer(Player player, String reason){
        if(player != null && player.isOnline()){
            player.kickPlayer(reason);
        }
    }



    public List<String> getPlayerInfo(String playerName){
        List<String> playerInfo = new ArrayList<>();



        ConfigurationSection playerSec = config.getConfigurationSection(playerName);
        String staff = playerSec.getString("Staff");
        String reason = playerSec.getString("Reason");

        playerInfo.add(staff);
        playerInfo.add(reason);

        return playerInfo;
    }

    public boolean unban(String player){

        if(config.contains(player)) {

            config.set(player, null);

            saveConfig();
            return true;
        }
        return false;
    }

    public void addPlayerToBanList(String playerName, String staff, String reason){
        config.createSection(playerName);
        ConfigurationSection playerSection = config.getConfigurationSection(playerName);
        playerSection.set("Staff", staff);
        playerSection.set("Reason", reason);

        saveConfig();

    }
    public boolean getConfirmation(String playerName){

        return config.contains(playerName);

    }

    public static TempBanNew getInstance(){
        return instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}
