/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : video

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2021-08-27 17:18:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `actor`
-- ----------------------------
DROP TABLE IF EXISTS `actor`;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `img_src` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `fans` int(11) DEFAULT NULL,
  `trend` int(11) DEFAULT NULL,
  `video_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of actor
-- ----------------------------
INSERT INTO `actor` VALUES ('1', null, '测试', '199990', '199999', '4');
INSERT INTO `actor` VALUES ('2', null, '123', '2222', '22222', '1');

-- ----------------------------
-- Table structure for `admins`
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('1', 'admin', '123', 'static/images/管理员头像.png');

-- ----------------------------
-- Table structure for `area`
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES ('1', '内地');
INSERT INTO `area` VALUES ('2', '香港');
INSERT INTO `area` VALUES ('3', '台湾');
INSERT INTO `area` VALUES ('4', '日本');
INSERT INTO `area` VALUES ('5', '韩国');
INSERT INTO `area` VALUES ('6', '欧美地区');
INSERT INTO `area` VALUES ('7', '澳门');
INSERT INTO `area` VALUES ('8', '美国');
INSERT INTO `area` VALUES ('9', '法国');
INSERT INTO `area` VALUES ('10', '东南亚地区');
INSERT INTO `area` VALUES ('11', '印度');
INSERT INTO `area` VALUES ('12', '泰国');
INSERT INTO `area` VALUES ('13', '英国');

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(10) DEFAULT NULL,
  `pid` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '电影', '0');
INSERT INTO `category` VALUES ('2', '动漫', '0');
INSERT INTO `category` VALUES ('3', '电视剧', '0');
INSERT INTO `category` VALUES ('4', 'IT学习', '0');
INSERT INTO `category` VALUES ('5', '爱情', '1');
INSERT INTO `category` VALUES ('6', '动作', '1');
INSERT INTO `category` VALUES ('7', '喜剧', '1');
INSERT INTO `category` VALUES ('8', '战争', '1');
INSERT INTO `category` VALUES ('10', '剧情', '1');
INSERT INTO `category` VALUES ('11', '武侠', '1');
INSERT INTO `category` VALUES ('12', '冒险', '1');
INSERT INTO `category` VALUES ('13', '枪战', '1');
INSERT INTO `category` VALUES ('14', '恐怖', '1');
INSERT INTO `category` VALUES ('15', '悬疑', '1');
INSERT INTO `category` VALUES ('16', '奇幻', '1');
INSERT INTO `category` VALUES ('17', '动画', '1');
INSERT INTO `category` VALUES ('18', '惊悚', '1');
INSERT INTO `category` VALUES ('19', '经典', '1');
INSERT INTO `category` VALUES ('20', '青春', '1');
INSERT INTO `category` VALUES ('21', '古装', '1');
INSERT INTO `category` VALUES ('22', '文艺', '1');
INSERT INTO `category` VALUES ('23', '热血', '2');
INSERT INTO `category` VALUES ('24', '爱情', '2');
INSERT INTO `category` VALUES ('25', '搞笑', '2');
INSERT INTO `category` VALUES ('26', '少儿', '2');
INSERT INTO `category` VALUES ('27', '亲子', '2');
INSERT INTO `category` VALUES ('28', '魔法', '2');
INSERT INTO `category` VALUES ('29', '运动', '2');
INSERT INTO `category` VALUES ('30', '机战', '2');
INSERT INTO `category` VALUES ('31', '科幻', '2');
INSERT INTO `category` VALUES ('32', '校园', '2');
INSERT INTO `category` VALUES ('33', '动物', '2');
INSERT INTO `category` VALUES ('34', '冒险', '2');
INSERT INTO `category` VALUES ('35', '神话', '2');
INSERT INTO `category` VALUES ('36', '推理', '2');
INSERT INTO `category` VALUES ('37', '剧情', '2');
INSERT INTO `category` VALUES ('38', '战争', '2');
INSERT INTO `category` VALUES ('39', '经典', '2');
INSERT INTO `category` VALUES ('40', '男性向', '2');
INSERT INTO `category` VALUES ('41', '女性向', '2');
INSERT INTO `category` VALUES ('42', 'java', '4');
INSERT INTO `category` VALUES ('43', 'javaScript', '4');
INSERT INTO `category` VALUES ('44', 'html+css', '4');
INSERT INTO `category` VALUES ('45', 'mybatis', '4');
INSERT INTO `category` VALUES ('46', 'spring', '4');
INSERT INTO `category` VALUES ('47', 'springmvc', '4');
INSERT INTO `category` VALUES ('48', 'layui', '4');
INSERT INTO `category` VALUES ('49', 'android', '4');
INSERT INTO `category` VALUES ('50', '123', '1');
INSERT INTO `category` VALUES ('51', '肥皂剧', '3');

-- ----------------------------
-- Table structure for `history`
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `episode` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of history
-- ----------------------------
INSERT INTO `history` VALUES ('8', '26', '9', '123', '2020-10-21 21:17:16', 'file/video/images/123.jpg', '第一级', '1');
INSERT INTO `history` VALUES ('9', '26', '10', '123', '2020-10-22 09:46:24', 'file/video/images/123.jpg', '第一级', '1');
INSERT INTO `history` VALUES ('10', '38', '10', '青凌团建', '2020-10-22 09:56:46', 'file/video/images/合照.jpg', '合照', '2');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '测试01', '13741338355', '123', 'file/user/avatar/测试01-1574082105175.png', '23', '1995-12-07', '男');
INSERT INTO `user` VALUES ('2', '测试02', '13031516546', '111111', 'file/user/avatar/测试02-1572346933596.png', '20', '1999-10-20', '女');
INSERT INTO `user` VALUES ('3', '刘爸爸', '13538978202', '111111', 'file/user/avatar/刘爸爸-1574072845324.png', '21', '1998-10-20', '男');
INSERT INTO `user` VALUES ('4', '测试0003', '13031516546', '111111', 'file/user/avatar/d95cb271-12b0-4a96-8d60-e3650a262ed9.png', '22', '1997-01-21', '女');
INSERT INTO `user` VALUES ('5', '隔壁老王', '13484888464', '111111', 'file/user/avatar/4f456e40-1415-408d-adb1-c376456e4402.png', '0', null, null);
INSERT INTO `user` VALUES ('6', '123', '18671292320', '123321', 'file/user/avatar/f1b26b24-c705-4fa9-87f4-f078b4138639.jpg', '0', null, null);
INSERT INTO `user` VALUES ('7', '321', '18671292320', '123321', 'file/user/avatar/cc18b7e0-7da5-41f3-88f0-f7bb9f66b40a.jpg', '0', null, null);
INSERT INTO `user` VALUES ('8', '111', '18671292320', '123321', 'file/user/avatar/99a73093-83b2-4da1-a70a-12620c6cf355.jpg', '0', null, null);
INSERT INTO `user` VALUES ('9', '222', '18671292320', '123321', 'file/user/avatar/1dcbdf37-bbe2-478e-9235-c5005b79ebcb.jpg', '0', null, null);
INSERT INTO `user` VALUES ('10', '333', '18671292320', '321321', 'file/user/avatar/6060e4ed-c3bf-4a40-9618-682b8d0bb337.jpg', '0', null, null);

-- ----------------------------
-- Table structure for `user_collection`
-- ----------------------------
DROP TABLE IF EXISTS `user_collection`;
CREATE TABLE `user_collection` (
  `uid` int(11) DEFAULT NULL,
  `vid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_collection
-- ----------------------------
INSERT INTO `user_collection` VALUES ('9', '26');
INSERT INTO `user_collection` VALUES ('10', '26');

-- ----------------------------
-- Table structure for `videos`
-- ----------------------------
DROP TABLE IF EXISTS `videos`;
CREATE TABLE `videos` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `current_episode` int(4) DEFAULT NULL,
  `total_episode` int(4) DEFAULT NULL,
  `img_src` varchar(255) DEFAULT NULL,
  `starring` varchar(255) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `type` int(10) DEFAULT NULL,
  `location` varchar(20) DEFAULT NULL,
  `publish_date` year(4) DEFAULT NULL,
  `finished` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of videos
-- ----------------------------
INSERT INTO `videos` VALUES ('26', '123', '1', '12', 'file/video/images/123.jpg', '123', '1', '1', '内地', '2019', '0');
INSERT INTO `videos` VALUES ('27', 'asd', '123', '123', 'file/video/images/asd.jpg', '刘力 测试', '123', '1', '内地', '2019', '0');
INSERT INTO `videos` VALUES ('28', '测试', '123', '123', 'file/video/images/测试.jpg', '测试 测绘师', '123', '1', '美国', '2019', '1');
INSERT INTO `videos` VALUES ('34', 'ces', '1', '1', 'file/video/images/ces.jpg', '测试', '1', '1', '内地', '2019', '0');
INSERT INTO `videos` VALUES ('35', 'ce', '1', '1', 'file/video/images/hezhao.jpg', '测试', '1', '1', '内地', '2019', '0');
INSERT INTO `videos` VALUES ('38', '青凌团建', '4', '5', 'file/video/images/合照.jpg', '刘力 朱娟 毛玲', '团建游玩', '3', '日本', '2017', '0');

-- ----------------------------
-- Table structure for `video_area`
-- ----------------------------
DROP TABLE IF EXISTS `video_area`;
CREATE TABLE `video_area` (
  `video_id` int(11) DEFAULT NULL,
  `area_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video_area
-- ----------------------------
INSERT INTO `video_area` VALUES ('26', '1');
INSERT INTO `video_area` VALUES ('27', '1');
INSERT INTO `video_area` VALUES ('28', '8');
INSERT INTO `video_area` VALUES ('34', '1');
INSERT INTO `video_area` VALUES ('35', '1');
INSERT INTO `video_area` VALUES ('38', '4');

-- ----------------------------
-- Table structure for `video_category`
-- ----------------------------
DROP TABLE IF EXISTS `video_category`;
CREATE TABLE `video_category` (
  `video_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video_category
-- ----------------------------
INSERT INTO `video_category` VALUES ('26', '5');
INSERT INTO `video_category` VALUES ('26', '5');
INSERT INTO `video_category` VALUES ('26', '6');
INSERT INTO `video_category` VALUES ('27', '5');
INSERT INTO `video_category` VALUES ('27', '6');
INSERT INTO `video_category` VALUES ('27', '7');
INSERT INTO `video_category` VALUES ('28', '6');
INSERT INTO `video_category` VALUES ('28', '8');
INSERT INTO `video_category` VALUES ('28', '8');
INSERT INTO `video_category` VALUES ('34', '5');
INSERT INTO `video_category` VALUES ('34', '5');
INSERT INTO `video_category` VALUES ('34', '5');
INSERT INTO `video_category` VALUES ('35', '5');
INSERT INTO `video_category` VALUES ('35', '5');
INSERT INTO `video_category` VALUES ('35', '5');
INSERT INTO `video_category` VALUES ('38', '51');
INSERT INTO `video_category` VALUES ('38', '51');
INSERT INTO `video_category` VALUES ('38', '51');

-- ----------------------------
-- Table structure for `video_detail`
-- ----------------------------
DROP TABLE IF EXISTS `video_detail`;
CREATE TABLE `video_detail` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL,
  `episode` int(10) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `upload_date` datetime DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video_detail
-- ----------------------------
INSERT INTO `video_detail` VALUES ('19', '第一级', '1', '123-01.MP4', '2020-10-21 20:53:39', '26');
INSERT INTO `video_detail` VALUES ('20', '出发', '1', '青凌团建-01.MP4', '2020-10-22 09:55:54', '38');
INSERT INTO `video_detail` VALUES ('21', '合照', '2', '青凌团建-02.MP4', '2020-10-22 09:56:22', '38');
INSERT INTO `video_detail` VALUES ('22', '比赛', '3', '青凌团建-03.MP4', '2020-10-22 09:56:51', '38');


-- ----------------------------
-- Table structure for `carousel`  轮播图管理
-- ----------------------------
DROP TABLE IF EXISTS `carousel`;
CREATE TABLE `carousel` (
                            `id` int(10) NOT NULL AUTO_INCREMENT,
                            `title` varchar(20) DEFAULT NULL,
                            `imgSrc` varchar(255) DEFAULT NULL,
                            `status` int(10) DEFAULT 0,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of carousel
-- ----------------------------
INSERT INTO `carousel` VALUES ('1', '功夫', 'file/video/images/轮播图/功夫.jpg', '1');
INSERT INTO `carousel` VALUES ('2', '狄仁杰2四大天王', 'file/video/images/轮播图/狄仁杰2四大天王.jpg', '1');
INSERT INTO `carousel` VALUES ('3', '过春天', 'file/video/images/轮播图/过春天.jpg', '1');
INSERT INTO `carousel` VALUES ('4', '慎重勇者', 'file/video/images/轮播图/慎重勇者.jpg', '1');
INSERT INTO `carousel` VALUES ('5', '超人高中生们', 'file/video/images/轮播图/超人高中生们.jpg', '1');
INSERT INTO `carousel` VALUES ('6', '好小子', 'file/video/images/轮播图/好小子.jpg', '1');

-- ----------------------------
-- Table structure for `comment`评论表
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vId` int(11) NOT NULL,
  `uId` int(11) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
