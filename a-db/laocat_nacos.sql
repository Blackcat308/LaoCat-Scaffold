/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : 127.0.0.1:3306
 Source Schema         : laocat_nacos

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 16/06/2022 16:40:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (3, 'LaoCat-auth-dev.yml', 'DEFAULT_GROUP', 'server:\n  port: 1011', '63a33259c40105a1d9c9dbbf7f784648', '2022-06-14 07:52:06', '2022-06-16 05:51:11', NULL, '127.0.0.1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (4, 'LaoCat-getaway-dev.yml', 'DEFAULT_GROUP', 'server:\n  port: 2000\n\nspring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes:\n        - id: LaoCat-auth\n          uri: lb://LaoCat-auth\n          predicates:\n            - Path=/laocat_auth/**\n        - id: LaoCat-user\n          uri: lb://LaoCat-user\n          predicates:\n            - Path=/laocat_user/**\n\n    globalcors:\n        cors-configurations:\n          \'[/**]\':\n            allow-credentials: true\n            allowed-headers: \"*\"\n            max-age: 3600\n            allowedMethods:\n              - GET\n              - POST\n              - PUT\n              - OPTIONS\n              - DELETE', 'f441614f6347d2596c04c61783311e80', '2022-06-14 07:53:03', '2022-06-16 05:55:29', NULL, '127.0.0.1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (11, 'LaoCat-user-dev.yml', 'DEFAULT_GROUP', 'server:\n  port: 1012\n\nspring:\n  datasource:\n    username: root\n    password: 123456\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/laocat_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8&rewriteBatchedStatements=true\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      initial-size: 5\n      min-idle: 5\n      max-active: 20\n      max-wait: 600000\n      min-evictable-idle-time-millis: 300000\n      validation-query: SELECT 1 FROM DUAL\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      time-between-eviction-runs-millis: 60000\n\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    banner: false\n    db-config:\n      id-type: id_worker_str\n      logic-delete-field: deleted\n      logic-delete-value: 1\n      logic-not-delete-value: 0\n  configuration:\n    call-setters-on-nulls: true\n#    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', '667e41c9a75c46d18d09bef4c64ae02f', '2022-06-15 08:24:59', '2022-06-16 05:55:45', NULL, '127.0.0.1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (20, 'application-dev.yml', 'DEFAULT_GROUP', 'security:\n  jwt:\n    secret: defaultSecret\n    effectiveTime: 3600\n    uniqueTag: laocat\n    autoRefreshRemainingTime: 600\n    allowMultiplePeople: false\n    dischargedGetMethod: true\n    ignoreUrl: \n        - /login\n        - /authentication\n        - /userInfo/**\n        - /favicon.ico\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', '6347cadabde9351f8a6be6d4c59c3eec', '2022-06-16 05:50:50', '2022-06-16 07:46:30', NULL, '127.0.0.1', '', '', '', '', '', 'yaml', '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint(64) UNSIGNED NOT NULL,
  `nid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (0, 1, 'LaoCat-auth.yml', 'DEFAULT_GROUP', '', 'test: 1', '0a18b0fb516e42becfb68564ce650d8f', '2022-06-14 15:31:17', '2022-06-14 07:31:18', NULL, '127.0.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (0, 2, 'LaoCat-getaway', 'DEFAULT_GROUP', '', 'test: 1', '0a18b0fb516e42becfb68564ce650d8f', '2022-06-14 15:50:43', '2022-06-14 07:50:44', NULL, '127.0.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (0, 3, 'LaoCat-auth-dev.yml', 'DEFAULT_GROUP', '', 'test: 1', '0a18b0fb516e42becfb68564ce650d8f', '2022-06-14 15:52:06', '2022-06-14 07:52:06', NULL, '127.0.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (0, 4, 'LaoCat-getaway-dev.yml', 'DEFAULT_GROUP', '', 'test: 1', '0a18b0fb516e42becfb68564ce650d8f', '2022-06-14 15:53:02', '2022-06-14 07:53:03', NULL, '127.0.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (1, 5, 'LaoCat-auth.yml', 'DEFAULT_GROUP', '', 'test: 1', '0a18b0fb516e42becfb68564ce650d8f', '2022-06-14 15:53:11', '2022-06-14 07:53:12', NULL, '127.0.0.1', 'D', '');
INSERT INTO `his_config_info` VALUES (2, 6, 'LaoCat-getaway', 'DEFAULT_GROUP', '', 'test: 1', '0a18b0fb516e42becfb68564ce650d8f', '2022-06-14 15:53:15', '2022-06-14 07:53:15', NULL, '127.0.0.1', 'D', '');
INSERT INTO `his_config_info` VALUES (4, 7, 'LaoCat-getaway-dev.yml', 'DEFAULT_GROUP', '', 'test: 1', '0a18b0fb516e42becfb68564ce650d8f', '2022-06-14 15:59:06', '2022-06-14 07:59:07', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (4, 8, 'LaoCat-getaway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes[0]:\n        id: LaoCat-auth\n        uri: lb://LaoCat-auth\n        predicates[0]: Path=/laocat/v1/auth/**\n\n        \n    globalcors:\n        cors-configurations:\n          \'[/**]\':\n            allow-credentials: true\n            allowed-headers: \"*\"\n            max-age: 3600\n            allowedMethods:\n              - GET\n              - POST\n              - PUT\n              - OPTIONS\n              - DELETE', '2699df6df62aefffeacd377631668404', '2022-06-14 16:05:18', '2022-06-14 08:05:18', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (4, 9, 'LaoCat-getaway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes[0]:\n        id: LaoCat-auth\n        uri: lb://LaoCat-auth\n        predicates[0]: Path=/laocat_auth/**\n\n        \n    globalcors:\n        cors-configurations:\n          \'[/**]\':\n            allow-credentials: true\n            allowed-headers: \"*\"\n            max-age: 3600\n            allowedMethods:\n              - GET\n              - POST\n              - PUT\n              - OPTIONS\n              - DELETE', '8c7f7e6f334087a6e280c17a8adc351e', '2022-06-14 16:15:42', '2022-06-14 08:15:42', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (4, 10, 'LaoCat-getaway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes:\n        - id: LaoCat-auth\n          uri: lb://LaoCat-auth\n          predicates:\n            - Path=/laocat_auth/**\n        \n    globalcors:\n        cors-configurations:\n          \'[/**]\':\n            allow-credentials: true\n            allowed-headers: \"*\"\n            max-age: 3600\n            allowedMethods:\n              - GET\n              - POST\n              - PUT\n              - OPTIONS\n              - DELETE', 'f11c8b3b2dea1dc32bc37a99bc77460f', '2022-06-14 16:20:35', '2022-06-14 08:20:35', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (3, 11, 'LaoCat-auth-dev.yml', 'DEFAULT_GROUP', '', 'test: 1', '0a18b0fb516e42becfb68564ce650d8f', '2022-06-15 14:49:40', '2022-06-15 06:49:40', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (3, 12, 'LaoCat-auth-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 1011\n\nsecurity:\n  jwt:\n    secret: defaultSecret\n    expiration: 3600\n    md5Key: randomKey\n    autoRefreshTokenExpiration: 600\n    preventsLogin: false\n    preventsGetMethod: true', '00db204986de076984a2ccf184758828', '2022-06-15 15:48:45', '2022-06-15 07:48:45', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (0, 13, 'LaoCat-user-dev.yml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 8892\r\n\r\nspring:\r\n  datasource:\r\n    username: root\r\n    password: 123456\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://127.0.0.1:3306/laocat_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8&rewriteBatchedStatements=true\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    druid:\r\n      initial-size: 5\r\n      min-idle: 5\r\n      max-active: 20\r\n      max-wait: 600000\r\n      min-evictable-idle-time-millis: 300000\r\n      validation-query: SELECT 1 FROM DUAL\r\n      test-while-idle: true\r\n      test-on-borrow: false\r\n      test-on-return: false\r\n      time-between-eviction-runs-millis: 60000\r\n\r\nmybatis-plus:\r\n  mapper-locations: classpath:mapper/*.xml\r\n  global-config:\r\n    banner: false\r\n    db-config:\r\n      id-type: id_worker_str\r\n      logic-delete-field: deleted\r\n      logic-delete-value: 1\r\n      logic-not-delete-value: 0\r\n  configuration:\r\n    call-setters-on-nulls: true\r\n#    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', '3378861a3ff4a748f456803e15535940', '2022-06-15 16:24:59', '2022-06-15 08:24:59', NULL, '127.0.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (11, 14, 'LaoCat-user-dev.yml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 8892\r\n\r\nspring:\r\n  datasource:\r\n    username: root\r\n    password: 123456\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://127.0.0.1:3306/laocat_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8&rewriteBatchedStatements=true\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    druid:\r\n      initial-size: 5\r\n      min-idle: 5\r\n      max-active: 20\r\n      max-wait: 600000\r\n      min-evictable-idle-time-millis: 300000\r\n      validation-query: SELECT 1 FROM DUAL\r\n      test-while-idle: true\r\n      test-on-borrow: false\r\n      test-on-return: false\r\n      time-between-eviction-runs-millis: 60000\r\n\r\nmybatis-plus:\r\n  mapper-locations: classpath:mapper/*.xml\r\n  global-config:\r\n    banner: false\r\n    db-config:\r\n      id-type: id_worker_str\r\n      logic-delete-field: deleted\r\n      logic-delete-value: 1\r\n      logic-not-delete-value: 0\r\n  configuration:\r\n    call-setters-on-nulls: true\r\n#    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', '3378861a3ff4a748f456803e15535940', '2022-06-15 16:25:32', '2022-06-15 08:25:33', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (4, 15, 'LaoCat-getaway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes:\n        - id: LaoCat-auth\n          uri: lb://LaoCat-auth\n          predicates:\n            - Path=/laocat_auth/**\n        \n    globalcors:\n        cors-configurations:\n          \'[/**]\':\n            allow-credentials: true\n            allowed-headers: \"*\"\n            max-age: 3600\n            allowedMethods:\n              - GET\n              - POST\n              - PUT\n              - OPTIONS\n              - DELETE', 'f11c8b3b2dea1dc32bc37a99bc77460f', '2022-06-15 16:26:07', '2022-06-15 08:26:08', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (4, 16, 'LaoCat-getaway-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 2000\n\nspring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes:\n        - id: LaoCat-auth\n          uri: lb://LaoCat-auth\n          predicates:\n            - Path=/laocat_auth/**\n        \n    globalcors:\n        cors-configurations:\n          \'[/**]\':\n            allow-credentials: true\n            allowed-headers: \"*\"\n            max-age: 3600\n            allowedMethods:\n              - GET\n              - POST\n              - PUT\n              - OPTIONS\n              - DELETE', 'b443f8ae7c4ef9a53a29d8bcd554b347', '2022-06-16 08:39:08', '2022-06-16 00:39:08', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (11, 17, 'LaoCat-user-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 1012\n\nspring:\n  datasource:\n    username: root\n    password: 123456\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/laocat_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8&rewriteBatchedStatements=true\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      initial-size: 5\n      min-idle: 5\n      max-active: 20\n      max-wait: 600000\n      min-evictable-idle-time-millis: 300000\n      validation-query: SELECT 1 FROM DUAL\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      time-between-eviction-runs-millis: 60000\n\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    banner: false\n    db-config:\n      id-type: id_worker_str\n      logic-delete-field: deleted\n      logic-delete-value: 1\n      logic-not-delete-value: 0\n  configuration:\n    call-setters-on-nulls: true\n#    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', '667e41c9a75c46d18d09bef4c64ae02f', '2022-06-16 08:48:02', '2022-06-16 00:48:03', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (4, 18, 'LaoCat-getaway-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 2000\n\nspring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes:\n        - id: LaoCat-auth\n          uri: lb://LaoCat-auth\n          predicates:\n            - Path=/laocat_auth/**\n        - id: LaoCat-user\n          uri: lb://LaoCat-user\n          predicates:\n            - Path=/laocat_user/**\n\n    globalcors:\n        cors-configurations:\n          \'[/**]\':\n            allow-credentials: true\n            allowed-headers: \"*\"\n            max-age: 3600\n            allowedMethods:\n              - GET\n              - POST\n              - PUT\n              - OPTIONS\n              - DELETE', 'f441614f6347d2596c04c61783311e80', '2022-06-16 08:48:16', '2022-06-16 00:48:16', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (3, 19, 'LaoCat-auth-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 1011\n\nsecurity:\n  jwt:\n    secret: defaultSecret\n    effectiveTime: 3600\n    uniqueTag: laocat\n    autoRefreshRemainingTime: 600\n    allowMultiplePeople: false\n    dischargedGetMethod: true', 'bc5103b9211de06c8f97b4152dabb4a4', '2022-06-16 08:48:30', '2022-06-16 00:48:30', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (11, 20, 'LaoCat-user-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 1012\n\nspring:\n  datasource:\n    username: root\n    password: 123456\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/laocat_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8&rewriteBatchedStatements=true\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      initial-size: 5\n      min-idle: 5\n      max-active: 20\n      max-wait: 600000\n      min-evictable-idle-time-millis: 300000\n      validation-query: SELECT 1 FROM DUAL\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      time-between-eviction-runs-millis: 60000\n\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    banner: false\n    db-config:\n      id-type: id_worker_str\n      logic-delete-field: deleted\n      logic-delete-value: 1\n      logic-not-delete-value: 0\n  configuration:\n    call-setters-on-nulls: true\n#    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', 'a904e6918580126da7f11feacf60c7df', '2022-06-16 11:17:55', '2022-06-16 03:17:56', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (11, 21, 'LaoCat-user-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 1012\n\nspring:\n  datasource:\n    username: root\n    password: 123456\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/laocat_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8&rewriteBatchedStatements=true\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      initial-size: 5\n      min-idle: 5\n      max-active: 20\n      max-wait: 600000\n      min-evictable-idle-time-millis: 300000\n      validation-query: SELECT 1 FROM DUAL\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      time-between-eviction-runs-millis: 60000\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', 'a2275ff23948a0571a93b407e86e166e', '2022-06-16 11:21:02', '2022-06-16 03:21:03', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (0, 22, 'application-dev.yml', 'DEFAULT_GROUP', '', '11', '6512bd43d9caa6e02c990b0a82652dca', '2022-06-16 13:50:50', '2022-06-16 05:50:50', NULL, '127.0.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (3, 23, 'LaoCat-auth-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 1011\n\nsecurity:\n  jwt:\n    secret: defaultSecret\n    effectiveTime: 3600\n    uniqueTag: laocat\n    autoRefreshRemainingTime: 600\n    allowMultiplePeople: false\n    dischargedGetMethod: true\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', '382695e9d299d2ff9e8e526daaca1ccb', '2022-06-16 13:51:10', '2022-06-16 05:51:11', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (20, 24, 'application-dev.yml', 'DEFAULT_GROUP', '', '11', '6512bd43d9caa6e02c990b0a82652dca', '2022-06-16 13:55:15', '2022-06-16 05:55:15', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (4, 25, 'LaoCat-getaway-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 2000\n\nspring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes:\n        - id: LaoCat-auth\n          uri: lb://LaoCat-auth\n          predicates:\n            - Path=/laocat_auth/**\n        - id: LaoCat-user\n          uri: lb://LaoCat-user\n          predicates:\n            - Path=/laocat_user/**\n\n    globalcors:\n        cors-configurations:\n          \'[/**]\':\n            allow-credentials: true\n            allowed-headers: \"*\"\n            max-age: 3600\n            allowedMethods:\n              - GET\n              - POST\n              - PUT\n              - OPTIONS\n              - DELETE\n  \nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', 'ecd5236e472e36936019b0b9a88b503c', '2022-06-16 13:55:29', '2022-06-16 05:55:29', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (11, 26, 'LaoCat-user-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 1012\n\nspring:\n  datasource:\n    username: root\n    password: 123456\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/laocat_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8&rewriteBatchedStatements=true\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      initial-size: 5\n      min-idle: 5\n      max-active: 20\n      max-wait: 600000\n      min-evictable-idle-time-millis: 300000\n      validation-query: SELECT 1 FROM DUAL\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      time-between-eviction-runs-millis: 60000\n\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    banner: false\n    db-config:\n      id-type: id_worker_str\n      logic-delete-field: deleted\n      logic-delete-value: 1\n      logic-not-delete-value: 0\n  configuration:\n    call-setters-on-nulls: true\n#    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', 'a904e6918580126da7f11feacf60c7df', '2022-06-16 13:55:44', '2022-06-16 05:55:45', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (20, 27, 'application-dev.yml', 'DEFAULT_GROUP', '', 'security:\n  jwt:\n    secret: defaultSecret\n    effectiveTime: 3600\n    uniqueTag: laocat\n    autoRefreshRemainingTime: 600\n    allowMultiplePeople: false\n    dischargedGetMethod: true\n    ignoreUrl: \n        - /login\n        - /authentication\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', 'd0a244e8afe6e6408938e7d020f34ff1', '2022-06-16 14:02:08', '2022-06-16 06:02:09', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (20, 28, 'application-dev.yml', 'DEFAULT_GROUP', '', 'security:\n  jwt:\n    secret: defaultSecret\n    effectiveTime: 3600\n    uniqueTag: laocat\n    autoRefreshRemainingTime: 600\n    allowMultiplePeople: false\n    dischargedGetMethod: true\n    ignoreUrl: \n        - /login\n        - /authentication\n        - /userInfo/userInfoByUserName\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', 'e16264d104ab43640e221a9be9c76614', '2022-06-16 14:46:53', '2022-06-16 06:46:53', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (20, 29, 'application-dev.yml', 'DEFAULT_GROUP', '', 'security:\n  jwt:\n    secret: defaultSecret\n    effectiveTime: 3600\n    uniqueTag: laocat\n    autoRefreshRemainingTime: 600\n    allowMultiplePeople: false\n    dischargedGetMethod: true\n    ignoreUrl: \n        - /login\n        - /authentication\n        - /userInfo/userInfoByUserName\n        - /favicon.ico\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', '0acd77822f3a5447615df46486236649', '2022-06-16 15:08:14', '2022-06-16 07:08:14', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (20, 30, 'application-dev.yml', 'DEFAULT_GROUP', '', 'security:\n  jwt:\n    secret: defaultSecret\n    effectiveTime: 3600\n    uniqueTag: laocat\n    autoRefreshRemainingTime: 600\n    allowMultiplePeople: false\n    dischargedGetMethod: true\n    ignoreUrl: \n        - /login\n        - /authentication\n        - /userInfo/userInfoByUserName\n        - /favicon.ico\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', '0acd77822f3a5447615df46486236649', '2022-06-16 15:46:30', '2022-06-16 07:46:30', NULL, '127.0.0.1', 'U', '');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permissions
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
