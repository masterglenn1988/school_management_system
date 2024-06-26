package edu.ph.myschoolportal.model;

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
public class ApiResponse {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    private String timestamp;
    private List<?> message;
    private String path;
    private String error;
    private int code;

    public static ApiResponse apiSuccessResponse(List<?> message, String path, int code){
        return ApiResponse.builder()
                .timestamp(new SimpleDateFormat(DATE_FORMAT).format(new Date()))
                .message(message)
                .path(path)
                .error(null)
                .code(code)
                .build();
    }

    public static ApiResponse apiFailedResponse(String error, String path, int code){
        return ApiResponse.builder()
                .timestamp(new SimpleDateFormat(DATE_FORMAT).format(new Date()))
                .message(null)
                .path(path)
                .error(error)
                .code(code)
                .build();
    }

}
