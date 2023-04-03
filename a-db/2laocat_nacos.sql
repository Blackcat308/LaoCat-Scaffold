/*
 Navicat Premium Data Transfer

 Source Server         : local8.0
 Source Server Type    : MySQL
 Source Server Version : 80028 (8.0.28)
 Source Host           : 127.0.0.1:3306
 Source Schema         : laocat_nacos

 Target Server Type    : MySQL
 Target Server Version : 80028 (8.0.28)
 File Encoding         : 65001

 Date: 03/04/2023 11:10:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
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
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id` ASC, `group_id` ASC, `tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (3, 'LaoCat-auth-dev.yml', 'DEFAULT_GROUP', 'server:\n  port: 1011\n\nspring:\n  mvc:\n    static-path-pattern: /**\n  thymeleaf:\n    prefix: classpath:/templates/\n    suffix: .html\n    cache: false\n    \nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  metrics:\n    tags:\n      application: ${spring.application.name}', 'f309c498123ff79ed8ff023ced795896', '2022-06-14 07:52:06', '2023-03-31 02:20:38', NULL, '127.0.0.1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (4, 'LaoCat-getaway-dev.yml', 'DEFAULT_GROUP', 'server:\n  port: 2000\n\nspring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes:\n        - id: LaoCat-auth\n          uri: lb://LaoCat-auth\n          predicates:\n            - Path=/laocat_auth/**\n        - id: LaoCat-user\n          uri: lb://LaoCat-user\n          predicates:\n            - Path=/laocat_user/**\n\n    globalcors:\n        cors-configurations:\n          \'[/**]\':\n            allow-credentials: true\n            allowed-headers: \"*\"\n            max-age: 3600\n            allowedMethods:\n              - GET\n              - POST\n              - PUT\n              - OPTIONS\n              - DELETE\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  metrics:\n    tags:\n      application: ${spring.application.name}', '70d52a0212af6f3ce98bca09cb64ff37', '2022-06-14 07:53:03', '2023-03-31 02:20:54', NULL, '127.0.0.1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (11, 'LaoCat-user-dev.yml', 'DEFAULT_GROUP', 'server:\n  port: 1012\n\nspring:\n  datasource:\n    username: root\n    password: 12345\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/laocat_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8&rewriteBatchedStatements=true\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      initial-size: 5\n      min-idle: 5\n      max-active: 20\n      max-wait: 600000\n      min-evictable-idle-time-millis: 300000\n      validation-query: SELECT 1 FROM DUAL\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      time-between-eviction-runs-millis: 60000\n\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    banner: false\n    db-config:\n      id-type: id_worker_str\n      logic-delete-field: deleted\n      logic-delete-value: 1\n      logic-not-delete-value: 0\n  configuration:\n    call-setters-on-nulls: true\n#    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  metrics:\n    tags:\n      application: ${spring.application.name}', '5194d1cf066ed695df7fdb9465b46058', '2022-06-15 08:24:59', '2023-03-31 02:21:04', NULL, '127.0.0.1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (20, 'application-dev.yml', 'DEFAULT_GROUP', 'spring:\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    database: 5\n    password:\n    timeout: 50000\n    jedis:\n      pool:\n        max-active: 10\n        min-idle: 0\n        max-wait: 50000\n        max-idle: 10\n  jackson:\n    default-property-inclusion: non_null\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\nsecurity:\n  jwt:\n    secret: defaultSecret\n    effectiveTime: 3600\n    uniqueTag: laocat\n    autoRefreshRemainingTime: 600\n    allowMultiplePeople: false\n    dischargedGetMethod: true\n    ignoreUrl: \n        - /login\n        - /authentication\n        - /userInfo/**\n        - /favicon.ico\n        - /actuator/prometheus\n        - /userRole/userRoleByUserId\n\n\nxxl:\n  job:\n    accessToken: default_token\n    admin:\n      addresses: http://127.0.0.1:8858/xxl-job-admin\n    executor:\n      address:\n      appname: xxl-job-executor-sample\n      ip:\n      logpath: /data/applogs/xxl-job/jobhandler\n      logretentiondays: 30\n      port: 9999\n\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', 'e75427e4906878ab24b8c889613fb132', '2022-06-16 05:50:50', '2023-04-03 02:47:47', NULL, '127.0.0.1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (49, 'LaoCat-job-dev.yml', 'DEFAULT_GROUP', 'server:\r\n  port: 1013\r\n\r\n# spring:\r\n#   datasource:\r\n#     username: root\r\n#     password: 12345\r\n#     driver-class-name: com.mysql.cj.jdbc.Driver\r\n#     url: jdbc:mysql://127.0.0.1:3306/laocat_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8&rewriteBatchedStatements=true\r\n#     type: com.alibaba.druid.pool.DruidDataSource\r\n#     druid:\r\n#       initial-size: 5\r\n#       min-idle: 5\r\n#       max-active: 20\r\n#       max-wait: 600000\r\n#       min-evictable-idle-time-millis: 300000\r\n#       validation-query: SELECT 1 FROM DUAL\r\n#       test-while-idle: true\r\n#       test-on-borrow: false\r\n#       test-on-return: false\r\n#       time-between-eviction-runs-millis: 60000\r\n\r\n# mybatis-plus:\r\n#   mapper-locations: classpath:mapper/*.xml\r\n#   global-config:\r\n#     banner: false\r\n#     db-config:\r\n#       id-type: id_worker_str\r\n#       logic-delete-field: deleted\r\n#       logic-delete-value: 1\r\n#       logic-not-delete-value: 0\r\n#   configuration:\r\n#     call-setters-on-nulls: true\r\n# #    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n  metrics:\r\n    tags:\r\n      application: ${spring.application.name}', '79fa8ca36aedfbceb9aa11ad650a621e', '2023-04-03 01:40:00', '2023-04-03 01:40:00', NULL, '127.0.0.1', '', '', NULL, NULL, NULL, 'yaml', NULL);

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id` ASC, `group_id` ASC, `tenant_id` ASC, `datum_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id` ASC, `group_id` ASC, `tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id` ASC, `group_id` ASC, `tenant_id` ASC, `tag_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id` ASC, `tag_name` ASC, `tag_type` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint UNSIGNED NOT NULL,
  `nid` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create` ASC) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified` ASC) USING BTREE,
  INDEX `idx_did`(`data_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (3, 46, 'LaoCat-auth-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 1011\n\nspring:\n  mvc:\n    static-path-pattern: /**\n  thymeleaf:\n    prefix: classpath:/templates/\n    suffix: .html\n    cache: false', '75e2c7dbd781210ba011d21099be98ad', '2023-03-31 09:22:41', '2023-03-31 01:22:41', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (4, 47, 'LaoCat-getaway-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 2000\n\nspring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes:\n        - id: LaoCat-auth\n          uri: lb://LaoCat-auth\n          predicates:\n            - Path=/laocat_auth/**\n        - id: LaoCat-user\n          uri: lb://LaoCat-user\n          predicates:\n            - Path=/laocat_user/**\n\n    globalcors:\n        cors-configurations:\n          \'[/**]\':\n            allow-credentials: true\n            allowed-headers: \"*\"\n            max-age: 3600\n            allowedMethods:\n              - GET\n              - POST\n              - PUT\n              - OPTIONS\n              - DELETE', 'f441614f6347d2596c04c61783311e80', '2023-03-31 09:23:03', '2023-03-31 01:23:04', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (4, 48, 'LaoCat-getaway-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 2000\n\nspring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes:\n        - id: LaoCat-auth\n          uri: lb://LaoCat-auth\n          predicates:\n            - Path=/laocat_auth/**\n        - id: LaoCat-user\n          uri: lb://LaoCat-user\n          predicates:\n            - Path=/laocat_user/**\n\n    globalcors:\n        cors-configurations:\n          \'[/**]\':\n            allow-credentials: true\n            allowed-headers: \"*\"\n            max-age: 3600\n            allowedMethods:\n              - GET\n              - POST\n              - PUT\n              - OPTIONS\n              - DELETE\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include:\n  metrics:\n    tags:\n      application: ${spring.application.name}', '8bec604e1dbb111d595896dc068e01e3', '2023-03-31 09:23:24', '2023-03-31 01:23:24', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (3, 49, 'LaoCat-auth-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 1011\n\nspring:\n  mvc:\n    static-path-pattern: /**\n  thymeleaf:\n    prefix: classpath:/templates/\n    suffix: .html\n    cache: false\n    \nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include:\n  metrics:\n    tags:\n      application: ${spring.application.name}', 'c4226e349377c250b283e05397483ace', '2023-03-31 09:23:34', '2023-03-31 01:23:35', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (11, 50, 'LaoCat-user-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 1012\n\nspring:\n  datasource:\n    username: root\n    password: 123456\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/laocat_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8&rewriteBatchedStatements=true\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      initial-size: 5\n      min-idle: 5\n      max-active: 20\n      max-wait: 600000\n      min-evictable-idle-time-millis: 300000\n      validation-query: SELECT 1 FROM DUAL\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      time-between-eviction-runs-millis: 60000\n\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    banner: false\n    db-config:\n      id-type: id_worker_str\n      logic-delete-field: deleted\n      logic-delete-value: 1\n      logic-not-delete-value: 0\n  configuration:\n    call-setters-on-nulls: true\n#    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', '667e41c9a75c46d18d09bef4c64ae02f', '2023-03-31 09:23:49', '2023-03-31 01:23:50', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (11, 51, 'LaoCat-user-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 1012\n\nspring:\n  datasource:\n    username: root\n    password: 123456\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/laocat_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8&rewriteBatchedStatements=true\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      initial-size: 5\n      min-idle: 5\n      max-active: 20\n      max-wait: 600000\n      min-evictable-idle-time-millis: 300000\n      validation-query: SELECT 1 FROM DUAL\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      time-between-eviction-runs-millis: 60000\n\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    banner: false\n    db-config:\n      id-type: id_worker_str\n      logic-delete-field: deleted\n      logic-delete-value: 1\n      logic-not-delete-value: 0\n  configuration:\n    call-setters-on-nulls: true\n#    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: prometheus, health\n  metrics:\n    tags:\n      application: ${spring.application.name}', 'a3bf8b7c9ad212d7e410e3a9c11997c9', '2023-03-31 09:34:14', '2023-03-31 01:34:14', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (20, 52, 'application-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    database: 5\n    password:\n    timeout: 50000\n    jedis:\n      pool:\n        max-active: 10\n        min-idle: 0\n        max-wait: 50000\n        max-idle: 10\n  jackson:\n    default-property-inclusion: non_null\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\nsecurity:\n  jwt:\n    secret: defaultSecret\n    effectiveTime: 3600\n    uniqueTag: laocat\n    autoRefreshRemainingTime: 600\n    allowMultiplePeople: false\n    dischargedGetMethod: true\n    ignoreUrl: \n        - /login\n        - /authentication\n        - /userInfo/**\n        - /favicon.ico\n        - /hello\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', '792c12cfeb03b493bf2b662d10f0c22d', '2023-03-31 10:05:37', '2023-03-31 02:05:38', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (3, 53, 'LaoCat-auth-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 1011\n\nspring:\n  mvc:\n    static-path-pattern: /**\n  thymeleaf:\n    prefix: classpath:/templates/\n    suffix: .html\n    cache: false\n    \nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: prometheus, health\n  metrics:\n    tags:\n      application: ${spring.application.name}', 'cc18c4b29b2aa73b0f2ea3855710aedc', '2023-03-31 10:15:49', '2023-03-31 02:15:49', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (4, 54, 'LaoCat-getaway-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 2000\n\nspring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes:\n        - id: LaoCat-auth\n          uri: lb://LaoCat-auth\n          predicates:\n            - Path=/laocat_auth/**\n        - id: LaoCat-user\n          uri: lb://LaoCat-user\n          predicates:\n            - Path=/laocat_user/**\n\n    globalcors:\n        cors-configurations:\n          \'[/**]\':\n            allow-credentials: true\n            allowed-headers: \"*\"\n            max-age: 3600\n            allowedMethods:\n              - GET\n              - POST\n              - PUT\n              - OPTIONS\n              - DELETE\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: prometheus, health\n  metrics:\n    tags:\n      application: ${spring.application.name}', '086c45e447a5eea0170dc3d8f8657110', '2023-03-31 10:16:02', '2023-03-31 02:16:03', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (11, 55, 'LaoCat-user-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 1012\n\nspring:\n  datasource:\n    username: root\n    password: 12345\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/laocat_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8&rewriteBatchedStatements=true\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      initial-size: 5\n      min-idle: 5\n      max-active: 20\n      max-wait: 600000\n      min-evictable-idle-time-millis: 300000\n      validation-query: SELECT 1 FROM DUAL\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      time-between-eviction-runs-millis: 60000\n\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    banner: false\n    db-config:\n      id-type: id_worker_str\n      logic-delete-field: deleted\n      logic-delete-value: 1\n      logic-not-delete-value: 0\n  configuration:\n    call-setters-on-nulls: true\n#    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: prometheus, health\n  metrics:\n    tags:\n      application: ${spring.application.name}', '4b83750417330b3424702794479f8c4d', '2023-03-31 10:16:13', '2023-03-31 02:16:14', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (3, 56, 'LaoCat-auth-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 1011\n\nspring:\n  mvc:\n    static-path-pattern: /**\n  thymeleaf:\n    prefix: classpath:/templates/\n    suffix: .html\n    cache: false\n    \nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: *\n  metrics:\n    tags:\n      application: ${spring.application.name}', 'aa8f66f17e451f7e58956b2a773666c2', '2023-03-31 10:20:38', '2023-03-31 02:20:38', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (4, 57, 'LaoCat-getaway-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 2000\n\nspring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes:\n        - id: LaoCat-auth\n          uri: lb://LaoCat-auth\n          predicates:\n            - Path=/laocat_auth/**\n        - id: LaoCat-user\n          uri: lb://LaoCat-user\n          predicates:\n            - Path=/laocat_user/**\n\n    globalcors:\n        cors-configurations:\n          \'[/**]\':\n            allow-credentials: true\n            allowed-headers: \"*\"\n            max-age: 3600\n            allowedMethods:\n              - GET\n              - POST\n              - PUT\n              - OPTIONS\n              - DELETE\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: *\n  metrics:\n    tags:\n      application: ${spring.application.name}', '0e1397f1b89df7af94902d50ff065017', '2023-03-31 10:20:54', '2023-03-31 02:20:54', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (11, 58, 'LaoCat-user-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 1012\n\nspring:\n  datasource:\n    username: root\n    password: 12345\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/laocat_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8&rewriteBatchedStatements=true\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      initial-size: 5\n      min-idle: 5\n      max-active: 20\n      max-wait: 600000\n      min-evictable-idle-time-millis: 300000\n      validation-query: SELECT 1 FROM DUAL\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      time-between-eviction-runs-millis: 60000\n\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    banner: false\n    db-config:\n      id-type: id_worker_str\n      logic-delete-field: deleted\n      logic-delete-value: 1\n      logic-not-delete-value: 0\n  configuration:\n    call-setters-on-nulls: true\n#    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: *\n  metrics:\n    tags:\n      application: ${spring.application.name}', '294b671077af2b7077348ce7a1a1378f', '2023-03-31 10:21:04', '2023-03-31 02:21:04', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (0, 59, 'LaoCat-job-dev.yml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 1013\r\n\r\n# spring:\r\n#   datasource:\r\n#     username: root\r\n#     password: 12345\r\n#     driver-class-name: com.mysql.cj.jdbc.Driver\r\n#     url: jdbc:mysql://127.0.0.1:3306/laocat_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8&rewriteBatchedStatements=true\r\n#     type: com.alibaba.druid.pool.DruidDataSource\r\n#     druid:\r\n#       initial-size: 5\r\n#       min-idle: 5\r\n#       max-active: 20\r\n#       max-wait: 600000\r\n#       min-evictable-idle-time-millis: 300000\r\n#       validation-query: SELECT 1 FROM DUAL\r\n#       test-while-idle: true\r\n#       test-on-borrow: false\r\n#       test-on-return: false\r\n#       time-between-eviction-runs-millis: 60000\r\n\r\n# mybatis-plus:\r\n#   mapper-locations: classpath:mapper/*.xml\r\n#   global-config:\r\n#     banner: false\r\n#     db-config:\r\n#       id-type: id_worker_str\r\n#       logic-delete-field: deleted\r\n#       logic-delete-value: 1\r\n#       logic-not-delete-value: 0\r\n#   configuration:\r\n#     call-setters-on-nulls: true\r\n# #    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n  metrics:\r\n    tags:\r\n      application: ${spring.application.name}', '79fa8ca36aedfbceb9aa11ad650a621e', '2023-04-03 09:40:00', '2023-04-03 01:40:00', NULL, '127.0.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (20, 60, 'application-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    database: 5\n    password:\n    timeout: 50000\n    jedis:\n      pool:\n        max-active: 10\n        min-idle: 0\n        max-wait: 50000\n        max-idle: 10\n  jackson:\n    default-property-inclusion: non_null\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\nsecurity:\n  jwt:\n    secret: defaultSecret\n    effectiveTime: 3600\n    uniqueTag: laocat\n    autoRefreshRemainingTime: 600\n    allowMultiplePeople: false\n    dischargedGetMethod: true\n    ignoreUrl: \n        - /login\n        - /authentication\n        - /userInfo/**\n        - /favicon.ico\n        - /hello\n        - /actuator/prometheus\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', 'ade439bb34fb029dccd04c7bc8571116', '2023-04-03 09:46:35', '2023-04-03 01:46:36', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (20, 61, 'application-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    database: 5\n    password:\n    timeout: 50000\n    jedis:\n      pool:\n        max-active: 10\n        min-idle: 0\n        max-wait: 50000\n        max-idle: 10\n  jackson:\n    default-property-inclusion: non_null\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\nsecurity:\n  jwt:\n    secret: defaultSecret\n    effectiveTime: 3600\n    uniqueTag: laocat\n    autoRefreshRemainingTime: 600\n    allowMultiplePeople: false\n    dischargedGetMethod: true\n    ignoreUrl: \n        - /login\n        - /authentication\n        - /userInfo/**\n        - /favicon.ico\n        - /hello\n        - /actuator/prometheus\n\n\nxxl:\n  job:\n    accessToken: default_token\n    admin:\n      addresses: http://127.0.0.1:8858/xxl-job-admin\n    executor:\n      address:\n      appname: xxl-job-executor-sample\n      ip:\n      logpath: /data/applogs/xxl-job/jobhandler\n      logretentiondays: 30\n      port: 9999\n\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', '83de7f2642d7a7a7f4d0559c84b7ec09', '2023-04-03 10:07:11', '2023-04-03 02:07:11', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (20, 62, 'application-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    database: 5\n    password:\n    timeout: 50000\n    jedis:\n      pool:\n        max-active: 10\n        min-idle: 0\n        max-wait: 50000\n        max-idle: 10\n  jackson:\n    default-property-inclusion: non_null\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\nsecurity:\n  jwt:\n    secret: defaultSecret\n    effectiveTime: 3600\n    uniqueTag: laocat\n    autoRefreshRemainingTime: 600\n    allowMultiplePeople: false\n    dischargedGetMethod: true\n    ignoreUrl: \n        - /login\n        - /authentication\n        - /userInfo/**\n        - /favicon.ico\n        - /hello\n        - /actuator/prometheus\n\n\nxxl:\n  job:\n    accessToken: default_token\n    admin:\n      addresses: http://127.0.0.1:8858/xxl-job-admin\n    executor:\n      address:\n      appname: xxl-job-executor-sample\n      ip:\n      logpath: /data/applogs/xxl-job/jobhandler\n      logretentiondays: 30\n      port: 9999\n\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', '836e09ab3c8dd35a48d86efc2f559592', '2023-04-03 10:30:36', '2023-04-03 02:30:36', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (20, 63, 'application-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    database: 5\n    password:\n    timeout: 50000\n    jedis:\n      pool:\n        max-active: 10\n        min-idle: 0\n        max-wait: 50000\n        max-idle: 10\n  jackson:\n    default-property-inclusion: non_null\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\nsecurity:\n  jwt:\n    secret: defaultSecret\n    effectiveTime: 3600\n    uniqueTag: laocat\n    autoRefreshRemainingTime: 600\n    allowMultiplePeople: false\n    dischargedGetMethod: true\n    ignoreUrl: \n        - /login\n        - /authentication\n        - /userInfo/**\n        - /favicon.ico\n        - /actuator/prometheus\n\n\nxxl:\n  job:\n    accessToken: default_token\n    admin:\n      addresses: http://127.0.0.1:8858/xxl-job-admin\n    executor:\n      address:\n      appname: xxl-job-executor-sample\n      ip:\n      logpath: /data/applogs/xxl-job/jobhandler\n      logretentiondays: 30\n      port: 9999\n\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', '4d72a88d7a73ef5458871bcd744a53d9', '2023-04-03 10:31:24', '2023-04-03 02:31:25', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (20, 64, 'application-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    database: 5\n    password:\n    timeout: 50000\n    jedis:\n      pool:\n        max-active: 10\n        min-idle: 0\n        max-wait: 50000\n        max-idle: 10\n  jackson:\n    default-property-inclusion: non_null\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\nsecurity:\n  jwt:\n    secret: defaultSecret\n    effectiveTime: 3600\n    uniqueTag: laocat\n    autoRefreshRemainingTime: 600\n    allowMultiplePeople: false\n    dischargedGetMethod: true\n    ignoreUrl: \n        # - /login\n        - /authentication\n        - /userInfo/**\n        - /favicon.ico\n        - /actuator/prometheus\n\n\nxxl:\n  job:\n    accessToken: default_token\n    admin:\n      addresses: http://127.0.0.1:8858/xxl-job-admin\n    executor:\n      address:\n      appname: xxl-job-executor-sample\n      ip:\n      logpath: /data/applogs/xxl-job/jobhandler\n      logretentiondays: 30\n      port: 9999\n\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', 'cac62607ae440ddb893f64e695a102d1', '2023-04-03 10:37:06', '2023-04-03 02:37:06', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (20, 65, 'application-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    database: 5\n    password:\n    timeout: 50000\n    jedis:\n      pool:\n        max-active: 10\n        min-idle: 0\n        max-wait: 50000\n        max-idle: 10\n  jackson:\n    default-property-inclusion: non_null\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\nsecurity:\n  jwt:\n    secret: defaultSecret\n    effectiveTime: 3600\n    uniqueTag: laocat\n    autoRefreshRemainingTime: 600\n    allowMultiplePeople: false\n    dischargedGetMethod: true\n    ignoreUrl: \n        - /login\n        - /authentication\n        - /userInfo/**\n        - /favicon.ico\n        - /actuator/prometheus\n\n\nxxl:\n  job:\n    accessToken: default_token\n    admin:\n      addresses: http://127.0.0.1:8858/xxl-job-admin\n    executor:\n      address:\n      appname: xxl-job-executor-sample\n      ip:\n      logpath: /data/applogs/xxl-job/jobhandler\n      logretentiondays: 30\n      port: 9999\n\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', '4d72a88d7a73ef5458871bcd744a53d9', '2023-04-03 10:47:47', '2023-04-03 02:47:47', NULL, '127.0.0.1', 'U', '');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role` ASC, `resource` ASC, `action` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

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
  UNIQUE INDEX `idx_user_role`(`username` ASC, `role` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp` ASC, `tenant_id` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
