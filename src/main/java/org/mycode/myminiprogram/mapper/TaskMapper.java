package org.mycode.myminiprogram.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mycode.myminiprogram.entity.Task;

@Mapper
public interface TaskMapper extends BaseMapper<Task> {

}
