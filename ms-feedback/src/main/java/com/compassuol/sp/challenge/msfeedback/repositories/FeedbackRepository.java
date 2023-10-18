package com.compassuol.sp.challenge.msfeedback.repositories;

import com.compassuol.sp.challenge.msfeedback.domain.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
