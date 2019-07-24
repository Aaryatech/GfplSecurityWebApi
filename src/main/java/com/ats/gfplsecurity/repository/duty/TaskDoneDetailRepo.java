package com.ats.gfplsecurity.repository.duty;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.duty.TaskDoneDetail;

@Repository
public interface TaskDoneDetailRepo extends JpaRepository<TaskDoneDetail, Integer> {

	List<TaskDoneDetail> findAllByDelStatus(int i);

	TaskDoneDetail findByTaskDoneDetailId(int taskDoneDetail);

	List<TaskDoneDetail> findAllByDelStatusAndTaskDoneHeaderId(int i, int headerId);

	@Transactional
	@Modifying
	@Query("update TaskDoneDetail set task_status=:status, photo1=:photo1, photo2=:photo2, photo3=:photo3, photo4=:photo4, photo5=:photo5, remark=:remark  WHERE task_done_detail_id=:detailId")
	int updateTaskWithPhotoAndRemark(@Param("detailId") int detailId, @Param("status") int status,
			@Param("photo1") String photo1, @Param("photo2") String photo2, @Param("photo3") String photo3,
			@Param("photo4") String photo4, @Param("photo5") String photo5, @Param("remark") String remark);

	@Transactional
	@Modifying
	@Query("update TaskDoneDetail set task_status=:status, photo1=:photo1, photo2=:photo2, photo3=:photo3, photo4=:photo4, photo5=:photo5  WHERE task_done_detail_id=:detailId")
	int updateTaskWithPhoto(@Param("detailId") int detailId, @Param("status") int status,
			@Param("photo1") String photo1, @Param("photo2") String photo2, @Param("photo3") String photo3,
			@Param("photo4") String photo4, @Param("photo5") String photo5);

	@Transactional
	@Modifying
	@Query("update TaskDoneDetail set task_status=:status, remark=:remark  WHERE task_done_detail_id=:detailId")
	int updateTaskWithRemark(@Param("detailId") int detailId, @Param("status") int status,
			@Param("remark") String remark);
	
	@Transactional
	@Modifying
	@Query("update TaskDoneDetail set task_status=:status  WHERE task_done_detail_id=:detailId")
	int updateTask(@Param("detailId") int detailId, @Param("status") int status);
	
	@Transactional
	@Modifying
	@Query("update TaskDoneDetail set ex_int2=1  WHERE task_done_detail_id=:detailId")
	int updateTaskNotifyStatus(@Param("detailId") int detailId);

}
