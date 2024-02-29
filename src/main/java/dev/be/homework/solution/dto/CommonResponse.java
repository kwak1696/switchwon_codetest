package dev.be.homework.solution.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class CommonResponse<T> {
    T data;
    Object detail;
    String status;

    public CommonResponse(T data) {
        this.data = data;
        this.status = "success";
    }

    public CommonResponse(T data, Object detail) {
        this.data = data;
        this.detail = detail;
        this.status = "fail";
    }

}
