package com.nt.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nt.entity.Transaction;
import com.nt.entity.User;
import com.nt.service.TransactionService;
import com.nt.service.UserService;

@Controller
@RequestMapping("/finance")
public class FinanceController {
    private final UserService userService;
    private final TransactionService transactionService;

    @Autowired
    public FinanceController(UserService userService, TransactionService transactionService) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "createUser"; 
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/finance/user/" + user.getId();
    }

    @GetMapping("/user/{id}")
    public String viewUser(@PathVariable Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("transactions", user.getTransactions());
        return "userDashboard"; 
    }

    @PostMapping("/user/{id}/addTransaction")
    public String addTransaction(@PathVariable Long id, @ModelAttribute Transaction transaction) {
        User user = userService.findUserById(id);
        user.addTransaction(transaction);
        userService.saveUser(user);
        return "redirect:/finance/user/" + id; 
    }
}
