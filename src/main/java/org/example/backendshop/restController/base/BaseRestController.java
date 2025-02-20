package org.example.backendshop.restController.base;

import org.example.backendshop.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;


public abstract class BaseRestController {
    @Autowired
    protected ColorService colorService;
    @Autowired
    protected CategoryService categoryService;
    @Autowired
    protected ManufacturerService manufacturerService;
    @Autowired
    protected OriginService originService;
    @Autowired
    protected SizeService sizeService;
    @Autowired
    protected WeightTypeService weightTypeService;
    @Autowired
    protected ProductService productService;
    @Autowired
    protected ImageProductService imageProductService;
    @Autowired
    protected ProductDetailService productDetailService;

    protected String getClearComma(String number) {
        if(number != null) {
            return number.replace(",", "");
        }else {
            return number;
        }

    }

}
