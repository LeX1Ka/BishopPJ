# Bishop Prototype

`bishop-prototype` — это демо-приложение, использующее `synthetic-human-core-starter` для:
- AOP-аудита действий с отправкой событий в Kafka
- обработки команд (CRITICAL и COMMON) через очередь
- экспорта метрик через Micrometer
- глобальной обработки ошибок

---

## Состав проекта

- `synthetic-human-core-starter` — стартер-библиотека (AOP + Kafka + метрики + очередь)
- `bishop-prototype` — демо-приложение
- `docker-compose.yml` — инфраструктура (Kafka + Zookeeper)

---

## Запуск Kafka через Docker

```bash
docker-compose up -d
```
#### Kafka будет доступна на localhost:19092

## 2. Запуск приложения
```bash
cd bishop-prototype
mvn spring-boot:run
```

## Тестирование
### AOP-аудит

GET http://localhost:8080/api/command/hello?name=Ash
#### Результат в Kafka (audit-topic):

```
{
"className": "org.example.bishopprototype.service.DemoAuditService",
"methodName": "sayHello",
"arguments": ["Ash"],
"result": "Hello, Ash",
"timestamp": "2025-07-20T08:22:38.270702400Z"
}
```
#### Проверка:

```
docker exec -it kafka kafka-console-consumer --bootstrap-server kafka:29092 --topic audit-topic --from-beginning
```

## Команда в очередь
```
POST http://localhost:8080/api/command
Content-Type: application/json

{
"description": "Test command",
"priority": "COMMON",
"author": "Ash",
"time": "2025-07-20T11:11:11"
}
```

### Лог:
```
Выполнение COMMON команды: Test command
```

### Критическая команда
```
{
"description": "Shutdown system",
"priority": "CRITICAL",
"author": "Admin",
"time": "2025-07-20T11:11:11"
}
```
### Лог:
```
Critical Command
```
## Конфигурация
application.properties:

```
spring.application.name=bishop-prototype
spring.kafka.bootstrap-servers=localhost:19092
audit.mode=kafka
audit.kafkaTopic=audit-topic
```
## Метрики
#### queue.size — размер очереди команд

#### commands.executed.by.author — количество команд по авторам

##### Пример:

```
/actuator/metrics/commands.executed.by.author?tag=author:Ash
```