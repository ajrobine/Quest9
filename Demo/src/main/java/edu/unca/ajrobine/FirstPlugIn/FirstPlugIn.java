package edu.unca.ajrobine.FirstPlugIn;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * This is the main class of the sample plug-in
 */
public class FirstPlugIn extends JavaPlugin {
	/*
	 * This is called when your plug-in is enabled
	 */
	FirstPlugInLogger logger;

	@Override
	public void onEnable() {
		// create a logger and use it
		logger = new FirstPlugInLogger(this);
		logger.info("plugin enabled");

		// save the configuration file
		saveDefaultConfig();

		// Create the SampleListener
		new FirstPlugInListener(this);

		// set the command executor for sample
		this.getCommand("demo").setExecutor(new FirstPlugInCommandExecutor(this));
	}

	/*
	 * This is called when your plug-in shuts down
	 */
	@Override
	public void onDisable() {
		logger.info("plugin disabled");

	}
	
	public void setMetadata(Player player, String key, Object value, FirstPlugIn plugin) {
		player.setMetadata(key, new FixedMetadataValue(plugin, value));
	}
	
	public Object getMetadata(Player player, String key, FirstPlugIn plugin) {
		List<MetadataValue> values = player.getMetadata(key);
		for (MetadataValue value : values) {
			if (value.getOwningPlugin().getDescription().getName()
					.equals(plugin.getDescription().getName())) {
				return (value.asBoolean());
			}
		}
		return null;
	}

}
