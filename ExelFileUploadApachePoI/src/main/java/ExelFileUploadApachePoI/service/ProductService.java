package ExelFileUploadApachePoI.service;

import ExelFileUploadApachePoI.entities.Product;
import ExelFileUploadApachePoI.helper.Helper;
import ExelFileUploadApachePoI.repositories.ProdoctRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProdoctRepository prodoctRepository;

    public void save(MultipartFile file) throws IOException {

        List<Product> list = Helper.convertExcelToListOfProduct(file.getInputStream());
        this.prodoctRepository.saveAll(list);
    }

    public List<Product> getAllProducts() {
        return this.prodoctRepository.findAll();
    }
}
