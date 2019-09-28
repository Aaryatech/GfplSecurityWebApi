package com.ats.gfplsecurity.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.boot.archive.spi.ArchiveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.gfplsecurity.common.Info;
import com.ats.gfplsecurity.model.VisitorMaster;
import com.ats.gfplsecurity.repository.VisitorMasterRepository;

@RestController
public class VisitorRestController {

	@Autowired
	VisitorMasterRepository visitorMasterRepository;

	@PostMapping("/saveVisitor")
	public @ResponseBody VisitorMaster saveVisitor(@RequestBody VisitorMaster visitor) {

		VisitorMaster save = new VisitorMaster();

		try {

			save = visitorMasterRepository.save(visitor);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return save;

	}

	@PostMapping("/getVisitorList")
	public @ResponseBody List<VisitorMaster> getVisitorList(@RequestBody VisitorMaster visitor) {

		List<VisitorMaster> list = new ArrayList<>();

		try {

			list = visitorMasterRepository.findByDelStatus(1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	@PostMapping("/deleteVistor")
	public @ResponseBody Info deleteVistor(@RequestParam("visitorId") int visitorId) {

		Info info = new Info();

		try {

			int delete = visitorMasterRepository.deleteVistor(visitorId);

			if (delete > 0) {
				info.setError(false);
				info.setMessage("deleted");
			} else {
				info.setError(true);
				info.setMessage("failed to deleted");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return info;

	}
	
	@PostMapping("/getVisitorById")
	public @ResponseBody VisitorMaster getVisitorById(@RequestParam("visitorId") int visitorId) {

		VisitorMaster visitorMaster = new VisitorMaster();

		try {

			visitorMaster = visitorMasterRepository.findByVisitorIdAndDelStatus(visitorId,1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return visitorMaster;

	}
}
