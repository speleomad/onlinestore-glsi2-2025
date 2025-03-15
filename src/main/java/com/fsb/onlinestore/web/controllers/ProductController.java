package com.fsb.onlinestore.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.fsb.onlinestore.web.models.Product;
import com.fsb.onlinestore.web.models.requests.ProductForm;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/* Carte d'opérations CRUD
 *  Get / ou /products  :  Permet de récupérer la liste des produit
 * 
 */
@Controller
public class ProductController {

    private static List<Product> products = new ArrayList<Product>();
    private static Long idCount = 0L;
    static {
        products.add(new Product(++idCount, "SS-S9", "Samsung Galaxy S9", 500D, 50, "samsung-s9.png"));
        products.add(new Product(++idCount, "NK-5P", "Nokia 5.1 Plus", 60D, 60, null));
        products.add(new Product(++idCount, "IP-7", "iPhone 7", 600D, 30, "iphone-7.png"));
    }

    @RequestMapping({"/","/products"})
    public String getProducts(Model model) {
       model.addAttribute("products", products);
        return "list";
    }
    @RequestMapping("/products/create")
    public String showAddProductForm(Model model) {
        model.addAttribute("productForm", new ProductForm());
        return "create";
    }
    
    @RequestMapping(path="/products/create", method=RequestMethod.POST)
    public String addProduct(@ModelAttribute @Valid ProductForm productForm,
                             BindingResult bindingResult, Model model) {
         if(bindingResult.hasErrors() ){
            model.addAttribute("error", "erreur input!");
            return "edit";
         }
          Product product=new Product(++idCount,productForm.getCode(),productForm.getName(),productForm.getPrice(),productForm.getQuantity(),null);
         products.add(product);
          return "redirect:/products";
    }
    
    

}