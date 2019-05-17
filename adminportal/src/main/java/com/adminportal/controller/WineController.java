package com.adminportal.controller;

import com.adminportal.service.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adminportal.domain.Wine;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
@RequestMapping("/wine")
public class WineController {

    @Autowired
    private WineService wineService;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addWine(Model model) {
		Wine wine = new Wine();
		model.addAttribute("wine", wine);
		return "addWine";
	}

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addWinePost(
            @ModelAttribute("wine") Wine wine, HttpServletRequest request
    ) {
        wineService.save(wine);

        MultipartFile wineImage=wine.getWineImage();

        try{
            byte[] bytes = wineImage.getBytes();
            String name = wine.getId()+".png";
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/wine"+name)));
            stream.write(bytes);
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:wineList";
    }

    @RequestMapping("/wineList")
    public String wineList(Model model) {

	    return "wineList";

    }


}
