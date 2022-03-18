package com.book.trendyol.entity;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    private Integer id;
    @NotNull(message = "Field 'author' is required")
    @NotBlank(message = "Field 'author' cannot be empty")
    private String author;
    @NotNull(message = "Field 'title' is required")
    @NotBlank(message = "Field 'title' cannot be empty")
    private String title;
}
