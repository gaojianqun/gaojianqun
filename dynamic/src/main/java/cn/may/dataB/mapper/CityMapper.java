package cn.may.dataB.mapper;

import cn.may.model.City;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2017/11/3.
 */
@Mapper
public interface CityMapper {
       List<City> findCity();
}
