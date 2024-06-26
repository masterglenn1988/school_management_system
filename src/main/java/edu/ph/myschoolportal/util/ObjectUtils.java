package edu.ph.myschoolportal.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.Nullable;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Random;

@Slf4j
public final class ObjectUtils {

    private ObjectUtils() {
        throw new IllegalStateException("Object Utility class");
    }

    public static <T> T copyAs(Object sourceObject, Class<T> targetClass) {

        T instance = null;
        try {
            instance = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(sourceObject, instance);
        } catch (RuntimeException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.error("Error in copying object properties", e);
        }

        return instance;
    }

    public static Date getLocalDateTime() {
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        log.info("Current Local Date and Time: " + ldt);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isEmpty(@Nullable Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }
        return false;
    }

    public static String randomPasswordGenerator(int len) throws NoSuchAlgorithmException {
        String passwordStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = SecureRandom.getInstanceStrong();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(passwordStr.charAt(rand.nextInt(passwordStr.length())));
        }
        return sb.toString();
    }
}
