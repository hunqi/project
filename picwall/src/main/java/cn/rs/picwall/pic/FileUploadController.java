package cn.rs.picwall.pic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private PictureService pictureService;

    @GetMapping
    public String listUploadedFiles(Model model) throws IOException {
        model.addAttribute("files", pictureService.findForPager(null));
        return "picUpload";
    }

    @PostMapping
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        try {
            if (file == null || file.getBytes().length == 0) {
                redirectAttributes.addFlashAttribute("message",
                        "No file selected !");
                return "redirect:/upload";
            }

            pictureService.save(file.getBytes());
        } catch (IOException e) {
            throw new ServiceException(e, "Failed to save file content");
        }

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

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


}
