package com.sal.elecex.Utils;

import org.springframework.stereotype.Service;

@Service
public class UtilsService {

    public String resultMethods(boolean bol) {
        return bol ? "OK" : "KO";
    }

    public String returnNullIfIts(String input) {
        if ("null".equalsIgnoreCase(input)) {
            return null;
        }
        return input;
    }

}
