package com.book.controller;

import com.book.entity.Attachment;
import com.book.service.AttachmentService;
import com.book.util.FileUtil;
import com.book.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private ImageUtil imageUtil;

    @RequestMapping(value="/image", method= {RequestMethod.GET,RequestMethod.POST} )
    @ResponseBody
    public byte[] readAttachedFile(@RequestParam(name="imgId") Long id, @RequestParam(value = "size", defaultValue = "0") int size) {
        Attachment attachment = attachmentService.readAttachment(id);
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
