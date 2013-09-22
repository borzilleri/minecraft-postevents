package io.rampant.postevents;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import play.Plugin;

class PostEventsPlugin extends Plugin {
	@Override
	public void onStart() {
		Config conf = ConfigFactory.load()
			.withFallback(ConfigFactory.defaultReference());

		if( conf.getBoolean("postevents.events.JOIN") ) {
			Dispatcher.getInstance().register("join", PlayerStatus.getInstance());
		}

		if( conf.getBoolean("postevents.events.PART") ) {
			Dispatcher.getInstance().register("join", PlayerStatus.getInstance());
		}
	}
}
