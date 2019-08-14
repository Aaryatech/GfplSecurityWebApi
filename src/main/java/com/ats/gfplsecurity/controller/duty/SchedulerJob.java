package com.ats.gfplsecurity.controller.duty;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ats.gfplsecurity.common.Firebase;
import com.ats.gfplsecurity.model.Employee;
import com.ats.gfplsecurity.model.duty.AssignDuty;
import com.ats.gfplsecurity.model.duty.DutyHeader;
import com.ats.gfplsecurity.model.duty.Shift;
import com.ats.gfplsecurity.model.duty.TaskDetail;
import com.ats.gfplsecurity.model.duty.TaskDoneDetail;
import com.ats.gfplsecurity.model.duty.TaskDoneHeader;
import com.ats.gfplsecurity.model.duty.TaskNotification;
import com.ats.gfplsecurity.repository.EmployeeRepository;
import com.ats.gfplsecurity.repository.duty.AssignDutyRepo;
import com.ats.gfplsecurity.repository.duty.DutyHeaderRepo;
import com.ats.gfplsecurity.repository.duty.ShiftRepo;
import com.ats.gfplsecurity.repository.duty.TaskDetailRepo;
import com.ats.gfplsecurity.repository.duty.TaskDoneDetailRepo;
import com.ats.gfplsecurity.repository.duty.TaskDoneHeaderRepo;
import com.ats.gfplsecurity.repository.duty.TaskNotificationRepo;

@Component
public class SchedulerJob {

	@Autowired
	AssignDutyRepo assignDutyRepo;

	@Autowired
	DutyHeaderRepo dutyHeaderRepo;

	@Autowired
	TaskDoneHeaderRepo taskDoneHeaderRepo;

	@Autowired
	TaskDoneDetailRepo taskDoneDetailRepo;

	@Autowired
	TaskDetailRepo taskDetailRepo;

	@Autowired
	ShiftRepo shiftRepo;

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	TaskNotificationRepo taskNotificationRepo;
	

	private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	// ------------------------------------------------------------------

	// second, minute, hour, day of month, month, day(s) of week

	@Scheduled(cron = "1 * * * * *")
	public void getAllTaskDoneDetailList() {

		String strTime = timeFormat.format(new Date());
		String strDate = dateFormat.format(new Date());

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

		System.out.println(
				" CRON JOB ----------------------------------------------------------------------------------------------------  "
						+ sdf.format(Calendar.getInstance().getTimeInMillis()));

		List<AssignDuty> assignDutyList = null;

		try {

			int todaysDayCount = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
			int todaysDateCount = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

			assignDutyList = assignDutyRepo.findAllByDelStatusAndExInt1(1, 1);

			if (assignDutyList != null) {

				for (int i = 0; i < assignDutyList.size(); i++) {

					DutyHeader duty = dutyHeaderRepo.findByDutyId(assignDutyList.get(i).getDutyId());
					Shift shift = shiftRepo.findByShiftId(duty.getShiftId());

					if (duty != null) {

						if (duty.getType() == 1) {// Daily Basis

							// if(assignDutyList.get(i).getNotifyTime().equalsIgnoreCase(strTime)) {
							//
							// }
							
							List<Integer> empIdList=new ArrayList<>();

							if(!assignDutyList.get(i).getEmpIds().isEmpty()) {
							
							 empIdList = Stream.of(assignDutyList.get(i).getEmpIds().split(","))
									.map(Integer::parseInt).collect(Collectors.toList());
							}
							
							for (int j = 0; j < empIdList.size(); j++) {

								TaskDoneHeader taskDoneHeader = null;
								taskDoneHeader = taskDoneHeaderRepo.findByTaskDateAndDutyIdAndEmpId(strDate,
										duty.getDutyId(), empIdList.get(j));

								if (taskDoneHeader == null) {

									System.out.println("DUTY WEIGHT ========================================= "
											+ duty.getTotalTaskWt());

									TaskDoneHeader taskHead = new TaskDoneHeader(0, strDate, duty.getDutyId(),
											empIdList.get(j), 0, "", 0, duty.getCreatedBy(), 1, 0,
											duty.getTotalTaskWt(), 0, "0", 0, 0, 0, "", "", "");

									TaskDoneHeader result = taskDoneHeaderRepo.save(taskHead);
									System.out.println(
											"TASK HEAD SAVE -----------------------------------------------------------  "
													+ result);

									if (result != null) {
										if (result.getTaskDoneHeaderId() > 0) {

											try {
												Employee emp = employeeRepository
														.findByEmpIdAndDelStatus(empIdList.get(j), 1);

												List<TaskDoneHeader> headerList = taskDoneHeaderRepo
														.findByTaskDateAndEmpIdAndDelStatus(strDate, emp.getEmpId(), 1);

												String dutyCount = "";
												if (headerList != null) {
													dutyCount = String.valueOf(headerList.size());
												}

												if (emp.getExVar1() != null) {
													Firebase.sendPushNotifForCommunication(emp.getExVar1(),
															"Duty Reminder",
															dutyCount + " duty has been assigned and on " + strDate,
															"10");
												}
											} catch (Exception e) {
												e.printStackTrace();
											}

											List<TaskDetail> taskDetail = null;
											taskDetail = taskDetailRepo.findAllByDelStatusAndDutyIdOrderByTaskWeightDesc(1,
													duty.getDutyId());

											if (taskDetail != null) {

												for (int k = 0; k < taskDetail.size(); k++) {

													TaskDoneDetail taskDoneDetail = new TaskDoneDetail(0,
															result.getTaskDoneHeaderId(), result.getDutyId(),
															taskDetail.get(k).getTaskId(),
															taskDetail.get(k).getTaskNameEng(),
															taskDetail.get(k).getTaskDescEng(), strDate, "", "", "", "",
															"", "", taskDetail.get(k).getTaskWeight(), 0, 1, taskDetail.get(k).getExInt1(), 0, 0,
															taskDetail.get(k).getExVar1(), "", "");

													TaskDoneDetail taskRes = taskDoneDetailRepo.save(taskDoneDetail);
													System.out.println(
															"TASK DETAIL SAVE -----------------------------------------------------------  "
																	+ taskRes);

												}
											}
										}
									}

									/*
									 * try {
									 * 
									 * SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm a"); Date d1 =
									 * timeFormat.parse(shift.getShiftFromTime()); String dispTime =
									 * sdfTime.format(d1);
									 * 
									 * Employee emp = employeeRepository.findByEmpIdAndDelStatus(empIdList.get(j),
									 * 1);
									 * 
									 * Date date = timeFormat.parse(shift.getShiftFromTime()); Calendar cal =
									 * Calendar.getInstance(); cal.setTime(date);
									 * 
									 * cal.add(Calendar.HOUR, -2);
									 * 
									 * String beforeStartTime = timeFormat.format(cal.getTime());
									 * 
									 * if (strTime.equalsIgnoreCase(beforeStartTime)) {
									 * 
									 * Firebase.sendPushNotifForCommunication(emp.getExVar1(), "Duty Reminder",
									 * "Your duty has been assigned and will start at " + dispTime, "10");
									 * 
									 * }
									 * 
									 * } catch (ParseException e) { // TODO Auto-generated catch block
									 * e.printStackTrace(); }
									 */

								} else {

									/*
									 * try {
									 * 
									 * SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm a"); Date d1 =
									 * timeFormat.parse(shift.getShiftFromTime()); String dispTime =
									 * sdfTime.format(d1);
									 * 
									 * Employee emp = employeeRepository.findByEmpIdAndDelStatus(empIdList.get(j),
									 * 1);
									 * 
									 * Date date = timeFormat.parse(shift.getShiftFromTime()); Calendar cal =
									 * Calendar.getInstance(); cal.setTime(date);
									 * 
									 * cal.add(Calendar.MINUTE, -15);
									 * 
									 * String before15Min = timeFormat.format(cal.getTime());
									 * 
									 * if (strTime.equalsIgnoreCase(before15Min)) {
									 * 
									 * Firebase.sendPushNotifForCommunication(emp.getExVar1(), "Duty Reminder",
									 * "Your duty has been assigned and will start at " + dispTime, "10");
									 * 
									 * }
									 * 
									 * } catch (ParseException e) { // TODO Auto-generated catch block
									 * e.printStackTrace(); }
									 */
								}

							}

						} else if (duty.getType() == 2) {// Day Basis

							List<Integer> dayCountList = Stream.of(duty.getTypeDesc().split(",")).map(Integer::parseInt)
									.collect(Collectors.toList());

							if (dayCountList.contains(todaysDayCount)) {

								List<Integer> empIdList = Stream.of(assignDutyList.get(i).getEmpIds().split(","))
										.map(Integer::parseInt).collect(Collectors.toList());

								for (int j = 0; j < empIdList.size(); j++) {

									TaskDoneHeader taskDoneHeader = null;
									taskDoneHeader = taskDoneHeaderRepo.findByTaskDateAndDutyIdAndEmpId(strDate,
											duty.getDutyId(), empIdList.get(j));

									if (taskDoneHeader == null) {

										System.out.println("DUTY WEIGHT ========================================= "
												+ duty.getTotalTaskWt());

										TaskDoneHeader taskHead = new TaskDoneHeader(0, strDate, duty.getDutyId(),
												empIdList.get(j), 0, "", 0, duty.getCreatedBy(), 1, 0,
												duty.getTotalTaskWt(), 0, "0", 0, 0, 0, "", "", "");

										TaskDoneHeader result = taskDoneHeaderRepo.save(taskHead);
										System.out.println(
												"TASK HEAD SAVE -----------------------------------------------------------  "
														+ result);

										if (result != null) {
											if (result.getTaskDoneHeaderId() > 0) {
												
												try {
													Employee emp = employeeRepository
															.findByEmpIdAndDelStatus(empIdList.get(j), 1);

													List<TaskDoneHeader> headerList = taskDoneHeaderRepo
															.findByTaskDateAndEmpIdAndDelStatus(strDate, emp.getEmpId(), 1);

													String dutyCount = "";
													if (headerList != null) {
														dutyCount = String.valueOf(headerList.size());
													}

													if (emp.getExVar1() != null) {
														Firebase.sendPushNotifForCommunication(emp.getExVar1(),
																"Duty Reminder",
																dutyCount + " duty has been assigned and on " + strDate,
																"10");
													}
												} catch (Exception e) {
													e.printStackTrace();
												}

												List<TaskDetail> taskDetail = null;
												taskDetail = taskDetailRepo.findAllByDelStatusAndDutyIdOrderByTaskWeightDesc(1,
														duty.getDutyId());

												if (taskDetail != null) {

													for (int k = 0; k < taskDetail.size(); k++) {

														TaskDoneDetail taskDoneDetail = new TaskDoneDetail(0,
																result.getTaskDoneHeaderId(), result.getDutyId(),
																taskDetail.get(k).getTaskId(),
																taskDetail.get(k).getTaskNameEng(),
																taskDetail.get(k).getTaskDescEng(), strDate, "", "", "",
																"", "", "", taskDetail.get(k).getTaskWeight(), 0, 1, taskDetail.get(k).getExInt1(),
																0, 0, taskDetail.get(k).getExVar1(), "", "");

														TaskDoneDetail taskRes = taskDoneDetailRepo
																.save(taskDoneDetail);
														System.out.println(
																"TASK DETAIL SAVE -----------------------------------------------------------  "
																		+ taskRes);

													}
												}
											}
										}

										/*
										 * try {
										 * 
										 * SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm a"); Date d1 =
										 * timeFormat.parse(shift.getShiftFromTime()); String dispTime =
										 * sdfTime.format(d1);
										 * 
										 * Employee emp = employeeRepository.findByEmpIdAndDelStatus(empIdList.get(j),
										 * 1);
										 * 
										 * Date date = timeFormat.parse(shift.getShiftFromTime()); Calendar cal =
										 * Calendar.getInstance(); cal.setTime(date);
										 * 
										 * cal.add(Calendar.HOUR, -2);
										 * 
										 * String beforeStartTime = timeFormat.format(cal.getTime());
										 * 
										 * if (strTime.equalsIgnoreCase(beforeStartTime)) {
										 * 
										 * Firebase.sendPushNotifForCommunication(emp.getExVar1(), "Duty Reminder",
										 * "Your duty has been assigned and will start at " + dispTime, "10");
										 * 
										 * }
										 * 
										 * } catch (ParseException e) { // TODO Auto-generated catch block
										 * e.printStackTrace(); }
										 */

									} else {
										/*
										 * try {
										 * 
										 * SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm a"); Date d1 =
										 * timeFormat.parse(shift.getShiftFromTime()); String dispTime =
										 * sdfTime.format(d1);
										 * 
										 * Employee emp = employeeRepository.findByEmpIdAndDelStatus(empIdList.get(j),
										 * 1);
										 * 
										 * Date date = timeFormat.parse(shift.getShiftFromTime()); Calendar cal =
										 * Calendar.getInstance(); cal.setTime(date);
										 * 
										 * cal.add(Calendar.MINUTE, -15);
										 * 
										 * String before15Min = timeFormat.format(cal.getTime());
										 * 
										 * if (strTime.equalsIgnoreCase(before15Min)) {
										 * 
										 * Firebase.sendPushNotifForCommunication(emp.getExVar1(), "Duty Reminder",
										 * "Your duty has been assigned and will start at " + dispTime, "10");
										 * 
										 * }
										 * 
										 * } catch (ParseException e) { // TODO Auto-generated catch block
										 * e.printStackTrace(); }
										 */
									}
								}
							}

						} else if (duty.getType() == 3) {// Date Basis

							List<Integer> dateCountList = Stream.of(duty.getTypeDesc().split(","))
									.map(Integer::parseInt).collect(Collectors.toList());

							if (dateCountList.contains(todaysDateCount)) {

								List<Integer> empIdList = Stream.of(assignDutyList.get(i).getEmpIds().split(","))
										.map(Integer::parseInt).collect(Collectors.toList());

								for (int j = 0; j < empIdList.size(); j++) {

									TaskDoneHeader taskDoneHeader = null;
									taskDoneHeader = taskDoneHeaderRepo.findByTaskDateAndDutyIdAndEmpId(strDate,
											duty.getDutyId(), empIdList.get(j));

									if (taskDoneHeader == null) {

										System.out.println("DUTY WEIGHT ========================================= "
												+ duty.getTotalTaskWt());

										TaskDoneHeader taskHead = new TaskDoneHeader(0, strDate, duty.getDutyId(),
												empIdList.get(j), 0, "", 0, duty.getCreatedBy(), 1, 0,
												duty.getTotalTaskWt(), 0, "0", 0, 0, 0, "", "", "");

										TaskDoneHeader result = taskDoneHeaderRepo.save(taskHead);
										System.out.println(
												"TASK HEAD SAVE -----------------------------------------------------------  "
														+ result);

										if (result != null) {
											if (result.getTaskDoneHeaderId() > 0) {
												
												try {
													Employee emp = employeeRepository
															.findByEmpIdAndDelStatus(empIdList.get(j), 1);

													List<TaskDoneHeader> headerList = taskDoneHeaderRepo
															.findByTaskDateAndEmpIdAndDelStatus(strDate, emp.getEmpId(), 1);

													String dutyCount = "";
													if (headerList != null) {
														dutyCount = String.valueOf(headerList.size());
													}

													if (emp.getExVar1() != null) {
														Firebase.sendPushNotifForCommunication(emp.getExVar1(),
																"Duty Reminder",
																dutyCount + " duty has been assigned and on " + strDate,
																"10");
													}
												} catch (Exception e) {
													e.printStackTrace();
												}

												List<TaskDetail> taskDetail = null;
												taskDetail = taskDetailRepo.findAllByDelStatusAndDutyIdOrderByTaskWeightDesc(1,
														duty.getDutyId());

												if (taskDetail != null) {

													for (int k = 0; k < taskDetail.size(); k++) {

														TaskDoneDetail taskDoneDetail = new TaskDoneDetail(0,
																result.getTaskDoneHeaderId(), result.getDutyId(),
																taskDetail.get(k).getTaskId(),
																taskDetail.get(k).getTaskNameEng(),
																taskDetail.get(k).getTaskDescEng(), strDate, "", "", "",
																"", "", "", taskDetail.get(k).getTaskWeight(), 0, 1,taskDetail.get(k).getExInt1(),
																0, 0, taskDetail.get(k).getExVar1(), "", "");

														TaskDoneDetail taskRes = taskDoneDetailRepo
																.save(taskDoneDetail);
														System.out.println(
																"TASK DETAIL SAVE -----------------------------------------------------------  "
																		+ taskRes);

													}
												}
											}
										}

										/*
										 * try {
										 * 
										 * SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm a"); Date d1 =
										 * timeFormat.parse(shift.getShiftFromTime()); String dispTime =
										 * sdfTime.format(d1);
										 * 
										 * Employee emp = employeeRepository.findByEmpIdAndDelStatus(empIdList.get(j),
										 * 1);
										 * 
										 * Date date = timeFormat.parse(shift.getShiftFromTime()); Calendar cal =
										 * Calendar.getInstance(); cal.setTime(date);
										 * 
										 * cal.add(Calendar.HOUR, -2);
										 * 
										 * String beforeStartTime = timeFormat.format(cal.getTime());
										 * 
										 * if (strTime.equalsIgnoreCase(beforeStartTime)) {
										 * 
										 * Firebase.sendPushNotifForCommunication(emp.getExVar1(), "Duty Reminder",
										 * "Your duty has been assigned and will start at " + dispTime, "10");
										 * 
										 * }
										 * 
										 * } catch (ParseException e) { // TODO Auto-generated catch block
										 * e.printStackTrace(); }
										 */

									} else {
										/*
										 * try {
										 * 
										 * SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm a"); Date d1 =
										 * timeFormat.parse(shift.getShiftFromTime()); String dispTime =
										 * sdfTime.format(d1);
										 * 
										 * Employee emp = employeeRepository.findByEmpIdAndDelStatus(empIdList.get(j),
										 * 1);
										 * 
										 * Date date = timeFormat.parse(shift.getShiftFromTime()); Calendar cal =
										 * Calendar.getInstance(); cal.setTime(date);
										 * 
										 * cal.add(Calendar.MINUTE, -15);
										 * 
										 * String before15Min = timeFormat.format(cal.getTime());
										 * 
										 * if (strTime.equalsIgnoreCase(before15Min)) {
										 * 
										 * Firebase.sendPushNotifForCommunication(emp.getExVar1(), "Duty Reminder",
										 * "Your duty has been assigned and will start at " + dispTime, "10");
										 * 
										 * }
										 * 
										 * } catch (ParseException e) { // TODO Auto-generated catch block
										 * e.printStackTrace(); }
										 */
									}
								}
							}

						} // else

					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// -----------------------------------------------------------------------

	@Scheduled(cron = "0 0/15 * * * *")
	public void sendNotification() {

		String strTime = timeFormat.format(new Date());
		String strDate = dateFormat.format(new Date());

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

		System.out.println(
				" CRON JOB ----------------------------------------------------------------------------------------------------  "
						+ sdf.format(Calendar.getInstance().getTimeInMillis()));

		List<AssignDuty> assignDutyList = null;

		try {

			int todaysDayCount = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
			int todaysDateCount = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

			assignDutyList = assignDutyRepo.findAllByDelStatusAndExInt1(1, 1);

			if (assignDutyList != null) {

				for (int i = 0; i < assignDutyList.size(); i++) {

					DutyHeader duty = dutyHeaderRepo.findByDutyId(assignDutyList.get(i).getDutyId());
					Shift shift = shiftRepo.findByShiftId(duty.getShiftId());

					SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm a");
					Date d1 = timeFormat.parse(shift.getShiftFromTime());
					String dispTime = sdfTime.format(d1);

					Date date = timeFormat.parse(shift.getShiftFromTime());
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
						
					//cal.add(Calendar.HOUR, -2);

					String beforeStartTime = timeFormat.format(cal.getTime());

					Calendar cal1 = Calendar.getInstance();
					cal1.setTime(date);
					cal1.add(Calendar.MINUTE, -15);

					String before15Min = timeFormat.format(cal1.getTime());
					
					Calendar currTime=Calendar.getInstance();
					
					if (duty != null) {

						if (duty.getType() == 1) {// Daily Basis

							List<Integer> empIdList = Stream.of(assignDutyList.get(i).getEmpIds().split(","))
									.map(Integer::parseInt).collect(Collectors.toList());

							for (int j = 0; j < empIdList.size(); j++) {
								
								Employee emp = employeeRepository.findByEmpIdAndDelStatus(empIdList.get(j), 1);

								try {

									System.err.println("TIME - "+strTime+"  ----------------1-----------------          2 HR BEFORE TIME - "+beforeStartTime);

									/*if (strTime.equalsIgnoreCase(beforeStartTime)) {										

										if (emp.getExVar1() != null) {
											Firebase.sendPushNotifForCommunication(emp.getExVar1(), "Duty Reminder",
													"Your duty has been assigned and will start at " + dispTime, "10");
										}
									}
*/
									System.err.println("TIME - "+strTime+"  ----------------1-----------------          15 MIN BEFORE TIME - "+before15Min);

									/*if (strTime.equalsIgnoreCase(before15Min)) {

										if (emp.getExVar1() != null) {
											Firebase.sendPushNotifForCommunication(emp.getExVar1(), "Duty Reminder",
													"Your duty has been assigned and will start at " + dispTime, "10");
										}
									}*/
									
									if (currTime.getTime().after(cal1.getTime()) && currTime.getTime().before(cal.getTime())) {
										
										System.err.println("CURR TIME - "+currTime.getTime()+"-------  SHIFT - "+cal.getTime()+" ------------- 15 MIN BEFOR - "+cal1.getTime());

										if (emp.getExVar1() != null) {
											Firebase.sendPushNotifForCommunication(emp.getExVar1(), "Duty Reminder",
													"Your duty has been assigned and will start at " + dispTime, "10");
										}
									}
									
									

								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

						} else if (duty.getType() == 2) {// Day Basis

							List<Integer> dayCountList = Stream.of(duty.getTypeDesc().split(",")).map(Integer::parseInt)
									.collect(Collectors.toList());

							if (dayCountList.contains(todaysDayCount)) {

								List<Integer> empIdList = Stream.of(assignDutyList.get(i).getEmpIds().split(","))
										.map(Integer::parseInt).collect(Collectors.toList());

								for (int j = 0; j < empIdList.size(); j++) {
									
									Employee emp = employeeRepository.findByEmpIdAndDelStatus(empIdList.get(j), 1);

									try {

										System.err.println("TIME - "+strTime+"  ----------------2-----------------          2 HR BEFORE TIME - "+beforeStartTime);

										
										/*if (strTime.equalsIgnoreCase(beforeStartTime)) {

											if (emp.getExVar1() != null) {
												Firebase.sendPushNotifForCommunication(emp.getExVar1(), "Duty Reminder",
														"Your duty has been assigned and will start at " + dispTime,
														"10");
											}

										}*/

										System.err.println("TIME - "+strTime+"  ----------------2-----------------          15 MIN BEFORE TIME - "+before15Min);

										/*if (strTime.equalsIgnoreCase(before15Min)) {

											if (emp.getExVar1() != null) {
												Firebase.sendPushNotifForCommunication(emp.getExVar1(), "Duty Reminder",
														"Your duty has been assigned and will start at " + dispTime,
														"10");
											}

										}*/
										
										if (currTime.getTime().after(cal1.getTime()) && currTime.getTime().before(cal.getTime())) {
											
											System.err.println("CURR TIME - "+currTime.getTime()+"-------  SHIFT - "+cal.getTime()+" ------------- 15 MIN BEFOR - "+cal1.getTime());

											if (emp.getExVar1() != null) {
												Firebase.sendPushNotifForCommunication(emp.getExVar1(), "Duty Reminder",
														"Your duty has been assigned and will start at " + dispTime, "10");
											}
										}
										

									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}
							}

						} else if (duty.getType() == 3) {// Date Basis

							List<Integer> dateCountList = Stream.of(duty.getTypeDesc().split(","))
									.map(Integer::parseInt).collect(Collectors.toList());

							if (dateCountList.contains(todaysDateCount)) {

								List<Integer> empIdList = Stream.of(assignDutyList.get(i).getEmpIds().split(","))
										.map(Integer::parseInt).collect(Collectors.toList());

								for (int j = 0; j < empIdList.size(); j++) {
									
									Employee emp = employeeRepository.findByEmpIdAndDelStatus(empIdList.get(j), 1);

									try {
										
										System.err.println("TIME - "+strTime+"  ----------------3-----------------          2 HR BEFORE TIME - "+beforeStartTime);


										/*if (strTime.equalsIgnoreCase(beforeStartTime)) {

											if (emp.getExVar1() != null) {
												Firebase.sendPushNotifForCommunication(emp.getExVar1(), "Duty Reminder",
														"Your duty has been assigned and will start at " + dispTime,
														"10");
											}

										}*/
										
										System.err.println("TIME - "+strTime+"  ----------------3-----------------          15 MIN BEFORE TIME - "+before15Min);


										/*if (strTime.equalsIgnoreCase(before15Min)) {

											if (emp.getExVar1() != null) {
												Firebase.sendPushNotifForCommunication(emp.getExVar1(), "Duty Reminder",
														"Your duty has been assigned and will start at " + dispTime,
														"10");
											}

										}*/
										
										if (currTime.getTime().after(cal1.getTime()) && currTime.getTime().before(cal.getTime())) {
											
											System.err.println("CURR TIME - "+currTime.getTime()+"-------  SHIFT - "+cal.getTime()+" ------------- 15 MIN BEFOR - "+cal1.getTime());

											if (emp.getExVar1() != null) {
												Firebase.sendPushNotifForCommunication(emp.getExVar1(), "Duty Reminder",
														"Your duty has been assigned and will start at " + dispTime, "10");
											}
										}
										

									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}
							}

						} // else

					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	// -----------------------------------------------------------------------

		@Scheduled(cron = "1 * * * * *")
		public void sendTaskNotification() {

			String strTime = timeFormat.format(new Date());
			String strDate = dateFormat.format(new Date());

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

			System.out.println(
					" CRON JOB ----------------------------------------------------------------------------------------------------  "
							+ sdf.format(Calendar.getInstance().getTimeInMillis()));

			List<TaskNotification> notifyTaskList = null;
			
			

			try {


				notifyTaskList = taskNotificationRepo.getTaskForNotify(strDate);
				
			
				
				if(notifyTaskList!=null) {
					
					for(int i=0;i<notifyTaskList.size();i++) {
						
						Date date = timeFormat.parse(notifyTaskList.get(i).getTime());
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						
						Calendar cal1 = Calendar.getInstance();
						cal1.setTime(date);
						cal1.add(Calendar.MINUTE, -10);

						Calendar currTime=Calendar.getInstance();
						
						
						if (currTime.getTime().after(cal1.getTime()) && currTime.getTime().before(cal.getTime())){
							
							try {
								
								String time="";
								SimpleDateFormat sdfTime=new SimpleDateFormat("hh:mm a");
								Date d=timeFormat.parse(notifyTaskList.get(i).getTime());
								time=sdfTime.format(d.getTime());
								
								Firebase.sendPushNotifForCommunication(notifyTaskList.get(i).getToken(), "Task Reminder",
										"You have to start the task " + notifyTaskList.get(i).getTaskName()+" on "+time, "10");
								
								int result = taskDoneDetailRepo.updateTaskNotifyStatus(notifyTaskList.get(i).getTaskDoneDetailId());
								
							}catch (Exception e) {
								e.printStackTrace();
							}
							
						}
						
					}
					
				}
				

				

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		
		
		
	
	
	

}
