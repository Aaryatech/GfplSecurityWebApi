package com.ats.gfplsecurity.controller.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.gfplsecurity.common.Firebase;
import com.ats.gfplsecurity.common.Info;
import com.ats.gfplsecurity.model.chat.ChatDetail;
import com.ats.gfplsecurity.model.chat.ChatDisplay;
import com.ats.gfplsecurity.model.chat.ChatEmpToken;
import com.ats.gfplsecurity.model.chat.ChatGroup;
import com.ats.gfplsecurity.model.chat.ChatGroupDisplay;
import com.ats.gfplsecurity.model.chat.ChatHeader;
import com.ats.gfplsecurity.model.chat.ChatHeaderDisplay;
import com.ats.gfplsecurity.repository.chat.ChatDetailRepo;
import com.ats.gfplsecurity.repository.chat.ChatDisplayRepo;
import com.ats.gfplsecurity.repository.chat.ChatEmpTokenRepo;
import com.ats.gfplsecurity.repository.chat.ChatGroupDisplayRepo;
import com.ats.gfplsecurity.repository.chat.ChatGroupRepo;
import com.ats.gfplsecurity.repository.chat.ChatHeaderDisplayRepo;
import com.ats.gfplsecurity.repository.chat.ChatHeaderRepo;
import com.ats.gfplsecurity.repository.chat.ChatMemoDisplayRepo;
import com.ats.gfplsecurity.repository.chat.ChatMemoRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ats.gfplsecurity.model.chat.ChatMemo;
import com.ats.gfplsecurity.model.chat.ChatMemoDisplay;

@RestController
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	ChatGroupRepo chatGroupRepo;

	@Autowired
	ChatGroupDisplayRepo chatGroupDisplayRepo;

	@Autowired
	ChatHeaderRepo chatHeaderRepo;

	@Autowired
	ChatHeaderDisplayRepo chatHeaderDisplayRepo;

	@Autowired
	ChatDetailRepo chatDetailRepo;

	@Autowired
	ChatMemoRepo chatMemoRepo;

	@Autowired
	ChatMemoDisplayRepo chatMemoDisplayRepo;

	@Autowired
	ChatEmpTokenRepo chatEmpTokenRepo;
	
	@Autowired
	ChatDisplayRepo chatDisplayRepo;
	

	// -----------------------CHAT GROUP CRUD------------------------------------

	// --Save new GROUP--
	@PostMapping("/saveChatGroup")
	public ChatGroup saveChatGroup(@RequestBody ChatGroup chatGroup) {
		ChatGroup group = null;

		group = chatGroupRepo.save(chatGroup);

		if (group == null) {
			group = new ChatGroup();
		}

		return group;
	}

	// --Get CHAT GROUP By Id--
	@PostMapping("/getChatGroupById")
	public ChatGroup getChatGroupById(@RequestParam(value = "groupId") int groupId) {
		ChatGroup group = null;
		group = chatGroupRepo.findByGroupId(groupId);

		if (group == null) {
			group = new ChatGroup();
		}

		return group;

	}

	// --Get all CHAT GROUP List--
	@GetMapping("/getAllChatGroup")
	public List<ChatGroup> getAllChatGroup() {

		List<ChatGroup> groupList = null;
		groupList = chatGroupRepo.findAllByDelStatus(1);

		if (groupList == null) {
			groupList = new ArrayList<>();
		}

		return groupList;
	}

	// --Get all CHAT GROUP List--
	@GetMapping("/getAllActiveChatGroup")
	public List<ChatGroup> getAllActiveChatGroup() {

		List<ChatGroup> groupList = null;
		groupList = chatGroupRepo.findAllByDelStatusAndIsActive(1, 1);

		if (groupList == null) {
			groupList = new ArrayList<>();
		}

		return groupList;
	}

	@PostMapping("/getAllChatGroupDisplay")
	public List<ChatGroupDisplay> getAllChatGroupDisplay(@RequestParam(value = "isActive") int isActive) {

		List<ChatGroupDisplay> groupList = null;
		groupList = chatGroupDisplayRepo.getAllChatGroupDisplay(isActive);

		if (groupList == null) {
			groupList = new ArrayList<>();
		}

		return groupList;
	}

	@PostMapping("/deleteChatGroup")
	public Info deleteChatGroup(@RequestParam(value = "groupId") int groupId) {
		Info info = null;

		ChatGroup group = chatGroupRepo.findByGroupId(groupId);

		if (group != null) {
			group.setDelStatus(0);
			chatGroupRepo.save(group);
			info = new Info();
			info.setError(false);
			info.setMessage("Success");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Failed");
		}
		return info;
	}

	@PostMapping("/updateIsActiveChatGroup")
	public Info updateIsActiveChatGroup(@RequestParam(value = "groupId") int groupId,
			@RequestParam(value = "isActive") int isActive) {
		Info info = null;

		ChatGroup group = chatGroupRepo.findByGroupId(groupId);

		if (group != null) {
			group.setIsActive(isActive);
			chatGroupRepo.save(group);
			info = new Info();
			info.setError(false);
			info.setMessage("Success");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Failed");
		}
		return info;
	}

	// -----------------------CHAT HEADER CRUD------------------------------------

	@PostMapping("/saveChatHeader")
	public ChatHeader saveChatGroup(@RequestBody ChatHeader chatHeader) {
		ChatHeader header = null;

		header = chatHeaderRepo.save(chatHeader);

		if (header == null) {
			header = new ChatHeader();
		}

		return header;
	}

	@PostMapping("/getChatHeaderById")
	public ChatHeader getChatHeaderById(@RequestParam(value = "headerId") int headerId) {
		ChatHeader header = null;
		header = chatHeaderRepo.findByHeaderId(headerId);

		if (header == null) {
			header = new ChatHeader();
		}

		return header;

	}

	@GetMapping("/getAllChatHeader")
	public List<ChatHeader> getAllChatHeader() {

		List<ChatHeader> headerList = null;
		headerList = chatHeaderRepo.findAllByDelStatus(1);

		if (headerList == null) {
			headerList = new ArrayList<>();
		}

		return headerList;
	}

	@PostMapping("/getAllChatHeaderDisplay")
	public List<ChatHeaderDisplay> getAllChatHeaderDisplay(@RequestParam(value = "isActive") int isActive) {

		List<ChatHeaderDisplay> headerList = null;
		headerList = chatHeaderDisplayRepo.getAllChatHeaderDisplay(isActive);

		if (headerList == null) {
			headerList = new ArrayList<>();
		}

		return headerList;
	}

	@PostMapping("/deleteChatHeader")
	public Info deleteChatHeader(@RequestParam(value = "headerId") int headerId) {
		Info info = null;

		ChatHeader header = chatHeaderRepo.findByHeaderId(headerId);

		if (header != null) {
			header.setDelStatus(0);
			chatHeaderRepo.save(header);
			info = new Info();
			info.setError(false);
			info.setMessage("Success");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Failed");
		}
		return info;
	}

	@PostMapping("/updateIsActiveChatHeader")
	public Info updateIsActiveChatHeader(@RequestParam(value = "headerId") int headerId,
			@RequestParam(value = "isActive") int isActive) {
		Info info = null;

		ChatHeader header = chatHeaderRepo.findByHeaderId(headerId);

		if (header != null) {
			header.setIsActive(isActive);
			chatHeaderRepo.save(header);
			info = new Info();
			info.setError(false);
			info.setMessage("Success");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Failed");
		}
		return info;
	}

	@PostMapping("/getAllChatHeaderDisplayByUser")
	public List<ChatHeaderDisplay> getAllChatHeaderDisplayByUser(@RequestParam(value = "userId") int userId) {

		List<ChatHeaderDisplay> headerList = null;
		headerList = chatHeaderDisplayRepo.getAllChatHeaderDisplayByUserId(userId);

		if (headerList == null) {
			headerList = new ArrayList<>();
		}

		return headerList;
	}

	// -----------------------CHAT DETAIL CRUD------------------------------------

	@PostMapping("/saveChatDetail")
	public ChatDetail saveChatDetail(@RequestBody ChatDetail chatDetail) {
		ChatDetail detail = null;

		Calendar cal=Calendar.getInstance();
		chatDetail.setServerDate(""+cal.getTimeInMillis());
		
		detail = chatDetailRepo.save(chatDetail);

		if (detail == null) {
			detail = new ChatDetail();
		} else {
			
             String json = "";
             
             ObjectMapper Obj = new ObjectMapper();
             try { 
                 json = Obj.writeValueAsString(detail); 
             }catch(Exception e) {
            	 e.printStackTrace();
             } 
			
			List<ChatEmpToken> empTokenList = chatEmpTokenRepo.getEmpTokenByHeader(detail.getHeaderId());

			if (empTokenList != null) {

				if (empTokenList.size() > 0) {
					for (int i = 0; i < empTokenList.size(); i++) {

						if (empTokenList.get(i).getEmpId() != detail.getUserId()
								&& empTokenList.get(i).getToken() != null) {
							
							

							try {
								Firebase.sendPushNotifForCommunication(empTokenList.get(i).getToken(),empTokenList.get(i).getHeaderName(),json,"chat-"+detail.getHeaderId());
							} catch (IOException e) {
								e.printStackTrace();
							}

						}

					}
				}

			}

		}

		return detail;
	}

	@PostMapping("/getAllChatDetailByHeader")
	public List<ChatDetail> getAllChatDetail(@RequestParam(value = "headerId") int headerId) {
		List<ChatDetail> detailList = new ArrayList<>();

		detailList = chatDetailRepo.findByHeaderId(headerId);

		return detailList;
	}
	
	
	@GetMapping("/getAllChatByHeaderAndLastSync")
	public List<ChatDisplay> getAllChatByHeaderAndLastSync(@RequestParam(value = "headerId") int headerId,@RequestParam(value = "lastSyncId") int lastSyncId,@RequestParam(value = "userId") int userId) {
		List<ChatDisplay> detailList = new ArrayList<>();

		detailList = chatDisplayRepo.getChatByHeaderAndLastSyncId(headerId, lastSyncId, userId);

		return detailList;
	}
	
	
	

	@PostMapping("/getChatDetailById")
	public ChatDetail getChatDetailById(@RequestParam(value = "detailId") int detailId) {
		ChatDetail detail = null;
		detail = chatDetailRepo.findByChatTaskDetailId(detailId);

		if (detail == null) {
			detail = new ChatDetail();
		}

		return detail;

	}

	@PostMapping("/getChatDetailByHeaderId")
	public List<ChatDetail> getChatDetailByHeaderId(@RequestParam(value = "headerId") int headerId) {
		List<ChatDetail> detailList = chatDetailRepo.findByHeaderId(headerId);
		return detailList;

	}

	@PostMapping("/deleteChatDetail")
	public Info deleteChatDetail(@RequestParam(value = "detailId") int detailId) {
		Info info = null;

		ChatDetail detail = chatDetailRepo.findByChatTaskDetailId(detailId);

		if (detail != null) {
			detail.setDelStatus(0);
			chatDetailRepo.save(detail);
			info = new Info();
			info.setError(false);
			info.setMessage("Success");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Failed");
		}
		return info;
	}

	// -----------------------MEMO CRUD------------------------------------

	@PostMapping("/saveChatMemo")
	public ChatMemo saveChatMemo(@RequestBody ChatMemo chatMemo) {
		ChatMemo memo = null;

		memo = chatMemoRepo.save(chatMemo);

		if (memo == null) {
			memo = new ChatMemo();
		}

		return memo;
	}

	@PostMapping("/getChatMemoById")
	public ChatMemo getChatMemoById(@RequestParam(value = "memoId") int memoId) {
		ChatMemo memo = null;
		memo = chatMemoRepo.findByMemoId(memoId);

		if (memo == null) {
			memo = new ChatMemo();
		}

		return memo;
	}

	@GetMapping("/getAllChatMemo")
	public List<ChatMemoDisplay> getAllChatMemo() {
		List<ChatMemoDisplay> memoList = chatMemoDisplayRepo.getAllChatMemoDisplay();
		return memoList;
	}

	@PostMapping("/getChatMemoByGeneratedUserId")
	public List<ChatMemoDisplay> getChatMemoByGeneratedUserId(
			@RequestParam(value = "generatedUserId") int generatedUserId) {
		List<ChatMemoDisplay> memoList = chatMemoDisplayRepo.getChatMemoByGeneratedUserId(generatedUserId);
		return memoList;
	}

	@PostMapping("/getChatMemoByHeaderId")
	public List<ChatMemoDisplay> getChatMemoByHeaderId(@RequestParam(value = "headerId") int headerId) {
		List<ChatMemoDisplay> memoList = chatMemoDisplayRepo.getChatMemoByHeaderId(headerId);
		return memoList;
	}

}
