package com.book.util;

import com.book.config.security.permission.AclCheck;
import com.book.config.security.permission.AclException;
import com.book.config.security.permission.Permission;
import com.book.entity.DomainBase;
import com.book.entity.User;
import com.book.entity.UserPermission;
import com.book.repository.UserService;
import com.book.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

public abstract class AppBase {
    public static User LOGGED_IN_USER;
    public static String FILE_STORAGE_BASE_DIR = "C:\\temp\\";
    private Object object;
    @Autowired
    HttpSession httpSession;
    @Autowired
    UserService userService;
    @Autowired
    UserPermissionService userPermissionService;
    public void setInstance(AppBase object) {
        this.object = object;
    }

    public void doAclCheck(String methodName, Class... args) throws AclException {
        Method method = null;
        try {
            method = object.getClass().getMethod(methodName, args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        AclCheck aclCheck = method.getAnnotation(AclCheck.class);
        if (httpSession.getAttribute("username")!=null){
            List<UserPermission> userPermissions = userPermissionService.getPermissionByUsername(httpSession.getAttribute("username").toString());
            boolean hasPermission = false;
            for (UserPermission userPermission : userPermissions) {
                for (Permission permission : aclCheck.permissionNames()) {
                    if (userPermission.getPermission().equals(permission)) {
                        hasPermission = true;
                        break;
                    } else {
                        hasPermission = false;
                    }
                }
            }
            if (!hasPermission) {
                throw new AclException("Not Permitted!");
            }
        }
    }
}
