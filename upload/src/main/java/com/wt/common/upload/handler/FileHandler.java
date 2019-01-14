package com.wt.common.upload.handler;

import com.wt.common.core.exception.BaseErrorException;
import com.wt.common.upload.exception.FilePathCheckException;
import com.wt.common.upload.exception.FileReadException;
import com.wt.common.upload.exception.FileStreamCloseException;
import com.wt.common.upload.exception.UploadException;
import com.wt.common.upload.model.UploadFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.upload.handler
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/6 下午2:59
 * @Version: v1.0
 */
@Component
public class FileHandler {
    Logger logger = LoggerFactory.getLogger(FileHandler.class);

    @Value("${uploadPath}")
    private String uploadPath;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /**
     * 保存文件到文件系统
     * @param fileBytes
     * @return 文件
     */
    public UploadFile writeFileSystem(byte[] fileBytes, String suffix) throws UploadException {
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            String path = "/"+sdf.format(new Date());
            String fileSystemPath = uploadPath+path;
            checkPathExsits(fileSystemPath);
            String fileName = generateFileName()+"."+suffix;
            String filePath = path+"/"+fileName;
//            String fileSystemPath = uploadPath+filePath;
            fileOutputStream = new FileOutputStream(fileSystemPath+"/"+fileName);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 20240);
            bufferedOutputStream.write(fileBytes);
            bufferedOutputStream.flush();

            UploadFile uploadFile = new UploadFile();

            uploadFile.setFileName(fileName);
            uploadFile.setLocation(filePath);
            return uploadFile;
        } catch (IOException e) {
            throw new BaseErrorException("文件读取异常", e);
        } finally {
            try {
                bufferedOutputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                throw new BaseErrorException("文件流关闭异常", e);
            }
        }
    }

    /**
     * 从文件系统读取文件
     * @param path
     * @return
     */
    public byte[] readFileFromSystem(String path) throws UploadException {
        logger.debug("开始读取文件："+path);
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] result = null;
        try {
            fileInputStream = new FileInputStream(path);
            bufferedInputStream = new BufferedInputStream(fileInputStream,1048576);

            int position = bufferedInputStream.read();

            byte[] bytes = new byte[1024];

            while((position=bufferedInputStream.read(bytes))!=-1){
                byteArrayOutputStream.write(bytes,0,position);
            }
            result = byteArrayOutputStream.toByteArray();
            return result;
        } catch (IOException e) {
            throw new FileReadException(e);
        } finally {
            try {
                byteArrayOutputStream.close();
                bufferedInputStream.close();
                fileInputStream.close();
            } catch (IOException e) {
                throw new FileStreamCloseException(e);
            }

        }
    }

    /**
     * 生成文件名称
     * @return
     */
    private String generateFileName(){
        String date = sdf1.format(new Date());
        return date;
    }

    public static void main(String[] args) throws UploadException {
        FileHandler fileHandler = new FileHandler();
        byte[] bytes = fileHandler.readFileFromSystem("/Users/lichking2015/Downloads/架构实战——软件架构设计的过程.pdf");
//        fileHandler.writeFileSystem(bytes);
    }



    public void checkPathExsits(String path) throws FilePathCheckException {
        File file = new File(path);
        if(file.exists()){
            if(!file.isDirectory()){
                throw new FilePathCheckException();
            }
        }else{
            logger.debug("文件目录：{1}不存在，程序自动创建",path);
            file.mkdir();
        }
    }
}
