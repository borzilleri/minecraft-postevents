package postevents;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import postevents.common.CommonProxy;
import postevents.common.Log;
import net.minecraftforge.common.Configuration;

@Mod(modid = "POSTEvents", name = "POSTEvents", version = "0.9-SNAPSHOT")
@NetworkMod(serverSideRequired = true)
public class POSTEvents {

	@Mod.Instance("POSTEvents")
	public static POSTEvents instance;

	@SidedProxy(clientSide = "postevents.client.ClientProxy",
		serverSide = "postevents.common.CommonProxy")
	public static CommonProxy proxy;

	public static class Config {
		public static boolean sendEvents = true;
		public static String host = "http://requestb.in/13aybxs1";
		public static boolean event_JOIN = true;
		public static boolean event_PART = true;
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Log.init();

		Configuration conf = new Configuration(event
			.getSuggestedConfigurationFile());

		Config.sendEvents = conf.get("general", "enabled", Config.sendEvents,
			"Enable sending events (false disables the mod).")
			.getBoolean(Config.sendEvents);
		Config.host = conf.get("remote", "host", Config.host,
			"Remote host URI to POST events to (REQUIRED!).").getString();
		Config.event_JOIN = conf.get("events", "join", Config.event_JOIN,
			"Send the player JOIN event.").getBoolean(Config.event_JOIN);
		Config.event_PART = conf.get("events", "part", Config.event_PART,
			"Send the player PART event.").getBoolean(Config.event_PART);

		conf.save();
	}

	@Mod.EventHandler
	public void init(FMLPostInitializationEvent event) {
		proxy.init();
	}
}
