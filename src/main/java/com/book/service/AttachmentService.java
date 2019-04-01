package com.book.service;

import com.book.entity.Attachment;
import com.book.repository.AttachmentRepository;
import com.book.util.AppBase;
import com.book.util.FileUtil;
import com.book.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.logging.Logger;

@Service
public class AttachmentService extends AppBase {

    @Autowired
    private AttachmentRepository attachmentRepository;

    private Logger logger = Logger.getLogger(AttachmentService.class.getName());

    /**
     * @return Attachment object
     * @param attachment Attachment object details to save in DB
     * @param multipartFile File that has been uploaded and to write to disk
     * @param referenceId Reference ID (Reference of Book or User) which is the owner of this attachment
     * */
    public Attachment save(Attachment attachment, MultipartFile multipartFile, Long referenceId) {
        try {
            // Make directories to save file
            FileUtil.makeDirectory(FILE_STORAGE_BASE_DIR + "\\" + referenceId);
            // Generate uploaded file extension
            String extension = ".jpg";
            StringTokenizer tokenizer = new StringTokenizer(multipartFile.getOriginalFilename(), ".");
            while (tokenizer.hasMoreTokens()) {
                extension = tokenizer.nextToken();
            }
            String url = FILE_STORAGE_BASE_DIR + "\\" + referenceId+ "\\";
            attachment.setDisplayName(Long.toString(attachment.getId()));
            attachment.setPath(url);
            attachment.setType("."+extension);
            File file = File.createTempFile("\\"+attachment.getDisplayName(), attachment.getType());
            multipartFile.transferTo(file);
            ImageUtil.generateThumbs(attachment, file);
            logger.info("File written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        attachmentRepository.save(attachment);
        return attachment;
    }

    public Optional<Attachment> readAttachment(Long id) {
        return attachmentRepository.findById(id);
    }

}
