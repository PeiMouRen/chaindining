package com.rhythm.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhythm.common.util.CommonUtil;
import com.rhythm.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping(value = "/page")
public class PageController {

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/nopermission")
    public String nopermission() {
        return "nopermission";
    }

    @RequestMapping(value = "/loginPage")
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, Model model) {
        try {
            User user = objectMapper.readValue((String)request.getSession().getAttribute("user"), User.class);
            model.addAttribute("username", user.getUsername());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "index";
    }

    @RequiresRoles(value = "admin")
    @RequestMapping(value = "/user-manage")
    public String userManage() {
        return "user-manage";
    }

    @RequiresRoles(value = {"admin", "manager"})
    @RequestMapping(value = "/dining-manage")
    public String diningManage(Model model, HttpSession session) {
        try {
            User user = objectMapper.readValue((String)session.getAttribute("user"), User.class);
            model.addAttribute("userLevel", user.getLevel());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "dining-manage";
    }

    @RequestMapping(value = "/dining-info")
    public String diningInfo(Model model, HttpSession session) {
        try {
            User user = objectMapper.readValue((String)session.getAttribute("user"), User.class);
            model.addAttribute("userLevel", user.getLevel());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "dining-info";
    }

    @RequestMapping(value = "/product-manage")
    public String productManage(Model model, HttpSession session) {
        try {
            User user = objectMapper.readValue((String)session.getAttribute("user"), User.class);
            model.addAttribute("userLevel", user.getLevel());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "product-manage";
    }

    @RequestMapping(value = "/order-manage")
    public String orderManage(Model model, HttpSession session) {
        try {
            User user = objectMapper.readValue((String)session.getAttribute("user"), User.class);
            model.addAttribute("userLevel", user.getLevel());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "order-manage";
    }


}
