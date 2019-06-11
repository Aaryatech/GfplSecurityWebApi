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

	//private static String DOC_URL = "/opt/tomcat-latest/webapps/uploads/SUGGESTION/";
	
	//private static String DOC_URL="/home/lenovo/Documents/RtoUpload/";
	//private static String DOC_URL=	"/home/aaryate1/tomcat.aaryatechindia.in/tomcat-8.0.18/webapps/rtodocupload/";
	private static String DOC_URL=	"/opt/tomcat-latest/webapps/uploads/APP/";
	
	//private static String BILL_FOLDER ="/home/maxadmin/Desktop/photos/";
	
	@RequestMapping(value = { "/photoUpload" }, method = RequestMethod.POST)
	public @ResponseBody Info getFarmerContract(@RequestParam("file") MultipartFile[] uploadfile , @RequestParam("imageName") List<String> imageName,@RequestParam("type") String type) {
System.err.println(" no  of files to push " +uploadfile.length);
		Info info = new Info();

		//System.out.println("File Name " + imageName.toString());

		try {

			saveUploadedFiles(uploadfile ,  imageName,type);

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
	private void saveUploadedFiles(MultipartFile[] files,  List<String> imageName,String type) throws IOException {

		/*try {
		for (MultipartFile file : files) {
			Path path=null;
			if (file.isEmpty()) {
				continue; 
			}
            if(type.equalsIgnoreCase("1"))
            {
			 path =Paths.get(DOC_URL + imageName);
            }

            
			byte[] bytes = file.getBytes();
			
			Files.write(path, bytes);

		}

	
	}*/
		
		try {
			for (int i=0;i<files.length;i++) {
				Path path=null;
				
	            if(type.equalsIgnoreCase("1"))
	            {
	            	
	            String name=imageName.get(i).substring(1, imageName.get(i).length()-1);
	            
				 path =Paths.get(DOC_URL + name);
	            }

	            
				byte[] bytes = files[i].getBytes();
				
				Files.write(path, bytes);

			}

		
		}
	catch(Exception e)
	{
		e.printStackTrace();
	}

}
}
