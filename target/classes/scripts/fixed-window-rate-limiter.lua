local key=KEYS[1]

local limit = tonumber(ARGV[1])
local windowSizeMs = tonumber(ARGV[2])

local count = redis.call("INCR", key)

if count == 1 then
    redis.call( "PEXPIRE", key, windowSizeMs )
end

if count <= limit then
    local remaining = limit - count
    return { 1,  remaining, 0 }
end

local ttl = redis.call( "PTTL", key)

return { 0, 0, ttl }