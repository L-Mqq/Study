package org.mycode.myminiprogram.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;  //表示日期和时间

@Data  //自动生成getter setter toString equals hashCode
@TableName("task")
public class Task {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("title")
    @NotNull(message = "任务标题不能为空")
    private String title;

    @TableField("description")
    private String description;

    @TableField("completed")
    private boolean completed;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private  LocalDateTime updateTime;

}
