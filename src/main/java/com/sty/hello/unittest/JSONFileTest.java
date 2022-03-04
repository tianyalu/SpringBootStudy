package com.sty.hello.unittest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sty.hello.bean.CarBrand;
import com.sty.hello.controller.MyController;
import com.sty.hello.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JSONFileTest {
    public static void main(String[] args) {
        //writeFile();
        readFile();
    }

    private static void writeFile() {
        List<CarBrand> carBrands = new ArrayList<>();
        String jsonFile = Objects.requireNonNull(JSONFileTest.class.getClassLoader().getResource("search_subjects.json")).getPath();
        System.out.println("path: " + jsonFile);
        String s = JsonUtil.readJson(jsonFile);
        JSONObject jsonObject = JSON.parseObject(s);
        JSONArray movies = jsonObject.getJSONArray("subjects"); //构建JSONArray数组
        for (int i = 0; i < movies.size(); i++) {
            JSONObject key = (JSONObject) movies.get(i);
            int id = i + 1;
            String name = (String) key.get("title");
            String icon = (String) key.get("cover");
            carBrands.add(new CarBrand(id, name, icon));
        }
        Object o = JSONObject.toJSON(carBrands);
        JsonUtil.writeJson("E://", "car_brand.json", o);
    }

    private static void readFile() {
        String jsonFile = JSONFileTest.class.getClassLoader().getResource("car_brand.json").getPath();
        System.out.println("file: " + jsonFile);
        String s = JsonUtil.readJson(jsonFile);
        List<CarBrand> carBrands = JSON.parseArray(s, CarBrand.class);
        System.out.println("carBrands.size: " + carBrands.size());
        for (int i = 0; i < carBrands.size(); i++) {
            System.out.println(carBrands.get(i).toString());
        }
    }
}
