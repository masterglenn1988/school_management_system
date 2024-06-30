package edu.ph.myschoolportal.model.common;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@ToString
public class RestApiResponse {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    private String timestamp;
    private List<?> message;
    private String path;
    private String error;
    private int status;

    public static RestApiResponse apiSuccessResponse(List<?> message, String path, int status){
        return RestApiResponse.builder()
                .timestamp(new SimpleDateFormat(DATE_FORMAT).format(new Date()))
                .message(message)
                .path(path)
                .error(null)
                .status(status)
                .build();
    }

    public static RestApiResponse apiFailedResponse(String error, String path, int status){
        return RestApiResponse.builder()
                .timestamp(new SimpleDateFormat(DATE_FORMAT).format(new Date()))
                .message(null)
                .path(path)
                .error(error)
                .status(status)
                .build();
    }

}
