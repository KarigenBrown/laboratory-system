package edu.bistu.utils;

import edu.bistu.domain.entity.WebManager;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    private SecurityUtils() {
    }

    public static Integer getUserId() {
        return ((WebManager) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getId();
    }
}
