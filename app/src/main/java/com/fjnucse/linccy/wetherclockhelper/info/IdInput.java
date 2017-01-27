package com.fjnucse.linccy.wetherclockhelper.info;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by lcx on 12/17/16.
 */

public class IdInput {
    public String id;

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("apikey", "7ee4db22ac82d07de07019c5c7bade8f");
        return map;
    }
}
