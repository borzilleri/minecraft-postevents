package controllers.postevents;

import io.rampant.postevents.Dispatcher;
import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class Event extends Controller {
	Dispatcher dispatcher;

	/**
	 * Used for non-Dependency injected situations.
	 */
	public Event() {

	}

	@Inject
	public Event(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result main() {
		Form<models.postevents.Event> form = Form.form(models.postevents.Event
			.class).bindFromRequest();

		if( form.hasErrors() ) {
			return badRequest(form.errorsAsJson());
		}

		try {
			dispatcher.dispatchEvent(form.get());
		}
		catch( Exception e ) {
			return internalServerError(e.getMessage());
		}
		return ok();
	}

}
