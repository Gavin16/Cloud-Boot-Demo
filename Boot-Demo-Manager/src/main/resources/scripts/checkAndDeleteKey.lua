--获取KEY
local key1 = KEYS[1]
--获取参数
local avg1 = ARGV[1]

--打印日志到reids
--注意，这里的打印日志级别，需要和redis.conf配置文件中的日志设置级别一致才行
redis.log(redis.LOG_WARNING,"key1=" ..key1)
redis.log(redis.LOG_WARNING,"avg=" ..avg1)

local var = redis.call("get", key1)
if var == avg1 then
    redis.call("del",key1)
    return 1
else
    return 0
end
