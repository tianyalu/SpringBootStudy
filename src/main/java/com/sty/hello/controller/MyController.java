package com.sty.hello.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sty.hello.bean.Article;
import com.sty.hello.bean.CarBrand;
import com.sty.hello.utils.JsonUtil;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyController {
    private List<CarBrand> carBrands = null;

    @ResponseBody
    @RequestMapping("/test")
    public String hello() {
        return "hello Spring Boot! sty";
    }

    @ResponseBody
    @GetMapping("/carBrand.do")
    public List<CarBrand> getBrandsService(
            @RequestParam(value = "since") int since,
            @RequestParam(value = "page_size") int pageSize
    ) {
        System.out.println("CarBrandServlet since:" + since + " pageSize:" + pageSize);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        getCarBrands();
        //从carBrands集合中取出一段数据出来
        List<CarBrand> searchList = new ArrayList<>();
        //第一次请求，since等于0，重新给since赋值为第一个元素的id
        if(since == 0) {
            CarBrand carBrand = carBrands.get(0);
            searchList.add(carBrand);
            since = carBrand.getId();
        }
        for (int i = 0; i < carBrands.size(); i++) {
            try {
                //通过请求参数since,找到id等于since的元素
                //往后找到pageSize个元素
                CarBrand carBrand = carBrands.get(i);
                if (carBrand.getId() == since) {
                    for (int j = i + 1; j < i + pageSize; j++) {
                        searchList.add(carBrands.get(j));
                    }
                }
            }catch (IndexOutOfBoundsException e) {
                //索引越界，跳出循环
                System.out.println(e.getMessage());
                break;
            }
        }

        return searchList;
    }

    private List<CarBrand> getCarBrands() {
        if(carBrands == null) {
            synchronized (this) {
                if(carBrands == null) {
                    carBrands = new ArrayList<>();
                    //String jsonFile = MyController.class.getClassLoader().getResource("search_subjects.json").getPath();
                    String jsonFile = MyController.class.getClassLoader().getResource("car_brand.json").getPath();
                    String s = JsonUtil.readJson(jsonFile);
                    carBrands = JSON.parseArray(s, CarBrand.class);
                }
            }
        }
        return carBrands;
    }
}
