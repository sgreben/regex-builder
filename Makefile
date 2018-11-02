.PHONY: jar signed-jar export-gpg-key deploy-release deploy-snapshot publish-gpg-key

jar:
	mkdir -p target
	docker-compose build build-jar
	docker-compose run --rm build-jar
deploy-release: publish-gpg-key
	docker-compose build deploy-release
deploy-snapshot: publish-gpg-key
	docker-compose build deploy-snapshot
signed-jar: export-gpg-key
	mkdir -p target
	docker-compose build build-signed-jar
	docker-compose run --rm build-signed-jar
publish-gpg-key: export-gpg-key
	docker-compose build publish-gpg-key
	docker-compose run --rm publish-gpg-key
export-gpg-key: gpg-home
gpg-home:
	mkdir -p gpg-home
	docker-compose build export-gpg-key
	docker-compose run --rm export-gpg-key
