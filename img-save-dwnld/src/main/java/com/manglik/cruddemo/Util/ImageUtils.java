package com.manglik.cruddemo.Util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils  {
    public static byte[] compressImage(MultipartFile file) throws IOException {
        byte[] data = file.getBytes();
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp =  new byte[4 * 1024];
        while(!deflater.finished()){
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        }
        catch (Exception ex){

        }
        return  outputStream.toByteArray() ;
    }

    public static byte[] decompressImage(byte[] data){
        Inflater inflater = new Inflater();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp =  new byte[4 * 1024];
        try {
            while(!inflater.finished()){
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        }
        catch (Exception ex){

        }
        return outputStream.toByteArray();
    }
}
