package com.ats.gfplsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.gfplsecurity.common.Info;
import com.ats.gfplsecurity.model.AlbumEnquiry;
import com.ats.gfplsecurity.model.Purpose;
import com.ats.gfplsecurity.repository.AlbumEnquiryRepo;

@RestController
public class EnquiryController {

	@Autowired AlbumEnquiryRepo albmEnq;
	
	// --Get all AlbumEnquiry--
		@GetMapping("/getAllEnquiry")
		public List<AlbumEnquiry> getAllPurposesWithName() {
			return albmEnq.findByDelStatus(1);
		}
		
		// --Save new AlbumEnquiry--
		@PostMapping("/saveAlbumEnquiry")
		public AlbumEnquiry saveAlbumEnquiry(@RequestBody AlbumEnquiry enq) {
			return albmEnq.save(enq);
		}
		
		// --Get AlbumEnquiry By Id--
		@PostMapping("/getEnquiryById")
		public AlbumEnquiry getEnquiryById(@RequestParam(value = "enqNo") int enqNo) {
			return albmEnq.findByEnquiryNoAndDelStatus(enqNo, 1);
		}
		
		// --deleteAlbumEnquiry--
		@PostMapping("/deleteAlbumEnquiry")
		public Info deleteAlbumEnquiry(@RequestParam(value = "enqNo") int enqNo) {
			Info info = null;

			AlbumEnquiry albm = albmEnq.findByEnquiryNo(enqNo);

			if (albm != null) {
				albm.setDelStatus(0);
				AlbumEnquiry updatedAlbmEnq = albmEnq.save(albm);
				info = new Info();
				info.setError(false);
				info.setMessage("AlbumEnquiry Deleted Successfully");
			} else {
				info = new Info();
				info.setError(true);
				info.setMessage("AlbumEnquiry Deletion Failed");
			}
			return info;
		}
		
		// --Update AlbumId--
				@PostMapping("/updateAlbumId")
				public Info saveAlbumEnquiry(@RequestParam(value = "enqId") int enqId, @RequestParam(value = "albmId") int albmId) {
				Info info = new Info();
					int updatedAlbmId = albmEnq.updtAlbmIdByEnqId(enqId, albmId);
					
					if(updatedAlbmId!=0) {
						info.setError(false);
						info.setMessage("AlbumEnquiry Updated Successfully");
					} else {
						info = new Info();
						info.setError(true);
						info.setMessage("AlbumEnquiry Update Failed");
					}						
					
					return info;
				}
}
