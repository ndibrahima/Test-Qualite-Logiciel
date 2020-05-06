package com.tactfactory.monprojetsb.monprojetsb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tactfactory.monprojetsb.monprojetsb.entities.Product;
import com.tactfactory.monprojetsb.monprojetsb.repositories.ProductRepository;

@Controller
@RequestMapping(value= {"products"})
public class ProductController {

  @Autowired
  private ProductRepository productRepository;

  @RequestMapping(value = {"/index", "/"})
  public String index(Model model) {
    model.addAttribute("page", "Product index");
    model.addAttribute("items", productRepository.findAll());
    return "product/index";
  }

  @GetMapping(value = {"/create"})
  public String createGet(Model model) {
    model.addAttribute("page", "Product create");
    return "product/create";
  }

  @PostMapping(value = {"/create"})
  public String createPost(@ModelAttribute Product product) {

    if (product != null) {
      productRepository.save(product);
    }

    return "redirect:index";
  }

  @PostMapping(value = {"/delete"})
  public String delete(@RequestParam Long id) {

    if (id != null) {
      productRepository.deleteById(id);
    }

    return "redirect:/products/index";
  }

  @GetMapping(value = {"/{id}/details"})
  public String details(@PathVariable(value = "id") Long id, Model model) {

    model.addAttribute("page", "Product details");
    model.addAttribute("backlink", "/products/index");

    if (id != null) {
      model.addAttribute("item",productRepository.findById(id).get());
    }

    return "product/details";
  }
}
