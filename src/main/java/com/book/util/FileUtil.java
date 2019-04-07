package com.book.util;

import com.book.entity.Attachment;
import com.book.service.AttachmentService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@Component
public class FileUtil extends AppBase{
    @Autowired
    private ImageUtil imageUtil;

    public static File makeDirectory (String path) {
        File dir = Paths.get(path + "//").toFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        System.out.println("Making directory: "+dir.getAbsolutePath());
        return dir;
    }

    public byte[] readAttachmentAsByteArray (Attachment attachment, int size){
        byte[] bs = null;
        try {
            bs = imageUtil.readImage(attachment, size);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return bs;
    }
}
