package mockitotest.constants;
public final class CommonMessages {

    /**
     * Return when a parameter in the body is required but not provided.
     */
    public static final String PARAM_REQUIRED = "PARAM_REQUIRED";

    /**
     * Return when a parameter in the body has length exceeding its maximum
     * storage allowed or is less than the minimum required.
     */
    public static final String PARAM_INVALID_LENGTH = "PARAM_INVALID_LENGTH";
    /**
     * Return when a parameter in the body has length less than the minimum required.
     */
    public static final String PARAM_INVALID_MINIMUM_LENGTH = "PARAM_INVALID_MINIMUM_LENGTH";

    /**
     * The date, time or date time value does not conform to ISO 8601 format
     * then we will have this specific error message.
     */
    public static final String DATE_FORMAT = "DATE_FORMAT";

    /**
     * The content of the parameter is invalid.
     */
    public static final String PARAM_INVALID = "PARAM_INVALID";

    /**
     * This parameter is not allowed to be repeated.
     */
    public static final String PARAM_NO_REPEAT = "PARAM_NO_REPEAT";

    /**
     * When a resource with that name or id already exists then this error can be thrown.
     */
    public static final String PARAM_DUPLICATE = "PARAM_DUPLICATE";

    /**
     * The resource specified in the parameter does not exist.
     */
    public static final String PARAM_NOT_EXISTS = "PARAM_NOT_EXISTS";

    /**
     * This is reserved for enumeration. This is returned when value
     * specified for the field is not present in the enumeration.
     */
    public static final String PARAM_INVALID_VALUE = "PARAM_INVALID_VALUE";

    /**
     * Resource Type Mismatch
     */
    public static final String RESOURCE_TYPE_MISMATCH = "RESOURCE_TYPE_MISMATCH";

    /**
     * Don't use unless you have specific use case.
     */
    public static final String CANT_PARSE_CONTENT = "CANT_PARSE_CONTENT";

    /**
     * Very rarely a parameter could be a multi-value field and has a specific type
     * such as integer, decimal, boolean, date, time, datetime, quantity.
     * In such a case if a invalid modifier was specified then this error case should be returned.
     */
    public static final String PARAM_MODIFIER_INVALID = "PARAM_MODIFIER_INVALID";

    /**
     * If a request has a parameter that is not expected in the request.
     * E.g. phoneNumber parameter is provided when the request expects mobilePhoneNumber.
     */
    public static final String PARAM_UNKNOWN = "PARAM_UNKNOWN";

    /**
     * Unknown content
     */
    public static final String UNKNOWN_CONTENT = "UNKNOWN_CONTENT";

    /**
     * Value : Json Source for a resource should start with an object
     */
    public static final String JSON_OBJECT = "JSON_OBJECT";

    /**
     * When the input to the request cannot be parsed in to DTO then we should return ERROR_PARSING.
     * This could suggest a number of things wrong with the JSON input. A comma could be missing or there
     * may not be a closing braces. Generally JSON rules are as follows
     *
     * JSON syntax is derived from JavaScript object notation syntax:
     * <ul>
     *     <li>Data is in name/value pairs</li>
     *     <li>Data is separated by commas</li>
     *     <li>Curly braces hold objects</li>
     *     <li>Square brackets hold arrays</li>
     * </ul>
     *
     * If this syntax is not follows we should return ERROR_PARSING.
     */
    public static final String ERROR_PARSING = "ERROR_PARSING";

    /**
     * Generic message detail for error parsing messages.
     */
    public static final String ERROR_REQUEST_BODY = "ERROR_REQUEST_BODY";

    /**
     * Duplicate Id {} for resource type {}
     */
    public static final String DUPLICATE_ID = "DUPLICATE_ID";

    /**
     * Error message for HTTP_RESPONSE 401 - Unauthorized
     */
    public static final String AUTH_REQUIRED = "AUTH_REQUIRED";

    /**
     * Error message for HTTP_RESPONSE 403 - Access denied
     */
    public static final String PERMISSION_DENIED = "PERMISSION_DENIED";

    /**
     * Error message for HTTP_RESPONSE 403 - Consent denied
     * This message is thrown when consent check fails.
     */
    public static final String CONSENT_DENIED = "CONSENT_DENIED";

    /**
     * The resource specified in the URL does not exist. Only applies to the resource specified in the URL.
     * If a resource Id is in the body of the request then use PARAM_NOT_EXISTS
     */
    public static final String NOT_EXIST = "NOT_EXIST";

    /**
     * Error message for HTTP_RESPONSE 405 - Method not allowed.
     * To be used only when a user makes a wrong method request on the URL.
     * For more generic not allowed error see NOT_ALLOWED.
     */
    public static final String OP_NOT_ALLOWED = "OP_NOT_ALLOWED";

    /**
     * The format of this is: &lt;something&gt; not allowed for &lt;resource&gt; because &lt;reason&gt;.
     * Example: Delete not allowed for permission f3f29022-abb2-407d-b0c5-37a48edb6705 because a role is associated with it.
     */
    public static final String NOT_ALLOWED = "NOT_ALLOWED";

    /**
     * Number validation. Format: {0} must be less than or equal to {1}.
     */
    public static final String NUMBER_MAX = "MUST_BE_LESS_THAN_OR_EQUAL";
    /**
     * Format: Invitation patient has been sent, please check inbox
     */
    public static final String INVITATION_PATIENT_HAS_SENT = "INVITATION_PATIENT_HAS_SENT";
    /**
     * Format: Invitation does not exist or has expired
     */
    public static final String INVITATION_NOT_EXISTS_OR_EXPIRED = "INVITATION_NOT_EXISTS_OR_EXPIRED";
    /**
     * Format: {0} required for resource type {1}
     */
    public static final String FIELD_VALUE_REQUIRED = "FIELD_VALUE_REQUIRED";
    /**
     * Format: time slot is occupied
     */
    public static final String TIME_SLOT_OCCUPIED = "TIME_SLOT_OCCUPIED";
    /**
     * Format: {0} not found
     */
    public static final String NOT_FOUND = "NOT_FOUND";
    /**
     * Format: Number of Period exceeds Max
     */
    public static final String NUMBER_OR_PERIOD_EXCEED_MAX = "NUMBER_OR_PERIOD_EXCEED_MAX";
    /**
     * Format: Period type does not match
     */
    public static final String PERIOD_TYPE_NOT_MATCH = "PERIOD_TYPE_NOT_MATCH";
    /**
     * Format: Doctor start time or end time missing
     */
    public static final String CLINIC_START_TIME_OR_END_TIME_MISSING = "CLINIC_START_TIME_OR_END_TIME_MISSING";
    /**
     * Format: Doctor start time or end time missing
     */
    public static final String DOCTOR_START_TIME_OR_END_TIME_MISSING = "DOCTOR_START_TIME_OR_END_TIME_MISSING";
    /**
     * Format: Start time of doctor schedule should not be after end time
     */
    public static final String START_TIME_AFTER_END_TIME = "START_TIME_AFTER_END_TIME";
    /**
     * Format: Doctor schedule should be within Clinic schedule
     */
    public static final String TIME_NOT_IN_BETWEEN = "TIME_NOT_IN_BETWEEN";
    /**
     * Format: Appointment Status is {0}, Cannot change Status to {1}
     */
    public static final String CANNOT_CHANGE_APPOINTMENT_STATUS = "CANNOT_CHANGE_APPOINTMENT_STATUS";
    /**
     * Format: Error while loading {0}
     */
    public static final String INTEGRATION_ERROR = "INTEGRATION_ERROR";
    /**
     * Error message for HTTP_RESPONSE 415 - Unsupported Media Type.
     * The content-type of the input is something that is not supported.
     * E.g. application/xml instead of application/json
     */
    public static final String UNSUPPORTED_MEDIA_TYPE = "UNSUPPORTED_MEDIA_TYPE";

    /**
     * Error message for HTTP_RESPONSE 406 - Cannot generate accepted format
     */
    public static final String NOT_ACCEPTABLE = "NOT_ACCEPTABLE";

    /**
     * Error message for HTTP_RESPONSE 500 - Internal server error.
     * When there is an exception that we do not handle. You can simply say Internal Server Error or
     * you can say Internal Server Error and add a message explaining what happened.
     *
     * E.g.
     * Internal Server Error. Connection to Forgerock timed out.
     */
    public static final String INTERNAL_ERROR = "INTERNAL_ERROR";

    /**
     * Error message for HTTP_RESPONSE 501 - Not implemented
     */
    public static final String NOT_IMPLEMENTED = "NOT_IMPLEMENTED";

    /**
     * Error message for empty query result
     */
    public static final String NO_MATCH = "NO_MATCH";

    /**
     * When a input is expected in a certain form but does not conform to that form then
     * you can use BAD_FORMAT. E.g. The time “-07:00:00” is bad format because it cannot
     * have a negative sign.
     *
     * If a integer JSON field was expected and a string was specified instead.
     * In general can be used for wrong JSON type.
     */
    public static final String BAD_FORMAT = "BAD_FORMAT";

    /**
     * The ID is invalid in the URL. This is reserved for the URL only. When the ID is
     * in the body of the request use PARAM_INVALID.
     */
    public static final String INVALID_ID = "INVALID_ID";

    /**
     * Specifically reserved for ID fields for all other fields use PARAM_INVALID_LENGTH if the
     * field length exceeds maximum storage limits specified.
     */
    public static final String ID_TOO_LONG = "ID_TOO_LONG";

    /**
     * Unknown sort parameter specified in the URL while retrieving a list.
     */
    public static final String SORT_UNKNOWN = "SORT_UNKNOWN";

    /**
     * Mutually exclusive fields error.
     */
    public static final String FIELDS_MUTUALLY_EXCLUSIVE = "FIELDS_MUTUALLY_EXCLUSIVE";

    /**
     * Error message for version aware resources.
     */
    public static final String VERSION_AWARE = "VERSION_AWARE";

    /**
     * Error message for version mismatches.
     */
    public static final String VERSION_AWARE_CONFLICT = "VERSION_AWARE_CONFLICT";

    /**
     * Generic error message for gRPC client errors.
     */
    public static final String ERROR_INTEGRATION_GRPC_CLIENT = "ERROR_INTEGRATION_GRPC_CLIENT";

    /**
     * The header value in the REST API call is required.
     */
    public static final String HEADER_REQUIRED = "HEADER_REQUIRED";

    /**
     * The header value in the REST API call is invalid.
     */
    public static final String HEADER_INVALID_VALUE = "HEADER_INVALID_VALUE";

    /**
     * Multiple matches exist for search parameters
     */
    public static final String SEARCH_MULTIPLE = "SEARCH_MULTIPLE";

    /**
     * A resource specified on input is of a type that is not understood.
     */
    public static final String UNKNOWN_TYPE = "UNKNOWN_TYPE";

    /**
     * Session quota exhausted.
     */
    public static final String SESSION_QUOTA_EXHAUSTED = "SESSION_QUOTA_EXHAUSTED";

    /**
     * The  provided access grant is invalid, expired, or revoked.
     */
    public static final String AS_INVALID_GRANT = "AS_INVALID_GRANT";

    /**
     * The Service is temporarily unavailable.
     */
    public static final String SERVICE_UNAVAILABLE = "SERVICE_UNAVAILABLE";

    /**
     * The bad request is sent
     */
    public static final String BAD_REQUEST = "BAD_REQUEST";

    /**
     *
     */
    public static final String BAD_GATEWAY = "BAD_GATEWAY";

    /**
     * A request conflict with the current state of the target resource
     */
    public static final String CONFLICT = "CONFLICT";

    /**
     * Gateway Timeout
     */
    public static final String GATEWAY_TIMEOUT = "GATEWAY_TIMEOUT";

    /**
     * Access to the target resource is no longer available at the origin server
     */
    public static final String GONE = "GONE";

    /**
     * I'm a teapot
     */
    public static final String I_AM_A_TEAPOT = "I_AM_A_TEAPOT";

    /**
     * Unprocessable Content
     */
    public static final String UNPROCESSABLE_ENTITY = "UNPROCESSABLE_ENTITY";

    /**
     * The Resource is invalid.
     */
    public static final String RESOURCE_PERMISSION = "RESOURCE_PERMISSION";

    private CommonMessages() {
        // Private constructor
    }

}

