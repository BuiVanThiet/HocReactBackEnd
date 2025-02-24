package org.example.backendshop.restController;

import jakarta.servlet.http.HttpSession;
import org.example.backendshop.dto.request.AttributeRequest;
import org.example.backendshop.dto.request.ImageByProductRequest;
import org.example.backendshop.dto.request.ProductRequest;
import org.example.backendshop.dto.response.ProductResponse;
import org.example.backendshop.entites.Category;
import org.example.backendshop.entites.Manufacturer;
import org.example.backendshop.entites.Origin;
import org.example.backendshop.entites.Product;
import org.example.backendshop.restController.base.BaseRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product-manage")
@CrossOrigin(origins = "http://localhost:3000/")  // URL của React app
public class ProductRestController extends BaseRestController {
    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    public ProductRestController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    @GetMapping("/get-all")
    public List<Object[]> getAllProduct(HttpSession session) {
        List<Object[]> productList = productService.getAllProduct();
        return productList;
    }

    @GetMapping("/product/{id}")
    public ProductResponse getProductById(@PathVariable("id") String id,HttpSession session) {
        Integer idInteger = Integer.parseInt(id);
        ProductResponse productResponse = productService.getProductById(idInteger);
        session.setAttribute("idProduct", productResponse.getId());
        if(productResponse.getId() == null) {
                return null;
            }
            return productResponse;
    }

    @GetMapping("/image_product/{id}")
    public List<ImageByProductRequest> getImageProductById(@PathVariable("id") String id) {
        return this.imageProductService.getListImageByProduct(Integer.parseInt(id));
    }

    @PostMapping("/add-product")
    public ResponseEntity<?> getAddProduct(@RequestBody ProductRequest productRequest, HttpSession session) {
        Product productAdd = new Product();
        String describe = productRequest.getDescribe();
        System.out.println(productRequest.toString());
        // Kiểm tra null trước khi gọi phương thức replace
        if (describe != null) {
            describe = describe.replace("\\n", "\n");
        }
        productAdd.setCodeProduct(productRequest.getCodeProduct());
        productAdd.setNameProduct(productRequest.getNameProduct());

        Category category = this.categoryService.findById(productRequest.getIdCategory()).orElse(null);
        productAdd.setCategory(category);

        Manufacturer manufacturer = this.manufacturerService.findById(productRequest.getIdManufacturer()).orElse(null);
        productAdd.setManufacturer(manufacturer);

        Origin origin = this.originService.findById(productRequest.getIdOrigin()).orElse(null);
        productAdd.setOrigin(origin);

        productAdd.setStatus(productRequest.getStatus());
        productAdd.setDescribe(describe.trim());
        productAdd.setUpdateDate(new Date());
        productAdd.setCreateDate(new Date());
        Product productUploadImage = this.productService.save(productAdd);
        session.setAttribute("idProduct", productUploadImage.getId());
        messagingTemplate.convertAndSend("/topic/product", productAdd);
        return ResponseEntity.ok(productUploadImage);
    }

    @PostMapping("/upload_image_product")
    public ResponseEntity<?> getUploadImageProduct(@RequestParam("files") List<MultipartFile> files, HttpSession session) throws IOException {
        Integer idPR = (Integer) session.getAttribute("idProduct");
        if (idPR == null) {
            return ResponseEntity.badRequest().body("Không tìm thấy sản phẩm để upload ảnh.");
        }

        Product productUploadImage = this.productService.findById(idPR).orElse(null);
        if (productUploadImage == null) {
            return ResponseEntity.notFound().build();
        }

        for (MultipartFile multipartFile : files) {
            this.productService.uploadFile(multipartFile, productUploadImage);
        }
        messagingTemplate.convertAndSend("/topic/product", new Product());
        session.removeAttribute("idProduct");
        return ResponseEntity.ok("Upload ảnh thành công.");
    }

    @PostMapping("/delete_image_product")
    public ResponseEntity<?> getDeleteImageProduct(@RequestBody List<String> files) throws IOException {
        for (String id: files) {
            this.productService.deleteFileImage(id);
            System.out.println("data anh"+id);
        }
        messagingTemplate.convertAndSend("/topic/product", new Product());
        return ResponseEntity.ok(null);
    }

    @PostMapping("/update")
    public ResponseEntity<?> getUpdate(@RequestBody ProductRequest productRequest,HttpSession session) {
        System.out.println(productRequest.getId());
        System.out.println(productRequest.toString());
        System.out.println(productRequest.getStatus());
        String describe = productRequest.getDescribe().replace("\\n", "\n");

        Product product = this.productService.findById(productRequest.getId()).orElse(null);
        product.setCodeProduct(productRequest.getCodeProduct().trim());
        product.setNameProduct(productRequest.getNameProduct().trim());

        Category category = this.categoryService.findById(productRequest.getIdCategory()).orElse(null);
        product.setCategory(category);

        Manufacturer manufacturer = this.manufacturerService.findById(productRequest.getIdManufacturer()).orElse(null);
        product.setManufacturer(manufacturer);

        Origin origin = this.originService.findById(productRequest.getIdOrigin()).orElse(null);
        product.setOrigin(origin);

        product.setStatus(productRequest.getStatus());
        product.setDescribe(describe.trim());
        product.setUpdateDate(new Date());
        Product productUploadImage = this.productService.save(product);
        session.setAttribute("idProduct", productUploadImage.getId());
        messagingTemplate.convertAndSend("/topic/product", productUploadImage);
        return ResponseEntity.ok(productUploadImage);
    }

}
