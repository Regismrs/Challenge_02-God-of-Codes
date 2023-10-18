package com.compassuol.sp.challenge.msfeedback.domain.entities;

import com.compassuol.sp.challenge.msfeedback.enums.ScaleEnum;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "feedback_tb")
public class Feedback implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="order_id", nullable = false)
    private Long orderId;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private ScaleEnum scale;

    private String comment;
}
