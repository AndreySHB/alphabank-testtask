
**Сервис выдает случайную гиф определенного типа в зависимости от изменений курса выбранной валюты**

**Стэк**:

Spring Boot 2.7, Lombok, Feign

**Описание:**

• если курс по отношению к USD за сегодня выше вчерашнего, то отдается рандомная гифка отсюда https://giphy.com/search/rich

• если ниже - отсюда https://giphy.com/search/broke
Ссылки

• REST API курсов валют - https://docs.openexchangerates.org/

• REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide

**Запуск**:

1) Скопировать проект: git clone https://github.com/deerhunter9136/alphabank-testtask

2) Открыть как Gradle проект(build.gradle файл) и собрать

3) Убедиться что установлена SDK 11 или выше

4) Запустить AlphabankTesttaskApplication

Через Docker:

1) Открыть терминал из корневой папки с проектом

2) docker build -t alphatest .

3) docker run -p 8080:8080 alphatest

URL:http://localhost:8080/myapp/





