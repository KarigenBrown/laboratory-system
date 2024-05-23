# laboratory system

1. 在mysql中运行sql\currentWeb.sql脚本
2. 修改两个application.yml里面关于各个url的配置（MySQL，MinIO，Redis）
3. 在父工程（web）的pom.xml文件所在目录执行下述命令，即可获得web-admin和web-guest的jar包

```shell
mvn -DskipTests=true package
```

4. 在container文件夹执行下述命令，即可获得镜像

```shell
podman build -f .\Dockerfile-Admin -t lab-sys-admin ..
podman build -f .\Dockerfile-Guest -t lab-sys-guest ..
```

数据库和中间件的配置如container\docker-compose.yml中的文件所示，如有更改应同时更改web-admin和web-guest的application.yml文件