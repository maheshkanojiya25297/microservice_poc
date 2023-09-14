package ExelFileUploadApachePoI.controllers;

import ExelFileUploadApachePoI.entities.Product;
import ExelFileUploadApachePoI.helper.Helper;
import ExelFileUploadApachePoI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("Excel")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/product/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (Helper.checkExcelFormat(file)) {
            this.productService.save(file);
            return ResponseEntity.ok(Map.of("message", "file uploaded and data is saved to database"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload Excel File Only !");
        }

    }

    @GetMapping("/product")
    public List<Product> getAllProduct() {
        return this.productService.getAllProducts();
    }


}
