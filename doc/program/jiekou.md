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
    + authTime	认证时间
    + authKey	认证key

-----

获得用户信息接口
------
+ 包括用户状态，用户将领，装备，背包信息等
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
		* levelUpXp 升级所需xp
		* fightForce	战斗力
		* castleTimer	上一次城堡收获的时间戳	
		* farmCastle	上一次农场收获的时间戳
    * userRoleList
        * id    用户角色id
        * name  用户角色名称
        * role_id   原角色id
        * level 当前等级
        + xp    当前经验
        + soldier   士兵的种类
        + weapon    武器信息
        + armor 护甲信息
        + accessory 饰品信息
        + fight_force    战斗力
        + attack    攻击力
        + defence   防御力
        + health    生命值
        + putongSkillId		普通技能id
        + tianfuSkillId		天赋技能id
    + userEquipList 装备列表
        + id    id
        + type      类型（武器，盔甲等）
        + name  名字
        + level 等级
        + strength  强化等级
    + userEqupPackkage
        + id    用户装备id
    + materialMapPackage
        + id    材料id
        + count 背包中此材料的数量
    + skillMapPackage
        + id    原技能id
        + count 背包中此技能的id
    + skillList
    	+ 该用户的所有技能
   	+ teamList(list)
   		+ id	team中的用户将领id

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
    + mod   tavern
    + act   invite
+ 相应数据
	* 相应数据为数组
	* 每个数组元素对应的内容为
		* gold:消耗的金币
		* status: 0为没有被招募，1已经被招募
		* id:对应的role的id

--------

雇佣将领
------
+ 请求数据
	* mod	tavern
	* act	enroll	
+ 响应数据
	* attack	攻击力
	* defence	防御力
	* fightForce	战斗力
	* health	生命值
	* id	邀请将领列表中的序号
	* level	等级
	* roleId	role的id
	* soldier	兵种的类别
	* uid	用户id

----


送走将领
--------
+ 请求数据
	* mod	tavern
	* act	leave
+ 响应数据
	* gold	获得金币的数量


------


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

装备接口
=======
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
		
商店相关接口
=====
商店信息
-----
+ 请求数据
	+ mod：shop
	+ act：itemList
+ 返回数据：
	+ category：类别
	+ currency：购买需要的货币 cash gold等
	+ id：商品id
	+ price：价格

------

购买商品
-----
+ 请求数据
	+ mod:shop
	+ act:buy
	+ num:数量
	+ id：商品id
+ 返回数据
	+ cost_num：花费
	+ cost_type：货币类型
	+ awards：获得的物品，为一个map
	    + key=material，获得的为材料
	    + value={id:xxx,count:yyyy}
	    + key=equip
	    + value=equipId的list

--------

战斗相关接口
=========
--------

战斗
------
+ 请求数据
    + mod：battle
    + act：pve
    + battleId
+ 返回数据
    + 见战斗返回数据相关文档

----

技能相关接口
=======
设置技能
-----


升级技能
-------


祖灵相关接口
=========
















