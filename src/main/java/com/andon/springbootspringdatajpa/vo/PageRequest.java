package com.andon.springbootspringdatajpa.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author Andon
 * 2022/2/11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageRequest {

    @Min(value = 0, message = "页码不能小于 0")
    @ApiModelProperty(value = "页码")
    int page = 0;

    @Min(value = 1, message = "页大小不能小于 1")
    @Max(value = 100, message = "页大小不能大于 100")
    @ApiModelProperty(value = "页大小")
    int size = 10;
}
