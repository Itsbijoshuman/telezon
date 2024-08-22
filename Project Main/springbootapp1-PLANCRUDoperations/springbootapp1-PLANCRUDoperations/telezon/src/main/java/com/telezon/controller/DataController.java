package com.telezon.controller;

import com.telezon.model.DataUsage;
import com.telezon.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping
    public String listDataUsages(Model model) {
        List<DataUsage> dataUsages = dataService.getAllDataUsages();
        model.addAttribute("dataUsages", dataUsages);
        model.addAttribute("dataUsage", new DataUsage()); // Prepare an empty DataUsage object for form binding
        return "data"; // Refers to data.html
    }

    @PostMapping
    public String addDataUsage(@ModelAttribute DataUsage dataUsage) {
        dataService.saveDataUsage(dataUsage);
        return "redirect:/data"; // Redirects to /data URL
    }

    @GetMapping("/{id}")
    public String editDataUsage(@PathVariable Integer id, Model model) {
        Optional<DataUsage> dataUsage = dataService.getDataUsageById(id);
        if (dataUsage.isPresent()) {
            model.addAttribute("dataUsage", dataUsage.get());
            return "data"; // Refers to data.html for editing
        } else {
            return "redirect:/data"; // Redirect if the dataUsage is not found
        }
    }

    @PostMapping("/{id}")
    public String updateDataUsage(@PathVariable Integer id, @ModelAttribute DataUsage dataUsage) {
        if (dataService.getDataUsageById(id).isPresent()) {
            dataUsage.setId(id);
            dataService.updateDataUsage(dataUsage);
        }
        return "redirect:/data"; // Redirect to /data URL
    }

    @DeleteMapping("/{id}")
    public String deleteDataUsage(@PathVariable Integer id) {
        dataService.deleteDataUsage(id);
        return "redirect:/data"; // Redirect to /data URL
    }
}
