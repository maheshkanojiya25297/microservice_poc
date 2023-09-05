package com.jwt.token.generation.service;

import com.jwt.token.generation.model.Address;
import com.jwt.token.generation.repositories.AddressRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.*;

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

    public String generateReport() throws FileNotFoundException, JRException {
        List<Address> listOfaddress = new ArrayList<>();
        log.info("listOfaddress\\" + listOfaddress + " \\");
        this.addressRepository.findAll().stream().forEach(address -> listOfaddress.add(address));
        File file = ResourceUtils.getFile("classpath:address_info.jrxml");
        log.info("file\\" + file + " \\");
        InputStream inputStream = new FileInputStream(file);
        log.info("inputStream\\" + inputStream + " \\");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        log.info("jasperReport\\" + jasperReport + " \\");
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(listOfaddress);
        log.info("jrBeanCollectionDataSource\\" + jrBeanCollectionDataSource + " \\");
        Map<String, Object> map = new HashMap<>();
        map.put("createdBy", "Mahesh Kanojiya");
        log.info("map\\" + map + " \\");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, jrBeanCollectionDataSource);
        log.info("jasperPrint\\" + jasperPrint + " \\");
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\temp\\Address.pdf");
        JasperExportManager.exportReportToHtmlFile(jasperPrint, "C:\\temp\\Address.html");
        JasperExportManager.exportReportToXmlFile(jasperPrint, "C:\\temp\\Address.xml", true);
        xlsx(jasperPrint);
        csv(jasperPrint);
        return "Report successfully generated at location = C:\\temp\\";

    }

    private void csv(JasperPrint jasperPrint) throws JRException {
        JRCsvExporter exporter = new JRCsvExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput((WriterExporterOutput) new SimpleWriterExporterOutput("C:\\temp\\Address.csv"));
        SimpleCsvExporterConfiguration  configuration = new SimpleCsvExporterConfiguration();
        configuration.setFieldDelimiter(",");
        exporter.setConfiguration(configuration);
        exporter.exportReport();
    }

    private void xlsx(JasperPrint jasperPrint) throws JRException {
        JRXlsxExporter exporter = new JRXlsxExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("C:\\temp\\Address.xlsx"));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setRemoveEmptySpaceBetweenColumns(true);
        configuration.setDetectCellType(true);
        exporter.setConfiguration(configuration);
        exporter.exportReport();
    }
}

