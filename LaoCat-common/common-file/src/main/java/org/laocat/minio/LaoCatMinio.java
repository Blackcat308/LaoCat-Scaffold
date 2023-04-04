package org.laocat.minio;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteObject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @description: minio 调用类
 * @author: LaoCat
 * @date: 2023/4/3
 */
@Slf4j
@RequiredArgsConstructor
public class LaoCatMinio implements InitializingBean {
    private final String url;
    private final String accessKey;
    private final String secretKey;
    private MinioClient minioClient;

    /**
     * @description: 创建 bucket
     * @author: LaoCat
     * @date: 2023/4/3
     * @returnType: void
     */
    @SneakyThrows
    public void createBucket(String bucket) {
        BucketExistsArgs args = BucketExistsArgs.builder().bucket(bucket).build();
        if (!minioClient.bucketExists(args)) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
    }

    /**
     * @description: 删除 bucket
     * @author: LaoCat
     * @date: 2023/4/3
     * @returnType: void
     */
    @SneakyThrows
    public void deleteBucket(String bucket) {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucket).build());
    }

    /**
     * @description: 获取 bucket 不一定存在
     * @author: LaoCat
     * @date: 2023/4/3
     * @returnType: java.util.Optional<io.minio.messages.Bucket>
     */
    @SneakyThrows
    public Optional<Bucket> bucket(String bucket) {
        return minioClient.listBuckets()
                .stream()
                .filter(bucketLambda -> bucketLambda.name().equals(bucket))
                .findFirst();
    }

    /**
     * @description: 获取 buckets
     * @author: LaoCat
     * @date: 2023/4/3
     * @returnType: java.util.Optional<io.minio.messages.Bucket>
     */
    @SneakyThrows
    public List<Bucket> buckets() {
        return minioClient.listBuckets();
    }


    /***************************************  bucket ↑  |  object ↓  ************************************************/


    /**
     * @description: 上传已知文件大小与文件类型
     * @author: LaoCat
     * @date: 2023/4/3
     * @returnType: void
     */
    @SneakyThrows
    public void upload(String bucket, String object, InputStream in, long fileSize, String contentType) {
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(bucket)
                .object(object)
                .stream(in, fileSize, -1)
                .contentType(contentType)
                .build();
        minioClient.putObject(args);
    }

    /**
     * @description: 删除文件
     * @author: LaoCat
     * @date: 2023/4/3
     * @returnType: void
     */
    @SneakyThrows
    public void deleteObject(String bucket, String object) {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(object).build());
    }

    /**
     * @description: 批量删除文件
     * @author: LaoCat
     * @date: 2023/4/3
     * @returnType: void
     */
    @SneakyThrows
    public void deleteBatchObject(String bucket, List<String> object) {
        List<DeleteObject> objects = new LinkedList<>();
        object.forEach(objectLambda -> {
            objects.add(new DeleteObject(objectLambda));
        });

        minioClient.removeObjects(RemoveObjectsArgs.builder().bucket(bucket).objects(objects).build());
    }

    /**
     * @description: 下载
     * @author: LaoCat
     * @date: 2023/4/3
     * @returnType: void
     */
    @SneakyThrows
    public void downloadObject(String bucket, String object,String fileName) {

        minioClient.downloadObject(DownloadObjectArgs
                .builder()
                .bucket(bucket)
                .object(object)
                .filename(fileName)
                .build());
    }

    /**
     * @description: 获取文件
     * @author: LaoCat
     * @date: 2023/4/3
     * @returnType: void
     */
    @SneakyThrows
    public InputStream getObject(String bucket, String object) {
        return minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(object).build());
    }

    /**
     * @description: 获取文件链接
     * @author: LaoCat
     * @date: 2023/4/3
     * @returnType: void
     */
    @SneakyThrows
    public String getObjectUrl(Method method, String bucket, String object, Integer expiry, TimeUnit timeUnit) {
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .method(method)
                .bucket(bucket)
                .object(object)
                .expiry(expiry, timeUnit)
                .build();

        return minioClient.getPresignedObjectUrl(args);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.minioClient = MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
    }
}
