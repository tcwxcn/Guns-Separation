/****** Object:  table [dbo].[database_info]  Script Date: 2019-11-23 22:13:11 ******/
GO


use [guns]
GO


CREATE TABLE [dbo].[database_info]
(
	[db_id] bigint NOT NULL ,
	[db_name] varchar(255) NOT NULL ,
	[jdbc_driver] varchar(255) NOT NULL ,
	[user_name] varchar(255) NOT NULL ,
	[password] varchar(255) NOT NULL ,
	[jdbc_url] varchar(2000) NOT NULL ,
	[remarks] varchar(255) NULL ,
	[create_time] datetime NULL 
)
ON [PRIMARY];
GO


EXECUTE sp_addextendedproperty  N'MS_Description', N'', N'SCHEMA', N'dbo', N'TABLE', N'database_info', NULL, NULL;
GO

ALTER TABLE [dbo].[database_info] ADD CONSTRAINT [PK_database_info] PRIMARY KEY CLUSTERED 
(
[db_id]
)
WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON);
GO



insert into [dbo].[database_info]([db_id],[db_name],[jdbc_driver],[user_name],[password],[jdbc_url],[remarks],[create_time]) values
('1198241099289976833','master','net.sourceforge.jtds.jdbc.Driver','guns','Guns123456','jdbc:jtds:sqlserver://rm-2zem3l79b11r1p284jo.sqlserver.rds.aliyuncs.com:3433;DatabaseName=guns','主数据源，项目启动数据源！','2019-11-23 22:05:14.000')

;
/****** Object:  table [dbo].[oa_myleave]  Script Date: 2019-11-23 22:13:11 ******/
GO


use [guns]
GO


CREATE TABLE [dbo].[oa_myleave]
(
	[myleave_id] varchar(100) NOT NULL ,
	[username] varchar(100) NOT NULL ,
	[type] varchar(100) NOT NULL ,
	[starttime] varchar(100) NOT NULL ,
	[endtime] varchar(100) NOT NULL ,
	[whenlong] varchar(50) NOT NULL ,
	[reason] varchar(1000) NULL 
)
ON [PRIMARY];
GO


EXECUTE sp_addextendedproperty  N'MS_Description', N'', N'SCHEMA', N'dbo', N'TABLE', N'oa_myleave', NULL, NULL;
GO

ALTER TABLE [dbo].[oa_myleave] ADD CONSTRAINT [PK_oa_myleave] PRIMARY KEY CLUSTERED 
(
[myleave_id]
)
WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON);
GO





/****** Object:  table [dbo].[oauth_user_info]  Script Date: 2019-11-23 22:13:11 ******/
GO


use [guns]
GO


CREATE TABLE [dbo].[oauth_user_info]
(
	[oauth_id] bigint NOT NULL ,
	[user_id] bigint NOT NULL ,
	[nick_name] varchar(255) NULL ,
	[avatar] varchar(500) NULL ,
	[blog] varchar(255) NULL ,
	[company] varchar(255) NULL ,
	[location] varchar(255) NULL ,
	[email] varchar(255) NULL ,
	[remark] varchar(255) NULL ,
	[gender] int NULL ,
	[source] varchar(255) NULL ,
	[token] varchar(255) NULL ,
	[uuid] varchar(255) NULL ,
	[create_time] datetime NULL ,
	[create_user] bigint NULL ,
	[update_time] datetime NULL ,
	[update_user] bigint NULL 
)
ON [PRIMARY];
GO


EXECUTE sp_addextendedproperty  N'MS_Description', N'', N'SCHEMA', N'dbo', N'TABLE', N'oauth_user_info', NULL, NULL;
GO

ALTER TABLE [dbo].[oauth_user_info] ADD CONSTRAINT [PK_oauth_user_info] PRIMARY KEY CLUSTERED 
(
[oauth_id]
)
WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON);
GO





/****** Object:  table [dbo].[sys_config]  Script Date: 2019-11-23 22:13:12 ******/
GO


use [guns]
GO


CREATE TABLE [dbo].[sys_config]
(
	[id] bigint NOT NULL ,
	[name] varchar(200) NOT NULL ,
	[code] varchar(200) NOT NULL ,
	[dict_flag] char(1) NOT NULL ,
	[dict_type_id] bigint NULL ,
	[value] varchar(200) NOT NULL ,
	[remark] varchar(200) NULL ,
	[create_time] datetime NULL ,
	[create_user] bigint NULL ,
	[update_time] datetime NULL ,
	[update_user] bigint NULL 
)
ON [PRIMARY];
GO


EXECUTE sp_addextendedproperty  N'MS_Description', N'', N'SCHEMA', N'dbo', N'TABLE', N'sys_config', NULL, NULL;
GO

ALTER TABLE [dbo].[sys_config] ADD CONSTRAINT [PK_sys_config] PRIMARY KEY CLUSTERED 
(
[id]
)
WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON);
GO



insert into [dbo].[sys_config]([id],[name],[code],[dict_flag],[dict_type_id],[value],[remark],[create_time],[create_user],[update_time],[update_user]) values
('1143324237579165697','验证码开关','GUNS_KAPTCHA_OPEN','Y','1106120265689055233','DISABLE','是否开启验证码','2019-06-24 12:46:43.000','1','2019-06-25 09:04:42.000','1'),
('1143386834613694465','阿里云短信的keyId','GUNS_SMS_ACCESSKEY_ID','N',null,'xxxkey','阿里云短信的密钥key','2019-06-25 13:13:59.000','1','2019-06-25 13:19:21.000','1'),
('1143386953933254657','阿里云短信的secret','GUNS_SMS_ACCESSKEY_SECRET','N',null,'xxxsecret','阿里云短信的secret','2019-06-25 13:14:28.000','1',null,null),
('1143387023449649154','阿里云短信的签名','GUNS_SMS_SIGN_NAME','N',null,'xxxsign','阿里云短信的签名','2019-06-25 13:14:44.000','1',null,null),
('1143387131109044225','阿里云短信登录的模板号','GUNS_SMS_LOGIN_TEMPLATE_CODE','N',null,'SMS_XXXXXX','阿里云短信登录的模板号','2019-06-25 13:15:10.000','1',null,null),
('1143387225019510785','验证码短信失效时间','GUNS_SMS_INVALIDATE_MINUTES','N',null,'2','验证码短信失效时间','2019-06-25 13:15:32.000','1',null,null),
('1143468689664876546','管理系统名称','GUNS_SYSTEM_NAME','N',null,'Guns快速开发平台','管理系统名称','2019-06-25 18:39:15.000','1',null,null),
('1143468867767607297','默认系统密码','GUNS_DEFAULT_PASSWORD','N',null,'111111','默认系统密码','2019-06-25 18:39:57.000','1',null,null),
('1143469008025133058','OAuth2登录用户的账号标识','GUNS_OAUTH2_PREFIX','N',null,'oauth2','OAuth2登录用户的账号标识','2019-06-25 18:40:31.000','1',null,null),
('1145207130463191041','顶部导航条是否开启','GUNS_DEFAULT_ADVERT','Y','1106120265689055233','ENABLE','顶部Guns广告是否开启','2019-06-30 13:47:11.000','1','2019-06-30 13:47:20.000','1'),
('1145915627211370498','Guns发布的编号','GUNS_SYSTEM_RELEASE_VERSION','N',null,'20191121','用于防止浏览器缓存相关的js和css','2019-07-02 12:42:30.000','1',null,null),
('1145915627211370499','文件上传路径','GUNS_FILE_UPLOAD_PATH','N',null,'D:/tmp','文件上传默认目录','2019-08-30 09:10:40.000','1','2019-11-23 22:02:35.973','1'),
('1145915627211370500','BPMN文件上传路径','GUNS_BPMN_FILE_UPLOAD_PATH','N',null,'D:/tmp','工作流文件上传默认目录','2019-08-30 09:10:40.000','1','2019-11-23 22:02:41.570','1'),
('1145915627211370501','获取系统地密钥过期时间','GUNS_JWT_SECRET_EXPIRE','N',null,'86400','获取系统地密钥过期时间（单位：秒），默认1天','2019-10-16 23:02:39.000','1',null,null),
('1145915627211370502','获取token的header标识','GUNS_TOKEN_HEADER_NAME','N',null,'Authorization','获取token的header标识','2019-10-16 23:02:39.000','1',null,null),
('1145915627211370503','获取租户是否开启的标识','GUNS_TENANT_OPEN','Y','1106120265689055233','DISABLE','获取租户是否开启的标识，默认是关的','2019-10-16 23:02:39.000','1',null,null)

;
/****** Object:  table [dbo].[sys_dept]  Script Date: 2019-11-23 22:13:12 ******/
GO


use [guns]
GO


CREATE TABLE [dbo].[sys_dept]
(
	[dept_id] bigint NOT NULL ,
	[pid] bigint NULL ,
	[pids] varchar(512) NULL ,
	[simple_name] varchar(45) NULL ,
	[full_name] varchar(255) NULL ,
	[description] varchar(255) NULL ,
	[version] int NULL ,
	[sort] int NULL ,
	[create_time] datetime NULL ,
	[update_time] datetime NULL ,
	[create_user] bigint NULL ,
	[update_user] bigint NULL 
)
ON [PRIMARY];
GO


EXECUTE sp_addextendedproperty  N'MS_Description', N'', N'SCHEMA', N'dbo', N'TABLE', N'sys_dept', NULL, NULL;
GO

ALTER TABLE [dbo].[sys_dept] ADD  CONSTRAINT [DF_sys_dept_pid] DEFAULT ((0)) FOR [pid];
GO
ALTER TABLE [dbo].[sys_dept] ADD  CONSTRAINT [DF_sys_dept_pids] DEFAULT ('') FOR [pids];
GO
ALTER TABLE [dbo].[sys_dept] ADD CONSTRAINT [PK_sys_dept] PRIMARY KEY CLUSTERED 
(
[dept_id]
)
WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON);
GO



insert into [dbo].[sys_dept]([dept_id],[pid],[pids],[simple_name],[full_name],[description],[version],[sort],[create_time],[update_time],[create_user],[update_user]) values
('24','0','[0],','总公司','总公司','',null,'1',null,null,null,null),
('25','24','[0],[24],','开发部','开发部','',null,'2',null,null,null,null),
('26','24','[0],[24],','运营部','运营部','',null,'3',null,null,null,null),
('27','24','[0],[24],','战略部','战略部','',null,'4',null,null,null,null)

;
/****** Object:  table [dbo].[sys_dict]  Script Date: 2019-11-23 22:13:12 ******/
GO


use [guns]
GO


CREATE TABLE [dbo].[sys_dict]
(
	[dict_id] bigint NOT NULL ,
	[dict_type_id] bigint NOT NULL ,
	[code] varchar(50) NOT NULL ,
	[name] varchar(255) NOT NULL ,
	[parent_id] bigint NOT NULL ,
	[parent_ids] varchar(255) NULL ,
	[status] varchar(10) NOT NULL ,
	[sort] int NULL ,
	[description] varchar(1000) NULL ,
	[create_time] datetime NULL ,
	[update_time] datetime NULL ,
	[create_user] bigint NULL ,
	[update_user] bigint NULL 
)
ON [PRIMARY];
GO


EXECUTE sp_addextendedproperty  N'MS_Description', N'', N'SCHEMA', N'dbo', N'TABLE', N'sys_dict', NULL, NULL;
GO

ALTER TABLE [dbo].[sys_dict] ADD  CONSTRAINT [DF_sys_dict_status] DEFAULT ('ENABLE') FOR [status];
GO
ALTER TABLE [dbo].[sys_dict] ADD CONSTRAINT [PK_sys_dict] PRIMARY KEY CLUSTERED 
(
[dict_id]
)
WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON);
GO



insert into [dbo].[sys_dict]([dict_id],[dict_type_id],[code],[name],[parent_id],[parent_ids],[status],[sort],[description],[create_time],[update_time],[create_user],[update_user]) values
('1106120532442595330','1106120208097067009','M','男','0','[0]','ENABLE',null,'','2019-03-14 17:11:00.000',null,'1',null),
('1106120574163337218','1106120208097067009','F','女','0','[0]','ENABLE',null,'','2019-03-14 17:11:10.000',null,'1',null),
('1106120645697191938','1106120265689055233','ENABLE','启用','0','[0]','ENABLE',null,'','2019-03-14 17:11:27.000',null,'1',null),
('1106120699468169217','1106120265689055233','DISABLE','禁','0','[0]','ENABLE',null,'','2019-03-14 17:11:40.000',null,'1',null),
('1106120784318939137','1106120322450571266','ENABLE','启用','0','[0]','ENABLE',null,'','2019-03-14 17:12:00.000',null,'1',null),
('1106120825993543682','1106120322450571266','FREEZE','冻结','0','[0]','ENABLE','1','','2019-03-14 17:12:10.000','2019-03-16 10:56:36.000','1','1'),
('1106120875872206849','1106120322450571266','DELETED','已删除','0','[0]','ENABLE','-1221','','2019-03-14 17:12:22.000','2019-03-16 10:56:53.000','1','1'),
('1106120935070613505','1106120388036902914','Y','删除','0','[0]','ENABLE','23333','','2019-03-14 17:12:36.000','2019-03-16 10:58:53.000','1','1'),
('1106120968910258177','1106120388036902914','N','未删除','0','[0]','ENABLE','1212211221','','2019-03-14 17:12:44.000','2019-03-16 10:59:03.000','1','1'),
('1149218674746355713','1149217131989069826','BASE_SYSTEM','系统管理','0','[0]','ENABLE','1','系统管理平台','2019-07-11 15:27:38.000','2019-07-11 20:27:14.000','1','1'),
('1160533174626959361','1160532704105742337','00101','办公审批','0','[0]','ENABLE','10','','2019-08-11 20:47:25.000',null,'1',null),
('1160533264645111810','1160532704105742337','00102','行政审批','0','[0]','ENABLE','20','','2019-08-11 20:47:47.000',null,'1',null),
('1160533377727741954','1160532775455047681','KEY_LEAVE','请假流程标识','0','[0]','ENABLE','10','','2019-08-11 20:48:14.000',null,'1',null),
('1160533455343337474','1160532775455047681','KEY_FINANCE','财务流程标识','0','[0]','ENABLE','20','','2019-08-11 20:48:32.000',null,'1',null),
('1160533574843252737','1160532886713155585','00401','事假','0','[0]','ENABLE','10','','2019-08-11 20:49:01.000',null,'1',null),
('1160533625615302658','1160532886713155585','00402','婚假','0','[0]','ENABLE','20','','2019-08-11 20:49:13.000',null,'1',null),
('1160533707215486977','1160532886713155585','00403','产假','0','[0]','ENABLE','30','','2019-08-11 20:49:32.000',null,'1',null),
('1160533765403066370','1160532886713155585','00404','病假','0','[0]','ENABLE','40','','2019-08-11 20:49:46.000',null,'1',null),
('1160533863834992641','1160532886713155585','00405','公假','0','[0]','ENABLE','50','','2019-08-11 20:50:09.000',null,'1',null),
('1160533945309347841','1160532886713155585','00406','年假','0','[0]','ENABLE','60','','2019-08-11 20:50:29.000',null,'1',null),
('1160534007389241346','1160532886713155585','00407','其他','0','[0]','ENABLE','70','','2019-08-11 20:50:44.000',null,'1',null)

;
/****** Object:  table [dbo].[sys_dict_type]  Script Date: 2019-11-23 22:13:13 ******/
GO


use [guns]
GO


CREATE TABLE [dbo].[sys_dict_type]
(
	[dict_type_id] bigint NOT NULL ,
	[code] varchar(255) NOT NULL ,
	[name] varchar(255) NOT NULL ,
	[description] varchar(1000) NULL ,
	[system_flag] nvarchar(1) NOT NULL ,
	[status] varchar(10) NOT NULL ,
	[sort] int NULL ,
	[create_time] datetime NULL ,
	[create_user] bigint NULL ,
	[update_time] datetime NULL ,
	[update_user] bigint NULL 
)
ON [PRIMARY];
GO


EXECUTE sp_addextendedproperty  N'MS_Description', N'', N'SCHEMA', N'dbo', N'TABLE', N'sys_dict_type', NULL, NULL;
GO

ALTER TABLE [dbo].[sys_dict_type] ADD  CONSTRAINT [DF_sys_dict_type_status] DEFAULT ('ENABLE') FOR [status];
GO
ALTER TABLE [dbo].[sys_dict_type] ADD CONSTRAINT [PK_sys_dict_type] PRIMARY KEY CLUSTERED 
(
[dict_type_id]
)
WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON);
GO



insert into [dbo].[sys_dict_type]([dict_type_id],[code],[name],[description],[system_flag],[status],[sort],[create_time],[create_user],[update_time],[update_user]) values
('1106120208097067009','SEX','性别','','Y','ENABLE','4','2019-03-14 17:09:43.000','1',null,null),
('1106120265689055233','STATUS','状态','','Y','ENABLE','3','2019-03-14 17:09:57.000','1',null,null),
('1106120322450571266','ACCOUNT_STATUS','账号状态','','Y','ENABLE','21112','2019-03-14 17:10:10.000','1','2019-03-16 10:56:15.000','1'),
('1106120388036902914','DEL_FLAG','是否删除','','Y','ENABLE','2','2019-03-14 17:10:26.000','1','2019-03-27 16:26:31.000','1'),
('1149217131989069826','SYSTEM_TYPE','系统分类','系统所有分类的维护','Y','ENABLE','70','2019-07-11 15:21:30.000','1','2019-07-11 15:28:21.000','1'),
('1160532704105742337','FLOW_CATEGARY','工作流分类','工作流分类','Y','ENABLE','60','2019-08-11 20:45:33.000','1',null,null),
('1160532775455047681','FLOW_KEY','工作流标识','工作流标识','Y','ENABLE','70','2019-08-11 20:45:50.000','1',null,null),
('1160532886713155585','LEAVE_TYPE','请假类型','请假类型','Y','ENABLE','80','2019-08-11 20:46:17.000','1','2019-08-11 20:46:23.000','1')

;
/****** Object:  table [dbo].[sys_file_info]  Script Date: 2019-11-23 22:13:13 ******/
GO


use [guns]
GO


CREATE TABLE [dbo].[sys_file_info]
(
	[file_id] varchar(50) NOT NULL ,
	[file_bucket] varchar(100) NULL ,
	[file_name] varchar(100) NULL ,
	[file_suffix] varchar(50) NULL ,
	[file_size_kb] bigint NULL ,
	[final_name] varchar(100) NULL ,
	[file_path] varchar(1000) NULL ,
	[create_time] datetime NULL ,
	[update_time] datetime NULL ,
	[create_user] bigint NULL ,
	[update_user] bigint NULL 
)
ON [PRIMARY];
GO


EXECUTE sp_addextendedproperty  N'MS_Description', N'', N'SCHEMA', N'dbo', N'TABLE', N'sys_file_info', NULL, NULL;
GO

ALTER TABLE [dbo].[sys_file_info] ADD CONSTRAINT [PK_sys_file_info] PRIMARY KEY CLUSTERED 
(
[file_id]
)
WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON);
GO



insert into [dbo].[sys_file_info]([file_id],[file_bucket],[file_name],[file_suffix],[file_size_kb],[final_name],[file_path],[create_time],[update_time],[create_user],[update_user]) values
('1167385745179131905',null,'请假流程.bpmn20.xml','xml','6','1167385745179131905.xml','/Users/stylefeng/tmp/gunsTempFiles/1167385745179131905.xml','2019-08-30 18:37:05.000',null,'1',null),
('1198240493510848513',null,'leave.bpmn20.xml','xml','6','1198240493510848513.xml','D:/tmp\\1198240493510848513.xml','2019-11-23 22:02:50.547',null,'1',null)

;
/****** Object:  table [dbo].[sys_login_log]  Script Date: 2019-11-23 22:13:14 ******/
GO


use [guns]
GO


CREATE TABLE [dbo].[sys_login_log]
(
	[login_log_id] bigint NOT NULL ,
	[log_name] varchar(255) NULL ,
	[user_id] bigint NULL ,
	[create_time] datetime NULL ,
	[succeed] varchar(255) NULL ,
	[message] ntext NULL ,
	[ip_address] varchar(255) NULL 
)
ON [PRIMARY];
GO


EXECUTE sp_addextendedproperty  N'MS_Description', N'', N'SCHEMA', N'dbo', N'TABLE', N'sys_login_log', NULL, NULL;
GO

ALTER TABLE [dbo].[sys_login_log] ADD CONSTRAINT [PK_sys_login_log] PRIMARY KEY CLUSTERED 
(
[login_log_id]
)
WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON);
GO





/****** Object:  table [dbo].[sys_menu]  Script Date: 2019-11-23 22:13:14 ******/
GO


use [guns]
GO


CREATE TABLE [dbo].[sys_menu]
(
	[menu_id] bigint NOT NULL ,
	[code] varchar(255) NULL ,
	[pcode] varchar(255) NULL ,
	[pcodes] varchar(255) NULL ,
	[name] varchar(255) NULL ,
	[icon] varchar(255) NULL ,
	[url] varchar(255) NULL ,
	[sort] int NULL ,
	[levels] int NULL ,
	[menu_flag] varchar(32) NULL ,
	[description] varchar(255) NULL ,
	[status] varchar(32) NULL ,
	[new_page_flag] varchar(32) NULL ,
	[open_flag] varchar(32) NULL ,
	[system_type] varchar(100) NULL ,
	[create_time] datetime NULL ,
	[update_time] datetime NULL ,
	[create_user] bigint NULL ,
	[update_user] bigint NULL 
)
ON [PRIMARY];
GO


EXECUTE sp_addextendedproperty  N'MS_Description', N'', N'SCHEMA', N'dbo', N'TABLE', N'sys_menu', NULL, NULL;
GO

ALTER TABLE [dbo].[sys_menu] ADD  CONSTRAINT [DF_sys_menu_status] DEFAULT ('ENABLE') FOR [status];
GO
ALTER TABLE [dbo].[sys_menu] ADD CONSTRAINT [PK_sys_menu] PRIMARY KEY CLUSTERED 
(
[menu_id]
)
WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON);
GO



insert into [dbo].[sys_menu]([menu_id],[code],[pcode],[pcodes],[name],[icon],[url],[sort],[levels],[menu_flag],[description],[status],[new_page_flag],[open_flag],[system_type],[create_time],[update_time],[create_user],[update_user]) values
('105','system','0','[0],','系统管理','layui-icon layui-icon-set','#','20','1','Y',null,'ENABLE',null,'1','BASE_SYSTEM',null,'2019-03-29 16:32:27.000',null,'1'),
('106','mgr','system','[0],[system],','用户管理','','/mgr','10','2','Y',null,'ENABLE',null,'0','BASE_SYSTEM',null,'2019-06-30 13:48:07.000',null,'1'),
('107','mgr_add','mgr','[0],[system],[mgr],','添加用户',null,'/mgr/add','1','3','N',null,'ENABLE',null,'0','BASE_SYSTEM',null,'2019-06-30 13:48:07.000',null,'1'),
('108','mgr_edit','mgr','[0],[system],[mgr],','修改用户',null,'/mgr/edit','2','3','N',null,'ENABLE',null,'0','BASE_SYSTEM',null,'2019-06-30 13:48:07.000',null,'1'),
('109','mgr_delete','mgr','[0],[system],[mgr],','删除用户',null,'/mgr/delete','3','3','N',null,'ENABLE',null,'0','BASE_SYSTEM',null,'2019-06-30 13:48:07.000',null,'1'),
('110','mgr_reset','mgr','[0],[system],[mgr],','重置密码',null,'/mgr/reset','4','3','N',null,'ENABLE',null,'0','BASE_SYSTEM',null,'2019-06-30 13:48:07.000',null,'1'),
('111','mgr_freeze','mgr','[0],[system],[mgr],','冻结用户',null,'/mgr/freeze','5','3','N',null,'ENABLE',null,'0','BASE_SYSTEM',null,'2019-06-30 13:48:07.000',null,'1'),
('112','mgr_unfreeze','mgr','[0],[system],[mgr],','解除冻结用户',null,'/mgr/unfreeze','6','3','N',null,'ENABLE',null,'0','BASE_SYSTEM',null,'2019-06-30 13:48:07.000',null,'1'),
('113','mgr_setRole','mgr','[0],[system],[mgr],','分配角色',null,'/mgr/setRole','7','3','N',null,'ENABLE',null,'0','BASE_SYSTEM',null,'2019-06-30 13:48:07.000',null,'1'),
('114','role','system','[0],[system],','角色管理','','/role','20','2','Y',null,'ENABLE',null,'0','BASE_SYSTEM',null,'2019-06-30 13:48:12.000',null,'1'),
('115','role_add','role','[0],[system],[role],','添加角色',null,'/role/add','1','3','N',null,'ENABLE',null,'0','BASE_SYSTEM',null,'2019-06-30 13:48:12.000',null,'1'),
('116','role_edit','role','[0],[system],[role],','修改角色',null,'/role/edit','2','3','N',null,'ENABLE',null,'0','BASE_SYSTEM',null,'2019-06-30 13:48:12.000',null,'1'),
('117','role_remove','role','[0],[system],[role],','删除角色',null,'/role/remove','3','3','N',null,'ENABLE',null,'0','BASE_SYSTEM',null,'2019-06-30 13:48:12.000',null,'1'),
('118','role_setAuthority','role','[0],[system],[role],','配置权限',null,'/role/setAuthority','4','3','N',null,'ENABLE',null,'0','BASE_SYSTEM',null,'2019-06-30 13:48:12.000',null,'1'),
('119','menu','system','[0],[system],','菜单管理','','/menu','50','2','Y',null,'ENABLE',null,'0','BASE_SYSTEM',null,'2019-06-30 13:48:25.000',null,'1'),
('120','menu_add','menu','[0],[system],[menu],','添加菜单',null,'/menu/add','1','3','N',null,'ENABLE',null,'0','BASE_SYSTEM',null,'2019-06-30 13:48:25.000',null,'1'),
('121','menu_edit','menu','[0],[system],[menu],','修改菜单',null,'/menu/edit','2','3','N',null,'ENABLE',null,'0','BASE_SYSTEM',null,'2019-06-30 13:48:25.000',null,'1'),
('122','menu_remove','menu','[0],[system],[menu],','删除菜单',null,'/menu/remove','3','3','N',null,'ENABLE',null,'0','BASE_SYSTEM',null,'2019-06-30 13:48:25.000',null,'1'),
('128','log','system','[0],[system],','业务日志','','/log','70','2','Y',null,'ENABLE',null,'0','BASE_SYSTEM',null,'2019-06-30 13:48:39.000',null,'1'),
('130','druid','system','[0],[system],','监控管理','','/druid','80','2','Y',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:50:06.000',null,'1'),
('131','dept','system','[0],[system],','部门管理','','/dept','30','2','Y',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:48:53.000',null,'1'),
('132','dict','system','[0],[system],','字典管理','','/dictType','40','2','Y',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:49:04.000',null,'1'),
('133','loginLog','system','[0],[system],','登录日志','','/loginLog','60','2','Y',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:49:29.000',null,'1'),
('134','log_clean','log','[0],[system],[log],','清空日志',null,'/log/delLog','3','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:48:39.000',null,'1'),
('135','dept_add','dept','[0],[system],[dept],','添加部门',null,'/dept/add','1','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:48:53.000',null,'1'),
('136','dept_update','dept','[0],[system],[dept],','修改部门',null,'/dept/update','1','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:48:53.000',null,'1'),
('137','dept_delete','dept','[0],[system],[dept],','删除部门',null,'/dept/delete','1','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:48:53.000',null,'1'),
('138','dict_add','dict','[0],[system],[dict],','添加字典',null,'/dictType/addItem','1','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:49:04.000',null,'1'),
('139','dict_update','dict','[0],[system],[dict],','修改字典',null,'/dictType/editItem','1','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:49:04.000',null,'1'),
('140','dict_delete','dict','[0],[system],[dict],','删除字典',null,'/dictType/delete','1','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:49:04.000',null,'1'),
('141','notice','system','[0],[system],','通知管理','','/notice','90','2','Y',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:49:44.000',null,'1'),
('142','notice_add','notice','[0],[system],[notice],','添加通知',null,'/notice/add','1','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:49:44.000',null,'1'),
('143','notice_update','notice','[0],[system],[notice],','修改通知',null,'/notice/update','2','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:49:44.000',null,'1'),
('144','notice_delete','notice','[0],[system],[notice],','删除通知',null,'/notice/delete','3','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:49:44.000',null,'1'),
('145','sys_message','dashboard','[0],[dashboard],','消息通知','layui-icon layui-icon-tips','/system/notice','30','2','Y',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-04-08 22:49:39.000',null,'1'),
('149','api_mgr','dev_tools','[0],[dev_tools],','接口文档','fa-leaf','/swagger-ui.html','30','2','Y',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-15 18:13:00.000',null,'1'),
('150','to_menu_edit','menu','[0],[system],[menu],','菜单编辑跳转','','/menu/menu_edit','4','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:48:25.000',null,'1'),
('151','menu_list','menu','[0],[system],[menu],','菜单列表','','/menu/list','5','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:48:25.000',null,'1'),
('152','to_dept_update','dept','[0],[system],[dept],','修改部门跳转','','/dept/dept_update','4','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:48:53.000',null,'1'),
('153','dept_list','dept','[0],[system],[dept],','部门列表','','/dept/list','5','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:48:53.000',null,'1'),
('154','dept_detail','dept','[0],[system],[dept],','部门详情','','/dept/detail','6','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:48:53.000',null,'1'),
('155','to_dict_edit','dict','[0],[system],[dict],','修改菜单跳转','','/dict/dict_edit','4','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:49:04.000',null,'1'),
('156','dict_list','dict','[0],[system],[dict],','字典列表','','/dict/list','5','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:49:04.000',null,'1'),
('157','dict_detail','dict','[0],[system],[dict],','字典详情','','/dict/detail','6','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:49:04.000',null,'1'),
('158','log_list','log','[0],[system],[log],','日志列表','','/log/list','2','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:48:39.000',null,'1'),
('159','log_detail','log','[0],[system],[log],','日志详情','','/log/detail','3','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:48:39.000',null,'1'),
('160','del_login_log','loginLog','[0],[system],[loginLog],','清空登录日志','','/loginLog/delLoginLog','1','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:49:29.000',null,'1'),
('161','login_log_list','loginLog','[0],[system],[loginLog],','登录日志列表','','/loginLog/list','2','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:49:29.000',null,'1'),
('162','to_role_edit','role','[0],[system],[role],','修改角色跳转','','/role/role_edit','5','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:48:12.000',null,'1'),
('163','to_role_assign','role','[0],[system],[role],','角色分配跳转','','/role/role_assign','6','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:48:12.000',null,'1'),
('164','role_list','role','[0],[system],[role],','角色列表','','/role/list','7','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:48:12.000',null,'1'),
('165','to_assign_role','mgr','[0],[system],[mgr],','分配角色跳转','','/mgr/role_assign','8','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:48:07.000',null,'1'),
('166','to_user_edit','mgr','[0],[system],[mgr],','编辑用户跳转','','/mgr/user_edit','9','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:48:07.000',null,'1'),
('167','mgr_list','mgr','[0],[system],[mgr],','用户列表','','/mgr/list','10','3','N',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-06-30 13:48:07.000',null,'1'),
('171','dev_tools','0','[0],','开发管理','layui-icon layui-icon-code-circle','#','30','1','Y',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-05-11 13:40:27.000',null,'1'),
('172','dashboard','0','[0],','主控面板','layui-icon layui-icon-home','#','10','1','Y',null,'ENABLE',null,null,'BASE_SYSTEM',null,'2019-04-08 22:48:15.000',null,'1'),
('1110777136265838594','demos_show','dev_tools','[0],[dev_tools],','模板页面','layui-icon layui-icon-template','#','40','2','Y',null,'ENABLE',null,null,'BASE_SYSTEM','2019-03-27 13:34:41.000','2019-06-15 18:13:11.000','1','1'),
('1110777366856089602','excel_import','demos_show','[0],[dev_tools],[demos_show],','excel导入','','/excel/import','10','3','Y',null,'ENABLE',null,null,'BASE_SYSTEM','2019-03-27 13:35:36.000','2019-06-15 18:13:11.000','1','1'),
('1110777491464667137','excel_export','demos_show','[0],[dev_tools],[demos_show],','excel导出','','/excel/export','20','3','Y',null,'ENABLE',null,null,'BASE_SYSTEM','2019-03-27 13:36:06.000','2019-06-15 18:13:11.000','1','1'),
('1110787391943098370','advanced_form','demos_show','[0],[dev_tools],[demos_show],','高级表单','','/egForm','30','3','Y',null,'ENABLE',null,null,'BASE_SYSTEM','2019-03-27 14:15:26.000','2019-06-15 18:13:11.000','1','1'),
('1110839216310329346','pdf_view','demos_show','[0],[dev_tools],[demos_show],','文档预览','','/loadPdfFile','40','3','Y',null,'ENABLE',null,null,'BASE_SYSTEM','2019-03-27 17:41:22.000','2019-06-15 18:13:11.000','1','1'),
('1111545968697860098','console','dashboard','[0],[dashboard],','项目介绍','','/system/console','10','2','Y',null,'ENABLE',null,null,'BASE_SYSTEM','2019-03-29 16:29:45.000','2019-04-09 20:57:08.000','1','1'),
('1111546189892870145','console2','dashboard','[0],[dashboard],','统计报表','','/system/console2','20','2','Y',null,'ENABLE',null,null,'BASE_SYSTEM','2019-03-29 16:30:38.000','2019-04-08 22:49:48.000','1','1'),
('1127085735660421122','code_generate','dev_tools','[0],[dev_tools],','代码生成','','/gen','20','2','Y',null,'ENABLE',null,null,'BASE_SYSTEM','2019-05-11 13:39:14.000','2019-06-15 18:12:45.000','1','1'),
('1139826657964593154','meta_data','dev_tools','[0],[dev_tools],','系统配置','','#','10','2','Y',null,'ENABLE',null,null,'BASE_SYSTEM','2019-06-15 17:27:07.000','2019-06-15 18:12:35.000','1','1'),
('1139827152854716418','data_source','meta_data','[0],[dev_tools],[meta_data],','数据源管理','','/databaseInfo','10','3','Y',null,'ENABLE',null,null,'BASE_SYSTEM','2019-06-15 17:29:05.000','2019-06-15 18:12:35.000','1','1'),
('1140241958563880962','tenant_info','system','[0],[system],','租户管理','layui-icon layui-icon-auz','/tenantInfo','999','2','Y','','ENABLE','','','BASE_SYSTEM',null,null,'1','1'),
('1140241958563880963','tenant_info_add','tenant_info','[0],[system],[tenant_info],','租户表添加','fa-star','','999','3','N','','ENABLE','','','BASE_SYSTEM',null,null,'1','1'),
('1140241958563880964','tenant_info_edit','tenant_info','[0],[system],[tenant_info],','租户表修改','fa-star','','999','3','N','','ENABLE','','','BASE_SYSTEM',null,null,'1','1'),
('1140241958563880965','tenant_info_delete','tenant_info','[0],[system],[tenant_info],','租户表删除','fa-star','','999','3','N','','ENABLE','','','BASE_SYSTEM',null,null,'1','1'),
('1142957882422112257','SYS_CONFIG','meta_data','[0],[dev_tools],[meta_data],','参数配置','fa-star','/sysConfig','5','3','Y','','ENABLE','','','BASE_SYSTEM','2019-06-24 08:49:28.000','2019-06-24 08:57:34.000','1','1'),
('1142957882422112258','SYS_CONFIG_ADD','SYS_CONFIG','[0],[dev_tools],[meta_data],[SYS_CONFIG],','参数配置添加','fa-star','','999','4','N','','ENABLE','','','BASE_SYSTEM','2019-06-24 08:49:28.000','2019-06-24 08:57:34.000','1','1'),
('1142957882422112259','SYS_CONFIG_EDIT','SYS_CONFIG','[0],[dev_tools],[meta_data],[SYS_CONFIG],','参数配置修改','fa-star','','999','4','N','','ENABLE','','','BASE_SYSTEM','2019-06-24 08:49:28.000','2019-06-24 08:57:34.000','1','1'),
('1142957882426306562','SYS_CONFIG_DELETE','SYS_CONFIG','[0],[dev_tools],[meta_data],[SYS_CONFIG],','参数配置删除','fa-star','','999','4','N','','ENABLE','','','BASE_SYSTEM','2019-06-24 08:49:28.000','2019-06-24 08:57:34.000','1','1'),
('1144441072852684801','SYS_POSITION','system','[0],[system],','职位管理','fa-star','/position','35','2','Y','','ENABLE','','','BASE_SYSTEM','2019-06-28 11:03:09.000','2019-06-28 11:06:42.000','1','1'),
('1144441072852684802','SYS_POSITION_ADD','SYS_POSITION','[0],[system],[SYS_POSITION],','职位表添加','fa-star','','999','3','N','','ENABLE','','','BASE_SYSTEM','2019-06-28 11:03:09.000','2019-06-28 11:06:42.000','1','1'),
('1144441072852684803','SYS_POSITION_EDIT','SYS_POSITION','[0],[system],[SYS_POSITION],','职位表修改','fa-star','','999','3','N','','ENABLE','','','BASE_SYSTEM','2019-06-28 11:03:09.000','2019-06-28 11:06:42.000','1','1'),
('1144441072852684804','SYS_POSITION_DELETE','SYS_POSITION','[0],[system],[SYS_POSITION],','职位表删除','fa-star','','999','3','N','','ENABLE','','','BASE_SYSTEM','2019-06-28 11:03:09.000','2019-06-28 11:06:42.000','1','1'),
('1149955324929765378','system_info','dashboard','[0],[dashboard],','系统监控','layui-icon-star-fill','/system/systemInfo','40','2','Y',null,'ENABLE',null,null,'BASE_SYSTEM','2019-07-13 16:14:49.000',null,'1',null),
('1158737199356919809','online_office','0','[0],','在线办公','layui-icon-cellphone','#','40','1','Y',null,'ENABLE',null,null,'BASE_SYSTEM',null,null,'1',null),
('1158737313060306945','office_model','online_office','[0],[online_office],','工作流程','layui-icon-star-fill','#','10','2','Y',null,'ENABLE',null,null,'BASE_SYSTEM',null,null,'1',null),
('1158737441812856834','model_manager','office_model','[0],[online_office],[office_model],','模型管理','layui-icon-star-fill','/model','10','3','Y',null,'ENABLE',null,null,'BASE_SYSTEM',null,null,'1','1'),
('1158737665570586625','procdef','office_model','[0],[online_office],[office_model],','流程管理','layui-icon-star-fill','/process','20','3','Y',null,'ENABLE',null,null,'BASE_SYSTEM',null,null,'1',null),
('1158737892184637441','process_on','office_model','[0],[online_office],[office_model],','运行中流程','layui-icon-star-fill','/taskRunning','30','3','Y',null,'ENABLE',null,null,'BASE_SYSTEM',null,null,'1',null),
('1158738158376140801','hiprocdef','office_model','[0],[online_office],[office_model],','历史流程','layui-icon-star-fill','/historyProc','40','3','Y',null,'ENABLE',null,null,'BASE_SYSTEM',null,null,'1',null),
('1158738253440040962','task_manager','online_office','[0],[online_office],','任务管理','layui-icon-star-fill','#','20','2','Y',null,'ENABLE',null,null,'BASE_SYSTEM',null,null,'1',null),
('1158738549708898305','approve','online_office','[0],[online_office],','申请审批','layui-icon-star-fill','#','30','2','Y',null,'ENABLE',null,null,'BASE_SYSTEM',null,null,'1',null),
('1158738875899920385','rutask','task_manager','[0],[online_office],[task_manager],','待办任务','layui-icon-star-fill','/taskWaiting','10','3','Y',null,'ENABLE',null,null,'BASE_SYSTEM',null,null,'1',null),
('1158739014999818241','havetask','task_manager','[0],[online_office],[task_manager],','已办任务','layui-icon-star-fill','/taskHistory','20','3','Y',null,'ENABLE',null,null,'BASE_SYSTEM',null,null,'1',null),
('1158739122743099393','leave','approve','[0],[online_office],[approve],','请假审批','layui-icon-star-fill','/myleave','10','3','Y',null,'ENABLE',null,null,'BASE_SYSTEM',null,null,'1',null),
('1184836690833002497','TRANSLATION','dev_tools','[0],[dev_tools],','多语言配置','layui-icon-senior','/translation','999','2','Y','','ENABLE','','','BASE_SYSTEM',null,null,'1','1'),
('1184836690833002498','TRANSLATION_ADD','TRANSLATION','[0],[dev_tools],[TRANSLATION],','多语言添加','fa-star','','999','3','N','','ENABLE','','','BASE_SYSTEM',null,null,'1','1'),
('1184836690833002499','TRANSLATION_EDIT','TRANSLATION','[0],[dev_tools],[TRANSLATION],','多语言修改','fa-star','','999','3','N','','ENABLE','','','BASE_SYSTEM',null,null,'1','1'),
('1184836690833002500','TRANSLATION_DELETE','TRANSLATION','[0],[dev_tools],[TRANSLATION],','多语言删除','fa-star','','999','3','N','','ENABLE','','','BASE_SYSTEM',null,null,'1','1')

;
/****** Object:  table [dbo].[sys_notice]  Script Date: 2019-11-23 22:13:15 ******/
GO


use [guns]
GO


CREATE TABLE [dbo].[sys_notice]
(
	[notice_id] bigint NOT NULL ,
	[title] varchar(255) NULL ,
	[content] ntext NULL ,
	[create_time] datetime NULL ,
	[create_user] bigint NULL ,
	[update_time] datetime NULL ,
	[update_user] bigint NULL 
)
ON [PRIMARY];
GO


EXECUTE sp_addextendedproperty  N'MS_Description', N'', N'SCHEMA', N'dbo', N'TABLE', N'sys_notice', NULL, NULL;
GO

ALTER TABLE [dbo].[sys_notice] ADD CONSTRAINT [PK_sys_notice] PRIMARY KEY CLUSTERED 
(
[notice_id]
)
WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON);
GO



insert into [dbo].[sys_notice]([notice_id],[title],[content],[create_time],[create_user],[update_time],[update_user]) values
('6','欢迎','hi，Guns旗舰版发布了！','2017-01-11 08:53:20.000','1','2018-12-28 23:24:48.000','1'),
('8','你好','你好，世界！','2017-05-10 19:28:57.000','1','2018-12-28 23:28:26.000','1')

;
/****** Object:  table [dbo].[sys_operation_log]  Script Date: 2019-11-23 22:13:15 ******/
GO


use [guns]
GO


CREATE TABLE [dbo].[sys_operation_log]
(
	[operation_log_id] bigint NOT NULL ,
	[log_type] varchar(32) NULL ,
	[log_name] varchar(255) NULL ,
	[user_id] bigint NULL ,
	[class_name] varchar(255) NULL ,
	[method] ntext NULL ,
	[create_time] datetime NULL ,
	[succeed] varchar(32) NULL ,
	[message] ntext NULL 
)
ON [PRIMARY];
GO


EXECUTE sp_addextendedproperty  N'MS_Description', N'', N'SCHEMA', N'dbo', N'TABLE', N'sys_operation_log', NULL, NULL;
GO

ALTER TABLE [dbo].[sys_operation_log] ADD CONSTRAINT [PK_sys_operation_log] PRIMARY KEY CLUSTERED 
(
[operation_log_id]
)
WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON);
GO



insert into [dbo].[sys_operation_log]([operation_log_id],[log_type],[log_name],[user_id],[class_name],[method],[create_time],[succeed],[message]) values
('1198242675031269378','业务日志','清空业务日志','1','cn.stylefeng.guns.sys.modular.system.controller.LogController','delLog','2019-11-23 22:11:30.657','成功','主键id=null')

;
/****** Object:  table [dbo].[sys_position]  Script Date: 2019-11-23 22:13:15 ******/
GO


use [guns]
GO


CREATE TABLE [dbo].[sys_position]
(
	[position_id] bigint NOT NULL ,
	[name] varchar(255) NULL ,
	[code] varchar(255) NULL ,
	[sort] bigint NULL ,
	[status] varchar(255) NULL ,
	[remark] varchar(255) NULL ,
	[create_time] datetime NULL ,
	[create_user] bigint NULL ,
	[update_time] datetime NULL ,
	[update_user] bigint NULL 
)
ON [PRIMARY];
GO


EXECUTE sp_addextendedproperty  N'MS_Description', N'', N'SCHEMA', N'dbo', N'TABLE', N'sys_position', NULL, NULL;
GO

ALTER TABLE [dbo].[sys_position] ADD CONSTRAINT [PK_sys_position] PRIMARY KEY CLUSTERED 
(
[position_id]
)
WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON);
GO



insert into [dbo].[sys_position]([position_id],[name],[code],[sort],[status],[remark],[create_time],[create_user],[update_time],[update_user]) values
('1','董事长','President','1','ENABLE','','2019-06-27 18:14:43.000','1',null,null),
('2','总经理','GM','2','ENABLE',null,'2019-06-27 18:14:43.000','1',null,null)

;
/****** Object:  table [dbo].[sys_relation]  Script Date: 2019-11-23 22:13:16 ******/
GO


use [guns]
GO


CREATE TABLE [dbo].[sys_relation]
(
	[relation_id] bigint NOT NULL ,
	[menu_id] bigint NULL ,
	[role_id] bigint NULL 
)
ON [PRIMARY];
GO


EXECUTE sp_addextendedproperty  N'MS_Description', N'', N'SCHEMA', N'dbo', N'TABLE', N'sys_relation', NULL, NULL;
GO

ALTER TABLE [dbo].[sys_relation] ADD CONSTRAINT [PK_sys_relation] PRIMARY KEY CLUSTERED 
(
[relation_id]
)
WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON);
GO



insert into [dbo].[sys_relation]([relation_id],[menu_id],[role_id]) values
('1138325281010921473','105','5'),
('1138325281031892994','132','5'),
('1138325281044475906','138','5'),
('1138325281057058818','139','5'),
('1138325281069641730','140','5'),
('1138325281082224642','155','5'),
('1138325281099001858','156','5'),
('1138325281111584770','157','5'),
('1138325281124167682','141','5'),
('1138325281136750593','142','5'),
('1138325281149333506','143','5'),
('1138325281161916418','144','5'),
('1138325281174499329','171','5'),
('1138325281187082241','149','5'),
('1138325281199665154','1127085735660421122','5'),
('1138325281216442370','172','5'),
('1138325281229025281','145','5'),
('1138325281245802498','1111545968697860098','5'),
('1138325281258385410','1111546189892870145','5'),
('1198240207090216962','105','1'),
('1198240207195074561','106','1'),
('1198240207253794818','107','1'),
('1198240207350263809','108','1'),
('1198240207488675842','109','1'),
('1198240209258672129','110','1'),
('1198240209304809473','111','1'),
('1198240209359335425','112','1'),
('1198240209405472769','113','1'),
('1198240209451610114','165','1'),
('1198240209497747458','166','1'),
('1198240209539690497','167','1'),
('1198240209581633537','114','1'),
('1198240209644548098','115','1'),
('1198240209686491138','116','1'),
('1198240211427127298','117','1'),
('1198240211485847553','118','1'),
('1198240211531984897','162','1'),
('1198240211594899458','163','1'),
('1198240211636842498','164','1'),
('1198240211678785537','119','1'),
('1198240211720728577','120','1'),
('1198240211766865921','121','1'),
('1198240211808808961','122','1'),
('1198240211854946305','150','1'),
('1198240211892695042','151','1'),
('1198240213675274242','128','1'),
('1198240213713022977','134','1'),
('1198240213754966018','158','1'),
('1198240213796909058','159','1'),
('1198240213826269185','130','1'),
('1198240213868212226','131','1'),
('1198240213901766658','135','1'),
('1198240213939515394','136','1'),
('1198240213981458433','137','1'),
('1198240215281692674','152','1'),
('1198240219589242881','153','1'),
('1198240219643768833','154','1'),
('1198240219702489090','132','1'),
('1198240219748626434','138','1'),
('1198240219798958082','139','1'),
('1198240219845095426','140','1'),
('1198240219891232770','155','1'),
('1198240219941564417','156','1'),
('1198240219991896065','157','1'),
('1198240220067393538','133','1'),
('1198240220105142274','160','1'),
('1198240220151279617','161','1'),
('1198240220197416961','141','1'),
('1198240220239360002','142','1'),
('1198240220285497345','143','1'),
('1198240220381966337','144','1'),
('1198240220461658114','1140241958563880962','1'),
('1198240220516184065','1140241958563880963','1'),
('1198240220566515714','1140241958563880964','1'),
('1198240220616847361','1140241958563880965','1'),
('1198240220650401793','1144441072852684801','1'),
('1198240220688150529','1144441072852684802','1'),
('1198240220730093569','1144441072852684803','1'),
('1198240220759453697','1144441072852684804','1'),
('1198240220843339777','171','1'),
('1198240220893671426','149','1'),
('1198240220931420162','1110777136265838594','1'),
('1198240220973363201','1110777366856089602','1'),
('1198240221011111938','1110777491464667137','1'),
('1198240221065637889','1110787391943098370','1'),
('1198240221090803713','1110839216310329346','1'),
('1198240221128552450','1127085735660421122','1'),
('1198240221174689793','1139826657964593154','1'),
('1198240221229215746','1139827152854716418','1'),
('1198240221283741697','1142957882422112257','1'),
('1198240221329879041','1142957882422112258','1'),
('1198240221380210690','1142957882422112259','1'),
('1198240221443125249','1142957882426306562','1'),
('1198240221489262593','1184836690833002497','1'),
('1198240221535399937','1184836690833002498','1'),
('1198240221568954370','1184836690833002499','1'),
('1198240221606703106','1184836690833002500','1'),
('1198240221652840449','172','1'),
('1198240221690589186','145','1'),
('1198240221736726530','1111545968697860098','1'),
('1198240221766086657','1111546189892870145','1'),
('1198240221799641089','1149955324929765378','1'),
('1198240221841584130','1158737199356919809','1'),
('1198240221879332865','1158737313060306945','1'),
('1198240221921275905','1158737441812856834','1'),
('1198240221950636033','1158737665570586625','1'),
('1198240221975801858','1158737892184637441','1'),
('1198240222000967682','1158738158376140801','1'),
('1198240222026133505','1158738253440040962','1'),
('1198240222051299329','1158738875899920385','1'),
('1198240222089048066','1158739014999818241','1'),
('1198240222114213889','1158738549708898305','1'),
('1198240222139379713','1158739122743099393','1')

;
/****** Object:  table [dbo].[sys_role]  Script Date: 2019-11-23 22:13:16 ******/
GO


use [guns]
GO


CREATE TABLE [dbo].[sys_role]
(
	[role_id] bigint NOT NULL ,
	[pid] bigint NULL ,
	[name] varchar(255) NULL ,
	[description] varchar(255) NULL ,
	[sort] int NULL ,
	[version] int NULL ,
	[create_time] datetime NULL ,
	[update_time] datetime NULL ,
	[create_user] bigint NULL ,
	[update_user] bigint NULL 
)
ON [PRIMARY];
GO


EXECUTE sp_addextendedproperty  N'MS_Description', N'', N'SCHEMA', N'dbo', N'TABLE', N'sys_role', NULL, NULL;
GO

ALTER TABLE [dbo].[sys_role] ADD CONSTRAINT [PK_sys_role] PRIMARY KEY CLUSTERED 
(
[role_id]
)
WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON);
GO



insert into [dbo].[sys_role]([role_id],[pid],[name],[description],[sort],[version],[create_time],[update_time],[create_user],[update_user]) values
('1','0','超级管理员','administrator','1','1',null,null,null,null),
('5','1','第三方登录','oauth_role','2',null,null,null,null,null)

;
/****** Object:  table [dbo].[sys_translation]  Script Date: 2019-11-23 22:13:16 ******/
GO


use [guns]
GO


CREATE TABLE [dbo].[sys_translation]
(
	[tran_id] bigint NOT NULL ,
	[tran_code] varchar(255) NULL ,
	[tran_name] varchar(255) NULL ,
	[languages] int NULL ,
	[tran_value] varchar(255) NULL ,
	[create_time] datetime NULL ,
	[create_user] bigint NULL ,
	[update_time] datetime NULL ,
	[update_user] bigint NULL 
)
ON [PRIMARY];
GO


EXECUTE sp_addextendedproperty  N'MS_Description', N'', N'SCHEMA', N'dbo', N'TABLE', N'sys_translation', NULL, NULL;
GO

ALTER TABLE [dbo].[sys_translation] ADD CONSTRAINT [PK_sys_translation] PRIMARY KEY CLUSTERED 
(
[tran_id]
)
WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON);
GO



insert into [dbo].[sys_translation]([tran_id],[tran_code],[tran_name],[languages],[tran_value],[create_time],[create_user],[update_time],[update_user]) values
('1184999114561286145','BTN_SEARCH','搜索按钮','1','搜索',null,'1',null,null),
('1184999114561286146','BTN_SEARCH','搜索按钮','2','search',null,null,null,null),
('1184999114561286147','BTN_ADD','添加按钮','1','添加',null,null,null,null),
('1184999114561286148','BTN_ADD','添加按钮','2','add',null,null,null,null),
('1184999114561286150','BTN_EXPORT','导出按钮','1','导出',null,null,null,null),
('1184999114561286151','BTN_EXPORT','导出按钮','2','export',null,null,null,null),
('1185127290116263937','BTN_DELETE','删除按钮','1','删除',null,'1',null,null),
('1185127461399056385','BTN_DELETE','删除按钮','2','delete',null,'1',null,null),
('1185127896490987522','BTN_EDIT','修改按钮','1','修改',null,'1',null,null),
('1185127978657402881','BTN_EDIT','修改按钮','2','edit',null,'1',null,null),
('1185128265912700929','BTN_ASSIGN_ROLES','分配角色按钮','1','分配角色',null,'1',null,'1'),
('1185128442824249345','BTN_ASSIGN_ROLES','分配角色按钮','2','assign roles',null,'1',null,'1'),
('1185128740447866881','BTN_RESET_PASSWORD','重置密码','1','重置密码',null,'1',null,'1'),
('1185128883926618114','BTN_RESET_PASSWORD','重置密码','2','reset password',null,'1',null,'1'),
('1185129198088376321','BTN_SUBMIT','提交按钮','1','提交',null,'1',null,null),
('1185129308289519618','BTN_SUBMIT','提交按钮','2','submit',null,'1',null,null),
('1185129491752570882','BTN_CANCEL','取消按钮','1','取消',null,'1',null,null),
('1185129607062376450','BTN_CANCEL','取消按钮','2','cancel',null,'1',null,null),
('1185129934188728322','BTN_CONFIRM','确定按钮','1','确定',null,'1',null,null),
('1185130144491130881','BTN_CONFIRM','确定按钮','2','confirm',null,'1',null,null),
('1185130586352668673','BTN_SAVE','保存按钮','1','保存',null,'1',null,null),
('1185130686109995009','BTN_SAVE','保存按钮','2','save',null,'1',null,null),
('1185131904605638657','BTN_PERMISSION_CONFIG','权限配置按钮','1','权限配置',null,'1',null,null),
('1185132038101946370','BTN_PERMISSION_CONFIG','权限配置按钮','2','permission config',null,'1',null,null),
('1185132531570200577','BTN_EXPAND_ALL','展开所有按钮','1','展开所有',null,'1',null,null),
('1185132702320316418','BTN_EXPAND_ALL','展开所有按钮','2','expand all',null,'1',null,null),
('1185133005677547522','BTN_COLLAPSE_ALL','折叠所有按钮','1','折叠所有',null,'1',null,null),
('1185133152151031810','BTN_COLLAPSE_ALL','折叠所有按钮','2','collapse all',null,'1',null,null),
('1185133716238782466','BTN_CLEAR_LOG','清空日志按钮','1','清空日志',null,'1',null,null),
('1185133818449776642','BTN_CLEAR_LOG','清空日志按钮','2','clear log',null,'1',null,null),
('1185134961246621697','BTN_BACK','返回按钮','1','返回',null,'1',null,null),
('1185135058730635265','BTN_BACK','返回按钮','2','back',null,'1',null,null),
('1185135339954524162','BTN_GENERATE_CODE','生成代码按钮','1','生成代码',null,'1',null,null),
('1185135449107091458','BTN_GENERATE_CODE','生成代码按钮','2','generate code ',null,'1',null,null),
('1185135668041371650','BTN_RESET','重置按钮','1','重置',null,'1',null,null),
('1185135783120490497','BTN_RESET','重置按钮','2','reset',null,'1',null,null),
('1185136595410374658','BTN_IMPORT_PROCESS','导入流程按钮','1','导入流程',null,'1',null,null),
('1185136784481210369','BTN_IMPORT_PROCESS','导入流程按钮','2','import process',null,'1',null,null),
('1185137183988666370','BTN_BATCH_DELETE','批量删除按钮','1','批量删除',null,'1',null,null),
('1185137301957660673','BTN_BATCH_DELETE','批量删除按钮','2','batch delete',null,'1',null,null),
('1185138720563548161','BTN_LEAVE_APPLICATION','新建请假单按钮','1','新建请假单',null,'1',null,null),
('1185138909642772482','BTN_LEAVE_APPLICATION','新建请假单按钮','2','leave application',null,'1',null,null),
('1185189608899715074','LAYER_TITLE','提示框标题','1','信息',null,'1',null,null),
('1185189726621245441','LAYER_TITLE','提示框标题','2','infomation',null,'1',null,null),
('1185192392994803714','BTN_ADD_TYPE','添加类型','1','添加类型',null,'1',null,null),
('1185192465178775553','BTN_ADD_TYPE','添加类型','2','add dict type',null,'1',null,null),
('1185196578591412226','BTN_DETAIL','查看详情按钮','1','查看详情',null,'1',null,null),
('1185196624628092930','BTN_DETAIL','查看详情按钮','2','detail',null,'1',null,null),
('1185198263422390273','BTN_CONDITION','查询条件','1','查询条件',null,'1',null,null),
('1185198328505405442','BTN_CONDITION','查询条件','2','condition',null,'1',null,null),
('1185202736316248065','BTN_PROCESS_DESIGNER','流程设计器按钮','1','流程设计器',null,'1',null,null),
('1185202834534264834','BTN_PROCESS_DESIGNER','流程设计器按钮','2','process designer',null,'1',null,null),
('1185202924925710337','BTN_DEPLOY','部署按钮','1','部署',null,'1',null,null),
('1185202963215511553','BTN_DEPLOY','部署按钮','2','deploy',null,'1',null,null),
('1185203057667043330','BTN_PREVIEW','预览按钮','1','预览',null,'1',null,null),
('1185203128915685378','BTN_PREVIEW','预览按钮','2','preview',null,'1',null,null),
('1185203319320309762','BTN_MAP_MODEL','映射模型按钮','1','映射模型',null,'1',null,null),
('1185203369178001410','BTN_MAP_MODEL','映射模型按钮','2','map model',null,'1',null,null),
('1185203453026332673','BTN_DOWNLOAD','打包下载','1','打包下载',null,'1',null,null),
('1185203497880219649','BTN_DOWNLOAD','打包下载','2','download',null,'1',null,null),
('1185203615111016450','BTN_HANG_UP','挂起按钮','1','挂起',null,'1',null,null),
('1185203690688180226','BTN_HANG_UP','挂起按钮','2','hang-up',null,'1',null,null),
('1185203748275974146','BTN_ACTIVE','激活按钮','1','激活',null,'1',null,null),
('1185203805092016130','BTN_ACTIVE','激活按钮','2','active',null,'1',null,null),
('1185204123397746689','BTN_PROCESS_INFO','流程信息按钮','1','流程信息',null,'1',null,null),
('1185204183095275522','BTN_PROCESS_INFO','流程信息按钮','2','process info',null,'1',null,null),
('1185204321981263874','BTN_APPOINT','委派按钮','1','委派',null,'1',null,null),
('1185204360698884097','BTN_APPOINT','委派按钮','2','appoint',null,'1',null,null),
('1185204489879252993','BTN_DISABLE','作废按钮','1','作废',null,'1',null,null),
('1185204530614333441','BTN_DISABLE','作废按钮','2','disable',null,'1',null,null),
('1185204645152387073','BTN_APPOINT_ROLE','指定角色按钮','1','指定角色',null,'1',null,null),
('1185204717277638657','BTN_APPOINT_ROLE','指定角色按钮','2','appoint role',null,'1',null,null),
('1185205156547096577','BTN_CHOOSE','选择按钮','1','选择',null,'1',null,null),
('1185205205565927426','BTN_CHOOSE','选择按钮','2','choose',null,'1',null,null),
('1185205330925285377','BTN_DEAL','办理按钮','1','办理',null,'1',null,null),
('1185205405613256705','BTN_DEAL','办理按钮','2','deal',null,'1',null,null),
('1185205639139520513','BTN_APPOINT_PERSON','指定人员按钮','1','指定人员',null,'1',null,null),
('1185205725617680386','BTN_APPOINT_PERSON','指定人员','2','appoint person',null,'1',null,null),
('1185205860275810306','BTN_APPROVE','批准按钮','1','批准',null,'1',null,null),
('1185205911563759617','BTN_APPROVE','批准按钮','2','approve',null,'1',null,null),
('1185206072910245890','BTN_REJECT','驳回按钮','1','驳回',null,'1',null,null),
('1185206116057051138','BTN_REJECT','驳回按钮','2','reject',null,'1',null,null),
('1185415573330403330','FIELD_ACCOUNT','账号字段','1','账号',null,'1',null,null),
('1185415640732868609','FIELD_ACCOUNT','账号字段','2','account',null,'1',null,null),
('1185415905989042177','FIELD_DEPT','部门字段','1','部门',null,'1',null,null),
('1185415962184327170','FIELD_DEPT','部门字段','2','dept',null,'1',null,null),
('1185416053171363842','FIELD_POST','职位字段','1','职位',null,'1',null,null),
('1185416141247553537','FIELD_POST','职位字段','2','post',null,'1',null,null),
('1185416219261607937','FIELD_PHONE','电话字段','1','电话',null,'1',null,null),
('1185416277042339841','FIELD_PHONE','电话字段','2','phone',null,'1',null,null),
('1185416377336537090','FIELD_CREATE_TIME','创建时间字段','1','创建时间',null,'1',null,null),
('1185416420625948673','FIELD_CREATE_TIME','创建时间字段','2','create time',null,'1',null,null),
('1185416504335867906','FIELD_UPDATE_TIME','更新时间字段','1','更新时间',null,'1',null,null),
('1185416545180000257','FIELD_UPDATE_TIME','更新时间字段','2','update time',null,'1',null,null),
('1185416642995363841','FIELD_STATUS','状态字段','1','状态',null,'1',null,null),
('1185416711777755138','FIELD_STATUS','状态字段','2','status',null,'1',null,null),
('1185423973135941633','FIELD_BIRTHDAY','出生日期字段','1','出生日期',null,'1',null,null),
('1185424101922045953','FIELD_BIRTHDAY','出生日期字段','2','birthday',null,'1',null,null),
('1185424218197794819','TITLE_ADD_USER','添加用户标题','1','添加用户',null,'1',null,null),
('1185424218197794820','TITLE_ADD_USER','添加用户标题','2','add user',null,'1',null,null),
('1185424218197794821','TITLE_EDIT_USER','编辑用户标题','1','编辑用户',null,'1',null,null),
('1185424218197794822','TITLE_EDIT_USER','编辑用户标题','2','edit user',null,'1',null,null),
('1185424218197794823','TITLE_ROLE_ASSIGN','角色分配标题','1','角色分配',null,'1',null,null),
('1185424218197794824','TITLE_ROLE_ASSIGN','角色分配标题','2','role assign',null,'1',null,null),
('1185424218197794825','TITLE_ADD_ROLE','添加角色标题','1','添加角色',null,'1',null,null),
('1185424218197794826','TITLE_ADD_ROLE','添加角色标题','2','add role',null,'1',null,null),
('1185424218197794827','TITLE_EDIT_ROLE','修改角色标题','1','修改角色',null,'1',null,null),
('1185424218197794828','TITLE_EDIT_ROLE','修改角色标题','2','edit role',null,'1',null,null),
('1185424218197794829','TITLE_ROLE_CONFIG','权限配置标题','1','权限配置',null,'1',null,null),
('1185424218197794830','TITLE_ROLE_CONFIG','权限配置标题','2','role config',null,'1',null,null),
('1185424218197794831','TITLE_ADD_DEPT','添加部门标题','1','添加部门',null,'1',null,null),
('1185424218197794832','TITLE_ADD_DEPT','添加部门标题','2','add dept',null,'1',null,null),
('1185424218197794833','TITLE_EDIT_DEPT','修改部门标题','1','修改部门',null,'1',null,null),
('1185424218197794834','TITLE_EDIT_DEPT','修改部门标题','2','edit dept',null,'1',null,null),
('1185424218197794835','TITLE_ADD_POST','添加职位标题','1','添加职位',null,'1',null,null),
('1185424218197794836','TITLE_ADD_POST','添加职位标题','2','add post',null,'1',null,null),
('1185424218197794837','TITLE_EDIT_POST','修改职位标题','1','修改职位',null,'1',null,null),
('1185424218197794838','TITLE_EDIT_POST','修改职位标题','2','edit post',null,'1',null,null),
('1185424218197794839','TITLE_ADD_DICT_TYPE','添加字典类型标题','1','添加字典类型',null,'1',null,null),
('1185424218197794840','TITLE_ADD_DICT_TYPE','添加字典类型标题','2','add dict type',null,'1',null,null),
('1185424218197794841','TITLE_EDIT_DICT_TYPE','修改字典类型标题','1','修改字典类型',null,'1',null,null),
('1185424218197794842','TITLE_EDIT_DICT_TYPE','修改字典类型标题','2','edit dict type',null,'1',null,null),
('1185424218197794843','TITLE_ADD_DICT','添加字典标题','1','添加字典',null,'1',null,null),
('1185424218197794844','TITLE_ADD_DICT','添加字典标题','2','add dict',null,'1',null,null),
('1185424218197794845','TITLE_EDIT_DICT','修改字典标题','1','修改字典',null,'1',null,null),
('1185424218197794846','TITLE_EDIT_DICT','修改字典标题','2','edit dict',null,'1',null,null),
('1185424218197794847','TITLE_ADD_MENU','添加菜单标题','1','添加菜单',null,'1',null,null),
('1185424218197794848','TITLE_ADD_MENU','添加菜单标题','2','add menu',null,'1',null,null),
('1185424218197794849','TITLE_EDIT_MENU','修改菜单标题','1','修改菜单',null,'1',null,null),
('1185424218197794850','TITLE_EDIT_MENU','修改菜单标题','2','edit menu',null,'1',null,null),
('1185424218197794851','TITLE_LOG_DETAIL','日志详情标题','1','日志详情',null,'1',null,null),
('1185424218197794852','TITLE_LOG_DETAIL','日志详情标题','2','log detail',null,'1',null,null),
('1185424218197794853','TITLE_SELECT_FIELD','选择字段标题','1','选择字段',null,'1',null,null),
('1185424218197794854','TITLE_SELECT_FIELD','选择字段标题','2','select field',null,'1',null,null),
('1185424218197794855','TITLE_ADD_LANGUAGE','添加多语言标题','1','添加多语言',null,'1',null,null),
('1185424218197794856','TITLE_ADD_LANGUAGE','添加多语言标题','2','add language',null,'1',null,null),
('1185424218197794857','TITLE_EDIT_LANGUAGE','修改多语言标题','1','修改多语言',null,'1',null,null),
('1185424218197794858','TITLE_EDIT_LANGUAGE','修改多语言标题','2','edit language',null,'1',null,null),
('1185424218197794859','TITLE_ADD','添加标题','1','添加',null,'1',null,null),
('1185424218197794860','TITLE_ADD','添加标题','2','add',null,'1',null,null),
('1185424218197794861','TITLE_PROCESS_DESIGNER','流程设计器标题','1','流程设计器',null,'1',null,null),
('1185424218197794862','TITLE_PROCESS_DESIGNER','流程设计器标题','2','process designer',null,'1',null,null),
('1185424218197794863','TITLE_IMPORT_PROCESS','导入流程标题','1','导入流程',null,'1',null,null),
('1185424218197794864','TITLE_IMPORT_PROCESS','导入流程标题','2','import process',null,'1',null,null),
('1185424218197794865','TITLE_EDIT','修改标题','1','修改标题',null,'1',null,null),
('1185424218197794866','TITLE_EDIT','修改标题','2','edit',null,'1',null,null),
('1185424218197794867','TITLE_PREVIEW_PROCESS','预览流程xml标题','1','预览流程xml',null,'1',null,null),
('1185424218197794868','TITLE_PREVIEW_PROCESS','预览流程xml标题','2','preview process xml',null,'1',null,null),
('1185424218197794869','TITLE_APPOINT','指定委派对象标题','1','指定委派对象',null,'1',null,null),
('1185424218197794870','TITLE_APPOINT','指定委派对象标题','2','appoint',null,'1',null,null),
('1185424218197794871','TITLE_HANDLE','办理标题','1','办理',null,'1',null,null),
('1185424218197794872','TITLE_HANDLE','办理标题','2','handle',null,'1',null,null),
('1185424218197794873','TITLE_PROCESS_INFO','流程信息标题','1','流程信息',null,'1',null,null),
('1185424218197794874','TITLE_PROCESS_INFO','流程信息标题','2','process info',null,'1',null,null),
('1185424218197794875','MENU_DASHBOARD','菜单_主控面板','1','主控面板',null,'1',null,null),
('1185424218197794876','MENU_DASHBOARD','菜单_主控面板','2','dashboard',null,'1',null,null),
('1185424218197794877','MENU_CONSOLE','菜单_项目介绍','1','项目介绍',null,'1',null,null),
('1185424218197794878','MENU_CONSOLE','菜单_项目介绍','2','project info',null,'1',null,null),
('1185424218197794879','MENU_CONSOLE2','菜单_统计报表','1','统计报表',null,'1',null,null),
('1185424218197794880','MENU_CONSOLE2','菜单_统计报表','2','stat',null,'1',null,null),
('1185424218197794881','MENU_SYS_MESSAGE','菜单_消息通知','1','消息通知',null,'1',null,null),
('1185424218197794882','MENU_SYS_MESSAGE','菜单_消息通知','2','message info',null,'1',null,null),
('1185424218197794883','MENU_SYSTEM_INFO','菜单_系统监控','1','系统监控',null,'1',null,null),
('1185424218197794884','MENU_SYSTEM_INFO','菜单_系统监控','2','monitor',null,'1',null,null),
('1185424218197794885','MENU_SYSTEM','菜单_系统管理','1','系统管理',null,'1',null,null),
('1185424218197794886','MENU_SYSTEM','菜单_系统管理','2','system',null,'1',null,null),
('1185424218197794887','MENU_MGR','菜单_用户管理','1','用户管理',null,'1',null,null),
('1185424218197794888','MENU_MGR','菜单_用户管理','2','users',null,'1',null,null),
('1185424218197794889','MENU_ROLE','菜单_角色管理','1','角色管理',null,'1',null,null),
('1185424218197794890','MENU_ROLE','菜单_角色管理','2','roles',null,'1',null,null),
('1185424218197794891','MENU_DEPT','菜单_部门管理','1','部门管理',null,'1',null,null),
('1185424218197794892','MENU_DEPT','菜单_部门管理','2','depts',null,'1',null,null),
('1185424218197794893','MENU_SYS_POSITION','菜单_职位管理','1','职位管理',null,'1',null,null),
('1185424218197794894','MENU_SYS_POSITION','菜单_职位管理','2','posts',null,'1',null,null),
('1185424218197794895','MENU_DICT','菜单_字典管理','1','字典管理',null,'1',null,null),
('1185424218197794896','MENU_DICT','菜单_字典管理','2','dict type',null,'1',null,null),
('1185424218197794897','MENU_MENU','菜单_菜单管理','1','菜单管理',null,'1',null,null),
('1185424218197794898','MENU_MENU','菜单_菜单管理','2','menus',null,'1',null,null),
('1185424218197794899','MENU_LOGINLOG','菜单_登录日志','1','登录日志',null,'1',null,null),
('1185424218197794900','MENU_LOGINLOG','菜单_登录日志','2','login log',null,'1',null,null),
('1185424218197794901','MENU_LOG','菜单_业务日志','1','业务日志',null,'1',null,null),
('1185424218197794902','MENU_LOG','菜单_业务日志','2','business log',null,'1',null,null),
('1185424218197794903','MENU_DRUID','菜单_监控管理','1','监控管理',null,'1',null,null),
('1185424218197794904','MENU_DRUID','菜单_监控管理','2','druid monitor',null,'1',null,null),
('1185424218197794905','MENU_NOTICE','菜单_通知管理','1','通知管理',null,'1',null,null),
('1185424218197794906','MENU_NOTICE','菜单_通知管理','2','messages',null,'1',null,null),
('1185424218197794907','MENU_TENANT_INFO','菜单_租户管理','1','租户管理',null,'1',null,null),
('1185424218197794908','MENU_TENANT_INFO','菜单_租户管理','2','tenants',null,'1',null,null),
('1185424218197794909','MENU_DEV_TOOLS','菜单_开发管理','1','开发管理',null,'1',null,null),
('1185424218197794910','MENU_DEV_TOOLS','菜单_开发管理','2','developer',null,'1',null,null),
('1185424218197794911','MENU_META_DATA','菜单_系统配置','1','系统配置',null,'1',null,null),
('1185424218197794912','MENU_META_DATA','菜单_系统配置','2','system config',null,'1',null,null),
('1185424218197794913','MENU_SYS_CONFIG','菜单_参数配置','1','参数配置',null,'1',null,null),
('1185424218197794914','MENU_SYS_CONFIG','菜单_参数配置','2','parameters',null,'1',null,null),
('1185424218197794915','MENU_DATA_SOURCE','菜单_数据源管理','1','数据源管理',null,'1',null,null),
('1185424218197794916','MENU_DATA_SOURCE','菜单_数据源管理','2','datasources',null,'1',null,null),
('1185424218197794917','MENU_CODE_GENERATE','菜单_代码生成','1','代码生成',null,'1',null,null),
('1185424218197794918','MENU_CODE_GENERATE','菜单_代码生成','2','generator',null,'1',null,null),
('1185424218197794919','MENU_API_MGR','菜单_接口文档','1','接口文档',null,'1',null,null),
('1185424218197794920','MENU_API_MGR','菜单_接口文档','2','api docs',null,'1',null,null),
('1185424218197794921','MENU_DEMOS_SHOW','菜单_模板页面','1','模板页面',null,'1',null,null),
('1185424218197794922','MENU_DEMOS_SHOW','菜单_模板页面','2','examples',null,'1',null,null),
('1185424218197794923','MENU_EXCEL_IMPORT','菜单_excel导入','1','excel导入',null,'1',null,null),
('1185424218197794924','MENU_EXCEL_IMPORT','菜单_excel导入','2','excel import',null,'1',null,null),
('1185424218197794925','MENU_EXCEL_EXPORT','菜单_excel导出','1','excel导出',null,'1',null,null),
('1185424218197794926','MENU_EXCEL_EXPORT','菜单_excel导出','2','excel export',null,'1',null,null),
('1185424218197794927','MENU_ADVANCED_FORM','菜单_高级表单','1','高级表单',null,'1',null,null),
('1185424218197794928','MENU_ADVANCED_FORM','菜单_高级表单','2','advanced form',null,'1',null,null),
('1185424218197794929','MENU_PDF_VIEW','菜单_文档预览','1','文档预览',null,'1',null,null),
('1185424218197794930','MENU_PDF_VIEW','菜单_文档预览','2','preview office',null,'1',null,null),
('1185424218197794931','MENU_TRANSLATION','菜单_多语言配置','1','多语言配置',null,'1',null,null),
('1185424218197794932','MENU_TRANSLATION','菜单_多语言配置','2','languages',null,'1',null,null),
('1185424218197794933','MENU_ONLINE_OFFICE','菜单_在线办公','1','在线办公',null,'1',null,null),
('1185424218197794934','MENU_ONLINE_OFFICE','菜单_在线办公','2','online office',null,'1',null,null),
('1185424218197794935','MENU_OFFICE_MODEL','菜单_工作流程','1','工作流程',null,'1',null,null),
('1185424218197794936','MENU_OFFICE_MODEL','菜单_工作流程','2','workflow',null,'1',null,null),
('1185424218197794937','MENU_MODEL_MANAGER','菜单_模型管理','1','模型管理',null,'1',null,null),
('1185424218197794938','MENU_MODEL_MANAGER','菜单_模型管理','2','models',null,'1',null,null),
('1185424218197794939','MENU_PROCDEF','菜单_流程管理','1','流程管理',null,'1',null,null),
('1185424218197794940','MENU_PROCDEF','菜单_流程管理','2','workflow manager',null,'1',null,null),
('1185424218197794941','MENU_PROCESS_ON','菜单_运行中流程','1','运行中流程',null,'1',null,null),
('1185424218197794942','MENU_PROCESS_ON','菜单_运行中流程','2','running',null,'1',null,null),
('1185424218197794943','MENU_HIPROCDEF','菜单_历史流程','1','历史流程',null,'1',null,null),
('1185424218197794944','MENU_HIPROCDEF','菜单_历史流程','2','history',null,'1',null,null),
('1185424218197794945','MENU_TASK_MANAGER','菜单_任务管理','1','任务管理',null,'1',null,null),
('1185424218197794946','MENU_TASK_MANAGER','菜单_任务管理','2','task manager',null,'1',null,null),
('1185424218197794947','MENU_RUTASK','菜单_待办任务','1','待办任务',null,'1',null,null),
('1185424218197794948','MENU_RUTASK','菜单_待办任务','2','task to do',null,'1',null,null),
('1185424218197794949','MENU_HAVETASK','菜单_已办任务','1','已办任务',null,'1',null,null),
('1185424218197794950','MENU_HAVETASK','菜单_已办任务','2','done task',null,'1',null,null),
('1185424218197794951','MENU_APPROVE','菜单_申请审批','1','申请审批',null,'1',null,null),
('1185424218197794952','MENU_APPROVE','菜单_申请审批','2','apply for',null,'1',null,null),
('1185424218197794953','MENU_LEAVE','菜单_请假审批','1','请假审批',null,'1',null,null),
('1185424218197794954','MENU_LEAVE','菜单_请假审批','2','apply for leave',null,'1',null,null),
('1185424218197794955','TITLE_BASE_INFO','标题_基本信息','1','基本信息',null,'1',null,null),
('1185424218197794956','TITLE_BASE_INFO','标题_基本信息','2','base info',null,'1',null,null),
('1185424218197794957','TITLE_DUTY_INFO','标题_职务信息','1','职务信息',null,'1',null,null),
('1185424218197794958','TITLE_DUTY_INFO','标题_职务信息','2','duty info',null,'1',null,null),
('1185424226450931714','FIELD_PASSWORD','密码字段','1','密码',null,'1',null,null),
('1185424290489565185','FIELD_PASSWORD','密码字段','2','password',null,'1',null,null),
('1185424619130060801','FIELD_REPEAT_PASSWORD','重复密码字段','2','repeat password',null,'1',null,'1'),
('1185424729297649665','FIELD_REPEAT_PASSWORD','重复密码字段','1','重复密码',null,'1',null,null),
('1185424867197976577','FIELD_EMAIL','邮箱字段','1','邮箱',null,'1',null,null),
('1185424928715833346','FIELD_EMAIL','邮箱字段','2','email',null,'1',null,null),
('1185425063239745538','FIELD_SEX','性别字段','1','性别',null,'1',null,null),
('1185425140188446722','FIELD_SEX','性别字段','2','sex',null,'1',null,null),
('1185426601941139661','FIELD_OPERATION','操作字段','1','操作',null,'1',null,null),
('1185426601941139662','FIELD_OPERATION','操作字段','2','operation',null,'1',null,null),
('1185426859010031617','FIELD_NAME','名称字段','1','名称',null,'1',null,null),
('1185426937057640450','FIELD_NAME','名称字段','2','name',null,'1',null,null),
('1185427225235685377','FIELD_SUPERIOR_ROLE','上级角色字段','1','上级角色',null,'1',null,null),
('1185427320681267202','FIELD_SUPERIOR_ROLE','上级角色字段','2','superior role',null,'1',null,'1'),
('1185427531721867266','FIELD_ALIAS','别名字段','2','alias',null,'1',null,'1'),
('1185427681475297281','FIELD_ALIAS','别名字段','1','别名',null,'1',null,null),
('1185427922840715265','FIELD_ROLE_NAME','角色名称字段','1','角色名称',null,'1',null,null),
('1185428016252059650','FIELD_ROLE_NAME','角色名称字段','2','role name',null,'1',null,'1'),
('1185428250399080450','FIELD_SUPERIOR_NAME','上级名称字段','1','上级名称',null,'1',null,null),
('1185428394779607041','FIELD_SUPERIOR_NAME','上级名称字段','2','superior name',null,'1',null,'1'),
('1185428542482022402','FIELD_SORT','排序字段','1','排序',null,'1',null,null),
('1185428621955694593','FIELD_SORT','排序字段','2','sort',null,'1',null,null),
('1185429113037389826','FIELD_DEPT_SHORT_NAME','部门简称字段','1','部门简称',null,'1',null,'1'),
('1185429265257070594','FIELD_DEPT_SHORT_NAME','部门简称字段','2','dept short name',null,'1',null,'1'),
('1185429532220325889','FIELD_DEPT_FULL_NAME','部门全称字段','1','部门全称',null,'1',null,null),
('1185429673622896642','FIELD_DEPT_FULL_NAME','部门全称字段','2','dept full name',null,'1',null,'1'),
('1185430031527051266','FIELD_REMARK','备注字段','1','备注',null,'1',null,null),
('1185430121348071425','FIELD_REMARK','备注字段','2','remark',null,'1',null,'1'),
('1185430332564832258','FIELD_DEPT_NAME','部门名称字段','1','部门名称',null,'1',null,null),
('1185430445257392130','FIELD_DEPT_NAME','部门名称字段','2','dept name',null,'1',null,'1'),
('1185430718591795201','FIELD_POST_NAME','职位名称字段','1','职位名称',null,'1',null,null),
('1185430791140671489','FIELD_POST_NAME','职位名称字段','2','post name',null,'1',null,'1'),
('1185430947961503745','FIELD_POST_CODE','职位编码字段','1','职位编码',null,'1',null,null),
('1185431063556521985','FIELD_POST_CODE','职位编码字段','2','post code',null,'1',null,'1'),
('1185431297082785793','FIELD_ORDER','顺序字段','1','顺序',null,'1',null,null),
('1185431380062896129','FIELD_ORDER','顺序字段','2','order',null,'1',null,null),
('1185431632866181121','FIELD_TYPE_NAME','类型名称字段','1','类型名称',null,'1',null,null),
('1185431700226703361','FIELD_TYPE_NAME','类型名称字段','2','type name',null,'1',null,'1'),
('1185432944068526081','FIELD_TYPE_CODE','类型编码字段','1','类型编码',null,'1',null,null),
('1185433063430029314','FIELD_TYPE_CODE','类型编码字段','2','type code',null,'1',null,null),
('1185433367965859841','FIELD_SYSTEM_DICT_FLAG','是否是系统字典字段','1','是否是系统字典',null,'1',null,null),
('1185433533968023554','FIELD_SYSTEM_DICT_FLAG','是否是系统字典字段','2','system dictionary or not',null,'1',null,null),
('1185433719826022402','FIELD_DICT_DESCRIPTION','字典描述字段','1','字典描述',null,'1',null,null),
('1185433828135534593','FIELD_DICT_DESCRIPTION','字典描述字段','2','dict deion',null,'1',null,null),
('1185434066179063809','FIELD_ADD_TIME','添加时间字段','1','添加时间',null,'1',null,null),
('1185434203345387522','FIELD_ADD_TIME','添加时间字段','2','add time',null,'1',null,null),
('1185434492697837569','FIELD_SYSTEM_DICT','系统字典字段','1','系统字典',null,'1',null,null),
('1185434574587428865','FIELD_SYSTEM_DICT','系统字典字段','2','system dict',null,'1',null,null),
('1185434888703049729','FIELD_YES','是字段','1','是',null,'1',null,null),
('1185435024334258177','FIELD_YES','是字段','2','yes',null,'1',null,null),
('1185435102755160066','FIELD_NO','否字段','1','否',null,'1',null,null),
('1185435206937477122','FIELD_NO','否字段','2','no',null,'1',null,null),
('1185435476765442049','FIELD_MENU_NAME','菜单名称字段','1','菜单名称',null,'1',null,null),
('1185435537318608897','FIELD_MENU_NAME','菜单名称字段','2','menu name',null,'1',null,null),
('1185435685855690754','FIELD_MENU_CODE','菜单编号字段','1','菜单编号',null,'1',null,null),
('1185435816109801473','FIELD_MENU_CODE','菜单编号字段','2','menu code',null,'1',null,null),
('1185436012176736257','FIELD_MENU_PCODE','菜单父编号字段','1','菜单父编号',null,'1',null,null),
('1185436181475622913','FIELD_MENU_PCODE','菜单父编号字段','2','menu parent code',null,'1',null,null),
('1185436494915960834','FIELD_REQUEST_URL','请求地址字段','1','请求地址',null,'1',null,null),
('1185436554617683970','FIELD_REQUEST_URL','请求地址字段','2','request url',null,'1',null,null),
('1185437647829467138','FIELD_LEVEL','层级字段','1','层级',null,'1',null,null),
('1185437715454230530','FIELD_LEVEL','层级字段','2','level',null,'1',null,null),
('1185437912972394497','FIELD_MENU_FLAG','是否是菜单字段','1','是否是菜单',null,'1',null,null),
('1185438098784256002','FIELD_MENU_FLAG','是否是菜单字段','2','Is it the menu',null,'1',null,null),
('1185438543414034433','FIELD_PARENT_CODE','父级编号字段','1','父级编号',null,'1',null,null),
('1185438666789486594','FIELD_PARENT_CODE','父级编号字段','2','parent code',null,'1',null,null),
('1185438827003510786','FIELD_ICON','图标字段','1','图标',null,'1',null,null),
('1185438903448895489','FIELD_ICON','图标字段','2','icon',null,'1',null,null),
('1185439184152690689','FIELD_SYSTEM_CLASSIFY','系统分类字段','1','系统分类',null,'1',null,null),
('1185439285638070273','FIELD_SYSTEM_CLASSIFY','系统分类字段','2','system classify',null,'1',null,null),
('1185439883024400386','FIELD_LOG_NAME','日志名称字段','1','日志名称',null,'1',null,null),
('1185439985445109761','FIELD_LOG_NAME','日志名称字段','2','log name',null,'1',null,null),
('1185440188059353089','FIELD_USER_NAME','用户名称字段','1','用户名称',null,'1',null,null),
('1185440312504352770','FIELD_USER_NAME','用户名称字段','2','username',null,'1',null,null),
('1185440464552067074','FIELD_TIME','时间字段','1','时间',null,'1',null,null),
('1185440522823532545','FIELD_TIME','时间字段','2','time',null,'1',null,null),
('1185440770258108418','FIELD_SPECIFIC_MESSAGE','具体消息字段','1','具体消息',null,'1',null,null),
('1185440854764945410','FIELD_SPECIFIC_MESSAGE','具体消息字段','2','specific message',null,'1',null,null),
('1185441078254239745','FIELD_IP','ip字段','1','IP 地址',null,'1',null,null),
('1185441170583453698','FIELD_IP','ip字段','2','IP address',null,'1',null,null),
('1185441349017534466','FIELD_LOG_TYPE','日志类型字段','1','日志类型',null,'1',null,null),
('1185441443485843457','FIELD_LOG_TYPE','日志类型字段','2','log type',null,'1',null,null),
('1185441718556688385','FIELD_CLASS_NAME','类名字段','1','类名',null,'1',null,null),
('1185441804468617217','FIELD_CLASS_NAME','类名字段','2','class name',null,'1',null,null),
('1185441942285058050','FIELD_METHOD_NAME','方法名字段','1','方法名',null,'1',null,null),
('1185442053983567873','FIELD_METHOD_NAME','方法名字段','2','method name',null,'1',null,null),
('1185442292647854082','FIELD_TITLE','标题字段','1','标题',null,'1',null,null),
('1185442345877766146','FIELD_TITLE','标题字段','2','title',null,'1',null,null),
('1185442451666501634','FIELD_CONTENT','内容字段','1','内容',null,'1',null,null),
('1185442509879246850','FIELD_CONTENT','内容字段','2','content',null,'1',null,null),
('1185442781288464386','FIELD_PUBLISHER','发布者字段','1','发布者',null,'1',null,null),
('1185442840667226114','FIELD_PUBLISHER','发布者字段','2','publisher',null,'1',null,null),
('1185443425298677761','FIELD_TENANT_NAME','租户名称字段','1','租户名称',null,'1',null,null),
('1185443487567314946','FIELD_TENANT_NAME','租户名称字段','2','tenant name',null,'1',null,null),
('1185443685811093505','FIELD_TENANT_CODE','租户编码字段','1','租户编码',null,'1',null,null),
('1185443788189859842','FIELD_TENANT_CODE','租户编码字段','2','tenant code',null,'1',null,null),
('1185444109146390529','FIELD_RELATED_DBNAME','关联的数据库名称字段','1','关联的数据库名称',null,'1',null,null),
('1185444234589634562','FIELD_RELATED_DBNAME','关联的数据库名称字段','2','related database name',null,'1',null,null),
('1185444365300924418','FIELD_CREATE_USER','创建人字段','1','创建人',null,'1',null,null),
('1185444442509672450','FIELD_CREATE_USER','创建人字段','2','create user',null,'1',null,null),
('1185444600110645250','FIELD_UPDATE_USER','更新人字段','1','更新人',null,'1',null,null),
('1185444724622753794','FIELD_UPDATE_USER','更新人字段','2','update user',null,'1',null,null),
('1185445022653218817','FIELD_MANAGE_ACCOUNT','管理账号字段','1','管理账号',null,'1',null,null),
('1185445110721019906','FIELD_MANAGE_ACCOUNT','管理账号字段','2','manage account',null,'1',null,null),
('1185445335120478210','FIELD_MANAGE_PASSWORD','管理密码字段','1','管理密码',null,'1',null,null),
('1185445433598541826','FIELD_MANAGE_PASSWORD','管理密码字段','2','manage password',null,'1',null,null),
('1185445960260517890','FIELD_PROPERTY_CODE','属性编码字段','1','属性编码',null,'1',null,null),
('1185446030070513665','FIELD_PROPERTY_CODE','属性编码字段','2','property code',null,'1',null,null),
('1185446413727694849','FIELD_PROPERTY_VALUE','属性值字段','1','属性值',null,'1',null,null),
('1185446595869540354','FIELD_PROPERTY_VALUE','属性值字段','2','property value',null,'1',null,'1'),
('1185446801579180034','FIELD_CODE','编码字段','1','编码',null,'1',null,null),
('1185446851126493185','FIELD_CODE','编码字段','2','code',null,'1',null,null),
('1185447058786484225','FIELD_VALUE_RANGE','取值范围字段','1','取值范围',null,'1',null,null),
('1185447132077752322','FIELD_VALUE_RANGE','取值范围字段','2','value range',null,'1',null,null),
('1185447295194234881','FIELD_DICT_TYPE','字典类型字段','1','字典类型',null,'1',null,null),
('1185447371748671489','FIELD_DICT_TYPE','字典类型字段','2','dict type',null,'1',null,null),
('1185447599964946434','FIELD_PARAMETER_VALUES','参数值字段','1','参数值',null,'1',null,null),
('1185447680533331969','FIELD_PARAMETER_VALUES','参数值字段','2','parameter values',null,'1',null,null),
('1185447840881573889','FIELD_DATABASE_NAME','数据库名称字段','1','数据库名称',null,'1',null,null),
('1185447972238786561','FIELD_DATABASE_NAME','数据库名称字段','2','database name',null,'1',null,null),
('1185448164220469250','FIELD_DRIVER_TYPE','驱动类型字段','1','驱动类型',null,'1',null,'1'),
('1185448326716194817','FIELD_DRIVER_TYPE','驱动类型字段','2','driver type',null,'1',null,null),
('1185448672251346946','FIELD_URL_OF_JDBC','JDBC URL字段','1','JDBC URL',null,'1',null,'1'),
('1185448754677809154','FIELD_URL_OF_JDBC','JDBC URL字段','2','JDBC URL',null,'1',null,'1'),
('1185449056055328770','FIELD_JDBC_DRIVER','JDBC驱动字段','1','JDBC驱动',null,'1',null,null),
('1185449139245154305','FIELD_JDBC_DRIVER','JDBC驱动字段','1','JDBC DRIVER',null,'1',null,null),
('1185449295659139073','FIELD_JDBC_ACCOUNT','JDBC账号字段','1','JDBC账号',null,'1',null,null),
('1185449405960945666','FIELD_JDBC_ACCOUNT','JDBC账号字段','2','JDBC ACCOUNT',null,'1',null,null),
('1185449617840406530','FIELD_JDBC_PASSWORD','JDBC密码字段','1','JDBC密码',null,'1',null,null),
('1185449694109630466','FIELD_JDBC_PASSWORD','JDBC密码字段','2','JDBC PASSWORD',null,'1',null,null),
('1185450328393252866','FIELD_AUTHOR','作者字段','1','作者',null,'1',null,null),
('1185450389260992513','FIELD_AUTHOR','作者字段','2','author',null,'1',null,null),
('1185450575773302785','FIELD_PROJECT_PACKAGE_NAME','项目包名称字段','1','项目包名称',null,'1',null,null),
('1185450683680161793','FIELD_PROJECT_PACKAGE_NAME','项目包名称字段','2','project package name',null,'1',null,null),
('1185450910927552514','FIELD_MODULE_NAME','模块名称字段','1','模块名称',null,'1',null,null),
('1185450981513494530','FIELD_MODULE_NAME','模块名称字段','2','module name',null,'1',null,null),
('1185451130771996673','FIELD_TABLE_REMOVE_PREFIX','表前缀移除字段','1','表前缀移除',null,'1',null,null),
('1185451233670856706','FIELD_TABLE_REMOVE_PREFIX','表前缀移除字段','2','remove table prefix',null,'1',null,null),
('1185451382853861377','FIELD_CHOOSE_DATABASE','数据源选择字段','1','数据源选择',null,'1',null,null),
('1185451502580269058','FIELD_CHOOSE_DATABASE','数据源选择字段','2','choose database',null,'1',null,null),
('1185451879371374593','FIELD_CHOOSE_VERSION','版本选择字段','1','版本选择',null,'1',null,null),
('1185451990151331842','FIELD_CHOOSE_VERSION','版本选择字段','2','choose version',null,'1',null,null),
('1185452118270541825','FIELD_CHOOSE_TABLE','选择表字段','1','选择表',null,'1',null,null),
('1185452193617018882','FIELD_CHOOSE_TABLE','选择表字段','2','choose table',null,'1',null,null),
('1185452567182704642','FIELD_LANGUAGE','语种字段','1','语种',null,'1',null,null),
('1185452630323757057','FIELD_LANGUAGE','语种字段','2','language',null,'1',null,null),
('1185452939716591618','FIELD_TRANSLATION_VALUE','翻译值字段','1','翻译值',null,'1',null,null),
('1185453377996193793','FIELD_TRANSLATION_VALUE','翻译值字段','2','the value of the translation',null,'1',null,null),
('1185454151333576706','FIELD_MODEL_NAME','模型名称字段','1','模型名称',null,'1',null,null),
('1185454213409275905','FIELD_MODEL_NAME','模型名称字段','2','model name',null,'1',null,null),
('1185454352857300993','FIELD_CLASSIFY','分类字段','1','分类',null,'1',null,null),
('1185454468288741377','FIELD_CLASSIFY','分类字段','2','classify',null,'1',null,null),
('1185454911110774786','FIELD_MODEL_CLASSIFY','模型分类字段','1','模型分类',null,'1',null,null),
('1185454993281384449','FIELD_MODEL_CLASSIFY','模型分类字段','2','model classify',null,'1',null,null),
('1185455123162202113','FIELD_MODEL_DESCRIPTION','模型描述字段','1','模型描述',null,'1',null,null),
('1185455205060182018','FIELD_MODEL_DESCRIPTION','模型描述字段','2','model deion',null,'1',null,null),
('1185455362581463042','FIELD_FLOW_NAME','流程名称字段','1','流程名称',null,'1',null,null),
('1185455438246707202','FIELD_FLOW_NAME','流程名称字段','2','flow name',null,'1',null,null),
('1185455542206726146','FIELD_FLOW_FLAG','流程标识字段','1','流程标识',null,'1',null,null),
('1185455611786035202','FIELD_FLOW_FLAG','流程标识字段','2','flow flag',null,'1',null,null),
('1185455731176898562','FIELD_FLOW_AUTHOR','流程作者字段','1','流程作者',null,'1',null,null),
('1185455843567468545','FIELD_FLOW_AUTHOR','流程作者字段','2','flow author',null,'1',null,null),
('1185456133762973697','FIELD_FLOW_KEY','流程定义KEY字段','1','流程定义KEY',null,'1',null,null),
('1185456245516009473','FIELD_FLOW_KEY','流程定义KEY字段','2','the key of flow',null,'1',null,null),
('1185456382304845825','FIELD_VERSION','版本字段','1','版本',null,'1',null,null),
('1185456443038367746','FIELD_VERSION','版本字段','2','version',null,'1',null,null),
('1185456707891888130','FIELD_DEPLOY_TIME','部署时间字段','1','部署时间',null,'1',null,null),
('1185456786539282433','FIELD_DEPLOY_TIME','部署时间字段','2','deploy time',null,'1',null,null),
('1185456999723171842','FIELD_FLOW_BPMN_NAME','流程bpmn文件名称字段','1','流程bpmn文件名称',null,'1',null,null),
('1185457145794002945','FIELD_FLOW_BPMN_NAME','流程bpmn文件名称字段','2','process BPMN file name',null,'1',null,null),
('1185457317659803649','FIELD_PIC_NAME','流程图片名称字段','1','流程图片名称',null,'1',null,null),
('1185457486820278274','FIELD_PIC_NAME','流程图片名称字段','2','process picture name',null,'1',null,null),
('1185457756451110914','FIELD_PROPOSER','申请人字段','1','申请人',null,'1',null,null),
('1185457820938534913','FIELD_PROPOSER','申请人字段','2','proposer',null,'1',null,null),
('1185458019140370433','FIELD_CURRENT_NODE','当前节点(审批人)字段','1','当前节点(审批人)',null,'1',null,null),
('1185458125159792642','FIELD_CURRENT_NODE','当前节点(审批)字段','2','current node(proposer)',null,'1',null,null),
('1185458283322802178','FIELD_CURRENT_TASK','当前任务字段','1','当前任务',null,'1',null,null),
('1185458463648514049','FIELD_CURRENT_TASK','当前任务字段','2','current task',null,'1',null,null),
('1185458668015976449','FIELD_FLOW_VERSION','流程版本字段','1','流程版本',null,'1',null,null),
('1185458770356994050','FIELD_FLOW_VERSION','流程版本字段','2','flow version',null,'1',null,null),
('1185458987953291266','FIELD_START_TIME','开始时间字段','1','开始时间',null,'1',null,null),
('1185459059571032066','FIELD_START_TIME','开始时间字段','2','start time',null,'1',null,null),
('1185459223505403906','FIELD_END_TIME','结束时间字段','1','结束时间',null,'1',null,null),
('1185459286055059458','FIELD_END_TIME','结束时间字段','2','end time',null,'1',null,null),
('1185459456683540481','FIELD_USED_TIME','用时字段','1','用时',null,'1',null,null),
('1185459592042119170','FIELD_USED_TIME','用时字段','2','total time',null,'1',null,null),
('1185459909597069314','FIELD_CURRENT_COMMISSION','当前节点(代办人)字段','1','当前节点(代办人)',null,'1',null,null),
('1185460049523245057','FIELD_CURRENT_COMMISSION','当前节点(代办人)字段','2','current node(commission)',null,'1',null,null),
('1185460365564051458','FIELD_COMMISSON_OR_ROLE','办理人 or 角色字段','1','办理人 or 角色',null,'1',null,null),
('1185460556107087873','FIELD_COMMISSON_OR_ROLE','办理人 or 角色字段','2','transactor or role',null,'1',null,null),
('1185460758096379905','FIELD_TYPE','类型字段','1','类型',null,'1',null,null),
('1185460807480115202','FIELD_TYPE','类型字段','2','type',null,'1',null,null),
('1185461111927865346','FIELD_SHIYOU','事由字段','1','事由',null,'1',null,null),
('1185461185273659393','FIELD_SHIYOU','事由字段','2','the origin of an incident',null,'1',null,null),
('1185461553730682882','FIELD_LEAVE_TYPE','请假类型字段','1','请假类型',null,'1',null,null),
('1185461741706805250','FIELD_LEAVE_TYPE','请假类型字段','2','type of the ask for leave',null,'1',null,null),
('1185496726711160834','FIELD_NUMBER','序号字段','1','序号',null,'1',null,null),
('1185497214294806530','FIELD_NUMBER','序号字段','2','number',null,'1',null,null),
('1185506150615994369','FIELD_FILE','文件字段','1','文件',null,'1',null,null),
('1185506213719298049','FIELD_FILE','文件字段','2','file',null,'1',null,null),
('1185506474894413826','FIELD_CHOOSE_FILE','选择文件字段','1','选择文件',null,'1',null,null),
('1185506615659450369','FIELD_CHOOSE_FILE','选择文件字段','2','select the file',null,'1',null,null),
('1185513372263727106','FIELD_RUNNING','正在运行字段','1','正在运行',null,'1',null,null),
('1185513454203650049','FIELD_RUNNING','正在运行字段','2','running',null,'1',null,null),
('1185513675730010113','FIELD_HANG_UP','已挂起字段','1','已挂起',null,'1',null,null),
('1185513675730010114','FIELD_HANG_UP','已挂起字段','2','hang up',null,'1',null,null),
('1185513675730011111','TIPS_NAME_PHONE','提示_姓名手机号','1','账号/姓名/手机号',null,'1',null,null),
('1185513768017211112','TIPS_NAME_PHONE','提示_姓名手机号','2','account/name/mobile',null,'1',null,null),
('1185513768017281027','TIPS_REG_TIME','提示_注册时间','1','注册时间',null,'1',null,null),
('1185513768017281028','TIPS_REG_TIME','提示_注册时间','2','register time',null,'1',null,null),
('1185513768017281029','TIPS_ROLE_NAME','提示_角色名称','1','角色名称',null,'1',null,null),
('1185513768017281030','TIPS_ROLE_NAME','提示_角色名称','2','role name',null,'1',null,null),
('1185513768017281031','TIPS_DEPT_NAME','提示_部门名称','1','部门名称',null,'1',null,null),
('1185513768017281032','TIPS_DEPT_NAME','提示_部门名称','2','dept name',null,'1',null,null),
('1185513768017281035','TIPS_POST_NAME','提示_职务名称','1','职务名称',null,'1',null,null),
('1185513768017281036','TIPS_POST_NAME','提示_职务名称','2','duty name',null,'1',null,null),
('1185513768017281037','TIPS_NAME_CODE','提示_名称编码','1','名称/编码',null,'1',null,null),
('1185513768017281038','TIPS_NAME_CODE','提示_名称编码','2','name/code',null,'1',null,null),
('1185513768017281039','TIPS_MENU_NAME','提示_菜单名称编码','1','菜单名称/编码',null,'1',null,null),
('1185513768017281040','TIPS_MENU_NAME','提示_菜单名称编码','2','menu name/code',null,'1',null,null),
('1185513768017281041','TIPS_LEVEL','提示_层级','1','层级',null,'1',null,null),
('1185513768017281042','TIPS_LEVEL','提示_层级','2','level',null,'1',null,null),
('1185513768017281043','TIPS_BEGIN_TIME','提示_开始时间','1','开始时间',null,'1',null,null),
('1185513768017281044','TIPS_BEGIN_TIME','提示_开始时间','2','begin time',null,'1',null,null),
('1185513768017281045','TIPS_END_TIME','提示_结束时间','1','结束时间',null,'1',null,null),
('1185513768017281046','TIPS_END_TIME','提示_结束时间','2','end time',null,'1',null,null),
('1185513768017281047','TIPS_LOG_NAME','提示_日志名称','1','日志名称',null,'1',null,null),
('1185513768017281048','TIPS_LOG_NAME','提示_日志名称','2','log name',null,'1',null,null),
('1185513768017281049','TIPS_TYPE','提示_类型','1','类型',null,'1',null,null),
('1185513768017281050','TIPS_TYPE','提示_类型','2','type',null,'1',null,null),
('1185513768017281051','TIPS_MESSAGE_NAME','提示_通知名称','1','通知名称',null,'1',null,null),
('1185513768017281052','TIPS_MESSAGE_NAME','提示_通知名称','2','message name',null,'1',null,null),
('1185513768017281053','TIPS_TENANT_NAME','提示_租户名称','1','租户名称',null,'1',null,null),
('1185513768017281054','TIPS_TENANT_NAME','提示_租户名称','2','tenant name',null,'1',null,null),
('1185513768017281055','TIPS_STATUS','提示_状态','1','状态',null,'1',null,null),
('1185513768017281056','TIPS_STATUS','提示_状态','2','status',null,'1',null,null),
('1185513768017281057','MENU_PERSONAL_INFO','菜单_个人中心','1','个人中心',null,'1',null,null),
('1185513768017281058','MENU_PERSONAL_INFO','菜单_个人中心','2','personal info',null,'1',null,null),
('1185513768017281059','MENU_CHANGE_PASSWORD','菜单_修改密码','1','修改密码',null,'1',null,null),
('1185513768017281060','MENU_CHANGE_PASSWORD','菜单_修改密码','2','change password',null,'1',null,null),
('1185513768017281061','MENU_LOGOUT','菜单_退出','1','退出',null,'1',null,null),
('1185513768017281062','MENU_LOGOUT','菜单_退出','2','logout',null,'1',null,null),
('1185513768017281063','MENU_OTHER','菜单_其他','1','其他',null,'1',null,null),
('1185513768017281064','MENU_OTHER','菜单_其他','2','other',null,'1',null,null),
('1185513768017281065','MENU_BASE_INFO','菜单_基本信息','1','基本信息',null,'1',null,null),
('1185513768017281066','MENU_BASE_INFO','菜单_基本信息','2','base info',null,'1',null,null),
('1185513768017281067','BTN_UPDATE_INFO','按钮_更新基本信息','1','更新基本信息',null,'1',null,null),
('1185513768017281068','BTN_UPDATE_INFO','按钮_更新基本信息','2','update',null,'1',null,null),
('1185513768017281069','FIELD_OLD_PASSWORD','字段_旧密码','1','旧密码',null,'1',null,null),
('1185513768017281070','FIELD_OLD_PASSWORD','字段_旧密码','2','old password',null,'1',null,null),
('1185513768017281071','FIELD_NEW_PASSWORD','字段_新密码','1','新密码',null,'1',null,null),
('1185513768017281072','FIELD_NEW_PASSWORD','字段_新密码','2','new password',null,'1',null,null),
('1185513768017281073','FIELD_RE_PASSWORD','字段_确认密码','1','确认密码',null,'1',null,null),
('1185513768017281074','FIELD_RE_PASSWORD','字段_确认密码','2','confirm password',null,'1',null,null),
('1185513768017281083','MENU_INDEX','菜单_首页','1','首页',null,'1',null,null),
('1185513768017281084','MENU_INDEX','菜单_首页','2','index',null,'1',null,null)

;
/****** Object:  table [dbo].[sys_user]  Script Date: 2019-11-23 22:13:17 ******/
GO


use [guns]
GO


CREATE TABLE [dbo].[sys_user]
(
	[user_id] bigint NOT NULL ,
	[avatar] varchar(255) NULL ,
	[account] varchar(45) NULL ,
	[password] varchar(45) NULL ,
	[salt] varchar(45) NULL ,
	[name] varchar(45) NULL ,
	[birthday] datetime NULL ,
	[sex] varchar(32) NULL ,
	[email] varchar(45) NULL ,
	[phone] varchar(45) NULL ,
	[role_id] varchar(255) NULL ,
	[dept_id] bigint NULL ,
	[status] varchar(32) NULL ,
	[create_time] datetime NULL ,
	[create_user] bigint NULL ,
	[update_time] datetime NULL ,
	[update_user] bigint NULL ,
	[version] int NULL 
)
ON [PRIMARY];
GO


EXECUTE sp_addextendedproperty  N'MS_Description', N'', N'SCHEMA', N'dbo', N'TABLE', N'sys_user', NULL, NULL;
GO

ALTER TABLE [dbo].[sys_user] ADD CONSTRAINT [PK_sys_user] PRIMARY KEY CLUSTERED 
(
[user_id]
)
WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON);
GO



insert into [dbo].[sys_user]([user_id],[avatar],[account],[password],[salt],[name],[birthday],[sex],[email],[phone],[role_id],[dept_id],[status],[create_time],[create_user],[update_time],[update_user],[version]) values
('1','1124606971782160385','admin','17db03c22596b7609c7c9704f16663e0','abcdef','stylefeng','2018-11-16 00:00:00.000','M','sn93@qq.com','18200000000','1','27','ENABLE','2016-01-29 08:49:53.000',null,'2018-12-28 22:52:24.000','24','25'),
('1198240116275146754',null,'1221','2276729a170e0b63a87427b4129c03b1','bhgz9','212121','2019-11-15 00:00:00.000','M','123@123.com','18200000000',null,'27','DELETED','2019-11-23 22:01:20.597','1','2019-11-23 22:03:24.657','1',null)

;
/****** Object:  table [dbo].[sys_user_pos]  Script Date: 2019-11-23 22:13:17 ******/
GO


use [guns]
GO


CREATE TABLE [dbo].[sys_user_pos]
(
	[user_pos_id] bigint NOT NULL ,
	[user_id] bigint NOT NULL ,
	[pos_id] bigint NOT NULL 
)
ON [PRIMARY];
GO


EXECUTE sp_addextendedproperty  N'MS_Description', N'', N'SCHEMA', N'dbo', N'TABLE', N'sys_user_pos', NULL, NULL;
GO

ALTER TABLE [dbo].[sys_user_pos] ADD CONSTRAINT [PK_sys_user_pos] PRIMARY KEY CLUSTERED 
(
[user_pos_id]
)
WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON);
GO



insert into [dbo].[sys_user_pos]([user_pos_id],[user_id],[pos_id]) values
('1144495219551617025','1','1')

;
/****** Object:  table [dbo].[tenant_info]  Script Date: 2019-11-23 22:13:17 ******/
GO


use [guns]
GO


CREATE TABLE [dbo].[tenant_info]
(
	[tenant_id] bigint NOT NULL ,
	[name] varchar(255) NULL ,
	[code] varchar(255) NULL ,
	[db_name] varchar(255) NULL ,
	[create_time] datetime NULL ,
	[create_user] bigint NULL ,
	[update_time] datetime NULL ,
	[update_user] bigint NULL 
)
ON [PRIMARY];
GO


EXECUTE sp_addextendedproperty  N'MS_Description', N'', N'SCHEMA', N'dbo', N'TABLE', N'tenant_info', NULL, NULL;
GO

ALTER TABLE [dbo].[tenant_info] ADD CONSTRAINT [PK_tenant_info] PRIMARY KEY CLUSTERED 
(
[tenant_id]
)
WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON);
GO





