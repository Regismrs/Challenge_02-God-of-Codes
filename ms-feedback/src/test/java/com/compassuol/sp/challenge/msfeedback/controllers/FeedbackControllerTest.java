package com.compassuol.sp.challenge.msfeedback.controllers;

import com.compassuol.sp.challenge.msfeedback.domain.dto.FeedbackRequest;
import com.compassuol.sp.challenge.msfeedback.domain.dto.FeedbackResponse;
import com.compassuol.sp.challenge.msfeedback.exceptions.NotFound;
import com.compassuol.sp.challenge.msfeedback.services.FeedbackService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.compassuol.sp.challenge.msfeedback.common.FeedbackConstants.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedbackControllerTest {
    @InjectMocks
    private FeedbackController feedbackController;
    @Mock
    private FeedbackService service;
    @Test
    void updateFeedbackWithValidDataReturnFeedback() {
        FeedbackResponse expected = new FeedbackResponse(
                    FEEDBACK_RESPONSE.getId(),
                    FEEDBACK_RESPONSE.getOrderId(),
                    FEEDBACK_RESPONSE.getScale(),
                    FEEDBACK_RESPONSE.getComment()
                );

        when(service.updateFeedback(1L, FEEDBACK_REQUEST)).thenReturn(FEEDBACK_RESPONSE);

        ResponseEntity<FeedbackResponse> response =
                feedbackController.updateFeedback(1L, FEEDBACK_REQUEST);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void updateFeedbackWithInvalidDataThrowsException() {
        when(service.updateFeedback(any(Long.class), any(FeedbackRequest.class))).thenThrow(RuntimeException.class);

        assertThatThrownBy(
                () -> feedbackController.updateFeedback(1L, FEEDBACK_REQUEST)
        ).isInstanceOf(RuntimeException.class);
    }

    @Test
    void updateFeedbackWithUnexistentIdThrowsException() {
        when(service.updateFeedback(any(Long.class), any(FeedbackRequest.class))).thenThrow(NotFound.class);

        assertThatThrownBy(
                () -> feedbackController.updateFeedback(1L, FEEDBACK_REQUEST)
        ).isInstanceOf(NotFound.class);

    }
    @Test
    void FeedbackByIdWithExistent() {
        when(service.findById(1L)).thenReturn(FEEDBACK_RESPONSE);

        ResponseEntity<FeedbackResponse> sut = feedbackController.getFeedback(1L);

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(sut.getBody()).isEqualTo(FEEDBACK_RESPONSE);
    }
    @Test
    void FeedbackByIdWithNonExistent() {
        when(service.findById(1L)).thenThrow(NotFound.class);

        assertThatThrownBy(
                () -> feedbackController.getFeedback(1L)
        ).isInstanceOf(NotFound.class);
    }
}