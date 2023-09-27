MAKEFILE := Makefile
COMPOSE_FILE := docker-compose.yml
DB_SERVICE := mysql-geektext

MYSQL_USER := root
MYSQL_PASSWORD := root
MYSQL_DATABASE := geektextdb

DUMMY_SQL := src/main/resources/dummy.sql

.PHONY: help
help:
	@echo "Usage: make [target]"
	@echo ""
	@echo "Available targets:"
	@awk '/^[a-zA-Z\-\_0-9]+:/ { \
		helpMessage = match(lastLine, /^## (.*)/); \
		if (helpMessage) { \
			helpCommand = substr($$1, 0, index($$1, ":")); \
			helpMessage = substr(lastLine, RSTART + 3, RLENGTH); \
			printf "  \033[36m%-20s\033[0m %s\n", helpCommand, helpMessage; \
		} \
	} \
	{ lastLine = $$0 }' $(MAKEFILE)

.PHONY: start
## Start application
start:
	docker-compose up --build

.PHONY: test
## Test application
test:
	./mvnw test

.PHONY: fmt-check
## Check project formatting
fmt-check:
	./mvnw spotless:check

.PHONY: fmt-apply
## Apply project formatting
fmt-apply:
	./mvnw spotless:apply

.PHONY: db-reset
## Reset database
db-reset: db-drop db-create

.PHONY: db-drop
db-drop:
	docker-compose -f $(COMPOSE_FILE) exec -T $(DB_SERVICE) mysql -u$(MYSQL_USER) -p$(MYSQL_PASSWORD) -e "DROP DATABASE IF EXISTS $(MYSQL_DATABASE)"
	@echo "Database $(MYSQL_DATABASE) dropped."

.PHONY: db-create
db-create:
	docker-compose -f $(COMPOSE_FILE) exec -T $(DB_SERVICE) mysql -u$(MYSQL_USER) -p$(MYSQL_PASSWORD) -e "CREATE DATABASE IF NOT EXISTS $(MYSQL_DATABASE)"
	@echo "Database $(MYSQL_DATABASE) created."

.PHONY: db-dummy
## Load dummy to database
db-dummy:
	docker-compose -f $(COMPOSE_FILE) exec -T $(DB_SERVICE) mysql -u$(MYSQL_USER) -p$(MYSQL_PASSWORD) $(MYSQL_DATABASE) < $(DUMMY_SQL)
	@echo "Dummy data loaded into $(MYSQL_DATABASE)."