package cn.rs.picwall.pic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pic")
public class PicController {

    @GetMapping("/display")
    public String display(Model model) {
        return "displayPics";
    }

    @GetMapping("/upload")
    public String upload(Model model){
        return "upload";
    }

}
