/**
 * 
 */
package com.csf.base.exception;

/**
 * @author mba0019
 *
 */
public enum HttpStatus {

	// 1xx Informational

	/**
	 * {@code 100 Continue}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.2.1
	 */
	CONTINUE(100, "Continue"),
	/**
	 * {@code 101 Switching Protocols}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.2.2
	 */
	SWITCHING_PROTOCOLS(101, "Switching Protocols"),
	/**
	 * {@code 102 Processing}.
	 * 
	 * @see WebDAV
	 */
	PROCESSING(102, "Processing"),
	/**
	 * {@code 103 Checkpoint}.
	 * 
	 * @see A proposal for supporting resumable POST/PUT HTTP requests in HTTP/1.0
	 */
	CHECKPOINT(103, "Checkpoint"),

	// 2xx Success

	/**
	 * {@code 200 OK}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.3.1
	 */
	OK(200, "OK"),
	/**
	 * {@code 201 Created}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.3.2
	 */
	CREATED(201, "Created"),
	/**
	 * {@code 202 Accepted}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.3.3
	 */
	ACCEPTED(202, "Accepted"),
	/**
	 * {@code 203 Non-Authoritative Information}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.3.4
	 */
	NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),
	/**
	 * {@code 204 No Content}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.3.5
	 */
	NO_CONTENT(204, "No Content"),
	/**
	 * {@code 205 Reset Content}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.3.6
	 */
	RESET_CONTENT(205, "Reset Content"),
	/**
	 * {@code 206 Partial Content}.
	 * 
	 * @see HTTP/1.1: Range Requests, section 4.1
	 */
	PARTIAL_CONTENT(206, "Partial Content"),
	/**
	 * {@code 207 Multi-Status}.
	 * 
	 * @see WebDAV
	 */
	MULTI_STATUS(207, "Multi-Status"),
	/**
	 * {@code 208 Already Reported}.
	 * 
	 * @see WebDAV Binding Extensions
	 */
	ALREADY_REPORTED(208, "Already Reported"),
	/**
	 * {@code 226 IM Used}.
	 * 
	 * @see Delta encoding in HTTP
	 */
	IM_USED(226, "IM Used"),

	// 3xx Redirection

	/**
	 * {@code 300 Multiple Choices}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.4.1
	 */
	MULTIPLE_CHOICES(300, "Multiple Choices"),
	/**
	 * {@code 301 Moved Permanently}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.4.2
	 */
	MOVED_PERMANENTLY(301, "Moved Permanently"),
	/**
	 * {@code 302 Found}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.4.3
	 */
	FOUND(302, "Found"),
	/**
	 * {@code 302 Moved Temporarily}.
	 * 
	 * @see HTTP/1.0, section 9.3
	 * @deprecated in favor of {@link #FOUND} which will be returned from
	 *             {@code HttpStatus.valueOf(302)}
	 */
	@Deprecated
	MOVED_TEMPORARILY(302, "Moved Temporarily"),
	/**
	 * {@code 303 See Other}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.4.4
	 */
	SEE_OTHER(303, "See Other"),
	/**
	 * {@code 304 Not Modified}.
	 * 
	 * @see HTTP/1.1: Conditional Requests, section 4.1
	 */
	NOT_MODIFIED(304, "Not Modified"),
	/**
	 * {@code 305 Use Proxy}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.4.5
	 * @deprecated due to security concerns regarding in-band configuration of a
	 *             proxy
	 */
	@Deprecated
	USE_PROXY(305, "Use Proxy"),
	/**
	 * {@code 307 Temporary Redirect}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.4.7
	 */
	TEMPORARY_REDIRECT(307, "Temporary Redirect"),
	/**
	 * {@code 308 Permanent Redirect}.
	 * 
	 * @see RFC 7238
	 */
	PERMANENT_REDIRECT(308, "Permanent Redirect"),

	// --- 4xx Client Error ---

	/**
	 * {@code 400 Bad Request}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.5.1
	 */
	BAD_REQUEST(400, "Bad Request"),
	/**
	 * {@code 401 Unauthorized}.
	 * 
	 * @see HTTP/1.1: Authentication, section 3.1
	 */
	UNAUTHORIZED(401, "Unauthorized"),
	/**
	 * {@code 402 Payment Required}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.5.2
	 */
	PAYMENT_REQUIRED(402, "Payment Required"),
	/**
	 * {@code 403 Forbidden}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.5.3
	 */
	FORBIDDEN(403, "Forbidden"),
	/**
	 * {@code 404 Not Found}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.5.4
	 */
	NOT_FOUND(404, "Not Found"),
	/**
	 * {@code 405 Method Not Allowed}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.5.5
	 */
	METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
	/**
	 * {@code 406 Not Acceptable}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.5.6
	 */
	NOT_ACCEPTABLE(406, "Not Acceptable"),
	/**
	 * {@code 407 Proxy Authentication Required}.
	 * 
	 * @see HTTP/1.1: Authentication, section 3.2
	 */
	PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
	/**
	 * {@code 408 Request Timeout}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.5.7
	 */
	REQUEST_TIMEOUT(408, "Request Timeout"),
	/**
	 * {@code 409 Conflict}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.5.8
	 */
	CONFLICT(409, "Conflict"),
	/**
	 * {@code 410 Gone}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.5.9
	 */
	GONE(410, "Gone"),
	/**
	 * {@code 411 Length Required}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.5.10
	 */
	LENGTH_REQUIRED(411, "Length Required"),
	/**
	 * {@code 412 Precondition failed}.
	 * 
	 * @see HTTP/1.1: Conditional Requests, section 4.2
	 */
	PRECONDITION_FAILED(412, "Precondition Failed"),
	/**
	 * {@code 413 Payload Too Large}.
	 * 
	 * @since 4.1
	 * @see HTTP/1.1: Semantics and Content, section 6.5.11
	 */
	PAYLOAD_TOO_LARGE(413, "Payload Too Large"),
	/**
	 * {@code 413 Request Entity Too Large}.
	 * 
	 * @see HTTP/1.1, section 10.4.14
	 * @deprecated in favor of {@link #PAYLOAD_TOO_LARGE} which will be returned
	 *             from {@code HttpStatus.valueOf(413)}
	 */
	@Deprecated
	REQUEST_ENTITY_TOO_LARGE(413, "Request Entity Too Large"),
	/**
	 * {@code 414 URI Too Long}.
	 * 
	 * @since 4.1
	 * @see HTTP/1.1: Semantics and Content, section 6.5.12
	 */
	URI_TOO_LONG(414, "URI Too Long"),
	/**
	 * {@code 414 Request-URI Too Long}.
	 * 
	 * @see HTTP/1.1, section 10.4.15
	 * @deprecated in favor of {@link #URI_TOO_LONG} which will be returned from
	 *             {@code HttpStatus.valueOf(414)}
	 */
	@Deprecated
	REQUEST_URI_TOO_LONG(414, "Request-URI Too Long"),
	/**
	 * {@code 415 Unsupported Media Type}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.5.13
	 */
	UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
	/**
	 * {@code 416 Requested Range Not Satisfiable}.
	 * 
	 * @see HTTP/1.1: Range Requests, section 4.4
	 */
	REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested range not satisfiable"),
	/**
	 * {@code 417 Expectation Failed}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.5.14
	 */
	EXPECTATION_FAILED(417, "Expectation Failed"),
	/**
	 * {@code 418 I'm a teapot}.
	 * 
	 * @see HTCPCP/1.0
	 */
	I_AM_A_TEAPOT(418, "I'm a teapot"),
	/**
	 * @deprecated See
	 * 
	 *             WebDAV Draft Changes
	 */
	@Deprecated
	INSUFFICIENT_SPACE_ON_RESOURCE(419, "Insufficient Space On Resource"),
	/**
	 * @deprecated See
	 * 
	 *             WebDAV Draft Changes
	 */
	@Deprecated
	METHOD_FAILURE(420, "Method Failure"),
	/**
	 * @deprecated See WebDAV Draft Changes
	 */
	@Deprecated
	DESTINATION_LOCKED(421, "Destination Locked"),
	/**
	 * {@code 422 Unprocessable Entity}.
	 * 
	 * @see WebDAV
	 */
	UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),
	/**
	 * {@code 423 Locked}.
	 * 
	 * @see WebDAV
	 */
	LOCKED(423, "Locked"),
	/**
	 * {@code 424 Failed Dependency}.
	 * 
	 * @see WebDAV
	 */
	FAILED_DEPENDENCY(424, "Failed Dependency"),
	/**
	 * {@code 426 Upgrade Required}.
	 * 
	 * @see Upgrading to TLS Within HTTP/1.1
	 */
	UPGRADE_REQUIRED(426, "Upgrade Required"),
	/**
	 * {@code 428 Precondition Required}.
	 * 
	 * @see Additional HTTP Status Codes
	 */
	PRECONDITION_REQUIRED(428, "Precondition Required"),
	/**
	 * {@code 429 Too Many Requests}.
	 * 
	 * @see Additional HTTP Status Codes
	 */
	TOO_MANY_REQUESTS(429, "Too Many Requests"),
	/**
	 * {@code 431 Request Header Fields Too Large}.
	 * 
	 * @see Additional HTTP Status Codes
	 */
	REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),
	/**
	 * {@code 451 Unavailable For Legal Reasons}.
	 * 
	 * @see An HTTP Status Code to Report Legal Obstacles
	 * @since 4.3
	 */
	UNAVAILABLE_FOR_LEGAL_REASONS(451, "Unavailable For Legal Reasons"),

	// --- 5xx Server Error ---

	/**
	 * {@code 500 Internal Server Error}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.6.1
	 */
	INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
	/**
	 * {@code 501 Not Implemented}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.6.2
	 */
	NOT_IMPLEMENTED(501, "Not Implemented"),
	/**
	 * {@code 502 Bad Gateway}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.6.3
	 */
	BAD_GATEWAY(502, "Bad Gateway"),
	/**
	 * {@code 503 Service Unavailable}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.6.4
	 */
	SERVICE_UNAVAILABLE(503, "Service Unavailable"),
	/**
	 * {@code 504 Gateway Timeout}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.6.5
	 */
	GATEWAY_TIMEOUT(504, "Gateway Timeout"),
	/**
	 * {@code 505 HTTP Version Not Supported}.
	 * 
	 * @see HTTP/1.1: Semantics and Content, section 6.6.6
	 */
	HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version not supported"),
	/**
	 * {@code 506 Variant Also Negotiates}
	 * 
	 * @see Transparent Content Negotiation
	 */
	VARIANT_ALSO_NEGOTIATES(506, "Variant Also Negotiates"),
	/**
	 * {@code 507 Insufficient Storage}
	 * 
	 * @see WebDAV
	 */
	INSUFFICIENT_STORAGE(507, "Insufficient Storage"),
	/**
	 * {@code 508 Loop Detected}
	 * 
	 * @see WebDAV Binding Extensions
	 */
	LOOP_DETECTED(508, "Loop Detected"),
	/**
	 * {@code 509 Bandwidth Limit Exceeded}
	 */
	BANDWIDTH_LIMIT_EXCEEDED(509, "Bandwidth Limit Exceeded"),
	/**
	 * {@code 510 Not Extended}
	 * 
	 * @see HTTP Extension Framework
	 */
	NOT_EXTENDED(510, "Not Extended"),
	/**
	 * {@code 511 Network Authentication Required}.
	 * 
	 * @see Additional HTTP Status Codes
	 */
	NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required");

	private final int value;

	private final String reasonPhrase;

	HttpStatus(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Return the integer value of this status code.
	 */
	public int value() {
		return this.value;
	}

	/**
	 * Return the reason phrase of this status code.
	 */
	public String getReasonPhrase() {
		return this.reasonPhrase;
	}

	/**
	 * Whether this status code is in the HTTP series
	 * {@link org.springframework.http.HttpStatus.Series#INFORMATIONAL}. This is a
	 * shortcut for checking the value of {@link #series()}.
	 */
	public boolean is1xxInformational() {
		return Series.INFORMATIONAL.equals(series());
	}

	/**
	 * Whether this status code is in the HTTP series
	 * {@link org.springframework.http.HttpStatus.Series#SUCCESSFUL}. This is a
	 * shortcut for checking the value of {@link #series()}.
	 */
	public boolean is2xxSuccessful() {
		return Series.SUCCESSFUL.equals(series());
	}

	/**
	 * Whether this status code is in the HTTP series
	 * {@link org.springframework.http.HttpStatus.Series#REDIRECTION}. This is a
	 * shortcut for checking the value of {@link #series()}.
	 */
	public boolean is3xxRedirection() {
		return Series.REDIRECTION.equals(series());
	}

	/**
	 * Whether this status code is in the HTTP series
	 * {@link org.springframework.http.HttpStatus.Series#CLIENT_ERROR}. This is a
	 * shortcut for checking the value of {@link #series()}.
	 */
	public boolean is4xxClientError() {
		return Series.CLIENT_ERROR.equals(series());
	}

	/**
	 * Whether this status code is in the HTTP series
	 * {@link org.springframework.http.HttpStatus.Series#SERVER_ERROR}. This is a
	 * shortcut for checking the value of {@link #series()}.
	 */
	public boolean is5xxServerError() {
		return Series.SERVER_ERROR.equals(series());
	}

	/**
	 * Whether this status code is in the HTTP series
	 * {@link org.springframework.http.HttpStatus.Series#CLIENT_ERROR} or
	 * {@link org.springframework.http.HttpStatus.Series#SERVER_ERROR}. This is a
	 * shortcut for checking the value of {@link #series()}.
	 */
	public boolean isError() {
		return is4xxClientError() || is5xxServerError();
	}

	/**
	 * Returns the HTTP status series of this status code.
	 * 
	 * @see HttpStatus.Series
	 */
	public Series series() {
		return Series.valueOf(this);
	}

	/**
	 * Return a string representation of this status code.
	 */
	@Override
	public String toString() {
		return Integer.toString(this.value) + " " + name();
	}

	/**
	 * Return the enum constant of this type with the specified numeric value.
	 * 
	 * @param statusCode the numeric value of the enum to be returned
	 * @return the enum constant with the specified numeric value
	 * @throws IllegalArgumentException if this enum has no constant for the
	 *                                  specified numeric value
	 */
	public static HttpStatus valueOf(int statusCode) {
		HttpStatus status = resolve(statusCode);
		if (status == null) {
			throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
		}
		return status;
	}

	/**
	 * Resolve the given status code to an {@code HttpStatus}, if possible.
	 * 
	 * @param statusCode the HTTP status code (potentially non-standard)
	 * @return the corresponding {@code HttpStatus}, or {@code null} if not found
	 * @since 5.0
	 */
	public static HttpStatus resolve(int statusCode) {
		for (HttpStatus status : values()) {
			if (status.value == statusCode) {
				return status;
			}
		}
		return null;
	}

	/**
	 * Enumeration of HTTP status series.
	 * 
	 * 
	 * Retrievable via {@link HttpStatus#series()}.
	 */
	public enum Series {

		INFORMATIONAL(1), SUCCESSFUL(2), REDIRECTION(3), CLIENT_ERROR(4), SERVER_ERROR(5);

		private final int value;

		Series(int value) {
			this.value = value;
		}

		/**
		 * Return the integer value of this status series. Ranges from 1 to 5.
		 */
		public int value() {
			return this.value;
		}

		public static Series valueOf(int status) {
			int seriesCode = status / 100;
			for (Series series : values()) {
				if (series.value == seriesCode) {
					return series;
				}
			}
			throw new IllegalArgumentException("No matching constant for [" + status + "]");
		}

		public static Series valueOf(HttpStatus status) {
			return valueOf(status.value);
		}
	}

}