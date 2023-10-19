package com.compassuol.sp.challenge.msfeedback.services;

import com.compassuol.sp.challenge.msfeedback.domain.dto.FeedbackRequest;
import com.compassuol.sp.challenge.msfeedback.domain.dto.FeedbackResponse;
import com.compassuol.sp.challenge.msfeedback.exceptions.NotFound;
import com.compassuol.sp.challenge.msfeedback.repositories.FeedbackRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.compassuol.sp.challenge.msfeedback.common.FeedbackConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceTest {

    @InjectMocks
    private FeedbackService feedbackService;
    @Mock
    private FeedbackRepository repository;


    @Test
    void getAll() {
    }

    @Test
    void saveFeedback() {
    }

    @Test
    void updateFeedbackWithExistentIdReturnFeedback() {
        FeedbackResponse expected = new FeedbackResponse(
                1L,
                FEEDBACK.getOrderId(),
                FEEDBACK.getScale(),
                FEEDBACK.getComment());

        when(repository.findById(1L)).thenReturn(Optional.of(FEEDBACK));
        when(repository.save(FEEDBACK)).thenReturn(FEEDBACK);

        FeedbackResponse sut = feedbackService.updateFeedback(1L, FEEDBACK_REQUEST);

        assertThat(sut).isInstanceOf(FeedbackResponse.class);
        assertThat(sut).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void updateFeedbackWithUnexistentIdThrowsException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(
                () -> feedbackService.updateFeedback(1L, FEEDBACK_REQUEST )
        ).isInstanceOf(NotFound.class);
    }

    @Test
    void updateFeedbackWithIdAndInvalidDataThrowsException() {
        when(repository.findById(1L)).thenReturn(Optional.of(FEEDBACK));
        when(repository.save(FEEDBACK)).thenThrow(RuntimeException.class);

        FeedbackRequest request = new FeedbackRequest(1L, null, "abc");

        assertThatThrownBy(
                () -> feedbackService.updateFeedback(1L, FEEDBACK_REQUEST )
        ).isInstanceOf(RuntimeException.class);
    }

}