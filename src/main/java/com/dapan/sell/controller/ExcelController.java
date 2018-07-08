package com.dapan.sell.controller;

import com.dapan.sell.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/wx")
public class ExcelController {

    @Autowired
    private ExcelService service;

    @GetMapping("/upload")
    public ModelAndView up() {
        return new ModelAndView("upload/upload_excel");
    }

    @PostMapping("/import")
    public ModelAndView load(@RequestParam("file") MultipartFile file,
                             Map<String, Object> map) {

        String filename = file.getOriginalFilename();


        try {
            boolean notNull = service.batchImport(filename, file);

                map.put("msg", "导入成功");
//                map.put("url", "/sell/common/success");

                return new ModelAndView("common/success", map);
        } catch (IOException e) {
            e.printStackTrace();

            map.put("msg", "导入失败");
//            map.put("url", "common/error");

            return new ModelAndView("common/error", map);
        }
    }
}
