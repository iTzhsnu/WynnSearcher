package com.github.iTzhsnu.WynnSearcher;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String s = "100-145";

        if (s.indexOf("-") != -1) {
            String[] ss = s.split("-");

            for (String value : ss) System.out.println(value);
        }

    }
}
