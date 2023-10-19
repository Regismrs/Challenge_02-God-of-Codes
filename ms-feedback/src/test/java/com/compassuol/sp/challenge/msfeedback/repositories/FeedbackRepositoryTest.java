package com.compassuol.sp.challenge.msfeedback.repositories;

import com.compassuol.sp.challenge.msfeedback.domain.entities.Feedback;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;


import java.util.Optional;

import static com.compassuol.sp.challenge.msfeedback.common.FeedbackConstants.FEEDBACK;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FeedbackRepositoryTest {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Test
    void deleteFeedbackWithExistentIdReturnsEmpty() {
        feedbackRepository.delete(FEEDBACK);

        Optional<Feedback> deletedFeedback = feedbackRepository.findById(FEEDBACK.getId());

        assertThat(deletedFeedback).isEmpty();
    }

}
