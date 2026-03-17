package com.example.backend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyInfoResponse {
    String memberName;
    String memberId;
    String roleType;
}
