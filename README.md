# Hotel Management API 🏨

Современное REST API на базе **Spring Boot** для управления каталогом отелей, их характеристиками и удобствами.

---

## 🚀 Технологический стек

- **Core:** Java 21, Spring Boot 4.0.6
- **Data:** Spring Data JPA, Hibernate, H2 (In-memory)
- **Validation:** Bean Validation (Jakarta)
- **Mapping:** MapStruct (высокопроизводительный маппинг DTO)
- **Testing:** JUnit 5, Mockito, MockMvc
- **Lombok:** Минимизация шаблонного кода

---

## 🛠 Ключевые особенности

- **Оптимизированные гистограммы:** Статистика по отелям (города, бренды, страны, удобства) рассчитывается на стороне базы данных с использованием агрегатных функций `GROUP BY`.
- **Гибкий поиск:** Поддержка фильтрации по названию, бренду, городу, стране и списку удобств.
- **Строгая валидация:** Все входящие данные проверяются на длину, формат и обязательность полей.
- **Поддержка Unicode:** Полная совместимость с кириллицей и спецсимволами в описаниях.

---

## 📖 API Эндпоинты

### Отели
- `GET /property-view/hotels` — Получить краткий список всех отелей.
- `GET /property-view/hotels/{id}` — Детальная информация об отеле.
- `POST /property-view/hotels` — Создать новый отель.
- `POST /property-view/hotels/{id}/amenities` — Массовое добавление удобств (amenities).

### Поиск и Статистика
- `GET /property-view/search` — Поиск с фильтрами.
    - Параметры: `name`, `brand`, `city`, `country`, `amenities`.
- `GET /property-view/histogram/{param}` — Статистика по параметру.
    - Параметры: `city`, `brand`, `country`, `amenities`.

---

## 🏗 Схема данных (DTO)

Пример создания отеля:
```json
{
  "name": "DoubleTree by Hilton",
  "description": "A luxury hotel in the heart of the city...",
  "brand": "Hilton",
  "address": {
    "houseNumber": 10,
    "street": "Pobediteley Ave",
    "city": "Minsk",
    "country": "Belarus",
    "postCode": "220004"
  },
  "contacts": {
    "phone": "+375 17 309-80-00",
    "email": "info@doubletree.com"
  },
  "arrivalTime": {
    "checkIn": "14:00",
    "checkOut": "12:00"
  }
}
```

---

## 🔧 Запуск проекта

1. **Сборка проекта:**
   ```bash
   mvn clean install
   ```
2. **Запуск тестов:**
   ```bash
   mvn test
   ```
3. **Запуск приложения:**
   ```bash
   mvn spring-boot:run
   ```

Сервис будет доступен по адресу: `http://localhost:8092'

---

## 🧪 Тестирование

Проект покрыт unit и интеграционными тестами:
- **Repository Tests:** Проверка JPA запросов и схемы БД.
- **Service Tests:** Бизнес-логика, работа с транзакциями и Hibernate.
- **Controller Tests:** Интеграционные тесты API с использованием `MockMvc`.

---

*Разработано в рамках тестового задания для GP Solutions.*