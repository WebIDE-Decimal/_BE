#!/bin/bash

sudo docker ps -a -q --filter "name=컨테이터이름" | grep -q . && docker stop 컨테이너이름 && docker rm 컨테이너이름 | true

# 기존 이미지 삭제
sudo docker rmi 도커계정이름/레포지토리:태그

# 도커허브 이미지 pull
sudo docker pull 도커계정이름/레포지토리:태그


# 도커 run
docker run -d -p 8080:8080 --name 컨테이너이름 도커계정이름/레포지토리:태그

# 사용하지 않는 불필요한 이미지 삭제 -> 현재 컨테이너가 물고 있는 이미지는 삭제되지 않습니다.
docker rmi -f $(docker images -f "dangling=true" -q) || true