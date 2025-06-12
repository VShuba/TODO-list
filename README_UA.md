# TODO REST API – Практичне №7.2 (Складність: 5 б.)

Цей репозиторій містить CRUD REST API для застосунку зі списком справ (TODO), реалізований на базі Spring Boot. Призначений для швидкого розгортання та використання на AWS Elastic Beanstalk.

## Огляд проєкту

**Мета:** створити REST API, що підтримує управління станами завдань, ролі користувачів, багатомовність та автоматизоване розгортання.

## Функціонал

- CRUD API для задач TODO
- Поля задачі: заголовок, опис, дедлайн, статус
- Логіка переходів станів задач:
  - Початковий: `Planned`
  - Перехід можливий до: `Work_in_progress`, `Postponed`
  - Далі: `Notified`, `Signed`
  - Завершальний: `Done`
  - `Cancelled` можливий з будь-якого стану і є фінальним
- Уся логіка переходів станів повністю покрита unit-тестами

## Сутності

- **User** – користувач з авторизацією/аутентифікацією
- **Role** – окрема сутність, ролі призначаються користувачам
- **Task** – основна сутність TODO

## Безпека

- Spring Security з підтримкою Basic Auth, JWT та OAuth2 (перевірено)
- Розмежування доступу за ролями
- Керування ролями через окремий контролер

## AOP Логування

- Використано Spring AOP для логування викликів методів контролерів та сервісів

## Підтримка i18n

- API підтримує інтернаціоналізацію (i18n)
- Мова обирається через заголовок запиту або query-параметр
- Повідомлення про помилки локалізовані через message bundles

## Обробка помилок

- Кастомні винятки
- Централізована обробка помилок через `@ControllerAdvice`

## Документація

- Інтегрований Swagger/OpenAPI 3 UI
- Swagger підтримує Basic Auth
- Доступний за `/swagger-ui.html`

## Варіанти збереження

- In-memory (H2)
- PostgreSQL
- MariaDB

## Розгортання

- Застосунок пакується як виконуваний JAR
- Розгортається на AWS Elastic Beanstalk
- GitHub Actions використовується для CI/CD (через `beanstalk-deploy`)

### Корисні посилання

- [Стаття з розгортання в AWS](https://aws.plainenglish.io/deploy-spring-boot-applications-into-aws-using-aws-elastic-beanstalk-b2204d8fbd9c)
- [beanstalk-deploy GitHub Action](https://github.com/marketplace/actions/beanstalk-deploy)

## Shell-скрипти

Набір скриптів на `curl` для базового використання API:

- Створення користувача / призначення ролей
- Додавання TODO задачі
- Оновлення статусу задачі
- Отримання локалізованого списку задач

## Структура проєкту

```
├── ...todo_list
│   ├── aop                # Логування через Spring AOP
│   ├── config             # Налаштування застосунку, локалі, безпеки
│   ├── controllers        # REST контролери (Task, User, Role)
│   ├── dto                # DTO об'єкти
│   ├── exceptions         # Кастомні винятки
│   ├── models             # JPA-сутності (Task, User, Role)
│   ├── projections        # (Опціонально) інтерфейсні DTO-проекції
│   ├── repository         # Репозиторії Spring Data
│   ├── security           # Конфігурація безпеки, фільтри
│   ├── service            # Бізнес-логіка
│   └── TodoListApplication.java # Головна точка входу Spring Boot
├── resources
│   ├── static             # (Опціонально) статичні файли
│   ├── templates          # (Опціонально) шаблони для Thymeleaf
│   ├── application.yaml   # Основна конфігурація
│   ├── messages.properties     # Стандартні i18n-повідомлення
│   ├── messages_en.properties # Англійські повідомлення
│   └── messages_uk.properties # Українські повідомлення
└── test
```

## Підсумок

Цей проєкт демонструє:

- Логіку роботи зі станами (State Machine)
- Spring Security: Basic Auth, JWT, OAuth2
- Мультимовний REST API
- Централізовану обробку помилок
- Логування на основі AOP
- Розгортання на AWS через Elastic Beanstalk
- CI/CD через GitHub Actions
- Документування через Swagger
