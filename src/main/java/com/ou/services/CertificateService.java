package com.ou.services;


import com.ou.pojo.CourseCertificate;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface CertificateService {
    String convertHtmlToXhtml(String html);
    File convertXhtmlToPdf(String xhtml, String outFileName) throws IOException;
    File generatePdf(CourseCertificate courseCertificate) throws IOException;
}
