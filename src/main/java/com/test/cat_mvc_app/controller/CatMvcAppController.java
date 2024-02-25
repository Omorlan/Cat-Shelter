package com.test.cat_mvc_app.controller;

import com.test.cat_mvc_app.cat.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CatMvcAppController {
    @Autowired
    private BdController bdController;


    @GetMapping("/addCat")
    public String showAddCatForm() {
        return "addCat";
    }

    @PostMapping("/addCat")
    public String addCat(@RequestParam("catName") String catName,
                         @RequestParam("catAge") String catAge,
                         @RequestParam("catBreed") String catBreed,
                         @RequestParam("catDescription") String catDescription,
                         Model model) {

        if (catName.isEmpty() || catAge.isEmpty() || catBreed.isEmpty() || catDescription.isEmpty()) {
            model.addAttribute("errorMessage", "Please fill in all fields");
            return "addCat";
        }
        Cat newCat = new Cat(catName, catBreed, Integer.parseInt(catAge), catDescription);
        bdController.addCat(newCat);
        return "redirect:/allCats";
    }

    @PostMapping("/editCat/{catId}")
    public String editCat(@PathVariable("catId") int catId,
                          @RequestParam("catName") String catName,
                          @RequestParam("catAge") int catAge,
                          @RequestParam("catBreed") String catBreed,
                          @RequestParam("catDescription") String catDescription) {
        Cat updatedCat = new Cat(catId, catName, catAge, catBreed, catDescription);
        bdController.updateCat(updatedCat);
        return "redirect:/allCats";
    }


    @GetMapping("/editCatForm/{catId}")
    public String showEditCatForm(@PathVariable("catId") int catId, Model model) {
        Cat cat = bdController.getCatById(catId);
        model.addAttribute("cat", cat);
        return "editCat";
    }

    @PostMapping("/toMain")
    public String toMain(Model model) {
        return "redirect:/index";
    }

    @RequestMapping(value = "/index")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/allCats")
    public String showAllCats(Model model) {
        List<Cat> allCats = bdController.getAllCats();
        model.addAttribute("cats", allCats);
        return "allCats";
    }

    @PostMapping("/clearTable")
    public String clearTable(Model model) {

        bdController.clearTable();

        return "redirect:/index";
    }

    @PostMapping("/deleteSelectedCats")
    public String deleteSelectedCats(@RequestParam("selectedCats") List<Long> selectedCatsIds) {
        bdController.deleteSelectedCats(selectedCatsIds);
        return "redirect:/allCats";
    }
}