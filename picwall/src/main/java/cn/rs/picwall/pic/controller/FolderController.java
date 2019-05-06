package cn.rs.picwall.pic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/folder")
public class FolderController {

    @GetMapping
    public String show(Model model){
        return "folder";
    }

}
