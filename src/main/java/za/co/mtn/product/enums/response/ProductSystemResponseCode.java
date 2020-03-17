package za.co.mtn.product.enums.response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.Getter;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Getter
public enum ProductSystemResponseCode implements IResponseCode {

    HTTP_CONNECTION_ERROR(-1, "There was an error when making the HTTP connection.",
            ProtocolException.class, MalformedURLException.class, IOException.class, ServiceException.class,
            RemoteException.class, InterruptedException.class, ExecutionException.class, TimeoutException.class),
    JSON_MAPPING_ERROR(-2, "There was an error when marshalling an object.",
            JsonParseException.class, JsonMappingException.class, NoSuchAlgorithmException.class, IOException.class),
    SUCCESS(0, "Success."),
    NORMAL(1, "Success."),
    PAUSED(2, "Paused."),
    CANCELLED(3, "Cancelled."),
    EXPIRED(4, "Expired."),
    GRACE(5, "Grace period."),
    CHARGE_FREE(6, "Charge free period."),
    OK(200, "Success."),
    BAD_REQUEST(400, "The input parameters provided are invalid.",
            UnsupportedEncodingException.class),
    UNAUTHORIZED(401, "The supplied authentication is invalid."),
    NOT_FOUND(404, "The endpoint cannot be found."),
    METHOD_NOT_ALLOWED(405, "The endpoint cannot handle the supplied HTTP request method."),
    INTERNAL_SERVER_ERROR(500, "There was an error while processing the request."),
    SYSTEM_ERROR(3803, "There was an error while processing the request."),
    INTERNAL_ERROR(3804, "There was an error while processing the request."),
    ENDPOINT_NOT_FOUND(3805, "The endpoint cannot be found."),
    INVALID_PARAMETER(3806, "The input parameters provided are invalid."),
    REQUEST_TOO_LARGE(3810, "The payload supplied for the request is too large."),
    REQUEST_TIMEOUT(3811, "The request has timed out."),
    USER_NOT_EXIST(3812, "The customer ID supplied cannot be found."),
    LOGIN_FAILED(3818, "The supplied authentication is invalid."),
    INVALID_COOKIE(3818, "The cookie provided is invalid."),
    GENERAL_ERROR(10701, "There was an error while processing the request."),
    ANOTHER_SYSTEM_ERROR(10702, "There was an error while processing the request."),
    ANOTHER_GENERAL_ERROR(10703, "There was an error while processing the request."),
    INVALID_PARAMETERS(10704, "The input parameters provided are invalid."),
    METHOD_NOT_SUPPORTED(10705, "The endpoint cannot handle the supplied HTTP request method."),
    MSISDN_NOT_FOUND(10706, "The customer ID supplied cannot be found."),
    TIMED_OUT(10707, "The request has timed out."),
    REQUEST_BAD(10708, "The input parameters provided are invalid."),
    ANOTHER_REQUEST_BAD(10709, "The input parameters provided are invalid."),
    ACCOUNT_DISABLED(10710, "The account of the customer ID supplied is disabled."),
    ROUTE_NOT_FOUND(10711, "The QTP route cannot be found."),
    READ_FAILED(10720, "There was an error while reading the request."),
    PARTIAL_SUCCESS(10729, "Partial success."),
    CATALOG_NOT_FOUND(10730, "The catalog could not be found."),
    REGISTRATION_FAILED(10731, "The customer registration failed."),
    QUERY_FAILED(10732, "There was an error while processing the request."),
    QUERY_DUPLICATE(10736, "The query information supplied is a duplicate."),
    FURTHER_SYSTEM_ERROR(10738, "There was an error while processing the request."),
    INVALID_TOKEN(10751, "The supplied authentication is invalid."),
    QUERY_NOT_ALLOWED(10752, "The query information supplied is a not allowed."),
    RATE_EXCEEDED(10753, "The rate has exceeded."),
    INTERFACE_NOT_ALLOWED(10754, "The interface supplied is a not allowed."),
    IMSI_MSISDN_FAILED(10755, "The conversion from IMSi to MSISDN failed."),
    QTP_FAILED(10756, "The QTP request has failed."),
    NO_ROUTE(10757, "The QTP route cannot be found."),
    SYSTEM_ERROR_AGAIN(13801, "There was an error while processing the request."),
    NOT_RUNNING(13802, "The process is no longer running."),
    BAD_ARGS(13804, "The input parameters provided are invalid."),
    SP_NOT_SUPPORTED(13805, "The SP provided is not supported."),
    SP_NOT_FOUND(13806, "The SP provided could not be found."),
    REQUEST_TIMED_OUT(13807, "The request has timed out."),
    STORED_PROC_INIT(13809, "There was an error while processing the request to the database."),
    SP_LOOKUP(13810, "The SP provided could not be looked up."),
    DECODE_PARAMS(13811, "The input parameters could not be decoded."),
    EXECUTE_REQUEST(13812, "The request could not be executed."),
    ENCODE_RESPONSE(13813, "The response could not be encoded."),
    CALLBACK_ERROR(13814, "There was an error while processing the callback."),
    CALLBACK_FAILED(13815, "There was an error while processing the callback."),
    SP_EXISTS(13816, "The SP provided already exists."),
    QAP_NOT_FOUND(13816, "The QAP route cannot be found."),
    INVALID_CONFIG(13821, "The config supplied is invalid."),
    EBB_NOT_FOUND(13826, "The EBB route cannot be found."),
    ANOTHER_INVALID_TOKEN(13831, "The supplied authentication is invalid."),
    USER_NOT_SUBSCRIBED(13841, "The customer ID supplied cannot be found."),
    TOKEN_PENDING(13842, "The token supplied is pending."),
    TOKEN_SUSPENDED(13843, "The token supplied is suspended."),
    TOKEN_EXPIRED(13846, "The token supplied is expired."),
    TOKEN_DECLINED(13848, "The token supplied is declined."),
    TOKEN_NOT_APPROVED(13849, "The token supplied is not approved."),
    CACHE_ERROR(13852, "There was an error while processing the cache."),
    CONNECTION_ERROR(13853, "There was an error when making the HTTP connection."),
    CACHE_UPDATE_ERROR(13857, "There was an error while updating the cache."),
    USER_DISCONNECTED(13870, "The customer ID supplied has been disconnected."),
    TOKEN_LOCKED(13871, "The token supplied is locked."),
    INSUFFICIENT_FUNDS(13872, "The customer ID supplied has insufficient funds.");

    int responseCode;
    Class<? extends Exception>[] exceptions;
    String message;

    @SafeVarargs
    ProductSystemResponseCode(final int responseCode, final String message, final Class<? extends Exception>... exceptions) {
        this.responseCode = responseCode;
        this.message = message;
        this.exceptions = exceptions;
    }

    public static Optional<ProductSystemResponseCode> resolveForException(Exception exception) {
        for (ProductSystemResponseCode value : ProductSystemResponseCode.values()) {
            for (Class valueException : value.getExceptions()) {
                if (valueException.equals(exception.getClass())) {
                    return Optional.of(value);
                }
            }
        }

        return Optional.empty();
    }

    public static Optional<ProductSystemResponseCode> resolveForCode(int code) {
        for (ProductSystemResponseCode value : ProductSystemResponseCode.values()) {
            if (code == value.getResponseCode()) {
                return Optional.of(value);
            }
        }

        return Optional.empty();
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getMessage() {
        return message;
    }

    public Class<? extends Exception>[] getExceptions() {
        return exceptions;
    }

}
