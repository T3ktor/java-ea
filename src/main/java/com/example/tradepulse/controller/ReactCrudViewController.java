package com.example.tradepulse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReactCrudViewController {

    @GetMapping("/react-crud")
    public String showReactCrudPage() {
        return "react_crud";
    }
}
