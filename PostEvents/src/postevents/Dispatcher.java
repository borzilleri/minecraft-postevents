package postevents;

import com.google.common.base.Strings;

import java.util.Map;

public class Dispatcher {

	public enum TYPE {
		JOIN,
		PART
	}

	public static void trigger(TYPE event, Map<String, String> data) {
		if( POSTEvents.Config.sendEvents &&
			!Strings.isNullOrEmpty(POSTEvents.Config.host) ) {
			Thread t = new Thread(new PostService(event, data));
			t.start();
		}
	}

}
