package edu.bistu.utils;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class MinioUtils {
    @Autowired
    private MinioClient minioClient;

    @SneakyThrows(Exception.class)
    public boolean bucketExists(String bucketName) {
        return minioClient.bucketExists(
                BucketExistsArgs.builder()
                        .bucket(bucketName)
                        .build()
        );
    }

    @SneakyThrows(Exception.class)
    public void makeBucket(String bucketName) {
        if (!bucketExists(bucketName)) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucketName)
                            .build()
            );
        }
    }

    @SneakyThrows(Exception.class)
    public Optional<Bucket> getBucket(String bucketName) {
        return minioClient.listBuckets().stream()
                .filter(bucket -> bucket.name().equals(bucketName))
                .findFirst();
    }

    @SneakyThrows(Exception.class)
    public void removeBucket(String bucketName) {
        minioClient.removeBucket(
                RemoveBucketArgs.builder()
                        .bucket(bucketName)
                        .build()
        );
    }

    @SneakyThrows(Exception.class)
    public InputStream getObject(String bucketName, String objectName) {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .build()
        );
    }

    /**
     * @param bucketName
     * @param objectName
     * @param fileName:本地文件路径
     * @return
     */
    @SneakyThrows(Exception.class)
    public ObjectWriteResponse putObject(String bucketName, String objectName, String fileName) {
        if (!bucketExists(bucketName)) {
            makeBucket(bucketName);
        }

        return minioClient.uploadObject(
                UploadObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .filename(fileName)
                        .build()
        );
    }

    @SneakyThrows(Exception.class)
    public ObjectWriteResponse putObject(String bucketName, String objectName, InputStream inputStream) {
        if (!bucketExists(bucketName)) {
            makeBucket(bucketName);
        }

        return minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(inputStream, inputStream.available(), -1)
                        .build()
        );
    }

    /**
     * @param bucketName
     * @param fileName
     * @param expires:过期时间,单位为秒,默认7天
     * @return
     */
    @SneakyThrows(Exception.class)
    public String getObjectUrl(String bucketName, String fileName, Integer expires) {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(fileName)
                        .expiry(expires)
                        .build()
        );
    }

    @SneakyThrows(Exception.class)
    public String upload(String bucketName, String fileName, MultipartFile file) {
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .contentType(file.getContentType())
                .stream(file.getInputStream(), file.getSize(), -1)
                .build();
        minioClient.putObject(putObjectArgs);
        return getObjectUrl(bucketName, fileName, (int) TimeUnit.DAYS.toSeconds(7));
    }

    public void download(String bucketName, String fileName, HttpServletResponse httpResponse) {
        GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .build();
        try (GetObjectResponse object = minioClient.getObject(getObjectArgs)) {
            StatObjectResponse stat = minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );
            httpResponse.setCharacterEncoding(CharsetUtil.UTF_8);
            httpResponse.setContentType(stat.contentType());
            httpResponse.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            IoUtil.copy(object, httpResponse.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
