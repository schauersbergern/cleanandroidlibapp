package com.schauersberger.libapp.exception;

import android.content.Context;

import com.schauersberger.data.exception.NetworkConnectionException;
import com.schauersberger.data.exception.NewsNotFoundException;
import com.schauersberger.libapp.R;

/**
 * Factory used to create error messages from an Exception as a condition.
 */
public class ErrorMessageFactory {

    private ErrorMessageFactory() {
        //empty
    }

    /**
     * Creates a String representing an error message.
     *
     * @param context Context needed to retrieve string resources.
     * @param exception An exception used as a condition to retrieve the correct error message.
     * @return {@link String} an error message.
     */
    public static String create(Context context, Exception exception) {
        String message = context.getString(R.string.exception_message_generic);

        if (exception instanceof NetworkConnectionException) {
            message = context.getString(R.string.exception_message_no_connection);
        } else if (exception instanceof NewsNotFoundException) {
            message = context.getString(R.string.exception_message_news_not_found);
        }

        return message;
    }
}