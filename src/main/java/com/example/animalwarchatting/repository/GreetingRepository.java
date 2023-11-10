package com.example.animalwarchatting.repository;

import com.example.animalwarchatting.entity.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {
    Greeting save(Greeting greeting);

}

//     ### MySQL에 넣으면 됨..
//    create database animalwarChatting;
//        use animalwarChatting;
//
//        -- ID가 31보다 큰 데이터를 삭제
//        -- DELETE FROM greeting
//        -- WHERE id <= (SELECT MAX(id) - 30 FROM greeting);
//
//
//        DELIMITER //
//        CREATE EVENT delete_old_records_event_limit_30
//        ON SCHEDULE EVERY 1 SECOND
//        DO
//        BEGIN
//        DECLARE old_id INT;
//        SET old_id = (SELECT MAX(id) - 30 FROM greeting);
//
//        IF old_id > 0 THEN
//        DELETE FROM greeting WHERE id <= old_id;
//        END IF;
//        END;
////
//        DELIMITER ;
//
//        -- 이벤트 활성화
//        ALTER EVENT delete_old_records_event_limit_30 ON COMPLETION NOT PRESERVE ENABLE;
//
//        -- DROP EVENT delete_old_records_event_limit_30;
//
//        select * from greeting;
//
//
//        -- 행 수 확인
//        SELECT COUNT(*) AS row_count
//        FROM greeting;
