package cn.rs.picwall.pic.controller;

import cn.rs.picwall.pic.dao.FolderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.security.core.context.SecurityContextHolder.*;

@Controller
@RequestMapping("/folder")
public class FolderController {

    private static final Logger logger = LoggerFactory.getLogger(FolderController.class);

    @Autowired
    private FolderDao folderDao;

    @GetMapping
    public String show(Model model) {
        UserDetails user = (UserDetails) getContext().getAuthentication().getPrincipal();
        logger.info("show folders for {}", user.getUsername());
        model.addAttribute("folders", folderDao.findByUserName(user.getUsername()));

        return "folder";
    }

}
