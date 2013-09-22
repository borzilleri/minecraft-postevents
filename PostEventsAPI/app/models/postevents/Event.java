package models.postevents;

import play.data.validation.Constraints;

import java.util.Map;

public class Event {
	@Constraints.Required
	public String event;
	public Map<String,String> data;


}
