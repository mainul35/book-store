package com.book.controller.common;

import com.book.entity.Attachment;
import com.book.service.AttachmentService;
import com.book.util.FileUtil;
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
    private FileUtil fileUtil;
    @RequestMapping(value="/image", method= {RequestMethod.GET,RequestMethod.POST} )
    @ResponseBody
    public byte[] readAttachedFile(@RequestParam(name="imgId") Long id, @RequestParam(value = "size", defaultValue = "0") int size) {
        Attachment attachment = attachmentService.readAttachment(id);
        return fileUtil.readAttachmentAsByteArray(attachment, size);
    }
}
