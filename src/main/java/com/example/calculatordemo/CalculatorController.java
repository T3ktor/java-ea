package com.example.calculatordemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalculatorController {

    private final Calculator calculator;

    public CalculatorController(Calculator calculator) {
        this.calculator = calculator;
    }

    @GetMapping("/calc")
    public String showForm(Model model) {
        model.addAttribute("calcForm", new CalculatorForm());
        return "calc";
    }

    @PostMapping("/calc")
    public String calculate(@ModelAttribute("calcForm") CalculatorForm calcForm, Model model) {
        try {
            int a = calcForm.getA();
            int b = calcForm.getB();
            String op = calcForm.getOp();

            if ("add".equalsIgnoreCase(op)) {
                model.addAttribute("result", String.valueOf(calculator.add(a, b)));
                model.addAttribute("symbol", "+");
            } else if ("sub".equalsIgnoreCase(op)) {
                model.addAttribute("result", String.valueOf(calculator.subtract(a, b)));
                model.addAttribute("symbol", "-");
            } else if ("mul".equalsIgnoreCase(op)) {
                model.addAttribute("result", String.valueOf(calculator.multiply(a, b)));
                model.addAttribute("symbol", "*");
            } else if ("divide".equalsIgnoreCase(op)) {
                double res = calculator.divide(a, b);
                model.addAttribute("result", String.format(java.util.Locale.US, "%.2f", res));
                model.addAttribute("symbol", "/");
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "calc";
    }
}
