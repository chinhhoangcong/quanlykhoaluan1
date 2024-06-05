/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcc.controllers;

import com.hcc.pojo.Thesis;
import com.hcc.service.ThesisService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ACER
 */
@Controller
public class ThesisController {

    @Autowired
    private ThesisService sisService;

    @GetMapping("/thesises")
    public String createView(Model model) {
        model.addAttribute("thesis", new Thesis());
        return "addThesis";
    }

    @PostMapping("/thesises")
    public String createThesis(@ModelAttribute(value = "thesis") @Valid Thesis p,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            try {
                this.sisService.addOrUpdate(p);
                return "redirect:/";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        return "addThesis";
    }
    
        @GetMapping("/thesises/{thesisId}")
    public String updateView(Model model, @PathVariable(value = "thesisId") int id, @RequestParam Map<String,String> params) {
        model.addAttribute("thesis", this.sisService.getThesisbyId(id));
        
        return "addThesis";
    }
    
}
