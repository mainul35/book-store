package com.book.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Paths;

@Component
public class FileUtil extends AppBase{

    public static File makeDirectory (String path) {
        File dir = Paths.get(path + "//").toFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        System.out.println("Making directory: "+dir.getAbsolutePath());
        return dir;
    }


}
