package com.ats.gfplsecurity.controller.chat;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.ats.gfplsecurity.model.Employee;
import com.ats.gfplsecurity.model.chat.ChatDetail;
import com.ats.gfplsecurity.model.chat.ChatDetailIdsByReadStatus;
import com.ats.gfplsecurity.model.chat.ChatDisplay;
import com.ats.gfplsecurity.model.chat.ChatEmpToken;
import com.ats.gfplsecurity.model.chat.ChatGroup;
import com.ats.gfplsecurity.model.chat.ChatGroupDisplay;
import com.ats.gfplsecurity.model.chat.ChatHeader;
import com.ats.gfplsecurity.model.chat.ChatHeaderDisplay;
import com.ats.gfplsecurity.model.chat.ChatHeaderEmpList;
import com.ats.gfplsecurity.repository.EmployeeRepository;
import com.ats.gfplsecurity.repository.chat.ChatDetailIdsByReadStatusRepo;
import com.ats.gfplsecurity.repository.chat.ChatDetailRepo;
import com.ats.gfplsecurity.repository.chat.ChatDisplayRepo;
import com.ats.gfplsecurity.repository.chat.ChatEmpTokenRepo;
import com.ats.gfplsecurity.repository.chat.ChatGroupDisplayRepo;
import com.ats.gfplsecurity.repository.chat.ChatGroupRepo;
import com.ats.gfplsecurity.repository.chat.ChatHeaderDisplayRepo;
import com.ats.gfplsecurity.repository.chat.ChatHeaderEmpListRepo;
import com.ats.gfplsecurity.repository.chat.ChatHeaderRepo;
import com.ats.gfplsecurity.repository.chat.ChatMemoDisplayRepo;
import com.ats.gfplsecurity.repository.chat.ChatMemoRepo;
import com.ats.gfplsecurity.repository.chat.MemoGeneratedRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ats.gfplsecurity.model.chat.ChatMemo;
import com.ats.gfplsecurity.model.chat.ChatMemoDisplay;
import com.ats.gfplsecurity.model.chat.MemoGenerated;

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

	@Autowired
	ChatHeaderEmpListRepo chatHeaderEmpListRepo;

	@Autowired
	MemoGeneratedRepo memoGeneratedRepo;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ChatDetailIdsByReadStatusRepo chatDetailIdsByReadStatusRepo;

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

	@PostMapping("/getAllChatGroupDisplayByUser")
	public List<ChatGroupDisplay> getAllChatGroupDisplayByUser(@RequestParam(value = "userId") int userId) {

		List<ChatGroupDisplay> groupList = null;
		groupList = chatGroupDisplayRepo.getAllChatGroupDisplayByUser(userId);

		if (groupList == null) {
			groupList = new ArrayList<>();
		}

		return groupList;
	}

	@PostMapping("/getAllChatGroupDisplayMasterByUser")
	public List<ChatGroupDisplay> getAllChatGroupDisplayMasterByUser(@RequestParam(value = "userId") int userId) {

		List<ChatGroupDisplay> groupList = null;
		groupList = chatGroupDisplayRepo.getAllChatGroupDisplayMasterByUser(userId);

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
		} else {

			Employee createdByEmp = employeeRepository.findByEmpIdAndDelStatus(header.getCreatedUserId(), 1);
			String createdBy = createdByEmp.getEmpFname() + " " + createdByEmp.getEmpMname() + " "
					+ createdByEmp.getEmpSname();

			Calendar cal = Calendar.getInstance();
			String date = String.valueOf(cal.getTimeInMillis());

			/*
			 * ChatDetail chat = new ChatDetail(0, header.getHeaderId(), 1, "Task created",
			 * date, date, header.getCreatedUserId(), createdBy, 1, 1, 0, 0, 0, "", "", "");
			 */

			ChatDetail chat = new ChatDetail();
			chat.setChatTaskDetailId(0);
			chat.setHeaderId(header.getHeaderId());
			chat.setTypeOfText(1);
			chat.setTextValue("Task Created");
			chat.setLocalDate(date);
			chat.setServerDate(date);
			chat.setUserId(header.getCreatedUserId());
			chat.setUserName(createdBy);
			chat.setDelStatus(1);
			chat.setMarkAsRead(1);
			chat.setExInt1(0);
			chat.setExInt2(0);
			chat.setExInt3(0);
			chat.setExVar1("" + header.getCreatedUserId());
			chat.setExVar2("");
			chat.setExVar3("");

			saveChatDetailNotifyToAll(chat);

			String json = "";

			ObjectMapper Obj = new ObjectMapper();
			try {
				json = Obj.writeValueAsString(header);
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.err.println("CHAT HEADER SAVED --------------------------------- " + header);

			List<ChatEmpToken> empTokenList = chatEmpTokenRepo.getEmpTokenByHeader(header.getHeaderId());

			if (empTokenList != null) {

				if (empTokenList.size() > 0) {
					for (int i = 0; i < empTokenList.size(); i++) {

						if (empTokenList.get(i).getEmpId() != header.getCreatedUserId()
								&& empTokenList.get(i).getToken() != null) {

							try {
								Firebase.sendPushNotifForCommunication(empTokenList.get(i).getToken(),
										empTokenList.get(i).getHeaderName(), json, "header");
							} catch (IOException e) {
								e.printStackTrace();
							}

						}

					}
				}

			}

		}

		return header;
	}

	@PostMapping("/editChatHeader")
	public ChatHeader editChatGroup(@RequestBody ChatHeader chatHeader) {
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

	@PostMapping("/getAllChatHeaderDisplayByUserAndGroup")
	public List<ChatHeaderDisplay> getAllChatHeaderDisplayByUserAndGroup(@RequestParam(value = "userId") int userId,
			@RequestParam(value = "groupId") int groupId) {

		List<ChatHeaderDisplay> headerList = null;
		headerList = chatHeaderDisplayRepo.getAllChatHeaderDisplayByUserIdAndGroup(userId, groupId);

		if (headerList == null) {
			headerList = new ArrayList<>();
		}

		return headerList;
	}

	@PostMapping("/getAllClosedChatHeaderDisplay")
	public List<ChatHeaderDisplay> getAllClosedChatHeaderDisplay(@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate) {

		List<ChatHeaderDisplay> headerList = null;
		headerList = chatHeaderDisplayRepo.getAllClosedChatHeaderDisplay(fromDate, toDate);

		if (headerList == null) {
			headerList = new ArrayList<>();
		}

		return headerList;
	}

	@PostMapping("/updateChatHeaderCloseRequest")
	public Info updateChatHeaderCloseRequest(@RequestParam(value = "headerId") int headerId,
			@RequestParam(value = "status") int status, @RequestParam(value = "empId") int empId) {
		Info info = null;

		int res = chatHeaderRepo.updateCloseRequest(headerId, status, empId);
		if (res > 0) {

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

	@PostMapping("/updateChatHeaderClose")
	public Info updateChatHeaderCloseRequest(@RequestParam(value = "headerId") int headerId,
			@RequestParam(value = "status") int status, @RequestParam(value = "empId") int empId,
			@RequestParam(value = "remark") String remark) {
		Info info = null;

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String date = sdf.format(cal.getTimeInMillis());

		int res = chatHeaderRepo.updateChatClose(headerId, status, empId, remark, date);
		if (res > 0) {

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

	// -----------------------CHAT DETAIL CRUD------------------------------------

	@PostMapping("/saveChatDetail")
	public ChatDetail saveChatDetail(@RequestBody ChatDetail chatDetail) {
		ChatDetail detail = null;

		Calendar cal = Calendar.getInstance();
		chatDetail.setServerDate("" + cal.getTimeInMillis());

		detail = chatDetailRepo.save(chatDetail);
		System.err.println("CHAT SAVED --------------------------------------------------------------- " + detail);

		if (detail == null) {
			detail = new ChatDetail();
		} else {

			String json = "";

			ObjectMapper Obj = new ObjectMapper();
			try {
				json = Obj.writeValueAsString(detail);
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.err.println(
					"CHAT SAVED ------------------------------11111--------------------------------- " + detail);

			List<ChatEmpToken> empTokenList = chatEmpTokenRepo.getEmpTokenByHeader(detail.getHeaderId());

			if (empTokenList != null) {

				if (empTokenList.size() > 0) {
					for (int i = 0; i < empTokenList.size(); i++) {

						if (empTokenList.get(i).getEmpId() != detail.getUserId()
								&& empTokenList.get(i).getToken() != null) {

							try {
								Firebase.sendPushNotifForCommunication(empTokenList.get(i).getToken(),
										empTokenList.get(i).getHeaderName(), json, "chat");
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
	
	
	@PostMapping("/saveChatDetailNotifyToAll")
	public ChatDetail saveChatDetailNotifyToAll(@RequestBody ChatDetail chatDetail) {
		ChatDetail detail = null;

		Calendar cal = Calendar.getInstance();
		chatDetail.setServerDate("" + cal.getTimeInMillis());

		detail = chatDetailRepo.save(chatDetail);
		System.err.println("CHAT SAVED --------------------------------------------------------------- " + detail);

		if (detail == null) {
			detail = new ChatDetail();
		} else {

			String json = "";

			ObjectMapper Obj = new ObjectMapper();
			try {
				json = Obj.writeValueAsString(detail);
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.err.println(
					"CHAT SAVED ------------------------------11111--------------------------------- " + detail);

			List<ChatEmpToken> empTokenList = chatEmpTokenRepo.getEmpTokenByHeader(detail.getHeaderId());

			if (empTokenList != null) {

				if (empTokenList.size() > 0) {
					for (int i = 0; i < empTokenList.size(); i++) {

						if (empTokenList.get(i).getToken() != null) {

							try {
								Firebase.sendPushNotifForCommunication(empTokenList.get(i).getToken(),
										empTokenList.get(i).getHeaderName(), json, "chat");
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
	

	@PostMapping("/saveChatDetailWithoutNotify")
	public List<ChatDetail> saveChatDetailWithoutNotify(@RequestBody ArrayList<ChatDetail> chatDetailList) {
		System.err.println("SAVE CHAT DETAIL- -------------------- " + chatDetailList);

		List<ChatDetail> chatList = null;

		Calendar cal = Calendar.getInstance();

		System.err.println("SAVE CHAT DETAIL- -------------------- " + chatDetailList);

		if (chatDetailList.size() > 0) {

			for (int i = 0; i < chatDetailList.size(); i++) {
				chatDetailList.get(i).setServerDate("" + cal.getTimeInMillis());
			}

			chatList = chatDetailRepo.saveAll(chatDetailList);
			System.err
					.println("CHAT SAVED --------------------------------------------------------------- " + chatList);

		}

		return chatList;
	}

	@PostMapping("/getAllChatDetailByHeader")
	public List<ChatDetail> getAllChatDetail(@RequestParam(value = "headerId") int headerId) {
		List<ChatDetail> detailList = new ArrayList<>();

		detailList = chatDetailRepo.findByHeaderId(headerId);

		return detailList;
	}

	@GetMapping("/getAllChatByHeaderAndLastSync")
	public List<ChatDisplay> getAllChatByHeaderAndLastSync(@RequestParam(value = "headerId") int headerId,
			@RequestParam(value = "lastSyncId") int lastSyncId, @RequestParam(value = "userId") int userId) {
		List<ChatDisplay> detailList = new ArrayList<>();

		detailList = chatDisplayRepo.getChatByHeaderAndLastSyncId(headerId, lastSyncId, userId);

		return detailList;
	}

	@PostMapping("/getAllChatByLastSyncAndUserId")
	public List<ChatDisplay> getAllChatByHeaderAndLastSync(@RequestParam(value = "lastSyncId") int lastSyncId,
			@RequestParam(value = "userId") int userId) {
		List<ChatDisplay> detailList = new ArrayList<>();

		detailList = chatDisplayRepo.getChatByLastSyncIdAndUserId(lastSyncId, userId);

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

	@PostMapping("/getAllChatDetailForUpdateReadStatus")
	public List<ChatDetail> getAllChatDetailForUpdateReadStatus() {
		List<ChatDetail> detailList = new ArrayList<>();

		detailList = chatDetailRepo.findAllByDelStatus(1);

		return detailList;
	}

	// -----------------------MEMO CRUD------------------------------------

	@PostMapping("/saveChatMemo")
	public ChatMemo saveChatMemo(@RequestBody ChatMemo chatMemo) {
		ChatMemo memo = null;

		memo = chatMemoRepo.save(chatMemo);

		if (memo != null) {

			Employee memoToEmp = employeeRepository.findByEmpIdAndDelStatus(memo.getUserId(), 1);
			Employee memoByEmp = employeeRepository.findByEmpIdAndDelStatus(memo.getGeneratedUserId(), 1);
			ChatHeader header = chatHeaderRepo.findByHeaderId(memo.getTaskHeaderId());

			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

			try {

				java.util.Date d = sdf1.parse(memo.getMemoDate());
				String memoDate = sdf2.format(d.getTime());

				if (memoToEmp.getExVar1() != null) {

					Firebase.sendPushNotifForCommunication(memoToEmp.getExVar3(), "Memo Generated",
							"You have received memo for the task " + header.getHeaderName() + " on " + memoDate + " by "
									+ memoByEmp.getEmpFname() + " " + memoByEmp.getEmpMname() + " "
									+ memoByEmp.getEmpSname(),
							"memo");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

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

	@PostMapping("/getChatEmpListByHeader")
	public List<ChatHeaderEmpList> getChatEmpListByHeader(@RequestParam(value = "headerId") int headerId) {
		List<ChatHeaderEmpList> empList = chatHeaderEmpListRepo.getChatHeaderEmps(headerId);
		return empList;
	}

	@PostMapping("/getMemoBetweenDate")
	public List<MemoGenerated> getMemoBetweenDate(@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate) {
		List<MemoGenerated> memoList = memoGeneratedRepo.getAllMemoGenerated(fromDate, toDate);
		return memoList;
	}

	@PostMapping("/getMemoByDateAndEmpId")
	public List<MemoGenerated> getMemoByDateAndEmpId(@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate, @RequestParam(value = "empId") int empId) {
		List<MemoGenerated> memoList = memoGeneratedRepo.getAllMemoGeneratedByDateAndEmpId(fromDate, toDate, empId);
		return memoList;
	}

	@PostMapping("/getMemoByEmpId")
	public List<MemoGenerated> getMemoByEmpId(@RequestParam(value = "empId") int empId) {
		List<MemoGenerated> memoList = memoGeneratedRepo.getAllMemoGeneratedByEmpId(empId);
		return memoList;
	}

	// ----------------------CHAT READ ---------------------------------

	@PostMapping("/saveUserIdChatRead")
	public Info saveUserIdChatRead(@RequestBody ArrayList<ChatDetail> chatDetailList,
			@RequestParam(value = "userId") int userId) {

		Info info = new Info();
		info.setError(false);
		info.setMessage("success");

		System.err.println("SAVE CHAT DETAIL- -------------------- " + chatDetailList);

		if (chatDetailList.size() > 0) {

			for (int i = 0; i < chatDetailList.size(); i++) {

				int res = chatDetailRepo.updateUserIdAfterChatRead(userId, chatDetailList.get(i).getChatTaskDetailId());

				// int res = chatDetailRepo.updateUserIdAfterChatRead(1, 1);
				System.err.println("RESULT ------------------ " + res);

				if (res > 0) {

					System.err.println("CHAT ADD USER ID-------------------- " + res);

					int ans = chatDetailRepo.updateChatReadStatus(chatDetailList.get(i).getChatTaskDetailId());
					if (ans > 0) {

						List<ChatEmpToken> empTokenList = chatEmpTokenRepo
								.getEmpTokenByHeader(chatDetailList.get(i).getHeaderId());

						if (empTokenList != null) {

							if (empTokenList.size() > 0) {

								List<String> tokenList = new ArrayList<>();

								for (int j = 0; j < empTokenList.size(); j++) {
									tokenList.add(empTokenList.get(j).getToken());
								}

								new Firebase().send_FCM_NotificationList(tokenList,""+chatDetailList.get(i).getChatTaskDetailId(),"readTag",""+chatDetailList.get(i).getChatTaskDetailId());

							}

						}

					}

				}

			}
		}

		return info;
	}

	@PostMapping("/getChatDetailIdsByReadStatus")
	public List<ChatDetailIdsByReadStatus> getChatDetailIdsByReadStatus(
			@RequestParam(value = "readStatus") int readStatus) {
		List<ChatDetailIdsByReadStatus> idList = chatDetailIdsByReadStatusRepo.getChatDetailIdsByMarkStatus(readStatus);
		System.err.println("ID LIST------------------------- " + idList);
		return idList;
	}

}
