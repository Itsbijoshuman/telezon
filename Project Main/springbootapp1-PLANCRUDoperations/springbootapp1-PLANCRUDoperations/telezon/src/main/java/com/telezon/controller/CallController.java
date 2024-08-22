package com.telezon.controller;

import com.telezon.model.Call;
import com.telezon.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/calls")
public class CallController {

    @Autowired
    private CallService callService;

    @GetMapping
    public String listCalls(Model model) {
        List<Call> calls = callService.getAllCalls();
        model.addAttribute("calls", calls);
        model.addAttribute("call", new Call()); // Prepare an empty Call object for form binding
        return "call"; // Refers to call.html
    }

    @PostMapping
    public String addCall(@ModelAttribute Call call) {
        callService.saveCall(call);
        return "redirect:/calls"; // Redirects to /calls URL
    }

    @GetMapping("/{id}")
    public String editCall(@PathVariable Integer id, Model model) {
        Optional<Call> call = callService.getCallById(id);
        if (call.isPresent()) {
            model.addAttribute("call", call.get());
            return "call"; // Refers to call.html for editing
        } else {
            return "redirect:/calls"; // Redirect if the call is not found
        }
    }

    @PostMapping("/{id}")
    public String updateCall(@PathVariable Integer id, @ModelAttribute Call call) {
        if (callService.getCallById(id).isPresent()) {
            call.setCid(id);
            callService.updateCall(call);
        }
        return "redirect:/calls"; // Redirect to /calls URL
    }

    @DeleteMapping("/{id}")
    public String deleteCall(@PathVariable Integer id) {
        callService.deleteCall(id);
        return "redirect:/calls"; // Redirect to /calls URL
    }
}
