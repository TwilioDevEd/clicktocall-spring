install:
	./gradlew build

serve:
	. .env
	./gradlew bootRun --debug
