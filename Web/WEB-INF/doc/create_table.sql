grant all privileges on webboard.* to wbuser@localhost identified by 'admin123';
CREATE database webboard;
flush privileges; 


DROP TABLE IF EXISTS `webboard`;
CREATE TABLE IF NOT EXISTS `webboard` (
  `no` int(11) NOT NULL AUTO_INCREMENT COMMENT '�ѹ�',
  `title` varchar(20) NOT NULL COMMENT 'Ÿ��Ʋ',
  `content` varchar(800) NOT NULL COMMENT '����',
  `reg_name` varchar(20) NOT NULL COMMENT '����ڸ�',
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '�����',
  `upd_name` varchar(20) NOT NULL COMMENT '�����ڸ�',
  `upd_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '������',
  PRIMARY KEY (`no`)
) DEFAULT CHARSET=utf8;

ALTER TABLE `board01` CHANGE `content` `content` VARCHAR(800) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '����';