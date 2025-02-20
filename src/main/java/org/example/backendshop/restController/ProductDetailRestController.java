package org.example.backendshop.restController;

import jakarta.servlet.http.HttpSession;
import org.example.backendshop.dto.request.ProductDetailResponse;
import org.example.backendshop.entites.*;
import org.example.backendshop.restController.base.BaseRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product-detail-manage")
@CrossOrigin(origins = "http://localhost:3000/")
public class ProductDetailRestController extends BaseRestController {
    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    public ProductDetailRestController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/all-product-detail/{idPR}")
    public List<Object[]> getListProductDetail(@PathVariable("idPR") String idPR) {
        return this.productDetailService.getAllProductDetail(Integer.parseInt(idPR));
    }
    @PostMapping("/add_product_detail")
    public ResponseEntity<?> addProductDetail(@RequestBody List<ProductDetailResponse> listProductDetail, HttpSession session) {
        if (listProductDetail == null || listProductDetail.isEmpty()) {
            return ResponseEntity.badRequest().body("Danh sách sản phẩm chi tiết không được để trống.");
        }
        Integer idProduct = (Integer) session.getAttribute("idProduct");

        for (ProductDetailResponse response : listProductDetail) {
            ProductDetail productDetail = new ProductDetail();

            Product product = this.productService.findById(idProduct != null ? idProduct : Integer.parseInt(response.getIdProduct())).orElse(null);
            productDetail.setProduct(product);

            Size size = this.sizeService.findById(Integer.parseInt(response.getIdSize())).orElse(null);
            productDetail.setSize(size);

            Color color = this.colorService.findById(Integer.parseInt(response.getIdColor())).orElse(null);
            productDetail.setColor(color);

            WeightType weightType = this.weightTypeService.findById(Integer.parseInt(response.getIdWeight())).orElse(null);
            productDetail.setWeightType(weightType);

            productDetail.setSellingPrice(new BigDecimal(getClearComma(response.getSellingPrice())));
            productDetail.setImportPrice(new BigDecimal(getClearComma(response.getImportPrice())));
            productDetail.setQuantity(Integer.parseInt(getClearComma(response.getQuantity())));
            productDetail.setWeight(new BigDecimal(getClearComma(response.getWeight())));
            productDetail.setCreateDate(new Date());
            productDetail.setUpdateDate(new Date());
            productDetail.setStatus(response.getStatus());
            this.productDetailService.save(productDetail);
            messagingTemplate.convertAndSend("/topic/productDetail", productDetail);
        }
        return ResponseEntity.ok("Thêm sản phẩm chi tiết thành công.");
    }
    @PostMapping("/update_product_detail")
    public ResponseEntity<?> updateProductDetail(@RequestBody List<ProductDetailResponse> listProductDetail) {
        if (listProductDetail == null || listProductDetail.isEmpty()) {
            return ResponseEntity.badRequest().body("Danh sách sản phẩm chi tiết không được để trống.");
        }
        for (ProductDetailResponse response : listProductDetail) {
//            response.setQuantity(getClearComma(response.getQuantity()));
//            response.setImportPrice(getClearComma(response.getImportPrice()));
//            response.setSellingPrice(getClearComma(response.getSellingPrice()));
//            response.setWeight(getClearComma(response.getWeight()));
            ProductDetail productDetail = new ProductDetail();
            productDetail.setId(response.getId());

            Product product = this.productService.findById(Integer.parseInt(response.getIdProduct())).orElse(null);
            productDetail.setProduct(product);

            Size size = this.sizeService.findById(Integer.parseInt(response.getIdSize())).orElse(null);
            productDetail.setSize(size);

            Color color = this.colorService.findById(Integer.parseInt(response.getIdColor())).orElse(null);
            productDetail.setColor(color);

            WeightType weightType = this.weightTypeService.findById(Integer.parseInt(response.getIdWeight())).orElse(null);
            productDetail.setWeightType(weightType);

            productDetail.setSellingPrice(new BigDecimal(getClearComma(response.getSellingPrice())));
            productDetail.setImportPrice(new BigDecimal(getClearComma(response.getImportPrice())));
            productDetail.setQuantity(Integer.parseInt(getClearComma(response.getQuantity())));
            productDetail.setWeight(new BigDecimal(getClearComma(response.getWeight())));
            productDetail.setUpdateDate(new Date());
            productDetail.setStatus(response.getStatus());
            this.productDetailService.save(productDetail);
            messagingTemplate.convertAndSend("/topic/productDetail", productDetail);
        }

        return ResponseEntity.ok("Thêm sản phẩm chi tiết thành công.");
    }

    @GetMapping("/price-seling-product/{idPR}/{idC}/{idS}")
    public Object[] getListProductDetail(
            @PathVariable("idPR") String idPR,
            @PathVariable("idC") String idC,
            @PathVariable("idS") String idS) {
        System.out.println(idPR+"-"+idC+"-"+idS);
        // Convert the id parameters to Integer, or handle null values
        Integer productId = "null".equals(idPR) ? null : Integer.parseInt(idPR);
        Integer colorId = "null".equals(idC) ? null : Integer.parseInt(idC);
        Integer sizeId = "null".equals(idS) ? null : Integer.parseInt(idS);
        for (Object objects: this.productDetailService.getSellingPrice(productId, colorId, sizeId)) {
            System.out.println(objects);
        }
        return this.productDetailService.getSellingPrice(productId, colorId, sizeId);
    }

}
