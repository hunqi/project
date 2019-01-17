package cn.rs.picwall.pic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/display")
public class PhotoController {

    @Autowired
    private PictureService pictureService;

    @GetMapping("/")
    public String display(Model model) {
        model.addAttribute("files", pictureService.findAll());

        return "picDisplay";
    }

}
