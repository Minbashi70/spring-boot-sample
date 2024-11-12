package com.minbashi.project.dto.request;

public record PersonRequest(Long id, String firstName, String lastName, String phoneNumber, Integer age) {
}
