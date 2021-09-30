package id.co.microservice.commons.dto.json;

import java.io.Serializable;

import com.openpojo.business.BusinessIdentity;
import com.openpojo.business.annotation.BusinessKey;

public class NameValuePair implements Serializable {
	
	private static final long serialVersionUID = 8862657236569447600L;
	
	@BusinessKey
	private String name;
	
	private Object value;
	
	public NameValuePair() {
		super();
	}
	
	public NameValuePair(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	@Override
	public int hashCode() {
		return BusinessIdentity.getHashCode(this);
	}
	
	@Override
	public boolean equals(final Object obj) {
		return BusinessIdentity.areEqual(this, obj);
	}
	
	@Override
	public String toString() {
		return BusinessIdentity.toString(this);
	}

}
