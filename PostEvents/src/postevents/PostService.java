package postevents;

import org.json.JSONException;
import postevents.common.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

class PostService implements Runnable {
	private Dispatcher.TYPE event;
	private Map<String, String> data;

	PostService(Dispatcher.TYPE event, Map<String, String> data) {
		this.event = event;
		this.data = data;
	}

	@Override
	public void run() {
		HttpPost request = new HttpPost(POSTEvents.Config.host);
		JSONObject payload = new JSONObject();

		try {
			payload.put("event", event.name());
			payload.put("data", data);

			request.setEntity(new StringEntity(
				payload.toString(), ContentType.APPLICATION_JSON));

			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(request);
			if( !requestIsSuccessful(response) ) {
				Log.warn("{} event failed to POST: {} {}",
					event.name(), response.getStatusLine().getStatusCode(),
					response.getStatusLine().getReasonPhrase());
			}
		}
		catch( IOException e ) {
			Log.severe("IOException sending {} event: {}",
				event.name(), e.getMessage());
		}
		catch( JSONException e ) {
			Log.severe("JSONException while encoding payload: {}", e.getMessage());
		}
	}

	/**
	 * Returns true if the response was considered successful.
	 * <p/>
	 * Honestly, we really only care about a 2** response code. If we get one,
	 * we assume everything went peachy, and call it good. Hell, even if it
	 * failed, there's not a lot we can do to fix it, but oh well.
	 *
	 * @param response An {@link HttpResponse} object.
	 * @return True if the response returned a 2xx status code.
	 */
	private boolean requestIsSuccessful(HttpResponse response) {
		int status = response.getStatusLine().getStatusCode();
		return status >= 200 && status < 300;
	}
}
