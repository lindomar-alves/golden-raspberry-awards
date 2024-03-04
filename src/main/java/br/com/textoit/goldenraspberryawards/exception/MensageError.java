package br.com.textoit.goldenraspberryawards.exception;

import java.util.List;

public record MensageError(
        Integer code,
        String message,
        List<String> errors
)
{

    public static MensageError to(Integer code, String message) {
        return new MensageError(code, message, null);
    }

}
