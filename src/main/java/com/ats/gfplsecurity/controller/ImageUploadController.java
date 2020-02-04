package com.ats.gfplsecurity.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ats.gfplsecurity.common.Info;

import javassist.bytecode.stackmap.BasicBlock.Catch;

@RestController
public class ImageUploadController {

	
	private static String DOC_URL = "/opt/tomcat-latest/webapps/uploads/APP/";

	 private static String CHAT_URL= "/opt/tomcat-latest/webapps/uploads/APP/CHAT/";
	//private static String CHAT_URL = "C:/Users/MAXADMIN/Desktop/chat/";
	 
	 public static final String ALBUM_FOLDER = "/opt/tomcat-latest/webapps/uploads/ALBUM/";



	@RequestMapping(value = { "/photoUpload" }, method = RequestMethod.POST)
	public @ResponseBody Info getFarmerContract(@RequestParam("file") MultipartFile[] uploadfile,
			@RequestParam("imageName") List<String> imageName, @RequestParam("type") String type) {
		System.err.println(" no  of files to push " + uploadfile.length);
		Info info = new Info();

		// System.out.println("File Name " + imageName.toString());

		try {

			saveUploadedFiles(uploadfile, imageName, type);

			info.setError(false);
			info.setMessage("File uploaded successfully");

		} catch (IOException e) {

			e.printStackTrace();
			info.setError(true);
			info.setMessage("File upload failed");
		}

		return info;
	}

	// save file
	private void saveUploadedFiles(MultipartFile[] files, List<String> imageName, String type) throws IOException {

		
		try {
			for (int i = 0; i < files.length; i++) {
				Path path = null;

				if (type.equalsIgnoreCase("1")) {

					String name = imageName.get(i).substring(1, imageName.get(i).length() - 1);
					path = Paths.get(DOC_URL + name);
					
				} else if (type.equalsIgnoreCase("chat")) {

					String name = imageName.get(i).substring(1, imageName.get(i).length() - 1);
					path = Paths.get(CHAT_URL + name);
					
				}else if (type.equalsIgnoreCase("album")) {

					String name = imageName.get(i).substring(1, imageName.get(i).length() - 1);
					path = Paths.get(ALBUM_FOLDER + name);
					
				}
				
				byte[] bytes = files[i].getBytes();
				Files.write(path, bytes);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
