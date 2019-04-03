package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: springboot-learn
 * <p>
 * Created by yuanqn on 2019/3/26 23:01
 */
@Controller
public class HelloWorld {

    @ResponseBody
    @RequestMapping("/world")
    public String world() {
        return "hello world";
    }
}
