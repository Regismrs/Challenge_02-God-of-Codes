package com.compassuol.sp.challenge.msfeedback.controllers;

import com.compassuol.sp.challenge.msfeedback.domain.dto.FeedbackRequest;
import com.compassuol.sp.challenge.msfeedback.domain.dto.FeedbackResponse;
import com.compassuol.sp.challenge.msfeedback.exceptions.NotFound;
import com.compassuol.sp.challenge.msfeedback.services.FeedbackService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static com.compassuol.sp.challenge.msfeedback.common.FeedbackConstants.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FeedbackControllerTest {
    @InjectMocks
    private FeedbackController feedbackController;
    @Mock
    private FeedbackService feedbackService;
    @Test
    void updateFeedbackWithValidDataReturnFeedback() {
        FeedbackResponse expected = new FeedbackResponse(
                FEEDBACK_RESPONSE.getId(),
                FEEDBACK_RESPONSE.getOrderId(),
                FEEDBACK_RESPONSE.getScale(),
                FEEDBACK_RESPONSE.getComment()
        );

        when(feedbackService.updateFeedback(1L, FEEDBACK_REQUEST)).thenReturn(FEEDBACK_RESPONSE);

        ResponseEntity<FeedbackResponse> response =
                feedbackController.updateFeedback(1L, FEEDBACK_REQUEST);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void updateFeedbackWithInvalidDataThrowsException() {
        when(feedbackService.updateFeedback(any(Long.class), any(FeedbackRequest.class))).thenThrow(ConstraintViolationException.class);

        assertThatThrownBy(
                () -> feedbackController.updateFeedback(1L, FEEDBACK_REQUEST)
        ).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void updateFeedbackWithUnexistentIdThrowsException() {
        when(feedbackService.updateFeedback(any(Long.class), any(FeedbackRequest.class))).thenThrow(NotFound.class);

        assertThatThrownBy(
                () -> feedbackController.updateFeedback(1L, FEEDBACK_REQUEST)
        ).isInstanceOf(NotFound.class);

    }
    @Test
    void FeedbackByIdWithExistent() {
        when(feedbackService.findById(1L)).thenReturn(FEEDBACK_RESPONSE);

        ResponseEntity<FeedbackResponse> sut = feedbackController.getFeedback(1L);

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(sut.getBody()).isEqualTo(FEEDBACK_RESPONSE);
    }
    @Test
    void FeedbackByIdWithNonExistent() {
        when(feedbackService.findById(1L)).thenThrow(NotFound.class);

        assertThatThrownBy(
                () -> feedbackController.getFeedback(1L)
        ).isInstanceOf(NotFound.class);
    }

    @Test
    void deleteFeedbackWithExistentIdReturnsSuccess() {
        Long id = 1L;
        doNothing().when(feedbackService).deleteFeedback(id);
        ResponseEntity<?> response = feedbackController.deleteFeedback(id);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(feedbackService).deleteFeedback(id);
    }

    @Test
    void deleteFeedbackWithNonexistentIdThrowsException(){
        Long id = 1L;
        doThrow(new NotFound("Feedback " + id + " not found")).when(feedbackService).deleteFeedback(id);

        assertThatThrownBy(
                () -> feedbackController.deleteFeedback(id)
        ).isInstanceOf(NotFound.class);
    }

    @Test
    void createFeedbackWithValidDataReturnProduct() {
        when(feedbackService.saveFeedback(FEEDBACK_REQUEST)).thenReturn(FEEDBACK_RESPONSE);

        ResponseEntity<FeedbackResponse> sut = feedbackController.createFeedback(FEEDBACK_REQUEST);

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(sut.getBody()).isEqualTo(FEEDBACK_RESPONSE);
    }

    void getAllFeedbackReturnProductsList() {
        when(feedbackService.getAll()).thenReturn(FEEDBACK_LIST);

        ResponseEntity<List<FeedbackResponse>> sut = feedbackController.getAll();

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(sut.getBody()).hasSize(2);
        assertThat(sut.getBody().get(0)).isEqualTo(FEEDBACK_RES_DTO1);
    }

    @Test
    void getAllFeedbackReturnEmptyList() {
        when(feedbackService.getAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<FeedbackResponse>> sut = feedbackController.getAll();

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(sut.getBody()).isEmpty();
    }

    @Test
    void createFeedbackWithInvalidDataThrowException() {
        when(feedbackService.saveFeedback(FEEDBACK_REQUEST)).thenThrow(ConstraintViolationException.class);

        assertThatThrownBy(
                () -> feedbackController.createFeedback(FEEDBACK_REQUEST)
        ).isInstanceOf(ConstraintViolationException.class);
    }
}