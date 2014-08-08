文档总体概述
======
* 文档包括两个部分，请求和响应的介绍
* 请求字段包括如下的基本内容  
	mod：模块名称   
	act：动作名称   
	uid：用户的uid，如果没有uid的时候传-1   
	auth_key：登陆的时候获得的权限相关的key，如果key失效的时候会返回固定的错误码，则需要重新登陆，重新获取auth_key   
	auth_time:登陆的时候获得的相关时间戳
	seq：此次login登陆之后的操作的序号，每次登陆之后会重新从1开始计算
	args：为一个json的object对象
* 响应字段包括如下的基本内容   
	mod：模块名称   
	act：动作名称   
	args：请求中的相关参数   
	data：返回的数据，为一个json的object或者数组对象   
	serverTime：此次操作的服务器端的时间   
	code：用户的相关返回值，会在后面详细介绍每一个code对应的内容
* 每个接口描述中主要描述请求中的args的相关内容和返回值中的data中的相关内容
* 所有的请求的url为统一的url，只是通过post协议的中的mod和act的不同来区分，所以文档中没有对api做详细的描述
* 错误代码详见错误代码文档


用户相关接口
======
注册接口
-----
* 请求数据  
	* mod:user
	* act:register		 	 
	* name：用户名   
    * password：用户密码   
* 返回数据   
	* id：用户注册后的uid  
---

登陆接口
-----
* 请求数据   
	+ mod: user
	+ action: login
	+ name：用户名   
	+ password：密码   
+ 返回数据
    * uid   uid

-----

获得用户信息接口
------
* 请求数据
    * mod   user
    * action getInfo
* 返回数据
	* userStatus
		* id	用户id 
		* gold	金币数量
		* food	粮食的数量
		* cash	元宝的数量
		* honor	荣誉的数量
		* level	等级
		* xp	当前经验
		* name	用户名称
		* soul	魂魄数量
		* levelUpXp 升级所需xp
		* fightForce	战斗力
		* castleTimer	上一次城堡收获的时间戳	
		* farmCastle	上一次农场收获的时间戳
    * teamRoleList
        * id    用户角色id
        * name  用户角色名称

---	

城堡相关接口
=======
收获金币接口
-----
+ 请求数据
	* mod:castle
	* action:harvest
+ 响应数据
	* gold	金币数量
	* timer	收获时间的时间戳

----
	
农场相关接口
======
收获粮食接口
-------
+ 请求数据
	* mod:farm
	* act:harvest
+ 响应数据
	* food	粮食数量
	* timer	收获时候的时间戳


---

酒馆相关接口
========
邀请将领
------
+ 请求数据
+ 相应数据
	* 相应数据为数组
	* 每个数组对应的内容为
		* gold:消耗的金币
		* status: 0为没有被招募，1已经被招募
		* id:对应的role的id
		* roleName：角色的名字

--------

雇佣将领
------
+ 请求数据
	* mod	tavern
	* act	enroll	
+ 响应数据
	* attack	攻击力
	* curSkill 	选择的主动技能
	* defence	防御力
	* fightForce	战斗力
	* health	生命值
	* id	邀请将领列表中的序号
	* level	等级
	* roleId	role的id
	* skillLevel	所有技能的等级
	* soldier	兵种的类别
	* uid	用户id

----


送走将领
--------
+ 请求数据
	* mod	tavern
	* act	leave
+ 响应数据
	* soul	获得灵魂的数量


技能相关接口(todo)
========
升级技能
-------
+ 请求数据
	* 基本数据
		* mod	skill
		* act	upgrade
	* 参数
		* skill	技能序号
		* urid	用户将领的id
	
+ 响应数据
	* nextSkillLevel	下一个技能的等级


将领接口
=======
穿装备接口
-------
+ 请求数据
	* 基本数据
		* mod	role
		* act	wear
	* 参数
		* ueid	用户装备id
		* urid	用户角色id
+ 响应数据

脱装备接口
------
+ 请求数据
	* 基本数据
		* mod	role
		* act takeOff
	* 参数
		* ueid	用户装备id
		* urid	用户角色id
+ 响应数据

------	
	
获得将领列表
-------
+ 请求数据
	+ 基本数据
		+ mod	role
		+ act	roleList
	+ 参数
		
+ 响应数据
	+ 数组
		+ accessory	饰品
		+ armor		头盔
		+ weapon	武器
		+ attack	攻击力
		+ defence	防御力
		+ health	士兵数
		+ fightForce	战斗力
		+ id		用户将领的id
		+ roleId	将领id
		+ roleName	将领名称
		+ level		等级
		+ curSkill	当前开启的技能
		+ skillLevels： 为一个hash，有1-6作为key，每个技能的value为等 

---------
获得将领的详细信息(已经删除)
----------
+ 请求数据
	+ 基本数据
		+ mod	role
		+ act	role
	+ 参数
		+ urid	用户将领id
+ 响应数据
	+ role
		+ accessory	饰品
		+ armor		头盔
		+ weapon	武器
		+ attack	攻击力
		+ defence	防御力
		+ health	士兵数
		+ fightForce	战斗力
		+ id		用户将领的id
		+ roleId	将领id
		+ roleName	将领名称
		+ level		等级
		+ curSkill	当前开启的技能
		+ skillLevels： 为一个hash，有1-6作为key，每个技能的value为等 

-------

装备接口
=======
获得没有使用过的用户装备列表
-------
+ 请求数据
	+ mod:equip
	+ act:noUsedEquipList
	+ type:weapon,accessory,armor	
+ 返回数据
	+ 返回的数据是一个数据
		+ equipName：装备名称
		+ fail:失败次数
		+ gems:宝石相关的数据
		+ id：用户装备id
		+ level：装备等级
		+ refine：
		+ star：
		+ strength：强化等级		
		+ type：weapon，accessory，armor
		+ uid：用户id
		+ urid：用户role的id		

-----

强化
------
+ 请求数据
	+ mod:equip
	+ act:strengthen
	+ ueid:用户装备id
+ 返回数据
	+ gold:使用的金币数
	+ strength：强化后的等级

----
	
升级
------
+ 请求数据
	+ mod:equip
	+ act:make
	+ ueid:用户装备id
+ 返回数据
	+ level：升级后的等级
	+ material：hash为一个id和他的数据

-----
	
装备详细信息
------
+ 请求数据
	+ mod: equip
	+ act:equip
	+ ueid:用户装备id
+ 返回数据
	+ equipName：装备名称
	+ fail:失败次数
	+ gems:宝石相关的数据
	+ id：用户装备id
	+ level：装备等级
	+ refine：
	+ star：
	+ strength：强化等级		
	+ type：weapon，accessory，armor
	+ uid：用户id
	+ urid：用户role的id	
	+ materialType1CurrentCount：现在拥有的升级材料1的数量
	+ materialType1Id：升级材料1的类型
	+ materialType1Name：升级材料1的名称
	+ materialType1NeedCount：升级需要的材料1的数量
	+ materialType2CurrentCount：现在拥有的升级材料2的数量
	+ materialType2Id：升级材料2的类型
	+ materialType2Name：升级材料2的名称
	+ materialType2NeedCount：升级需要的材料2的数量
	+ needGold：强化需要的金币
	+ curGold：当前的拥有的金币数量

	
商店相关接口
=====
商店信息
-----
+ 请求数据
	+ mod：shop
	+ act：shopList
+ 返回数据：
	+ category1：一级类别
	+ category2：二级类别
	+ category3：三级类别
	+ currency：购买需要的货币
	+ id：商品id
	+ price：价格

------

购买商品
-----
+ 请求数据
	+ mod:inventory
	+ act:buy
	+ num:数量
	+ id：商品id
+ 返回数据
	+ cost_num：花费
	+ cost_type：货币类型

----

背包相关接口
=====

获取背包信息
------
+ 请求数据
    + mod:package
    + act:get
+ 返回数据
    + equip:为装备列表
        + id：用户装备id
        + name：装备名称
    + gemList：宝石列表
        + id：宝石材料的id
        + name：宝石的名称
    + gemMap: 宝石hash
        + key：宝石的id
        + value：现有的数量
    + materialList: 材料列表
    + materialMap: 材料hash

--------

战斗相关接口
=========
获得战斗的章节相关信息（此接口为章节、城堡、将领三级中的第一级）
--------
+ 请求数据
    + mod：battle
    + act：chapterList
+ 返回数据
    + string的列表，每个元素为章节的名称

--------

获得城堡的相关信息（第二级）
---------
+ 请求数据
    + mod：battle
    + act：castleList
    + chapterId：章节的id
+ 返回数据
    + castleList:为一个数组，每个元素为一个castle中的将领的详细信息列表
        + list：为一个数组，每个元素为将领的详细信息
            + id：将领的id
            + name：将领的名称
--------


获得将领的team信息
-------
+ 请求数据
    + mod:battle
    + act:pveTeam
    + battleId:
+ 返回数据
    + award：奖励
        + gold：金币
        + heroXp：英雄经验
        + honor：荣誉
        + xp：用户经验
    + pveTeam：
        + id：battleid
        + data:为数组，每个元素是相关将领数据
            + name：将领的名称
            + rid：将领的id
-----

战斗
------
+ 请求数据
    + mod：battle
    + act：pve
    + battleId
+ 返回数据
    + 见战斗返回数据相关文档

















