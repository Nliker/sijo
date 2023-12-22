package com.backend.s3.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.backend.image.model.ImageDto;
import com.backend.image.model.mapper.ImageMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service{
	private final AmazonS3 amazonS3;
	private final ImageMapper imageMapper;
	
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadImage(MultipartFile multipartFile) throws Exception{
        String originalFileName = multipartFile.getOriginalFilename();
        String saveFileName=UUID.randomUUID().toString()+"-"+originalFileName;
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        amazonS3.putObject(bucket, saveFileName, multipartFile.getInputStream(), metadata);
        ImageDto image=new ImageDto(originalFileName,saveFileName);
        imageMapper.insertImage(image);
        return amazonS3.getUrl(bucket, saveFileName).toString();
    }
    public void deleteImage(String saveFileName) throws Exception  {
    	imageMapper.deleteImage(saveFileName);
        amazonS3.deleteObject(bucket, saveFileName);
    }
}
