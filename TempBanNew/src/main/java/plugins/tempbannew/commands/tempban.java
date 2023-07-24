package plugins.tempbannew.commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import plugins.tempbannew.TempBanNew;
public class tempban implements CommandExecutor{


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length != 2){
            sender.sendMessage("Usage: /tempban [player] [reason]");
        }
        else {
            String playerName = args[0];
            String reason = args[1];
            String staff = sender.getName();

            String finalReason = "Ai fost banat de " + staff + " pentru " + reason;

            Player player = Bukkit.getPlayerExact(playerName);

            if(player == null){
                sender.sendMessage("Invalid player");
            }
            else {




                TempBanNew.getInstance().addPlayerToBanList(playerName, staff, reason);
                TempBanNew.getInstance().kickPlayer(player, finalReason);
            }
        }

        return true;
    }
}
