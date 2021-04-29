--获取KEY
local key1 = KEYS[1]
--获取参数
local uuid = ARGV[1]
local expireTm = ARGV[2]

local value = redis.call("get", key1)
if value then
    if value == uuid then
        redis.call("expire", key1, expireTm)
        return 1
    else
        return 0
    end
else
    return -1
end