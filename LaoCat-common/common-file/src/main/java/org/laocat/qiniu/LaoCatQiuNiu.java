package org.laocat.qiniu;

import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import java.io.InputStream;

/**
 * @description: @see
 * @author: LaoCat
 * @date: 2023/4/3
 */
@RequiredArgsConstructor
@Slf4j
public class LaoCatQiuNiu implements InitializingBean {
    private final String bucket;
    private final String accessKey;
    private final String secretKey;
    private String uploadToken;
    private UploadManager uploadManager;

    /**
     * @description: 上传
     * @author: LaoCat
     * @date: 2023/4/3
     * @returnType: void
     */
    @SneakyThrows
    public Response upload(String fileName, InputStream in) {
        return uploadManager.put(in, fileName, uploadToken, null, null);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Auth auth = Auth.create(accessKey, secretKey);
        this.uploadToken = auth.uploadToken(bucket);
        this.uploadManager = new UploadManager(new Configuration(Region.region1()));
    }
}
