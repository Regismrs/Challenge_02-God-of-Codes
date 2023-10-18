package com.compassuol.sp.challenge.msfeedback.domain.entities;

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
    //enum
    private String scale;

    private String comment;
}
