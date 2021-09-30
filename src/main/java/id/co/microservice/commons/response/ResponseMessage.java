package id.co.microservice.commons.response;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import id.co.microservice.commons.constant.CommonConstants;
import id.co.microservice.commons.dto.json.JsonTimestampDeserializer;
import id.co.microservice.commons.dto.json.JsonTimestampSerializer;
import id.co.microservice.commons.dto.json.NameValuePair;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openpojo.business.BusinessIdentity;
import com.openpojo.business.annotation.BusinessKey;

public class ResponseMessage<T> implements Serializable {
	
	private static final long serialVersionUID = 4748491956686749377L;
	
	public static final String ATTR_ERROR_MESSAGE = "errorMessage";
	public static final String ATTR_ERROR_STACK_TRACE = "errorStackTrace";
	
	private String requestId;
	private Integer responseCode;
	
	@BusinessKey
	private String statusCode;
	
	private String responseDescription;
	
	@JsonSerialize(using= JsonTimestampSerializer.class)
	@JsonDeserialize(using= JsonTimestampDeserializer.class)
	@JsonFormat(pattern = CommonConstants.DEFAUL_DATE_PATTERN)
	private Date requestTime;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@JsonDeserialize(using = JsonTimestampDeserializer.class)
	private Date responseTime;
	
	private T replyMessage;
	
	private List<NameValuePair> additionalAttributeList;
	
	public ResponseMessage() {
		super();
	}
	
	public ResponseMessage(final String requestId) {
		super();
		this.setRequestId(requestId);
		this.setRequestTime(new Timestamp(System.currentTimeMillis()));
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	public T getReplyMessage() {
		return replyMessage;
	}

	public void setReplyMessage(T replyMessage) {
		this.replyMessage = replyMessage;
	}

	public List<NameValuePair> getAdditionalAttributeList() {
		return additionalAttributeList;
	}

	public void setAdditionalAttributeList(List<NameValuePair> additionalAttributeList) {
		this.additionalAttributeList = additionalAttributeList;
	}
	
	/**
	 * Build response message
	 * 
	 * @param replyMessage
	 * 				Object response
	 * @param status
	 * 				HTTP status code
	 * @param statusCode
	 * 				Response status code
	 * @param responseDesc
	 * 				Response Description
	 */
	public void buildResponseMessage(final T replyMessage, final int status, final String statusCode,
			final String responseDesc) {
		this.setReplyMessage(replyMessage);
		this.setResponseCode(status);
		this.setStatusCode(statusCode);
		this.setResponseDescription(responseDesc);
		this.setResponseTime(new Timestamp(System.currentTimeMillis()));
	}
	
	/**
	 * Build response message
	 * 
	 * @param replyMessage
	 * 				Object response
	 * @param status
	 * 				HTTP status code
	 * @param statusCode
	 * 				Response status code
	 * @param responseDesc
	 * 				Response Description
	 * @param nameValuePairList
	 * 				Additional attributes with name value pair
	 */
	public void buildResponseMessage(final T replyMessage, final int status, final String statusCode,
			final String responseDesc, final List<NameValuePair> nameValuePairList) {
		this.setReplyMessage(replyMessage);
		this.setResponseCode(status);
		this.setStatusCode(statusCode);
		this.setResponseDescription(responseDesc);
		this.setResponseTime(new Timestamp(System.currentTimeMillis()));
		this.setAdditionalAttributeList(nameValuePairList);
	}
	
	/**
	 * Build response message without object reply message
	 * 
	 * @param status
	 * 				HTTP status code
	 * @param statusCode
	 * 				Response status code
	 * @param responseDesc
	 * 				Response Description
	 */
	public void buildResponseMessage(final int status, final String statusCode,
			final String responseDesc) {
		this.setResponseCode(status);
		this.setStatusCode(statusCode);
		this.setResponseDescription(responseDesc);
		this.setResponseTime(new Timestamp(System.currentTimeMillis()));
	}
	
	/**
	 * Build response message
	 * 
	 * @param status
	 * 				HTTP status code
	 * @param statusCode
	 * 				Response status code
	 * @param responseDesc
	 * 				Response Description
	 * @param nameValuePairList
	 * 				Additional attributes with name value pair
	 */
	public void buildResponseMessage(final int status, final String statusCode,
			final String responseDesc, final List<NameValuePair> nameValuePairList) {
		this.setResponseCode(status);
		this.setStatusCode(statusCode);
		this.setResponseDescription(responseDesc);
		this.setResponseTime(new Timestamp(System.currentTimeMillis()));
		this.setAdditionalAttributeList(nameValuePairList);
	}
	
	/**
	 * Build Error response message
	 * 
	 * @param responseCode
	 * 				response code based on HTTP status code
	 * @param statusCode
	 * 				local status code
	 * @param responseDescription
	 * 				Response Description
	 * @param errorMessage
	 * 				error message
	 * @param stackTrace
	 * 				error stack trace
	 */
	public void buildErrorResponseMessage(final int responseCode, final String statusCode,
			final String responseDescription, final String errorMessage, final String stackTrace) {
		this.setResponseCode(responseCode);
		this.setStatusCode(statusCode);
		this.setResponseDescription(responseDescription);
		this.setAdditionalAttributeList(
				Arrays.asList(new NameValuePair(ResponseMessage.ATTR_ERROR_MESSAGE, errorMessage),
						new NameValuePair(ResponseMessage.ATTR_ERROR_STACK_TRACE, stackTrace)));
		this.setResponseTime(new Timestamp(System.currentTimeMillis()));
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
