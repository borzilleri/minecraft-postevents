package io.rampant.postevents;

import models.postevents.Event;

public interface PostEventListener {
	public void onEvent(Event event);
}
