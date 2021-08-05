package Coming.controller;

import Coming.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Coming
 * @date 2021/6/29 21:13
 */
@Controller
public class IndexController {

    /**
     * 未登录页面
     */
    @GetMapping(value = {"/","login"})
    public String loginPage(){
        return "login";
    }

    @PostMapping("login")
    public String main(User user, HttpSession session, Model model){
        if(StringUtils.hasLength(user.getUsername()) && "123456".equals(user.getPassword())){
            //判断用户是否登陆成功
            session.setAttribute("loginUser",user);
            //登录成功后重定向到main.html;防止表单重复提交
            return "redirect:/main.html";
        }else{
            model.addAttribute("msg","username or password is error!");
            //回到登录界面
            return "login";
        }
    }

    /**
     * 去main页面
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session, Model model){
        Object loginUser = session.getAttribute("loginUser");
        if(loginUser !=null){
            return "main";
        }else{
            //回到登录页面
            model.addAttribute("msg","please login again!");
            return "login";
        }
    }
}
