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
 * This is a sample CommandExecutor.
 * The code for Quest 9 can be found near the bottom of this file.
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
			sender.sendMessage(ChatColor.RED + command.getUsage());
			return false;
		} else if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED
					+ "you are not logged in! you cannot use these commands!");
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
		} 


		else if (args[0].equalsIgnoreCase("superspeed")
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
		} else if (args[0].equalsIgnoreCase("maxlevel")
				&& sender.hasPermission("FirstPlugIn.maxlevel")) {
			Player andrew = plugin.getServer().getPlayer(args[1]);
			if (andrew != null) {
				andrew.setLevel(1);
				sender.sendMessage(ChatColor.RED + args[1] + "thought he could get farther by cheating.  WRONG! Back to Level 1 with you!");
				plugin.logger.info(args[1] + "thought he could get farther by cheating.  WRONG! Back to Level 1 with you!");
				
			} else {
				sender.sendMessage(ChatColor.RED + args[1]
						+ "does not have permission to go to max level");
			}
			return true;
		} 
		
/* The next two sections of code use Metadata,
 * as per the guidelines of Quest 9,
 * to turn the 'creating trees' ability on and off.  
 */
		else if (args[0].equalsIgnoreCase("creatingtrees")
			&& sender.hasPermission("FirstPlugIn.creatingtrees")) {
				Player andrew = (Player) sender;
				plugin.setMetadata(andrew, "creatingtrees", true, plugin);
				sender.sendMessage(ChatColor.RED + andrew.getName()
						+ "you can magically plant trees now!");
				plugin.logger.info(andrew.getName() + "can plant magically trees now");
				return true;
		} else if (args[0].equalsIgnoreCase ("notcreatingtrees")
				&& sender.hasPermission("FirstPlugIn.creatingtrees")) {
			Player andrew = (Player) sender;
			plugin.setMetadata(andrew, "creatingtrees", false, plugin);
			sender.sendMessage(ChatColor.RED + andrew.getName()
					+ "you cannot magically plant trees anymore");
			plugin.logger.info(andrew.getName() + "can no longer magically plant trees");
			return true;
		}
		
	 
		
		else {
			return false;
		}
	}

}
