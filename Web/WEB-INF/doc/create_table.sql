grant all privileges on webboard.* to wbuser@localhost identified by 'admin123';
CREATE database webboard;
flush privileges; 


DROP TABLE IF EXISTS `webboard`;
CREATE TABLE IF NOT EXISTS `webboard` (
  `no` int(11) NOT NULL AUTO_INCREMENT COMMENT '넘버',
  `title` varchar(20) NOT NULL COMMENT '타이틀',
  `content` varchar(800) NOT NULL COMMENT '내용',
  `reg_name` varchar(20) NOT NULL COMMENT '등록자명',
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '등록일',
  `upd_name` varchar(20) NOT NULL COMMENT '수정자명',
  `upd_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '수정일',
  PRIMARY KEY (`no`)
) DEFAULT CHARSET=utf8;

ALTER TABLE `board01` CHANGE `content` `content` VARCHAR(800) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '내용';