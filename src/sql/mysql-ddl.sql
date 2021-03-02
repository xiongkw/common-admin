CREATE DATABASE IF NOT EXISTS common_admin DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE common_admin;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `username` varchar(255) NOT NULL COMMENT '用户名',
    `password` varchar(255) NOT NULL COMMENT '密码',
    `gender` varchar(32) NOT NULL DEFAULT '0' COMMENT '性别',
    `birthday` datetime NULL COMMENT '出生年月',
    `phone` varchar(32) NULL COMMENT '电话',
    `email` varchar(128) NULL COMMENT 'email',
    `address` varchar(256) NULL COMMENT '地址',
    `cn_name` varchar(10) NULL COMMENT '中文姓名',
    `en_name` varchar(10) NULL COMMENT '英文姓名',
    `enabled` bit(1) NOT NULL DEFAULT 1 COMMENT '是否可用',
    `is_deleted` bit(1) NOT NULL DEFAULT 0 COMMENT '是否被删除',
    `status` varchar(20) NULL COMMENT '状态',
    `created_time` datetime NULL COMMENT '创建时间',
    `created_by` bigint(20) NULL COMMENT '创建人',
    `updated_time` datetime NULL COMMENT '修改时间',
    `updated_by` bigint(20) NULL COMMENT '修改人',
    `remark` varchar(512) NULL COMMENT '备注',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`name` varchar(256) NOT NULL COMMENT '名称',
	`parent_id` bigint(20) NULL COMMENT '父ID',
    `is_deleted` bigint(1) NOT NULL DEFAULT 0 COMMENT '是否被删除',
    `status` varchar(20) NULL COMMENT '状态',
    `created_time` datetime NULL COMMENT '创建时间',
    `created_by` bigint(20) NULL COMMENT '创建人',
    `updated_time` datetime NULL COMMENT '修改时间',
    `updated_by` bigint(20) NULL COMMENT '修改人',
    `remark` varchar(512) NULL COMMENT '备注',
	primary key(id)
);

DROP TABLE IF EXISTS `team_member`;
CREATE TABLE `team_member` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`team_id` bigint(20) NULL COMMENT '团队ID',
	`user_id` bigint(20) NULL COMMENT '成员ID',
	`is_leader` bit(1) NOT NULL DEFAULT 0 COMMENT '是否负责人',
    `status` varchar(20) NULL COMMENT '状态',
    `created_time` datetime NULL COMMENT '创建时间',
    `created_by` bigint(20) NULL COMMENT '创建人',
    `updated_time` datetime NULL COMMENT '修改时间',
    `updated_by` bigint(20) NULL COMMENT '修改人',
    `remark` varchar(512) NULL COMMENT '备注',
	primary key(id)
);

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `code` varchar(255) NOT NULL,
    `name` varchar(255) NOT NULL,
    `status` varchar(20) NULL COMMENT '状态',
    `created_time` datetime NULL COMMENT '创建时间',
    `created_by` bigint(20) NULL COMMENT '创建人',
    `updated_time` datetime NULL COMMENT '修改时间',
    `updated_by` bigint(20) NULL COMMENT '修改人',
    `remark` varchar(512) NULL COMMENT '备注',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id` bigint(20) NOT NULL,
    `role_id` bigint(20) NOT NULL,
    `status` varchar(20) NULL COMMENT '状态',
    `created_time` datetime NULL COMMENT '创建时间',
    `created_by` bigint(20) NULL COMMENT '创建人',
    `updated_time` datetime NULL COMMENT '修改时间',
    `updated_by` bigint(20) NULL COMMENT '修改人',
    `remark` varchar(512) NULL COMMENT '备注',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `role_id` bigint(20) NOT NULL,
    `resource_id` bigint(20) NOT NULL,
    `status` varchar(20) NULL COMMENT '状态',
    `created_time` datetime NULL COMMENT '创建时间',
    `created_by` bigint(20) NULL COMMENT '创建人',
    `updated_time` datetime NULL COMMENT '修改时间',
    `updated_by` bigint(20) NULL COMMENT '修改人',
    `remark` varchar(512) NULL COMMENT '备注',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `code` varchar(255) NOT NULL,
    `name` varchar(255) NOT NULL,
    `description` varchar(255) NULL,
    `pid` bigint(20) NULL,
    `status` varchar(20) NULL COMMENT '状态',
    `created_time` datetime NULL COMMENT '创建时间',
    `created_by` bigint(20) NULL COMMENT '创建人',
    `updated_time` datetime NULL COMMENT '修改时间',
    `updated_by` bigint(20) NULL COMMENT '修改人',
    `remark` varchar(512) NULL COMMENT '备注',
    PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `attr_spec`;
CREATE TABLE `attr_spec` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `code` varchar(50) NOT NULL COMMENT '属性编码',
    `name` varchar(100) NOT NULL COMMENT '属性名称',
    `type` varchar(50) NOT NULL COMMENT '属性类型',
    `description` varchar(2000) NULL COMMENT '说明',
    `status` varchar(20) NULL COMMENT '状态',
    `created_time` datetime NULL COMMENT '创建时间',
    `created_by` bigint(20) NULL COMMENT '创建人',
    `updated_time` datetime NULL COMMENT '修改时间',
    `updated_by` bigint(20) NULL COMMENT '修改人',
    `remark` varchar(512) NULL COMMENT '备注',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `attr_value`;
CREATE TABLE `attr_value` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `attr_spec_id` bigint(20) NOT NULL COMMENT '属性规格ID',
    `attr_value` varchar(50) NOT NULL COMMENT '属性值',
    `attr_name` varchar(100) NOT NULL COMMENT '属性名称',
    `sequence` integer NOT NULL COMMENT '排序',
    `description` varchar(2000) NULL COMMENT '说明',
    `status` varchar(20) NULL COMMENT '状态',
    `created_time` datetime NULL COMMENT '创建时间',
    `created_by` bigint(20) NULL COMMENT '创建人',
    `updated_time` datetime NULL COMMENT '修改时间',
    `updated_by` bigint(20) NULL COMMENT '修改人',
    `remark` varchar(512) NULL COMMENT '备注',
    PRIMARY KEY (`id`)
);