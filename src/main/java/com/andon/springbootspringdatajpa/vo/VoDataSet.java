package com.andon.springbootspringdatajpa.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Andon
 * 2022/2/10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VoDataSet {

    @NotNull(message = "id不能为空!")
    @ApiModelProperty(value = "id", required = true)
    String id;

    @NotNull(message = "名称不能为空!")
    @ApiModelProperty(value = "名称", required = true)
    String name;

//    @NotNull(message = "数据可见性不能为空!")
//    @ApiModelProperty(value = "数据可见性: SELF -> 仅自己 ALL -> 所有人", required = true)
//    VisibleLevel visibleLevel;

    @NotNull(message = "数据可见性不能为空!")
    @ApiModelProperty(value = "数据可见性: SELF -> 仅自己 ALL -> 所有人", required = true)
    String visibleLevel;

    @NotNull(message = "有效性不能为空!")
    @ApiModelProperty(value = "有效性", required = true)
    boolean available;

    @ApiModelProperty(value = "描述")
    String description;

    @ApiModelProperty(value = "备注")
    String note;

    @ApiModelProperty(value = "数据创建时间")
    Date createTime;

    @ApiModelProperty(value = "数据更新时间")
    Date updateTime;
}
