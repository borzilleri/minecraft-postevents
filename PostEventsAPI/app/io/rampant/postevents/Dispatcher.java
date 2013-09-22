package io.rampant.postevents;

import models.postevents.Event;
import play.Logger;

import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class Dispatcher {
	private Logger.ALogger LOGGER = Logger.of(Dispatcher.class);
	private final ConcurrentHashMap<String, Set<PostEventListener>> handlers = new
		ConcurrentHashMap<>();

	// Map of event(string) -> List of handlers (method, object)


	/**
	 * Singleton manager for non-Dependency Injected environments.
	 */
	private static class DispatcherHolder {
		public static final Dispatcher INSTANCE = new Dispatcher();
	}

	/**
	 * Retrieve the singleton, for non-DI environments.
	 *
	 * @return Singleton {@link Dispatcher} instance
	 */
	public static Dispatcher getInstance() {
		return DispatcherHolder.INSTANCE;
	}

	public void register(String event, PostEventListener handler) {
		Set<PostEventListener> eventListeners = handlers.putIfAbsent(event,
			new HashSet<PostEventListener>());
		synchronized( eventListeners ) {
			eventListeners.add(handler);
		}
	}

	public void unregister(String event, PostEventListener handler) {
		if( handlers.containsKey(event) ) {
			Set<PostEventListener> eventListeners = handlers.get(event);
			synchronized( eventListeners ) {
				eventListeners.remove(handler);
			}
		}
	}

	public void dispatchEvent(Event event) {
		if( handlers.containsKey(event.event) ) {
			for( PostEventListener listener : handlers.get(event.event) ) {
				listener.onEvent(event);
			}
		}
	}
}
