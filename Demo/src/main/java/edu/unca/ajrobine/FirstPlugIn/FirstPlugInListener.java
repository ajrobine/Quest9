package edu.unca.ajrobine.FirstPlugIn;

import java.text.MessageFormat;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.TreeType;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

/*
 * This is a sample event listener
 */
public class FirstPlugInListener implements Listener {
    private final FirstPlugIn plugin;

    /*
     * This listener needs to know about the plugin which it came from
     */
    public FirstPlugInListener(FirstPlugIn plugin) {
        // Register the listener
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        
        this.plugin = plugin;
    }

    /*
     * Send the sample message to all players that join
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage(this.plugin.getConfig().getString("sample.message"));
    }
    
    /*
     * Another example of a event handler. This one will give you the name of
     * the entity you interact with, if it is a Creature it will give you the
     * creature Id.
     */
    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        final EntityType entityType = event.getRightClicked().getType();

        event.getPlayer().sendMessage(MessageFormat.format(
                "You interacted with a {0} it has an id of {1}",
                entityType.getName(),
                entityType.getTypeId()));
    }
    
    /* This event handler allows the user to create a pine tree
     * by right-clicking a block.  If there is already a pine tree
     * there, then the tree is removed and replaced with a new tree. 
    */ 
     
    @EventHandler(priority = EventPriority.LOW)
    public void pineTreeEvent(PlayerInteractEvent event) {
    	if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
    		if ((Boolean) plugin.getMetadata(event.getPlayer(), "creatingtrees", plugin)) {
    		Block b = event.getClickedBlock();
    		if (b != null) {
    			Location loc = b.getLocation();
    			TreeType pine = null;
    			loc.getWorld().generateTree(loc, pine);
    			b.setType(Material.WOOD);
    			event.getPlayer().sendMessage("you planted a tree!");
    			plugin.logger.info(event.getPlayer() + "planted a tree!");
    		}
    		}
    	}
    }
    
    /*In the spirit of Halloween, this event handler creates an explosion
     *with a left click, but leaves a jack-o-lantern floating in midair, occupying the 
     *space of the block that was clicked.  An eerie sound of thunder
     *is also triggered, even though it may be sunny and there is no lightning.  Spooky!
     */
    
    @EventHandler(priority = EventPriority.LOW)
    public void secondEvent(PlayerInteractEvent event) {
    	if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
    		Block b = event.getClickedBlock();
    		if (b != null) {
    			Location loc = b.getLocation();
    			loc.getWorld().createExplosion(loc, 10);
    			b.setType(Material.JACK_O_LANTERN);
    			loc.getWorld().setThundering(true);
    			event.getPlayer().sendMessage("you found the headless horseman's head!");
    			plugin.logger.info(event.getPlayer() + "found the headless horseman's head!");
    		}
    	}
    }
    
    
}
