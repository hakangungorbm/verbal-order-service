package tr.com.medlineadana.verbalorder.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Message {
	String text;
	String severity;
	String uiType;
	
	public Message(String text, String severity, String uiType) {
		super();
		this.text = text;
		this.severity = severity;
		this.uiType = uiType;
	}
	
	public Message(String text) {
		super();
		this.text = text;
	}
	
}
