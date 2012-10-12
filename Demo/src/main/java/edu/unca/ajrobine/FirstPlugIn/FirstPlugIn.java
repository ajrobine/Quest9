package edu.unca.ajrobine.FirstPlugIn;

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

}
