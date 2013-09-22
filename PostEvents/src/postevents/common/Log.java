package postevents.common;

import cpw.mods.fml.common.FMLLog;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
	private static Logger instance;

	public static void init() {
		instance = Logger.getLogger("POSTEvents");
		instance.setParent(FMLLog.getLogger());
	}

	public static void log(Level level, String format, Object... data) {
		instance.log(level, String.format(format, data));
	}

	public static void info(String format, Object... data) {
		log(Level.INFO, format, data);
	}

	public static void warn(String format, Object... data) {
		log(Level.WARNING, format, data);
	}

	public static void severe(String format, Object... data) {
		log(Level.SEVERE, format, data);
	}
}
