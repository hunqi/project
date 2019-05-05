package cn.rs.picwall.pic.controller;

import cn.rs.picwall.pic.pojo.exception.ServiceException;
import cn.rs.picwall.pic.pojo.vo.PictureRequest;
import cn.rs.picwall.pic.service.PictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/upload")
public class FileUploadController implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private PictureService pictureService;

    @GetMapping
    public String listUploadedFiles(Model model) throws IOException {
        model.addAttribute("files", pictureService.findForPager(null));
        return "picUpload";
    }

    @PostMapping
    public String handleFileUpload(@RequestParam("files") MultipartFile[] files,
                                   RedirectAttributes redirectAttributes) {

        if (files != null && files.length > 0) {
            String fileNames = "";
            for (MultipartFile file : files) {
                try {
                    pictureService.save(new PictureRequest(file.getOriginalFilename(), file.getBytes()));
                } catch (IOException e) {
                    throw new ServiceException(e, "Failed to save file content");
                }

                fileNames += file.getOriginalFilename() + " ";
            }
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + fileNames + "!");
        } else {
            redirectAttributes.addFlashAttribute("message",
                    "No file selected !");
            return "redirect:/upload";
        }

        return "redirect:/upload";
    }

    @DeleteMapping
    public String deleteById(@RequestParam("id") int id) {
        System.out.println("id=" + id);
        pictureService.deleteById(id);
        return "picUpload";
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> systemErr(ServiceException e) {
        return ResponseEntity.ok().body("System error: " + e.getMessage());
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Map<String, Object> model = new HashMap<>();
        if (ex instanceof MaxUploadSizeExceededException) {
            model.put("errors", "Maximum upload size exceeded.");
            logger.error("Exceed max upload size.{}", ex.getMessage());
        } else {
            model.put("errors", "Unexpected error: " + "System error.");
            logger.error("System error.{}", ex.getMessage());
        }

        return new ModelAndView("picUpload", model);
    }
}
