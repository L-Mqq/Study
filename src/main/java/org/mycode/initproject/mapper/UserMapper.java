package org.mycode.initproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mycode.initproject.model.User;

/**
* @author WIND
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2026-11-13 15:47:27
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




