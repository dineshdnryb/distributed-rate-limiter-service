Current Redis implementation still performs multiple Redis operations separately.

Under concurrent traffic or multi-node deployment this can lead to race conditions and inconsistent throttling behavior.

Future improvement:
Move logic into Redis Lua scripts for atomic execution.