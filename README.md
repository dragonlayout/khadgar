# khadgar

## Test

### Integration test
Integration test for mysql and redis, using [testcontainers](https://www.testcontainers.org/) need docker. 
To make testcontainers work with [colima](https://github.com/abiosoft/colima) on macOS, you need add following lines to
your `.zshrc` file.

```bash
export TESTCONTAINERS_DOCKER_SOCKET_OVERRIDE=/var/run/docker.sock
export DOCKER_HOST="unix://${HOME}/.colima/docker.sock"
```


