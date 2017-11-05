package cn.may.dataC.mapper;

import cn.may.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2017/11/3.
 */
@Mapper
public interface RoleMapper {
    List<Role> findRole();
}
