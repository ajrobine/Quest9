package edu.unca.ajrobine.FirstPlugIn;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Joiner;

/*
 * This is a sample CommandExectuor
 */
public class FirstPlugInCommandExecutor implements CommandExecutor {
	private final FirstPlugIn plugin;

	/*
	 * This command executor needs to reference the plugin it came
	 * from
	 */
	public FirstPlugInCommandExecutor(FirstPlugIn plugin) {
		this.plugin = plugin;
	}

	/*
	 * On command set the sample message
	 */
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length == 0) {
			return false;
		} else if (!(sender instanceof Player)) {
			return false;
			// the cake will be on the ground but not
			// where the player is looking
		} else if (args[0].equalsIgnoreCase("cake")) {
			Player andrew = (Player) sender;
			Location loc = andrew.getLocation();
			World w = loc.getWorld();
			loc.setX(loc.getX() + 1);
			Block b = w.getBlockAt(loc);
			b.setTypeId(92);
			return true;
			// how to stop the message from always starting
			// with the word message.
		} else if (args[0].equalsIgnoreCase("message")
				&& sender.hasPermission("FirstPlugIn.message")) {
			this.plugin.getConfig().set("sample.message",
					Joiner.on(' ').join(args));
			return true;
		} else if (args[0].equalsIgnoreCase("kick")
				&& sender.hasPermission("FirstPlugIn.kick")) {
			Player andrew = plugin.getServer().getPlayer(args[1]);
			if (andrew != null) {
				andrew.kickPlayer("you've been kicked off!");
				sender.sendMessage(ChatColor.RED + args[1] + " was kicked off");
				plugin.logger.info(args[1] + " has been kicked off");
			} else {
				sender.sendMessage(ChatColor.RED + args[1]
						+ " is not logged on");
			}
			return true;
		} else if (args[0].equalsIgnoreCase("extrahealth")
				&& sender.hasPermission("FirstPlugIn.extrahealth")) {
			Player andrew = plugin.getServer().getPlayer(args[1]);
			if (andrew != null) {
				andrew.setHealth(20);
				sender.sendMessage(ChatColor.RED + args[1] + "got his health back");
				plugin.logger.info(args[1] + "got his health back");
			} else {
				sender.sendMessage(ChatColor.RED + args[1]
						+ " does not have permission to gain health");
			}
			return true;
		} else if (args[0].equalsIgnoreCase("superspeed")
				&& sender.hasPermission("FirstPlugIn.superspeed")) {
			Player andrew = plugin.getServer().getPlayer(args[1]);
			if (andrew != null) {
				andrew.setWalkSpeed(10);
				sender.sendMessage(ChatColor.RED + args[1] + "is walking at super speed!");
				plugin.logger.info(args[1] + "is walking at super speed!");
			} else {
				sender.sendMessage(ChatColor.RED + args[1]
						+ "does not have permission to walk faster");
			}
		    
		    return true;
		} else if (args[0].equalsIgnoreCase("freeexperience")
				&& sender.hasPermission("FirstPlugIn.freeexperience")) {
			Player andrew = plugin.getServer().getPlayer(args[1]);
			if (andrew != null) {
				andrew.setExp(100);
				sender.sendMessage(ChatColor.RED + args[1] + "gained 100 points experience!");
				plugin.logger.info(args[1] + "gained 100 experience!");
			} else {
				sender.sendMessage(ChatColor.RED + args[1]
						+ "does not have permission to gain free experience");
			
			}
			return true;
		}
		
         
		else {
			return false;
		}
	}

}
