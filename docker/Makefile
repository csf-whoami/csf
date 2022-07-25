up: build
	@docker-compose up
up-sql: build-sql
	@docker-compose -f ./docker-compose.mysql.yml up -d
up-minio: build-minio
	@docker-compose -f ./docker-compose.mysql.yml up -d
up-no-cache:
	@docker-compose up -d --build --no-deps

stop:
	@docker-compose stop
stop-sql:
	@docker-compose -f ./docker-compose.mysql.yml stop

build:
	@docker-compose build
build-sql:
	@docker-compose -f ./docker-compose.mysql.yml build
build-minio:
	@docker-compose -f ./docker-compose.minio.yml build
build-no-cache:
	@docker-compose build --no-cache

clean:
	@docker ps -aq -f status=exited | xargs docker rm
	@docker images -q -f dangling=true | xargs docker rmi

clean-cache:
	@docker system prune
