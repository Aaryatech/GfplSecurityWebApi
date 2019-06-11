package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.Location;

@Repository
public interface LocationRepo extends JpaRepository<Location, Integer> {

	List<Location> findAllByDelStatus(int i);

	Location findByLocId(int locId);

}