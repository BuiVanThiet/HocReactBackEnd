package org.example.backendshop.implement;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.example.backendshop.dto.response.ProductResponse;
import org.example.backendshop.entites.ImageProduct;
import org.example.backendshop.entites.Product;
import org.example.backendshop.repositores.ProductRepository;
import org.example.backendshop.services.ImageProductService;
import org.example.backendshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ProductImplement implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ImageProductService imageProductService;

    private final Cloudinary cloudinary;


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public <S extends Product> S save(S entity) {
        return productRepository.save(entity);
    }

    @Override
    public Optional<Product> findById(Integer integer) {
        return productRepository.findById(integer);
    }

    @Override
    public void deleteById(Integer integer) {
        productRepository.deleteById(integer);
    }
    @Override
    public List<Object[]> getAllProduct() {
        return productRepository.getAllProduct();
    }


    @Override
    public String uploadFile(MultipartFile multipartFile,Product productUpload) throws IOException {
        String nameImage = UUID.randomUUID().toString();
        // Đẩy file lên Cloudinary với tên file gốc
        ImageProduct imageProduct = new ImageProduct();
        imageProduct.setProduct(productUpload);
        imageProduct.setCodeImage(cloudinary.uploader()
                .upload(multipartFile.getBytes(), Map.of(
                        "public_id", "chillInFree/" + nameImage, // Đưa vào thư mục chillInFree
                        "folder", "chillInFree" // Chỉ định thư mục
                ))
                .get("url")
                .toString());
        imageProduct.setUpdateDate(new Date());
        imageProduct.setCreateDate(new Date());
        imageProduct.setStatus(1);
        this.imageProductService.save(imageProduct);
        return imageProduct.getCodeImage();
    }
    @Override
    public String deleteFileImage(String publicId) throws IOException {
        // Lấy đối tượng ImageProduct từ database bằng publicId
        ImageProduct imageProduct = this.imageProductService.findById(Integer.parseInt(publicId)).orElse(null);
        // Kiểm tra nếu không tìm thấy imageProduct
        if (imageProduct == null) {
            return "Ảnh không tồn tại trong hệ thống!";
        }
        // Cắt chuỗi để loại bỏ phần version và URL, giữ lại phần publicId của ảnh
        String resultCut = imageProduct.getCodeImage().replaceAll("http://res.cloudinary.com/dfy4umpja/image/upload/v[0-9]+/", "").replaceAll("\\.png$", "");
//        // Gọi Cloudinary API để xóa ảnh
//        Map<String, Object> result = cloudinary.uploader().destroy(resultCut, Map.of());
        try {
            ApiResponse apiResponse = cloudinary.api().deleteResources(Arrays.asList(resultCut),
                    ObjectUtils.asMap("type", "upload", "resource_type", "image"));
            System.out.println(apiResponse);
            this.imageProductService.deleteById(Integer.parseInt(publicId));
            return apiResponse.toString();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            return "false";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        System.out.println("result: "+result+"code image: "+resultCut);
//        // Kiểm tra kết quả trả về từ Cloudinary
//        if ("ok".equals(result.get("result"))) {
//            // Xóa dữ liệu ảnh trong hệ thống
//            this.imageProductService.deleteById(Integer.parseInt(publicId));
//            return "Xóa ảnh thành công!";
//        } else {
//            return "Xóa ảnh thất bại hoặc ảnh không tồn tại!";
//        }
    }


    @Override
    public ProductResponse getProductById(Integer idCheck) {
        return this.productRepository.getProductById(idCheck);
    }
}
