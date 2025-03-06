package com.codingshuttle.SecurityApp.SecurityApplication.dto;

import lombok.*;

@Data
//@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String title;
    private String description;

    private UserDTO author;
}
