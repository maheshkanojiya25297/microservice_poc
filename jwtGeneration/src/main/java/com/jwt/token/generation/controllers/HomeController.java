package com.jwt.token.generation.controllers;

import com.jwt.token.generation.model.Address;
import com.jwt.token.generation.model.User;
import com.jwt.token.generation.repositories.AddressRepository;
import com.jwt.token.generation.service.AddressService;
import com.jwt.token.generation.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/home")
public class HomeController {

    // http://localhost:8081/home/user

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> getUser() {
        System.out.println("getting user");
        return this.userService.getUser();
    }

    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/address")
    public ResponseEntity<?> getAddress() {
        return ResponseEntity.ok(this.addressService.getAddress());
    }

    @GetMapping("/jasperpdf/export")
    public void createPDF(HttpServletResponse response) throws IOException, JRException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        addressService.exportJasperReport(response);
    }

    @GetMapping("/report")
    public String empReport() throws JRException, FileNotFoundException {
        return addressService.generateReport();
    }

    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> downloadExcel() throws FileNotFoundException, JRException {
        File file = ResourceUtils.getFile("classpath:address_info.jrxml");
        System.out.println("file\n" + file + " \n");
        InputStream inputStream = new FileInputStream(file);
        System.out.println("inputStream\n" + inputStream + " \n");
        JasperReport jasperPrint = JasperCompileManager.compileReport(inputStream);
        List<Address> data = this.addressService.fetchAddress();
        System.out.println("data\n" + data + " \n");
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(data);
        Map<String, Object> params = new HashMap<>();
        params.put("createdBy", "XYZ");
        System.out.println("params\n" + params + " \n");
        JasperPrint jasperPrint1 = JasperFillManager.fillReport(jasperPrint, params, jrBeanCollectionDataSource);
        System.out.println("jasperPrint1\n" + jasperPrint1 + " \n");
        byte[] excelBytes = null;
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint1));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        //("C:\\temp\\Address.xlsx"));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setRemoveEmptySpaceBetweenColumns(true);
        configuration.setDetectCellType(true);
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        excelBytes = outputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "report.xlsx");
        ByteArrayResource resource = new ByteArrayResource(excelBytes);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(excelBytes.length)
                .body(resource);
    }
}
