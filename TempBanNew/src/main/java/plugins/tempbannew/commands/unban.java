package plugins.tempbannew.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import plugins.tempbannew.TempBanNew;

public class unban implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length != 1){
            sender.sendMessage("Usage: /unban [player]");
        }

        String player = args[0];

        if(TempBanNew.getInstance().unban(player)) {

            sender.sendMessage("Unbanned " + player);
        }
        else{
            sender.sendMessage("Invalid player!");
        }
        return true;
    }
}
