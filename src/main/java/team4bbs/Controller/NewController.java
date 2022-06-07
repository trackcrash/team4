package team4bbs.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewController {
    @GetMapping
    public String index(){
        return "index";
    }
}
