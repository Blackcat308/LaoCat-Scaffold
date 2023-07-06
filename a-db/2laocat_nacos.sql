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

 Date: 06/07/2023 17:33:40
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
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
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
  `encrypted_data_key` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id` ASC, `group_id` ASC, `tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (3, 'LaoCat-auth-dev.yml', 'DEFAULT_GROUP', 'server:\n  port: 1011\n\nspring:\n  mvc:\n    static-path-pattern: /**\n  thymeleaf:\n    prefix: classpath:/templates/\n    suffix: .html\n    cache: false\n    \nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  metrics:\n    tags:\n      application: ${spring.application.name}', 'f309c498123ff79ed8ff023ced795896', '2022-06-14 07:52:06', '2023-07-06 06:58:43', 'nacos', '192.168.1.81', 'LaoCat-Scaffold', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (4, 'LaoCat-getaway-dev.yml', 'DEFAULT_GROUP', 'server:\n  port: 2000\n\nspring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          lowerCaseServiceId: true\n      routes:\n        - id: LaoCat-auth\n          uri: lb://LaoCat-auth\n          predicates:\n            - Path=/laocat_auth/**\n          filters:\n            - SwaggerHeaderFilter\n            - StripPrefix=1\n        - id: LaoCat-user\n          uri: lb://LaoCat-user\n          predicates:\n            - Path=/laocat_user/**\n          filters:\n            - SwaggerHeaderFilter\n            - StripPrefix=1\n\n    globalcors:\n        cors-configurations:\n          \'[/**]\':\n            allow-credentials: true\n            allowed-headers: \"*\"\n            max-age: 3600\n            allowedMethods:\n              - GET\n              - POST\n              - PUT\n              - OPTIONS\n              - DELETE\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  metrics:\n    tags:\n      application: ${spring.application.name}', 'd18a2143b8b6939fdd3d595b834323d5', '2022-06-14 07:53:03', '2023-07-06 09:25:04', 'nacos', '192.168.1.81', 'LaoCat-Scaffold', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (11, 'LaoCat-user-dev.yml', 'DEFAULT_GROUP', 'server:\n  port: 1012\n\nspring:\n  datasource:\n    username: root\n    password: 12345\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/laocat_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8&rewriteBatchedStatements=true\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      initial-size: 5\n      min-idle: 5\n      max-active: 20\n      max-wait: 600000\n      min-evictable-idle-time-millis: 300000\n      validation-query: SELECT 1 FROM DUAL\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      time-between-eviction-runs-millis: 60000\n\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    banner: false\n    db-config:\n      id-type: id_worker_str\n      logic-delete-field: deleted\n      logic-delete-value: 1\n      logic-not-delete-value: 0\n  configuration:\n    call-setters-on-nulls: true\n#    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  metrics:\n    tags:\n      application: ${spring.application.name}', '5194d1cf066ed695df7fdb9465b46058', '2022-06-15 08:24:59', '2023-07-06 06:58:53', 'nacos', '192.168.1.81', 'LaoCat-Scaffold', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (20, 'application-dev.yml', 'DEFAULT_GROUP', 'spring:\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    database: 5\n    password:\n    timeout: 50000\n    jedis:\n      pool:\n        max-active: 10\n        min-idle: 0\n        max-wait: 50000\n        max-idle: 10\n  mvc:\n    pathmatch:\n      matching-strategy: ant_path_matcher\n  jackson:\n    default-property-inclusion: non_null\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\nsecurity:\n  jwt:\n    secret: defaultSecret\n    effectiveTime: 3600\n    uniqueTag: laocat\n    autoRefreshRemainingTime: 600\n    allowMultiplePeople: false\n    dischargedGetMethod: true\n    ignoreUrl: \n        - /login\n        - /authentication\n        - /userInfo/**\n        - /favicon.ico\n        - /actuator/prometheus\n        - /userRole/userRoleByUserId\n        - /**/v2/api-docs\n\n\nxxl:\n  job:\n    accessToken: default_token\n    admin:\n      addresses: http://127.0.0.1:8858/xxl-job-admin\n    executor:\n      address:\n      appname: xxl-job-executor-sample\n      ip:\n      logpath: /data/applogs/xxl-job/jobhandler\n      logretentiondays: 30\n      port: 9999\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', 'a9143fc5cbb91dae9b8ee7506272bdfd', '2022-06-16 05:50:50', '2023-07-06 07:07:20', 'nacos', '192.168.1.81', 'LaoCat-Scaffold', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (49, 'LaoCat-job-dev.yml', 'DEFAULT_GROUP', 'server:\n  port: 1013\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  metrics:\n    tags:\n      application: ${spring.application.name}', '073eb04f1a28213dbc4be8f5defb9d75', '2023-04-03 01:40:00', '2023-07-06 03:26:28', 'nacos', '192.168.1.81', 'LaoCat-Scaffold', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (60, 'LaoCat-file-dev.yml', 'DEFAULT_GROUP', 'server:\n  port: 3000\n\nfile:\n  handler: qiniu\n\nminio:\n  accessKey: FtciMIDpg1zFuRuT\n  secretKey: zAySpjKisMaPZD6KAOjUA5digqC0StCI\n  url: http://127.0.0.1:9000\n  default-expiry: 5\n\nqiniu:\n  accessKey: a7uOsXFaUUD86Ivwc7TQEufdW9h7tERhjhPAq851\n  secretKey: 8_9-TFZAgS3W60VJJfD36foIoSkRT-nTCDVBgVFW\n  bucket: laocat-store\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  metrics:\n    tags:\n      application: ${spring.application.name}', '0bf191c1ed0875de98519cba3f93348b', '2023-04-04 01:46:03', '2023-07-06 03:26:37', 'nacos', '192.168.1.81', 'LaoCat-Scaffold', '', '', '', '', 'yaml', '', '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id` ASC, `group_id` ASC, `tenant_id` ASC, `datum_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

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
  `encrypted_data_key` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id` ASC, `group_id` ASC, `tenant_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

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
  `encrypted_data_key` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create` ASC) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified` ASC) USING BTREE,
  INDEX `idx_did`(`data_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (0, 1, '12312312312', 'DEFAULT_GROUP', '', '31231231231231', '9b70e5a7b20df5ff6d8b5540da003617', '2023-07-06 11:17:09', '2023-07-06 03:17:09', NULL, '192.168.1.81', 'I', '', '');
INSERT INTO `his_config_info` VALUES (1, 2, '12312312312', 'DEFAULT_GROUP', '', '31231231231231', '9b70e5a7b20df5ff6d8b5540da003617', '2023-07-06 11:17:28', '2023-07-06 03:17:28', NULL, '192.168.1.81', 'D', '', '');
INSERT INTO `his_config_info` VALUES (3, 3, 'LaoCat-auth-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 1011\n\nspring:\n  mvc:\n    static-path-pattern: /**\n  thymeleaf:\n    prefix: classpath:/templates/\n    suffix: .html\n    cache: false\n    \nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  metrics:\n    tags:\n      application: ${spring.application.name}', 'f309c498123ff79ed8ff023ced795896', '2023-07-06 11:25:22', '2023-07-06 03:25:23', 'nacos', '192.168.1.81', 'U', '', '');
INSERT INTO `his_config_info` VALUES (4, 4, 'LaoCat-getaway-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 2000\n\nspring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes:\n        - id: LaoCat-auth\n          uri: lb://LaoCat-auth\n          predicates:\n            - Path=/laocat_auth/**\n        - id: LaoCat-user\n          uri: lb://LaoCat-user\n          predicates:\n            - Path=/laocat_user/**\n\n    globalcors:\n        cors-configurations:\n          \'[/**]\':\n            allow-credentials: true\n            allowed-headers: \"*\"\n            max-age: 3600\n            allowedMethods:\n              - GET\n              - POST\n              - PUT\n              - OPTIONS\n              - DELETE\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  metrics:\n    tags:\n      application: ${spring.application.name}', '70d52a0212af6f3ce98bca09cb64ff37', '2023-07-06 11:26:00', '2023-07-06 03:26:01', 'nacos', '192.168.1.81', 'U', '', '');
INSERT INTO `his_config_info` VALUES (11, 5, 'LaoCat-user-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 1012\n\nspring:\n  datasource:\n    username: root\n    password: 12345\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/laocat_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8&rewriteBatchedStatements=true\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      initial-size: 5\n      min-idle: 5\n      max-active: 20\n      max-wait: 600000\n      min-evictable-idle-time-millis: 300000\n      validation-query: SELECT 1 FROM DUAL\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      time-between-eviction-runs-millis: 60000\n\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    banner: false\n    db-config:\n      id-type: id_worker_str\n      logic-delete-field: deleted\n      logic-delete-value: 1\n      logic-not-delete-value: 0\n  configuration:\n    call-setters-on-nulls: true\n#    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  metrics:\n    tags:\n      application: ${spring.application.name}', '5194d1cf066ed695df7fdb9465b46058', '2023-07-06 11:26:11', '2023-07-06 03:26:12', 'nacos', '192.168.1.81', 'U', '', '');
INSERT INTO `his_config_info` VALUES (20, 6, 'application-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    database: 5\n    password:\n    timeout: 50000\n    jedis:\n      pool:\n        max-active: 10\n        min-idle: 0\n        max-wait: 50000\n        max-idle: 10\n  jackson:\n    default-property-inclusion: non_null\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\nsecurity:\n  jwt:\n    secret: defaultSecret\n    effectiveTime: 3600\n    uniqueTag: laocat\n    autoRefreshRemainingTime: 600\n    allowMultiplePeople: false\n    dischargedGetMethod: true\n    ignoreUrl: \n        - /login\n        - /authentication\n        - /userInfo/**\n        - /favicon.ico\n        - /actuator/prometheus\n        - /userRole/userRoleByUserId\n\n\nxxl:\n  job:\n    accessToken: default_token\n    admin:\n      addresses: http://127.0.0.1:8858/xxl-job-admin\n    executor:\n      address:\n      appname: xxl-job-executor-sample\n      ip:\n      logpath: /data/applogs/xxl-job/jobhandler\n      logretentiondays: 30\n      port: 9999\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', 'fa9709031f07527b40520fdbd963b217', '2023-07-06 11:26:20', '2023-07-06 03:26:20', 'nacos', '192.168.1.81', 'U', '', '');
INSERT INTO `his_config_info` VALUES (49, 7, 'LaoCat-job-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 1013\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  metrics:\n    tags:\n      application: ${spring.application.name}', '073eb04f1a28213dbc4be8f5defb9d75', '2023-07-06 11:26:28', '2023-07-06 03:26:28', 'nacos', '192.168.1.81', 'U', '', '');
INSERT INTO `his_config_info` VALUES (60, 8, 'LaoCat-file-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 3000\n\nfile:\n  handler: qiniu\n\nminio:\n  accessKey: FtciMIDpg1zFuRuT\n  secretKey: zAySpjKisMaPZD6KAOjUA5digqC0StCI\n  url: http://127.0.0.1:9000\n  default-expiry: 5\n\nqiniu:\n  accessKey: a7uOsXFaUUD86Ivwc7TQEufdW9h7tERhjhPAq851\n  secretKey: 8_9-TFZAgS3W60VJJfD36foIoSkRT-nTCDVBgVFW\n  bucket: laocat-store\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  metrics:\n    tags:\n      application: ${spring.application.name}', '0bf191c1ed0875de98519cba3f93348b', '2023-07-06 11:26:37', '2023-07-06 03:26:37', 'nacos', '192.168.1.81', 'U', '', '');
INSERT INTO `his_config_info` VALUES (3, 9, 'LaoCat-auth-dev.yml', 'DEFAULT_GROUP', 'LaoCat-Scaffold', 'server:\n  port: 1011\n\nspring:\n  mvc:\n    static-path-pattern: /**\n  thymeleaf:\n    prefix: classpath:/templates/\n    suffix: .html\n    cache: false\n    \nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  metrics:\n    tags:\n      application: ${spring.application.name}', 'f309c498123ff79ed8ff023ced795896', '2023-07-06 14:11:19', '2023-07-06 06:11:20', 'nacos', '192.168.1.81', 'U', '', '');
INSERT INTO `his_config_info` VALUES (3, 10, 'LaoCat-auth-dev.yml', 'DEFAULT_GROUP', 'LaoCat-Scaffold', 'server:\n  port: 1011\n\nspring:\n  mvc:\n    static-path-pattern: /**\n    pathmatch:\n      matching-strategy: ant_path_matcher\n  thymeleaf:\n    prefix: classpath:/templates/\n    suffix: .html\n    cache: false\n    \nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  metrics:\n    tags:\n      application: ${spring.application.name}', '55b7a361712c34971df85436c7d9af8e', '2023-07-06 14:26:17', '2023-07-06 06:26:17', 'nacos', '192.168.1.81', 'U', '', '');
INSERT INTO `his_config_info` VALUES (3, 11, 'LaoCat-auth-dev.yml', 'DEFAULT_GROUP', 'LaoCat-Scaffold', 'server:\n  port: 1011\n\nspring:\n  mvc:\n    static-path-pattern: /**\n  thymeleaf:\n    prefix: classpath:/templates/\n    suffix: .html\n    cache: false\n    \nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  metrics:\n    tags:\n      application: ${spring.application.name}', 'f309c498123ff79ed8ff023ced795896', '2023-07-06 14:57:26', '2023-07-06 06:57:27', 'nacos', '192.168.1.81', 'U', '', '');
INSERT INTO `his_config_info` VALUES (11, 12, 'LaoCat-user-dev.yml', 'DEFAULT_GROUP', 'LaoCat-Scaffold', 'server:\n  port: 1012\n\nspring:\n  datasource:\n    username: root\n    password: 12345\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/laocat_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8&rewriteBatchedStatements=true\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      initial-size: 5\n      min-idle: 5\n      max-active: 20\n      max-wait: 600000\n      min-evictable-idle-time-millis: 300000\n      validation-query: SELECT 1 FROM DUAL\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      time-between-eviction-runs-millis: 60000\n\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    banner: false\n    db-config:\n      id-type: id_worker_str\n      logic-delete-field: deleted\n      logic-delete-value: 1\n      logic-not-delete-value: 0\n  configuration:\n    call-setters-on-nulls: true\n#    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  metrics:\n    tags:\n      application: ${spring.application.name}', '5194d1cf066ed695df7fdb9465b46058', '2023-07-06 14:58:09', '2023-07-06 06:58:09', 'nacos', '192.168.1.81', 'U', '', '');
INSERT INTO `his_config_info` VALUES (20, 13, 'application-dev.yml', 'DEFAULT_GROUP', 'LaoCat-Scaffold', 'spring:\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    database: 5\n    password:\n    timeout: 50000\n    jedis:\n      pool:\n        max-active: 10\n        min-idle: 0\n        max-wait: 50000\n        max-idle: 10\n  jackson:\n    default-property-inclusion: non_null\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\nsecurity:\n  jwt:\n    secret: defaultSecret\n    effectiveTime: 3600\n    uniqueTag: laocat\n    autoRefreshRemainingTime: 600\n    allowMultiplePeople: false\n    dischargedGetMethod: true\n    ignoreUrl: \n        - /login\n        - /authentication\n        - /userInfo/**\n        - /favicon.ico\n        - /actuator/prometheus\n        - /userRole/userRoleByUserId\n\n\nxxl:\n  job:\n    accessToken: default_token\n    admin:\n      addresses: http://127.0.0.1:8858/xxl-job-admin\n    executor:\n      address:\n      appname: xxl-job-executor-sample\n      ip:\n      logpath: /data/applogs/xxl-job/jobhandler\n      logretentiondays: 30\n      port: 9999\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', 'fa9709031f07527b40520fdbd963b217', '2023-07-06 14:58:34', '2023-07-06 06:58:35', 'nacos', '192.168.1.81', 'U', '', '');
INSERT INTO `his_config_info` VALUES (3, 14, 'LaoCat-auth-dev.yml', 'DEFAULT_GROUP', 'LaoCat-Scaffold', 'server:\n  port: 1011\n\nspring:\n  mvc:\n    static-path-pattern: /**\n  pathmatch:\n    matching-strategy: ant_path_matcher\n  thymeleaf:\n    prefix: classpath:/templates/\n    suffix: .html\n    cache: false\n    \nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  metrics:\n    tags:\n      application: ${spring.application.name}', 'd423580874bfbbc77aaf97d2e107f143', '2023-07-06 14:58:42', '2023-07-06 06:58:43', 'nacos', '192.168.1.81', 'U', '', '');
INSERT INTO `his_config_info` VALUES (11, 15, 'LaoCat-user-dev.yml', 'DEFAULT_GROUP', 'LaoCat-Scaffold', 'server:\n  port: 1012\n\nspring:\n  datasource:\n    username: root\n    password: 12345\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/laocat_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8&rewriteBatchedStatements=true\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      initial-size: 5\n      min-idle: 5\n      max-active: 20\n      max-wait: 600000\n      min-evictable-idle-time-millis: 300000\n      validation-query: SELECT 1 FROM DUAL\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      time-between-eviction-runs-millis: 60000\n  mvc:\n    pathmatch:\n      matching-strategy: ant_path_matcher\n\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    banner: false\n    db-config:\n      id-type: id_worker_str\n      logic-delete-field: deleted\n      logic-delete-value: 1\n      logic-not-delete-value: 0\n  configuration:\n    call-setters-on-nulls: true\n#    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  metrics:\n    tags:\n      application: ${spring.application.name}', 'd3c7e5b19637de48b6c2573e089308e4', '2023-07-06 14:58:53', '2023-07-06 06:58:53', 'nacos', '192.168.1.81', 'U', '', '');
INSERT INTO `his_config_info` VALUES (20, 16, 'application-dev.yml', 'DEFAULT_GROUP', 'LaoCat-Scaffold', 'spring:\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    database: 5\n    password:\n    timeout: 50000\n    jedis:\n      pool:\n        max-active: 10\n        min-idle: 0\n        max-wait: 50000\n        max-idle: 10\n  mvc:\n    pathmatch:\n      matching-strategy: ant_path_matcher\n  jackson:\n    default-property-inclusion: non_null\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\nsecurity:\n  jwt:\n    secret: defaultSecret\n    effectiveTime: 3600\n    uniqueTag: laocat\n    autoRefreshRemainingTime: 600\n    allowMultiplePeople: false\n    dischargedGetMethod: true\n    ignoreUrl: \n        - /login\n        - /authentication\n        - /userInfo/**\n        - /favicon.ico\n        - /actuator/prometheus\n        - /userRole/userRoleByUserId\n\n\nxxl:\n  job:\n    accessToken: default_token\n    admin:\n      addresses: http://127.0.0.1:8858/xxl-job-admin\n    executor:\n      address:\n      appname: xxl-job-executor-sample\n      ip:\n      logpath: /data/applogs/xxl-job/jobhandler\n      logretentiondays: 30\n      port: 9999\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', '42e3f9e6367231e97c580b230a8effa9', '2023-07-06 15:03:03', '2023-07-06 07:03:03', 'nacos', '192.168.1.81', 'U', '', '');
INSERT INTO `his_config_info` VALUES (20, 17, 'application-dev.yml', 'DEFAULT_GROUP', 'LaoCat-Scaffold', 'spring:\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    database: 5\n    password:\n    timeout: 50000\n    jedis:\n      pool:\n        max-active: 10\n        min-idle: 0\n        max-wait: 50000\n        max-idle: 10\n  mvc:\n    pathmatch:\n      matching-strategy: ant_path_matcher\n  jackson:\n    default-property-inclusion: non_null\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\nsecurity:\n  jwt:\n    secret: defaultSecret\n    effectiveTime: 3600\n    uniqueTag: laocat\n    autoRefreshRemainingTime: 600\n    allowMultiplePeople: false\n    dischargedGetMethod: true\n    ignoreUrl: \n        - /login\n        - /authentication\n        - /userInfo/**\n        - /favicon.ico\n        - /actuator/prometheus\n        - /userRole/userRoleByUserId\n        - /v2/api-docs\n\n\nxxl:\n  job:\n    accessToken: default_token\n    admin:\n      addresses: http://127.0.0.1:8858/xxl-job-admin\n    executor:\n      address:\n      appname: xxl-job-executor-sample\n      ip:\n      logpath: /data/applogs/xxl-job/jobhandler\n      logretentiondays: 30\n      port: 9999\n\nlogging:\n  level:\n    org.laocat: debug\n  file:\n    name: /logs/${spring.application.name}/${spring.application.name}.log\n  logback:\n    rollingpolicy:\n      max-file-size: 20MB\n      max-history: 365\n      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.log.gz\n  pattern:\n    console: \"%clr(%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %X{NAME} %clr(:){faint} %X{IP} %clr(:){faint} %X{REQUEST_ID} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    file: \"%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %X{NAME} : %X{IP} : %X{REQUEST_ID} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}\"\n    dateformat: \"yyyy-MM-dd HH:mm:ss.SSS\"', '47d6a0dcd345ad6067a7c86eb3deecc1', '2023-07-06 15:07:19', '2023-07-06 07:07:20', 'nacos', '192.168.1.81', 'U', '', '');
INSERT INTO `his_config_info` VALUES (4, 18, 'LaoCat-getaway-dev.yml', 'DEFAULT_GROUP', 'LaoCat-Scaffold', 'server:\n  port: 2000\n\nspring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes:\n        - id: LaoCat-auth\n          uri: lb://LaoCat-auth\n          predicates:\n            - Path=/laocat_auth/**\n        - id: LaoCat-user\n          uri: lb://LaoCat-user\n          predicates:\n            - Path=/laocat_user/**\n\n    globalcors:\n        cors-configurations:\n          \'[/**]\':\n            allow-credentials: true\n            allowed-headers: \"*\"\n            max-age: 3600\n            allowedMethods:\n              - GET\n              - POST\n              - PUT\n              - OPTIONS\n              - DELETE\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  metrics:\n    tags:\n      application: ${spring.application.name}', '70d52a0212af6f3ce98bca09cb64ff37', '2023-07-06 17:25:04', '2023-07-06 09:25:04', 'nacos', '192.168.1.81', 'U', '', '');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role` ASC, `resource` ASC, `action` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permissions
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username` ASC, `role` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
