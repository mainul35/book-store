package com.book.controller;

import com.book.entity.DomainBase;
import com.book.util.AppBase;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

public abstract class ControllerBase extends AppBase {
    public abstract List<DomainBase> getAll();
    public abstract void save(DomainBase object);
    public abstract DomainBase details (Long id);
    public abstract DomainBase update (DomainBase object);
    public abstract void delete (Long id);

    public Object checkRequestTypeAndPerformAction (HttpServletRequest request, HttpServletResponse response, Object object, Map params) {
        if (request.getHeader("x-requested-with") == null) {
            try {
                response.setContentType("text/html");
                File file = ResourceUtils.getFile("classpath:templates/site/spa.html");
                BufferedReader br = new BufferedReader(new FileReader(file));
                PrintWriter out = response.getWriter();
                String line = "";
                while ((line = br.readLine())!=null) {
                    if (line.contains("<div class=\"container\">")) {
                        line += "<content-loading-metadata metadata-url='"+request.getRequestURI()+"' object='"+params.get("object").toString()+"' hasParam='"+params.get("hasParam").toString()+"'></content-loading-metadata>";
                    }
                    out.write(line);
                }
                out.close();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return object;
        }
    }
}
