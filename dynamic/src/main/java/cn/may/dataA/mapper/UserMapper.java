package cn.may.dataA.mapper;

import cn.may.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2017/11/3.
 */
@Mapper
public interface UserMapper {
    List<User> findUser();
}
