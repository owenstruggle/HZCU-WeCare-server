package cn.owem.wecare.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author Owem
 * @date 2022/12/16 05:22
 * @description TODO
 **/
@Service
public class UploadUtil {
    @Value("${userSetting.localSrc}")
    private String localSrc;

    public HashMap<String, String> uploadMedia(MultipartFile uploadFile, HttpServletRequest req, String src) {
        //获取文件名
        String fileName = uploadFile.getOriginalFilename();
        //获取文件后缀名
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = UUID.randomUUID() + suffixName;
        //指定本地文件夹存储图片
        String filePath = localSrc + src;
        File file = new File(filePath, fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        HashMap<String, String> ssMap = new HashMap<>();
        try {
            file.createNewFile();
            uploadFile.transferTo(new File(filePath + fileName));
            ssMap.put("url", "https://" + req.getRemoteHost() + ":" + req.getServerPort() + "/share/" + src + fileName);
            ssMap.put("fileName", fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ssMap;
    }

}
