Current Redis implementation still performs multiple Redis operations separately.

Under concurrent traffic or multi-node deployment this can lead to race conditions and inconsistent throttling behavior.

Future improvement:
Move logic into Redis Lua scripts for atomic execution.

## Why Lua Scripts Are Needed

The Redis Fixed Window implementation currently uses multiple Redis commands such as INCR, EXPIRE, and TTL.

Although Redis commands are individually atomic, the full rate-limiting decision is not atomic because these commands are executed separately.

This can cause issues under concurrent traffic or partial failures. For example, if the application increments a counter but crashes before setting expiry, the key may remain in Redis without TTL.

To solve this, the rate limiting decision will be moved into a Redis Lua script so increment, expiry, limit check, and retry-after calculation happen as one atomic operation.