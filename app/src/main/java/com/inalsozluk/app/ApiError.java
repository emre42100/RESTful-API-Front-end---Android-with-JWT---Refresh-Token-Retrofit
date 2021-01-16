package com.inalsozluk.app;

import java.util.List;
import java.util.Map;

/**
 * Created by Emre on 18/07/2018.
 */

public class ApiError {

    String mesage;
    Map<String, List<String>> errors;

    public String getMesage() {
        return mesage;
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }
}
