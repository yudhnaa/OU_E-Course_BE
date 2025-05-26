package com.ou.services.impl;

import com.ou.helpers.CloudinaryHelper;
import com.ou.pojo.CourseCertificate;
import com.ou.services.CertificateService;
import jakarta.servlet.ServletContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.time.LocalDate;
import java.util.Map;

@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private SpringTemplateEngine templateEngine;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private CloudinaryHelper cloudinaryHelper;

    @Override
    public String convertHtmlToXhtml(String html) {
        Document document = Jsoup.parse(html);
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        return document.html();
    }

    @Override
    public File convertXhtmlToPdf(String xhtml, String outFileName) throws IOException {
        String realPath = servletContext.getRealPath("/");
        File tempDir = new File(realPath, "temp");
        if (!tempDir.exists()) tempDir.mkdirs();

        File outputFile = new File(tempDir, outFileName);
        ITextRenderer iTextRenderer = new ITextRenderer();
        iTextRenderer.setDocumentFromString(xhtml);
        iTextRenderer.layout();
        OutputStream os = new FileOutputStream(outputFile);
        iTextRenderer.createPDF(os);
        os.close();
        return outputFile;
//        System.out.println("Saving to: " + new File(tempDir, outFileName).getAbsolutePath());
    }

    @Override
    public File generatePdf(CourseCertificate courseCertificate) throws IOException {
        // 1. Prepare HTML
        Context context = new Context();
        context.setVariable("courseCertificate", courseCertificate);
        context.setVariable("issueDate", LocalDate.now());

        // 2. Render Thymeleaf template to HTML string
        String html = templateEngine.process("dashboard/admin/certificate/certificate_template", context);

        // 3. Convert HTML to XHTML
        String xhtml = convertHtmlToXhtml(html);

        // 4. Generate PDF
        String outFileName = courseCertificate.getCourseStudentId().getStudentId().getId().toString() + "_" + courseCertificate.getCourseStudentId().getCourseId().getId().toString() + ".pdf";
        File certFile = convertXhtmlToPdf(xhtml, outFileName);

        return certFile.getAbsoluteFile();
    }

}
