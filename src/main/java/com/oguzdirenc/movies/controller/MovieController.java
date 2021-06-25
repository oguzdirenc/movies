package com.oguzdirenc.movies.controller;

import com.oguzdirenc.movies.enums.Type;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/movie")
public class MovieController {

    @RequestMapping("/addMovie")
    public String addMovie(){
        System.out.println(Type.get());
        System.out.println("aaaaaa");
        return "k";
    }
}
