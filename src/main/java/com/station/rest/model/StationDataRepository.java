package com.station.rest.model;

import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface StationDataRepository {

    @Select("SELECT * FROM stations")
    public List<Station> findAll();

    @Select("SELECT * FROM stations WHERE stationId=#{stationId}")
    public Station findByStationId(Integer stationId);

    @Select("SELECT * FROM stations WHERE name=#{name}")
    public List<Station> findByStationName(String name);

    @Select("SELECT * FROM stations WHERE hdEnabled = #{hdEnabled}")
    public List<Station> findByHdEnabledStations(Boolean hdEnabled);

    @Delete("DELETE FROM stations WHERE stationId = #{stationId}")
    public int deleteById(Integer stationId);

    @Insert("INSERT INTO stations(stationId,name,hdEnabled,callSign) VALUES (#{stationId},#{name},#{hdEnabled},#{callSign})")
    public int insert(Station station);

    @Update("Update stations set name=#{name},hdEnabled=#{hdEnabled},callSign=#{callSign} where stationId=#{stationId}")
    public int update(Station station);

}