package com.dinh.todo.controller.admin;

import com.dinh.todo.models.Product;
import com.dinh.todo.service.ProductService;
import com.dinh.todo.service.UploadService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product")
    public String getProductPage(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/createProduct")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("newProduct", product);

        return "admin/product/createProduct";
    }

    @GetMapping("admin/product/{id}")
    public String productDetailPage(Model model, @PathVariable long id) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "admin/product/productDetail";
    }

    @GetMapping("admin/product/update/{id}")
    public String updateProductPage(Model model, @PathVariable long id) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "admin/product/updateProduct";
    }

    @PostMapping("admin/product/createProduct")
    public String createProduct( @Valid @ModelAttribute("newProduct")Product newProduct, BindingResult bindingResult
    , @RequestParam("productImage") MultipartFile file, Model model) {

        if (bindingResult.hasErrors()) {
           // model.addAttribute("newProduct", newProduct);
            return "admin/product/createProduct";
        }
        String image = uploadService.handleSaveUploadFile(file, "product");
        newProduct.setImage(image);
        newProduct.setSold(0L);
        productService.save(newProduct);

        return  "redirect:/admin/product";
    }

    @PostMapping("admin/product/update")
    public String updateProduct(@Valid @ModelAttribute("product")Product product, BindingResult bindingResult,
                                @RequestParam("productImage") MultipartFile file, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/product/updateProduct";
        }

        if (!file.isEmpty()) {
            String img =  uploadService.handleSaveUploadFile(file, "product");
            product.setImage(img);
        } else {
            Product oldProduct = productService.findById(product.getId());
            product.setImage(oldProduct.getImage());
        }

        productService.save(product);
        return "redirect:/admin/product";
    }


    @PostMapping("admin/product/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productService.deleteById(id);
        return "redirect:/admin/product";
    }
}
