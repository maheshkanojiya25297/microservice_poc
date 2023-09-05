package com.jwt.token.generation.service;

import com.jwt.token.generation.model.Address;
import com.jwt.token.generation.repositories.AddressRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Object getAddress() {
        List<Address> addresses = this.addressRepository.findAll();
        return addresses;
    }

    public void exportJasperReport(HttpServletResponse response) throws IOException, JRException {
        log.info("Reading image----->: ");
        InputStream image = this.getClass().getClassLoader().getResourceAsStream("img/cherry.png");
        log.info("image----->: " + image);
        List<Address> addresses = this.addressRepository.findAll();
        log.info("Person address----->:" + addresses.get(0).getFirstname());
        File file = ResourceUtils.getFile("classpath:Address.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(addresses);
        log.info("jrBeanCollectionDataSource----->:" + jrBeanCollectionDataSource);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Simplifying Tech");
        parameters.put("logo", image);
        log.info("jrBeanCollectionDataSource----->:" + jrBeanCollectionDataSource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
        log.info("jasperPrint----->:" + jasperPrint);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

}

