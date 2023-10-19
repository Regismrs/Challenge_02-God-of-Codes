package com.compassuol.sp.challenge.msfeedback.services;

import com.compassuol.sp.challenge.msfeedback.domain.dto.FeedbackRequest;
import com.compassuol.sp.challenge.msfeedback.domain.dto.FeedbackResponse;
import com.compassuol.sp.challenge.msfeedback.domain.entities.Feedback;
import com.compassuol.sp.challenge.msfeedback.exceptions.NotFound;
import com.compassuol.sp.challenge.msfeedback.repositories.FeedbackRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.compassuol.sp.challenge.msfeedback.common.FeedbackConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceTest {

    @InjectMocks
    private FeedbackService feedbackService;
    @Mock
    private FeedbackRepository feedbackRepository;

    @Test
    void updateFeedbackWithExistentIdReturnFeedback() {
        FeedbackResponse expected = new FeedbackResponse(
                1L,
                FEEDBACK.getOrderId(),
                FEEDBACK.getScale(),
                FEEDBACK.getComment());

        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(FEEDBACK));
        when(feedbackRepository.save(FEEDBACK)).thenReturn(FEEDBACK);

        FeedbackResponse sut = feedbackService.updateFeedback(1L, FEEDBACK_REQUEST);

        assertThat(sut).isInstanceOf(FeedbackResponse.class);
        assertThat(sut).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void updateFeedbackWithUnexistentIdThrowsException() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(
                () -> feedbackService.updateFeedback(1L, FEEDBACK_REQUEST )
        ).isInstanceOf(NotFound.class);
    }

    @Test
    void updateFeedbackWithIdAndInvalidDataThrowsException() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(FEEDBACK));
        when(feedbackRepository.save(FEEDBACK)).thenThrow(RuntimeException.class);

        FeedbackRequest request = new FeedbackRequest(1L, null, "abc");

        assertThatThrownBy(
                () -> feedbackService.updateFeedback(1L, FEEDBACK_REQUEST )
        ).isInstanceOf(RuntimeException.class);
    }
  
    @Test
    void findByIdWithExistentIdReturnsFeedback() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(FEEDBACK));

        FeedbackResponse sut = feedbackService.findById(1L);

        assertThat(sut).isNotNull().isInstanceOf(FeedbackResponse.class).usingRecursiveComparison().isEqualTo(FEEDBACK_RESPONSE);
    }
  
    @Test
    void deleteFeedbackWithExistentIdReturnsSuccess() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(FEEDBACK));
        feedbackService.deleteFeedback(1L);
        verify(feedbackRepository).delete(FEEDBACK);
    }
  
    @Test
    void deleteFeedbackWithNonExistentIdThrowsException() {
        when(feedbackRepository.findById(1L)).thenThrow(NotFound.class);

        assertThatThrownBy( () -> feedbackService.deleteFeedback(1L )
        ).isInstanceOf(NotFound.class);
    }
    @Test
    void getProductsReturnsEmptyList() {
        when(feedbackRepository.findAll()).thenReturn(Collections.emptyList());

        List<FeedbackResponse> sut = feedbackService.getAll();

        assertThat(sut).isEmpty();
    }

    @Test
    void getFeedbackReturnsAllProducts() {
        List<Feedback> feedbackList = new ArrayList<>(){
            {
                add(FEEDBACK);
            }
        };
        when(feedbackRepository.findAll()).thenReturn(feedbackList);
        List<FeedbackResponse> sut = feedbackService.getAll();

        assertThat(sut).isNotEmpty();
        assertThat(sut).hasSize(1);

        assertThat(sut.get(0)).isInstanceOf(FeedbackResponse.class);
        assertThat(sut.get(0))
                .usingRecursiveComparison()
                .isEqualTo(FEEDBACK);

    }
  
}