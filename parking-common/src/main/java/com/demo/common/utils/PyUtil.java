package com.demo.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author Sheva
 * @Date 2021/3/21
 */
public class PyUtil {
    public static Map<String, String> recognizePlate(String path){
        String[] arguments = new String[]{"/usr/local/bin/python3",
                "/Users/sheva/IdeaProjects/ParkingSystem/parking-admin/src/main/resources/py/LPR.py",
                path};
        Map<String, String> result = new HashMap<>();
        try{
            Process proc = Runtime.getRuntime().exec(arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String plateNumber = in.readLine();
            String color = in.readLine();
            result.put("color", color);
            result.put("number", plateNumber);
            in.close();
            proc.waitFor();
            // System.out.println(res);
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        // String path = "/Users/sheva/PycharmProjects/LPR/Test/5.jpg";
        for (int i = 1; i < 21; i++){
            String path = "/Users/sheva/PycharmProjects/LPR/PlateDetect/test/";
            Map<String, String> res = recognizePlate(path + i + ".jpg");
            System.out.println(res.get("color") + "\n" + res.get("number"));
        }
        // Map<String, String> res = recognizePlate(path);
        // System.out.println(res.get("color") + "\n" + res.get("number"));
    }
}
