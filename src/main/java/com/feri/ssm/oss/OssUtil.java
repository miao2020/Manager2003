package com.feri.ssm.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import sun.misc.Cache;

import javax.xml.crypto.Data;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @program: Manager2003
 * @description: 基于阿里云的-对象存储OSS实现资源操作
 * @author: Feri(邢朋辉)
 * @create: 2020-09-04 10:42
 */
public class OssUtil {

    // Endpoint
    private static final String endpoint = "oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
    private static final String accessKeyId = "LTAI4GGEDdAHoscQnVrZk3Ta";
    private static final String accessKeySecret = "U7l8jYLfl4ALq1xTySDa8x4RoATUjF";
    //存储空间名称
    private static final String bucketName = "java-2003";
    //设置回调地址 可以监听上传是否成功
    private static final String callbackUrl="";

    /**
     * 实现字符串的上传
     * @param data 上传的内容
     * @param objname 对象名称
     * @return 访问路径*/
    public static String upload(String objname,byte[] data){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（objName）。

        ossClient.putObject(bucketName, objname, new ByteArrayInputStream(data));
        /**
         *generatePresignedUrl 生成访问的链接地址
         * 1.存储空间名称
         * 2.对象名称 文件名
         * 3.有效期*/
        String url=ossClient.generatePresignedUrl(bucketName,objname,getDate(3)).toString();
        // 关闭OSSClient。
        ossClient.shutdown();
        return url;
    }
    /**
     * 实现字符串的上传
     * @param content 字符串的内容
     * @param objname 对象名称
     * @return 访问路径*/
    public static String upload(String objname,String content){
        return upload(objname,content.getBytes());
    }

    //获取指定年后的日期
    private static Date getDate(int years){
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.YEAR,years);
        return calendar.getTime();
    }
    //实现断点续传
    public static String uploadFile(String objname,String filepath) throws Throwable {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ObjectMetadata meta = new ObjectMetadata();
        //指定上传的内容类型。
        meta.setContentType("text/plain");
        // 通过UploadFileRequest设置多个参数。
        UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName,objname);
        // 指定上传的本地文件。
        uploadFileRequest.setUploadFile(filepath);
        // 指定上传并发线程数，默认为1。
        uploadFileRequest.setTaskNum(5);
        // 指定上传的分片大小。
        uploadFileRequest.setPartSize(1 * 1024 * 1024);
        // 开启断点续传，默认关闭。
        uploadFileRequest.setEnableCheckpoint(true);
        // 记录本地分片上传结果的文件。
        uploadFileRequest.setCheckpointFile("ossupload.txt");
        // 文件的元数据。
        uploadFileRequest.setObjectMetadata(meta);
        // 设置上传成功回调，参数为Callback类型。
        uploadFileRequest.setCallback(new Callback());

        // 断点续传上传。
        ossClient.uploadFile(uploadFileRequest);
        String url=createURL(objname,ossClient);
        // 关闭OSSClient。
        ossClient.shutdown();
        return url;
    }
    private static String createURL(String objname, OSS client){
        return client.generatePresignedUrl(bucketName,objname,getDate(3)).toString();
    }
    public static void callback(String objname,byte[] data) throws IOException {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objname,new ByteArrayInputStream(data));

// 上传回调参数。
        Callback callback = new Callback();
        callback.setCallbackUrl(callbackUrl);
//（可选）设置回调请求消息头中Host的值，即您的服务器配置Host的值。
// callback.setCallbackHost("yourCallbackHost");
// 设置发起回调时请求body的值。
        callback.setCallbackBody("{\\\"mimeType\\\":${mimeType},\\\"size\\\":${size}}");
// 设置发起回调请求的Content-Type。
        callback.setCalbackBodyType(Callback.CalbackBodyType.JSON);
// 设置发起回调请求的自定义参数，由Key和Value组成，Key必须以x:开始。
        callback.addCallbackVar("x:var1", "value1");
        callback.addCallbackVar("x:var2", "value2");
        putObjectRequest.setCallback(callback);

        PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);

// 读取上传回调返回的消息内容。
        byte[] buffer = new byte[1024];
        putObjectResult.getResponse().getContent().read(buffer);
// 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
        putObjectResult.getResponse().getContent().close();

// 关闭OSSClient。
        ossClient.shutdown();
    }

    public static String rename(String file){
        if(file.length()>30){
            file=file.substring(file.length()-30);
        }
        return UUID.randomUUID().toString().replaceAll("-","")+"_"+file;
    }
}
