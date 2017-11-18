package com.twx.db;

import com.twx.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by twx on 2017/11/18.
 */
public interface LogRepository extends JpaRepository<LogEntity,Integer> {
}
