package cn.rs.picwall.pic.controller;

import cn.rs.picwall.pic.pojo.vo.Page;
import cn.rs.picwall.pic.pojo.vo.PictureResponse;
import cn.rs.picwall.pic.service.PictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PhotoController {

    private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);

    @Autowired
    private PictureService pictureService;

    @GetMapping("/display")
    public String display(Model model) {
        model.addAttribute("files", pictureService.findForPager(null));

        return "picDisplay";
    }

    @GetMapping("/pager")
    @ResponseBody
    public List<PictureResponse> findByPager(Page page) {
        logger.info("page={}", page);
        return pictureService.findForPager(page);
    }

}
