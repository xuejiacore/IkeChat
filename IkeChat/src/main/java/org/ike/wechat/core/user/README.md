# 对话服务
## 用户管理API

- 创建用户分组
    API_UG_CREATE_USER_GROUP = API_USERS | 0x01
    必要参数：group 用户组实体
    可选参数：无

- 查询分组列表
    API_UG_QUERY_USER_GROUPS = API_USERS | 0x02
    必要参数：无
    可选参数：无

- 查询用户所在分组
    API_UG_QUERY_USER_GROUP_IN = API_USERS | 0x03
    必要参数：openid 用户的openid
    可选参数：无

- 修改用户所在分组
    API_UG_MODIFY_GROUP_NAME = API_USERS | 0x04
    必要参数：id 分组Id，由微信分配;name 分组名字（30个字符以内）
    可选参数：无

- 单移动用户至分组
    API_UG_MOVE_USER_2_GROUP = API_USERS | 0x05
    必要参数：openid 用户的openid;to_groupid 分组id
    可选参数：无

- 批量移动用户至分组
    API_UG_MOVE_USER_2_GROUP_BAT = API_USERS | 0x06
    必要参数：openid_list 需要移动的用户openid数组;to_groupid 分组id
    可选参数：无

- 删除分组
    API_UG_DELETE_USER_GROUP = API_USERS | 0x07
    必要参数：id 分组id
    可选参数：无

- 创建标签(注意与分组有交集)
    API_UT_CREATE_USER_TAG = API_USERS | 0x08
    必要参数：name 标签的名称
    可选参数：无

- 获取已经存在的标签
    API_UT_FETCH_TAGS = API_USERS | 0x09
    必要参数：无
    可选参数：无

- 编辑标签
    API_UT_EDIT_TAG = API_USERS | 0x0a
    必要参数：name 修改后的标签名称;id 标签ID
    可选参数：无

- 删除标签
    API_UT_DELETE_TAG = API_USERS | 0x0b
    必要参数：id 标签ID
    可选参数：无

- 根据标签获取粉丝列表
    API_UT_FETCH_FANS_BY_TAG = API_USERS | 0x0c
    必要参数：tagid 标签ID
    可选参数：无

- 批量为用户打标签
    API_UT_TAG_2_USER_BAT = API_USERS | 0x0d
    必要参数：openid_list 粉丝列表;tagid 标签ID
    可选参数：无

- 批量取消用户标签
    API_UT_RM_TAG_BAT = API_USERS | 0x0e
    必要参数：openid_list 粉丝列表;tagid 标签ID
    可选参数：无

- 获取用户身上的所有标签
    API_UT_FETCH_USER_TAGS = API_USERS | 0x0f
    必要参数：openid 用户的openid
    可选参数：无

- 为用户设置备注
    API_UR_SET_USER_REMARK = API_USERS | 0x10
    必要参数：openid 用户的openid;remark 备注名称
    可选参数：无

- 获得用户的信息
    API_UI_FETCH_USER_INFO = API_USERS | 0x20
    必要参数：openid 用户的openid
    可选参数：lang 国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语

- 获取用户列表
    API_UI_FETCH_USER_LIST = API_USERS | 0x30
    必要参数：无
    可选参数：next_openid 第一个拉取的OPENID，不填默认从头开始拉取
