package plugins.tempbannew;
import jdk.tools.jlink.plugin.Plugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;



import java.util.List;



public class PlayerJoinEventListener implements Listener{



    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        String playerName = player.getName();

        boolean confirmation = TempBanNew.getInstance().getConfirmation(playerName);


        if (confirmation) {

            List<String> playerInfo = TempBanNew.getInstance().getPlayerInfo(playerName);
            if(playerInfo.size() != -1) {

                String staff = playerInfo.get(0);
                String reason = playerInfo.get(1);


                String finalReason = "Ai fost banat de " + staff + " pentru " + reason;


                TempBanNew.getInstance().kickPlayer(player, finalReason);
            }
        }

        event.getPlayer().sendMessage("Welcome to the server");


    }


}

