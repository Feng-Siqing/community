package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    AlphaService alphaService;

    @RequestMapping("/data")
    @ResponseBody
    public String get() {
        return alphaService.find();
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello Spring Boot.";
    }

    @RequestMapping("/http")
    //不需要返回值，因为response对象，可以直接向浏览器输出任何数据，不需要依赖这个返回值
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //获取请求数据
        System.out.println("************************");
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> e = request.getHeaderNames();
        System.out.println("*************************");
        while(e.hasMoreElements()) {
            String name = e.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println("***************************");
        System.out.println(request.getParameter("code"));

        //response是返回相应数据的对象
        response.setContentType("text/html;charset=utf-8");
        try (
            PrintWriter writer = response.getWriter();
                ){
            writer.write("<h1>牛客网</h1>");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    //简便方式处理请求数据
    //GET请求，也是默认请求
    //假设查询所有学生：/students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10")int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    //另一种请求方式，在路径中直接传参：/students/123
    @RequestMapping(path = "/students/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);

        return "A student";
    }

    //POST请求
    @RequestMapping(path = "student", method = RequestMethod.POST)
    @ResponseBody
    public String addStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "Success";
    }

    //响应HTML数据
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "Derrick");
        modelAndView.addObject("age", "27");
        modelAndView.setViewName("demo/view");

        return modelAndView;
    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name", "NTU");
        model.addAttribute("age", 100);
        return "/demo/view";
    }

    //响应Json数据，在异步请求中
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp() {
        Map<String, Object> emp = new HashMap<>();
        emp.put("Name", "ABC");
        emp.put("Age", 23);
        emp.put("Salary", 24000);
        emp.put("Company", "Google");
        return emp;
    }

}
