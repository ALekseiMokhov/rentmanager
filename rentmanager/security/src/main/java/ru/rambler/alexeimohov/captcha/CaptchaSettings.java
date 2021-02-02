package ru.rambler.alexeimohov.captcha;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CaptchaSettings {
    @Value("6LcPAT4aAAAAADGm1z8oDcOWGXo8SWBLUHzYWpaE")
    private String site;
    @Value("6LcPAT4aAAAAAE-Q9SzviJ3SfY9l4YjUbLhkYRAr")
    private String secret;

    // standard getters and setters
}