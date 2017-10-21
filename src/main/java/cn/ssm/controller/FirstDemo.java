package cn.ssm.controller;

import cn.ssm.entity.Student;
import cn.ssm.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping(value = "/Desk")
public class FirstDemo {

    @Autowired
    public MybatisService mybatisService;


    //跳转主页
    @RequestMapping(value = "/Home")
    public String homeUI(Model model){
        model.addAttribute("nub1",mybatisService.findByStates("已就业"));
        model.addAttribute("nub2",mybatisService.findByStates("学习中"));
        return "home.page";
    }
    //跳转2号页面
    @RequestMapping(value = "/Two")
    public String twoUI(Model model){
        model.addAttribute("tp1",mybatisService.findByType("前端开发"));
        model.addAttribute("tp2",mybatisService.findByType("后端开发"));
        return "two.page";
    }
    //跳转3号页面
    @RequestMapping(value = "/Three")
    public String threeUI(){
        return "three.page";
    }

    //跳转到注册页面
    @RequestMapping(value = "/addUI")
    public String addUI(){
        return "/WEB-INF/addUI.jsp";
    }

    //跳转到登录页面
    @RequestMapping(value = "/loginUI")
    public String loginUI(){
        return "/WEB-INF/loginUI.jsp";
    }



    //处理登录请求成功后跳转到首页否则跳回登录页面
    @RequestMapping(value = "/StudentL",method = RequestMethod.POST)
    public String login(HttpServletRequest request,Student student){
        String str = "/Desk/loginUI";
        try {
            Student temp = mybatisService.findByStudent(student);
            if (temp!=null) {
                //验证登录成功后,将temp放入session域中,并跳转到首页
                request.getSession().setAttribute("fstudent",temp);
                Date timedate = new Date();
                request.getSession().setAttribute("time",timedate);
                str = "redirect:/Desk/Home";
            }
        }catch (Exception e){}
        //登录失败跳回登录方法
        return str;
    }



    //处理注册请求后重定向至首页
    @RequestMapping(value = "/StudentA",method = RequestMethod.POST)
    public String addStudent(Student student){
        String url = "/Desk/loginUI";
        if (student!=null){
            //为帐号添加唯一uuid
            UUID uu  =  UUID.randomUUID();
            String id = uu.toString().replace("-","");
            student.setId(id);
            //注册时默认学习状态为在学状态
            student.setStates("学习中");
            Long ctime = new Date().getTime();
            student.setCreatetime(ctime);
            mybatisService.add(student);
            //注册成功,重定向到首页
            url = "redirect:/Desk/Home";
        }
        return url;
    }


    //获取所有用户并跳转至列表页面
    @RequestMapping(value = "/listUI")
    public String listStudent(Model model, HttpServletRequest request) throws ServletException, IOException {
        List<Student> list = mybatisService.findAll();
        model.addAttribute("studentlist",list);
        return "/WEB-INF/listUI.jsp";
    }


    //处理用户登出需求重定向回首页
    @RequestMapping(value = "/Clear")
    public String clearStudent(Model model, HttpServletRequest request) throws ServletException, IOException {
        request.getSession().removeAttribute("fstudent");
        request.getSession().removeAttribute("time");
        return "redirect:/Desk/Home";
    }


    //处理跳转至更新页面请求
    @RequestMapping(value = "/StudentU",method = RequestMethod.GET)
    public String updateUI(Model model,String id){
       Student student = mybatisService.findById(id);
       model.addAttribute("studentup",student);
       return "/WEB-INF/updateUI.jsp";
    }


    //处理更新请求并跳转至列表页面方法
    @RequestMapping(value = "/StudentP",method = RequestMethod.POST)
    public String updateStudent(Model model,Student student){
       mybatisService.update(student);
       return "/Desk/listUI";
    }


    //处理删除用户请求并跳回列表页面方法
    @RequestMapping(value = "/StudentD",method = RequestMethod.GET)
    public String deleteStudent(String id){
        Student student = mybatisService.findById(id);
        mybatisService.delete(student);
        return "/Desk/listUI";
    }


}
