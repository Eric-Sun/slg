系统缓存文档
=====
认证模块
------
+ key
	+ slg:auth:[uid]
+ value
	+ authTime:认证的时间戳
	+ authKey：认证的key

任务模块
-----
用户的任务完成信息

+ key
	+ slg:user:task:[uid]
+ value
	+ id: 用户id
	+ taskId:任务的id
	+ progress：现阶段任务完成的进度

用户模块
-----
用户状态信息

+ key
    + slg:user:status:[uid]
+ value
    + id:用户id
    + gold: 金币
    + food: 粮食
    + cash: 元宝
    + level: 当前用户等级
    + xp: 当前经验
    + name: 用户名称
    + soul: 魂魄数量
    + fightForce: 战斗力